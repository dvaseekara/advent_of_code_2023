import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class day1_1 {
  public static void main(String[] args) {
    if(args.length == 0){
      System.out.println("Provide filepath for input file");
    }else{
      String filename = args[0];
      System.out.println(calibrateValues(filename));   
    }
  }

  public static int calibrateValues(String filename){
    int sum = 0;
    try{
      List<String> lines = Files.readAllLines(Paths.get(filename));
      for(String line: lines){
        sum = sum + getNumber(line);
      }
    }catch(IOException e){
      e.printStackTrace();
    }
    return sum;
  }

  public static int getNumber(String line){
    String firstNumber = "";
    for (int i = 0; i < line.length(); i++){
      char character = line.charAt(i);
      if(Character.isDigit(character)){
        firstNumber = firstNumber + character;
        break;
      }
    }

    String secondNumber = "";
    for (int i = line.length()-1; i >= 0; i--){
      char character = line.charAt(i);
      if(Character.isDigit(character)){
        secondNumber = secondNumber + character;
        break;
      }
    }
    String number = firstNumber + secondNumber;
    return Integer.parseInt(number);   
  }
}
