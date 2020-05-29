package allumettes;

public class JeuReel implements Jeu {
	private int nombreAllumettes;

	public JeuReel(int nombreAllumettes) {
		this.nombreAllumettes = nombreAllumettes;
	}

	// constructeur par défaut
	public JeuReel() {
		this(13);
	}

	@Override
	public int getNombreAllumettes() {
		return this.nombreAllumettes;
	}

	@Override
	public void retirer(int nbPrises) throws CoupInvalideException {
		int maxPrise = Math.min(this.nombreAllumettes, Jeu.PRISE_MAX);
		if (nbPrises > maxPrise) {
			throw new CoupInvalideException(nbPrises, "> " + maxPrise);
		} else if (nbPrises < 1) {
			throw new CoupInvalideException(nbPrises, "< 1");
		} else {
			this.nombreAllumettes -= nbPrises;
		}
	} // fin de la méthode retirer

}
