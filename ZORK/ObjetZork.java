/**
 * Un ObjetZork est une classe permertant de créer des objets
 * 
 * un ObjetZork est caracterisé par :
 * 
 * sa description son poids sa transportabilité
 * 
 * @invariant getDescription() != null;
 * @invariant getPoids() > 0;
 * @invariant (getIsTransportable()==false || getIsTransportable()==true);
 * 
 * 
 * @author DIALLO Thierno
 * @author BARRY Ibrahima
 * @author HAMADOUCHE Nawal
 */

public class ObjetZork {
	private final static int POIDSMAX = 50;
	private String description;
	private boolean transportable;
	private int poids;

	/**
	 * Le constructeur de la ObjetZork qui initialise chaque attribut à sa
	 * valeur correspondante passer en param
	 * 
	 * @param description
	 * @param transportable
	 * @param poids
	 */
	public ObjetZork(String description, int poids) {
		this.description = description;
		this.poids = poids;
	}

	/**
	 * renvoie la description de l'objet
	 * 
	 * @return la description
	 */
	public String getDescription() {
		return description;
	}

	/*
	 * renvoie true si le poids de l'objet est inferieur au poids Max false si
	 * non
	 */
	public boolean getIsTransportable() {
		if (poids > POIDSMAX) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * renvoie le poids de l'objet
	 * 
	 * @return le poids
	 */
	public int getPoids() {
		return poids;
	}

	/**
	 * affiche les caracteristiques de l'objet
	 */
	public void afficherObjet() {
		System.out.println(getPoids());
		System.out.println(getDescription());
		System.out.println(getIsTransportable());
	}

	/**
	 * redefinition d'equals pour comparer deux objets zork
	 * 
	 * @return true si deux objets sont identiques ou false si non
	 */
	public boolean equals(Object oz) {
		if (!(oz instanceof ObjetZork)) {
			return false;
		} else {
			ObjetZork o = (ObjetZork) oz;
			if (getDescription().equals(o.getDescription())
					&& poids == o.getPoids()
					&& transportable == o.getIsTransportable()) {
				return true;
			}
		}

		return false;
	}

	/**
	 * redefinition toString pour décrire un objetzork
	 * 
	 * 
	 */
	public String toString() {

		String str = "ObjetZork : " + getDescription() + " Poids" + getPoids()
				+ " transportable =" + getIsTransportable();

		return str;
	}

	/**
	 * redefinition de hashcode
	 */
	public int hashCode() {
		if (!getIsTransportable()) {
			return description.hashCode();
		} else {
			return (int) (description.hashCode() + 25 * poids);
		}
	}

}
