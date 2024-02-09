import java.awt.print.Book;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("Railway Reservation System");
        System.out.println("--------------------------");
        Scanner sc = new Scanner(System.in);
        boolean exit = true;
        while(exit){
            System.out.println("\n1) Book" +
                    "\n2) Cancel" +
                    "\n3) Print Booked Tickets" +
                    "\n4) Print Available Ticket" +
                    "\n5) Exit" +
                    "\nEnter your choice: ");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    System.out.println("Enter your Name: ");
                    String name = sc.next();
                    System.out.println("Enter your age");
                    int age = sc.nextInt();
                    System.out.println("Enter your gender(M/F)");
                    String gender = sc.next();
                    String cName = null;
                    int cAge = 0;
                    if(gender.equals("F")){
                        System.out.println("Did you have a child? (If Yes(press 1) else (press 2)");
                        int val = sc.nextInt();
                        if(val == 1){
                            System.out.println("Enter child Name: ");
                            cName = sc.next();
                            System.out.println("Enter child Age: ");
                            cAge = sc.nextInt();
                            if(cAge>5){
                                System.out.println("You have to book a Separate ticket for your child");
                            }
                        }
                    }
                    System.out.println("Enter your Berth Preference(L/M/U)");
                    String bp = sc.next();
                    Details ob = new Details(name, age, gender, bp, cName, cAge);
                    bookTicket(ob);
                    break;
                case 2:
                    System.out.println("Enter your Passenger Id: ");
                    int id = sc.nextInt();
                    cancelTicket(id);
                    break;
                case 3:
                    System.out.println("Print Booked Tickets");
                    BookTickets show = new BookTickets();
                    show.BookedTickets();
                    break;
                case 4:
                    System.out.println("-----------------------------");
                    System.out.println("Available Tickets");
                    System.out.println("=================");
                    BookTickets obj = new BookTickets();
                    obj.Avalibility();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    exit = false;
                    break;
                default:
                    System.out.println("Invalid Choice ----!");
            }
        }
    }

    static void bookTicket(Details d){
        BookTickets bt = new BookTickets();
        if(BookTickets.aWl == 0){
            System.out.println("No tickets Available");
        } else if((d.bp.equals("L") || d.bp.equals("U") || d.bp.equals("M")) && (BookTickets.aLb > 0 || BookTickets.aUb > 0 || BookTickets.aMb > 0)){
            if((d.age>60 && BookTickets.aLb>0) || d.gender.equals("F") && d.cName!=null){
                if(d.gender.equals("F")){
                    System.out.println("Since you have a child Lower Berth Allocated");
                } else {
                    System.out.println("Since you are a Senior Citizen Lower Berth Allocated");
                }
                bt.bookTickets(d, BookTickets.lBP.get(0), "L");
                BookTickets.lBP.remove(0);
                BookTickets.aLb--;
            } else if (BookTickets.aLb>0 && d.bp.equals("L")){
                System.out.println("Lower Berth Allocated");
                bt.bookTickets(d, (BookTickets.lBP.get(0)), "L");
                BookTickets.lBP.remove(0);
                BookTickets.aLb--;
            } else if (BookTickets.aUb>0 && d.bp.equals("U")){
                System.out.println("Upper Berth Allocated");
                bt.bookTickets(d, BookTickets.uBP.get(0), "U");
                BookTickets.uBP.remove(0);
                BookTickets.aUb--;
            } else if (BookTickets.aMb>0 && d.bp.equals("M")) {
                System.out.println("Middle Berth Allocated");
                bt.bookTickets(d, BookTickets.mBP.get(0), "M");
                BookTickets.mBP.remove(0);
                BookTickets.aMb--;
            } else {
                if(BookTickets.aLb>0){
                    System.out.println("Lower Berth Given");
                    bt.bookTickets(d, BookTickets.lBP.get(0), "L");
                    BookTickets.lBP.remove(0);
                    BookTickets.aLb--;
                } else if (BookTickets.aMb>0) {
                    System.out.println("Middle Berth Given");
                    bt.bookTickets(d, BookTickets.uBP.get(0), "M");
                    BookTickets.mBP.remove(0);
                    BookTickets.aMb--;
                } else {
                    System.out.println("Upper Berth Given");
                    bt.bookTickets(d, BookTickets.uBP.get(0), "U");
                    BookTickets.uBP.remove(0);
                    BookTickets.aUb--;
                }
            }
        } else if (BookTickets.aRac > 0) {
            System.out.println("RAC Berth Allocated");
            bt.racTicket(d, BookTickets.rac.get(0), "RAC");
            BookTickets.rac.remove(0);
            BookTickets.aRac--;
        } else {
            System.out.println("Added to Waiting List");
            bt.wlTicket(d, BookTickets.wl.get(0), "WL");
            BookTickets.wl.remove(0);
            BookTickets.aWl--;
        }
    }

    public static void cancelTicket(int id){
        BookTickets bt = new BookTickets();
        if(!BookTickets.db.containsKey(id)){
            System.out.println("Passenger Id not found");
        } else {
            bt.cancelTicket(id);
        }
    }
}

/*

yuvaraj
21
M
M

sriram
21
M
M

*/