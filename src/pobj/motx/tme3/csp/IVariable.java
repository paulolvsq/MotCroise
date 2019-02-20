package pobj.motx.tme3.csp;

import java.util.List;

public interface IVariable {
	/**
	 * retourne la liste de String qui correspond à l'ensemble des valeurs que peut prendre chaque variable
	 * @return list
	 */
	public List<String> getDomain();

}
