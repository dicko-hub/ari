package ari.bibliotheque.GestionBibliotheque.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ari.bibliotheque.GestionBibliotheque.Entity.Auteur;


public interface AuteurRepository extends CrudRepository<Auteur, Long> {


	List<Auteur> findByNom(String nom);
	List<Auteur> findByPrenom(String prenom);
	List<Auteur> findByNomAndPrenom(String nom, String prenom);

    @Query("SELECT a FROM Auteur a WHERE a.nom=:nom and a.prenom=:prenom")
    List<Auteur> fetchAuteurs(@Param("nom") String nom, @Param("prenom") String prenom);
    
    @Override
    @Transactional(timeout = 8)
    Iterable<Auteur> findAll();
}
