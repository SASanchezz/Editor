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
        toolBar.add(penButton);

        JButton lineButton = new JButton(new  ImageIcon("images/line.png"));
        lineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Editor.regime = "line";
            }
        });
        toolBar.add(lineButton);

        JButton squareButton = new JButton(new  ImageIcon("images/square.png"));
        squareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Editor.regime = "square";
            }
        });
        toolBar.add(squareButton);

        JButton ovalButton = new JButton(new  ImageIcon("images/oval.png"));
        ovalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Editor.regime = "oval";
            }
        });
        toolBar.add(ovalButton);

        JButton rubberButton = new JButton(new  ImageIcon("images/rubber.png"));
        rubberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Editor.regime = "rubber";
            }
        });
        toolBar.add(rubberButton);

        JButton textButton = new JButton(new  ImageIcon("images/text.png"));
        textButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Editor.regime = "text";
            }
        });
        toolBar.add(textButton);

        toolBar.setBounds(0, 0, 30, 500);



        return toolBar;
    }
}
