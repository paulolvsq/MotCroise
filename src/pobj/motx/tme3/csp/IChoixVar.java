package pobj.motx.tme3.csp;

public interface IChoixVar {
	/**
	 * prend en paramètre un ICSP problem 
	 * retourne la IVariable choisie pour résoudre le problème
	 * @param problem
	 * @return IVariable choix
	 */
	public IVariable chooseVar(ICSP problem); 

}
