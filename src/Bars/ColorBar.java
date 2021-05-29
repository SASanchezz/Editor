package Bars;

import Main.Editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorBar {

    public static JToolBar ColorBar() {

        JToolBar ColorBar = new JToolBar("ColorBar", JToolBar.HORIZONTAL);
        JPanel panel = new JPanel();

        panel.add(new JLabel("Colors"));
        ColorBar.add(panel);
        ColorBar.setLayout(new GridLayout());
        JButton redbutton = new  JButton();

        redbutton.setBounds(0, 0, 50, 50);
        redbutton.setBackground(Color.red);


        redbutton.addActionListener(new  ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                Editor.colorsMenu.setOpaque(true);
                Editor.color = Color.red;
                Editor.colorsMenu.setBackground(Editor.color);
            }
        });
        ColorBar.add(redbutton);

        JButton orangebutton = new  JButton();
        orangebutton.setBackground(Color.orange);
        orangebutton.setBounds(60, 5, 50, 50);
        orangebutton.addActionListener(new  ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                Editor.colorsMenu.setOpaque(true);
                Editor.color = Color.orange;
                Editor.colorsMenu.setBackground(Editor.color);
            }
        });
        ColorBar.add(orangebutton);

        JButton yellowbutton = new  JButton();
        yellowbutton.setBackground(Color.yellow);
        yellowbutton.setBounds(80, 5, 50, 50);
        yellowbutton.addActionListener(new  ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                Editor.color = Color.yellow;
                Editor.colorsMenu.setOpaque(true);
                Editor.colorsMenu.setBackground(Editor.color);
            }
        });
        ColorBar.add(yellowbutton);

        JButton greenbutton = new  JButton();
        greenbutton.setBackground(Color.green);
        greenbutton.setBounds(100, 5, 50, 50);
        greenbutton.addActionListener(new  ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                Editor.color = Color.green;
                Editor.colorsMenu.setOpaque(true);
                Editor.colorsMenu.setBackground(Editor.color);
            }
        });
        ColorBar.add(greenbutton);

        JButton bluebutton = new JButton();
        bluebutton.setBackground(Color.blue);
        bluebutton.setBounds(120, 5, 50, 50);
        bluebutton.addActionListener(new  ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                Editor.color = Color.blue;
                Editor.colorsMenu.setOpaque(true);
                Editor.colorsMenu.setBackground(Editor.color);
            }
        });
        ColorBar.add(bluebutton);

        JButton cyanbutton = new  JButton();
        cyanbutton.setBackground(Color.cyan);
        cyanbutton.setBounds(140, 5, 50, 50);
        cyanbutton.addActionListener(new  ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                Editor.color = Color.cyan;
                Editor.colorsMenu.setOpaque(true);
                Editor.colorsMenu.setBackground(Editor.color);
            }
        });
        ColorBar.add(cyanbutton);

        JButton magentabutton = new  JButton();
        magentabutton.setBackground(Color.magenta);
        magentabutton.setBounds(160, 5, 50, 50);
        magentabutton.addActionListener(new  ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                Editor.color = Color.magenta;
                Editor.colorsMenu.setOpaque(true);
                Editor.colorsMenu.setBackground(Editor.color);
            }
        });
        ColorBar.add(magentabutton);

        JButton whitebutton = new  JButton();
        whitebutton.setBackground(Color.white);
        whitebutton.setBounds(180, 5, 50, 50);
        whitebutton.addActionListener(new  ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                Editor.color = Color.white;
                Editor.colorsMenu.setOpaque(true);
                Editor.colorsMenu.setBackground(Editor.color);
            }
        });
        ColorBar.add(whitebutton);

        JButton blackbutton = new  JButton();
        blackbutton.setBackground(Color.black);
        blackbutton.setBounds(200, 5, 50, 50);
        blackbutton.addActionListener(new  ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                Editor.color = Color.black;
                Editor.colorsMenu.setOpaque(true);
                Editor.colorsMenu.setBackground(Editor.color);
            }
        });
        ColorBar.add(blackbutton);
        ColorBar.setBounds(44, 0, 450, 50);


        return ColorBar;
    }
}
