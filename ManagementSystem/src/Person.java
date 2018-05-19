
public class Person {
    
    private String fname,lname,gender,address;
    private int id;
    private long phoneNumber;
    
    public void setfName(String fname)
    {
        this.fname = fname;
    }
    
    public void setlName(String lname)
    {
        this.lname = lname;
    }
    
    public void setGender(String gender)
    {
        this.gender = gender;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public void setPhoneNumber(long phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    
    public String getfName()
    {
        return fname;
    }
    public String getlname()
    {
        return lname;
    }
    public String getAddress()
    {
        return address;
    }
    
    public String getGender()
    {
        return gender;
    }
    
    public int getId()
    {
        return id;
    }
    
    public long getPhoneNumber()
    {
        return phoneNumber;
    }
}
