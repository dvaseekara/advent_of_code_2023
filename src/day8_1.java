import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;


public class day8_1 {
  public static void main(String[] args) throws IOException {

      String filePath = "Resources/day8_1.txt";
      Path path = Paths.get(filePath);
      int result = 0;      
      List<String> lines = Files.readAllLines(path);
      String directions = lines.get(0);
      lines.remove(0);
      Map<String, String[]> map = mapify(lines);
      result = findPath(directions, map);

      System.out.println("RESULT:  " + result);
  }

  private static int findPath(String directions, Map<String, String[]> map) {
    int count = 0;
    String currentPos = "AAA";
    String finalPos = "ZZZ";
    int i = 0;
    while(i < directions.toCharArray().length){
      if(directions.charAt(i) == 'R'){
        if(map.get(currentPos)[1].trim().equals(finalPos)){
          count++;
          break;
        }else{
          currentPos = map.get(currentPos.trim())[1].trim();
          count++;
        };
      }
       if(directions.charAt(i) == 'L'){
        if(map.get(currentPos)[0].trim().equals(finalPos)){
          count++;
          break;
        }else{
          currentPos = map.get(currentPos.trim())[0].trim();
          count++;
        };
      }
      i++;
      if(i == directions.toCharArray().length) i = 0;         
    }
    return count;
  }

  private static Map<String, String[]> mapify(List<String> lines) {
    Map<String, String[]> map = new HashMap<>();
    String key = "";
    String lr = "";
    for(String line:lines){
      if(!line.isEmpty()){
        String[] splitLine = line.split("=");
        key = splitLine[0];
        lr = splitLine[1];
        lr = lr.replace("(", "");
        lr = lr.replace(")", "");
        String[] value = lr.split(",");
        map.put(key.trim(), value);
      }

    }
    return map;
  }

  



}

