import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DBConnection {
  private static final String DRIVER="org.apache.derby.jdbc.EmbeddedDriver";
  private static final String JDBC_URL = "jdbc:derby:management;create=true";
   Connection conn = null;
   /*public static Connection getConnection() throws SQLException, ClassNotFoundException{
        Class.forName(DRIVER);
        conn =DriverManager.getConnection(JDBC_URL);
        System.out.println("good");
        return conn;
    }*/
  
  public DBConnection()
  {
       try {
                if(conn==null)
                {
                        Class.forName(DRIVER);
                        conn =DriverManager.getConnection(JDBC_URL);
                        System.out.println("Good");
                }
      } catch (SQLException ex) {
          Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
      } catch (ClassNotFoundException ex) {
          Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
      }

         
     
  }
  
  /*public static Connection getDBconnection()
  {
    try {
                if(conn==null)
                {
                        Class.forName(DRIVER);
                        conn =DriverManager.getConnection(JDBC_URL);
                        System.out.println("Good");
                }
      } catch (SQLException ex) {
          Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
      } catch (ClassNotFoundException ex) {
          Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
      }
            return conn;
  }*/
 
  public void createTable()
  {
      try {
          int num=1;
          String user="admin";
          String pass="admin";
          String usertable ="CREATE TABLE USERTABLE (KEYNUM INTEGER NOT NULL, USERNAME VARCHAR(255), PASSWORD VARCHAR(255), PRIMARY KEY (KEYNUM))";
          String students= "create table student " +
                            "(" +
                            " stud_id int not null primary key," +
                            "fname varchar(255)," +
                            "lname varchar(255)," +
                            "address varchar(255)," +
                            "gender varchar(255)," +
                            "phonenumber varchar(255)," +
                            "status varchar(255)" +
                            "" +
                            ")";
          
          String teachers ="create table teachers" +
                            "(" +
                            "" +
                            "teach_id int not null primary key," +
                            "fname varchar(255)," +
                            "lname varchar(255)," +
                            "address varchar(255)," +
                            "gender varchar(255)," +
                            "phonenumber varchar(255)," +
                            "status varchar(255)," +
                            "department varchar(255)," +
                            "designation varchar(255)" +
                            "" +
                            ")";
          String modules ="Create table modules" +
                            "(" +
                            "   roll_id int not null primary key generated always as identity (start with 1, increment by 1)," +
                            "   stud_id int not null," +
                            "   numberofmodules int," +
                            "   numberofrepeatedmodules int default 0," +
                            "   amountpaid int," +
                            "   totaltuition int," +
                            "   constraint studid_fk foreign key (stud_id) references app.student(stud_id)" +
                            ")";
          
          String salary = "create table salary" +
                            "(" +
                            "  roll_id int not null primary key generated always as identity (start with 1, increment by 1)," +
                            "  teach_id int not null," +
                            "   numberofteachinghours int default 0," +
                            "   totalsalary int default 1200," +
                            "   constraint teachid_fk foreign key (teach_id) references app.teachers(teach_id)" +
                            ")";
          conn.createStatement().execute(usertable);
          conn.createStatement().execute(students);
          conn.createStatement().execute(teachers);
          conn.createStatement().execute(modules);
          conn.createStatement().execute(salary);
          insertToTable(num,user,pass);
          
      } catch (SQLException ex) {
          Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  
  public void insertToTable(int num, String user, String pass)
  {
     
      try {
          conn.createStatement().execute("insert into usertable values("+num+","+"'"+user+"'"+","+"'"+pass+"'"+")");
      } catch (SQLException ex) {
          Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
      }
     
  }

  
}
