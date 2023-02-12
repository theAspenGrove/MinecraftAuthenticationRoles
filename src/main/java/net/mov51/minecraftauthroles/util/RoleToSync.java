package net.mov51.minecraftauthroles.util;

import net.mov51.minecraftauthroles.util.services.Service;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import static net.mov51.minecraftauthroles.util.ServiceHelper.handlePermission;
import static net.mov51.minecraftauthroles.util.ServiceHelper.services;
import static net.mov51.minecraftauthroles.util.logging.logDebug;

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
        //whether all conditions must be met or just one
        return allRequired;
    }
    private void LoadConditions(List<String> conditions){
        //if there is only one condition and it contains "and" then all conditions must be met
        if(conditions.size() == 1){
            if(conditions.get(0).contains(" and ")){
                allRequired = true;
                for (String s : conditions.get(0).split(" and ")) {
                    //handle each condition separately and add it to the list
                    handleCondition(s);
                }
            }
        }
        //if there is more than one condition then only one must be met and it will return true on the first match
        for (String condition : conditions) {
            //handle each condition separately and add it to the list
            handleCondition(condition);
        }
    }
    public void handleUser(Player p){
        logDebug("Handling user " + p.getName() + " for role " + getRole());
        UUID uuid = p.getUniqueId();
        //the current state of the condition criteria
        boolean authorized = false;
        if(isAllRequired()){
            //if all conditions are required then loop through all conditions, returning the value to false if any of them are false and breaking out of the loop
            authorized = true;
            for (Service service : getConditions()) {
                if(!service.authorize(uuid)){
                    authorized = false;
                    break;
                }
            }
        } else{
            //if only one condition is required then the default is false and it will return true on the first match
            for (Service service : getConditions()) {
                if(service.authorize(uuid)){
                    authorized = true;
                    break;
                }
            }
        }
        if(authorized){
            //if the user is authorized then add the role and permissions
            for(String permission : getPermissions()){
                handlePermission(permission,p,true);
            }
        }else{
            //if the user is not authorized then remove the role and permissions
            for(String permission : getPermissions()){
                handlePermission(permission,p,false);
            }
        }
    }
    private void handleCondition(String condition){
        //regex to get the value of the condition, that being the content within ()
        Pattern valuePattern = Pattern.compile("\\((.*?)\\)");
        //get the name of the service used for the key in the services map
        String service = condition.split("\\(")[0];
        //make sure that the condition contains a value and store it in a variable
        String value = "";
        if(valuePattern.matcher(condition).matches()){
            value = valuePattern.matcher(condition).group(1);
        }
        //if the service is valid then add it to the list of conditions
        if(services.containsKey(service)){
            this.conditions.add(services.get(service).newService(value));
        }else {
            //todo log error
        }
    }


}
