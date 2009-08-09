package batalhanaval.exceptions;

/**
 * Exce��o que indica que a posi��o do tabuleiro
 * j� foi atingida.
 *  
 * @author Darlan P. de Campos
 * @author Roger de C�rdova Farias
 */
@SuppressWarnings("serial")
public class PosicaoJaAtingidaException extends Exception {

	public PosicaoJaAtingidaException() {
		super("Posi��o j� atingida!");
	}
}
