package lesson5;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.function.Consumer;
import java.util.function.Function;

public class StudentDao {
    private final EntityManagerFactory factory;

    public StudentDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public Student studentFindById(Long id) {
        try {
            return executeForEntityManager(manager ->
                    (manager.createQuery("select s from Student s where s.id = :id", Student.class)
                            .setParameter("id", id)).getSingleResult());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void deleteById(Long id) {
        executeInTransaction(manager -> manager.createQuery("delete from Student s where s.id = :id")
                .setParameter("id", id).executeUpdate());
    }

    private void update(Student student) {
        executeInTransaction(manager -> manager.merge(student));
    }

    private void insert(Student student) {
        executeInTransaction(manager -> manager.persist(student));
    }

    public void save(Student student) {
        if (student.getId() == null) {
            insert(student);
        } else {
            update(student);
        }
    }

    private <R> R executeForEntityManager(Function<EntityManager, R> function) {
        EntityManager manager = factory.createEntityManager();
        try {
            return function.apply(manager);
        } finally {
            manager.close();
        }
    }

    private void executeInTransaction(Consumer<EntityManager> managerConsumer) {
        EntityManager manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            managerConsumer.accept(manager);
            manager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            manager.getTransaction().rollback();
        } finally {
            manager.close();
        }
    }
}
