package ari.bibliotheque.GestionBibliotheque.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ari.bibliotheque.GestionBibliotheque.Entity.Livre;

public interface LivreRepository extends CrudRepository<Livre, Long> {

  List<Livre> findByTitre(String titre);
  List<Livre> findByDescription(String description);

  @Query("SELECT l FROM Livre l WHERE l.titre=:titre and l.description=:description")
  List<Livre> fetchLivres(@Param("titre") String titre, @Param("description") String description);
  
  @Override
  @Transactional(timeout = 8)
  Iterable<Livre> findAll();
 
}