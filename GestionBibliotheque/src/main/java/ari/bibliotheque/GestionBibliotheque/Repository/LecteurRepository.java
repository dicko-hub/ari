package ari.bibliotheque.GestionBibliotheque.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ari.bibliotheque.GestionBibliotheque.Entity.Lecteur;


public interface LecteurRepository extends CrudRepository<Lecteur, Long> {
	
	List<Lecteur> findByNom(String nom);
	List<Lecteur> findByPrenom(String prenom);
	List<Lecteur> findByNomAndPrenom(String nom, String prenom);

    @Query("SELECT l FROM Lecteur l WHERE l.nom=:nom and l.prenom=:prenom")
    List<Lecteur> fetchLecteurs(@Param("nom") String nom, @Param("prenom") String prenom);
    
    @Override
    @Transactional(timeout = 8)
    Iterable<Lecteur> findAll();

}
