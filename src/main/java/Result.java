import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="result",indexes = @Index(columnList ="name" ))
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    public float getTime() {
        return time;
    }

    @Column(nullable = false)
    private float time;
    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private int score;

    public int getMax() {
        return max;
    }

    @Column(nullable = false)
    private int max;

    @Column(nullable = false)
    private Level level;

    public Result(){}
    public Result(int numer,String date, int score,int max, Level level,float time) {
        name="Proba_"+Integer.toString(numer+1);
        this.date = date;
        this.score = score;
        this.max = max;
        this.level = level;
        this.time=time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
