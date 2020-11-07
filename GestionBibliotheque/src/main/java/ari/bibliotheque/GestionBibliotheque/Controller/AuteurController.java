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

import ari.bibliotheque.GestionBibliotheque.Entity.Auteur;
import ari.bibliotheque.GestionBibliotheque.Entity.Livre;
import ari.bibliotheque.GestionBibliotheque.Service.IAuteurService;
import ari.bibliotheque.GestionBibliotheque.Service.ILivreService;

@Controller
@RequestMapping("auteurs")
public class AuteurController{
	
	@Autowired
	private IAuteurService AuteurService;
	@Autowired
	private ILivreService LivreService;
	
	/*
	 * Cette route donne accès à la page de gestion des auteurs
	 */
	@GetMapping("")
	public String menu() {
		return "auteur/menuAuteur";
	}
	
	/*
	 * Cette route permet de recuperer le formulaire d'ajout d'un auteur
	 */
	@GetMapping("/formulaire")
	public String formulaire(Auteur auteur, Model model) {
		List<Livre> livres = LivreService.getAllLivres();
		model.addAttribute("livres",livres);
		return "auteur/addAuteur";
	}
	
	/*
	 * Cette route permet de recuperer le formulaire de recherche d'un auteur
	 */
	@GetMapping("/search")
	public String search(Auteur auteur) {
		return "auteur/searchAuteur";
	}
	
	/*
	 * Cette route permet d'affiche la lsite des auteurs
	 */
	@GetMapping("/list")
	public String getAllAuteurs(Auteur auteur, Model model) {
		List<Auteur> list = AuteurService.getAllAuteurs();
		model.addAttribute("auteurs", list);
		return "auteur/afficheAuteurs";
	}
	
	/*
	 * Cette route permet d'acceder au formulaire de modification d'un auteur
	 */
	@GetMapping("/edit/{id}")
	public String getAuteurById(@PathVariable("id") Integer id, Model model) {
		List<Livre> livres = LivreService.getAllLivres();
		model.addAttribute("livres", livres);
	    Auteur auteur = AuteurService.getAuteurById(id);
		model.addAttribute("auteur", auteur);
		return "auteur/updateAuteur";
	}
	
	/*
	 * Cette route permet d'afficher plus en detail un auteur
	 */
	@GetMapping("/view/{id}")
	public String view(@PathVariable("id") Integer id, Model model) {
	    Auteur auteur = AuteurService.getAuteurById(id);
		model.addAttribute("auteur", auteur);
		return "auteur/afficheAuteur";
	}
	
	/*
	 * Cette route permet en etant appéllée par le formulaire d'ajout d'enregistrer un auteur
	 */
	@PostMapping("/add")
	public String addAuteur(@Valid Auteur auteur, BindingResult result, Model model) {
       if (result.hasErrors()) {
    	   List<Livre> livres = LivreService.getAllLivres();
   			model.addAttribute("livres", livres);
            return "auteur/addAuteur";
        }
                AuteurService.addAuteur(auteur);
                model.addAttribute("auteur", auteur);
                return "auteur/afficheAuteur";
	}
	
	/*
	 * Cette route permet de sauvegarder la version de l'auteur envoyer par le formulaire de modification
	 */
	@PostMapping("/update/{id}")
	public String updateUser(@PathVariable("id") long id, @Valid Auteur auteur,
	  BindingResult result, Model model) {
	    if (result.hasErrors()) {
	    	List<Livre> livres = LivreService.getAllLivres();
			model.addAttribute("livres", livres);
	        auteur.setId(id);
	        return "auteur/updateAuteur";
	    }

	    AuteurService.addAuteur(auteur);
        model.addAttribute("auteur", auteur);
	    return "auteur/afficheAuteur";
	}
	
	/*
	 * Cette route retourne l'object rechercher par l'auteur
	 */
	@PostMapping("/search")
	public String search(String nom, String prenom, Model model) {
	    List<Auteur> auteurs = AuteurService.getAuteursByNomAndPrenom(nom, prenom);
		if (auteurs.size() > 0) {
			model.addAttribute("auteurs", auteurs);
			return "auteur/afficheAuteurs";
		}
		else {
			model.addAttribute("message", "auteur non trouvé");
			return "auteur/searchAuteur";
		}
		
	}
	
	/*
	 * Cette route permet de recuperer le formulaire de modification
	 */
	@GetMapping("/update/{id}")
	public String updateAuteur(@PathVariable("id") long id, Model model) {
		List<Livre> livres = LivreService.getAllLivres();
		model.addAttribute("livres", livres);
		Auteur auteur = AuteurService.getAuteurById(id);
		model.addAttribute("auteur", auteur);
        return "auteur/updateAuteur";
	}
	
	/*
	 * Cette permet de supprimer un auteur
	 */
	@GetMapping("/delete/{id}")
	public String deleteAuteur(@PathVariable("id") long id, Model model) {
		Auteur auteur = AuteurService.getAuteurById(id);
		if(auteur == null)
			throw new IllegalArgumentException("Problème identifiant:" + id);
		
		AuteurService.deleteAuteur(auteur);
		List<Auteur> list = AuteurService.getAllAuteurs();
		model.addAttribute("auteurs", list);
		return "auteur/afficheAuteurs";
	}

}
