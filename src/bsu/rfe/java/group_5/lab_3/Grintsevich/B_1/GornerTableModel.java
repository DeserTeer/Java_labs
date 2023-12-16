package bsu.rfe.java.group_5.lab_3.Grintsevich.B_1;

import javax.swing.table.AbstractTableModel;
import java.util.Objects;

@SuppressWarnings("serial")
public class GornerTableModel extends AbstractTableModel {
    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;

    public Double getCoefficient(int index) {
        return coefficients[index];
    }

    public GornerTableModel(Double from, Double to, Double step, Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }

    public Double getFrom() {
        return from;
    }

    public Double getTo() {
        return to;
    }

    public Double getStep() {
        return step;
    }

    public int getColumnCount() {
        return 4;
    }

    public int getRowCount() {
        return new Double(Math.ceil((to - from) / step)).intValue() + 1;
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Значение X";
            case 1:
                return "Значение многочлена";
            case 2:
                return "Близко к целому";
            //case 3:
            default:
                return "Целая часть палиндром?";
 ////           default:
    //            System.err.println("Too many columns");
      //          return null;
        }
    }

    public Class<?> getColumnClass(int col) {
        if (col == 2 || col == 3) {
            return Boolean.class;
        }
// И в 1-ом и во 2-ом  столбце находятся значения типа Double
        return Double.class;
    }

    public static String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public Object getValueAt(int row, int col) {
        double x = from + step * row;
        if (col == 0) {
            return x;
        } else if (col == 1) {
            return calculateGornerValue(x);
        } else if (col == 2) {
            double curr = calculateGornerValue(x);
            if (curr <= Math.round(curr) + 0.1 && curr >= Math.round(curr) - 0.1) {
                return true;
            } else return false;
        } else if (col == 3) {
            Integer integ = calculateGornerValue(x).intValue();
            String str = integ.toString();
            boolean flag = true;
            for (int i = 0; i < coefficients.length/2; i++){
                if (!str.equals(reverseString(str))){
                    flag = false;
                }
            }
            return flag;
        }
        return null;
    }

    private Double calculateGornerValue(double x) {
        double result = 0.0;
        for (int i = coefficients.length - 1; i >= 0; i--)
            result = result * x + coefficients[i];

        return result;
    }


}