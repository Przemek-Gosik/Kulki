import javax.persistence.*;

public class DBOperations {
    private static DBOperations dbOperations;

    public EntityManagerFactory getManagerFactory() {
        return managerFactory;
    }

    public void setManagerFactory(EntityManagerFactory managerFactory) {
        this.managerFactory = managerFactory;
    }

    private EntityManagerFactory managerFactory;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;


    private DBOperations(EntityManagerFactory managerFactory1) {
        this.managerFactory = managerFactory1;
    }
    public static DBOperations getInstance(EntityManagerFactory managerFactory1){
        if(dbOperations == null){
            dbOperations = new DBOperations(managerFactory1);
        }
        return dbOperations;
    }

    public void addToDatabase(Object object ){
        EntityManager manager=managerFactory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(object);
        manager.getTransaction().commit();
        manager.close();
    }
    public  void updateUser(User user,Result result){
        EntityManager manager=managerFactory.createEntityManager();
        manager.getTransaction().begin();
        user.addResult(result);
        manager.merge(user);
        manager.getTransaction().commit();
        manager.close();
    }
    public void  changeLogin(User user,String newLogin){
        EntityManager manager=managerFactory.createEntityManager();
        manager.getTransaction().begin();
        user.setUsername(newLogin);
        manager.merge(user);
        manager.getTransaction().commit();
        manager.close();
        managerFactory.close();
    }
    public void deleteAccount(Long userId){
        EntityManager manager=managerFactory.createEntityManager();
        manager.getTransaction().begin();
        User user =manager.find(User.class,userId);
        manager.remove(user);
        manager.getTransaction().commit();
        manager.close();
    }
    public  boolean validateLogin(String userName, String password){
        EntityManager manager = managerFactory.createEntityManager();
        manager.getTransaction().begin();
        Query query = manager.createQuery("SELECT u FROM User u where u.username=:userName AND u.password=:password");
        query.setParameter("userName",userName);
        query.setParameter("password",password);

            try {
                this.user=(User) query.getSingleResult();
                manager.getTransaction().commit();
                manager.close();
                return true;
            }catch(NoResultException e ){
               return false;
            }





    }

}
