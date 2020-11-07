package ari.bibliotheque.GestionBibliotheque.Service;

import java.util.List;

import ari.bibliotheque.GestionBibliotheque.Entity.Lecteur;


public interface ILecteurService {

	 List<Lecteur> getAllLecteurs();
     Lecteur getLecteurById(long lecteurId);
     List<Lecteur> getLecteursByNomAndPrenom(String nom, String prenom);
     boolean addLecteur(Lecteur lecteur);
     void updateLecteur(Lecteur lecteur);
     void deleteLecteur(Lecteur lecteur);
}
