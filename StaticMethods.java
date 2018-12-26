import java.awt.*;

public class StaticMethods {

    /**
     * This method is used to set font.
     * @param fontName
     * @param style
     * @param size
     * @param currentFont
     * @return a Font
     */
    public static Font getFont(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }


    /**
     * This method is used to set a basic GridBagConstraints.
     * @param x gridx
     * @param y gridy
     * @param anchor
     * @param fill
     * @param top the param 'top' of  Insets object
     * @param left the param 'left' of  Insets object
     * @param bottom the param 'bottom' of  Insets object
     * @param right the param 'right' of  Insets object
     * @return a GridBagConstraints
     */
    public static GridBagConstraints setGbc(GridBagConstraints gbc, int x, int y, int anchor, int fill,
                                            int top, int left, int bottom, int right) {
        if (gbc == null){
            gbc = new GridBagConstraints();
        }
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = anchor;
        gbc.fill = fill;
        gbc.insets = new Insets(top, left, bottom, right);
        return gbc;
    }
}
