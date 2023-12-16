package bsu.rfe.java.group_5.lab_2.Grintsevich.B_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Math.*;
import static java.lang.Math.pow;

public class MainFrame extends JFrame {
    public static double EXP = Math.exp(1);

    private static final int width = 900;
    private static final int height = 400;
    private JTextField textX;
    private JTextField textY;
    private JTextField textZ;
    private JTextField text_mem1;
    private JTextField text_mem2;
    private JTextField text_mem3;
    private JTextField textResult;
    private ButtonGroup radioButtCalc = new ButtonGroup();
    private ButtonGroup radioButtMem = new ButtonGroup();
    private int formulaId_calculations = 1;
    private int formulaId_memory = 1;

    public Double calculate1(Double x, Double y, Double z) {

        return (pow(cos(pow(EXP, y)) + pow(EXP, pow(y, 2)) + sqrt(1 / x), 1 / 4)) / pow(cos(PI * pow(z, 3)) + log(pow(1 + z, 2)), sin(y));
    }

    // Формула №2
    public Double calculate2(Double x, Double y, Double z) {
        return ((1 + pow(x, z) + log(pow(y, 2))) * (1 - sin(y * z))) / (sqrt(pow(x, 3) + 1));
    }

    private void add_radio_button_memory(String buttonName, final int formulaId, ButtonGroup radioButtons, Box boxForButtons) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.formulaId_memory = formulaId;
            }
        });
        radioButtons.add(button);
        boxForButtons.add(button);
    }

    private void addRadioButton(String buttonName, final int formulaId, ButtonGroup radioButtons, Box boxForButtons) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.formulaId_calculations = formulaId;
            }
        });
        radioButtons.add(button);
        boxForButtons.add(button);
    }

    public MainFrame() {
        super("Лабораторная работа № 2");

        setSize(width, height);
        Toolkit kit = Toolkit.getDefaultToolkit();
        this.setLocationRelativeTo(null);
        Box box_for_radio_buttons = Box.createHorizontalBox();
        box_for_radio_buttons.add(Box.createHorizontalGlue());
        addRadioButton("Function 1", 1, radioButtCalc, box_for_radio_buttons);
        addRadioButton("Function 2", 2, radioButtCalc, box_for_radio_buttons);
        radioButtCalc.setSelected(radioButtCalc.getElements().nextElement().getModel(), true);
        box_for_radio_buttons.add(Box.createHorizontalGlue());
        box_for_radio_buttons.setBorder(BorderFactory.createLineBorder(Color.RED));

        //memory радио-кнопки
        Box box_for_radio_buttons_memories = Box.createHorizontalBox();
        add_radio_button_memory("mem 1", 1, radioButtMem, box_for_radio_buttons_memories);
        add_radio_button_memory("mem 2", 2, radioButtMem, box_for_radio_buttons_memories);
        add_radio_button_memory("mem 3", 3, radioButtMem, box_for_radio_buttons_memories);
        radioButtMem.setSelected(radioButtMem.getElements().nextElement().getModel(), true);

        // Поля для XYZ
        JLabel labelX = new JLabel("X:");
        textX = new JTextField("0", 10);
        textX.setMaximumSize(textX.getPreferredSize());

        JLabel labelY = new JLabel("Y:");
        textY = new JTextField("0", 10);
        textY.setMaximumSize(textY.getPreferredSize());

        JLabel labelZ = new JLabel("Z:");
        textZ = new JTextField("0", 10);
        textZ.setMaximumSize(textZ.getPreferredSize());

        Box box_for_XYZ = Box.createHorizontalBox();
        box_for_XYZ.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        box_for_XYZ.add(Box.createHorizontalGlue());
        box_for_XYZ.add(labelX);
        box_for_XYZ.add(Box.createHorizontalStrut(15));
        box_for_XYZ.add(textX);
        box_for_XYZ.add(Box.createHorizontalStrut(60));
        box_for_XYZ.add(labelY);
        box_for_XYZ.add(Box.createHorizontalStrut(15));
        box_for_XYZ.add(textY);
        box_for_XYZ.add(Box.createHorizontalStrut(60));
        box_for_XYZ.add(labelZ);
        box_for_XYZ.add(Box.createHorizontalStrut(15));
        box_for_XYZ.add(textZ);
        box_for_XYZ.add(Box.createHorizontalGlue());


        //Поле с результатом
        JLabel labelResult = new JLabel("Result:");
        textResult = new JTextField("0", 15);
        textResult.setMaximumSize(textResult.getPreferredSize());
        Box boxForResult = Box.createHorizontalBox();
        boxForResult.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        boxForResult.add(Box.createHorizontalGlue());
        boxForResult.add(labelResult);
        boxForResult.add(Box.createHorizontalStrut(10));
        boxForResult.add(textResult);
        boxForResult.add(Box.createHorizontalGlue());

        //memory текст
        JLabel label_mem1 = new JLabel("mem1:");
        text_mem1 = new JTextField("0", 12);
        text_mem1.setMaximumSize(text_mem1.getPreferredSize());

        JLabel label_mem2 = new JLabel("mem2:");
        text_mem2 = new JTextField("0", 12);
        text_mem2.setMaximumSize(text_mem2.getPreferredSize());

        JLabel label_mem3 = new JLabel("mem3:");
        text_mem3 = new JTextField("0", 12);
        text_mem3.setMaximumSize(text_mem3.getPreferredSize());


        //Кнопки
        JButton buttonCalc = new JButton("Calculate");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double x = Double.parseDouble(textX.getText());
                    Double y = Double.parseDouble(textY.getText());
                    Double z = Double.parseDouble(textZ.getText());
                    Double result;
                    if (x + 1 == 0 || cos(pow(EXP, y)) + pow(EXP, pow(y, 2)) + sqrt(1 / x) == 0) {
                        JOptionPane.showMessageDialog(buttonCalc, "Попытка вычисление корня из отрицательного значения", "Невозможно посчитать значение функции", 2);

                    } else if (x == 0) {
                        JOptionPane.showMessageDialog(buttonCalc, "Попытка деления на 0", "Невозможно посчитать значение функции", 2);
                    } else if (y == 0 || z + 1 == 0) {
                        JOptionPane.showMessageDialog(buttonCalc, "Невозможно вычислить логарифм 0", "Невозможно посчитать значение функции", 2);
                    } else {
                        if (formulaId_calculations == 1)
                            result = calculate1(x, y, z);
                        else
                            result = calculate2(x, y, z);
                        textResult.setText(result.toString());
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton buttonReset = new JButton("Clear");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textX.setText("0");
                textY.setText("0");
                textZ.setText("0");
                textResult.setText("0");
            }
        });

        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    if (formulaId_memory == 1)
                        text_mem1.setText("0");
                    else if (formulaId_memory == 2)
                        text_mem2.setText("0");
                    else
                        text_mem3.setText("0");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton buttonMPlus = new JButton("M+");
        buttonMPlus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    Double result = Double.parseDouble(textResult.getText());
                    if (formulaId_memory == 1)
                        text_mem1.setText(Double.toString(Double.parseDouble(text_mem1.getText()) + result));
                    else if (formulaId_memory == 2)
                        text_mem2.setText(Double.toString(Double.parseDouble(text_mem2.getText()) + result));
                    else
                        text_mem3.setText(Double.toString(Double.parseDouble(text_mem3.getText()) + result));
                    textResult.setText(result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        //Кнопки для кальки по коробкам
        Box boxForButtons = Box.createHorizontalBox();
        boxForButtons.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        boxForButtons.add(buttonCalc);
        boxForButtons.add(buttonReset);
        boxForButtons.add(Box.createHorizontalGlue());
        boxForButtons.add(buttonMPlus);
        boxForButtons.add(buttonMC);


        //коробки для переключателей мемов
        Box box_for_memory = Box.createHorizontalBox();
        box_for_memory.add(label_mem1);
        box_for_memory.add(Box.createHorizontalStrut(15));
        box_for_memory.add(text_mem1);
        //box_for_memory.add(Box.createHorizontalStrut(30));
        box_for_memory.add(Box.createHorizontalGlue());
        box_for_memory.add(label_mem2);
        box_for_memory.add(Box.createHorizontalStrut(15));
        box_for_memory.add(text_mem2);
        //box_for_memory.add(Box.createHorizontalStrut(30));
        box_for_memory.add(Box.createHorizontalGlue());
        box_for_memory.add(label_mem3);
        box_for_memory.add(Box.createHorizontalStrut(15));
        box_for_memory.add(text_mem3);
        text_mem1.setEnabled(false);
        text_mem2.setEnabled(false);
        text_mem3.setEnabled(false);


        //Коробки для показа мемов
        Box box_for_memories = Box.createVerticalBox();
        box_for_memories.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        box_for_memories.add(box_for_memory);


        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(box_for_radio_buttons);
        contentBox.add(box_for_XYZ);
        contentBox.add(box_for_memories);
        contentBox.add(box_for_radio_buttons_memories);
        contentBox.add(boxForResult);
        contentBox.add(boxForButtons);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);

    }
}