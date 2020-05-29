package allumettes;

public class Tricheur implements Strategie {

	@Override
	public int prise(Jeu jeu) {
		// TODO Auto-generated method stub
		// trichons
		while (jeu.getNombreAllumettes() > 2) {
			try {
				jeu.retirer(1);
			} catch (CoupInvalideException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 1;
	}

}
