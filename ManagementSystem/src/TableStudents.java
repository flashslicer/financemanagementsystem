import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class TableStudents extends JFrame {
   DefaultTableModel model = new DefaultTableModel();
    Container cnt = this.getContentPane();
    JTable jtbl = new JTable(model);

public TableStudents()
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
              DBConnection db = new DBConnection();
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            //Connection conn = DriverManager.getConnection("jdbc:derby:/home/michael/.netbeans/8.2/derby/management");
            PreparedStatement pstm = db.conn.prepareStatement("SELECT * FROM app.student inner join app.modules on app.student.stud_id = app.modules.stud_id where app.modules.totaltuition <= 0 and app.student.status='Active'");
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
