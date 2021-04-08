public class Course {
    private String id;
    private String name;
    private int numOfCredit;

    Course(String id, String name, int numOfCredit) {
        this.id = id;
        this.name = name;
        this.numOfCredit = numOfCredit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfCredit() {
        return numOfCredit;
    }

    public void setNumOfCredit(int numOfCredit) {
        this.numOfCredit = numOfCredit;
    }
}
