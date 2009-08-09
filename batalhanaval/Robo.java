package batalhanaval;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Jogador autom�tico, utilizando uma intelig�ncia computacional
 * b�sica.
 * 
 * @param jogo O jogo atual
 * @author Darlan P. de Campos
 * @author Roger de C�rdova Farias
 */
public class Robo extends Jogador {
	
	private static final long serialVersionUID = 1L;
	
	public static final int ACERTOU_AGUA = 0;
	public static final int ACERTOU_NAVIO = 1;
	
	public static final int PERDIDO = -1;
	public static final int NORTE = 0;
	public static final int SUL = 1;
	public static final int LESTE = 2;
	public static final int OESTE = 3;

	private int estado;
	private int direcao;
	private Point ultimoAcerto;
	private ArrayList<Point> naviosAcertados;
	
	public Robo(Jogo jogo) {
		super(jogo);
		
		direcao = PERDIDO;
		naviosAcertados = new ArrayList<Point>();
		
		for (Navio navio : getFrota()) {
			posicionaNavio(navio);
		}
	}
	
	/**
	 * O m�todo de ataque do jogador rob�.
	 * 
	 * Age de acordo com o n�vel de dificuldade do jogo:<br><br>
	 * 
	 * F�cil: atira aleatoriamente.<br>
	 * M�dio: pode atirar aleatoriamente ou com intelig�ncia.<br>
	 * Dif�cil: atira com intelig�ncia.
	 * 
	 */
	public int atira (){
		if (getJogo().getDificuldade()==Jogo.FACIL){
			return super.atira();
		}else if(getJogo().getDificuldade()==Jogo.MEDIO){
			int i = (int)(Math.random()*2);

			if (i==1){
				return super.atira();
			}
		}
		return atiraInteligente();
	}
	
	/**
	 * Posiciona aleatoriamente os navios.
	 * 
	 * @param n Navio sendo posicionado.
	 */
	private void posicionaNavio(Navio n) {
		int x = (int) (Math.random() * 10);
		int y = (int) (Math.random() * 10);
		int orientacao = (int) (Math.random() * 2);

		Point pos = new Point(x, y);
		n.setPosicao(pos);
		n.setOrientacao(orientacao);
		if (!getTabuleiro().cabeNavio(n)) {
			posicionaNavio(n);
		} else{
			getTabuleiro().adicionaNavio(n);
		}
	}

	/**
	 * Efetua um ataque inteligente ao tabuleiro do
	 * inimigo.
	 * 
	 * @return int Valor atingido.
	 */
	private int atiraInteligente(){
		if (estado == 0){			
			int res = super.atira();
			
			if (res != 1) {
				estado = ACERTOU_NAVIO;
				ultimoAcerto = getTiros().get(getTiros().size()-1);
				naviosAcertados.add(ultimoAcerto);
			}
			return res;
		} else {			
			int y = ultimoAcerto.y;
			int x = ultimoAcerto.x;

			// Quando o rob� chegar a uma posi��o em que o �ltimo
			// acerto est� cercado por posi��es inv�lidas,
			// n�o pode entrar em loop infinito tentando encontrar
			// uma posi��o v�lida.
			int tentativas = 0;
			
			do {
				if (direcao == PERDIDO){
					direcao = (int)(Math.random()*4);
				}

				y = ultimoAcerto.y;
				x = ultimoAcerto.x;
				
				tentativas++;
				
				switch (direcao) {
				case NORTE:
					y -= 1;				
					break;
				case SUL:
					y += 1;				
					break;
				case LESTE:
					x += 1;				
					break;
				case OESTE:
					x -= 1;				
					break;
				}
				if(!getOponente().getTabuleiro().posicaoValida(x, y)){
					if (tentativas <= 4){
						direcao = (direcao + 1) % 4;
					} else if (naviosAcertados.lastIndexOf(ultimoAcerto) > 0){
						tentativas = 0;
						direcao = PERDIDO;
						ultimoAcerto = naviosAcertados.get(
								naviosAcertados.lastIndexOf(ultimoAcerto)-1 );
					} else {
						estado = ACERTOU_AGUA;
						return super.atira();
					}
				}
			} while (!getOponente().getTabuleiro().posicaoValida(x, y));
			
			try {
				int res = super.atira(x, y);
				if (res > 1) {
					if (getOponente().getNavio(res).estaDestruido()) {
						estado = ACERTOU_AGUA;
						// Verifica se acertou algum outro navio antes
						while (naviosAcertados.size() > 0 && estado == ACERTOU_AGUA) {
							Point pt = naviosAcertados.get(naviosAcertados.size()-1);
							if (getOponente().getTabuleiro().getPosicao(pt.x, pt.y)
									== -res) {
								naviosAcertados.remove(naviosAcertados.size()-1);
							} else {
								ultimoAcerto = pt;
								estado = ACERTOU_NAVIO;
							}
						}
					} else {
						ultimoAcerto = getTiros().get(getTiros().size()-1);
						naviosAcertados.add(ultimoAcerto);
					}
				} else {
					direcao = PERDIDO;
				}
				return res;
				
			} catch (Exception e) { return 0; }
		
		}
	}
}
