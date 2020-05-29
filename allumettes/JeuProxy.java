package allumettes;

public class JeuProxy implements Jeu {
	private Jeu jeuR = null;

	public JeuProxy(Jeu jeu) {
		this.jeuR = jeu;
	}

	@Override
	public int getNombreAllumettes() {
		return this.jeuR.getNombreAllumettes();
	}

	@Override
	public void retirer(int nbPrises) throws CoupInvalideException {
		throw new OperationInterditeException();
	}
}
