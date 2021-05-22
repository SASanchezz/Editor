
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.*;


public class Editor extends JFrame{
    Path CurrentPath = Paths.get(System.getProperty("user.dir"));
    static String regime = "pen";


    public Editor() {
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(FileMenu());
        menuBar.add(TextMenu());
        setJMenuBar(menuBar);

        add(ToolBar.Toolbar(), BorderLayout.WEST);

        setVisible(true);
    }




    private JMenu FileMenu() {
        JMenu menu = new JMenu("File");
        JMenuItem newFile = new JMenuItem(new NewAction());
        JMenuItem openFile = new JMenuItem(new OpenAction());
        //JMenuItem save = new JMenuItem(new SaveAction());
        JMenuItem exit = new JMenuItem(new ExitAction());

        menu.add(newFile);
        menu.add(openFile);
        menu.add(exit);
        return menu;
    }

    private JMenu TextMenu() {
        JMenu menu = new JMenu("Font");
        JMenuItem apply = new JMenuItem(new ApplyAction());
        menu.add(new JLabel("Font size: "));
        menu.add(new JTextField(3));
        menu.add(apply);
        return menu;
    }
    //-----------------------------------------------------------------------------
    // Command Apply
    class ApplyAction extends AbstractAction {
        private ApplyAction() {
            // Icon
            putValue(AbstractAction.SMALL_ICON, new ImageIcon("images/apply.png"));
            putValue(NAME, "Apply");
        }
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    //-----------------------------------------------------------------------------
    // Command Open
    class OpenAction extends AbstractAction {
        //private static final long serialVersionUID = 1L;
        private OpenAction() {
            // Icon
            putValue(AbstractAction.SMALL_ICON, new ImageIcon("images/open.png"));
            putValue(NAME, "Open");
        }
        public void actionPerformed(ActionEvent e) {
            InputWindow Input = new InputWindow();
            Input.button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String PATH = Input.TextField.getText();
                    // Make frame disappear
                    Input.setVisible(false);
                    Input.dispose();
                    File OpenedImage = new File (Paths.get(CurrentPath.toString(), "images", PATH).toString());
                    if (Input.Validate(PATH) && OpenedImage.exists()) {
                        Background(OpenedImage);
                    } else {
                        Input.new ErrorWindow();

                    }
                }
            });
        }

    }
    //-----------------------------------------------------------------------------
    // Command Exit
    class ExitAction extends AbstractAction {
        //private static final long serialVersionUID = 1L;
        private ExitAction() {
            // Icon and name settings
            putValue(AbstractAction.SMALL_ICON, new ImageIcon("images/exit.png"));
            putValue(NAME, "Exit");

        }
        // Action of button - exit
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    //-----------------------------------------------------------------------------
    // Command Open
    class NewAction extends AbstractAction {
        //private static final long serialVersionUID = 1L;
        private NewAction() {
            // Icon and name settings
            putValue(AbstractAction.SMALL_ICON, new ImageIcon("images/new.png"));
            putValue(NAME, "New");
        }
        public void actionPerformed(ActionEvent e) {
            NewBackground();
        }
    }

    //-----------------------------------------------------------------------------
    // Make Background
    public void Background(File Image) {
        JLabel background;
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ImageIcon img = new ImageIcon(Image.getPath());
        background = new JLabel("", img, JLabel.CENTER);
        background.setVerticalAlignment(JLabel.NORTH);
        //background.setHorizontalAlignment(JLabel.LEFT);
        background.setBounds(0, 0, getWidth(), getHeight());
        //panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        panel.add(ToolBar.Toolbar(), BorderLayout.WEST);
        panel.add(background, BorderLayout.CENTER);

        setSize(getWidth()+300, getHeight()+100);

        setContentPane(panel);
        setVisible(true);
    }
    //-----------------------------------------------------------------------------
    // Make Empty Background
    public void NewBackground() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(ToolBar.Toolbar(), BorderLayout.WEST);

        setContentPane(panel);

        setVisible(true);
    }


}
