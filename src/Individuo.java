import java.util.Random;

public class Individuo implements Comparable<Individuo>,Cloneable{
	private Random random = new Random();
	private Double aptidao;
	private double qtdP1, qtdP2;

	public double getAptidao() {
		return aptidao;
	}

	public void setAptidao(double aptidao) {
		this.aptidao = aptidao;
	}
	
	public double getQtdP1() {
		return qtdP1;
	}

	public void setQtdP1(double qtdP1) {
		this.qtdP1 = qtdP1;
	}

	public double getQtdP2() {
		return qtdP2;
	}

	public void setQtdP2(double qtdP2) {
		this.qtdP2 = qtdP2;
	}
	public void setQtdp1(){
		this.qtdP1 = random.nextDouble()*100;
	}
	
	public void setQtdp2(){
		this.qtdP2 = random.nextDouble()*100;
	}
	
	public Individuo(){
		do {
			setQtdp1();
			setQtdp2();
		} while (!validar());
		avaliar();
	}
	
	public Individuo(double genes[]){
		qtdP1 = genes[0];
		qtdP2 = genes[1];
		if(random.nextDouble() <= Genetico.taxaMutacao){
			int pa = random.nextInt(genes.length);
			System.out.println(pa);
			mutacao(pa);
		}
		avaliar();
	}
	
	public boolean validar(){
		double tempoMaquina = (3*qtdP1) + (4*qtdP2);
		double materiaPrima = (9*qtdP1) + (7*qtdP2);
		if(tempoMaquina<=200 && materiaPrima<=300){
			return true;
		}else{
			return false;
		}
	}
	
	public void avaliar(){
		aptidao = (2*qtdP1) + (5*qtdP2);
	}
	
	public void mutacao(int posicao) {
		do {
			if (posicao == 0)
				this.setQtdp1();
			else if (posicao == 1)
				this.setQtdp2();
		} while (!validar());

	}

	@Override
	public String toString() {
		return "Aptidao :" + aptidao + " qtdP1 :" + qtdP1 + ", qtdP2 " + qtdP2;
	}
	
	@Override
	public int compareTo(Individuo i) {
		return this.aptidao.compareTo(i.aptidao);
	}
	
	@Override
	public Individuo clone() throws CloneNotSupportedException {
		double genes[] = new double[2];
		genes[0] = qtdP1;
		genes[1] = qtdP2;
		return new Individuo(genes);
	}
	
}
