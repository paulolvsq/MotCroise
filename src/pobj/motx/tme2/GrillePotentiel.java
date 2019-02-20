package pobj.motx.tme2;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme1.Case;
import pobj.motx.tme1.Emplacement;
import pobj.motx.tme1.GrillePlaces;
import pobj.motx.tme3.csp.MotUnique;

public class GrillePotentiel {
	/**
	 * attributs de la classe GrillePotentiel :
	 * une GrillePlaces grille qui contient la grille sur laquelle on va placer les lettres dans les cases
	 * un Dictionnaire dico qui contient tous les mots du dictionnaire qui rentrent dans la grille
	 * une ArrayList de dictionnaires qui contient les mots potentiels qui peuvent aller dans les emplacements de la grille
	 * une ArrayList de IContraintes qui va contenir toutes les CroixContrainte de la grille
	 */
	private GrillePlaces grille;
	private Dictionnaire dico;
	private List<Dictionnaire> motsPot;
	private List<IContrainte> contraintes;
	/**
	 * le constrcuteur prend en paramètres une grille et un dictionnaire complet
	 * on commence par initialiser la grille et le dictionnaire
	 * filtre les mots potentiels par taille puis par lettre
	 * initialise les contraintes correctement
	 * les propage à la fin
	 * @param grille
	 * @param dicoComplet
	 */
	public GrillePotentiel(GrillePlaces grille, Dictionnaire dicoComplet) {
		this.grille = grille;
		dico = dicoComplet.copy();
		motsPot = new ArrayList<>();
		for (Emplacement emp : grille.getPlaces()) {
			Dictionnaire filtre = dicoComplet.copy();
			filtre.filtreLongueur(emp.size());
			String lettres = emp.toString();
			for (int i = 0; i < lettres.length(); i++) {
				if (lettres.charAt(i) != ' ') {
					filtre.filtreParLettre(lettres.charAt(i), i);
				}
			}
			motsPot.add(filtre);
		}
		contraintes = new ArrayList<IContrainte>();
		int nbH = grille.getNbHorizontal();
		for (int m1 = 0; m1 < nbH; m1++) {
			Emplacement emp_Horizontal = grille.getPlaces().get(m1);
			for (int m2 = nbH; m2 < grille.size(); m2++) {
				Emplacement emp_Vertical = grille.getPlaces().get(m2);
				for (int c1 = 0; c1 < emp_Horizontal.size(); c1++) {
					Case c1_tmp = emp_Horizontal.getCase(c1);
					for (int c2 = 0; c2 < emp_Vertical.size(); c2++) {
						Case c2_tmp = emp_Vertical.getCase(c2);
						if (c1_tmp.equals(c2_tmp) && c1_tmp.isVide()) {
							CroixContrainte contrainte = new CroixContrainte(m1, c1, m2, c2);
							if (!(contraintes.contains(contrainte)))
								contraintes.add(contrainte);
						}
					}
				}
			}
		}
		for(int m = 0; m < grille.getPlaces().size(); m++) {
			if(grille.getPlaces().get(m).size() == 1) {
				MotUnique mu = new MotUnique(m);
				if(!(contraintes.contains(mu)))
					contraintes.add(mu);
			}
		}
		propage();
	}
	/**
	 * le constrcuteur prend en paramètres une grille, un dictionnaire complet et une liste de dictionnaires potentiels
	 * on commence par initialiser la grille et le dictionnaire
	 * filtre les mots potentiels par taille puis par lettre
	 * initialise les contraintes correctement
	 * les propage à la fin
	 * ajout d'une contrainte de mot unique c'est à dire que le domaine ne contient qu'un seul mot 
	 * @param grille
	 * @param dicoComplet
	 * @param potentiel
	 */
	public GrillePotentiel(GrillePlaces grille, Dictionnaire dicoComplet, List<Dictionnaire> potentiel) {
		this.grille = grille;
		dico = dicoComplet.copy();
		motsPot = new ArrayList<>();
		List<Emplacement> places = grille.getPlaces();
		for (int e = 0; e < places.size(); e++) {
			Emplacement emp = places.get(e);
			Dictionnaire filtre = potentiel.get(e);
			filtre.filtreLongueur(emp.size());
			String lettres = emp.toString();
			for (int i = 0; i < lettres.length(); i++) {
				if (lettres.charAt(i) != ' ') {
					filtre.filtreParLettre(lettres.charAt(i), i);
				}
			}
			motsPot.add(filtre);
		}
		contraintes = new ArrayList<>();
		int nbH = grille.getNbHorizontal();
		for (int m1 = 0; m1 < nbH; m1++) {
			Emplacement emp_Horizontal = grille.getPlaces().get(m1);
			for (int m2 = nbH; m2 < grille.size(); m2++) {
				Emplacement emp_Vertical = grille.getPlaces().get(m2);
				for (int c1 = 0; c1 < emp_Horizontal.size(); c1++) {
					Case c1_tmp = emp_Horizontal.getCase(c1);
					for (int c2 = 0; c2 < emp_Vertical.size(); c2++) {
						Case c2_tmp = emp_Vertical.getCase(c2);
						if (c1_tmp.equals(c2_tmp) && c1_tmp.isVide()) {
							CroixContrainte cc = new CroixContrainte(m1, c1, m2, c2);
							if (!(contraintes.contains(cc)))
								contraintes.add(cc);
						}
					}
				}
			}
		}
		propage();
		
	}
	/**
	 * propage les contraintes 
	 * on réduit progressivement le dictionnaire possible 
	 * si on atteint une stabilité et que le nombre de mots éliminés ne bouge plus alors on retourn true
	 * sinon c'est qu'il n'y a aucun mot possible dans l'emplacement et on retourne false
	 * @return boolean
	 */
	private boolean propage() {
		int nbMotsElimines = 0;
		int i = 0;
		while (true) {
			i = 0;
			while (i < contraintes.size()) {
				nbMotsElimines += contraintes.get(i).reduce(this);
				i++;
			}
			if (nbMotsElimines == 0) {
				return true;
			} else {
				if (isDead())
					return false;
				else
					nbMotsElimines = 0;
			}
		}
	}
	/**
	 * retourne true s'il n'y a aucun mot possible dans l'emplacement
	 * false sinon
	 * @return boolean 
	 */
	public boolean isDead() {
		for (Dictionnaire dico : motsPot) {
			if (dico.size() == 0)
				return true;
		}
		return false;
	}
	/**
	 * retourne la liste des dictionnaires de mots potentiels
	 * @return this.motsPot;
	 */
	public List<Dictionnaire> getMotsPot() {
		return this.motsPot;
	}

	/**
	 * retourne la liste des contraintes
	 * @return this.contraintes
	 */
	public List<IContrainte> getContraintes() {
		return contraintes;
	}
	/**
	 * retourne la liste des emplacements de la grille
	 * @return this.grille.getPlaces();
	 */
	public List<Emplacement> getEmp() {
		return this.grille.getPlaces();
	}
	/**
	 * prend en paramètre un entier m et une chaine de caractère soluce
	 * retourne une nouvelle GrillePotentiel dans laquelle on a fixé la solution à partir du dictionnaire à l'emplacement m
	 * @param m
	 * @param soluce
	 * @return new GrillePotentiel(grille.fixer(m, soluce), dico)
	 */
	public GrillePotentiel fixer(int m, String soluce) {
		return new GrillePotentiel(grille.fixer(m, soluce), dico);
	}
	/**
	 * prend en paramètre un entier m, une String m et une liste de dictionnaire qui contient des mots
	 * retourne une nouvelle GrillePotentiel dans laquelle on a fixé la solution à partir du dictionnaire à l'emplacement m
	 * @param m
	 * @param soluce
	 * @param potentiel
	 * @return new GrillePotentiel(grille.fixer(m, soluce), dico, potentiel)
	 */
	public GrillePotentiel fixer(int m, String soluce, List<Dictionnaire> potentiel) {
		return new GrillePotentiel(grille.fixer(m, soluce), dico, potentiel);
	}
	/**
	 * retourne le dictionnaire 
	 * @return
	 */
	public Dictionnaire getDico() {
		return this.dico;
	}

}
