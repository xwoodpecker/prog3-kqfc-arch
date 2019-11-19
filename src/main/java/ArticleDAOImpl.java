import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class ArticleDAOImpl implements ArticleDAO {
    private static SessionFactory factory;

       public ArticleDAOImpl() {
        try {
            factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /* Method to CREATE an article in the database */
    public Integer addArticle(EArticle article){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer articleID = null;

        try {
            String description = article.getDescription();
            Integer count = article.getCount();
                    tx = session.beginTransaction();
            EArticle employee = new EArticle(description, count);
            articleID = (Integer) session.save(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return articleID;
    }



    /* Method to  READ all the articles */
    public List<EArticle> getArticles( ){
        Session session = factory.openSession();
        Transaction tx = null;
        List articles = null;

        try {
            tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<EArticle> cq = cb.createQuery(EArticle.class);
            Root<EArticle> rootEntry = cq.from(EArticle.class);
            CriteriaQuery<EArticle> allArticles = cq.select(rootEntry);
            articles = session.createQuery(allArticles).list();

            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            return articles;
        }
    }


    /* Method to UPDATE count for an article */
    public void updateArticle(EArticle article){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            Integer ArticleID = article.getId();
            Integer count = article.getCount();

            tx = session.beginTransaction();
            article = (EArticle)session.get(EArticle.class, ArticleID);
            article.setCount( count );
            session.update(article);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to DELETE an article from the records */
    public void deleteArticle(Integer ArticleID){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            EArticle article = (EArticle)session.get(EArticle.class, ArticleID);
            session.delete(article);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}