package tests;

import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import model.Adresse;
import model.Employee;
import model.Entreprise;
import model.Projet;

class JpaTestN_M {


    private static final String PERSISTENCE_UNIT_NAME = "structure";
    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    
    @Before
    public void creerEntreprise()  throws Exception {
        EntityManager em = factory.createEntityManager();

        // Lecture des lignes 
        Query q = em.createQuery("select e from Entreprise e");
        

        // verifier la presence de données
        boolean createNewEntries = (q.getResultList().size() == 0);

        
        // Sinon creer des employees
        if (createNewEntries) {
            assertTrue(q.getResultList().size() == 0);
            Entreprise entreprise = new Entreprise();
            entreprise.setNom("Smart camps");
            
            // Debut de transaction
            em.getTransaction().begin();
            
            em.persist(entreprise);
            
            // Commit des données
            em.getTransaction().commit();
            
        }

        
        // fermeture de la connection
        em.close();

    }
    
    @Before
    public void creerEmployee() {

        EntityManager em = factory.createEntityManager();
        
     // insertion d'un employee dans la premiere entreprise
        Query q = em.createQuery("select e from Entreprise e");
        assertTrue(q.getResultList().size() > 0);
        
        Entreprise entreprise = new Entreprise();
        List<Entreprise> EntrepriseList = q.getResultList();
        entreprise = EntrepriseList.get(0);
        for(int i =0; i<4;i++) {
        Employee employee = new Employee();
        employee.setNom("Nom "+i);
        employee.setPrenom("Prenom "+i);
        employee.setAge(23);
        
        Adresse adresse = new Adresse();
        adresse.setNom("Nom de rue l'employee "+i);
        adresse.setNumero(i);
        adresse.setVille("Ville de l'employee "+i);
               

        em.getTransaction().begin();
        employee.setAdresse(adresse);
        em.persist(employee);
        entreprise.getMembers().add(employee);
        em.persist(entreprise);
        em.getTransaction().commit();
        }
        em.close();
    }

    
    @Before
    public void listeEmployee() {

        EntityManager em = factory.createEntityManager();
        
        Query q = em.createQuery("select e from Entreprise e");
        assertTrue(q.getResultList().size() > 0);
        
        
        List<Entreprise> EntrepriseList = q.getResultList();        
        Entreprise ent = new Entreprise();
        ent = EntrepriseList.get(0);
        List<Employee> membres = ent.getMembers();
        assertTrue(membres.size() > 0);
        em.close();
    }
    
    @Test
    public void creerProjet() {

        EntityManager em = factory.createEntityManager();
        
        // Creation de projet et affectation de membre
        Projet pro1 = new Projet();
        pro1.setNom("Le projet pro1");
        pro1.setDescription("Cet projet reunit les employees e1,e2 et e3");
        
        // Creation de projet et affectation de membre
        Projet pro2 = new Projet();
        pro2.setNom("Le projet pro2");
        pro2.setDescription("Cet projet reunit les employees e2,e3 et e4");
        
        Query q = em.createQuery("select e from Entreprise e");
        assertTrue(q.getResultList().size() > 0);
        
        List<Entreprise> EntrepriseList = q.getResultList();
        for (Entreprise ent : EntrepriseList) {
        	 List<Employee> membres = new ArrayList<Employee>();
        	 membres = ent.getMembers();
        	 for(int i =0; i<3;i++) {
             	Employee emp1 = membres.get(i);
     	        //Affecter les employees 1,2,3 au projet p1
     	        em.getTransaction().begin();
     	        pro1.getMembers().add(emp1);
     	        em.persist(pro1);
     	        em.getTransaction().commit();
             }
             
             for(int i =1; i<4;i++) {
             	Employee emp2 = membres.get(i);
 	            //Affecter les employees 1,2,3 au projet p1
 	            em.getTransaction().begin();
 	            pro2.getMembers().add(emp2);
 	            em.persist(pro2);
 	            em.getTransaction().commit();
             }
        }
       
                      
            
	        em.getTransaction().begin();
	        em.persist(pro1);
	        em.persist(pro2);
	        em.getTransaction().commit();
	        em.close();
        }
           


    
    @Test
    public void listeProjet() {

        EntityManager em = factory.createEntityManager();
        
        Query q = em.createQuery("select p from Projet p");
        assertTrue(q.getResultList().size() > 0);
        
        List<Projet> ProjetList = q.getResultList();
        for (Projet pro : ProjetList) {
            List<Employee> membres = new ArrayList<Employee>();
            membres = pro.getMembers();
            //assertTrue(membres.size() == 3);
            System.out.println(membres.size()+" "+pro.getNom());
        }
        em.close();
    }
    
    
}
