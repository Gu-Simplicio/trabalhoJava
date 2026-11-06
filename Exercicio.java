import java.awt.*;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Exercicio {   
    // valores usados para operações, além da String que guardará as transações 
    static int[] valoresParaCalc = new int[2];
    static String operacao;

   public static JPanel montaBotoes()  {
        JPanel painelCalculadora = new JPanel();
        painelCalculadora.setLayout(new GridLayout(5, 4, 8, 8));

        String[] botoesCalculadora = {
            "C", "/", "X", "-",
            "7", "8", "9", "+",
            "4", "5", "6", " ",
            "1", "2", "3", "=",
            " ", "0", ".", " "
        };

        for(String botao : botoesCalculadora) {
            if(botao.trim().isEmpty()) {
                // adiciona espaço em branco
                painelCalculadora.add(new JLabel());
            } else {
                JButton btn = new JButton(botao);
                btn.setFont(new Font("Arial", Font.BOLD, 18));
                btn.setFocusPainted(false);
                
                painelCalculadora.add(btn);
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
        
        JPanel calculadora = montaBotoes();
        janela.add(calculadora, BorderLayout.CENTER);

        return janela;
    }

    public static void main(String[] args) {
        JFrame calculadora = montaJanela();
        calculadora.setVisible(true);
    }
}