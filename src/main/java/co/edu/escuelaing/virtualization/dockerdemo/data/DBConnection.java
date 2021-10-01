package co.edu.escuelaing.virtualization.dockerdemo.data;


import com.mongodb.MongoClient;

import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;

public class DBConnection {
    MongoClientURI uri;
    MongoClient mongoClient;

    public DBConnection() {
        uri = new MongoClientURI("mongodb://sebs:clave@db:27017/?serverSelectionTimeoutMS=5000&connectTimeoutMS=10000&authSource=arep&authMechanism=SCRAM-SHA-1&3t.uriVersion=3");
        mongoClient = new MongoClient(uri);
    }

    public ArrayList<String[]> getNames(){
        MongoDatabase database = mongoClient.getDatabase("arep");
        MongoCollection<Document> collection =database.getCollection("logs");
        FindIterable fit = collection.find();
        ArrayList<Document> docs = new ArrayList<Document>();
        ArrayList<String[]> results = new ArrayList<>();
        fit.into(docs);
        for (Document doc : docs) {
            if (doc.get("mensaje")!= null && doc.get("fecha")!=null){
                results.add(new String[]{doc.get("mensaje").toString(), doc.get("fecha").toString()});
            }
        }
        return results;
    }

    public void insertData(String message){
        MongoDatabase database = mongoClient.getDatabase("arep");
        MongoCollection<Document> collection =database.getCollection("logs");
        Document document=new Document();
        document.put("mensaje",message);
        document.put("fecha",getDate());
        collection.insertOne(document);
    }

    private String getDate(){
        java.util.Date fecha = new Date();
        return (fecha.toString());
    }
}
