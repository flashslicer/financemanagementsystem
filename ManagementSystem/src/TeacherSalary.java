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
public class TeacherSalary extends JDialog 
{
    
    public TeacherSalary()
    {
        JPanel teacherPanel = new JPanel(new GridBagLayout());
        
        GridBagConstraints constraints =  new  GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets= new Insets(20,20,20,20);
        
        constraints.gridx=0;
        constraints.gridy=0;
        teacherPanel.add(teachid,constraints);
        
        constraints.gridy=1;
        teacherPanel.add(fname,constraints);
        
        constraints.gridy=2;
        teacherPanel.add(lname,constraints);
        
        constraints.gridy=3;
        teacherPanel.add(numberofteachinghours,constraints);
        
        constraints.gridy=4;
        teacherPanel.add(designation,constraints);
        
        constraints.gridy=5;
        teacherPanel.add(view,constraints);
        
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
        teacherPanel.add(teachingHoursTextField,constraints);
        
        
        constraints.gridx=1;
        constraints.gridy=4;
        teacherPanel.add(designationBox,constraints);
        designationBox.setSelectedIndex(0);
        designationBox.setEnabled(false);
        
        constraints.gridx=1;
        constraints.gridy=5;
        teacherPanel.add(balanceTextField,constraints);
        balanceTextField.setEditable(false);
        
        constraints.gridx=6;
        constraints.gridy=1;
        constraints.gridwidth=1;
        teacherPanel.add(search,constraints);
        
        constraints.gridx=6;
        constraints.gridy=2;
        constraints.gridwidth=1;
        teacherPanel.add(add,constraints);
        
        constraints.gridx=6;
        constraints.gridy=3;
        constraints.gridwidth=1;
        teacherPanel.add(update,constraints);
        
        constraints.gridx=6;
        constraints.gridy=4;
        constraints.gridwidth=1;
        teacherPanel.add(calculatesalary,constraints);
        
        constraints.gridx=6;
        constraints.gridy=5;
        constraints.gridwidth=1;
        teacherPanel.add(cancel,constraints);
        
        teacherPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Salary"));
        
        add(teacherPanel);
        
        pack();
        setLocationRelativeTo(null);
        calculatesalary.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    int hof = 8, co = 13, l = 18;
                    double ot,totalSal,ha,ma,ta,ns;
                    int teachhours = Integer.parseInt(teachingHoursTextField.getText());
                    DBConnection db = new DBConnection();
                    //Connection conn = DriverManager.getConnection("jdbc:derby:/home/michael/.netbeans/8.2/derby/management");
                    
                    switch (designationBox.getSelectedItem().toString()) {
                        case "HO":
                            {   
                               if(teachhours < hof)
                               {
                                    ot =teachhours;
                                totalSal = 1200 + ot;
                                ha = totalSal * 0.10;
                                ma = totalSal * 0.03;
                                ta = totalSal * 0.05;
                                ns = totalSal + ha + ma + ta;
                                PreparedStatement ps = db.conn.prepareStatement("update salary set  totalsalary=?, numberofteachinghours=? where teach_id="+idTextField.getText());
                                ps.setDouble(1, ns);
                                ps.setInt(2,Integer.parseInt(teachingHoursTextField.getText()));
                                ps.executeUpdate();
                                ps.close();
                                System.out.println("HO");
                               }
                               else
                               {
                                ot = (teachhours - hof) * 325;
                                totalSal = 1200 + ot;
                                ha = totalSal * 0.10;
                                ma = totalSal * 0.03;
                                ta = totalSal * 0.05;
                                ns = totalSal + ha + ma + ta;
                                PreparedStatement ps = db.conn.prepareStatement("update salary set  totalsalary=?, numberofteachinghours=? where teach_id="+idTextField.getText());
                                ps.setDouble(1, ns);
                                ps.setInt(2,Integer.parseInt(teachingHoursTextField.getText()));
                                ps.executeUpdate();
                                ps.close();
                                System.out.println("HO");
                               }
                               
                                break;
                            }
                        case "CO":
                            {
                                if(teachhours < co)
                                {
                                ot = teachhours;                               
                                totalSal = 1200 + ot;
                                ha = totalSal * 0.10;
                                ma = totalSal * 0.03;
                                ta = totalSal * 0.05;
                                ns = totalSal + ha + ma + ta;
                                PreparedStatement ps = db.conn.prepareStatement("update salary set  totalsalary=?, numberofteachinghours=? where teach_id="+idTextField.getText());
                                ps.setDouble(1, ns);
                                ps.setInt(2,Integer.parseInt(teachingHoursTextField.getText()));
                                ps.executeUpdate();
                                ps.close();
                                System.out.println("CO");
                                }
                                else
                                {
                                ot = (teachhours - co) * 325;                               
                                totalSal = 1200 + ot;
                                ha = totalSal * 0.10;
                                ma = totalSal * 0.03;
                                ta = totalSal * 0.05;
                                ns = totalSal + ha + ma + ta;
                                PreparedStatement ps = db.conn.prepareStatement("update salary set  totalsalary=?, numberofteachinghours=? where teach_id="+idTextField.getText());
                                ps.setDouble(1, ns);
                                ps.setInt(2,Integer.parseInt(teachingHoursTextField.getText()));
                                ps.executeUpdate();
                                ps.close();
                                System.out.println("CO");
                                }
                                break;
                            }
                        case "L":
                            {
                                if(teachhours < l)
                                {
                                     ot = teachhours;                               
                                        totalSal = 1200 + ot;
                                        ha = totalSal * 0.10;
                                        ma = totalSal * 0.03;
                                        ta = totalSal * 0.05;
                                        ns = totalSal + ha + ma + ta;
                                        PreparedStatement ps = db.conn.prepareStatement("update salary set  totalsalary=?, numberofteachinghours=? where teach_id="+idTextField.getText());
                                        ps.setDouble(1, ns);
                                        ps.setInt(2,Integer.parseInt(teachingHoursTextField.getText()));
                                        ps.executeUpdate();
                                        ps.close();
                                        System.out.println("L");
                                }
                                else
                                {
                                    
                                        ot = (teachhours - l) * 325;                               
                                        totalSal = 1200 + ot;
                                        ha = totalSal * 0.10;
                                        ma = totalSal * 0.03;
                                        ta = totalSal * 0.05;
                                        ns = totalSal + ha + ma + ta;
                                        PreparedStatement ps = db.conn.prepareStatement("update salary set  totalsalary=?, numberofteachinghours=? where teach_id="+idTextField.getText());
                                        ps.setDouble(1, ns);
                                        ps.setInt(2,Integer.parseInt(teachingHoursTextField.getText()));
                                        ps.executeUpdate();
                                        ps.close();
                                        System.out.println("L");
                                }
                                break;
                            }
                        default:
                            break;
                    }
                   
                } catch (SQLException ex) {
                    Logger.getLogger(TeacherSalary.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        search.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    DBConnection db = new DBConnection();
                   // Connection conn = DriverManager.getConnection("jdbc:derby:/home/michael/.netbeans/8.2/derby/management");
                    PreparedStatement ps = db.conn.prepareStatement("select * from salary where teach_id =?");
                    PreparedStatement ps1 = db.conn.prepareStatement("select * from teachers where teach_id =?");
                    ps.setString(1,idTextField.getText());
                    ps1.setString(1,idTextField.getText());
                    
                     ResultSet rs =ps.executeQuery();
                     ResultSet rs1 =ps1.executeQuery();
                     
                     if(rs.next())
                     {
                         teachingHoursTextField.setText(rs.getString("numberofteachinghours"));
                         balanceTextField.setText(rs.getString("totalsalary"));
                         
                     }
                     
                     if(rs1.next())
                     {
                        fnameTextField.setText(rs1.getString("fname"));
                        lnameTextField.setText(rs1.getString("lname"));
                        designationBox.setSelectedItem(rs1.getString("designation"));
                     }
                     
                     ps.close();
                     ps1.close();
                     db.conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TeacherSalary.class.getName()).log(Level.SEVERE, null, ex);
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
            new TeacherSalary().setVisible(true);
        });
    }
    
    private final JButton  calculatesalary = new JButton("Calculate Salary");
    private final JButton search = new JButton("Search");
    private final JButton add = new JButton("Add");
    private final JButton update = new JButton("Update");
    private final JButton cancel = new JButton("Cancel");
    private final JLabel teachid = new JLabel("Teacher ID:");
    private final JLabel fname = new JLabel("First Name:");
    private final JLabel lname = new JLabel("Last Name:");
    private final JLabel numberofteachinghours = new JLabel("Number of teaching hours:");
    private final JLabel designation = new JLabel("Designation:");
    private final JLabel view = new JLabel("View Balance");
    private final JTextField balanceTextField = new JTextField(20);
    private final JTextField idTextField = new JTextField(20);
    private final JTextField fnameTextField = new JTextField(20);
    private final JTextField lnameTextField = new JTextField(20);
    private final JTextField teachingHoursTextField = new JTextField(20);
    private final String designationList[]={"HO","CO","L"};
    private final JComboBox designationBox = new JComboBox(designationList);
}
