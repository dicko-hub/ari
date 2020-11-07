package tests;

import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.jupiter.api.Test;

import model.Adresse;
import model.Employee;
import model.Entreprise;


class JpaTest1_1 {


    private static final String PERSISTENCE_UNIT_NAME = "structure";
    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    
    @Test
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
    
    @Test
    public void creerEmployee() {

        EntityManager em = factory.createEntityManager();
        
     // insertion d'un employee dans la premiere entreprise
        
        Query q = em.createQuery("select e from Entreprise e");
        assertTrue(q.getResultList().size() > 0);
        
        Entreprise entreprise = new Entreprise();
        List<Entreprise> EntrepriseList = q.getResultList();
        entreprise = EntrepriseList.get(0);
        Employee employee = new Employee();
        employee.setNom("DICKO");
        employee.setPrenom("Seydou");
        employee.setAge(23);
        employee.setEntreprise(entreprise);
        
        Adresse adresse = new Adresse();
        adresse.setNom("Rue de Ticleni");
        adresse.setNumero(51);
        adresse.setVille("Villeneuve d'ascq");
        adresse.setEmployee(employee);
               

        em.getTransaction().begin();
        em.persist(adresse);
        employee.setAdresse(adresse);
        em.persist(employee);
        entreprise.getMembers().add(employee);
        em.persist(entreprise);
        em.getTransaction().commit();
        em.close();
    }

    
    @Test
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
    
    
}
