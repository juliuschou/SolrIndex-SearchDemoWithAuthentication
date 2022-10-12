package wsc.solr.searchdemo;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpClientUtil;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient.Builder;
import org.apache.solr.client.solrj.impl.PreemptiveBasicAuthClientBuilderFactory;
import org.apache.solr.client.solrj.impl.SolrHttpClientBuilder;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.ModifiableSolrParams;


public class SolrJExample {
    /**
     * The Solr instance URL running on localhost.
     */
    private static final String SOLR_CORE_URL = "http://localhost:9998/solr/jcg_example_collection";

    /**
     * The static solrClient instance.
     */
    private final SolrClient solrClient = getSolrClient();


    /**
     * Indexing articles by using Java object binding.
     */
    public void indexingByUsingJavaObjectBinding() {
        try {
            List<Article> articles = Article.getArticles();
            System.out.printf("Indexing %d articles...\n", articles.size());
            // send articles to Solr
            solrClient.addBeans(articles);

            // explicit commit pending documents for indexing
            solrClient.commit();

            System.out.printf("%d articles indexed.\n", articles.size());
        } catch (SolrServerException | IOException e) {
            System.err.printf("\nFailed to indexing articles: %s", e.getMessage());
        }
    }

    /**
     * Querying articles by using SolrQuery and converting results into domain
     * objects with Java object binding.
     */
    public void queryingByUsingSolrQueryAndJavaObjectBinding() {
        System.out.println("Querying by using SolrQuery and Java object binding...");
        // constructs a SolrQuery instance
        final SolrQuery solrQuery = new SolrQuery("author:\"Kevin Yang\"");
        solrQuery.addField("id");
        solrQuery.addField("title");
        solrQuery.addField("author");
        solrQuery.setSort("id", ORDER.asc);
        solrQuery.setRows(10);

        // sends search request and gets the response
        QueryResponse response = null;
        try {
            response = solrClient.query(solrQuery);
        } catch (SolrServerException | IOException e) {
            System.err.printf("\nFailed to search articles: %s", e.getMessage());
        }

        // converts to domain objects and prints to standard output
        if (response != null) {
            List<Article> articles = response.getBeans(Article.class);
            System.out.printf("Found %d articles\n", articles.size());
            for (Article article : articles) {
                System.out.println(article.toString());
            }
        }
    }

 
    
    private SolrClient getSolrClient() {
    	String userName = "hrqa";
    	String password = "1qazNJI(";
    	ModifiableSolrParams params = new ModifiableSolrParams();
    	params.set(HttpClientUtil.PROP_BASIC_AUTH_USER, userName);
    	params.set(HttpClientUtil.PROP_BASIC_AUTH_PASS, password);

    	// set the params for authentication here
    	PreemptiveBasicAuthClientBuilderFactory.setDefaultSolrParams(params);
    	PreemptiveBasicAuthClientBuilderFactory preemptiveBasicAuthClientBuilderFactory = new PreemptiveBasicAuthClientBuilderFactory();

    	// create a new client builder from the preemptive client builder factory
    	SolrHttpClientBuilder httpClientBuilder = preemptiveBasicAuthClientBuilderFactory
    	            .getHttpClientBuilder(Optional.empty());

    	// set the client builder to be used by the clientUtil 
    	HttpClientUtil.setHttpClientBuilder(httpClientBuilder);

    	// the params need to be passed here too
    	CloseableHttpClient httpAuthClient = HttpClientUtil.createClient(params);    
    	

    	// now build the solr client with the special http client
    	Builder solrClientBuilder = new HttpSolrClient.Builder(SOLR_CORE_URL).withHttpClient(httpAuthClient);
        
    	// create solr client
    	SolrClient client = solrClientBuilder.build();    	
    	return client;
    }

}
