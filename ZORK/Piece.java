import java.util.HashMap;
import java.util.Set;
import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Une piece dans un jeu d'aventure.
 * <p>
 *
 * Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode
 * texte.
 * </p>
 * <p>
 *
 * Une "Piece" represente un des lieux dans lesquels se déroule l'action du jeu.
 * Elle est reliee a au plus quatre autres "Piece" par des sorties. Les sorties
 * sont etiquettées "nord", "est", "sud", "ouest". Pour chaque direction, la
 * "Piece" possède une rÃ©fÃ©rence sur la piece voisine ou null s'il n'y a pas
 * de sortie dans cette direction.
 * </p>
 *
 * @author Michael Kolling
 * @author Marc Champesme (pour la traduction francaise)
 *
 * @author DIALLO Thierno (pour les modifications)
 * @author BARRY Ibrahima (pour les modifications)
 * @author HAMADOUCHE Nawal (pour les modifications)
 * @version 1.1
 * @since August 2000
 */
public class Piece  extends ArrayListConteneur{

	private String description;
	private Map<String, Piece> sorties;
	//private ArrayList<ObjetZork> lesObjetslesObjets = new ArrayList<ObjetZork>();
	private int nbObjets;

	/**
	 * Initialise une piece décrite par la chaine de caractères spécifiée.
	 * Initialement, cette piece ne possède aucune sortie. La description
	 * fournie est une courte phrase comme "la bibliothèque" ou
	 * "la salle de TP".
	 *
	 * @param description
	 *            Description de la piece.
	 */
	public Piece(String description) {
		this.description = description;
		sorties = new HashMap<String, Piece>();
		nbObjets = 0;
	}

	/**
	 * Définie les sorties de cette piece. A chaque direction correspond ou bien
	 * une piece ou bien la valeur null signifiant qu'il n'y a pas de sortie
	 * dans cette direction.
	 *
	 * @param nord
	 *            La sortie nord
	 * @param est
	 *            La sortie est
	 * @param sud
	 *            La sortie sud
	 * @param ouest
	 *            La sortie ouest
	 */

	public void setSorties(Piece nord, Piece est, Piece sud, Piece ouest) {
		if (nord != null) {
			sorties.put("nord", nord);
		}
		if (est != null) {
			sorties.put("est", est);
		}
		if (sud != null) {
			sorties.put("sud", sud);
		}
		if (ouest != null) {
			sorties.put("ouest", ouest);
		}
	}

	/**
	 * renvoie le nombre d'objets contenus dans une piece
	 *
	 * @return le nombre d'objets
	 */
	public int getNbObjets() {
		return nbObjets;
	}

	/**
	 * affiche tous les objets d'une piece
	 */
	public void afficherObjet() {
		System.out.print("les objets existant dans cette piece sont: ");
		for (int i = 0; i < getListeObjets().length; i++) {
			System.out.print(getListeObjets().get(i).getDescription());
			System.out.print(" " + getListeObjets().get(i).getPoids());
			System.out.print(", ");
		}
		System.out.println();
	}

	/**
	 * renvoie une sortie
	 *
	 * @return
	 */
	public Map<String, Piece> getSorties() {
		return sorties;
	}

	/**
	 * Renvoie la description de cette piece (i.e. la description spécifiée lors
	 * de la création de cette instance).
	 *
	 * @return Description de cette piece
	 */
	public String descriptionCourte() {
		return description;
	}

	/**
	 * Renvoie une description de cette piece mentionant ses sorties et
	 * directement formatée pour affichage, de la forme:
	 *
	 * <pre>
	 *  Vous etes dans la bibliothèque.
	 *  Sorties: nord ouest
	 * </pre>
	 *
	 * Cette description utilise la chaine de caractères renvoyée par la méthode
	 * descriptionSorties pour décrire les sorties de cette piece.
	 *
	 * @return Description affichable de cette piece
	 */
	public String descriptionLongue() {
		return "Vous etes  " + description + ".\n" + descriptionSorties();
	}

	/**
	 * Renvoie une description des sorties de cette piece, de la forme:
	 *
	 * <pre>
	 *  Sorties: nord ouest
	 * </pre>
	 *
	 * Cette description est utilisée dans la description longue d'une piece.
	 *
	 * @return Une description des sorties de cette pièce.
	 */
	public String descriptionSorties() {
		String resulString = "Sorties:";
		Set<String> keys = sorties.keySet();

		for (Iterator<String> iter = keys.iterator(); iter.hasNext();) {
			resulString += " " + iter.next();
		}
		return resulString;
	}

	/**
	 * Renvoie la piece atteinte lorsque l'on se déplace a partir de cette piece
	 * dans la direction spécifiée. Si cette piece ne possède aucune sortie dans
	 * cette direction, renvoie null.
	 *
	 * @param direction
	 *            La direction dans laquelle on souhaite se déplacer
	 * @return Piece atteinte lorsque l'on se déplace dans la direction
	 *         spécifiée ou null.
	 */
	public Piece pieceSuivante(String direction) {

		return sorties.get(direction);
	}

	/**
	 *
	 * @param l
	 *            'objet qui va être utiliser pour la comparaison
	 * @return le nombre d'objet contenu dans ObjetZork qui sont equals à oz
	 */
	public int contienCombienDe(ObjetZork oz) {
		int n = 0;
		for (int i = 0; i < getListeObjets().length; i++) {
			if (getListeObjets().get(i).equals(oz)) {
				n++;
			}
		}
		return n;
	}

	// public boolean contient(ObjetZork oz){
	// if(contienCombienDe(oz)>0){
	// return true;
	// }
	// return false;
	// }
	public boolean contient(ObjetZork oz) {
		if (getListeObjets().contains(oz))
			return true;
		return false;
	}

	/**
	 * permet de retirer un objet d'une à travers son nom et renvoie une copie
	 * de l'objet supprimé elle renvoie l'objet si non null
	 *
	 * @param nom
	 * @return un objet Zork
	 */
	public ObjetZork retirerObjet(String nom) {
		ObjetZork ob;

		for (int i = 0; i < getListeObjets().length; i++) {
			if (getListeObjets().get(i).getDescription().equalsIgnoreCase(nom)) {
				ob = getListeObjets().get(i);

				nbObjets--;
				getListeObjets().remove(i);
				return ob;
			}
		}
		return null;
	}

	/**
	 * elle ajoute un objet à l'ensemble des objets contenu dans une piece tout
	 * en augmentant le nombre d'objets et renvoie true l'ajout est reussi false
	 * si non
	 *
	 * @param oz
	 * @return
	 */


	/**
	 * renvoie la description de l'objet
	 *
	 * @param string
	 * @return
	 */
	public String getDescription(String string) {
		return description;
	}

	/**
	 * parcours l'ensemble des objets de la piece à travers le nom de l'objet
	 * passé en paramètre et le renvoie s'il le trouve si non renvoie null
	 *
	 * @param nomObjet
	 * @return
	 */
	public ObjetZork rechercherObjet(String nomObjet) {
		for (int i = 0; i < lesObjetslesObjets.size(); i++) {
			if (lesObjetslesObjets.get(i).getDescription().equalsIgnoreCase(nomObjet)) {
				return lesObjetslesObjets.get(i);
			}
		}
		return null;
	}

	/**
	 * renvoie un tableau d'objets contenant tous les elements de la piece
	 *
	 * @return
	 */
	@Override
	public boolean testAjouter(ObjetZork o) {
		// TODO Auto-generated method stub
		return false;
	}

}
