import java.lang.reflect.Field;
import java.time.LocalDate;

class Student {
    private String id;
    private String name;
    private String birthdate;

    Student(String id, String name, String birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Student) {
            Student temp = (Student) obj;
            if(this.id.equals(temp.id) && this.name.equals(temp.name) && this.birthdate.equals(temp.birthdate))
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (this.id.hashCode() + this.name.hashCode() + this.birthdate.hashCode());
    }
}
