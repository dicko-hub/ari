package ari.bibliotheque.GestionBibliotheque.Service;

import java.util.List;

import ari.bibliotheque.GestionBibliotheque.Entity.Livre;

public interface ILivreService {
	
	 List<Livre> getAllLivres();
     Livre getLivreById(long livreId);
     List<Livre> getLivreByTitre(String titre);
     boolean addLivre(Livre livre);
     void updateLivre(Livre livre);
     void deleteLivre(Livre livre);

}
