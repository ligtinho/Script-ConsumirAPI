/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumidorapi;

/**
 *
 * @author Matheus
 */
public class ExecutorChamadas {
    
    public static void main(String args[]){
        ConsumidorAPI consumidor = ConsumidorAPI.getInstance();
        
        consumidor.doLogin();
        System.out.println(consumidor.doRequest("Posicao"));
    }
    
}
