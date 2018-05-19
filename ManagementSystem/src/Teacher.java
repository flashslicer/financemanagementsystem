
public class Teacher extends Person {
    private String department,designation, status;
    private int numberofteachinghours;
    private double salary;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getNumberofteachinghours() {
        return numberofteachinghours;
    }

    public void setNumberofteachinghours(int numberofteachinghours) {
        this.numberofteachinghours = numberofteachinghours;
    }
    
    public String getTeacherStatus()
    {
        return status;
    }
    
    public void setTeacherStatus(String status)
    {
        this.status=status;
    }
    
  
    
}
