package Menu;

import Main.Editor;
import Main.FIleManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class FileMenu {

    public static JMenu FileMenu() {
        JMenu menu = new JMenu("File");
        JMenuItem newFile = new JMenuItem(new NewAction());
        JMenuItem openFile = new JMenuItem(new OpenAction());
        JMenuItem save = new JMenuItem(new SaveAction());
        JMenuItem saveas = new JMenuItem(new SaveAsAction());
        JMenuItem exit = new JMenuItem(new ExitAction());

        menu.add(newFile);
        menu.add(openFile);
        menu.add(save);
        menu.add(saveas);
        menu.add(exit);
        return menu;
    }

    //-----------------------------------------------------------------------------
    // Command Save
    static class SaveAction extends AbstractAction {
        private SaveAction() {
            // Icon
            putValue(AbstractAction.SMALL_ICON, new ImageIcon("images/save.png"));
            putValue(NAME, "Save");
        }
        @Override
        public void actionPerformed(ActionEvent e) {

            try
            {
                JFileChooser jf= new  JFileChooser("C:\\Users\\Sanchez\\Desktop\\Java_workspace\\Uni_Practice\\Editor\\images");
                if(FIleManager.path==null)
                {
                    int  result = jf.showSaveDialog(null);
                    if (result==JFileChooser.APPROVE_OPTION) {
                        String fileFullName = jf.getSelectedFile().getAbsolutePath();
                        ImageIO.write(Editor.imag, "jpg", new File(fileFullName+".jpg"));
                    }
                } else {
                    ImageIO.write(Editor.imag, "jpg", new  File(FIleManager.path));
                }

            }
            catch(IOException ex)
            {
                JOptionPane.showMessageDialog(new Editor(), "Error");
            }
        }
    }
    static class SaveAsAction extends AbstractAction {
        private SaveAsAction() {
            // Icon
            putValue(AbstractAction.SMALL_ICON, new ImageIcon("images/save.png"));
            putValue(NAME, "Save As..");
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            String fileFullName = null;
            try
            {
                JFileChooser jf= new  JFileChooser("C:\\Users\\Sanchez\\Desktop\\Java_workspace\\Uni_Practice\\Editor\\images");
                int  result = jf.showSaveDialog(null);
                if(result==JFileChooser.APPROVE_OPTION)
                {
                    fileFullName = jf.getSelectedFile().getAbsolutePath();
                    ImageIO.write(Editor.imag, "jpg", new  File(fileFullName+".jpg"));
                }

            }
            catch(IOException ex)
            {
                //JOptionPane.showMessageDialog("Ошибка ввода-вывода");
            }
        }
    }

    //-----------------------------------------------------------------------------
    // Command Open
    static class OpenAction extends AbstractAction {

        private OpenAction() {

            putValue(AbstractAction.SMALL_ICON, new ImageIcon("images/open.png"));
            putValue(NAME, "Open");
        }
        public void actionPerformed(ActionEvent e) {
            JFileChooser jf= new  JFileChooser("C:\\Users\\Sanchez\\Desktop\\Java_workspace\\Uni_Practice\\Editor\\images");
            int  result = jf.showOpenDialog(null);
            if(result==JFileChooser.APPROVE_OPTION)
            {
                FIleManager.ImageOpener(jf.getSelectedFile().getAbsolutePath());

                Editor.MainPanel = new Editor.MyPanel();


            }
        }


    }

    //-----------------------------------------------------------------------------
    // Command Exit
    static class ExitAction extends AbstractAction {
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
    // Command New File Open
    static class NewAction extends AbstractAction {
        //private static final long serialVersionUID = 1L;
        private NewAction() {
            // Icon and name settings
            putValue(AbstractAction.SMALL_ICON, new ImageIcon("images/new.png"));
            putValue(NAME, "New");
        }
        public void actionPerformed(ActionEvent e) {
            FIleManager.path = null;
            Editor.imag = null;
            Editor.MainPanel = new Editor.MyPanel();


        }
    }
}
