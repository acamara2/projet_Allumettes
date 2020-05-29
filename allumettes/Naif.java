package allumettes;

import java.util.Random;

public class Naif implements Strategie {

	@Override
	public int prise(Jeu jeu) {
		int min = 1;
		return min + (int) (Math.random() * ((jeu.PRISE_MAX - min) + 1));
	}

}
