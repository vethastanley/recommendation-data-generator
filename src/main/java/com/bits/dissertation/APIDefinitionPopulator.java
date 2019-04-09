package com.bits.dissertation;

import com.bits.dissertation.data.DataSource;
import com.bits.dissertation.entity.APIDefinition;
import com.bits.dissertation.es.ESManager;
import com.bits.dissertation.es.ESUrlBuilder;

import java.io.IOException;

public class APIDefinitionPopulator {

    public static void main(String[] args) {
        ESManager<APIDefinition> esManager = new ESManager<>(ESUrlBuilder.API_DEFINITION_URL);
        try {
            esManager.deleteAll();
            for (int i = 0; i < DataSource.INSTANCE.getAPIs().size(); i ++) {
                System.out.println("Trying to persist api # " + (i + 1));
                APIDefinition definition = new APIDefinition();
                definition.setId(DataSource.INSTANCE.getAPIs().get(i));
                definition.setName(DataSource.INSTANCE.getAPINames().get(i));
                definition.setDescription(DataSource.INSTANCE.getAPIDescription().get(i));
                definition.setImage(DataSource.INSTANCE.getAPIImages().get(i));
                esManager.persist(definition);
            }
        } catch (IOException e) {
            System.err.println("Unable to create api definition");
        }
    }
}
