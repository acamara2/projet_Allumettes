package allumettes;

public class Rapide implements Strategie {
	public int prise(Jeu jeu) {
		if (jeu.getNombreAllumettes() >= Jeu.PRISE_MAX) {
			return jeu.PRISE_MAX;
		} else {
			return jeu.getNombreAllumettes();
		}
	}

}
