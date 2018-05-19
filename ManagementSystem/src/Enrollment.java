import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Enrollment extends JDialog {
    
    public Enrollment()
    {
        JPanel studentPanel = new JPanel(new GridBagLayout());
        
         
        GridBagConstraints constraints =  new  GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets= new Insets(20,20,20,20);
        
        constraints.gridx=0;
        constraints.gridy=0;
        studentPanel.add(id, constraints);
        
        constraints.gridy=1;
        studentPanel.add(fname, constraints);
        
        constraints.gridy=2;
        studentPanel.add(lname,constraints);
        
        constraints.gridy=3;
        studentPanel.add(numberofmodules,constraints);
        
        constraints.gridy=4;
        studentPanel.add(numberofrepeatedmodules,constraints);
        
        constraints.gridy=5;
        studentPanel.add(amountpay,constraints);
        
        constraints.gridx=1;
        constraints.gridy=0;
        studentPanel.add(idTextField,constraints);
        
        constraints.gridx=1;
        constraints.gridy=1;
        studentPanel.add(firstNameLabel,constraints);
        
        constraints.gridx=1;
        constraints.gridy=2;
        studentPanel.add(lastNameLabel,constraints);
        
        constraints.gridx=1;
        constraints.gridy=3;
        studentPanel.add(pMTextField,constraints);
        
        constraints.gridx=1;
        constraints.gridy=4;
        studentPanel.add(pMRTextField,constraints);
        
        constraints.gridx=1;
        constraints.gridy=5;
        studentPanel.add(amountTextField,constraints);
        
        constraints.gridx=2;
        constraints.gridy=0;
        studentPanel.add(search,constraints);
        
        constraints.gridx=2;
        constraints.gridy=1;
        studentPanel.add(pay,constraints);
        
        constraints.gridx=2;
        constraints.gridy=2;
        studentPanel.add(cancel,constraints);
        
        cancel.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e)
           {
               Component comp = SwingUtilities.getRoot(studentPanel);   
                
                ((Window) comp).dispose(); 
           }
        });
        
        search.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e)
           {
               try {
                   DBConnection db = new DBConnection();
                   //Connection conn = DriverManager.getConnection("jdbc:derby:/home/michael/.netbeans/8.2/derby/management");
                   PreparedStatement ps = db.conn.prepareStatement("select * from student where  stud_id=?");
                   PreparedStatement ps1 = db.conn.prepareStatement("select * from modules where stud_id =?");
                   ps.setString(1,idTextField.getText());
                   ps1.setString(1,idTextField.getText());
                   
                   ResultSet rs =ps.executeQuery();
                   ResultSet rs1 =ps1.executeQuery();
                   
                    if(rs.next())
                     {
                        firstNameLabel.setText(rs.getString("fname"));
                        lastNameLabel.setText(rs.getString("lname"));
                         
                     }
                     
                     if(rs1.next())
                     {
                       pMTextField.setText(rs1.getString("numberofmodules"));
                       pMRTextField.setText(rs1.getString("numberofrepeatedmodules"));
                     }
                     
                     ps.close();
                     ps1.close();
                     db.conn.close();
               } catch (SQLException ex) {
                   JOptionPane.showMessageDialog(null, ex.toString());
               }
           }
        });
        
        pay.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e)
           {
               try {
                   DBConnection db = new DBConnection();
                   //Connection conn = DriverManager.getConnection("jdbc:derby:/home/michael/.netbeans/8.2/derby/management");
                   Student stud = new Student();
                    SettingsGUI sett = new SettingsGUI();
                    
                   stud.setNumberOfModules(Integer.parseInt(pMTextField.getText()));
                   stud.setNumberofRepModules(Integer.parseInt(pMRTextField.getText()));
                   stud.setAmountPaid(Integer.parseInt(amountTextField.getText()));
                   int intModuleTotal =Integer.parseInt(sett.pmTextField.getText());
                   int intRModuleTotal = Integer.parseInt(sett.prTextField.getText());
                   int totalModules = stud.getNumberOfModules() * intModuleTotal;
                   int totalRModules = stud.getNumberofRepModules() * intRModuleTotal;
                   int getTotalAmount =totalModules + totalRModules;
                   int getTotalAmounts = getTotalAmount - stud.getAmountPaid();
                   
                  
                  
                   if(stud.getNumberOfModules() > 6 || stud.getNumberofRepModules() > 2)
                   {
                       JOptionPane.showMessageDialog(null, "You can only enroll 6 subjects");
                   }
                   else
                   {
                       
                        PreparedStatement ps = db.conn.prepareStatement("update app.modules set  totaltuition=?, numberofmodules=?,numberofrepeatedmodules=? where stud_id="+idTextField.getText());
                        ps.setDouble(1,getTotalAmounts);
                        ps.setInt(2, Integer.parseInt(pMTextField.getText()));
                        ps.setInt(3, Integer.parseInt(pMRTextField.getText()));
                        ps.executeUpdate();
                        ps.close();
                        JOptionPane.showMessageDialog(null,"You are enrolled for this term");    
                   }
                   
               } catch (SQLException ex) {
                   Logger.getLogger(Enrollment.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
        });
        studentPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enrollment"));
        
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
            new Enrollment().setVisible(true);
        }); 
    }
    private JLabel id = new JLabel("Student ID:");
    private JLabel fname = new JLabel("First Name:");
    private JLabel lname = new JLabel("Last Name:");
    private JLabel numberofmodules = new JLabel("Number of Modules:");
    private JLabel numberofrepeatedmodules = new JLabel("Number of Repeated Modules:");
    private JLabel amountpay = new JLabel("Amount Paid:");
    private JLabel firstNameLabel = new JLabel("");
    private JLabel lastNameLabel = new JLabel("");
    private JTextField idTextField = new JTextField(20);
    private JTextField pMTextField = new JTextField(20);
    private JTextField pMRTextField = new JTextField(20);
    private JTextField amountTextField = new JTextField(20);
    private JButton search = new JButton("Search");
    private JButton pay = new JButton("Pay");
    private JButton cancel = new JButton("Cancel");
    
    
}
