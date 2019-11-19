import java.util.List;

public interface ArticleDAO {
    public Integer addArticle(EArticle article);

    public List<EArticle> getArticles();

    public void updateArticle(EArticle article);

    public void deleteArticle(Integer id);
}
