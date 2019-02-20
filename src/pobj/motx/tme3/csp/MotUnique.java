package pobj.motx.tme3.csp;

import pobj.motx.tme2.Dictionnaire;
import pobj.motx.tme2.GrillePotentiel;
import pobj.motx.tme2.IContrainte;

public class MotUnique implements IContrainte {
	/**
	 * attributs de la classe MotUnique : 
	 * un entier m qui sert à stocker l'indice auquel chercher le dictionnaire que 
	 */
	private int m;
	
	public MotUnique(int m) {
		this.m = m;
	}
	/**
	 * prend en paramètre une GrillePotentiel grille
	 * on stocke dans un dictionnaire grille.getMotsPot.get(m)
	 * on le filtre pour qu'il n'y ait qu'un seul mot dedans
	 * on élimine ce mot du domaine de tous les autres mots de la grille
	 * on retourne le nombre de mots filtrés
	 */
	public int reduce(GrillePotentiel grille) {
		Dictionnaire tmp = grille.getMotsPot().get(m);
		int nbMotsElimines = tmp.filtreMotUnique(tmp.get(0), tmp);
		for(Dictionnaire d : grille.getMotsPot()) {
			d.getMots().remove(tmp.getMots().get(0));
			nbMotsElimines++;
		}
		return nbMotsElimines; 
	}
}
