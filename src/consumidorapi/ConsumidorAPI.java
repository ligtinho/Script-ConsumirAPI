package consumidorapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;



/**
 *
 * @author Matheus
 */
public class ConsumidorAPI {

   private static String URLBase = "http://api.olhovivo.sptrans.com.br/v2.1";
   private static String token = "d0ba38a1fcc21a54c19215e10cc5f1cc4e1b466387e54243edf864f3fae41fbc";
   
   private static ConsumidorAPI instance;
   
   private CloseableHttpClient clienteHTTP;
   
   private ConsumidorAPI() {
       this.clienteHTTP = HttpClients.createDefault();
   }
   
   public static ConsumidorAPI getInstance(){
       if (instance == null) {
           instance = new ConsumidorAPI();
       }
       return instance;
   }
   
   public void doLogin(){
       try {
           HttpPost httpPost = new HttpPost(ConsumidorAPI.URLBase + "/Login/Autenticar?token=" + ConsumidorAPI.token);
         
           ResponseHandler<String> responseHandler = new ResponseHandler<String>(){
             @Override
             public String handleResponse( final HttpResponse response)throws ClientProtocolException,IOException {
                 int status = response.getStatusLine().getStatusCode();
                 if (status >= 200 && status < 300) {
                     HttpEntity entity = response.getEntity();
                     return entity != null ? EntityUtils.toString(entity) : null;
                 } else {
                     throw new ClientProtocolException("Unexpected response status: " + status);
                }
             } 
           };
         String responseBody = this.clienteHTTP.execute(httpPost, responseHandler);
         System.out.println("-----------------------");
         System.out.println(responseBody);
       } catch (IOException ex){
           Logger.getLogger(ConsumidorAPI.class.getName()).log(Level.SEVERE, null,ex);
       }
       
   }  
  
    public String doRequest(String path){
       String responseBody = null;
       try {
           HttpGet httpGet = new HttpGet(ConsumidorAPI.URLBase+path);
           ResponseHandler<String> responseHandler = new ResponseHandler<String>(){
             @Override
             public String handleResponse( final HttpResponse response)throws ClientProtocolException,IOException {
                 int status = response.getStatusLine().getStatusCode();
                 if (status >= 200 && status < 300) {
                     HttpEntity entity = response.getEntity();
                     return entity != null ? EntityUtils.toString(entity) : null;
                 } else {
                     throw new ClientProtocolException("Unexpected response status: " + status);
                }
             } 
           };
         responseBody = this.clienteHTTP.execute(httpGet, responseHandler);
         System.out.println("-----------------------");
       } catch (IOException ex){
           Logger.getLogger(ConsumidorAPI.class.getName()).log(Level.SEVERE, null,ex);
   }
       return responseBody;
}
}
