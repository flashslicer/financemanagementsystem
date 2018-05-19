import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainMenu extends JFrame
{
  public  JFrame menuJFrame = new JFrame();
   JButton addStudent,addTeacher, exit, showStudentBalance, showStudBalanceWithNZ, showAllteachers, inActiveStudents,inActiveTeachers,enroll;
    public MainMenu()
    {
        menuJFrame.setLayout(null);
        menuJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuJFrame.setTitle("Finance Management System");
        menuJFrame.setSize(600, 800);
        menuJFrame.setLocationRelativeTo(null);
        
        addTeacher = new JButton("Teacher");
        addTeacher.setBounds(100,50,200,50);//x ,y ,width, height
        menuJFrame.add(addTeacher);
        
        addStudent = new JButton("Student");
        addStudent.setBounds(100,150,200,50);//x ,y ,width, height
        menuJFrame.add(addStudent);
        menuJFrame.setVisible(true);
        
        showStudentBalance = new JButton("Show student with zero balance");
        showStudentBalance.setBounds(100,250,350,50);
        menuJFrame.add(showStudentBalance);
        
        
        showStudBalanceWithNZ = new JButton("Show Student with non-zero balance");
        showStudBalanceWithNZ.setBounds(100,350,350,50);
        menuJFrame.add(showStudBalanceWithNZ);
        
        showAllteachers = new JButton("Show All Teachers");
        showAllteachers.setBounds(100,450,350,50);
        menuJFrame.add(showAllteachers);
        
        inActiveStudents = new JButton("Inactive Students");
        inActiveStudents.setBounds(100,550,350,50);
        menuJFrame.add(inActiveStudents);
        
         inActiveTeachers = new JButton("Inactive Teachers");
        inActiveTeachers.setBounds(100,650,350,50);
        menuJFrame.add(inActiveTeachers);
        
        enroll = new JButton("Enroll");
        enroll.setBounds(400,100,100,50);
        menuJFrame.add(enroll);
        
        exit = new JButton("Exit");
        exit.setBounds(400,170,100,50);
        menuJFrame.add(exit);
        
        enroll.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Enrollment newFrame = new Enrollment();
                newFrame.setVisible(true);
            }
        });
        
        inActiveStudents.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                     ShowInactiveStudents newFrame = new ShowInactiveStudents();
                             newFrame.setVisible(true); 
            }
        });
        
        inActiveTeachers.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e)
           {
               ShowInactiveTeachers newFrame = new ShowInactiveTeachers();
                             newFrame.setVisible(true); 
           }
        });
        
        showAllteachers.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ShowTeachers newFrame = new ShowTeachers();
                             newFrame.setVisible(true);  
            }
        });
        
        showStudBalanceWithNZ.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                 ShowStudentTableWNZ newFrame = new ShowStudentTableWNZ();
                             newFrame.setVisible(true);  
            }
        });
        
        showStudentBalance.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                           
                             ShowStudentTableZ newFrame = new ShowStudentTableZ();
                             newFrame.setVisible(true);     
            }
        });
        
        exit.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e)
           {
                try {
                    Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                     Connection conn = DriverManager.getConnection("jdbc:derby:;shutdown=true");
                     
                 } catch (SQLException ex) {
                     Logger.getLogger(ManagementGUI.class.getName()).log(Level.SEVERE, null, ex);
                     System.exit(0);
                 } catch (ClassNotFoundException ex) {
                   Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
               }
               
           }
        });
        
         addStudent.addActionListener(new ActionListener(){
             @Override
             public void actionPerformed(ActionEvent e)
             {
                 
                
                 StudentAdd guiStudent = new StudentAdd();
                 guiStudent.setVisible(true);
                 
             }
         });
         
         addTeacher.addActionListener(new ActionListener(){
             @Override
             public void actionPerformed(ActionEvent e)
             {
                 
                 
                 TeacherAdd guiTeacher = new TeacherAdd();
                 guiTeacher.setVisible(true);
                 
                     
                 
             }
         });
        
        
        
    }
    
    
    public static void main(String args[])
    {
        new MainMenu();
    }
}
