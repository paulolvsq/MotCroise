package pobj.motx.tme3.csp;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme2.Dictionnaire;
import pobj.motx.tme2.GrillePotentiel;

public class DicoVariable implements IVariable {
	/**
	 * attributs de la classe DicoVariable
	 * un entier indice qui correspond à l'indice de l'emplacement du mot correspondant
	 * une GrillePotentiel gp
	 * une List<String> domaine défini à partir de l'emplacement du mot dans la GrillePotentiel
	 */
	private int indice;
	private GrillePotentiel gp;
	private List<String> domaine;
	
	public DicoVariable(int indice, GrillePotentiel gp) {
		this.indice = indice;
		this.gp = gp;
		domaine = new ArrayList<>();
		Dictionnaire dic_tmp = gp.getMotsPot().get(indice);
		for(int i = 0; i < dic_tmp.size(); i++) {
			domaine.add(dic_tmp.get(i));
		}
	}
	/**
	 * retourne le domaine
	 */
	public List<String> getDomain() {
		return this.domaine;
	}
	
	public String toString() {
		String res = "";
		for(String mot : this.domaine)
			res += (String) (mot) + "\t";
		return res;
	}
	/**
	 * retourne l'indice
	 * @return this.indice
	 */
	public int getIndice() {
		return this.indice;
	}

}
