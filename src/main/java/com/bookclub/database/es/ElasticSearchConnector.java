package com.bookclub.database.es;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.TransportOptions;
import co.elastic.clients.transport.TransportUtils;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;


public class ElasticSearchConnector {

    private static final String HOST = "localhost";
    private static final int PORT = 9201;


    private static class InstanceHolder {
        private static ElasticsearchClient init() throws IOException {

            File certFile = new File("/Users/raj1238/Documents/ES_certificate/http_ca.crt");

            SSLContext sslContext = TransportUtils
                    .sslContextFromHttpCaCrt(certFile);

            BasicCredentialsProvider credsProv = new BasicCredentialsProvider();
            credsProv.setCredentials(
                    AuthScope.ANY, new UsernamePasswordCredentials("elastic", "password")
            );


            RestClient restClient = RestClient
                    .builder(new HttpHost(HOST, PORT, "https"))
                    .setHttpClientConfigCallback(hc -> hc
                            .setSSLContext(sslContext)
                            .setDefaultCredentialsProvider(credsProv)
                    )
                    .build();
            ElasticsearchTransport transport = new RestClientTransport(
                    restClient, new JacksonJsonpMapper());

            return new ElasticsearchClient(transport);
        }

        private static ElasticsearchClient INSTANCE = null;

        static {
            try {
                INSTANCE = init();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static ElasticsearchClient getClient() {
        return ElasticSearchConnector.InstanceHolder.INSTANCE;
    }


}
