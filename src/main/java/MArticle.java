import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MArticle {
    private Integer id;
    private StringProperty description;
    private IntegerProperty count;

    public MArticle(){
        this(null, null, 0);
    }

    public MArticle(Integer id, String description, int count) {
        this.id = id;
        this.description = new SimpleStringProperty(description);
        this.count = new SimpleIntegerProperty(count);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public Integer getCount() {
        return count.get();
    }

    public void setCount(Integer count) {
        this.count.set(count);
    }

    public IntegerProperty countProperty() {
        return count;
    }

}

