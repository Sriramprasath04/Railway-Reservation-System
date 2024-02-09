import java.util.*;

public class BookTickets {
    static int aLb = 1;
    static int aMb = 1;
    static int aUb = 1;
    static int aRac = 1;
    static int aWl = 1;

    static List<Integer> uBP = new ArrayList<Integer>(Arrays.asList(1));
    static List<Integer> mBP = new ArrayList<Integer>(Arrays.asList(1));
//    2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21
    static List<Integer> lBP = new ArrayList<Integer>(Arrays.asList(1));
    static List<Integer> rac = new ArrayList<Integer>(Arrays.asList(1));
    static List<Integer> wl = new ArrayList<Integer>(Arrays.asList(1));
    static Queue<Integer> racLis = new LinkedList<>();
    static Queue<Integer> wlLis = new LinkedList<>();
    static List<Integer> bLis = new ArrayList<>();
    static Map<Integer, Details> db = new HashMap<>();

    public void cancelTicket(int pId){
        Details de = db.get(pId);
        bLis.remove(Integer.valueOf(pId));
        db.remove(pId);
        int gsNo = de.sNo;
        System.out.println("----------------Cancel Successfully");
        if (de.allocated.equals("L")) {
            lBP.add(gsNo);
            aLb++;

        } else if (de.allocated.equals("M")) {
            mBP.add(gsNo);
            aMb++;
        } else if (de.allocated.equals("U")) {
            uBP.add(gsNo);
            aUb++;
        }

        if(!racLis.isEmpty()){
            Details PassFrmRAC = db.get(racLis.poll());
            int PFRsNo = PassFrmRAC.sNo;
            rac.add(PFRsNo);
            racLis.remove(PassFrmRAC.pId);
            aRac++;
            if(!wlLis.isEmpty()){
                Details PassFromWl = db.get(wlLis.poll());
                int PFWlid = PassFromWl.sNo;
                wl.add(PFWlid);
                wlLis.remove(PassFromWl.pId);

                PassFromWl.sNo = rac.get(0);
                PassFromWl.allocated = "RAC";
                rac.remove(0);
                racLis.add(PassFromWl.pId);
                aWl++;
                aRac--;
            }
            Main.bookTicket(PassFrmRAC);
        }
    }
    public void BookedTickets(){
        if(db.isEmpty()){
            System.out.println("No Details Found");
        } else {
            for(Map.Entry<Integer, Details> p: db.entrySet()){
                System.out.println("---------------------------------");
                System.out.println("Passenger Id: "+p.getKey()+
                        "\nPassenger Name: "+p.getValue().name+
                        "\nPassenger age: "+p.getValue().age+
                        "\nPassenger Gender: "+p.getValue().gender+
                        "\nAllocated Berth: "+p.getValue().sNo+p.getValue().allocated+
                        "\nChild Name: "+p.getValue().cName+
                        "\nChild Age: "+p.getValue().cAge);

                System.out.println("---------------------------------");
            }
        }
    }
    public void Avalibility(){
        System.out.println("Lower Berth: "+aLb+
                "\nMiddle Berth: "+aMb+
                "\nUpper Berth: "+aUb+
                "\nRAC Berth: "+aRac+
                "\nWaiting List: "+aWl+
                "\n------------------------------------");
    }
    public void wlTicket(Details d, int sNo, String wl){
        d.sNo = sNo;
        d.allocated = wl;
        wlLis.add(d.pId);
        db.put(d.pId, d);
        System.out.println("Passenger Id: "+d.pId+
                "\nPassenger Name: "+d.name+
                "\nPassenger age: "+d.age+
                "\nPassenger Gender: "+d.gender+
                "\nAllocated Berth: "+sNo+wl);
        System.out.println("----------------Booked Successfully");
    }


    public void bookTickets(Details d, int sNo, String allocated){
        d.sNo = sNo;
        d.allocated = allocated;
        db.put(d.pId, d);
        bLis.add(d.pId);
        System.out.println("Passenger Id: "+d.pId+
                "\nPassenger Name: "+d.name+
                "\nPassenger age: "+d.age+
                "\nPassenger Gender: "+d.gender+
                "\nAllocated Berth: "+sNo+allocated);
        System.out.println("----------------Booked Successfully");
    }

    public void racTicket(Details d, int sNo, String RACberth){
        d.sNo = sNo;
        d.allocated = RACberth;
        db.put(d.pId, d);
        racLis.add(d.pId);
        System.out.println("Passenger Id: "+d.pId+
                "\nPassenger Name: "+d.name+
                "\nPassenger age: "+d.age+
                "\nPassenger Gender: "+d.gender+
                "\nAllocated Berth: "+sNo+RACberth);
        System.out.println("----------------Booked Successfully");
    }




}

/*


 */