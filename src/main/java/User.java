import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user",indexes = @Index(columnList ="username"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false)
    @ColumnTransformer(read = "AES_DECRYPT(UNHEX(password), 'SECRET KEY')", write = "HEX(AES_ENCRYPT(?, 'SECRET KEY'))")
    private String password;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private final List<Result> results = new ArrayList<>();

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void addResult(Result result){
        this.results.add(result);
    }
}
