
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("Hibernate_JPA");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> managerFactory.close()));
       JFrame frame = new JFrame("Witaj w grze !");
       frame.setLocationRelativeTo(null);
       new Login(frame,managerFactory);



    }
}
