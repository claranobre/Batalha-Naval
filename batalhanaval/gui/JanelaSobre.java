package batalhanaval.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import batalhanaval.*;

/**
 * Di�logo de cr�ditos do jogo.
 *
 * @author Darlan P. de Campos
 * @author Roger de C�rdova Farias
 */

@SuppressWarnings("serial")
public class JanelaSobre extends JDialog implements ActionListener {
	public JanelaSobre(JFrame p) {
		super(p, "Sobre", true);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(p); // Centraliza a janela

		JPanel painel = new JPanel(new BorderLayout());
		painel.setPreferredSize(new Dimension(300, 250));
		painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JTextArea creditos = new JTextArea("Batalha Naval " + Principal.VERSAO
				+ "\n\nUm jogo multiplataforma de estrat�gia, "
				+ "\ncom uso de intelig�ncia artificial."
				+ "\n\nUFSC/CTC/INE"
				+ "\nTurma: INE 5605 238B - Desenvolvimento"
				+ "\nde Sistemas Orientados a Objetos"
				+ "\n\nProfessor: Marco Aurelio Wehrmeister"
				+ "\n\nAcad�micos:\nDarlan Pedro de Campos\n"
				+ "Roger de C�rdova Farias");
		creditos.setOpaque(false);
		creditos.setEditable(false);

		painel.add(creditos);
		add(painel, BorderLayout.NORTH);
		
		JButton ok = new JButton("OK");
		ok.addActionListener(this);
		getRootPane().setDefaultButton(ok);
		
		JPanel painelOk = new JPanel(); 
		painelOk.add(ok);
		add(painelOk);
	}

	// Tratamento de a��o do bot�o OK
	public void actionPerformed(ActionEvent e) {
		JanelaSobre.this.dispose();
	}
}
