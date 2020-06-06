package jogoDaVelha;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class JogoDaVelha extends JFrame {

	final int PRIMEIRO_JOGADOR = 1;
	final int SEGUNDO_JOGADOR = 2;
	int vezJogador = PRIMEIRO_JOGADOR;
	int rodadasJogo = 0;
	
	ImageIcon iconCirculo = new ImageIcon( getClass().getResource("circulo.png") );
	ImageIcon iconX = new ImageIcon( getClass().getResource("x.png") );
	
	JPanel telaJogo = new JPanel(new GridLayout(3, 3, 10, 10));
	BotoesJogo[][] botoesJogoVelha = new BotoesJogo[3][3];
	JLabel informacaoJogador = new JLabel("JOGADOR : " + PRIMEIRO_JOGADOR);
	
	public JogoDaVelha() {
		configurarJanela();
		configurarTela();
	}
	
	public static void main(String[] args) {
		new JogoDaVelha();
	}
	
	public void mudarVezJogador() {
		if( vezJogador == PRIMEIRO_JOGADOR ) {
			vezJogador = 2;
			informacaoJogador.setText("Jogado : " + 2);
			informacaoJogador.setForeground(Color.RED);
		} else {
			vezJogador = 1;
			informacaoJogador.setText("Jogado : " + 1);
			informacaoJogador.setForeground(new Color(50, 200, 50));
		}
	}
	
	public boolean isVencendor(int jogadorInformado) {
		if( botoesJogoVelha[0][0].verificacaoJogador == jogadorInformado && botoesJogoVelha[0][1].verificacaoJogador == jogadorInformado && botoesJogoVelha[0][2].verificacaoJogador == jogadorInformado ) {
			return true;
		} 
		if( botoesJogoVelha[1][0].verificacaoJogador == jogadorInformado && botoesJogoVelha[1][1].verificacaoJogador == jogadorInformado && botoesJogoVelha[1][2].verificacaoJogador == jogadorInformado ) {
			return true;
		}
		if( botoesJogoVelha[2][0].verificacaoJogador == jogadorInformado && botoesJogoVelha[2][1].verificacaoJogador == jogadorInformado && botoesJogoVelha[2][2].verificacaoJogador == jogadorInformado ) {
			return true;
		}
		if( botoesJogoVelha[0][0].verificacaoJogador == jogadorInformado && botoesJogoVelha[1][0].verificacaoJogador == jogadorInformado && botoesJogoVelha[2][0].verificacaoJogador == jogadorInformado ) {
			return true;
		}
		if( botoesJogoVelha[0][1].verificacaoJogador == jogadorInformado && botoesJogoVelha[1][1].verificacaoJogador == jogadorInformado && botoesJogoVelha[2][1].verificacaoJogador == jogadorInformado ) {
			return true;
		}
		if( botoesJogoVelha[0][2].verificacaoJogador == jogadorInformado && botoesJogoVelha[1][2].verificacaoJogador == jogadorInformado && botoesJogoVelha[2][2].verificacaoJogador == jogadorInformado ) {
			return true;
		}
		if( botoesJogoVelha[0][0].verificacaoJogador == jogadorInformado && botoesJogoVelha[1][1].verificacaoJogador == jogadorInformado && botoesJogoVelha[2][2].verificacaoJogador == jogadorInformado ) {
			return true;
		}
		if( botoesJogoVelha[0][2].verificacaoJogador == jogadorInformado && botoesJogoVelha[1][1].verificacaoJogador == jogadorInformado && botoesJogoVelha[2][0].verificacaoJogador == jogadorInformado ) {
			return true;
		}
		return false;
	}
	
	public void configurarJanela() {
		
		setTitle("JOGO DA VELHA");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700, 600);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void configurarTela() {
		add(BorderLayout.CENTER, telaJogo);
		add(BorderLayout.NORTH, informacaoJogador);
		telaJogo.setBackground(Color.BLACK);
		informacaoJogador.setFont(new Font("Arial", Font.BOLD, 20));
		informacaoJogador.setForeground(new Color(50, 200, 50));
		informacaoJogador.setHorizontalAlignment(SwingConstants.CENTER);
		
		for (int colunaJogo = 0; colunaJogo < 3; colunaJogo++) {
			for (int linhaJogo = 0; linhaJogo < 3; linhaJogo++) {
				BotoesJogo criandoBotoesJogo = new BotoesJogo();
				botoesJogoVelha[colunaJogo][linhaJogo] = criandoBotoesJogo;
				telaJogo.add(criandoBotoesJogo);
			}
		}
	}
	
	public class BotoesJogo extends JButton {
		int verificacaoJogador = 0;
		
		public BotoesJogo() {
			setBackground(Color.WHITE);
			addActionListener( eventoClickMouse -> { 
				if( verificacaoJogador == 0 ) {
					if ( vezJogador == PRIMEIRO_JOGADOR ) {
						setIcon(iconCirculo);
						verificacaoJogador = PRIMEIRO_JOGADOR;
					} else {
						setIcon(iconX);
						verificacaoJogador = SEGUNDO_JOGADOR;
					}
					if (isVencendor(verificacaoJogador)) {
						JOptionPane.showMessageDialog(null, "Jogador " + vezJogador + " VENCEU!");
						System.exit(0);
					}
					rodadasJogo++;
					if( rodadasJogo == 9 ) {
						JOptionPane.showMessageDialog(null, "Deu velha!");
						System.exit(0);
					}
					mudarVezJogador();
				}
			});
		}
	}
}