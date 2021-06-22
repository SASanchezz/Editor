package Main;

import Bars.*;
import Menu.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;


public class Editor extends JFrame{
    public static BufferedImage imag = null;
    public static Color color = Color.BLACK;
    public static String regime = "pen";
    public static MyPanel MainPanel ;


    public Editor() {
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        MainPanel = new MyPanel();

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(FileMenu.FileMenu());
        menuBar.add(FontMenu.FontMenu());
        menuBar.add(ColorMenu());

        setJMenuBar(menuBar);

        MainPanel.add(ColorBar.ColorBar(), BorderLayout.NORTH);

        JScrollPane MyScrollPane = new JScrollPane(MainPanel);

        MyScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setContentPane(MyScrollPane);


        setVisible(true);


    }
    public static JMenu colorsMenu;
    public JMenu ColorMenu() {
        colorsMenu = new JMenu("Colors");
        //colorsMenu.setOpaque(true);
        //colorsMenu.setBackground(Color.BLACK);


        JMenuItem ColorPalette = new JMenuItem(new AbstractAction("More colors..") {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = JColorChooser.showDialog(Editor.this, "Select a color", Color.BLACK);
                colorsMenu.setOpaque(true);
                colorsMenu.setBackground(Editor.color);

                System.out.println(color.toString());
            }
        });

        colorsMenu.add(ColorPalette);
        return colorsMenu;
    }









    public static class MyPanel extends JPanel{
        boolean mousePressed = false;
        int X_START;
        int Y_START;
        int X_UNPRESSED;
        int Y_UNPRESSED;

        public MyPanel() {

            setLayout(new BorderLayout());
            //setBorder(BorderFactory.createLineBorder(Color.red));
            setPreferredSize(new Dimension(1200, 800));
            add(ToolBar.Toolbar(), BorderLayout.WEST);


            //Mouse activity

            addMouseListener(new  MouseAdapter() {
                public void mouseClicked (MouseEvent e){
                    System.out.println("Clicked: "+X_START);
                    System.out.println("regime: "+regime+"\n");

                    X_START = e.getX();
                    Y_START = e.getY();
                    Graphics g = imag.getGraphics();
                    Graphics2D g2 = (Graphics2D) g;
                    // color and width
                    g2.setColor(color);
                    g2.setStroke(new BasicStroke(FontMenu.cursorSlider.getValue()));
                    switch (regime) {
                        // pen
                        case "pen":
                            g2.drawLine(X_START, Y_START, X_START + 1, Y_START + 1);
                            g2.setColor(color);
                            break;
                        // rubber
                        case "rubber":

                            g2.setColor(Color.WHITE);
                            g2.drawLine(X_START, Y_START, X_START + 1, Y_START + 1);
                            break;
                        // if clicked on JLabel
//                        case "text":
//                            requestFocus();
//                            break;
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
                    // color and width
                    g2.setColor(color);
                    g2.setStroke(new BasicStroke(FontMenu.cursorSlider.getValue()));
                    // square and oval calculating
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
                        // line
                        case "line":
                            g.drawLine(X_START, Y_START, e.getX(), e.getY());
                            break;
                        // oval
                        case "oval":
                            g.drawOval(x1, y1, (x2-x1), (y2-y1));
                            break;
                        // rectangle
                        case "square":
                            g.drawRect(x1, y1, (x2-x1), (y2-y1));
                            break;
                        // filled oval
                        case "filledCircle":
                            g.drawOval(x1, y1, (x2-x1), (y2-y1));
                            g.fillOval(x1, y1, (x2-x1), (y2-y1));
                            break;
                        // filled rectangle
                        case "filledSquare":
                            g.drawRect(x1, y1, (x2-x1), (y2-y1));
                            g.fillRect(x1, y1, (x2-x1), (y2-y1));
                            break;
                        case "triangle":
                            g.drawLine(X_START, Y_START, e.getX(), Y_START);
                            g.drawLine(X_START, Y_START, e.getX()-((e.getX()-X_START)/2), e.getY());
                            g.drawLine(e.getX(), Y_START, e.getX()-((e.getX()-X_START)/2), e.getY());

                            break;
                        case "cut":
                            imag = imag.getSubimage(x1, y1, (x2-x1), (y2-y1));

                            break;
                        case "text":
                            Font MainFont = new Font("SansSerif", Font.PLAIN, (Integer)FontMenu.textSpinner.getValue());
                            JTextField textField = new JTextField(1);
                            textField.setFont(MainFont);
                            textField.setOpaque(false);
                            textField.setBounds(x1, y1, (x2-x1), (y2-y1));
                            MainPanel.add(textField);
                            System.out.println(textField.getWidth());
                            //repaint();
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
                        // color and width
                        g2.setColor(color);
                        g2.setStroke(new BasicStroke(FontMenu.cursorSlider.getValue()));

                        switch (regime)
                        {
                            // pen
                            case "pen":
                                g2.drawLine(X_UNPRESSED, Y_UNPRESSED, e.getX(), e.getY());
                                break;
                            // rubber
                            case "rubber":
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
                //setPreferredSize(new Dimension(1200, 800));
                imag = new  BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D d2 = (Graphics2D) imag.createGraphics();
                d2.setColor(Color.white);
                d2.fillRect(0, 0, this.getWidth(), this.getHeight());

            }
            super.paintComponent(g);
            g.drawImage(imag, 0, 0,this);
            setPreferredSize(new Dimension(imag.getWidth(), imag.getHeight()));
            repaint();

        }

    }
}
