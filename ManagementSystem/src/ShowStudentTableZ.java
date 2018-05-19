import javax.swing.JDialog;
import javax.swing.JFrame;
public class ShowStudentTableZ  extends JDialog{
     JFrame frame = new TableStudents();
    public ShowStudentTableZ()
    {
        
        frame.setTitle("Student Tables");
        frame.setSize(1000, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
     public static void main(String[] args) 
    {
        new ShowStudentTableZ();
    }
}
