/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpletestfour;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import static java.time.Instant.now;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Student
 */
public class DummyClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DummyClient client = new DummyClient();
        client.executeTest();
    }
    
    private void executeTest(){
        String city = "london";
        DummyServer server = new DummyServer();
        if (server.isConnected()) {
            System.out.println("[client] - The server is connected, test can proceed");
            System.out.println("[client] - The test has finished");
            
            TemperatureSample s1 = new TemperatureSample();
            s1.value = 10.3;
            s1.location = "london";
            s1.time = new Date();
            System.out.println("[client] - The sample is : " + s1);
            try {
                server.addSample(s1);
            } catch (IOException ex) {
                Logger.getLogger(DummyClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            TemperatureSample s2 = new TemperatureSample();
            s2.value = 9.3;
            s2.location = "london";
            s2.time = new Date();
            System.out.println("[client] - The sample is : " + s2);
            try {
                server.addSample(s2);
            } catch (IOException ex) {
                Logger.getLogger(DummyClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            TemperatureSample s3 = new TemperatureSample();
            s3.value = 8.3;
            s3.location = "colombo";
            s3.time = new Date();
            System.out.println("[client] - The sample is : " + s3);
            try {
                server.addSample(s3);
            } catch (IOException ex) {
                Logger.getLogger(DummyClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                server.getColdest(city);
                server.getHottest(city);
                server.getAverage(city);
                server.getNumberOfSamples(city);
                
                Date currentDate = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(currentDate);
                calendar.add(Calendar.DAY_OF_YEAR, -1);
                Date previousDate = calendar.getTime();
                
                server.getColdestTemperatureAfter(previousDate);
                server.getHottestTemperatureAfter(previousDate);
                server.getAverageTemperatureAfter(previousDate);
                
            } catch (Exception ex) {
                Logger.getLogger(DummyClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            System.out.println("[client] - The server is connected, the test has failed");
        }
    }
    
}
