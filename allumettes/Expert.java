package allumettes;

public class Expert implements Strategie {

	@Override
	public int prise(Jeu jeu) {
		int priseMin = 1;
		switch ((jeu.getNombreAllumettes() - 1) % (Jeu.PRISE_MAX + 1)) {
		case 0: //
			return priseMin;
		default:
			return (jeu.getNombreAllumettes() - 1) % (Jeu.PRISE_MAX + 1);
		}
	}
}
