package allumettes;

/**
 * Lance une partie des 13 allumettes en fonction des arguments fournis sur la
 * ligne de commande.
 * 
 * @author Xavier Crégut
 * @version $Revision: 1.5 $
 */
public class Partie {

	/**
	 * Lancer une partie. En argument sont donnés les deux joueurs sous la forme
	 * nom@stratégie.
	 * 
	 * @param args la description des deux joueurs
	 */
	public static void main(String[] args) {
		try {
			verifierNombreArguments(args);
            int argMax = 3;
			// Recupérer le nom du joeur 1 et sa strategie dans un tableau
			// dont le premier élement représente le nom et le deuxième la strategie.

			String[] nomStrat1 = null;
			String[] nomStrat2 = null;
			if (args.length == 2) {
				nomStrat1 = initialiserNomStrategie(args, 0);
				nomStrat2 = initialiserNomStrategie(args, 1);
			} else if (args.length == argMax) {
				nomStrat1 = initialiserNomStrategie(args, 1);
				nomStrat2 = initialiserNomStrategie(args, 2);
			}
			// Vérification de la méthode choisie
			if ((args.length == argMax) && !(args[0].equals("-confiant"))) {
				throw new ConfigurationException("Vérifiez la méthode choisie");
			}

			// Initialisation des éléments nécessaires à la partie & vérification de la
			// saisie des stratégies
			// voir le cas défaut du switch initialiserModeJoueur
			JeuReel jeuPrincipal = new JeuReel(13);
			Joueur joueur1 = initialiserModeJoueur(nomStrat1);
			Joueur joueur2 = initialiserModeJoueur(nomStrat2);
			Arbitre arbitre = new Arbitre(joueur1, joueur2);
			if (args.length == argMax) {
				arbitre.setConfiance(true);
			} // rends l'arbitre confiant si c'est explicitement demandé.
				// Démarrage du jeu !!
			arbitre.arbitrer(jeuPrincipal);
			// Fin jeu.

		} catch (ConfigurationException e) {
			System.out.println();
			System.out.println("Erreur : " + e.getMessage());
			afficherUsage();
			System.exit(1);
		}
	}

	private static void verifierNombreArguments(String[] args) {
		final int nbJoueurs = 2;
		if (args.length < nbJoueurs) {
			throw new ConfigurationException("Trop peu d'arguments : " + args.length);
		}
		if (args.length > nbJoueurs + 1) {
			throw new ConfigurationException("Trop d'arguments : " + args.length);
		}
	}

	/** Afficher des indications sur la manière d'exécuter cette classe. */
	public static void afficherUsage() {
		System.out.println("\n" + "Usage :" + "\n\t" + "java allumettes.Partie joueur1 joueur2" + "\n\t\t"
				+ "joueur est de la forme nom@stratégie" + "\n\t\t"
				+ "strategie = naif | rapide | expert | humain | tricheur" + "\n" + "\n\t" + "Exemple :" + "\n\t"
				+ "	java allumettes.Partie Xavier@humain " + "Ordinateur@naif" + "\n");
	}

	private static String[] initialiserNomStrategie(String[] args, int nb) {
		String[] nomStrategie = new String[2];
		String[] arrOfStr = args[nb].split("@");
		nomStrategie[0] = arrOfStr[0];
		nomStrategie[1] = arrOfStr[1];
		return nomStrategie;
	}

	private static Joueur initialiserModeJoueur(String[] nomStrategie) {
		Joueur joueur = null;
		switch (nomStrategie[1]) {
		case "naif":
			Naif modeJeu = new Naif();
			joueur = new Joueur(nomStrategie[0], modeJeu);
			return joueur;
		case "rapide":
			Rapide modeJeu2 = new Rapide();
			joueur = new Joueur(nomStrategie[0], modeJeu2);
			return joueur;
		case "expert":
			Expert modeJeu3 = new Expert();
			joueur = new Joueur(nomStrategie[0], modeJeu3);
			return joueur;
		case "humain":
			Humain modeJeu4 = new Humain();
			joueur = new Joueur(nomStrategie[0], modeJeu4);
			joueur.setMode((Humain) new Humain());
			return joueur;
		case "tricheur":
			Tricheur modeJeu5 = new Tricheur();
			joueur = new Joueur(nomStrategie[0], modeJeu5);
			return joueur;
		default:
			throw new ConfigurationException("Vérifier la saisie des stratégies.");
		}
	}

}