package demojpa.dao;

import demojpa.models.Contact;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.eclipse.persistence.jpa.jpql.parser.DeleteStatement;

public class ContactDaoInDatabaseJPA implements DaoGenerique<Contact> {
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("ContactsPU");
    
    @Override
    public void create(Contact c) {
        // EntityManager est une session
        EntityManager em = factory.createEntityManager();
        // début de la transaction
        em.getTransaction().begin();
               
        em.persist(c);
                       
        // fin de la transaction
        em.getTransaction().commit();
        // on ferme la session
        em.close();
    }

    @Override
    public List<Contact> readByName(String string) {
        // EntityManager est une session
        EntityManager em = factory.createEntityManager();
        // début de la transaction
        em.getTransaction().begin();
        
        Query query = em.createQuery("SELECT c FROM Contact c WHERE (c.nom LIKE (:string) OR (c.prenom LIKE :string) OR (c.numero LIKE :string))");
        query.setParameter("string", "%" + string + "%");
        List<Contact> contacts = (List<Contact>)query.getResultList();
        
        // fin de la transaction
        em.getTransaction().commit();
        // on ferme la session
        em.close();
        
        return contacts;
    }

    @Override
    public Contact readById(int i) {
        // EntityManager est une session
        EntityManager em = factory.createEntityManager();
        // début de la transaction
        em.getTransaction().begin();
        
        Query query = em.createQuery("SELECT c FROM Contact c WHERE (c.contactId = :id)");
        query.setParameter("id", i);
        Contact contact = (Contact)query.getSingleResult();
        
        // fin de la transaction
        em.getTransaction().commit();
        // on ferme la session
        em.close();
        
        return contact;

    }

    @Override
    public void update(Contact c) {
           
        // EntityManager est une session
        EntityManager em = factory.createEntityManager();
        // début de la transaction
        em.getTransaction().begin();
        /*
        Contact c1 = em.find(Contact.class, c.getContactId());
        c1.setNom(c.getNom());
        c1.setPrenom(c.getPrenom());
        c1.setNumero(c.getNumero());
        
                OU MEME MIEUX :
        */        
        em.refresh(em.find(Contact.class, c.getContactId()));
        
        // fin de la transaction
        em.getTransaction().commit();
        // on ferme la session
        em.close();
    }

    @Override
    public void delete(int id) {        
        // EntityManager est une session
        EntityManager em = factory.createEntityManager();
        // début de la transaction
        em.getTransaction().begin();
        
        em.remove(em.find(Contact.class, id));
                       
        // fin de la transaction
        em.getTransaction().commit();
        // on ferme la session
        em.close();

    }

    @Override
    public List<Contact> getAll() {
        
        // EntityManager est une session
        EntityManager em = factory.createEntityManager();
        // début de la transaction
        em.getTransaction().begin();
        
        Query query = em.createQuery("SELECT c FROM Contact c");
        List<Contact> contacts = (List<Contact>)query.getResultList();
        
                       
        // fin de la transaction
        em.getTransaction().commit();
        // on ferme la session
        em.close();
        
        return contacts;
    
}

    @Override
    public int getId(Contact t) {
        int id = -1;
        // EntityManager est une session
        EntityManager em = factory.createEntityManager();
        // début de la transaction
        em.getTransaction().begin();
        
        Query query = em.createQuery("SELECT c.contactId FROM Contact c" +
                "WHERE (c.nom = :nom) AND (c.prenom = :prenom) AND (c.numero = :numero)");
        id = (int)query.getSingleResult();
        
        // fin de la transaction
        em.getTransaction().commit();
        // on ferme la session
        em.close();
        
        return id;
}


    
    
}