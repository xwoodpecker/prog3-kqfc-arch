import java.util.List;

public class ArticleServiceImpl implements ArticleService{
    private ArticleDAO articlesDAO = new ArticleDAOImpl();

    @Override
    public Integer addArticle(EArticle article){
        return articlesDAO.addArticle(article);
    }

    public List<EArticle> getArticles(){
        return articlesDAO.getArticles();
    }

    public void updateArticle(EArticle article){
        articlesDAO.updateArticle(article);
    }
    public void deleteArticle(Integer id){
        articlesDAO.deleteArticle(id);
    }
}
