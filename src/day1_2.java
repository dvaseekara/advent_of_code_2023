import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.HashMap;

public class day1_2 {
  static HashMap<String, String> hm = new HashMap<>();


  public static void main(String[] args) {
    if(args.length != 1){
      System.out.println("Provide filepath for input file");
    }else{
      String filename = args[0];
      // String filename = "./ResourcesTest/day1_2.txt";
      initializeHashMap();
      System.out.println(calibrateValues(filename));   
    }
  }

  public static void initializeHashMap(){
    hm.put("one", "1");
    hm.put("two", "2");
    hm.put("three", "3");
    hm.put("four", "4");
    hm.put("five", "5");
    hm.put("six", "6");
    hm.put("seven", "7");
    hm.put("eight", "8");
    hm.put("nine", "9");
  }

  public static int calibrateValues(String filename){
    int sum = 0;
    try{
      List<String> lines = Files.readAllLines(Paths.get(filename));
      for(String line: lines){
        if(line != null || line != ""){
          sum = sum + Integer.parseInt(getNumber(line));
        }
      }
    }catch(IOException e){
      e.printStackTrace();
    }
    return sum;
  }

  public static String getNumber(String line){
    String firstNumber = "";
    String secondNumber = "";

    for(int i = 0; i < line.length(); i++){
      if(Character.isDigit(line.charAt(i))){
        firstNumber = line.charAt(i)+"";
        break;
      }
      for(String key: hm.keySet()){
        String subString = line.substring(0, i+1);
        if(subString.contains(key)){
          firstNumber = hm.get(key);
          i = line.length();
          break;
        } 
      }
    }
  
    for(int i = line.length()-1; i >= 0; i--){
      if(Character.isDigit(line.charAt(i))){
        secondNumber = line.charAt(i)+"";
        break;
      }
      for(String key: hm.keySet()){
        String subString = line.substring(i,line.length());
        if(subString.contains(key)){
          secondNumber = hm.get(key);
          i = 0;
          break;
        } 
      }
    }
    System.out.println(line + ":  " + firstNumber + secondNumber);
    return firstNumber + secondNumber;
  }
}
