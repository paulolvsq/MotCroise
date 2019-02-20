package pobj.motx.tme2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Dictionnaire {
	/**
	 * attributs de la classe Dictionnaire 
	 * une List<String> mots qui sert à stocker les mots du dictionnaire
	 * un tableau d'EnsembleLettres tmp qui sert à stocker temporairement des ensembles de lettres
	 */
	private List<String> mots = new ArrayList<>();
	private EnsembleLettre[] cache;

	/**
	 * Ajoute un mot au Dictionnaire, en dernière position.
	 * 
	 * @param mot
	 *            à ajouter, il sera stocké en minuscules (lowerCase)
	 */
	public void add(String mot) {
		mots.add(mot.toLowerCase());
	}

	/**
	 * Taille du dictionnaire, c'est à dire nombre de mots qu'il contient.
	 * 
	 * @return la taille
	 */
	public int size() {
		return mots.size();
	}

	/**
	 * Accès au i-eme mot du dictionnaire.
	 * 
	 * @param i
	 *            l'index du mot recherché, compris entre 0 et size-1.
	 * @return le mot à cet index
	 */
	public String get(int i) {
		return mots.get(i);
	}

	/**
	 * Rend une copie de ce Dictionnaire.
	 * @return une copie identique de ce Dictionnif (cache == null) {
	 */
	public Dictionnaire copy() {
		Dictionnaire copy = new Dictionnaire();
		copy.mots.addAll(mots);
		copy.setCache(getCache());
		return copy;
	}

	/**
	 * prend en paramètre la taille len
	 * filtre tous les mots du dictionnaire qui n'ont pas len caractères
	 * retourne le nombre de mots supprimés du dictionnaire
	 * @param len
	 * @return cpt : le nombre de mots supprimés
	 */
	public int filtreLongueur(int len) {
		List<String> cible = new ArrayList<>();
		int cpt = 0;
		for (String mot : mots) {
			if (mot.length() == len)
				cible.add(mot);
			else
				cpt++;
		}
		mots = cible;
		if (cpt > 0) videCache();
		return cpt;
	}
	
	/**
	 * méthode toString qui affiche la taille du dictionnaire 
			int taille = this.mots.get(0).length();
	 */
	public String toString() {
		if (size() == 1) {
			return mots.get(0);
		} else {
			return "Dico size =" + size();
		}
	}
	/**
	 * prend en paramètres un chemin vers un fichier sous forStringBuffer s = new StringBuffer();me de chaine de caractères 
	 * lit le contenu du fichier 
	 * crée une instance de Dictionnaire avec le contenu du fichier comme étant les mots
	 * retourne ce dictionnaire
	 * @param path
	 * @return dico 
	 */
	public static Dictionnaire loadDictionnaire(String path) {
		Dictionnaire dico = new Dictionnaire();
		try (Stream<String> stream = Files.lines(Paths.get(path))) {
			stream.forEach(dico::add);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dico;
	}

	/**
	 * prend en paramètres un char c et un indice i 
	 * parcourt tous les mots du dictionnaire et filtre ceux qui n'ont pas un caractère c à l'indice i du mot
	 * ajoute les mots qui ont un caractère c à l'indice i dans une liste de String res
	 * affecte à mots la liste res
	 * retourne le nombre de mots qui ont été filtrés 
	 * @param c
	 * @param i
	 * @return cpt
	 */
	public int filtreParLettre(char c, int i) {
		List<String> res = new ArrayList<>();
		int cpt = 0;
		for (String mot : mots) {
			if (mot.charAt(i) == c) {
				res.add(mot);
			} else {
				cpt++;
			}
		}
		mots = res;
		if (cpt > 0) this.videCache();
		return cpt;
	}
	/**
	 * prend en paramètres un EnsembleLettres el et un indice i 
	 * parcourt tous les mots du dictionnaire et ajoute dans une liste résultat les lettres des mots dans une liste res
	 * on n'oublie pas de vérifier si la liste res contient déjà la lettre
	 * affecte à mots la liste res
	 * retourne le nombre de mots qui ont été filtrés 
	 * @param el
	 * @param i
	 * @return cpt
	 */
	public int filtreParLettre(EnsembleLettre el, int i) {
		List<String> res = new ArrayList<>();
		int cpt = 0;
		for (String mot : mots) {
			char c = mot.charAt(i);
			if (el.contains(c)) {
				res.add(mot);
			} else {
				cpt++;
			}
		}
		mots = res;
		if (cpt > 0) this.videCache();
		return cpt;
	}
	/**
	 * prend en paramètre un entier i qui sert d'indice
	 * regarde si la liste des mots est vide et si c'est le cas renvoie un nouvel EnsembleLettre
	 * sinon on récupère l'EnsembleLettre de tmp à l'indice i
	 * on parcourt tous les mots du dictionnaire et on stocke dans un EnsembleLettre res les lettres des mots à l'indice i
	 * on prend soin de vérifier dans la méthode add que l'on ajoute pas une lettre déjà dans res
	 * @param i
	 * @return res
	 */
	public EnsembleLettre indiceEns(int i) {
		if (this.mots.isEmpty()) {
			return new EnsembleLettre();
		}
		EnsembleLettre res = this.getCache()[i];
		if (res == null) {
			res = new EnsembleLettre();
			for (String mot : this.mots) {
				res.add(mot.charAt(i));
			}
			this.getCache()[i] = res;
			//System.out.println("affichage du cache : " + cache[i].toString());
		}
		return res;
	}
	/**
	 * retourne l'attribut tmp qui correspond à un tableau d'EnsembleLettre
	 * s'il n'existe pas on en crée un et on le retourne
	 * @return tmp
	 */
	private EnsembleLettre[] getCache() {
		if (cache == null) {
			int taille = this.mots.get(0).length();
			cache = new EnsembleLettre[taille];
		}
		return this.cache;
	}
	/**
	 * vide cache et le met à null
	 */
	private void videCache() {
		this.cache = null;
	}
	/**
	 * prend en paramètre un tableau d'EnsembleLettre
	 * set tmp dans les attributs du Dictionnaire
	 * @param tmp
	 */
	public void setCache(EnsembleLettre[] tmp) {
		this.cache = tmp;
	}
	/**
	 * prend en paramètre l'indice de la case indexOf()du cache dont on veut l'ensemble de lettres
	 * retourne un ensemble de lettres à la case i du cache
	 * @param i
	 * @return cache[i]
	 */
	public EnsembleLettre charAt(int i) {
		if(mots.size() == 0) return new EnsembleLettre();
		if (cache == null) {
			int taille = this.mots.get(0).length();
			cache = new EnsembleLettre[taille];
		}
		if(cache[i] == null) {
			StringBuffer s = new StringBuffer();
			for(String mot : mots) {
				s.append(mot.charAt(i));
			}
			cache[i] = new EnsembleLettre(s.toString());
		}
		return cache[i];
	}
	/**
	 * retourne la liste des mots
	 * @return mots
	 */
	public List<String> getMots(){
		return this.mots;
	}
	/**
	 * prend en paramètre une chaine de caractère correspondant à un mot et un dictionnaire
	 * parcourt toutes les lettres du mot, pour chaque mot du dictionnaire, réalise un filtrage par lettre du mot à l'indice i 
	 * on obtient un dictionnaire dans lequel ont été supprimés tous les mots n'étant pas identiques au mot passé en paramètre
	 * incrémente un compteur du nombre de mots qu'on a filtré à chaque fois qu'on appelle la fonction filtreMotUnique
	 * @param mot
	 * @param dico
	 * @return
	 */
	public int filtreMotUnique(String mot, Dictionnaire dico) {
		int cpt = 0;
		for(int i = 0; i < mot.length(); i++) {
			for(int j = 0; j < dico.size(); j++) {
				cpt += filtreParLettre(mot.charAt(i), i);
			}
		}
		//System.out.println("mots dans le dico : "+dico.size());
		return cpt;
	}

}
