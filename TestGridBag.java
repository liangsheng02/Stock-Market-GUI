import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TestGridBag {
    public static void main(String[] argv) throws Exception {
        JFrame frame = new JFrame();
        GridBagLayout gbl = new GridBagLayout();

        frame.setLayout(gbl);
        JButton component = new JButton("1");

        frame.add(new JButton("2"));
        frame.add(new JButton("3"));
        frame.add(new JButton("4"));
        frame.add(new JButton("5"));
        frame.add(component);
        frame.add(new JButton("6"));
        frame.add(new JButton("7"));
        frame.add(new JButton("8"));
        frame.add(new JButton("9"));
        frame.add(new JButton("0"));

        gbl.layoutContainer(frame);

        GridBagConstraints gbc = new GridBagConstraints();

        int top = 20;
        int left = 20;
        int bottom = 2;
        int right = 40;
        gbc.insets = new Insets(top, left, bottom, right);

        gbl.setConstraints(component, gbc);


        frame.pack();
        frame.setVisible(true);
    }
}
