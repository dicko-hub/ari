package ari.bibliotheque.GestionBibliotheque.Service;

import java.util.List;

import ari.bibliotheque.GestionBibliotheque.Entity.Auteur;

public interface IAuteurService {
	
	 List<Auteur> getAllAuteurs();
	 List<Auteur> getAuteursByNomAndPrenom(String nom, String prenom);
     Auteur getAuteurById(long auteurId);
     boolean addAuteur(Auteur auteur);
     void updateAuteur(Auteur auteur);
     void deleteAuteur(Auteur auteur);

}
