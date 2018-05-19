
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class StudentUpdate extends JDialog {
    
    

    public StudentUpdate()
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
        
       /* constraints.gridy =7;
        studentPanel.add(numberofModules,constraints);
        
        constraints.gridy=8;
        studentPanel.add(numberofRepeatedModules,constraints);*/
        
        constraints.gridx=1;
        constraints.gridy=0;
        studentPanel.add(idTextField,constraints);
        
        constraints.gridx=1;
        constraints.gridy=1;
        studentPanel.add(fnameTextField,constraints);
        fnameTextField.setEditable(false);
        
        constraints.gridx=1;
        constraints.gridy=2;
        studentPanel.add(lnameTextField,constraints);
        lnameTextField.setEditable(false);
        
        constraints.gridx=1;
        constraints.gridy=3;
        studentPanel.add(addressTextField,constraints);
        
        constraints.gridx=1;
        constraints.gridy=4;
        studentPanel.add(genderBox,constraints);
        genderBox.setSelectedIndex(0);
        genderBox.setEnabled(false);
        
        constraints.gridx=1;
        constraints.gridy=5;
        studentPanel.add(studentBox,constraints);
        studentBox.setSelectedIndex(0);
        
        constraints.gridx=1;
        constraints.gridy=6;
        studentPanel.add(pNumberTextField,constraints);
        
        
     /*   constraints.gridx=1;
        constraints.gridy=7;
        studentPanel.add(nMTextField,constraints);
        
        constraints.gridx=1;
        constraints.gridy=8;
        studentPanel.add(nRMTextField,constraints);*/
        
        
        
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
        studentPanel.add(search,constraints);
        
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
     
        
       
        add.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Component comp = SwingUtilities.getRoot(studentPanel);   
                
                ((Window) comp).dispose(); 
                StudentAdd addStud = new StudentAdd();
                addStud.setVisible(true);
            }
        });
        
        search.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    DBConnection db = new DBConnection();
                    //Connection conn = DriverManager.getConnection("jdbc:derby:/home/michael/.netbeans/8.2/derby/management");
                    PreparedStatement ps = db.conn.prepareStatement("select * from student where stud_id=?");
                    ps.setString(1,idTextField.getText());
                    
                        
                    
                    ResultSet rs =ps.executeQuery();
                    
                    if(rs.next())
                    {
                        fnameTextField.setText(rs.getString("fname"));
                        lnameTextField.setText(rs.getString("lname"));
                        addressTextField.setText(rs.getString("address"));
                        genderBox.setSelectedItem(rs.getString("gender"));
                        studentBox.setSelectedItem(rs.getString("status"));
                        pNumberTextField.setText(rs.getString("phonenumber"));
                        
                        
                    }else
                    {
                        JOptionPane.showMessageDialog(null,"Not Found");
                    }
                    ps.close();
                    db.conn.close();
                    
                } catch (SQLException ex) {
                    Logger.getLogger(StudentUpdate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        update.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    DBConnection db = new DBConnection();
                    //Connection conn = DriverManager.getConnection("jdbc:derby:/home/michael/.netbeans/8.2/derby/management");
                    PreparedStatement ps = db.conn.prepareStatement("update student set fname=?, lname=?, address=?, gender=?, phonenumber=?, status=? where stud_id="+idTextField.getText());
                    ps.setString(1,fnameTextField.getText());
                    ps.setString(2,lnameTextField.getText());
                    ps.setString(3,addressTextField.getText());
                    ps.setString(4,genderBox.getSelectedItem().toString());
                    ps.setString(6, studentBox.getSelectedItem().toString());
                    ps.setLong(5,Long.parseLong(pNumberTextField.getText()));
                    ps.executeUpdate();
                    ps.close();
                    JOptionPane.showMessageDialog(null,"Update Records");
                } catch (SQLException ex) {
                    Logger.getLogger(StudentUpdate.class.getName()).log(Level.SEVERE, null, ex);
                }
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
       
       studentPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Update Student")); 
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
            new StudentUpdate().setVisible(true);
        }); 
        
        
    }
    private final JButton add= new JButton("Add");
    private final JButton cancel = new JButton("Cancel"); 
    private final JButton update = new JButton("Update");
    private final JButton search = new JButton("Search");
    private final JButton pay = new JButton("Pay tuition");
    private final JLabel studentid = new JLabel("Student ID :");;
    private final JLabel fname = new JLabel("First Name :");
    private final JLabel lname = new JLabel("Last Name :");
    private final JLabel address = new JLabel("Address :");
    private final JLabel gender = new JLabel("Gender :");
    private final JLabel studentLabel = new JLabel("Status:");
    private final JLabel phoneNumber = new JLabel("Phone Number : ");
    private final JTextField  idTextField = new JTextField(30); 
    private final JTextField fnameTextField = new JTextField(30); 
    private final JTextField lnameTextField= new JTextField(30);
    private final JTextField addressTextField= new JTextField(30);
    private final JTextField pNumberTextField = new JTextField(30);
    private final String [] studentActive ={"Active","Inactive"};
    private final JComboBox studentBox = new JComboBox(studentActive);
    private final String [] genderAdd= {"Male", "Female", "Others"};
    private final JComboBox genderBox = new JComboBox(genderAdd);
    
}
