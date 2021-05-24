
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;


public class Editor extends JFrame{
    Path CurrentPath = Paths.get(System.getProperty("user.dir"));
    static String regime = "pen";
    MyPanel MainPanel ;
    boolean mousePressed = false;
    int X_START;
    int Y_START;
    int X_UNPRESSED;
    int Y_UNPRESSED;

    public Editor() {
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainPanel = new MyPanel(new FlowLayout(FlowLayout.LEFT));

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(FileMenu());
        menuBar.add(TextMenu());

        setJMenuBar(menuBar);


        setContentPane(MainPanel);

        setVisible(true);


    }




    private JMenu FileMenu() {
        JMenu menu = new JMenu("File");
        JMenuItem newFile = new JMenuItem(new NewAction());
        JMenuItem openFile = new JMenuItem(new OpenAction());
        JMenuItem save = new JMenuItem(new SaveAction());
        JMenuItem exit = new JMenuItem(new ExitAction());

        menu.add(newFile);
        menu.add(openFile);
        menu.add(save);
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
    // Command Save
    class SaveAction extends AbstractAction {
        private SaveAction() {
            // Icon
            putValue(AbstractAction.SMALL_ICON, new ImageIcon("images/save.png"));
            putValue(NAME, "Save");
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            String fileFullName = null;
            try
            {
                JFileChooser jf= new  JFileChooser();
                if(OpenedImageFile==null)
                {
                    int  result = jf.showSaveDialog(null);
                    if(result==JFileChooser.APPROVE_OPTION)
                    {
                        fileFullName = jf.getSelectedFile().getAbsolutePath();
                        ImageIO.write(imag, "jpg", new  File(fileFullName+".jpg"));

                    }
                } else {
                    fileFullName = Paths.get(OpenedImageFile.getAbsolutePath()).toString();
                    ImageIO.write(imag, "jpg", new  File(fileFullName));
                }

            }
            catch(IOException ex)
            {
                //JOptionPane.showMessageDialog("Ошибка ввода-вывода");
            }
        }
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
    File OpenedImageFile = null;
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
                    if (PATH.split(":")[0].equals("C") || PATH.split(":")[0].equals("D")) {
                        OpenedImageFile = new File (PATH);
                    } else OpenedImageFile = new File (Paths.get(CurrentPath.toString(), "images", PATH).toString());

                    try {
                        imag = ImageIO.read(OpenedImageFile);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    if (Input.Validate(PATH) && OpenedImageFile.exists()) {
                        //Background(OpenedImageFile);
                        MainPanel = new MyPanel(new FlowLayout(FlowLayout.LEFT));
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
            imag = null;
            MainPanel = new MyPanel(new FlowLayout(FlowLayout.LEFT));


        }
    }



    BufferedImage imag;
    class MyPanel extends JPanel{
        public MyPanel(FlowLayout flowLayout) {
            setLayout(flowLayout);
            add(ToolBar.Toolbar(), BorderLayout.WEST);

            addMouseListener(new  MouseAdapter() {
                public void mouseClicked (MouseEvent e){
                    System.out.println("Clicked: "+X_START);
                    System.out.println("regime: "+regime+"\n");

                    X_START = e.getX();
                    Y_START = e.getY();
                    Graphics g = imag.getGraphics();
                    Graphics2D g2 = (Graphics2D) g;
                    // color set
                    g2.setColor(Color.BLACK);
                    switch (regime) {
                        // pen
                        case "pen":
                            g2.drawLine(X_START, Y_START, X_START + 1, Y_START + 1);
                            break;
                        // rubber
                        case "rubber":
                            g2.setStroke(new BasicStroke(3.0f));
                            g2.setColor(Color.WHITE);
                            g2.drawLine(X_START, Y_START, X_START + 1, Y_START + 1);
                            break;
                        // if clicked on JLabel
                        case "text":
                            requestFocus();
                            break;
                    }


                    mousePressed = true;
                    repaint();
                }



                public void mousePressed(MouseEvent e) {
                    System.out.println("Pressed: "+X_START);
                    System.out.println("regime: "+regime+"\n");
                    X_START=e.getX();
                    Y_START=e.getY();
                    X_UNPRESSED=e.getX();
                    Y_UNPRESSED=e.getY();
                    mousePressed=true;
                }

                public void mouseReleased(MouseEvent e) {
                    System.out.println("Released: "+X_START);
                    System.out.println("regime: "+regime+"\n");
                    Graphics g = imag.getGraphics();
                    Graphics2D g2 = (Graphics2D)g;
                    // установка цвета
                    g2.setColor(Color.BLACK);
                    // Общие рассчеты для овала и прямоугольника
                    int  x1=X_START, x2=X_UNPRESSED, y1=Y_START, y2=Y_UNPRESSED;
                    if(X_START>X_UNPRESSED)
                    {
                        x1=X_UNPRESSED; x2=X_START;
                    }
                    if(Y_START>Y_UNPRESSED)
                    {
                        y1=Y_UNPRESSED; y2=Y_START;
                    }
                    switch(regime)
                    {
                        // линия
                        case "line":
                            g.drawLine(X_START, Y_START, e.getX(), e.getY());
                            break;
                        // круг
                        case "oval":
                            g.drawOval(x1, y1, (x2-x1), (y2-y1));
                            break;
                        // прямоугольник
                        case "square":
                            g.drawRect(x1, y1, (x2-x1), (y2-y1));
                            break;
                    }
                    //xf=0; yf=0;
                    mousePressed=false;
                    repaint();
                }
            });

            addMouseMotionListener(new  MouseMotionAdapter()
            {
                public void mouseDragged(MouseEvent e)
                {
                    if (mousePressed)
                    {
                        Graphics g = imag.getGraphics();
                        Graphics2D g2 = (Graphics2D)g;
                        // установка цвета
                        g2.setColor(Color.BLACK);
                        switch (regime)
                        {
                            // pen
                            case "pen":
                                g2.drawLine(X_UNPRESSED, Y_UNPRESSED, e.getX(), e.getY());
                                break;
                            // rubber
                            case "rubber":
                                g2.setStroke(new  BasicStroke(3.0f));
                                g2.setColor(Color.WHITE);
                                g2.drawLine(X_UNPRESSED, Y_UNPRESSED, e.getX(), e.getY());
                                break;
                        }
                        X_UNPRESSED=e.getX();
                        Y_UNPRESSED=e.getY();
                    }
                    repaint();
                }
            });








        }
        public void paintComponent (Graphics g)
        {
            if(imag==null)
            {
                imag = new  BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D d2 = (Graphics2D) imag.createGraphics();
                d2.setColor(Color.white);
                d2.fillRect(0, 0, this.getWidth(), this.getHeight());
            }
            super.paintComponent(g);
            g.drawImage(imag, 0, 0,this);
            repaint();

        }

    }
}
