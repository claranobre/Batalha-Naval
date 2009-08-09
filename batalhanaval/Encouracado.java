package batalhanaval;

/**
 * Encoura�ado.
 * 
 * @author Darlan P. de Campos
 * @author Roger de  C�rdova Farias
 *
 */

@SuppressWarnings("serial")
public class Encouracado extends Navio {
	
	/**
	 * Constroi um novo encoura�ado.
	 * 
	 * @param jog Jogador
	 */
	public Encouracado(Jogador jog) {
		super("Encoura�ado", 4, 16, jog);
	}
}

