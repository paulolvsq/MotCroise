package pobj.motx.tme2;

import java.util.HashSet;

public class EnsembleLettre {
	/**
	 * on utilise comme attribut un HashSet<Character> qui va stocker les lettres (plus efficace que ArrayList pour ce type de structure de données, éléments uniques dans un HashSet, unicit� de l'�l�ment dans le HashSet, ensemble consid�r� au sens math�matique)
	 */
	HashSet<Character> lettres = new HashSet<Character>();
	/**
	 * constrcuteur vide utilisé par défaut
	 */
	public EnsembleLettre() {
		
	}
	/**
	 * prend en paramètre une chaine de caractères 
	 * on convertit la chaine de caractères en tableau pour la parcourir facilement
	 * ajoute dans lettres chaque  lettre du tableau issu de la chaine donnée en paramètre
	 * @param s
	 */
	public EnsembleLettre(String s) {
		for (char c : s.toCharArray()) {
			this.lettres.add(c);
		}
	}
	/**
	 * ajoute un caractère c passé en paramètre dans la HashSet lettres
	 * @param c
	 */
	public void add(char c) {
		lettres.add(c);
	}
	/**
	 * retourne la taille de lettres
	 * @return this.lettres.size()
	 */
	public int size() {
		return this.lettres.size();
	}
	/**
	 * prend en paramètre un EnsembleLettre el
	 * retourne l'intersection entre this et el
	 * @param el
	 * @return new EnsembleLettre(res)
	 */
	public EnsembleLettre intersection(EnsembleLettre el) {
		String res = "";
		for(Character c : el.lettres) {
			if(this.lettres.contains(c))
				res += (char) (c);
		}
		return new EnsembleLettre(res);
	}
	/**
	 * prend en paramètre un caractère c
	 * retourne true si la HashSet lettres contient le caractère, false sinon
	 * @param c
	 * @return boolean
	 */
	public boolean contains(char c) {
		return this.lettres.contains((Character) (c));
	}
	/**
	 * retourne l'ensemble de lettres sous forme de chaine de caractères
	 */
	public String toString() {
		StringBuffer s = new StringBuffer();
		for(Character c : lettres) {
			s.append(c);
			s.append(" ,");
		}
		return s.toString();
			
	}

}
