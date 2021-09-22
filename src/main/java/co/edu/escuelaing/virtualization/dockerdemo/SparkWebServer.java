/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.escuelaing.virtualization.dockerdemo;

import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import static spark.Spark.*;
/**
 *
 * @author jgarc
 */
public class SparkWebServer {
    public static void main(String... args) throws UnknownHostException{
        MongoDB mongo = new MongoDB();
        port(getPort());
        get("hello", (req,res) -> "Hello Docker!");
        get("database/:s" ,(req,res) -> {
            String s = req.params(":s"); 
            System.out.println(s); 
            mongo.addElelement(s); 
            res.type("application/json");
            return mongo.getLast(); 
                  }); 
    }
    
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

}