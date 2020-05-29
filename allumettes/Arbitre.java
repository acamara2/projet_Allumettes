package allumettes;

public class Arbitre {
	private Joueur joueur1;
	private Joueur joueur2;
	private boolean confiant = false;

	public Arbitre(Joueur j1, Joueur j2) {
		this.joueur1 = j1;
		this.joueur2 = j2;
	}

	public Arbitre(Joueur j1, Joueur j2, boolean confiance) {
		this(j1, j2);
		this.confiant = confiance;
	}

	public void arbitrer(Jeu jeu) {
		boolean triche = false;
		Jeu jeuMandataire;

		if (confiant) { // Mode de jeu confiant
			jeuMandataire = jeu;
		} else { // Mode de jeu non confiant
			jeuMandataire = new JeuProxy(jeu);
		}
		Joueur joueurEnCours = null;
		while (jeu.getNombreAllumettes() > 0 && !triche) {
			joueurEnCours = getJoueurEnAttente(joueurEnCours
					                          ,joueur1, joueur2);
			try {
				fairePrise(jeu, jeuMandataire, joueurEnCours);
			} catch (OperationInterditeException e) {
				triche = true;
			}
			// Verification que le joueur en attente n'a pas gagné.
			if (jeu.getNombreAllumettes() == 0) {
				presenterGagnantPerdant(getJoueurEnAttente(joueurEnCours, joueur1, joueur2), joueurEnCours);
			}

		}
		// S'il y'a triche crier!!!
		if (triche) {
			System.out.println("Partie abandonnée car " + joueurEnCours.getNom() + " a triché !");
		}
	}

	public boolean getConfiance() {
		return this.confiant;
	}

	public void setConfiance(boolean confiance) {
		this.confiant = confiance;
	}

	///////////////////// Sous méthodes ////////////////////////////
	private void presenterJeu(Jeu jeu, Joueur j) {
		System.out.println();
		System.out.println("Nombre d'allumettes restantes : " + jeu.getNombreAllumettes());
		System.out.println("Au tour de " + j.getNom() + ".");
	}

	private void presenterPrise(int prise, Joueur joueur) {
		if (prise >= 2) {
			System.out.println(joueur.getNom() + " prend " + prise + " allumettes.");
		} else {
			System.out.println(joueur.getNom() + " prend " + prise + " allumette.");
		}
	}

	private void presenterGagnantPerdant(Joueur gagnant, Joueur perdant) {
		System.out.println(perdant.getNom() + " a perdu !");
		System.out.println(gagnant.getNom() + " a gagné !");
	}

	private void afficherErreur(CoupInvalideException e) {
		System.out.print("Erreur ! ");
		System.out.println(e.getMessage());
		System.out.println("Recommencez !");
	}

	private void fairePrise(Jeu jeu, Jeu jeuMandataire, Joueur j) {
		boolean priseInvalide;
		int prise = 1;
		do { // Boucle pour la validité de la saisie.
			presenterJeu(jeu, j);
			try {
				prise = j.getPrise(jeuMandataire);
				presenterPrise(prise, j);
				jeu.retirer(prise);
				// Si on arrive dans cette partie du code c'est que la prise est valide
				priseInvalide = false;
			} catch (CoupInvalideException e) {
				afficherErreur(e);
				priseInvalide = true;
			}
		} while (priseInvalide);
	}

	private Joueur getJoueurEnAttente(Joueur joueurEnCours, Joueur j1, Joueur j2) {
		if (joueurEnCours == j2 || joueurEnCours == null) {
			return j1;
		} else {

			return j2;
		}
	}

}
