import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class day6_1 {
  public static void main(String[] args) throws IOException {

      String filePath = "Resources/day6_1.txt";
      Path path = Paths.get(filePath);
      int result = 0;      
      List<String> lines = Files.readAllLines(path);
      Map<Integer, Integer> raceMap = mapify(lines.get(0), lines.get(1));
      result = raceMap.entrySet().stream().mapToInt(day6_1::waysToWin).reduce(1, (a,b) -> a*b);

      System.out.println("RESULT:  " + result);
  }

  private static Map<Integer, Integer> mapify(String time, String distance) {
    String[] times = time.split(" ");
    String[] distances = distance.split(" ");
    List<Integer> timesList = new ArrayList<>();
    List<Integer> distanceList = new ArrayList<>();
    Map<Integer, Integer> raceMap = new HashMap<>();

    for(int i = 1 ; i < times.length; i++){
      if(!times[i].isEmpty()){
        timesList.add(Integer.parseInt(times[i]));
      }
    }

    for(int i = 1 ; i < distances.length; i++){
      if(!distances[i].isEmpty()){
        distanceList.add(Integer.parseInt(distances[i]));
      }
    }

    for(int i = 0; i < timesList.size(); i++){
      raceMap.put(timesList.get(i), distanceList.get(i));
    }
    return raceMap;
  }

  public static int waysToWin(Map.Entry<Integer,Integer> race){
    Integer time = race.getKey();
    Integer recordDistance = race.getValue();
    Integer result = 0;
    //brute force: iterate from 0:time and calculate distance, if distance is greater than mindist, res++
    //System of equations: 
    // t1 * t2 = d;
    // t1 + t2 = time;
    // recordDistance < d;
    // find values for t1 and t1 given the above
    // t1 * (time - t1) > recordDistance
    for (int i = 1; i <= time; i++){
      if(i * (time - i) > recordDistance){
        result++;
      }
    }
    return result;
  }

}

