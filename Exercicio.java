import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Exercicio {   
    // valor que irá guardar o valor atual dos cálculos
    static JLabel labelValor = new JLabel("0", SwingConstants.RIGHT);
    // valor usado nas operações
    static double valorCalculado = 0;
    static String operacaoAtual = "";

   public static JPanel montaBotoes()  {
        // cria o painel da calculadora
        JPanel painelCalculadora = new JPanel();
        painelCalculadora.setLayout(new GridLayout(5, 4, 4, 4)); // o layout terá 5 linhas, 4 colunas e 5px de distanciamento

        String[] botoesCalculadora = {
            "C", "^", "/", "X",
            "7", "8", "9", "-",
            "4", "5", "6", "+",
            "1", "2", "3", "=",
            " ", "0", ".", " "
        };

        for(String botaoCalculadora : botoesCalculadora) {
            if(botaoCalculadora.trim().isEmpty()) {
                // adiciona espaço em branco
                painelCalculadora.add(new JLabel());
            } else {
                JButton botao = new JButton(botaoCalculadora);
                botao.setFont(new Font("Arial", Font.BOLD, 18));
                botao.setFocusPainted(false);
                
                botao.addActionListener(e -> {
                    novoClique(botao.getText());
                });

                painelCalculadora.add(botao);
            }
        }

        return painelCalculadora;
   }

    // retorna a janela criada 
    public static JFrame montaJanela() {
        //define e configura a janela criada 
        JFrame janela = new JFrame("Calculadora");
        janela.setSize(350, 600);
        janela.setLocationRelativeTo(null);;
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //adiciona o JLabel com o valor atual
        labelValor.setFont(new Font("Arial", Font.BOLD, 36));
        janela.add(labelValor, BorderLayout.NORTH);

        // adiciona a calculadora
        JPanel calculadora = montaBotoes();
        janela.add(calculadora, BorderLayout.CENTER);

        return janela;
    }

    public static void novaOperacao(String operacao) {
        try {
            double valorAtual = Double.parseDouble(labelValor.getText());

            if(valorAtual == 0 && operacao == "/") throw new Exception("Não pode dividir por 0!");

            System.out.println("Valor na label: " + valorAtual);
            System.out.println("Valor calculado antes: " + valorCalculado);
            if(valorAtual != 0){
                switch(operacaoAtual) {
                    case "+":
                        valorCalculado += valorAtual;
                        break;
                    case "-":
                        valorCalculado -= valorAtual;
                        break;
                    case "X":
                        System.out.println("Teste");
                        valorCalculado *= valorAtual;
                        break;
                    case "/":
                        valorCalculado /= valorAtual;
                        break;
                    case "^":
                        valorCalculado = Math.pow(valorCalculado, valorAtual);
                        break;
                    default:
                        valorCalculado = valorAtual;
                        break;
                }
            } 
            System.out.println("Valor calculado depois: " + valorCalculado);
            
            System.out.println("Operação antes: " + operacaoAtual);
            operacaoAtual = operacao;
            System.out.println("Operação depois: " + operacaoAtual); 
            labelValor.setText("0");
        } catch(Exception e) {
            JOptionPane.showMessageDialog(labelValor, e.getMessage());
        }
    }

    // calcula o resultado final
    public static void calcResultado() {
        try {
            novaOperacao("=");
            labelValor.setText(String.valueOf(valorCalculado));
        } catch(Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // registro dos cliques
    public static void novoClique(String botao) {
        switch(botao){
            case "C": //caso o usuário clique em C, limpa tudo
                labelValor.setText("0");
                valorCalculado = 0;
                break;
            case "=":
                calcResultado();
                break;
            case "+":
                novaOperacao("+");
                break;
            case "-":
                novaOperacao("-");
                break;
            case "X":
                novaOperacao("X");
                break;
            case "/":
                novaOperacao("/");
                break;
            case "^":
                novaOperacao("^");
                break;
            default:
                if((labelValor.getText() == "0" || labelValor.getText() == "0.0") && botao != ".") {
                    labelValor.setText(botao);
                } else {
                    labelValor.setText(labelValor.getText() + botao);
                }
                break;
        }
    }

    public static void main(String[] args) {
        JFrame calculadora = montaJanela();
        calculadora.setVisible(true);
    }
}