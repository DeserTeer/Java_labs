package bsu.rfe.java.group_5.lab_3.Grintsevich.B_1;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class GornerTableCellRenderer implements TableCellRenderer {
    private JPanel panel = new JPanel();
    private JLabel label = new JLabel();
    // Ищем ячейки, строковое представление которых совпадает с needle
// (иголкой). Применяется аналогия поиска иголки в стоге сена, в роли
// стога сена - таблица
    private String needle = null;
    private String needle2 = null;

    private DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance();

    public GornerTableCellRenderer() {
// Показывать только 5 знаков после запятой
        formatter.setMaximumFractionDigits(5);
// Не использовать группировку (т.е. не отделять тысячи
// ни запятыми, ни пробелами), т.е. показывать число как "1000",
// а не "1 000" или "1,000"
        formatter.setGroupingUsed(false);
// Установить в качестве разделителя дробной части точку, а не
// запятую. По умолчанию, в региональных настройках
// Россия/Беларусь дробная часть отделяется запятой
        DecimalFormatSymbols dottedDouble =
                formatter.getDecimalFormatSymbols();
        dottedDouble.setDecimalSeparator('.');
        formatter.setDecimalFormatSymbols(dottedDouble);
// Разместить надпись внутри панели
        panel.add(label);
// Установить выравнивание надписи по левому краю панели
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
// Преобразовать double в строку с помощью форматировщика
        String formattedDouble = formatter.format(value);

        // Установить текст надписи равным строковому представлению числа
        label.setText(formattedDouble);

        double number = Double.parseDouble(formattedDouble);


        if (col == 1 && needle != null && needle.equals(formattedDouble)) {
// Номер столбца = 1 (т.е. второй столбец) + иголка не null
// (значит что-то ищем) +
// значение иголки совпадает со значением ячейки таблицы -
// окрасить задний фон панели в красный цвет
            panel.setBackground(Color.RED);


        } else {
            boolean flag = true;
            {
                Integer integerPart = (int) number;
                Double fractionalPart = number - integerPart;
                int integerLenght = 1;
                while ((int) (integerPart / Math.pow(10, integerLenght)) != 0) integerLenght++;
                fractionalPart *= Math.pow(10, integerLenght + 1);
                fractionalPart = (double) Math.round(fractionalPart);
                if (Math.round(fractionalPart) % 10 == 0) {
                    fractionalPart /= Math.pow(10, integerLenght + 1);
                    String fractionalPartString = fractionalPart.toString();
                    fractionalPartString = fractionalPartString.substring(2);
                    while (fractionalPartString.length() < integerLenght) fractionalPartString += "0";
                    String integerPartString = integerPart.toString();
                    if (!(fractionalPartString.equals(integerPartString))) {
                        flag = false;
                        // System.out.println("000000000000000000");System.out.println(number);System.out.println(integerPartString);System.out.println(fractionalPartString);System.out.println("11111111111111111");
                    }
                } else {
                    flag = false;
                    //  System.out.println("000000000000000000");System.out.println(number);System.out.println("11111111111111111");
                }
                //if(flag){System.out.println("000000000000000000");System.out.println(number);System.out.println("true");System.out.println("11111111111111111");}
            }
            if (flag) {
                panel.setBackground(Color.GREEN);
            } else {
                panel.setBackground(Color.WHITE);
            }
        }
        return panel;
    }
 /*public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
     // Преобразовать double в строку с помощью форматировщика
     String formattedDouble = formatter.format(value);
     // Установить текст надписи равным строковому представлению числа
     label.setText(formattedDouble);
     if (needle != null && needle.equals(formattedDouble))
         // Значение иголки совпадает со значением ячейки таблицы -
         // окрасить весь ряд в красный цвет
         table.setSelectionBackground(Color.RED);
     else
         // Иначе - в обычный белый
         table.setSelectionBackground(Color.WHITE);

     return panel;*/

    public void setNeedle(String needle) {
        this.needle = needle;
    }

}
