import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;
import java.util.Collection;
import java.util.HashMap;

public class day2_2 {
  public static void main(String[] args) {

    if(args.length != 1){
      System.out.println("Provide filepath for input file");
    }else{
      String filePath = args[0];
      // String filePath = "./day2.txt";
      Path path = Paths.get(filePath);
      int res = 0;
      try {
          List<String> lines = Files.readAllLines(path);
          for(int i = 0; i < lines.size(); i++){
            System.out.println(lines.get(i));
            res = res + calculatePower(lines.get(i));
          }
      } catch (IOException e) {
          e.printStackTrace();
      }

      System.out.println("RESULT:  " + res);
    }
  }

  public static Integer calculatePower(String line){
    HashMap<String, Integer> minimums = new HashMap<>();
    minimums.put("red", 0);
    minimums.put("blue", 0);
    minimums.put("green", 0);

    String[] subStrings = line.split(":");
    String[] sets = subStrings[1].split(";");
    for(String set:sets){
      String[] number_colors = set.split(",");
      for(String number_color : number_colors){
       String[] number_color_array = number_color.trim().split(" ");     
       Integer number = Integer.parseInt(number_color_array[0]);
       String color = number_color_array[1];
       if(minimums.get(color) < number){
         minimums.put(color, number);
       }
      }
    }
  
    return hmToPower(minimums);
  }

  public static Integer hmToPower(HashMap<String, Integer> miniumums){
    int power = 1;
    Collection<Integer> values = miniumums.values();
    for(Integer value : values){
      power = power * value;
    }
    System.out.println("POWER:  " + power);
    return power;
  }
}
