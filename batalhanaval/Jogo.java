package batalhanaval;

import java.io.Serializable;


/**
 * Jogo de batalha naval.
 * 
 * @author Darlan P. de Campos
 * @author Roger de Córdova Farias
 * 
 */

public class Jogo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	//// Constantes
	// Níveis de dificuldade
	public static final int FACIL = 0;
	public static final int MEDIO = 1;
	public static final int DIFICIL = 2;

	// Estados do jogo
	public static final int POSICIONANDO_NAVIOS = 0;
	public static final int VEZ_JOG1 = 1;
	public static final int VEZ_JOG2 = 2;
	public static final int TERMINADO = 3;
	
	private Jogador[] jogadores;

	private int dificuldade;
	private int estado;

	public Jogo(int dif) {
		jogadores = new Jogador[2];
		jogadores[0] = new Jogador(this);
		jogadores[1] = new Robo(this);
		
		dificuldade = dif;
		estado = POSICIONANDO_NAVIOS;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	/**
	 * 
	 * @param i
	 * @return O jogador indicado por <code>i</code>
	 */
	public Jogador getJogador(int i) {
		return jogadores[i];
	}

	public int getDificuldade() {
		return dificuldade;
	}
	
	public int getEstado() {
		return estado;
	}
}
