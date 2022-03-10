/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event.crud;

/**
 *
 * @author ideapadGAMING
 */
public class rating {
    
    int id_rate;
    int id_event;
    int rate;

    public rating(int id_rate, int id_event, int rate) {
        this.id_rate = id_rate;
        this.id_event = id_event;
        this.rate = rate;
    }

    public rating(int id_event, int rate) {
        this.id_event = id_event;
        this.rate = rate;
    }

    public rating() {
    }

    public int getId_rate() {
        return id_rate;
    }

    public int getId_event() {
        return id_event;
    }

    public int getRate() {
        return rate;
    }

    public void setId_rate(int id_rate) {
        this.id_rate = id_rate;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public void setRate(int rate) {
        this.rate = rate;
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
        final rating other = (rating) obj;
        if (this.id_rate != other.id_rate) {
            return false;
        }
        if (this.id_event != other.id_event) {
            return false;
        }
        if (this.rate != other.rate) {
            return false;
        }
        return true;
    }


    
    
    
    
}
