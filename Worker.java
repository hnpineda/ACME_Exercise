import java.util.HashMap;

/**
 *
 * @author herman
 */
public class Worker {
    private String name ;    
    private final String rawData;
    private HashMap<String, String> schedule;
        
    public Worker (String rawData){
        this.rawData = rawData;
        splitData ();
    }
    
    private void splitData (){
        this.name = rawData.split("=")[0];
        this.schedule = new HashMap<>();
        for (String dateRaw : rawData.split("=")[1].split(",")){
            this.schedule.put(
                    dateRaw.trim().substring(0, 2),
                    dateFormat(dateRaw.trim().substring(2, dateRaw.trim().length()))
            );
        }
    }
    
    private String dateFormat(String date){        
        String hourStart = String.valueOf(
                Integer.valueOf( date.split("\\s*-\\s*")[0].split("\\s*:\\s*")[0] ) * 100 + Integer.valueOf( date.split("\\s*-\\s*")[0].split("\\s*:\\s*")[1]) 
        );
        String hourEnd = String.valueOf(
                Integer.valueOf( date.split("\\s*-\\s*")[1].split("\\s*:\\s*")[0] ) * 100 + Integer.valueOf( date.split("\\s*-\\s*")[1].split("\\s*:\\s*")[1]) 
        );
        return hourStart + "-" + hourEnd;
    }

    @Override
    public String toString() {
        return "Schedule{" + "name=" + name + ", timeMap=" + schedule + '}';
    }

    public String getName() {
        return name;
    }

    public HashMap<String, String> getTimeMap() {
        return schedule;
    }
    
}