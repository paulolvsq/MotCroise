package pobj.motx.tme2;

public class CroixContrainte implements IContrainte {
	/**
	 * attributs de la classe CroixContrainte
	 * quatre entiers : m1 et m2 sont les indices des emplacements et c1, c2 les indices des cases de l'emplacement dans lesquelles a lieu un croisement
	 */
	private int m1, c1, m2, c2;
	/**
	 * le constructeur prend en paramètres les quatres entiers et les transmet à l'instance de CroixContrainte
	 * @param m1
	 * @param c1
	 * @param m2
	 * @param c2
	 */
	public CroixContrainte(int m1, int c1, int m2, int c2) {
		this.c1 = c1;
		this.m1 = m1;
		this.c2 = c2;
		this.m2 = m2;
	}
	/**
	 * prend en paramètre une GrillePotentiel grille
	 * calcule l'ensemble des lettres l1 pouvant figurer dans la case d'indice c1 de l'emplacement m1
	 * de même pour l2, un autre ensemble de lettres avec cette fois l'emplacement m2 et la case d'indice c2
	 * calcule ensuite l'intersection des deux ensembles de lettres l1 etl2 dans un ensemble res
	 * en fonction de la taille de res par rapport à la taille de l1 et la taille de l2 on ajoute le nombre de mots filtrés à une variable
	 * on retourne le nombre de mots filtrés
	 */
	public int reduce(GrillePotentiel grille) {
		int nbMotsElimines = 0;
		EnsembleLettre l1 = grille.getMotsPot().get(m1).indiceEns(c1);
		EnsembleLettre l2 = grille.getMotsPot().get(m2).indiceEns(c2);
		EnsembleLettre res = l1.intersection(l2);
		if(res.size() < l1.size())
			nbMotsElimines += grille.getMotsPot().get(m1).filtreParLettre(res, c1);
		if(res.size() < l2.size())
			nbMotsElimines += grille.getMotsPot().get(m2).filtreParLettre(res, c2);
		return nbMotsElimines;
	}
	/**
	 * méthode equals qui vérifie si deux instances de CroixContrainte sont identiques
	 * compare chacun des attributs m1, m2, c1, c2
	 */
	public boolean equals(Object o) {
		if(o == this)
			return true;
		if(!(o instanceof CroixContrainte))
			return false;
		CroixContrainte cc = (CroixContrainte) (o);
		return (cc.m1 == this.m1 && cc.m2 == this.m2 && cc.c1 == this.c1 && cc.c2 == this.c2) || (cc.m1 == this.m2 && cc.m2 == this.m1 && cc.c1 == this.c2 && cc.c2 == this.c1);
	}

}
