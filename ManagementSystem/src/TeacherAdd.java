import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TeacherAdd extends JDialog
{
    
    public TeacherAdd()
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
        
        /*constraints.gridy=9;
        teacherPanel.add(numberOfTeachinghours,constraints);*/
        
        constraints.gridx=1;
        constraints.gridy=0;
        teacherPanel.add(idTextField,constraints);
        
        constraints.gridx=1;
        constraints.gridy=1;
        teacherPanel.add(fnameTextField,constraints);
        
        constraints.gridx=1;
        constraints.gridy=2;
        teacherPanel.add(lnameTextField,constraints);
        
        constraints.gridx=1;
        constraints.gridy=3;
        teacherPanel.add(addressTextField,constraints);
        
        constraints.gridx=1;
        constraints.gridy=4;
        teacherPanel.add(genderBox,constraints);
        genderBox.setSelectedIndex(0);
        
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
        
       /* constraints.gridx=1;
        constraints.gridy=9;
        teacherPanel.add(teachingHoursTextField,constraints);*/
        
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
        
        /*constraints.gridx=6;
        constraints.gridy=4;
        constraints.gridwidth=1;
        teacherPanel.add(settings,constraints);*/
        
        constraints.gridx=6;
        constraints.gridy=4;
        constraints.gridwidth=1;
        teacherPanel.add(salary,constraints);
        
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
        
        
        
       add.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e)
          {
              try {
                  DBConnection db = new DBConnection();
                  //Connection conn = DriverManager.getConnection("jdbc:derby:/home/michael/.netbeans/8.2/derby/management");
                  Statement stmt = db.conn.createStatement();
                  Teacher teach = new Teacher();
                  SettingsGUI sett = new SettingsGUI();
                  teach.setId(Integer.parseInt(idTextField.getText()));
                  teach.setfName(fnameTextField.getText());
                  teach.setlName(lnameTextField.getText());
                  teach.setAddress(addressTextField.getText());
                  teach.setGender(genderBox.getSelectedItem().toString());
                  teach.setDepartment(departmentBox.getSelectedItem().toString());
                  teach.setDesignation(designationBox.getSelectedItem().toString());
                  teach.setPhoneNumber(Long.parseLong(pNumberTextField.getText()));
                  //teach.setNumberofteachinghours(Integer.parseInt(numberOfTeachinghours.getText()));
                  teach.setTeacherStatus(teacherBox.getSelectedItem().toString());
                  //int moneySalary= Integer.parseInt(sett.nofTextField.getText());
                  //int totalSalary = teach.getNumberofteachinghours() + moneySalary;
                  //insert into app.teacher(?) values (?)
                  String sql="insert into teachers (teach_id,fname,lname,address,gender,phonenumber,status,department,designation)"
                  +"values ("+teach.getId()+","+"'"+teach.getfName()+"'"+","+"'"+teach.getlname()+"'"+","+"'"+teach.getAddress()+"'"+","+"'"+teach.getGender()+"'"+","+"'"+teach.getPhoneNumber()+"'"+","
                            +"'"+teach.getTeacherStatus()+"'"+","+"'"+teach.getDepartment()+"'"+","+"'"+teach.getDesignation()+"'"+")";
                  String sql2="insert into salary(teach_id)"+"values("+teach.getId()+")";
                  stmt.executeUpdate(sql);
                  stmt.executeUpdate(sql2);
                  JOptionPane.showMessageDialog(null,"Added Records");
                  stmt.close();
                  
                  
              } catch (SQLException ex) {
                  Logger.getLogger(TeacherAdd.class.getName()).log(Level.SEVERE, null, ex);
              }
              
          }
       });
        
        
        
        teacherPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Add Teacher"));
        
        add(teacherPanel);
        
        pack();
        setLocationRelativeTo(null);
        
        
        update.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Component comp = SwingUtilities.getRoot(teacherPanel);
                
                ((Window) comp).dispose();
                
                TeacherUpdate updateTeach = new TeacherUpdate();
                updateTeach.setVisible(true);
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
    private final JButton edit = new JButton("Edit");
    private final JButton cancel = new JButton("Cancel");
    private final JButton update = new JButton("Update");
    private final JButton settings = new JButton("Settings");
    private final JButton salary = new JButton("Salary");
    private final JLabel teacherid = new JLabel("Teacher ID:");
    private final JLabel fname = new JLabel("First Name:");
    private final JLabel lname = new JLabel("Last Name:");
    private final JLabel address = new JLabel("Address:");
    private final JLabel gender = new JLabel("Gender");
    private final JLabel phoneNumber = new JLabel("Phone Number:");
    private final JLabel numberOfTeachinghours = new JLabel("Number of teaching hours:");
    private final JLabel department = new JLabel("Department:");
    private final JLabel designation = new JLabel("Designation:");
    private final JLabel teacherstatus = new JLabel("Status:");
    private final JTextField idTextField = new JTextField(30);
    private final JTextField fnameTextField = new JTextField(30);
    private final JTextField lnameTextField = new JTextField(30);
    private final JTextField addressTextField = new JTextField(30);
    private final JTextField pNumberTextField = new JTextField(30);
    private final JTextField teachingHoursTextField = new JTextField(30);
    private final String statusAdd[]={"Active","Inactive"};
    private final JComboBox teacherBox = new JComboBox(statusAdd);
    private final String genderAdd[]={"Male","Female","Others"};
    private final JComboBox genderBox = new JComboBox(genderAdd);
    private final String designationList[]={"HO","CO","L"};
    private final JComboBox designationBox = new JComboBox(designationList);
    private final String departmentList[]={"Business","Computing"};
    private final JComboBox departmentBox = new JComboBox(departmentList);
}
