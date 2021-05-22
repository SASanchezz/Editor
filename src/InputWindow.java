import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InputWindow extends JFrame{

//  Constructor
    public InputWindow(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 150);
        setLocationRelativeTo(null);
        // Add panel of input to general frame
        JPanel panel2 = InputBat();
        getContentPane().add(panel2, BorderLayout.SOUTH);
        // Label od enter
        JLabel EnterTheText = new JLabel("Enter the filepath with extension", SwingConstants.CENTER);
        EnterTheText.setFont(new Font("Arial", Font.PLAIN, 25));
        getContentPane().add(EnterTheText);
        setVisible(true);

    }
    /*
        Space for input
     */
    JTextField  TextField = new JTextField (20);
    JButton button = new JButton("Open");
    public JPanel InputBat() {

        JPanel panel = new JPanel();
        JLabel path = new JLabel("Path: ");
        TextField.setSize(420, 30);

        // Button to open file
        button.setSize(80,30);
        //Action of button


        panel.add(path);
        panel.add(TextField);
        panel.add(button);
        return panel;
    }
    /*
        Open entered file
     */

    public boolean Validate(String Path) {
        // Split file name and extension

        if (Path.split("\\.").length != 2) return false;
        else {
            return true;
        }
    }


    /*
        Error case
     */

    class ErrorWindow extends JFrame {
        public ErrorWindow() {
            setLocationRelativeTo(null);
            //  Error label
            setSize(300, 200);
            JLabel error_label = new JLabel("Wrong path name", SwingConstants.CENTER);
            error_label.setFont(new Font("Arial", Font.PLAIN, 30));
            error_label.setForeground(Color.red);
            // Back button
            JButton error_button = new JButton("Back");
            error_button.setSize(80, 30);
            error_button.setFont(new Font("Arial", Font.PLAIN, 25));
            // Action of BACK button
            error_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Make window disappear
                    setVisible(false);
                    dispose();
                }
            });
            add(error_button);
            add(BorderLayout.NORTH, error_label);
            setVisible(true);
        }
    }


}

