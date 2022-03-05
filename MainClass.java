import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author herman
 */

public class MainClass {
    public static void main(String[] args) {
        TreeMap<String, HashMap<String, String>> workerSchedules = readInputFile("schedules.txt");    
        TreeMap<String, Integer> overlappingInSchedules = new TreeMap<>();
        Iterator <String> referenceWorkersData = workerSchedules.keySet().iterator();
        while(referenceWorkersData.hasNext())
        {
            String referenceWorkerName= referenceWorkersData.next();
            HashMap<String, String> referenceWorkerWeekSchedule = workerSchedules.get(referenceWorkerName);
            referenceWorkersData.remove();
            Iterator <String> workerDataCompared = workerSchedules.keySet().iterator();
            while(workerDataCompared.hasNext())
            {
                String comparedWorkerName=workerDataCompared.next();
                HashMap<String, String> comparedWorkerWeekSchedule = workerSchedules.get(comparedWorkerName);
                Iterator <String> referenceWorkerDaySchedule = referenceWorkerWeekSchedule.keySet().iterator();
                int overlappingTimes = 0;
                while(referenceWorkerDaySchedule.hasNext()){
                    String referenceDay=(String)referenceWorkerDaySchedule.next();
                    String referenceTime=referenceWorkerWeekSchedule.get(referenceDay);
                    Iterator <String> comparedWorkerDaySchedule = comparedWorkerWeekSchedule.keySet().iterator();
                    while (comparedWorkerDaySchedule.hasNext()){
                        String dayToCompare=(String)comparedWorkerDaySchedule.next();
                        String timeToCompare=comparedWorkerWeekSchedule.get(dayToCompare);
                        if (referenceDay.equals(dayToCompare)){
                            if (isOverlapped(referenceTime, timeToCompare)){
                                overlappingTimes +=1;
                                overlappingInSchedules.put(referenceWorkerName+ "-" + comparedWorkerName, overlappingTimes);
                            }
                        }
                    }
                }
            }
        }
        printOverlapping(overlappingInSchedules);
    }
    
    public static void printOverlapping(TreeMap<String, Integer> overlappingInSchedules){
        Iterator <String> itOverlappingData = overlappingInSchedules.keySet().iterator();
        while(itOverlappingData.hasNext()){
            String overlapingPairName =itOverlappingData.next();
            System.out.println(overlapingPairName + ":" + overlappingInSchedules.get(overlapingPairName) );
        }
    }
    
    public static TreeMap<String, HashMap<String, String>> readInputFile(String txtPath){
        TreeMap<String, HashMap<String, String>> scheduleMap = new TreeMap<>();
        try {
            File myObj = new File(txtPath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String rawData = myReader.nextLine();
                Worker worker = new Worker(rawData);
                scheduleMap.put(worker.getName(), worker.getTimeMap());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return scheduleMap;
    }

    public static Boolean isOverlapped(String referenceTime, String timeToCompare){
        int referenceTimeStart = Integer.valueOf(referenceTime.split("-")[0]);
        int referenceTimeEnd = Integer.valueOf(referenceTime.split("-")[1]);
        int ComparedTimeStart = Integer.valueOf(timeToCompare.split("-")[0]);
        int ComparedTimeEnd = Integer.valueOf(timeToCompare.split("-")[1]);      
        boolean externalBound = referenceTimeStart < ComparedTimeEnd;
        boolean internalBound = referenceTimeEnd > ComparedTimeStart;
        return externalBound && internalBound;
    }
}