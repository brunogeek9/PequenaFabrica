import java.util.ArrayList;

public class Teste {

	public static void main(String args[]){
		Genetico g = new Genetico();
		ArrayList<Individuo> inds = new ArrayList<>();
		double[] genes = new double[2];
		genes[0] = 8;
		genes[1] = 9;
		for (int i = 0; i <= 30; i++) {
			
			inds.add(new Individuo(genes));
		}
		double[] genes2 = new double[2];
		genes2[0] = 7;
		genes2[1] = 4;
		
		for (int i = 0; i < 10; i++) {
			inds.add(new Individuo(genes2));
		}
		for (Individuo i : inds) {
			System.out.println(i.getAptidao());
		}
		if(g.estagnou(inds)){
			System.out.println("estagnou");
		}else{
			System.out.println("nao estagnou");
		}
	}
}
