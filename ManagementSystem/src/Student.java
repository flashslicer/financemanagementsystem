
public class Student extends Person 
{
    private int numberOfModules,numberofRepModules;
    private int amountPaid;
    private String studentactive;
    
    public void setStudentActive(String studentactive)
    {
        this.studentactive = studentactive;
    }
    
    public String getStudentActive()
    {
        return studentactive;
    }
    

    public int getNumberOfModules() {
        return numberOfModules;
    }

    public void setNumberOfModules(int numberOfModules) {
        this.numberOfModules = numberOfModules;
    }

    public int getNumberofRepModules() {
        return numberofRepModules;
    }

    public void setNumberofRepModules(int numberofRepModules) {
        this.numberofRepModules = numberofRepModules;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }
    
    
    
    
}
