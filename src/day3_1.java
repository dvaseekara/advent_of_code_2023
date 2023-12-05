import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;

public class day3_1 {
  public static void main(String[] args) {

    if(args.length != 1){
      System.out.println("Provide filepath for input file");
    }else{
      //parse lines to find numbers
      //if index of first digit + 1 or -1 is a symbol include it in sum
      //if in the previous line or next line the index range of the number+-1 have a symbol include it in the sum

      String filePath = args[0];
      // String filePath = "/home/danielvaseekaran/Documents/workspace/advent/ResourcesTest/day3_1.txt";
      Path path = Paths.get(filePath);
      try {
          Integer res = 0;
          List<String> lines = Files.readAllLines(path);
          for(int i = 0; i < lines.size(); i++){
            String currentLine = lines.get(i);
            String previousLine = "";
            if(i>0) previousLine = lines.get(i-1);
            String nextLine = "";
            if (i < lines.size() - 1) nextLine = lines.get(i+1);

            // Find integers and their range
            for(int j = 0; j < currentLine.length(); j++){
              if(Character.isDigit(currentLine.charAt(j))){
                String tempString = "";
                int[] range = new int[2];
                range[0] = j;
                while(j < currentLine.length() 
                  && Character.isDigit(currentLine.charAt(j))){
                  tempString = tempString + currentLine.charAt(j);
                  range[1] = j;
                  j++;
                }
                // System.out.println("PREVIOUS: " + previousLine);
                // System.out.println("CURRENT: " + currentLine);
                // System.out.println("NEXT:  " + nextLine);
                // System.out.println("INT:  " + tempString);
                // System.out.println("RANGE: ["+range[0]+", "+range[1]+"]");
                if(lineContainsSymbol(currentLine, range) 
                  || lineContainsSymbol(previousLine, range) 
                  || lineContainsSymbol(nextLine, range)){
                    // System.out.println("VALID");                    
                  res = res + Integer.parseInt(tempString);
                }
                // System.out.println("-------------------------------------");

              }
            }
          }
          System.out.println("RESULT:  " + res);
      } catch (IOException e) {
          e.printStackTrace();
      }
    }
  }

  public static boolean lineContainsSymbol(String line, int[] range){
    int lowerRange = range[0] - 1;
    int higherRange = range[1] + 1;
    for(int i = lowerRange; i <= higherRange; i++){
      if(i >= 0 
        && i < line.length()
        && !Character.isLetterOrDigit(line.charAt(i)) 
        && line.charAt(i) != '.'){
        return true;
      }
    }
    return false;
  }
}
