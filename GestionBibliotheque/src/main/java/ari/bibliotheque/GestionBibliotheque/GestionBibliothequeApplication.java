package ari.bibliotheque.GestionBibliotheque;

import java.util.Locale;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import ari.bibliotheque.GestionBibliotheque.Entity.Auteur;
import ari.bibliotheque.GestionBibliotheque.Entity.Lecteur;
import ari.bibliotheque.GestionBibliotheque.Entity.Livre;
import ari.bibliotheque.GestionBibliotheque.Service.IAuteurService;
import ari.bibliotheque.GestionBibliotheque.Service.ILecteurService;
import ari.bibliotheque.GestionBibliotheque.Service.ILivreService;

@SpringBootApplication
public class GestionBibliothequeApplication implements WebMvcConfigurer {
	

	public static void main(String[] args) {
		SpringApplication.run(GestionBibliothequeApplication.class, args);
	}
	
	@Bean
	  public LocaleResolver localeResolver() {
		  SessionLocaleResolver slr = new SessionLocaleResolver();
		  slr.setDefaultLocale(Locale.US);
		  return slr;
	  }
	  
	  @Bean
	  public LocaleChangeInterceptor localeChangeInterceptor() {
		  LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		  lci.setParamName("lang");
		  return lci;
	  }
	  
	  @Override
	  public void addInterceptors(InterceptorRegistry registry) {
	      registry.addInterceptor(localeChangeInterceptor());
	  }
		
	
	 @Bean
	  public CommandLineRunner demo(ILecteurService lecteurService, IAuteurService auteurService, ILivreService livreService) {
	    return (args) -> {
	    	//Ajout de lecteurs
			lecteurService.addLecteur(new Lecteur("lecteur 1 nom","lecteur 1 prenom",15));
			lecteurService.addLecteur(new Lecteur("lecteur 2 nom","lecteur 2 prenom",18));
			lecteurService.addLecteur(new Lecteur("lecteur 3 nom","lecteur 3 prenom",35));
			lecteurService.addLecteur(new Lecteur("lecteur 4 nom","lecteur 4 prenom",27));
			lecteurService.addLecteur(new Lecteur("lecteur 5 nom","lecteur 5 prenom",42));
			
			System.out.println("lecteurs ajouté : ok");
			
			//Ajout d'auteurs
			auteurService.addAuteur(new Auteur("auteur 1 nom","auteur 1 prenom"));
			auteurService.addAuteur(new Auteur("auteur 2 nom","auteur 2 prenom"));
			auteurService.addAuteur(new Auteur("auteur 3 nom","auteur 3 prenom"));
			auteurService.addAuteur(new Auteur("auteur 4 nom","auteur 4 prenom"));
			auteurService.addAuteur(new Auteur("auteur 5 nom","auteur 5 prenom"));
			
			System.out.println("auteurs ajouté : ok");
			
			//AJout de livres
			livreService.addLivre(new Livre("livre 1 titre","livre 1 description"));
			livreService.addLivre(new Livre("livre 2 titre","livre 2 description"));
			livreService.addLivre(new Livre("livre 3 titre","livre 3 description"));
			livreService.addLivre(new Livre("livre 4 titre","livre 4 description"));
			livreService.addLivre(new Livre("livre 5 titre","livre 5 description"));
			
			System.out.println("livre ajouté : ok");
	    };
	  }


}
