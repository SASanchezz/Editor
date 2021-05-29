package Menu;

import javax.swing.*;

public class FontMenu {
    public static JSlider cursorSlider = new JSlider(0, 80, 6);
    public static SpinnerModel numbers = new SpinnerNumberModel(24, 0, 80, 2);
    public static JSpinner textSpinner = new JSpinner(numbers);

    public static JMenu FontMenu() {
        JMenu menu = new JMenu("Font");
        menu.add(new JLabel("Font size: "));

        menu.add(textSpinner);

        menu.add(new JLabel("Cursor width: "));
        cursorSlider.setMajorTickSpacing(16);
        cursorSlider.setMinorTickSpacing(8);
        cursorSlider.setPaintTicks(true);
        cursorSlider.setSnapToTicks(true);
        cursorSlider.setPaintLabels(true);
        menu.add(cursorSlider);

        return menu;
    }

}
