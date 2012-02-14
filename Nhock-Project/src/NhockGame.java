
import javax.swing.JOptionPane;
import model.Cenario;
import model.Nhock;
import view.JanelaPrincipal;
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

	public NhockGame(){
		//Inicializa objetos b�sicos do modelo
		this.cenario = new Cenario();
		this.nhock = new Nhock();

		//Iniciliza objetos b�sicos da vis�o. Lembrando que a vis�o pode usar os dados do modelo
		//Por isso passamos os objetos do cen�rio e do nhock para o painel cen�rio.
		this.painel = new PainelCenario(this.cenario, this.nhock);

		this.janela = new JanelaPrincipal();
		this.janela.add(this.painel);
		this.janela.setVisible(true);
	}

	public void init(){
		//executa loop do jogo
			//Pega eventos
			//Processa
			//Desenha
	}
}
