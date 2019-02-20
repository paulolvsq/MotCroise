package pobj.motx.tme1;

public class Case {
	/**
	 * attributs de la classe Case : 
	 * une ligne
	 * une colonne
	 * un caractère
	 */
	private int lig;
	private int col;
	private char c;
	/**
	 * on transmet au constructeur une ligne, une colonne et un caractère pour une case
	 * @param lig
	 * @param col
	 * @param c
	 */
	public Case(int lig, int col, char c) {
		this.lig = lig;
		this.col = col;
		this.c = c;
	}
	/**
	 * accesseur sur l'indice de la ligne de la case
	 * @return lig
	 */
	public int getLig() {
		return this.lig;
	}
	/**
	 * accesseur sur l'indice de la colonne de la case
	 * @return col
	 */
	public int getCol() {
		return this.col;
	}
	/**
	 * accesseur sur le contenu de la case
	 * @return c
	 */
	public char getChar() {
		return this.c;
	}
	/**
	 * prend en paramètre le contenu de la case et le met dans la case
	 * @param c
	 */
	public void setChar(char c) {
		this.c = c;
	}
	/**
	 * renvoie true si la case est vide, false sinon
	 * @return boolean
	 */
	public boolean isVide() {
		return this.c == ' ';
	}
	/**
	 * renvoie true si la case est pleine, false sinon 
	 *ATTENTION : si une case contient une lettre, elle n'est ni pleine ni vide
	 * @return boolean
	 */
	public boolean isPleine() {
		return this.c == '*';    
	}
	/**
	 * affichage du caractère contenu dans la case
	 */
	public String toString() {
		return ""+this.c;
	}
	/**
	 * méthode equals qui vérifié si deux cases sont bien parfaitement identiques
	 */
	public boolean equals(Object o) {
		if(!(o instanceof Case)) return false;
		Case c = (Case) (o);
		return ((this.lig == c.lig) && (this.col == c.col) && (this.c == c.c));
	}
	
}
