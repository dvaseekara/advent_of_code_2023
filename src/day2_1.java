import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;
import java.util.HashMap;

public class day2_1 {
  static HashMap<String, Integer> limits = new HashMap<>();

  public static void main(String[] args) {

    if(args.length != 1){
      System.out.println("Provide filepath for input file");
    }else{
      initializeLimits();
      String filePath = args[0];
      // String filePath = "./day2.txt";
      Path path = Paths.get(filePath);
      int res = 0;
      try {
          List<String> lines = Files.readAllLines(path);
          for(int i = 0; i < lines.size(); i++){
            System.out.println("GAME:  "+ (i+1));
            if(isValidGame(lines.get(i))){
              res = res + i +1;
              System.out.println("PASSED");
            }else{
              System.out.println("FAILED");
            }
          }
      } catch (IOException e) {
          e.printStackTrace();
      }

      System.out.println("RESULT:  " + res);
    }
  }

  public static void initializeLimits(){
    limits.put("red", 12);
    limits.put("green", 13);
    limits.put("blue", 14);
  }

  public static Boolean isValidGame(String line){
    //Break it down into 
    String[] subStrings = line.split(":");
    String[] sets = subStrings[1].split(";");
    for(String set:sets){
      if(!isValidSet(set)){
        return false;
      }
    }      
    return true;
  }

  public static boolean isValidSet(String set){
    System.out.println(set);

    String[] colors = set.split(",");
    for(String color : colors){
      if(!isValidColor(color.trim())){
        return false;
      };
    }
    return true;
  }

  public static boolean isValidColor(String color){
    String number_color[] = color.split(" ");
    if(limits.get(number_color[1])<Integer.parseInt(number_color[0])){
      return false;
    }
    return true;
  }

}
