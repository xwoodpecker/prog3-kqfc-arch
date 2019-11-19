import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

public class ArticleMapper {

    private Mapper mapper = new DozerBeanMapper();

    public MArticle mapArticleEntityToModel(EArticle eArt) {
        MArticle destObject =
                mapper.map(eArt, MArticle.class);
        return destObject;
    }
    public EArticle mapArticleModelToEntity(MArticle eArt) {
        EArticle destObject =
                mapper.map(eArt, EArticle.class);
        return destObject;
    }
}
