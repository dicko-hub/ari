package ari.bibliotheque.GestionBibliotheque.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "lecteurs")
public class Lecteur {
	@Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
	
	@NotNull
	@Size(min=2, max=30)
    private String nom;
	
	@NotNull
	@Size(min=2, max=30)
    private String prenom;
	
	@NotNull
	@Min(13)
	private int age ;
	   
    
	public Lecteur() {
		
	}
	
	public Lecteur(@NotNull @Size(min = 2, max = 30) String nom, @NotNull @Size(min = 2, max = 30) String prenom, @NotNull
			@Min(13) int age) {
		
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
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
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Lecteur [nom=" + nom + ", prenom=" + prenom + ", age=" + age + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lecteur other = (Lecteur) obj;
		if (age != other.age)
			return false;
		if (id != other.id)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		return true;
	}

	
}
