import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SettingsGUI extends JDialog {
    
   
    public SettingsGUI()
    {
        JPanel settingsPanel = new JPanel(new GridBagLayout());
        
         
        GridBagConstraints constraints =  new  GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets= new Insets(20,20,20,20);
        
        constraints.gridy=0;
        settingsPanel.add(priceofmodule,constraints);
        
        
        constraints.gridy=1;
        settingsPanel.add(priceofrepeatmodule,constraints);
        
      
        
       
        constraints.gridx=1;
        constraints.gridy=0;
        settingsPanel.add(pmTextField,constraints);
        pmTextField.setText("512");
        
        constraints.gridx=1;
        constraints.gridy=1;
        settingsPanel.add(prTextField,constraints);
        prTextField.setText("111");
        
       
        
        constraints.gridx=6;
        constraints.gridy=0;
        settingsPanel.add(ok,constraints);
        
        constraints.gridx=6;
        constraints.gridy=1;
        settingsPanel.add(cancel,constraints);
        
        ok.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Component comp = SwingUtilities.getRoot(settingsPanel);   
                
                
               ((Window) comp).dispose();
               
            }
        });
        
        cancel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                 Component comp = SwingUtilities.getRoot(settingsPanel);   
                
                
                ((Window) comp).dispose();
            }
        });
        
        settingsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Settings")); 
       add(settingsPanel);
        
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
            new SettingsGUI().setVisible(true);
        }); 
    }
    
  
    
    public JLabel priceofmodule = new JLabel("Module Price:");
    public JLabel priceofrepeatmodule = new JLabel("Repeated Module Price:");
  
    public JTextField pmTextField = new JTextField(20);
    public JTextField prTextField = new JTextField(20);
    public JTextField nofTextField = new JTextField(20);
    public JButton ok = new JButton("OK");
    public JButton cancel = new JButton("Cancel");
}
