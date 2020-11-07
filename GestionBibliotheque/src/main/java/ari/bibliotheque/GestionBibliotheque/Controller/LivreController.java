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
@RequestMapping("livres")
public class LivreController{
	
	@Autowired
	private ILivreService LivreService;
	@Autowired
	private IAuteurService AuteurService;
	
	/*
	 * Cette route permet de recuperer la page de gestion des livres
	 */
	@GetMapping("")
	public String menu() {
		return "livre/menuLivre";
	}
	
	/*
	 * Cette route permet de recuperer le formulaire d'ajout d'un livre
	 */
	@GetMapping("/formulaire")
	public String formulaire(Livre livre, Model model) {
		List<Auteur> auteurs = AuteurService.getAllAuteurs();
		model.addAttribute("auteurs",auteurs);
		return "livre/addLivre";
	}
	
	/*
	 * Cette route permet de recuperer le formulaire de recherche d'un livre
	 */
	@GetMapping("/search")
	public String search(Livre livre) {
		return "livre/searchLivre";
	}
	
	/*
	 * Cette route recupere la liste des livres
	 */
	@GetMapping("/list")
	public String getAllLivres(Livre livre, Model model) {
		List<Livre> list = LivreService.getAllLivres();
		model.addAttribute("livres", list);
		return "livre/afficheLivres";
	}
	
	/*
	 * Cette route recupere le formulaire de modification d'un livre
	 */
	@GetMapping("/edit/{id}")
	public String getLivreById(@PathVariable("id") Integer id, Model model) {
		List<Auteur> auteurs = AuteurService.getAllAuteurs();
		model.addAttribute("auteurs",auteurs);
	    Livre livre = LivreService.getLivreById(id);
		model.addAttribute("livre", livre);
		return "livre/updateLivre";
	}
	
	/*
	 * Cette route recupere la page de detail d'un livre
	 */
	@GetMapping("/view/{id}")
	public String view(@PathVariable("id") Integer id, Model model) {
	    Livre livre = LivreService.getLivreById(id);
		model.addAttribute("livre", livre);
		return "livre/afficheLivre";
	}
	
	/*
	 * Cette route recupere le resultat de la recherche envoyé par le formulaire de recherche
	 */
	@PostMapping("/search")
	public String search(String titre, Model model) {
	    List<Livre> livres = LivreService.getLivreByTitre(titre);
		if (livres.size() > 0) {
			model.addAttribute("livres", livres);
			return "livre/afficheLivres";
		}
		else {
			model.addAttribute("message", "livre non trouvé");
			return "livre/searchLivre";
		}
		
	}
	
	/*
	 * Cette route permet d'enregistrer le livre fournit 
	 */
	@PostMapping("/add")
	public String addLivre(@Valid Livre livre, BindingResult result, Model model) {
       if (result.hasErrors()) {
    	   List<Auteur> auteurs = AuteurService.getAllAuteurs();
   		   model.addAttribute("auteurs",auteurs);
            return "livre/addLivre";
        }
                LivreService.addLivre(livre);
                model.addAttribute("livre", livre);
                return "livre/afficheLivre";
	}
	
	/*
	 * Cette route enregistre la version modifié du livre envoyé
	 */
	@PostMapping("/update/{id}")
	public String updateUser(@PathVariable("id") long id, @Valid Livre livre,
	  BindingResult result, Model model) {
	    if (result.hasErrors()) {
	    	List<Auteur> auteurs = AuteurService.getAllAuteurs();
	   		model.addAttribute("auteurs",auteurs);
	        livre.setId(id);
	        return "livre/updateLivre";
	    }

	    LivreService.addLivre(livre);
        model.addAttribute("livre", livre);
	    return "livre/afficheLivre";
	}
	
	/*
	 * Cette route recupere le formulaire de modification d'un livre
	 */
	@GetMapping("/update/{id}")
	public String updateLivre(@PathVariable("id") long id, Model model) {
		List<Auteur> auteurs = AuteurService.getAllAuteurs();
		model.addAttribute("auteurs",auteurs);
		Livre livre = LivreService.getLivreById(id);
		model.addAttribute("livre", livre);
        return "livre/updateLivre";
	}
	
	/*
	 * Cette route permet de supprimer un livre
	 */
	@GetMapping("/delete/{id}")
	public String deleteLivre(@PathVariable("id") long id, Model model) {
		Livre livre = LivreService.getLivreById(id);
		if(livre == null)
			throw new IllegalArgumentException("Problème identifiant:" + id);
		
		LivreService.deleteLivre(livre);
		List<Livre> list = LivreService.getAllLivres();
		model.addAttribute("livres", list);
		return "livre/afficheLivres";
	}

}
