package ari.bibliotheque.GestionBibliotheque.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ari.bibliotheque.GestionBibliotheque.Entity.Lecteur;
import ari.bibliotheque.GestionBibliotheque.Repository.LecteurRepository;


@Service
@Transactional
public class LecteurService implements ILecteurService {


	@Autowired
	private LecteurRepository lecteurRepository;

	@Override
	public List<Lecteur> getAllLecteurs() {
		List<Lecteur> list = new ArrayList<>();
		lecteurRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Lecteur getLecteurById(long lecteurId) {
		Lecteur obj = lecteurRepository.findById(lecteurId).get();
		return obj;
	}
	
	@Override
	public List<Lecteur> getLecteursByNomAndPrenom(String nom, String prenom) {
		List<Lecteur> obj = lecteurRepository.findByNomAndPrenom(nom, prenom);
		return obj;
	}

	@Override
	public boolean addLecteur(Lecteur lecteur) {
		List<Lecteur> base = lecteurRepository.fetchLecteurs(lecteur.getNom(), lecteur.getPrenom());
        if (base.size() > 0) {
           return false;
        } else {
        lecteurRepository.save(lecteur);
        return true;
        }
	}

	@Override
	public void updateLecteur(Lecteur lecteur) {
		lecteurRepository.save(lecteur);
		
	}

	@Override
	public void deleteLecteur(Lecteur lecteur) {
		lecteurRepository.delete(lecteur);
	}

}
