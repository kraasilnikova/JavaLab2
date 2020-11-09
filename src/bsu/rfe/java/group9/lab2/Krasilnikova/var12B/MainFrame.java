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
    private JTextField textFieldZ;

    private JTextField textFieldResult;
    private JTextField textMemoryField;

    // Группа радио-кнопок для обеспечения уникальности выделения в группе
    private ButtonGroup radioButtons = new ButtonGroup();
    private  ButtonGroup radioMemoryButtons = new ButtonGroup();

    // Контейнер для отображения радио-кнопок
    private Box hboxFormulaType = Box.createHorizontalBox();
    private Box hboxMemoryType = Box.createHorizontalBox();

    private int formulaId = 1;
    private int memoryId = 1;
    private Double mem1 = 0.0;
    private Double mem2 = 0.0;
    private Double mem3 = 0.0;

    public Double calculate1(Double x, Double y, Double z)
    {
        if (x == 0)
        {
            JOptionPane.showMessageDialog(MainFrame.this, "X не может равняться нулю", "" + "Ошибка ввода", JOptionPane.WARNING_MESSAGE);
            return 0.0;
        }
        if (x < 0)
        {
            JOptionPane.showMessageDialog(MainFrame.this, "X должен быть больше нуля!", "Ошибка ввода", JOptionPane.WARNING_MESSAGE);
        }
        if (y == -1)
        {
            JOptionPane.showMessageDialog(MainFrame.this, "Y не может равняться минус единице", "" + "Ошибка ввода", JOptionPane.WARNING_MESSAGE);
            return 0.0;
        }
        return Math.pow(Math.cos(Math.exp(x)) + Math.log(Math.pow(1+y, 2)) + Math.pow(Math.exp(Math.cos(x)) + Math.sin(Math.PI * z), 1/2) + Math.pow(1/x, 1/2) + Math.cos(y * y), Math.sin(z));
    }
    public Double calculate2(Double x, Double y, Double z)
    {
        if (z * x < 0)
        {
            JOptionPane.showMessageDialog(MainFrame.this, "Произведение Z * X должно быть больше нуля!", "" + "Ошибка ввода", JOptionPane.WARNING_MESSAGE);
            return 0.0;
        }
        if (y % 2 == 0 && x <= -1)
        {
            JOptionPane.showMessageDialog(MainFrame.this, "X должен быть больше минус единицы!", "" + "Ошибка ввода", JOptionPane.WARNING_MESSAGE);
            return 0.0;
        }
        return (1 + Math.pow(z * x, 1/2)) / Math.pow(1 + x * x * x, 1/y);
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
    private void addMemoryRadioButton(String buttonName, final int memoryId)
    {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                MainFrame.this.memoryId = memoryId;
                if (memoryId == 1)
                    textMemoryField.setText(MainFrame.this.mem1.toString());
                if (memoryId == 2)
                    textMemoryField.setText(MainFrame.this.mem2.toString());
                if (memoryId == 3)
                    textMemoryField.setText(MainFrame.this.mem3.toString());
            }
        });
        radioMemoryButtons.add(button);
        hboxMemoryType.add(button);
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

        // Создать область с полями ввода для X, Y, Z
        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        // Установить макс размер = желаемому для предотвращения масштабирования
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());

        // Создать контейнер «коробка с горизонтальной укладкой»
        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder(BorderFactory.createLineBorder(Color.RED));
        //hboxVariables.add(Box.createHorizontalGlue());
        // Добавить подпись для переменной Х
        hboxVariables.add(labelForX);
        // Добавить «распорку» C2-H2 шириной 10 пикселов для отступа между надписью и текстовым полем для ввода значения X
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(Box.createHorizontalStrut(50));
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(Box.createHorizontalStrut(50));
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);
        //hboxVariables.add(Box.createHorizontalGlue());


        // Создать область для вывода результата
        JLabel labelForResult = new JLabel("Результат:");
        textFieldResult = new JTextField("0", 20);
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
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    if (formulaId==1)
                        result = calculate1(x, y, z);
                    else
                        result = calculate2(x, y, z);
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
                textFieldZ.setText("0");
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

        hboxMemoryType.add(Box.createHorizontalGlue());
        addMemoryRadioButton("Переменная 1", 1);
        addMemoryRadioButton("Переменная 2", 2);
        addMemoryRadioButton("Переменная 3", 3);
        radioMemoryButtons.setSelected(radioMemoryButtons.getElements().nextElement().getModel(), true);
        hboxMemoryType.add(Box.createHorizontalGlue());
        hboxMemoryType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ev)
            {
                switch (memoryId)
                {
                    case(1): mem1 = 0.0;
                    case(2): mem2 = 0.0;
                    case(3): mem3 = 0.0;
                }
                textMemoryField.setText("0.0");
            }
        });
        JButton buttonM = new JButton("M+");
        buttonM.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ev)
            {
                Double result = Double.parseDouble(textFieldResult.getText());
                if (memoryId == 1)
                {
                    mem1 += result;
                    textMemoryField.setText(mem1.toString());
                }
                if (memoryId == 2)
                {
                    mem2 += result;
                    textMemoryField.setText(mem2.toString());
                }
                if (memoryId == 3)
                {
                    mem3 += result;
                    textMemoryField.setText(mem3.toString());
                }
            }
        });
        Box hboxMemoryButtons = Box.createHorizontalBox();
        hboxMemoryButtons.add(Box.createHorizontalGlue());
        hboxMemoryButtons.add(buttonMC);
        hboxMemoryButtons.add(Box.createHorizontalStrut(30));
        hboxMemoryButtons.add(buttonM);
        hboxMemoryButtons.add(Box.createHorizontalGlue());
        hboxMemoryButtons.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel labelForMemory = new JLabel("Память:");
        textMemoryField = new JTextField("0", 20);
        textMemoryField.setMaximumSize(textMemoryField.getPreferredSize());
        Box hboxMemoryResult = Box.createHorizontalBox();
        hboxMemoryResult.add(Box.createHorizontalGlue());
        hboxMemoryResult.add(labelForMemory);
        hboxMemoryResult.add(Box.createHorizontalStrut(10));
        hboxMemoryResult.add(textMemoryField);
        hboxMemoryResult.add(Box.createHorizontalGlue());
        hboxMemoryResult.setBorder(BorderFactory.createLineBorder(Color.RED));

        // Связать области воедино в компоновке BoxLayout
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(hboxMemoryType);
        contentBox.add(hboxMemoryButtons);
        contentBox.add(hboxMemoryResult);
        contentBox.add(Box.createVerticalGlue());
        // Установить «вертикальную коробку» в область содержания главного окна
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }
}