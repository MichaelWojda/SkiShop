package pl.san.mw.repositories;

import org.springframework.stereotype.Repository;
import pl.san.mw.model.Item;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
//autor Michał Wojda indeks:23512
@Repository
public class ItemRepository {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    public List<Item> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT e FROM Item e");
        return query.getResultList();

    }

    public Item getById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(Item.class, id);

    }


    public Item createItem(Item item) {
        System.out.println(item);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        item.setRent(false);
        tx.begin();
        entityManager.persist(item);
        entityManager.flush();
        tx.commit();
        entityManager.close();
        return item;
    }

    public void update(Item item) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        Item itemToBeUpdated = entityManager.find(Item.class, item.getId());
        Optional.ofNullable(itemToBeUpdated).ifPresent(i -> {
            try {
                tx.begin();
                itemToBeUpdated.setName(item.getName());
                itemToBeUpdated.setDescription(item.getDescription());
                itemToBeUpdated.setPrice(item.getPrice());
                itemToBeUpdated.setPersonWhoHasIt(item.getPersonWhoHasIt());
                itemToBeUpdated.setPrice(item.getPrice());
                itemToBeUpdated.setRent(item.isRent());
                tx.commit();
            }
            catch(Exception e){
                tx.rollback();
            }


        });
        entityManager.close();

    }

    public void delete(Item item) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        Item itemToBeDeleted = entityManager.find(Item.class, item.getId());
        Optional.of(itemToBeDeleted).ifPresent(i -> {
            try {
                tx.begin();
                entityManager.remove(itemToBeDeleted);
                tx.commit();
            }
            catch (Exception e){
                tx.rollback();
            }

        });
        entityManager.close();


    }


}
