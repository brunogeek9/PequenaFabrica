
import java.util.Random;

public class RoletaFabrica {
	private Populacao populacao;
	private double pesos[];
	private double fatia[];
	private double total;

	public RoletaFabrica(Populacao p) {
		populacao = p;
		pesos = new double[populacao.getNumIndividuos()];
		fatia = new double[populacao.getNumIndividuos()];
	}

	// Esse metodo tem que ser chamado uma vez a cada geracao do AG
	// Ja o rodarRoleta eh chamado muitas vezes numa msm geracao
	// Sendo assim, eh melhor chamar inicializarRoleta a cada geracao do AG ao
	// inves de chamar em rodarRoleta
	// (por isso ele fica public para ser chamado do AG)
	public void inicializaRoleta() {
		total = 0;
		//Soma total das aptidoes
		for (int i = 0; i < populacao.getNumIndividuos(); i++) {
			total += populacao.getIndividuo(i).getAptidao();
		}
		
		//pesos de cada aptidao individual
		for (int i = 0; i < pesos.length; i++) {
			pesos[i] = populacao.getIndividuo(i).getAptidao() / total;
		}
		
		/*
		 * A taxa de aptidao de cada individuo tem que englobar uma faixa de
		 * valores diferente para poder "fatiar a pizza", exemplo:
		 * se peso[0] = 0,15 e peso[1] = 0,15, 
		 * entao fatia[0] = 0,15 (qq valor entre 0 e 0,15 sorteara o individuo0) 
		 * e fatia[1] = 0,30 (qq valor entre 0,16 e 0,3 sorteara o individuo1)
		 */
		for(int i = 0; i < fatia.length; i++){
			if(i == 0)
				fatia[i] = pesos[i];
			else
				fatia[i] = fatia[i - 1] + pesos[i];
		//	System.out.println("\nIndividuo " + populacao.getIndividuo(i).getAptidao() + " Fatia " + fatia[i]);
		}
		
	}

	public int rodarRoleta() {
		double random = new Random().nextDouble();

		for (int i = 0; i < fatia.length; i++) {
			if(random < fatia[i]){
			//	System.out.println("\nRoleta: "+random+" sorteou: "+i + " Fatia: "+fatia[i]);
				return i; //individuo sorteado
			}
		}
		return 0;
	}
	
	public Individuo selecao(){
		return populacao.getIndividuo(rodarRoleta());
	}

	public Populacao getPopulacao() {
		return populacao;
	}

	public void setPopulacao(Populacao populacao) {
		this.populacao = populacao;
	}

	public double[] getPesos() {
		return pesos;
	}

	public void setPesos(double[] pesos) {
		this.pesos = pesos;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	
}
