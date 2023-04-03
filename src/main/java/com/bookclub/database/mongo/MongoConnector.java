package com.bookclub.database.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoConnector {

    private static final String HOST = "localhost";
    private static final String PORT = "27018";
//    private static final String USER = "admin";
//    private static final String PASS = "password";

    private static class InstanceHolder {
        private static MongoClient init(){
            return MongoClients.create("mongodb://"+HOST+":"+PORT);
        }

        private static final MongoClient INSTANCE = init();
    }


    public static MongoClient getClient(){
        return InstanceHolder.INSTANCE;
    }


}
