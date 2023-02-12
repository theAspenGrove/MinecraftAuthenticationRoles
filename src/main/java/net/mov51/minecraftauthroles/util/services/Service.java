package net.mov51.minecraftauthroles.util.services;

import java.util.UUID;

public class Service {
    String value;
    public Service(String value){
        this.value = value;
    }
    public boolean authorize(UUID uuid){
        return false;
    }
    public Service newService(String value){
        return new Service(value);
    }
    public String getValue() {
        return value;
    }

}
