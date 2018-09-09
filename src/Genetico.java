import java.util.ArrayList;
import java.util.Random;

public class Genetico {
	private Random rand;
	public static final double crossover = 0.8;
	public static final double taxaMutacao = 0.2;
	private static final int maxGeracoes = 1000;
	private static final int tamPopulacao = 300;
	private static final boolean elitismo = true;
	private int geracao;
	private Populacao populacao;
	private RoletaFabrica rf;
	public Genetico() {
		populacao = new Populacao();
		rand = new Random();
		geracao = 0;
	}

	public void principal() throws CloneNotSupportedException {
		populacao.iniciarPopulacao(tamPopulacao);
		do {
			populacao = geraPopulacao();
			geracao++;
		} while ((geracao <= maxGeracoes - 1));
		populacao.ordenarPopulacao();
		System.out.println("Geração " + geracao + " Melhor individuo " + populacao.getIndividuo(0));
		System.out.println(populacao.getNumIndividuos());

	}
	
	public Populacao geraPopulacao() throws CloneNotSupportedException {
		rf = new RoletaFabrica(populacao);
		rf.inicializaRoleta();
		Populacao populacaoTemporaria = new Populacao();
		ArrayList<Individuo> roleta = new ArrayList<>();
		if (elitismo) {
			populacaoTemporaria.setIndividuo(populacao.getIndividuo(0));
		}

		while (populacaoTemporaria.getNumIndividuos() < tamPopulacao) {
			/*roleta.add(rf.selecao());
			roleta.add(rf.selecao());
			populacaoTemporaria.setIndividuos(crossover(roleta));*/
			populacaoTemporaria.setIndividuos(crossover(selecaoTorneio()));
		}
		populacaoTemporaria.ordenarPopulacao();
		return populacaoTemporaria;

	}

	public ArrayList<Individuo> selecaoTorneio() {
		// criando uma populacao temporaria
		Populacao pi = new Populacao();
		pi.iniciarPopulacao(3);

		// colocando 3 individuos aleatoriamente na pi
		pi.setIndividuo(populacao.getIndividuo(rand.nextInt(populacao.getNumIndividuos())));
		pi.setIndividuo(populacao.getIndividuo(rand.nextInt(populacao.getNumIndividuos())));
		pi.setIndividuo(populacao.getIndividuo(rand.nextInt(populacao.getNumIndividuos())));
		pi.ordenarPopulacao();

		ArrayList<Individuo> pais = new ArrayList<>();
		// pegando os 0 e 1, estarei pegando os melhores(por pi estar ordenada)
		pais.add(pi.getIndividuo(0));
		pais.add(pi.getIndividuo(1));
		return pais;
	}
	
		
	public ArrayList<Individuo> crossover(ArrayList<Individuo> fathers) throws CloneNotSupportedException {
		ArrayList<Individuo> filhos = new ArrayList<>();
		double temp = 0;
		double[] genesi1 = new double[2];
		double[] genesi2 = new double[2];
		
		genesi1[0] = fathers.get(0).clone().getQtdP1();
		genesi1[1] = fathers.get(0).clone().getQtdP2();
		
		genesi2[0] = fathers.get(1).clone().getQtdP1();
		genesi2[1] = fathers.get(1).clone().getQtdP2();
		
		
		Individuo i1 = new Individuo(genesi1);
		Individuo i2 = new Individuo(genesi2);
		
		filhos.add(i1);
		filhos.add(i2);
		if (rand.nextDouble() <= crossover) {
			temp = filhos.get(0).getQtdP2();
			filhos.get(0).setQtdP2(filhos.get(1).getQtdP2());
			filhos.get(1).setQtdP2(temp);
		}
		return filhos;
	}

	

	public boolean estagnou(ArrayList<Individuo> individuos) {
		// double atual = individuos.get(0).getAptidao();
		int cont = 0;
		boolean estagnou = false;
		for (int i = 0; i < individuos.size() - 1; i++) {
			if (individuos.get(i).getAptidao() == individuos.get(i + 1).getAptidao()) {
				cont++;
				System.out.println(cont);
				if (cont >= 60) {
					estagnou = true;
				}
			} else {
				cont = 0;
			}
		}
		return estagnou;

	}
}
