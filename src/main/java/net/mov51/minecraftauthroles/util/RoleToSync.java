package net.mov51.minecraftauthroles.util;

import net.mov51.minecraftauthroles.util.services.Service;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import static net.mov51.minecraftauthroles.MinecraftAuthRoles.plugin;
import static net.mov51.minecraftauthroles.util.ServiceHelper.handlePermission;
import static net.mov51.minecraftauthroles.util.ServiceHelper.services;

public class RoleToSync {
    private final String role;
    private final List<String> permissions;
    private final List<Service> conditions = new ArrayList<>();
    private boolean allRequired = false;
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
    public boolean isAllRequired() {
        return allRequired;
    }
    private void LoadConditions(List<String> conditions){
        if(conditions.size() == 1){
            if(conditions.get(0).contains(" and ")){
                allRequired = true;
                for (String s : conditions.get(0).split(" and ")) {
                    handleCondition(s);
                }
            }
        }
        for (String condition : conditions) {
            handleCondition(condition);
        }
    }
    public void handleUser(Player p){
        UUID uuid = p.getUniqueId();
        boolean authorized = false;
        if(isAllRequired()){
            authorized = true;
            for (Service service : getConditions()) {
                if(!service.authorize(uuid)){
                    authorized = false;
                    break;
                }
            }
        } else{
            for (Service service : getConditions()) {
                if(service.authorize(uuid)){
                    authorized = true;
                    break;
                }
            }
        }
        if(authorized){
            plugin.getLogger().info("added role " + getRole() + " to " + p.getName());
            for(String permission : getPermissions()){
                handlePermission(permission,p,true);
            }
        }else{
            plugin.getLogger().info("removed role " + getRole() + " from " + p.getName());
            for(String permission : getPermissions()){
                handlePermission(permission,p,false);
            }
        }
    }
    private void handleCondition(String condition){
        Pattern pattern = Pattern.compile("\\((.*?)\\)");
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
