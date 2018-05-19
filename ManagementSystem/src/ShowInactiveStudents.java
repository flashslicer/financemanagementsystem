import javax.swing.JFrame;
import javax.swing.JDialog;
public class ShowInactiveStudents extends JDialog {
        JFrame frame = new TableInactiveStudents();
    public ShowInactiveStudents()
    {
        
        frame.setTitle("Archive for inactive Students");
        frame.setSize(1000, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
     public static void main(String[] args) 
    {
        new ShowInactiveStudents();
    }
    
}
