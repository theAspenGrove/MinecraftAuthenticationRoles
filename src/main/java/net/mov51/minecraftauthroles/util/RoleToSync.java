package net.mov51.minecraftauthroles.util;

import net.mov51.minecraftauthroles.util.services.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static net.mov51.minecraftauthroles.util.ServiceHelper.services;

public class RoleToSync {
    private final String role;
    private final List<String> permissions;
    private final List<Service> conditions = new ArrayList<>();
    public RoleToSync(String role, List<String> permissions, List<String> conditions){
        this.role = role;
        this.permissions = permissions;
        LoadConditions(conditions);
    }
    public String getRole() {
        return role;
    }
    public List<String> getPermissions() {
        return permissions;
    }
    public List<Service> getConditions() {
        return conditions;
    }
    private void LoadConditions(List<String> conditions){
        Pattern pattern = Pattern.compile("\\((.*?)\\)");
        for (String condition : conditions) {
            String service = condition.split("\\(")[0];
            String value = "";
            System.out.println("condition: " + condition);
            if(pattern.matcher(condition).matches()){
                value = pattern.matcher(condition).group(1);
            }
            System.out.println("service: " + service);
            if(services.containsKey(service)){
                this.conditions.add(services.get(service).newService(value));
            }else {
                System.out.println("service " + service + " is invalid");
            }
        }
    }
}
