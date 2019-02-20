package pobj.motx.tme1;

import java.util.ArrayList;
import java.util.List;

public class GrillePlaces {
	/**
	 * attributs de la classe GrillePlaces :
	 * une grille 
	 * une ArrayList d'Emplacement qui contient tous les mots de la grille 
	 * le nombre de mots horizontaux
	 */
	private Grille grille;
	private List<Emplacement> places;
	private int nbHorizontaux;
	/**
	 * le constructeur prend en paramètre une grille et la transmet à la nouvelle instance de GrillePlaces
	 * il initialise l'ArrayList d'Emplacement
	 * explore la grille et détermine les emplacements des mots qu'elle contient
	 * PAS ENCORE FAIT
	 * @param grille
	 */
	public GrillePlaces (Grille grille) {
		this.grille = grille;
		places = new ArrayList<Emplacement>();
		for(int i = 0; i < grille.nbLig(); i++) {
			cherchePlaces(getLig(i));
		}
		nbHorizontaux = places.size();
		for(int j = 0; j < grille.nbCol(); j++) {
			cherchePlaces(getCol(j));
		}
	}
	/**
	 * retourne l'ArrayList des mots détectés dans la grille
	 * @return places
	 */
	public List<Emplacement> getPlaces(){
		return this.places;
	}
	/**
	 * retourne le nombre de mots horizontaux dans la grille
	 * @return nbHor
	 */
	public int getNbHorizontal() {
		return this.nbHorizontaux;
	}
	/**
	 * retourne la taille de l'ArrayList qui correspond aux emplacements des mots dans la grille
	 * @return places.size()
	 */
	public int size() {
		return places.size();
	}
	/**
	 * prend en paramètre une List de cases -> la grille
	 * ajoute dans un buffer temporaire les cases non pleines qui correspondent à un mot de la grille
	 * ajoute dans la liste des emplacements libres le mot libre qu'on vient de trouver
	 * répète ces opérations sur toute la grille
	 * @param cases
	 */
	private void cherchePlaces(List<Case> cases) {
		List<Case> mot = new ArrayList<>();
		for(int i=0; i < cases.size(); i++) {
			if(!cases.get(i).isPleine()) {
				mot.add(cases.get(i));
				i++;
				while(i < cases.size() && !cases.get(i).isPleine()) {
					mot.add(cases.get(i));
					i++;
				}
				if(mot.size() >= 2) {
					places.add(new Emplacement(mot));
				}
			}
			mot = new ArrayList<>();
		}
	}
	/**
	 * prend en paramètre la ligne sur laquelle on veut récupérer la liste des mots
	 * parcourt la ligne de la grille donnée en paramètre
	 * retourne la liste des cases de celle-ci
	 * @param lig
	 * @return lignes
 	 */
	private List<Case> getLig(int lig){
		List<Case> lignes = new ArrayList<Case>();
		for(int i = 0; i < grille.nbCol(); i++) {
			lignes.add(grille.getCase(lig, i));
		}
		return lignes;
	}
	/**
	 * prend en paramètres la colonne sur laquelle on veut récupérer la liste des mots
	 * parcourt la colonne de la grille donée en paramètre
	 * retourne la liste des cases de celle-ci
	 * @param col
	 * @return colonnes
	 */
	private List<Case> getCol(int col){
		List<Case> colonnes = new ArrayList<Case>();
		for(int i = 0; i < grille.nbLig(); i++) {
			colonnes.add(grille.getCase(i, col));
		}
		return colonnes;
	}
	/**
	 * affiche la GrillePlaces
	 */
	public String toString() {
		return grille.toString();
	}
	/**
	 * crée une nouvelle grille qui contient une copie de l'ancienne grille
	 * modifie les cases de la nouvelle grille du mot d'indice m passé en paramètre avec le mot solution soluce passé en paramètre
	 * @param m
	 * @param soluce
	 * @return gr (copie de la grille dans laquelle on a mis le mot solution)
	 */
	public GrillePlaces fixer(int m, String soluce) {
		GrillePlaces gr = new GrillePlaces(this.grille.copy());
		Emplacement tmp = gr.getPlaces().get(m);
		for(int i = 0; i < tmp.size(); i++) {
			Case c = tmp.getLettres().get(i);
			c.setChar(soluce.charAt(i));
		}
		return gr;
	}
	
}
