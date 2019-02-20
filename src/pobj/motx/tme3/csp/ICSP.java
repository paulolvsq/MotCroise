package pobj.motx.tme3.csp;

import java.util.List;

public interface ICSP {
	/**
	 * retourne les variables du problème
	 * @return this.variables
	 */
	public List<IVariable> getVars();
	/**
	 * retourne true si le problème est encore satisfiable
	 * false sinon
	 * @return boolean
	 */
	public boolean isConsistent();
	/**
	 * prend en paramètre une IVariable vi et une String val
	 * affecte une des variables du problème
	 * retourne un nouveau problème CSP qui contient une variable de moins
	 * @param vi
	 * @param val
	 * @return
	 */
	public ICSP assign(IVariable vi, String val);

}
