package GUI;

import javax.swing.*;

public class IncorrectDataDialog{
    public IncorrectDataDialog()
    {
    }

    public static void show()
    {
        JFrame frame = new JFrame("Грешка");
        JOptionPane.showMessageDialog(frame,"Внесовте невалиден тип на податоци!", "Грешка", 0);
    }

    public static void showZero()
    {
        JFrame frame = new JFrame("Грешка");
        JOptionPane.showMessageDialog(frame,  "Внесовте 0! Внесете број поголем од 0", "Грешка", 0);
    }

    public static void showEmpty()
    {
        JFrame frame = new JFrame("Грешка");
        JOptionPane.showMessageDialog(frame, "Не внесовте никаков број!", "Грешка", 0);
    }
}