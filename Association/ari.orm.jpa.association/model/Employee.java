package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;



@Entity
public class Employee implements Serializable {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.TABLE)
	    private String id;
	    private String nom;
	    private String prenom;
	    private int age; 
	    private Adresse adresse;
	    
	    @ManyToMany
	    @JoinTable( name="EMP_PROJ", joinColumns=@JoinColumn(name="EMPLOYEE_ID", referencedColumnName = "id"),
	    			inverseJoinColumns=@JoinColumn(name="PROJET_ID",referencedColumnName = "id") )
	    private final List<Projet> projets = new ArrayList<Projet>();
		
		public List<Projet> getProjets() {
	        return projets;
	    }
	    
	    @OneToOne(mappedBy="Employee")
	    public Adresse getAdresse() {
			return adresse;
		}

		public void setAdresse(Adresse adresse) {
			this.adresse = adresse;
		}
		
		private Entreprise entreprise;
	    
	    public String getId() {
			return id;
		}

		public void setId(String id) {
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
		
		@ManyToOne
		public Entreprise getEntreprise() {
			return entreprise;
		}

		public void setEntreprise(Entreprise entreprise) {
			this.entreprise = entreprise;
		}

		


}
