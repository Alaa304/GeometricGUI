package GeometricGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class Geometric {
    protected String color;
    protected boolean filled;

    public Geometric(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }
}

class Rectangle extends Geometric {
    private double width;
    private double height;

    public Rectangle(double width, double height, String color, boolean filled) {
        super(color, filled);
        this.width = width;
        this.height = height;
    }

    public double calculateArea() {
        return width * height;
    }

    public double calculatePerimeter() {
        return 2 * (width + height);
    }
}

class Square extends Rectangle {
    public Square(double side, String color, boolean filled) {
        super(side, side, color, filled);
    }
}

class Circle extends Geometric {
    private double radius;

    public Circle(double radius, String color, boolean filled) {
        super(color, filled);
        this.radius = radius;
    }

    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
}

public class GeometricGUI {
    public static void main(String[] args) {
        GeometricGUI x = new GeometricGUI();
    }

    public GeometricGUI() {
        JFrame frame = new JFrame("Geometric Calculator");
        frame.setSize(400, 220);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        frame.setVisible(true);
    }

    public void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel shapeLabel = new JLabel("Select Shape:");
        shapeLabel.setBounds(10, 20, 80, 25);
        panel.add(shapeLabel);

        String[] shapes = {"Rectangle", "Square", "Circle"};
        final JComboBox<String> shapeComboBox = new JComboBox<>(shapes);
        shapeComboBox.setBounds(100, 20, 120, 25);
        panel.add(shapeComboBox);

        JLabel l1 = new JLabel("Value 1:");
        l1.setBounds(10, 50, 80, 25);
        panel.add(l1);

        final JTextField t1 = new JTextField(20);
        t1.setBounds(100, 50, 120, 25);
        panel.add(t1);

        JLabel l2 = new JLabel("Value 2:");
        l2.setBounds(10, 80, 80, 25);
        panel.add(l2);

        final JTextField t2 = new JTextField(20);
        t2.setBounds(100, 80, 120, 25);
        panel.add(t2);

        JButton calculateButton = new JButton("Calculate and Display");
        calculateButton.setBounds(10, 120, 180, 25);
        panel.add(calculateButton);

        final JLabel result = new JLabel("");
        result.setBounds(10, 150, 300, 25);
        panel.add(result);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedShape = (String) shapeComboBox.getSelectedItem();
                try {
                    double a1 = Double.parseDouble(t1.getText());
                    double a2 = t2.getText().isEmpty() ? 0 : Double.parseDouble(t2.getText());

                    if ("Rectangle".equals(selectedShape)) {
                        Rectangle rectangle = new Rectangle(a1, a2, "Blue", true);
                        result.setText("Area: " + rectangle.calculateArea() + ", Perimeter: " + rectangle.calculatePerimeter());
                    } else if ("Square".equals(selectedShape)) {
                        Square square = new Square(a1, "Red", true);
                        result.setText("Area: " + square.calculateArea() + ", Perimeter: " + square.calculatePerimeter());
                    } else if ("Circle".equals(selectedShape)) {
                        Circle circle = new Circle(a1, "Green", true);
                        result.setText("Area: " + circle.calculateArea() + ", Perimeter: " + circle.calculatePerimeter());
                    }
                } catch (NumberFormatException ex) {
                    result.setText("Invalid input. Please enter numeric values.");
                }
            }
        });
    }
}
