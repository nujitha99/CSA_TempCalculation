/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpletestfour;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Student
 */
public class DummyServer {
    
    ArrayList<TemperatureSample> samples = new ArrayList<>();
    String fileName = "Sample.ser";
    
    public DummyServer(){
        try {
            loadFromFile();
        } catch (IOException ex) {
            Logger.getLogger(DummyServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DummyServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean isConnected(){
        System.out.println("[server] - Testing if the server is connected...");
        return true;
    }
    
    public void addSample(TemperatureSample s) throws IOException{
        System.out.println("[server] - Adding " + s + " to " + samples);
        samples.add(s);
        System.out.println("[server] - Samples are " + samples);
        saveToFile();
    }
    
    private void saveToFile() throws FileNotFoundException, IOException {
        System.out.println("[server] - Saving " + samples + " to file " + fileName);
        
        FileOutputStream file = new FileOutputStream(fileName);
        ObjectOutputStream out = new ObjectOutputStream(file);
        
        out.writeObject(samples);
        
        out.close();
        file.close();
        
        System.out.println("[server] - Objects has been serialized");
    }
    
    private void loadFromFile() throws FileNotFoundException, IOException, ClassNotFoundException{
        System.out.println("[server] - loading samples from file");
        
        FileInputStream file = new FileInputStream(fileName);
        ObjectInputStream in = new ObjectInputStream(file);
        
        samples = (ArrayList<TemperatureSample>)in.readObject();
        
        in.close();
        file.close();
    }
    
    public void getColdest(String city) throws Exception {
        Double min = samples.get(0).value;
        for (TemperatureSample sample : samples) {
            if (sample.location.equals(city)) {
                if (min > sample.value) {
                    min = sample.value;
                }
            }
        }
        System.out.println("[server] - Coldest temperature of " + city + " is " + min);
    }

    public void getHottest(String city) throws Exception {
        Double high = samples.get(0).value;
        for (TemperatureSample sample : samples) {
            if (sample.location.equals(city)) {
                if (high < sample.value) {
                    high = sample.value;
                }
            }
        }
        System.out.println("[server] - Hottest temperature of " + city + " is " + high);
    }

    public void getAverage(String city) throws Exception {
       Double total = 0.0;
       int sampleCount = 0;
       Double avg;
       for (TemperatureSample sample : samples) {
           total += sample.value;
           sampleCount++;
       }
       avg = total/sampleCount;
       System.out.println("[server] - The average temperature of " + city + " is " + avg); 
    }

    public void getNumberOfSamples(String city) {
        int count = 0;
        for (TemperatureSample sample : samples) {
            count++;
        }
        System.out.println("[server] - Number of samples collected : " + count);
    }
    
    public void getColdestTemperatureAfter(Date date){
        Double minTemp = samples.get(0).value;
        for (TemperatureSample sample : samples) {
            if ((sample.time).after(date)) {
                if (minTemp > sample.value) {
                    minTemp = sample.value;
                }
            }
        }
        System.out.println("[server] - Coldest temperature after " + date + " is " + minTemp);
    }
    
    public void getHottestTemperatureAfter(Date date){
        Double maxTemp = samples.get(0).value;
        for (TemperatureSample sample : samples) {
            if ((sample.time).after(date)) {
                if (maxTemp < sample.value) {
                    maxTemp = sample.value;
                }
            }
        }
        System.out.println("[server] - Hottest temperature after " + date + " is " + maxTemp);
    }
    
    public void getAverageTemperatureAfter(Date date){
        Double totTemp = 0.0;
        int count = 0;
        Double avgTemp;
        
        for (TemperatureSample sample : samples) {
            if (sample.time.after(date)) {
                totTemp += sample.value;
                count++;
            }
        }
        
        avgTemp = totTemp / count;
        
        System.out.println("[server] - Average temperature after " + date + " is " + avgTemp);
    }
}
