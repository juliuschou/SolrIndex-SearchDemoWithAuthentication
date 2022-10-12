package wsc.solr.searchdemo;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.beans.Field;

public class Article {
    @Field
    private String id;

    @Field
    private String category;

    @Field
    private String title;

    @Field
    private String author;

    @Field
    private boolean published;

    /**
     * Default constructor.
     */
    public Article() {
    }

    public Article(String id, String category, String title, String author, boolean published) {
        super();
        this.id = id;
        this.category = category;
        this.title = title;
        this.author = author;
        this.published = published;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public boolean isPublished() {
        return published;
    }
    public void setPublished(boolean published) {
        this.published = published;
    }

    @Override
    public String toString() {
        return "Article [id=" + id + ", title=" + title + ", author=" + author + "]";
    }

    public static List<Article> getArticles() {
        List<Article> articles = new ArrayList<Article>();
        articles.add(new Article("0553573333", "java", "Java Array Example", "Kevin Yang", true));
        articles.add(new Article("0626166238", "java", "Java Arrays Showcases", "Kevin Yang", true));
        articles.add(new Article("0221234283", "java", "Java ArrayList 101", "Kevin Yang", true));
        articles.add(new Article("0553579908", "java", "Java Remote Method Invocation Example", "Kevin Yang", true));
        articles.add(new Article("055357342Y", "java", "Java StringTokenizer Example", "Kevin Yang", true));
        articles.add(new Article("0553292123", "java", "Java HashMap Example", "Evan Swing", true));
        articles.add(new Article("0928237471", "java", "Java HashSet Example", "Evan Swing", true));
        articles.add(new Article("0818231712", "solr", "Apache SolrCloud Example", "Kevin Yang", true));
        articles.add(new Article("0812521390", "solr", "The Solr Runbook", "James Cook", false));
        articles.add(new Article("0812550706", "solr", "The Apache Solr Cookbook", "James Cook", true));
        articles.add(new Article("0441385532", "solr", "The Solr REST API", "Steven Thomas", false));
        articles.add(new Article("0380014300", "solr", "SolrCloud Tutorial", "Roger Goodwill", true));
        return articles;
    }

}
