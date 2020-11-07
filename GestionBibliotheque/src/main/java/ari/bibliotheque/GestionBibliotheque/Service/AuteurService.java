package ari.bibliotheque.GestionBibliotheque.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ari.bibliotheque.GestionBibliotheque.Entity.Auteur;
import ari.bibliotheque.GestionBibliotheque.Repository.AuteurRepository;

@Service
@Transactional
public class AuteurService implements IAuteurService{

	@Autowired
	private AuteurRepository auteurRepository;

	@Override
	public List<Auteur> getAllAuteurs() {
		List<Auteur> list = new ArrayList<>();
		auteurRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Auteur getAuteurById(long auteurId) {
		Auteur obj = auteurRepository.findById(auteurId).get();
		return obj;
	}
	
	@Override
	public List<Auteur> getAuteursByNomAndPrenom(String nom, String prenom) {
		List<Auteur> obj = auteurRepository.findByNomAndPrenom(nom, prenom);
		return obj;
	}

	@Override
	public boolean addAuteur(Auteur auteur) {
		List<Auteur> base = auteurRepository.fetchAuteurs(auteur.getNom(), auteur.getPrenom());
        if (base.size() > 0) {
           return false;
        } else {
        auteurRepository.save(auteur);
        return true;
        }
	}

	@Override
	public void updateAuteur(Auteur auteur) {
		auteurRepository.save(auteur);
		
	}

	@Override
	public void deleteAuteur(Auteur auteur) {
		auteurRepository.delete(auteur);
	}

}
