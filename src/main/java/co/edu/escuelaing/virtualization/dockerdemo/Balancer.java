package co.edu.escuelaing.virtualization.dockerdemo;
import okhttp3.*;

import java.io.IOException;

public class Balancer {
    private OkHttpClient httpClient;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public Balancer() {
        httpClient=new OkHttpClient();
    }

    public String getMessages(String url) throws IOException {
        try{
            Request request = request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();
            System.out.println("sendig request to"+url);
            Response response = httpClient.newCall(request).execute();
            System.out.println("RESPONSE+ "+ response.body());
            return response.body().string();
        } catch (Exception e ){
            e.printStackTrace();
            return "";
        }
    }

    public String postMessage( String message,String url) throws IOException {
        try{
            RequestBody body = RequestBody.create(message,JSON);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();

            Response response = httpClient.newCall(request).execute();

            return response.body().string();
        } catch(Exception e){
            e.printStackTrace();
            return "";
        }
    }
}
