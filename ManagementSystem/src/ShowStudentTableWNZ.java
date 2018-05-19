
import javax.swing.JFrame;
import javax.swing.JDialog;
public class ShowStudentTableWNZ extends JDialog {
        JFrame frame = new TableStudentsWithNZ();
    public ShowStudentTableWNZ()
    {
        
        frame.setTitle("Student Table with non-zero balance");
        frame.setSize(1000, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
     public static void main(String[] args) 
    {
        new ShowStudentTableWNZ();
    }
}
