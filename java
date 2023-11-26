import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraSofisticada extends JFrame {

    private JTextField display;
    private double valorAtual = 0;
    private String operacaoPendente = "";
    private boolean inicioDeNovoNumero = true;

    public CalculadoraSofisticada() {
        super("Calculadora Sofisticada");
        configurarGUI();
    }

    private void configurarGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        add(display, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(5, 4, 5, 5));

        String[] botoes = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String buttonText : botoes) {
            JButton button = new JButton(buttonText);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.addActionListener(new BotaoClickListener());
            painelBotoes.add(button);
        }

        add(painelBotoes, BorderLayout.CENTER);

        setVisible(true);
    }

    private class BotaoClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String buttonText = source.getText();

            if (buttonText.equals("=")) {
                calcularResultado();
            } else if (buttonText.equals(".")) {
                adicionarPontoDecimal();
            } else {
                adicionarAoDisplay(buttonText);
            }
        }
    }

    private void adicionarAoDisplay(String input) {
        if (inicioDeNovoNumero) {
            display.setText(input);
            inicioDeNovoNumero = false;
        } else {
            display.setText(display.getText() + input);
        }
    }

    private void adicionarPontoDecimal() {
        if (inicioDeNovoNumero) {
            display.setText("0.");
            inicioDeNovoNumero = false;
        } else if (display.getText().indexOf('.') == -1) {
            display.setText(display.getText() + ".");
        }
    }

    private void calcularResultado() {
        try {
            double novoValor = Double.parseDouble(display.getText());

            switch (operacaoPendente) {
                case "+":
                    valorAtual += novoValor;
                    break;
                case "-":
                    valorAtual -= novoValor;
                    break;
                case "*":
                    valorAtual *= novoValor;
                    break;
                case "/":
                    valorAtual /= novoValor;
                    break;
                default:
                    valorAtual = novoValor;
            }

            display.setText(String.valueOf(valorAtual));
            inicioDeNovoNumero = true;
            operacaoPendente = "";
        } catch (NumberFormatException ex) {
            display.setText("Erro");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraSofisticada());
    }
}
