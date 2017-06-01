/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demojpa;

import demojpa.dao.ContactDaoInDatabaseJDBC;
import demojpa.dao.ContactDaoInDatabaseJPA;
import demojpa.models.Contact;
import demojpa.dao.DaoGenerique;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ib
 */
public class DemoJPA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        DaoGenerique<Contact> dao = new ContactDaoInDatabase();
        Contact c = new Contact();
        */
                
        DaoGenerique<Contact> dao = new ContactDaoInDatabaseJPA();
            
        System.out.println("getAll : " + dao.getAll());
        System.out.println("readByName : " + dao.readByName("4"));
        
        System.out.println("readById : " + dao.readById(51));
        dao.delete(51);
        System.out.println("readByName : " + dao.readByName("4"));
        dao.update(new Contact(153, "mod!", "modified!!!", "modified!!!"));
        System.out.println("getAll : " + dao.getAll());        
        
        
        
    }
    
}
