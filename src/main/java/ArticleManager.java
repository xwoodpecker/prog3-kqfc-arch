import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ArticleManager {

    private ArticleService articleService = new ArticleServiceImpl();
    private ArticleMapper articleMapper = new ArticleMapper();
    private ObservableList<EArticle> articleList = FXCollections.observableArrayList();
    private ObservableList<MArticle> articleModelList = FXCollections.observableArrayList();

    public Integer addArticle(EArticle article){
        return articleService.addArticle(article);
    }

    public ObservableList<EArticle> getArticles(){
        if(!articleModelList.isEmpty())
            articleModelList.clear();
        articleList = FXCollections.observableList((List<EArticle>) articleService.getArticles());
        return articleList;
    }

    public void deleteArticle(Integer id){
        articleService.deleteArticle(id);
    }

    public void updateArticle(EArticle article){
        articleService.updateArticle(article);
    }

    public ObservableList<MArticle> getArticleModels(){
        articleModelList.clear(); //???
        getArticles();
        for (EArticle e: articleList) {
            articleModelList.add(articleMapper.mapArticleEntityToModel(e));
        }
        return articleModelList;
    }

    public void addModel(MArticle tempArticle) {
        EArticle toAdd = articleMapper.mapArticleModelToEntity(tempArticle);
        Integer id = addArticle(toAdd);
        articleList.add(toAdd);
        tempArticle.setId(id);
    }

    public void updateModel(MArticle mArticle) {
        EArticle toUpdate = articleMapper.mapArticleModelToEntity(mArticle);
        updateArticle(toUpdate);
        articleList.removeIf(e -> e.getId() == mArticle.getId());
        articleList.add(toUpdate);
    }

    public void deleteModel(MArticle mArticle) {
        articleService.deleteArticle(mArticle.getId());
        articleList.removeIf(e -> e.getId() == mArticle.getId());
    }
}
