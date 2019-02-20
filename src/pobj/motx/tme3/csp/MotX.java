package pobj.motx.tme3.csp;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme1.Emplacement;
import pobj.motx.tme2.Dictionnaire;
import pobj.motx.tme2.GrillePotentiel;

public class MotX implements ICSP {
	/**
	 * attributs de la classe MotX
	 * une GrillePotentiel gp qui contient une grille
	 * une List<DicoVariable> dicos qui contient une liste de DicoVariable
	 * une List<IVariable> variables qui contient une liste de IVariables
	 */
	private GrillePotentiel gp;
	private List<DicoVariable> dicos;
	private List<IVariable> variables;
	
	public MotX(GrillePotentiel gp) {
		this.gp = gp;
		dicos = new ArrayList<>();
		variables = new ArrayList<>();
		List<Dictionnaire> tmp = gp.getMotsPot();
		for(int i = 0; i < tmp.size(); i++) {
			Emplacement emp = gp.getEmp().get(i);
			if(emp.hasCaseVide()) {
				DicoVariable dicoV = new DicoVariable(i, gp);
				dicos.add(dicoV);
				variables.add((IVariable) (dicoV));
			}
		}
	}
	/**
	 * retourne la liste des variables
	 */
	public List<IVariable> getVars() {
		return this.variables;
	}
	/**
	 * retourne true si le problème est encore satisfiable c'est à dire s'il existe un DicoVariable dont le domaine est vide
	 * false sinon
	 * @return boolean
	 */
	public boolean isConsistent() {
		for(DicoVariable dv : this.dicos) {
			if(dv.getDomain().size() == 0)
				return false;
		}
		return true;
	}
	
	/**
	 * prend en paramètre une IVariable vi et une String val
	 * affecte une des variables du problème
	 * retourne un nouveau problème CSP qui contient une variable de moins
	 * @param vi
	 * @param val
	 * @return new ICSP
	 */
	public ICSP assign(IVariable vi, String val) {
		if(vi instanceof DicoVariable) {
			DicoVariable dv = (DicoVariable) (vi);
			int indice = dv.getIndice();
			return new MotX(gp.fixer(indice, val, gp.getMotsPot()));
		}
		return null;
	}

}
