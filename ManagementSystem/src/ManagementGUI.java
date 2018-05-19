import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ManagementGUI 
{
    JFrame guiFrame = new JFrame();
   JButton loginButton = new JButton("Login");
    JButton cancelButton = new JButton("Cancel");
    JTextField userTextField;
    JPasswordField passwordField;
    JLabel username,password, titleLabel;
  
    
    
    public ManagementGUI()
    {
        guiFrame.setLayout(null);
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Finance Management");
        guiFrame.setSize(400,300);
        guiFrame.setLocationRelativeTo(null);
        
        titleLabel = new JLabel("Welcome to ABC College's Finance System");
        titleLabel.setBounds(60,40,300,30);
        guiFrame.add(titleLabel);
        
        username = new JLabel("User Name");
        username.setBounds(50,120,200,25);//x,y,width,height
        guiFrame.add(username);
        
        userTextField = new JTextField("");
        userTextField.setBounds(140,120,200,25);//x,y,width.height
        guiFrame.add(userTextField);
        
        password = new JLabel("Password");
        password.setBounds(50,150,200,25);//x,y,width,height
        guiFrame.add(password);
         
        passwordField = new JPasswordField("");
        passwordField.setBounds(140,150,200,25);//x,y,width.height
        guiFrame.add(passwordField);
        
         
         loginButton = new JButton("Login");
         loginButton.setBounds(100,190,90,25); //x,y,width,height
         guiFrame.add(loginButton);
         
         cancelButton = new JButton("Cancel");
         cancelButton.setBounds(200,190,90,25); //x,y,width,height
         guiFrame.add(cancelButton);
        
         cancelButton.addActionListener(new ActionListener(){
             @Override
             public void actionPerformed(ActionEvent e)
             {
                 try {
                     
                     Connection conn = DriverManager.getConnection("jdbc:derby:;shutdown=true");
                     
                 } catch (SQLException ex) {
                     Logger.getLogger(ManagementGUI.class.getName()).log(Level.SEVERE, null, ex);
                     System.exit(0);
                 }
                 
                
             }
         });
         
         loginButton.addActionListener(new ActionListener(){
             @Override
             public void actionPerformed(ActionEvent e){
                 try {
                     
                     
                    //Connection conn = DriverManager.getConnection("jdbc:derby:/home/michael/.netbeans/8.2/derby/management");
                    //Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                   DBConnection db = new DBConnection();
                    
                     //Connection conn = DBConnection.getDBconnection();
                    // ResultSet rs = db.conn.createStatement().executeQuery("select * from USERTABLE");
                    Statement st = db.conn.createStatement();
                    db.createTable();
                    
                     ResultSet  rs = st.executeQuery("select * from USERTABLE");
                    
                     while(rs.next())
                     {
                         if(rs.getString(2).contains(userTextField.getText()) && rs.getString(3).contains(passwordField.getText()))
                         {
                             guiFrame.setVisible(false);
                             guiFrame.dispose();
                             MainMenu newFrame = new MainMenu();
                             newFrame.setVisible(true);
                         }
                         else
                         {
                             JOptionPane.showMessageDialog(null,"Wrong credentials");
                         }
                     }
                 } catch (SQLException ex) {
                     Logger.getLogger(ManagementGUI.class.getName()).log(Level.SEVERE, null, ex);
                 } 
             }
         });
        
        guiFrame.setVisible(true);
            
    }
    
   
    public static void main(String args[])
    {
        new ManagementGUI();
    }

  
    
}
