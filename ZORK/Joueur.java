
import java.util.ArrayList;

/**
 * Un joueur dans un jeu d'aventure en mode texte.
 *
 * Un joueur peu transporté des ObjetsZork avec lui. chaque ObjetsZork a un
 * poids. Le joueur ne peut transporter des ObjetsZork dans la mesure ou le
 * poids total des ObjetsZork transportés ne dépasse pas le poids max fixé . Un
 * joueur est caracterisé par : la piece courante où il se trouve son nom le
 * poidCourant total des objets transporté le poids max à atteint fixé lors de
 * la creation du joueur le nombre d'objet transporté les objets qu'il
 * transporte
 *
 * @invariant getNom() != null;
 * @invariant getPoidsCourant() >= 0 && getPoidsCourant() <= getPoidsMax();
 * @invariant setPoidsMax() >= 0;
 *
 * @author DIALLO Thierno
 * @author BARRY Ibrahima
 * @author HAMADOUCHE Nawal
 *
 */

public class Joueur extends ArrayListConteneur{

	private Piece pieceCourante;
	private String nomJoueur;
	private int poidsMax; // le poids_max que le joueur est autorisé à
							// transporter
	private int poidsCourant; // le poids qu_il est entrain de transporter à
								// l'instant
	//private ArrayList<ObjetZork> lesObjetsTransportes = new ArrayList<ObjetZork>(); // les
																					// objets
																					// que

	// transportent le
	// joueur

	/**
	 * initialise un joueur dont le nom est la chaine de caracteres specifies et
	 * le poids max specifie ne possedant aucun poids courant
	 *
	 * @param nJoueur
	 * @param pMax
	 */
	public Joueur(String nJoueur, int pMax) {
		nomJoueur = nJoueur;
		poidsMax = pMax;
		poidsCourant = 0;

	}

	/**
	 * renvoie la piece courante
	 *
	 * @return la piece courante
	 */
	public Piece getPieceCourante() {
		return pieceCourante;

	}

	/**
	 * renvoie le nom du joueur
	 *
	 * @return le nom du joueur
	 */
	public String getNomJoueur() {
		return nomJoueur;
	}

	/**
	 * renvoie le poids max du joueur
	 *
	 * @return le poids max
	 */
	public int getPoidsMax() {
		return poidsMax;
	}

	/**
	 * renvoie le poids courant du joueur
	 *
	 * @return le poids courant
	 */
	public int getPoidsCourant() {
		return poidsCourant;
	}

	/**
	 * ajoute l'objet zork specifié à la liste d'objets transportés par le
	 * joueur renvoie true si l'objet est transportable false si non
	 *
	 * @param oz
	 * @return
	 */

	public  boolean  testAjouter(ObjetZork o){
		poidsCourant = poidsCourant + o.getPoids();
		if(poidsCourant < poidsMax)
			return true;
			return false;
	}
	
	@Override
	
	public boolean ajouter(ObjetZork oz) {
		if (testAjouter(oz) == true) {
			ajouter(oz);
			return true;
		}
		return false;
		}



	/**
	 * renvoie un objetzork à partir de son nom s'il fait partie des objets
	 * transportés par le joueur null si non
	 *
	 * @param nom
	 * @return ob
	 */

	/**
	 * affiche tous les objets que transporte le joueur
	 */

	public void afficherObjetJoueur() {
		System.out.print("les objets transporter par le joueur sont: ");
		for (int i = 0; i < getListeObjets().length; i++) {
			System.out.print(getListeObjets().get(i).getDescription());
			System.out.print(" " + getListeObjets().get(i).getPoids());
			System.out.print(", ");
		}
		System.out.println();
	}

	/**
	 * renvoie un objet parmi les objets transportés par le joueur a partir de
	 * sa description si non renvoie null
	 *
	 * @param nomObjet
	 * @return objetZork
	 */
	public ObjetZork rechercherObjetJoueur(String nomObjet) {

		for (int i = 0; i < getListeObjets().length; i++) {
			if (getListeObjets().get(i).getDescription().equals(nomObjet)) {
				return getListeObjets().get(i);
			}
		}
		return null;
	}

	/**
	 * Renvoie l'ensemble des objets transportés par le joueur
	 *
	 * @return
	 */
	

	/**
	 * renvoie le nombre d'objet transportés par le joueur
	 *
	 * @param lesObjetsTransportes
	 * @return
	 */
	public int nombreObjets(ArrayList<ObjetZork> lesObjetsTransportes) {
		return getListeObjets().length;
	}

}
