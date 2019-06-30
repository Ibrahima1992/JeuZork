/**
 * Classe principale du jeu "Zork".
 * <p>
 * 
 * Zork est un jeu d'aventure très rudimentaire avec une interface en mode
 * texte: les joueurs peuvent juste se déplacer parmi les différentes pieces. Ce
 * jeu nécessite vraiment d'etre enrichi pour devenir intéressant!
 * </p>
 * <p>
 * 
 * Pour jouer a ce jeu, créer une instance de cette classe et appeler sa méthode
 * "jouer".
 * </p>
 * <p>
 * 
 * Cette classe crée et initialise des instances de toutes les autres classes:
 * elle crée toutes les pieces, crée l'analyseur syntaxique et démarre le jeu.
 * Elle se charge aussi d'exécuter les commandes que lui renvoie l'analyseur
 * syntaxique.
 * </p>
 * 
 * @author DIALLO Thierno
 * @author BARRY Ibrahima
 * @author HAMADOUCHE Nawal
 * 
 * @author Michael Kolling
 * @author Marc Champesme (pour la traduction francaise)
 * @version 1.1
 * @since March 2000
 */

public class Jeu {
	private AnalyseurSyntaxique analyseurSyntaxique;
	private Piece pieceCourante;
	private Piece piecePrecedente;

	/**
	 * Crée le jeu et initialise la carte du jeu (i.e. les pièces).
	 */
	public Jeu() {

		creerPieces();
		analyseurSyntaxique = new AnalyseurSyntaxique();
	}

	/**
	 * declaration des differentes pieces
	 */
	Piece dehors;
	Piece salleTD;
	Piece salleTP;
	Piece hallBatimentC;
	Piece salleExamen;
	/**
	 * declation d'un joueur
	 */
	Joueur unJoueur;
	/**
	 * declaration et initialisation des objets
	 */
	ObjetZork carteEtudiant = new ObjetZork("carteEtudiant", 10);
	ObjetZork stylo = new ObjetZork("stylo", 20);
	ObjetZork ficheDeTable = new ObjetZork("ficheDeTable", 15);
	ObjetZork calculette = new ObjetZork("calculette", 20);
	ObjetZork boisson = new ObjetZork("boisson", 18);
	ObjetZork cafe = new ObjetZork("cafe", 8);
	ObjetZork polycopie = new ObjetZork("polycopie", 15);
	ObjetZork feuilleTP = new ObjetZork("feuilleTP", 3);
	ObjetZork feuilleTD = new ObjetZork("feuilleTD", 4);
	ObjetZork tableau = new ObjetZork("tableau", 300);
	ObjetZork table = new ObjetZork("table", 100);
	ObjetZork ordinateur = new ObjetZork("ordinateur", 200);

	public void creerPieces() {

		// creation d'un joueur
		unJoueur = new Joueur("toto", 50);
		// initialisation des differentes pieces
		dehors = new Piece("dehors");
		salleTD = new Piece("salleTD");
		salleTP = new Piece("salleTP");
		hallBatimentC = new Piece("hallBatimentC");
		salleExamen = new Piece("salleExamen");

		// ajout des differents dans la piece hallBatimentC
		hallBatimentC.ajouterObjet(stylo);
		hallBatimentC.ajouterObjet(carteEtudiant);
		hallBatimentC.ajouterObjet(boisson);
		hallBatimentC.ajouterObjet(calculette);
		hallBatimentC.ajouterObjet(cafe);
		hallBatimentC.ajouterObjet(feuilleTD);
		hallBatimentC.ajouterObjet(feuilleTP);
		hallBatimentC.ajouterObjet(ficheDeTable);
		hallBatimentC.ajouterObjet(polycopie);
		salleTD.ajouterObjet(tableau);
		salleTD.ajouterObjet(table);
		salleTP.ajouterObjet(ordinateur);
		salleExamen.ajouterObjet(table);
		salleExamen.ajouterObjet(tableau);

		// initialise les sorties des pieces

		dehors.setSorties(null, null, hallBatimentC, null);
		salleTD.setSorties(hallBatimentC, null, null, null);
		salleTP.setSorties(null, null, null, hallBatimentC);
		hallBatimentC.setSorties(dehors, salleTP, salleTD, salleExamen);
		salleExamen.setSorties(null, hallBatimentC, null, null);

		pieceCourante = dehors;
	}

	/**
	 * le joueur gagne s'il se trouve dans la salle de TD muni de sa feuille de
	 * td
	 * 
	 * @param unJoueur
	 * @return
	 */
	public boolean gain(Joueur unJoueur) {
		if (pieceCourante == salleTD
				&& (unJoueur.rechercherObjetJoueur("feuilleTD") == feuilleTD))
			return true;
		return false;
	}

	/**
	 * le joueur pert s'il se trouve dans la salle de TD sans sa feuille de td
	 * 
	 * @param unJoueur
	 * @return
	 */
	public boolean perte(Joueur unJoueur) {
		if (pieceCourante == salleTD
				&& (unJoueur.rechercherObjetJoueur("feuilleTD") != feuilleTD))
			return true;
		return false;
	}

	/**
	 * creer un joueur private Piece pieceCourante; private String nomJoueur;
	 * private double poidsMax; // le poids_max que le joueur est autorisé à
	 * transporter private double poidsCourant; // le poids qu_il est entrain de
	 * transporter à l'instant private ArrayList<ObjetZork>
	 * lesObjetsTransportes; // les objets que transportent le joueur
	 */

	/**
	 * Pour lancer le jeu. Boucle jusqu'a la fin du jeu.
	 */
	public void jouer() {
		afficherMsgBienvennue();
		// Entrée dans la boucle principale du jeu. Cette boucle lit
		// et exécute les commandes entrées par l'utilisateur, jusqu'a
		// ce que la commande choisie soit la commande "quitter"
		boolean termine = false;
		while (!termine) {
			Commande commande = analyseurSyntaxique.getCommande();
			termine = traiterCommande(commande);

			if (gain(unJoueur)) {
				pieceCourante.afficherObjet();
				System.out.println("gain");
				termine = true;

				// System.out.println("cette piece est vide");
			} else if (perte(unJoueur)) {
				pieceCourante.afficherObjet();
				System.out.println("perte");
				termine = true;
			}
			pieceCourante.afficherObjet();
		}

		System.out.println("Merci d'avoir jouer.  Au revoir.");
	}

	/**
	 * Affiche le messa/ge d'accueil pour le joueur.
	 */
	public void afficherMsgBienvennue() {
		System.out.println();
		System.out.println("Bienvennue dans le monde de Zork !");
		System.out
				.println("Zork est un nouveau jeu d'aventure, terriblement enuyeux.");
		System.out.println("Tapez 'aide' si vous avez besoin d'aide.");
		System.out.println();
		System.out.println(pieceCourante.descriptionLongue());
	}

	/**
	 * Exécute la commande spécifiée. Si cette commande termine le jeu, la
	 * valeur true est renvoyée, sinon false est renvoyée
	 * 
	 * @param commande
	 *            La commande a exécuter
	 * @return true si cette commande termine le jeu ; false sinon.
	 */
	public boolean traiterCommande(Commande commande) {
		if (commande.estInconnue()) {
			System.out.println("Je ne comprends pas ce que vous voulez...");
			return false;
		}

		String motCommande = commande.getMotCommande();
		String secondeComande = commande.getSecondMot();
		if (motCommande.equals("aide")) {
			afficherAide();
		} else if (motCommande.equals("aller")) {
			piecePrecedente = pieceCourante;
			deplacerVersAutrePiece(commande);
		} else if (motCommande.equals("prendre")) {
			if (pieceCourante.rechercherObjet(secondeComande) == null) {
				System.out.println("cet objet n'est pas dans cette piece");
			} else {
				ObjetZork oz = pieceCourante.rechercherObjet(secondeComande);
				if (oz.getIsTransportable() == false) {
					System.out
							.println("vous ne pouvez pas transporter cet objet");
				} else {
					// pieceCourante.retirerObjet(secondeComande);
					unJoueur.ajouterObjetJoueur(pieceCourante
							.retirerObjet(secondeComande));
					System.out.println("vous avez recuperer l'objet");
				}
			}
		} else if (motCommande.equals("poser")) {
			if (unJoueur.rechercherObjetJoueur(secondeComande) == null) {
				System.out
						.println("cet Objet n'est pas parmis les objets que detient le joueur");
			} else {
				// ObjetZork oz =
				// unJoueur.rechercherObjetJoueur(secondeComande);
				// unJoueur.retirerObjetJoueur(secondeComande);
				pieceCourante.ajouterObjet(unJoueur
						.retirerObjetJoueur(secondeComande));
				pieceCourante.afficherObjet();
				System.out.println("vous avez poser l'objet");
			}

		} else if (motCommande.equals("catalogue")) {
			unJoueur.afficherObjetJoueur();
		} else if (motCommande.equals("retour")) {
			System.out.println(piecePrecedente.descriptionLongue());
			piecePrecedente.afficherObjet();
			pieceCourante = piecePrecedente;
		} else if (motCommande.equals("quitter")) {
			if (commande.aSecondMot()) {
				System.out.println("Quitter quoi ?");
			} else {
				return true;
			}
		}
		return false;
	}

	// implementations des commandes utilisateur:

	/**
	 * Affichage de l'aide. Affiche notament la liste des commandes utilisables.
	 */
	public void afficherAide() {
		System.out.println("Vous etes perdu. Vous etes seul. Vous errez");
		System.out.println("sur le campus de l'Université Paris 13.");
		System.out.println();
		System.out.println("Les commandes reconnues sont:");
		analyseurSyntaxique.afficherToutesLesCommandes();
	}

	/**
	 * Tente d'aller dans la direction spécifiée par la commande. Si la piece
	 * courante possède une sortie dans cette direction, la piece correspondant
	 * a cette sortie devient la piece courante, dans les autres cas affiche un
	 * message d'erreur.
	 * 
	 * @param commande
	 *            Commande dont le second mot spécifie la direction a suivre
	 */
	public void deplacerVersAutrePiece(Commande commande) {
		if (!commande.aSecondMot()) {
			// si la commande ne contient pas un second mot, nous ne
			// savons pas ou aller..
			System.out.println("Aller où ?");
			return;
		}

		String direction = commande.getSecondMot();

		// Tentative d'aller dans la direction indiquée.
		Piece pieceSuivante = pieceCourante.pieceSuivante(direction);

		if (pieceSuivante == null) {
			System.out.println("Il n'y a pas de porte dans cette direction!");
		} else {
			pieceCourante = pieceSuivante;
			System.out.println(pieceCourante.descriptionLongue());
			// System.out.println(pieceCourante.getLesObjets());
		}
	}

}