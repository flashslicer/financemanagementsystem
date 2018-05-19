import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
public class StudentAdd extends JDialog
{
   //private final JFrame stud;
   
    
    public StudentAdd()
    {
        
        JPanel studentPanel = new JPanel(new GridBagLayout());
        
         
        GridBagConstraints constraints =  new  GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets= new Insets(20,20,20,20);
        
        constraints.gridx=0;
        constraints.gridy=0;
        studentPanel.add(studentid, constraints);
        
        constraints.gridy=1;
        studentPanel.add(fname, constraints);
        
        constraints.gridy=2;
        studentPanel.add(lname,constraints);
        
        constraints.gridy=3;
        studentPanel.add(address,constraints);
        
        constraints.gridy=4;
        studentPanel.add(gender,constraints);
        
        constraints.gridy=5;
        studentPanel.add(studentLabel,constraints);
        
        constraints.gridy=6;
        studentPanel.add(phoneNumber,constraints);
        
        constraints.gridy =7;
        studentPanel.add(numberofModules,constraints);
        
        constraints.gridy=8;
        studentPanel.add(numberofRepeatedModules,constraints);
        
        constraints.gridy=9;
        studentPanel.add(amountpaid,constraints);
        
        constraints.gridx=1;
        constraints.gridy=0;
        studentPanel.add(idTextField,constraints);
        
        constraints.gridx=1;
        constraints.gridy=1;
        studentPanel.add(fnameTextField,constraints);
        
        constraints.gridx=1;
        constraints.gridy=2;
        studentPanel.add(lnameTextField,constraints);
        
        constraints.gridx=1;
        constraints.gridy=3;
        studentPanel.add(addressTextField,constraints);
        
        constraints.gridx=1;
        constraints.gridy=4;
        studentPanel.add(genderBox,constraints);
        genderBox.setSelectedIndex(0);
        
        constraints.gridx=1;
        constraints.gridy=5;
        studentPanel.add(studentBox,constraints);
        studentBox.setSelectedIndex(0);
        
        constraints.gridx=1;
        constraints.gridy=6;
        studentPanel.add(pNumberTextField,constraints);
        
        constraints.gridx=1;
        constraints.gridy=7;
        studentPanel.add(nMTextField,constraints);
        
        
        constraints.gridx=1;
        constraints.gridy=8;
        studentPanel.add(nRMTextField,constraints);
        nRMTextField.setText("0");
        nRMTextField.setEnabled(false);
        
        constraints.gridx=1;
        constraints.gridy=9;
        studentPanel.add(amountTextField,constraints);
        
        constraints.gridx=6;
        constraints.gridy=1;
        constraints.gridwidth=1;
        studentPanel.add(add,constraints);
        
        constraints.gridx=6;
        constraints.gridy=2;
        constraints.gridwidth=1;
        studentPanel.add(update,constraints);
        
        constraints.gridx=6;
        constraints.gridy=3;
        constraints.gridwidth=1;
        studentPanel.add(cancel,constraints);
        
        constraints.gridx=6;
        constraints.gridy=4;
        constraints.gridwidth=1;
        studentPanel.add(settings,constraints);
        
         constraints.gridx=6;
        constraints.gridy=5;
        constraints.gridwidth=1;
        studentPanel.add(pay,constraints);
        
        pay.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Component comp = SwingUtilities.getRoot(studentPanel);   
                
                ((Window) comp).dispose(); 
                FeeDeposit feeStud = new FeeDeposit();
                feeStud.setVisible(true);
            }
        });
        
        settings.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                SettingsGUI n = new SettingsGUI();
                n.setVisible(true);
                
            }
        });
        
       add.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e)
           {
               try {
                  
                   
                   DBConnection db = new DBConnection();
                   //Connection conn = DriverManager.getConnection("jdbc:derby:/home/michael/.netbeans/8.2/derby/management");
                   Statement stmt = db.conn.createStatement();
                    Student stud = new Student();
                    SettingsGUI sett = new SettingsGUI();
                    
                   stud.setId(Integer.parseInt(idTextField.getText()));
                   stud.setfName(fnameTextField.getText());
                   stud.setlName(lnameTextField.getText());
                   stud.setAddress(addressTextField.getText());
                   stud.setGender(genderBox.getSelectedItem().toString());
                   stud.setPhoneNumber(Long.parseLong(pNumberTextField.getText()));
                   stud.setNumberOfModules(Integer.parseInt(nMTextField.getText()));
                   stud.setNumberofRepModules(Integer.parseInt(nRMTextField.getText()));
                   stud.setStudentActive(studentBox.getSelectedItem().toString());
                   stud.setAmountPaid(Integer.parseInt(amountTextField.getText()));
                   int intModuleTotal =Integer.parseInt(sett.pmTextField.getText());
                   int intRModuleTotal = Integer.parseInt(sett.prTextField.getText());
                   int totalModules = stud.getNumberOfModules() * intModuleTotal;
                   int totalRModules = stud.getNumberofRepModules() * intRModuleTotal;
                   int getTotalAmount =totalModules + totalRModules;
                   int getTotalAmounts = getTotalAmount - stud.getAmountPaid();
                   
                   if(stud.getNumberOfModules() > 6 )
                   {
                       JOptionPane.showMessageDialog(null,"You can only enroll 6 modules");
                   
                   }
                   else
                   {
                       
                       stmt.executeUpdate("insert into app.student "+ "(stud_id,fname,lname,address,gender,phonenumber,status) "+
                           "values "+"( "+stud.getId()+","+"'"+stud.getfName()+"'"+","+"'"+stud.getlname()+"'"+","+"'"+stud.getAddress()+"'"+
                           ","+"'"+stud.getGender()+"'"+","+"'"+stud.getPhoneNumber()+"'"+","+"'"+stud.getStudentActive()+"'"+")");
                   
                   stmt.executeUpdate("insert into app.modules "+"(stud_id,numberofmodules,numberofrepeatedmodules,amountpaid,totaltuition) "+
                                     "values "+"( "+stud.getId()+","+stud.getNumberOfModules()+","+stud.getNumberofRepModules()+","+stud.getAmountPaid()+","+
                           getTotalAmounts+")");
                   JOptionPane.showMessageDialog(null,"Added Records");
                  
                   stmt.close();
                       
                   }
                   
               } catch (SQLException ex) {
                   JOptionPane.showMessageDialog(null,ex.toString());
                   
               }
              
           }
           
       });
        
       
        update.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Component comp = SwingUtilities.getRoot(studentPanel);   
                
                ((Window) comp).dispose(); 
                StudentUpdate updateStud = new StudentUpdate();
                updateStud.setVisible(true);
            }
        });
        cancel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                
                //stud.setVisible(true); 
                Component comp = SwingUtilities.getRoot(studentPanel);   
                
                ((Window) comp).dispose(); 
                
                 
                 
            }
        });
       
       studentPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Add Student")); 
       add(studentPanel);
        
        pack();
        setLocationRelativeTo(null);
          
        
          
    }
    
    public static void main(String args[])
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex)
        {
            ex.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            new StudentAdd().setVisible(true);
        }); 
        
        
    }
    private final JButton add= new JButton("Add");
    private final JButton cancel = new JButton("Cancel"); 
    private final JButton update = new JButton("Update");
    private final JButton settings = new JButton("Settings");
    private final JButton pay = new JButton("Pay tuition");
    private final JLabel studentid = new JLabel("Student ID :");;
    private final JLabel fname = new JLabel("First Name :");
    private final JLabel lname = new JLabel("Last Name :");
    private final JLabel address = new JLabel("Address :");
    private final JLabel gender = new JLabel("Gender :");
    private final JLabel studentLabel = new JLabel("Status:");
    private final JLabel phoneNumber = new JLabel("Phone Number : ");
    private final JLabel numberofModules = new JLabel("Number of Modules:");
    private final JLabel numberofRepeatedModules = new JLabel("Number of Repeated Modules:");
    private final JLabel amountpaid = new JLabel("Amount Paid:");
    private final JTextField  idTextField = new JTextField(30); 
    private final JTextField fnameTextField = new JTextField(30); 
    private final JTextField lnameTextField= new JTextField(30);
    private final JTextField addressTextField= new JTextField(30);
    public final JTextField pNumberTextField = new JTextField(30);
    private final JTextField nMTextField = new JTextField(30);
    private final JTextField  nRMTextField = new JTextField(30);
    private final JTextField amountTextField = new JTextField(30);
    private final String [] studentActive ={"Active","Inactive"};
    private final JComboBox studentBox = new JComboBox(studentActive);
    private final String [] genderAdd= {"Male", "Female", "Others"};
    private final JComboBox genderBox = new JComboBox(genderAdd);
}
