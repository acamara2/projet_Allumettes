package allumettes;

public class Joueur {
	private Strategie mode = null;
	private String nom;

	public Joueur(String nom, Strategie mo) {
		this.nom = nom;
		this.mode = mo;
	}

	public String getNom() {
		return this.nom;
	}

	public int getPrise(Jeu jeu) {
		return mode.prise(jeu);
	}

	public Strategie getMode() {
		return this.mode;
	}

	public void setMode(Strategie mode) {
		this.mode = mode;
	}

}
