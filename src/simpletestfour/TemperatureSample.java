/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpletestfour;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Student
 */
public class TemperatureSample implements Serializable{
    public Double value;
    public String location;
    public Date time;

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TemperatureSample other = (TemperatureSample) obj;
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        if (!Objects.equals(this.time, other.time)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TemperatureSample{" + "value=" + value + ", location=" + location + ", date=" + time + '}';
    }
}
