
package test;
import GUI.*;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Main {
    public static void main(String[] args)
    {
        InputOutputFrame IOF = new InputOutputFrame();

        // Get the size of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        // Determine the new location of the window
        int w = IOF.getSize().width;
        int h = IOF.getSize().height;
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/2;

        // Move the window
        IOF.setLocation(x, y);
        IOF.setVisible(true);
    }
}