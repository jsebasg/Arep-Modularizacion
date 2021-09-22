/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.escuelaing.virtualization.dockerdemo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

/**
 *
 * @author jgarc
 */
public class MongoDB {
    MongoClientURI uri;
    MongoClient mongoClient;
    public MongoDB(){
        //uri = new MongoClientURI("mongodb://sebs:clave@db:27017/?serverSelectionTimeoutMS=5000&connectTimeoutMS=10000&authSource=Arep&authMechanism=SCRAM-SHA-1&3t.uriVersion=3");
        uri = new MongoClientURI("mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass&directConnection=true&ssl=false");
        
        mongoClient = new MongoClient(uri);
        MongoIterable<String> list = mongoClient.listDatabaseNames();
        for (String name : list) {
            System.out.println(name);
        }
        MongoIterable<String> list2 = mongoClient.getDatabase("arep").listCollectionNames();
        for (String name : list2) {
            System.out.println(name);
        }
    }
    public void addElelement(String message){
        MongoDatabase database = mongoClient.getDatabase("arep");
        MongoCollection<Document> collection = database.getCollection("local");
        System.out.println(collection.countDocuments());
        Document document=new Document();
        document.put("mensaje",message);
        document.put("fecha", LocalDateTime.now()); 
        System.out.println("entro a add1 DB");
        collection.insertOne(document);
        System.out.println("entro a add2 DB");
        System.out.println(collection.countDocuments());
    }
    public String getLast(){
        System.out.println("entro a last DB");
        MongoDatabase database = mongoClient.getDatabase("arep");
        MongoCollection<Document> collection =database.getCollection("local");
        FindIterable fit = collection.find();
        ArrayList<Document> docs = new ArrayList<Document>();
        ArrayList<String> results = new ArrayList<>();
        fit.into(docs);
        System.out.println(docs.size());
        for (Document doc : docs) {
            if (doc.get("mensaje")!= null){
                results.add("Elemento: " + doc.get("mensaje").toString() + "<br> Fecha de Creacion: "+ doc.get("fecha").toString()+ "<br>");
            }
            if(results.size() >= 10 )break;
            
        }
        return results.toString().replace(",", "<br>").replace("[", "").replace("]", "");
    }
}
