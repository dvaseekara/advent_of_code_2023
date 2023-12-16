import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class day6_2 {
  public static void main(String[] args) throws IOException {

      String filePath = "Resources/day6_1.txt";
      Path path = Paths.get(filePath);
      List<String> lines = Files.readAllLines(path);
      Double time = parsePoorKerning(lines.get(0));
      Double reordDistance = parsePoorKerning(lines.get(1));

      

      System.out.println("RESULT:  " + waysToWin(time, reordDistance));
  }

  
  private static Double parsePoorKerning(String string) {
    String subString = string.split(":")[1];
    subString = subString.replaceAll(" ", "");
    return Double.parseDouble(subString);
  }


  public static int waysToWin(Double time, Double recordDistance){
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

