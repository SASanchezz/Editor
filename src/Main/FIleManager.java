package Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.*;


public class FIleManager {
    public static String path = null;

    static public void ImageOpener(String path) {
        FIleManager.path = path;
        File imageFile = new File(path);
        try {
            Editor.imag = ImageIO.read(imageFile);
            Editor.MainPanel.setPreferredSize(new Dimension(Editor.imag.getWidth(), Editor.imag.getHeight()));

        } catch (FileNotFoundException ex) {
        JOptionPane.showMessageDialog(Editor.MainPanel, "Such file doesn't exist");
    }
                    catch (IOException ex) {
        JOptionPane.showMessageDialog(Editor.MainPanel, "Input-Output exception");
    }
                  catch (Exception ex) {
        JOptionPane.showMessageDialog(Editor.MainPanel, "Other error");

                  }
    }

}
