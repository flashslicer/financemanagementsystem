
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

public class FeeDeposit extends JDialog {
    
    public FeeDeposit()
    {
        JPanel studentPanel = new JPanel(new GridBagLayout());
        
         
        GridBagConstraints constraints =  new  GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets= new Insets(20,20,20,20);
        
        constraints.gridx=0;
        constraints.gridy=0;
        studentPanel.add(id,constraints);
        
        constraints.gridy=1;
        studentPanel.add(tuition,constraints);
        
        constraints.gridy=2;
        studentPanel.add(payment,constraints);
        
        constraints.gridx=1;
        constraints.gridy=0;
        studentPanel.add(idTextField,constraints);
        
        constraints.gridx=1;
        constraints.gridy=1;
        studentPanel.add(currentTuition,constraints);
        
        constraints.gridx=1;
        constraints.gridy=2;
        studentPanel.add(cashpay,constraints);
        
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
                
                //stud.setVisible(true); 
                Component comp = SwingUtilities.getRoot(studentPanel);   
                
                ((Window) comp).dispose(); 
                
                 
                 
            }
        });
        
        pay.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    DBConnection db = new DBConnection();
                    //Connection conn = DriverManager.getConnection("jdbc:derby:/home/michael/.netbeans/8.2/derby/management");
                    int payment = Integer.parseInt(currentTuition.getText());
                    int payamount = Integer.parseInt(cashpay.getText());
                    
                    if(payment <=0)
                    {
                        JOptionPane.showMessageDialog(null, "The tuition has been paid");
                    }
                    else
                    {
                        int total = payment - payamount;
                        String sql="update app.modules set  totaltuition=? where stud_id="+idTextField.getText();
                        PreparedStatement ps = db.conn.prepareStatement(sql);
                        ps.setDouble(1, total);
                        JOptionPane.showMessageDialog(null,"Paid");
                        ps.executeUpdate();
                        ps.close();
                        
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex.toString());
                }
            }
        });
        
        search.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e)
           {
               try {
                   DBConnection db = new DBConnection();
                   String sql="select * from app.modules where stud_id=?";
                   //Connection conn = DriverManager.getConnection("jdbc:derby:/home/michael/.netbeans/8.2/derby/management");
                   PreparedStatement ps = db.conn.prepareStatement(sql);
                   ps.setString(1,idTextField.getText());
                   ResultSet rs = ps.executeQuery();
                   
                   if(rs.next())
                   {
                      currentTuition.setText(rs.getString("totaltuition"));    
                   }
                   else
                   {
                       JOptionPane.showMessageDialog(null,"No such ID exist");
                   }
                   ps.close();
                   db.conn.close();
               } catch (SQLException ex) {
                   Logger.getLogger(FeeDeposit.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
        });
        
        
        studentPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Fee Deposit")); 
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
            new FeeDeposit().setVisible(true);
        }); 
    }
    
    private JButton pay = new JButton("Pay");
    private JButton search = new JButton("Search");
    private JButton cancel = new JButton("Cancel");
    private JLabel id = new JLabel("Student ID:");
    private JLabel tuition = new JLabel("Tuition");
    private JLabel currentTuition = new JLabel("0");
    private JLabel payment = new JLabel("Payment");
    private JTextField cashpay = new JTextField(20);
    private JTextField idTextField = new JTextField(20);
}
