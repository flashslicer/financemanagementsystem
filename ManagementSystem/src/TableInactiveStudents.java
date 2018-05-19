
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TableInactiveStudents extends JFrame {
    DefaultTableModel model = new DefaultTableModel();
    Container cnt = this.getContentPane();
    JTable jtbl = new JTable(model);
    
    public TableInactiveStudents()
    {
        cnt.setLayout(new FlowLayout(FlowLayout.LEFT));
        model.addColumn("Student ID");
        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("Address");
        model.addColumn("Gender");
        model.addColumn("Phone Number");
        model.addColumn("Status");
        
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            DBConnection db = new DBConnection();
            //Connection conn = DriverManager.getConnection("jdbc:derby:/home/michael/.netbeans/8.2/derby/management");
            PreparedStatement pstm = db.conn.prepareStatement("SELECT * FROM app.student inner join app.modules on app.student.stud_id = app.modules.stud_id where app.student.status='Inactive'");
            ResultSet Rs = pstm.executeQuery();
            while(Rs.next()){
                model.addRow(new Object[]{Rs.getInt(1), Rs.getString(2),Rs.getString(3),Rs.getString(4),Rs.getString(5),Rs.getString(6),Rs.getString(7)});
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        JScrollPane pg = new JScrollPane(jtbl);
        pg.setPreferredSize(new Dimension(1000,800));
        cnt.add(pg);
        this.pack();
    }
}
