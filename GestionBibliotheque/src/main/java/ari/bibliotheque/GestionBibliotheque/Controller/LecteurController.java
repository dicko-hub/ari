package ari.bibliotheque.GestionBibliotheque.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ari.bibliotheque.GestionBibliotheque.Entity.Lecteur;
import ari.bibliotheque.GestionBibliotheque.Service.ILecteurService;

@Controller
@RequestMapping("lecteurs")
public class LecteurController{
	
	@Autowired
	private ILecteurService LecteurService;
	
	/*
	 * Cette route permet de recuperer la page de gestion des lecteurs
	 */
	@GetMapping("")
	public String menu() {
		return "lecteur/menuLecteur";
	}
	
	/*
	 * Cette route permet de recuperer le formulaire d'ajout d'un lecteur
	 */
	@GetMapping("/formulaire")
	public String formulaire(Lecteur lecteur) {
		return "lecteur/addLecteur";
	}
	
	/*
	 * Cette route permet de recuperer le formulaire de recherche d'un lecteur
	 */
	@GetMapping("/search")
	public String search(Lecteur lecteur) {
		return "lecteur/searchLecteur";
	}
	
	/*
	 * Cette route retourne la liste de tout les lecteurs
	 */
	@GetMapping("/list")
	public String getAllLecteurs(Lecteur lecteur, Model model) {
		List<Lecteur> list = LecteurService.getAllLecteurs();
		model.addAttribute("lecteurs", list);
		return "lecteur/afficheLecteurs";
	}
	
	/*
	 * Cette route permet de retrourner le formulaire de modification d'un lecteur
	 */
	@GetMapping("/edit/{id}")
	public String getLecteurById(@PathVariable("id") Integer id, Model model) {
	    Lecteur Lecteur = LecteurService.getLecteurById(id);
		model.addAttribute("lecteur", Lecteur);
		return "lecteur/updateLecteur";
	}
	
	/*
	 * Cette route permet de retourner les details d'un lecteur
	 */
	@GetMapping("/view/{id}")
	public String view(@PathVariable("id") Integer id, Model model) {
	    Lecteur lecteur = LecteurService.getLecteurById(id);
		model.addAttribute("lecteur", lecteur);
		return "lecteur/afficheLecteur";
	}
	
	/*
	 * Cette permet de retourner le resulat de la recherche d'un lecteur type
	 */
	@PostMapping("/search")
	public String search(String nom, String prenom, Model model) {
	    List<Lecteur> lecteurs = LecteurService.getLecteursByNomAndPrenom(nom, prenom);
		if (lecteurs.size() > 0) {
			model.addAttribute("lecteurs", lecteurs);
			return "lecteur/afficheLecteurs";
		}
		else {
			model.addAttribute("message", "lecteur non trouvé");
			return "lecteur/searchLecteur";
		}
		
	}
	
	/*
	 * Cette route permet de sauvegarder un lecteur envoyer par le formulaire d'ajout
	 */
	@PostMapping("/add")
	public String addLecteur(@Valid Lecteur lecteur, BindingResult result, Model model) {
       if (result.hasErrors()) {
            return "lecteur/addLecteur";
        }
                LecteurService.addLecteur(lecteur);
                model.addAttribute("lecteur", lecteur);
                return "lecteur/afficheLecteur";
	}
	
	/*
	 * Cette route permet de sauvegarder la modification envoyer par le formulaire de modification
	 */
	@PostMapping("/update/{id}")
	public String updateLecteur(@PathVariable("id") long id, @Valid Lecteur lecteur,
	  BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        lecteur.setId(id);
	        return "lecteur/updateLecteur";
	    }

	    LecteurService.addLecteur(lecteur);
        model.addAttribute("lecteur", lecteur);
	    return "lecteur/afficheLecteur";
	}
	
	/*
	 * Cette route permet de recuperer le formulaire de modification d'un lecteur
	 */
	@GetMapping("/update/{id}")
	public String updateLecteur(@PathVariable("id") long id, Model model) {
	
		Lecteur lecteur = LecteurService.getLecteurById(id);
		model.addAttribute("lecteur", lecteur);
        return "lecteur/updateLecteur";
	}
	
	/*
	 * Cette route permet de supprimer un lecteur
	 */
	@GetMapping("/delete/{id}")
	public String deleteLecteur(@PathVariable("id") long id, Model model) {
		Lecteur lecteur = LecteurService.getLecteurById(id);
		if(lecteur == null)
			throw new IllegalArgumentException("Problème identifiant:" + id);
		
		LecteurService.deleteLecteur(lecteur);
		List<Lecteur> list = LecteurService.getAllLecteurs();
		model.addAttribute("lecteurs", list);
		return "lecteur/afficheLecteurs";
	}

}
