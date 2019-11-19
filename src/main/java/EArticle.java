import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ARTICLES", schema = "woXJ3nE7ii", catalog = "")
public class EArticle {
    private Integer id;
    private String description;
    private Integer count;

    public EArticle(String description, int count) {
        setDescription(description);
        setCount(count);
    }
    public EArticle(int ID, String description, int count) {
        setId(ID);
        setDescription(description);
        setCount(count);
    }

    public EArticle() {

    }

    @Id
    @GeneratedValue(generator= "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "count")
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EArticle that = (EArticle) o;
        return id == that.id &&
                count == that.count &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, count);
    }
}
