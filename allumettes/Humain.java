package allumettes;

import java.util.Scanner;

public class Humain implements Strategie {
	 Scanner sc = new Scanner(System.in);

	@Override
	public int prise(Jeu jeu) {

		System.out.print("Combien prenez-vous d'allumettes ? ");
		try {
			int choix = sc.nextInt();
			return choix;
		} catch (Exception e) {
			sc.nextLine();
			System.out.println("Vous devez donner un entier.");
			return prise(jeu);
		}
	}
}
