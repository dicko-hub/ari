package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Entreprise {
	@Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
	private String nom;
	

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@OneToMany(mappedBy = "entreprise")
    private final List<Employee> membres = new ArrayList<Employee>();
	
	public List<Employee> getMembers() {
        return membres;
    }

}
