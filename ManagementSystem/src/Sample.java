import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Sample {
    
    public static void main(String args[])
    {int num=1;
    String user="Admin";
    String pass="Admin";
        try {
            DBConnection db = new DBConnection();
          
          
            Connection conn = DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException ex) {
            Logger.getLogger(Sample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
