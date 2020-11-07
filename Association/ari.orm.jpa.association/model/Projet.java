package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class Projet  {
	@Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
	private String nom;
	private String description;
	
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@ManyToMany
	@JoinTable( name="EMP_PROJ", joinColumns= @JoinColumn(name="EMPLOYEE_ID", referencedColumnName = "id"),
							inverseJoinColumns=@JoinColumn(name="PROJECT_ID", referencedColumnName = "id"))
    private final List<Employee> membres = new ArrayList<Employee>();
	
	public List<Employee> getMembers() {
        return membres;
    }
	

}
