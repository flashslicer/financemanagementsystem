import javax.swing.JDialog;
import javax.swing.JFrame;
public class ShowTeachers extends JDialog {
      JFrame frame = new TableTeachers();
    public ShowTeachers()
    {
        
        frame.setTitle("Teacher Table");
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
     public static void main(String[] args) 
    {
        new ShowTeachers();
    }
    
}
