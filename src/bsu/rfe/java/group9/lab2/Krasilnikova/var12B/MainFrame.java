package bsu.rfe.java.group9.lab2.Krasilnikova.var12B;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainFrame extends JFrame
{
    // Размеры окна приложения
    private static final int WIDTH = 400;
    private static final int HEIGHT = 320;

    // Текстовые поля для считывания значений переменных
    private JTextField textFieldX;
    private JTextField textFieldY;

    private JTextField textFieldResult;
    private ButtonGroup radioButtons = new ButtonGroup();
    // Контейнер для отображения радио-кнопок
    private Box hboxFormulaType = Box.createHorizontalBox(); //Контейнер, в который будут добавляться радио-кнопки, инициализируется в конструкторе, наполняется в методепомощнике

    private int formulaId = 1;
    public Double calculate1(Double x, Double y)
    {
        return x*x + y*y;
    }
    public Double calculate2(Double x, Double y)
    {
        return x*x*x + 1/y;
    }

    // Вспомогательный метод для добавления кнопок на панель
    private void addRadioButton(String buttonName, final int formulaId)
    {
        // Создать экземпляр радио-кнопки с заданным текстом
        JRadioButton button = new JRadioButton(buttonName);
        // Определить и зарегистрировать обработчик
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ev)
            {
                MainFrame.this.formulaId = formulaId;
            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }

    public MainFrame()
    {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        //Позиционирование окна на экране
        setLocation((kit.getScreenSize().width - WIDTH)/2, (kit.getScreenSize().height - HEIGHT)/2);
        // Добавить «клей» C1-H1 с левой стороны
        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        // Установить выделенной 1-ую кнопку из группы
        radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);

        // Добавить «клей» C1-H2 с правой стороны
        hboxFormulaType.add(Box.createHorizontalGlue());
        // Задать рамку для коробки с помощью класса BorderFactory
        hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        // Создать область с полями ввода для X и Y
        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        // Установить макс размер = желаемому для предотвращения масштабирования
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());

        // Создать контейнер «коробка с горизонтальной укладкой»
        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder(BorderFactory.createLineBorder(Color.RED));
        hboxVariables.add(Box.createHorizontalGlue());
        // Добавить подпись для переменной Х
        hboxVariables.add(labelForX);
        // Добавить «распорку» C2-H2 шириной 10 пикселов для отступа между надписью и текстовым полем для ввода значения X
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createHorizontalStrut(100));
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalGlue());

        // Создать область для вывода результата
        JLabel labelForResult = new JLabel("Результат:");
        textFieldResult = new JTextField("0", 10);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        // Создать область для кнопок
        JButton buttonCalc = new JButton("Вычислить");
        // Определить и зарегистрировать обработчик нажатия на кнопку
        buttonCalc.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ev)
            {
                try
                {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double result;
                    if (formulaId==1)
                        result = calculate1(x, y);
                    else
                        result = calculate2(x, y);
                    // Установить текст надписи равным результату
                    textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(MainFrame.this, "Ошибка в формате записи числа с плавающей точкой",
                            "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ev)
            {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldResult.setText("0");
            }
        });
        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN));

        // Связать области воедино в компоновке BoxLayout
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(Box.createVerticalGlue());
        // Установить «вертикальную коробку» в область содержания главного окна
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }
}