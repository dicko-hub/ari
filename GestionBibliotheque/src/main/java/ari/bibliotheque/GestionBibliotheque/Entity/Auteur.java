package ari.bibliotheque.GestionBibliotheque.Entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "auteurs")
public class Auteur {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@NotNull
	@Size(min=2, max=30)
    private String nom;
	
	@NotNull
	@Size(min=2, max=30)
	private String prenom;
	
	
	@ManyToMany(mappedBy = "auteurs", fetch = FetchType.LAZY)
    private Set<Livre> livres = new HashSet<>();
	
	public Auteur() {
		
	}

	public Auteur(@NotNull @Size(min = 2, max = 30) String nom, @NotNull @Size(min = 2, max = 30) String prenom) {
		
		this.nom = nom;
		this.prenom = prenom;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	

	public Set<Livre> getLivres() {
		return livres;
	}

	public void setLivres(Set<Livre> livres) {
		this.livres = livres;
	}

	@Override
	public String toString() {
		return "Auteur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + "]";
	}
	
}
