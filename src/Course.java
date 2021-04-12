public class Course {
    private String id;
    private String name;
    private String numOfCredit;

    Course(String id, String name, String numOfCredit) {
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

    public String getNumOfCredit() {
        return numOfCredit;
    }

    public void setNumOfCredit(String numOfCredit) {
        this.numOfCredit = numOfCredit;
    }
}
