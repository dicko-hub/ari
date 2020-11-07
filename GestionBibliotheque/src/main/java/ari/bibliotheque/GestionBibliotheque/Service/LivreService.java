package ari.bibliotheque.GestionBibliotheque.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ari.bibliotheque.GestionBibliotheque.Entity.Livre;
import ari.bibliotheque.GestionBibliotheque.Repository.LivreRepository;

@Service
@Transactional
public class LivreService implements ILivreService{

	@Autowired
	private LivreRepository livreRepository;

	@Override
	public List<Livre> getAllLivres() {
		List<Livre> list = new ArrayList<>();
		livreRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Livre getLivreById(long livreId) {
		Livre obj = livreRepository.findById(livreId).get();
		return obj;
	}
	
	@Override
	public List<Livre> getLivreByTitre(String titre) {
		List<Livre> obj = livreRepository.findByTitre(titre);
		return obj;
	}

	@Override
	public boolean addLivre(Livre livre) {
		List<Livre> base = livreRepository.fetchLivres(livre.getTitre(), livre.getDescription());
        if (base.size() > 0) {
           return false;
        } else {
        livreRepository.save(livre);
        return true;
        }
	}

	@Override
	public void updateLivre(Livre livre) {
		livreRepository.save(livre);
		
	}

	@Override
	public void deleteLivre(Livre livre) {
		livreRepository.delete(livre);
	}

}

