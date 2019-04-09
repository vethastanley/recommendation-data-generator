package com.bits.dissertation.es;

import com.bits.dissertation.util.HttpDeleteWithBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class ESManager<T> {

    private String HOST;

    private String CONTENT_TYPE = "application/json";

    public ESManager(String host) {
        HOST = host;
        System.out.println("ES Host is " + HOST);
    }

    public void persist(T object) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpPost request = new HttpPost(HOST);

        String payload = objectMapper.writeValueAsString(object);
        //System.out.println("About to persist event " + payload);
        StringEntity entity = new StringEntity(payload);
        entity.setContentType(CONTENT_TYPE);
        request.setEntity(entity);
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse execute = client.execute(request);
        System.out.println("Response status is " + execute.getStatusLine());
        if (execute.getStatusLine().getStatusCode() != 201) {
            System.out.println("Error persisting " + object);
        }
    }

    public void deleteAll() throws IOException {
        HttpPost request = new HttpPost(HOST + ESUrlBuilder.DELETE_WITH_QUERY);
        StringEntity entity = new StringEntity(ESQuery.DELETE_ALL);
        entity.setContentType(CONTENT_TYPE);
        request.setEntity(entity);
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse execute = client.execute(request);
        System.out.println("Response status is " + execute.getStatusLine());
    }
}
