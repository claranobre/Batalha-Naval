package batalhanaval;

import batalhanaval.gui.*;

public class Principal {
	// Vers�o do jogo
	public static final float VERSAO = 1.1f;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Jogo novoJogo = new Jogo(Jogo.MEDIO);

		JanelaPrincipal principal = new JanelaPrincipal(novoJogo);
		principal.pack();
		principal.setVisible(true);
	}
}
