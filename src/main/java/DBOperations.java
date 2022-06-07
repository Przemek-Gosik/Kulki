import javax.persistence.*;

public class DBOperations {
    EntityManagerFactory managerFactory;
    EntityManager manager;
    User user;
    public DBOperations() {

            managerFactory = Persistence.createEntityManagerFactory("Hibernate_JPA");
            manager = managerFactory.createEntityManager();

    }

    public static void addToDatabase(Object object ){
        EntityManagerFactory managerFactory= Persistence.createEntityManagerFactory("Hibernate_JPA");
        EntityManager manager=managerFactory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(object);
        manager.getTransaction().commit();
        manager.close();
        managerFactory.close();
    }
    public static void updateUser(User user,Result result){
        EntityManagerFactory managerFactory= Persistence.createEntityManagerFactory("Hibernate_JPA");
        EntityManager manager=managerFactory.createEntityManager();
        manager.getTransaction().begin();
        user.addResult(result);
        manager.merge(user);
        manager.getTransaction().commit();
        manager.close();
        managerFactory.close();
    }
    public static void  changeLogin(User user,String newLogin){
        EntityManagerFactory managerFactory= Persistence.createEntityManagerFactory("Hibernate_JPA");
        EntityManager manager=managerFactory.createEntityManager();
        manager.getTransaction().begin();
        user.setUsername(newLogin);
        manager.merge(user);
        manager.getTransaction().commit();
        manager.close();
        managerFactory.close();
    }
    public static void deleteAccount(Long userId){
        EntityManagerFactory managerFactory= Persistence.createEntityManagerFactory("Hibernate_JPA");
        EntityManager manager=managerFactory.createEntityManager();
        manager.getTransaction().begin();
        User user =manager.find(User.class,userId);
        manager.remove(user);
        manager.getTransaction().commit();
        manager.close();
    }
    public  boolean validateLogin(String userName, String password){
        manager.getTransaction().begin();
        Query query = manager.createQuery("SELECT u FROM User u where u.username=:userName AND u.password=:password");
        query.setParameter("userName",userName);
        query.setParameter("password",password);
            try {
                user = (User) query.getSingleResult();
            }catch(NoResultException e ){
            }
            manager.getTransaction().commit();
            manager.close();
                if(user == null){
                    return false;
                }else {
                    return true;
                }





    }
    public User getUser(){
        return this.user;
    }
}
