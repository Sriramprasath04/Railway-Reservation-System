public class Details {
    static int id = 1;
    String name;
    int pId = id++;
    int age;
    String gender;
    String bp;
    String allocated;

    int sNo;
    String cName;
    int cAge;


    public Details(String name, int age, String gender, String bp, String cName, int cAge) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.bp = bp;
        this.allocated = " ";
        this.sNo = -1;
        this.cName = cName;
        this.cAge = cAge;
    }
}
