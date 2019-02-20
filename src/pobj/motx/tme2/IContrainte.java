package pobj.motx.tme2;

public interface IContrainte {
	
	/**
	 * interface à une seule méthode : 
	 * int reduce(GrillePotentiel grille)
	 */

	/**
	 * prend en paramètre une GrillePotentiel grille
	 * calcule l'ensemble des lettres l1 pouvant figurer dans la case d'indice c1 de l'emplacement m1
	 * de même pour l2, un autre ensemble de lettres avec cette fois l'emplacement m2 et la case d'indice c2
	 * calcule ensuite l'intersection des deux ensembles de lettres l1 etl2 dans un ensemble res
	 * en fonction de la taille de res par rapport à la taille de l1 et la taille de l2 on ajoute le nombre de mots filtrés à une variable
	 * on retourne le nombre de mots filtrés
	 */
	int reduce(GrillePotentiel grille);
	
}
