import javax.swing.JDialog;
import javax.swing.JFrame;
public class ShowInactiveTeachers extends JDialog {
      JFrame frame = new TableInactiveTeachers();
    public ShowInactiveTeachers()
    {
        
        frame.setTitle("Teacher Table");
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
     public static void main(String[] args) 
    {
        new ShowInactiveTeachers();
    }
    
}
