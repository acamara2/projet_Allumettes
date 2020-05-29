package allumettes;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class RapideTest {
	// Les joueurs à utiliser pour les tests
	private Strategie fast = new Rapide();
	private Joueur joueur;
	private Jeu jeu;

	@Before
	public void setUp() {
		// Construire les points
		joueur = new Joueur("CAMARA", fast);
	}

	@Test
	public void TesterPrise1() {

		for (int i = 13; i >= 3; i--) {
			jeu = new JeuReel(i);
			assertEquals("Erreur,la prise n'est pas maximat", 3, joueur.getPrise(jeu));
		}
	}

	@Test
	public void TesterPrise2() {
		for (int i = 3; i > 0; i--) {
			jeu = new JeuReel(i);
			assertEquals("Erreur,la prise n'est pas maximat", i, joueur.getPrise(jeu));
		}
	}
}
