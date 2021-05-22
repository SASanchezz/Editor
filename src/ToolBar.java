import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBar {

    protected static JToolBar Toolbar() {
        JToolBar toolBar = new JToolBar("Toolbar", JToolBar.VERTICAL);

        JButton penButton = new JButton(new  ImageIcon("images/pen.png"));
        penButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Editor.regime = "pen";
            }
        });

        JButton lineButton = new JButton(new  ImageIcon("images/line.png"));
        lineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Editor.regime = "line";
            }
        });

        JButton squareButton = new JButton(new  ImageIcon("images/square.png"));
        lineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Editor.regime = "square";
            }
        });

        JButton triangleButton = new JButton(new  ImageIcon("images/triangle.jpg"));
        lineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Editor.regime = "triangle";
            }
        });

        JButton rubberButton = new JButton(new  ImageIcon("images/rubber.png"));
        lineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Editor.regime = "rubber";
            }
        });

        toolBar.add(rubberButton);
        toolBar.add(penButton);
        toolBar.add(lineButton);
        toolBar.add(squareButton);
        toolBar.add(triangleButton);



        return toolBar;
    }
}
