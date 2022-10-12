# SolrIndex-SearchDemoWithAuthentication
## Reference
The main refercne of this repository is [Apache Solr in Java: Using Apache SolrJ](https://examples.javacodegeeks.com/apache-solr-in-java-using-apache-solrj/) written by Kevin Yang

## Main idea
How to authenticate with Solr Server and then update index and search for documents by the use of SolrJ (Solr Client Java API)?


## Class Diagram
![image](https://user-images.githubusercontent.com/4725611/195236124-bf88962c-f428-48f3-9216-19ede7794670.png)

## Authentication Code
```
private static final String SOLR_CORE_URL = "http://localhost:9998/solr/jcg_example_collection";
 
private SolrClient getSolrClient() {
    ModifiableSolrParams params = new ModifiableSolrParams();
    params.set(HttpClientUtil.PROP_BASIC_AUTH_USER, "user1");
    params.set(HttpClientUtil.PROP_BASIC_AUTH_PASS, "1qazNJI(");

    PreemptiveBasicAuthClientBuilderFactory.setDefaultSolrParams(params);
    PreemptiveBasicAuthClientBuilderFactory preemptiveBasicAuthClientBuilderFactory 
	= new PreemptiveBasicAuthClientBuilderFactory();

    SolrHttpClientBuilder httpClientBuilder = preemptiveBasicAuthClientBuilderFactory
                .getHttpClientBuilder(Optional.empty());

    HttpClientUtil.setHttpClientBuilder(httpClientBuilder);

    CloseableHttpClient httpAuthClient = HttpClientUtil.createClient(params);    
    

    Builder solrClientBuilder = new HttpSolrClient.Builder(SOLR_CORE_URL).withHttpClient(httpAuthClient);
        
    SolrClient client = solrClientBuilder.build();    
    return client;
}
```
