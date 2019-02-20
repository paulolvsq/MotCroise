package pobj.motx.tme1;

import java.util.ArrayList;
import java.util.List;

public class Emplacement {
	/**
	 * attributs de la classe Emplacement :
	 * une ArrayList de Case qui contient les lettres d'un mot dans un emplacement de la grille
	 */
	private List<Case> lettres = new ArrayList<Case>();
	
	public Emplacement(List<Case> lettres) {
		this.lettres = lettres;
	}
	/**
	 * affiche le mot à l'emplacement voulu dans la grille 
	 */
	public String toString() {
		String res = "";
		for(Case c: lettres) {
			res += c.getChar();
		}
		return res;
	}
	/**
	 * retourne la taille de la liste des lettres 
	 * @return lettres.size() (on l'utilise pour respecter l'encapsulation)
	 */
	public int size() {
		return lettres.size();
	}
	/**
	 * accesseur sur la liste des lettres d'un emplacement
	 * @return lettres
	 */
	public List<Case> getLettres(){
		return this.lettres;
	}
	/**
	 * retourne la case d'indice i dans la liste des lettres
	 * @param i
	 * @return this.lettres.get(i)
	 */
	public Case getCase(int i) {
		return this.lettres.get(i);
	}
	/**
	 * retourne true s'il y a au moins une case vide dans l'emplacement
	 * sinon false
	 * @return boolean
	 */
	public boolean hasCaseVide() {
		for(Case c : this.lettres) {
			if(c.isVide())
				return true;
		}
		return false;
	}

}
