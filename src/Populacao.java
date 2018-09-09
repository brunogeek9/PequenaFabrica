import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Populacao {
	private ArrayList<Individuo> individuos;	

	public Populacao(){
		individuos = new ArrayList<>();
	}
	
	public void iniciarPopulacao(int tam){
		for(int i = 0;i<tam;i++){
			individuos.add(new Individuo());
		}
		ordenarPopulacao();
		
	}
	
	public void ordenarPopulacao() {
		Collections.sort(individuos,Collections.reverseOrder());
		for (Individuo individuo : individuos) {
			individuo.avaliar();
		}
	}
	
	public Individuo getIndividuo(int pos) {
		return individuos.get(pos);
	}
	
	public void setIndividuo(Individuo individuo) {
		individuos.add(individuo);
	}
	
	public int getNumIndividuos() {
		return individuos.size();
	}
	
	public ArrayList<Individuo> getIndividuos() {
		return individuos;
	}

	public void setIndividuos(ArrayList<Individuo> individuos) {
		this.individuos.addAll(individuos);
	}
}
