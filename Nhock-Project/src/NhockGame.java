
import javax.swing.JOptionPane;
import model.Cenario;
import model.Nhock;
import util.Util;
import view.JanelaPrincipal;
import view.Joystick;
import view.PainelCenario;

/**
 * Responsabilidade da Classe: Executar o loop do Jogo com os objetos principais.
 */
public class NhockGame {

	//Modelo
	Cenario cenario;
	Nhock nhock;

	//Vis�o
	JanelaPrincipal janela;
	PainelCenario painel;
	Joystick joystick;

	public NhockGame(){
		//Inicializa objetos b�sicos do modelo
		this.cenario = new Cenario();
		this.nhock = new Nhock();

		//Iniciliza objetos b�sicos da vis�o. Lembrando que a vis�o pode usar os dados do modelo
		//Por isso passamos os objetos do cen�rio e do nhock para o painel cen�rio.
		this.painel = new PainelCenario(this.cenario, this.nhock);
		//Inicializa respons�vel por capturar eventos do usu�rio.
		this.joystick = new Joystick();

		this.janela = new JanelaPrincipal(); //Cira janela

		this.janela.add(this.painel); //Adiciona painel
		this.janela.addKeyListener(joystick); //Adiciona KeyListener
		
		this.janela.setVisible(true); //Exibe
		this.painel.createBufferStrategy(2); //T�cnica para a janela parar de piscar
	}

	public void init(){
		while(true){		
			Util.sleep(180); //P�e o programa pra dormir por um tempo - mude akqui para aumentar ou diminuir a velocidade.

			//Pega eventos
			if(joystick.direitaPressionada()){ this.nhock.turnDIREITA(); }
			if(joystick.esquerdaPressionada()){ this.nhock.turnESQUERDA(); }
			if(joystick.cimaPressionada()){ this.nhock.turnCIMA(); }
			if(joystick.baixoPressionada()){ this.nhock.turnBAIXO(); }

			//Processa			
			this.nhock.step();

			//Controle de Colis�o

			//nhock bate no muro
			if(this.cenario.ehMuro(this.nhock.getCabeca())){
				JOptionPane.showMessageDialog(this.janela, "Voc� perdeu brow. N�o viu a parede?");
				System.exit(0);
			}

			//nhock bate nele mesmo
			if(this.nhock.temColisao()){
				JOptionPane.showMessageDialog(this.janela, "Voc� perdeu brow, t� achando que pode fazer raio-x em voc� mesmo?.!!");
				System.exit(0);
			}

			//nhock pega a semente
			if(this.cenario.ehSemente(this.nhock.getCabeca())){
				this.nhock.addPonto();
				this.cenario.sorteiaSemente();
			}

			//Desenha
			this.painel.renderGraphics();
			this.painel.desenha();
		}
			
	}
}
