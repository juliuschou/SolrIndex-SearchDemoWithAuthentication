package wsc.solr.searchdemo;

import static org.junit.Assert.*;

import org.junit.Test;

public class SolrJExampleTest {

	@Test
	public void testIndexingByUsingJavaObjectBinding() {
		SolrJExample solrJExample = new SolrJExample();
		solrJExample.indexingByUsingJavaObjectBinding();
	}
	
	@Test
	public void testQueryingByUsingSolrQueryAndJavaObjectBinding() {
		SolrJExample solrJExample = new SolrJExample();
		solrJExample.queryingByUsingSolrQueryAndJavaObjectBinding();
		
	}

}
