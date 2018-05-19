
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


public class TeacherUpdate extends JDialog {
     public TeacherUpdate()
    {
        
        JPanel teacherPanel = new JPanel(new GridBagLayout());
        
        GridBagConstraints constraints =  new  GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets= new Insets(20,20,20,20);
        
        constraints.gridx=0;
        constraints.gridy=0;
        teacherPanel.add(teacherid, constraints);
        
        constraints.gridy=1;
        teacherPanel.add(fname, constraints);
        
        constraints.gridy=2;
        teacherPanel.add(lname,constraints);
        
        constraints.gridy=3;
        teacherPanel.add(address,constraints);
        
        constraints.gridy=4;
        teacherPanel.add(gender,constraints);
        
        constraints.gridy=5;
        teacherPanel.add(phoneNumber,constraints);
        
        constraints.gridy=6;
        teacherPanel.add(designation,constraints);
        
        constraints.gridy=7;
        teacherPanel.add(department,constraints);
        
        
        constraints.gridy=8;
        teacherPanel.add(teacherstatus,constraints);
        
        /*constraints.gridy=8;
        teacherPanel.add(numberOfTeachinghours,constraints);*/
        
        constraints.gridx=1;
        constraints.gridy=0;
        teacherPanel.add(idTextField,constraints);
        
        constraints.gridx=1;
        constraints.gridy=1;
        teacherPanel.add(fnameTextField,constraints);
        fnameTextField.setEditable(false);
        
        constraints.gridx=1;
        constraints.gridy=2;
        teacherPanel.add(lnameTextField,constraints);
        lnameTextField.setEditable(false);
        
        constraints.gridx=1;
        constraints.gridy=3;
        teacherPanel.add(addressTextField,constraints);
        
        constraints.gridx=1;
        constraints.gridy=4;
        teacherPanel.add(genderBox,constraints);
        genderBox.setSelectedIndex(0);
        genderBox.setEnabled(false);
        
        constraints.gridx=1;
        constraints.gridy=5;
        teacherPanel.add(pNumberTextField,constraints);
        
        constraints.gridx=1;
        constraints.gridy=6;
        teacherPanel.add(designationBox,constraints);
        designationBox.setSelectedIndex(0);
        
        constraints.gridx=1;
        constraints.gridy=7;
        teacherPanel.add(departmentBox,constraints);
        departmentBox.setSelectedIndex(0);
        
        constraints.gridx=1;
        constraints.gridy=8;
        teacherPanel.add(teacherBox,constraints);
        teacherBox.setSelectedIndex(0);
        
        
        
        constraints.gridx=6;
        constraints.gridy=1;
        constraints.gridwidth=1;
        teacherPanel.add(add,constraints);
        
        constraints.gridx=6;
        constraints.gridy=2;
        constraints.gridwidth=1;
        teacherPanel.add(update,constraints);
        
        
        constraints.gridx=6;
        constraints.gridy=3;
        constraints.gridwidth=1;
        teacherPanel.add(cancel,constraints);
        
        constraints.gridx=6;
        constraints.gridy=4;
        constraints.gridwidth=1;
        teacherPanel.add(search,constraints);
        
        constraints.gridx=6;
        constraints.gridy=5;
        constraints.gridwidth=1;
        teacherPanel.add(salary,constraints);
        
        teacherPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Update Teacher"));
        
        add(teacherPanel);
        
        pack();
        setLocationRelativeTo(null);
        
        
        salary.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Component comp = SwingUtilities.getRoot(teacherPanel);
                
                ((Window) comp).dispose();
                
                TeacherSalary salaryTeach = new TeacherSalary();
                salaryTeach.setVisible(true);
            }
            
        });
        
        add.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Component comp = SwingUtilities.getRoot(teacherPanel);
                
                ((Window) comp).dispose();
                
                TeacherAdd addTeach = new TeacherAdd();
                addTeach.setVisible(true);
            }
        });
        
        search.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    DBConnection db = new DBConnection();
                    // conn = DriverManager.getConnection("jdbc:derby:/home/michael/.netbeans/8.2/derby/management");
                    PreparedStatement ps = db.conn.prepareStatement("select * from app.teachers where teach_id=?");
                    
                    ps.setString(1,idTextField.getText());
                    
                    ResultSet rs =ps.executeQuery();
                    
                    if(rs.next())
                    {
                        fnameTextField.setText(rs.getString("fname"));
                        lnameTextField.setText(rs.getString("lname"));
                        addressTextField.setText(rs.getString("address"));
                        genderBox.setSelectedItem(rs.getString("gender"));
                        pNumberTextField.setText(rs.getString("phonenumber"));
                        teacherBox.setSelectedItem(rs.getString("status"));
                        designationBox.setSelectedItem(rs.getString("designation"));
                        departmentBox.setSelectedItem(rs.getString("department"));
                    }else{
                        JOptionPane.showMessageDialog(null, "No Records");
                    }
                    ps.close();
                    db.conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TeacherUpdate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        update.addActionListener(new ActionListener()
        {
           @Override
           public void actionPerformed(ActionEvent e)
           {
               try {
                   DBConnection db = new DBConnection();
                  // Connection conn = DriverManager.getConnection("jdbc:derby:/home/michael/.netbeans/8.2/derby/management");
                   String sql="update teachers set fname=?, lname=?, address=?, gender=?, phonenumber=?, status=?, department=?, designation=? where teach_id="+idTextField.getText();
                   PreparedStatement ps = db.conn.prepareStatement(sql);
                    ps.setString(1,fnameTextField.getText());
                    ps.setString(2,lnameTextField.getText());
                    ps.setString(3,addressTextField.getText());
                    ps.setString(4,genderBox.getSelectedItem().toString());
                    ps.setLong(5,Long.parseLong(pNumberTextField.getText()));
                    ps.setString(6,teacherBox.getSelectedItem().toString());
                    ps.setString(7,departmentBox.getSelectedItem().toString());
                    ps.setString(8,designationBox.getSelectedItem().toString());
                    ps.executeUpdate();
                    ps.close();
                    JOptionPane.showMessageDialog(null,"Updated Records");
               } catch (SQLException ex) {
                   Logger.getLogger(TeacherUpdate.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
        });
         
        cancel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                
               
                Component comp = SwingUtilities.getRoot(teacherPanel);
                
                ((Window) comp).dispose(); 
                
               
                 
            }
        });
        
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
            new TeacherAdd().setVisible(true);
        });
        
        
    }
    
    private final JButton add = new JButton("Add");
    private final JButton cancel = new JButton("Cancel");
    private final JButton update = new JButton("Update");
    private final JButton search = new JButton("Search");
    private final JButton salary = new JButton("Salary");
    private final JLabel teacherid = new JLabel("Teacher ID:");
    private final JLabel fname = new JLabel("First Name:");
    private final JLabel lname = new JLabel("Last Name:");
    private final JLabel address = new JLabel("Address:");
    private final JLabel gender = new JLabel("Gender");
    private final JLabel phoneNumber = new JLabel("Phone Number:");
    private final JLabel designation = new JLabel("Designation:");
    private final JLabel department = new JLabel("Department:");
    private final JLabel teacherstatus = new JLabel("Status:");
    private final JLabel numberOfTeachinghours = new JLabel("Number of Teaching Hours:");
    private final JTextField idTextField = new JTextField(30);
    private final JTextField fnameTextField = new JTextField(30);
    private final JTextField lnameTextField = new JTextField(30);
    private final JTextField addressTextField = new JTextField(30);
    private final JTextField pNumberTextField = new JTextField(30);
    private final JTextField teachingHoursTextField = new JTextField(30);
    private final String teacherAdd[]={"Active","Inactive"};
    private final JComboBox teacherBox= new JComboBox(teacherAdd);
    private final String genderAdd[]={"Male","Female","Others"};
    private final JComboBox genderBox = new JComboBox(genderAdd);
    private final String designationList[]={"HO","CO","L"};
    private final JComboBox designationBox = new JComboBox(designationList);
    private final String departmentList[] = {"Business","Computing"};
    private final JComboBox departmentBox = new JComboBox(departmentList);
}
