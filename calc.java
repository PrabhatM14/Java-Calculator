import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calc extends JFrame implements ActionListener {
    private JTextField display;
    private String currentInput = "";
    private double firstNumber = 0;
    private String operation = "";

    public calc() {
        // Set up  window
        setTitle("Java Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        
        // Create the display
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        
        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5));
        
        // Button labels
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C", "CE", "(", ")"
        };
        
        // Create and add buttons
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            buttonPanel.add(button);
        }
        
        setLayout(new BorderLayout(5, 5));
        add(display, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        switch (command) {
            case "0": case "1": case "2": case "3": case "4":
            case "5": case "6": case "7": case "8": case "9":
            case ".":
                currentInput += command;
                display.setText(currentInput);
                break;
                
            case "+": case "-": case "*": case "/":
                if (!currentInput.isEmpty()) {
                    firstNumber = Double.parseDouble(currentInput);
                    operation = command;
                    currentInput = "";
                }
                break;
                
            case "=":
                if (!currentInput.isEmpty() && !operation.isEmpty()) {
                    double secondNumber = Double.parseDouble(currentInput);
                    double result = calculate(firstNumber, secondNumber, operation);
                    display.setText(String.valueOf(result));
                    currentInput = String.valueOf(result);
                    operation = "";
                }
                break;
                
            case "C": // Clear all
                currentInput = "";
                firstNumber = 0;
                operation = "";
                display.setText("");
                break;
                
            case "CE": // Clear entry
                currentInput = "";
                display.setText("");
                break;
                
            case "(": case ")":
                currentInput += command;
                display.setText(currentInput);
                break;
        }
    }
    
    private double calculate(double a, double b, String op) {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": 
                if (b == 0) {
                    JOptionPane.showMessageDialog(this, "Cannot divide by zero!", "Error", JOptionPane.ERROR_MESSAGE);
                    return 0;
                }
                return a / b;
            default: return 0;
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            calc calculator = new calc();
            calculator.setVisible(true);
        });
    }
}
