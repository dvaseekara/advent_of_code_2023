import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;


public class day3_2 {
  public static void main(String[] args) {

    // if(args.length != 1){
    //   System.out.println("Provide filepath for input file");
    // }else{
      //parse lines to find numbers
      //if index of first digit + 1 or -1 is a symbol include it in sum
      //if in the previous line or next line the index range of the number+-1 have a symbol include it in the sum

      // String filePath = args[0]      
      String filePath = "/home/danielvaseekaran/Documents/workspace/advent/ResourcesTest/day3_1.txt";
      Path path = Paths.get(filePath);
      List<String> lines;
      int res = 0;
      try {
        lines = Files.readAllLines(path);
        for(int i = 0; i < lines.size(); i++){
          //if line contains symbol - There could be multiple symbols per line
            //search previous line for number
            //search next line for number
            //multiple those numbers together if they exist
          String line = lines.get(i);
          String previousLine = "";
          String nextLine = "";
          if(i>0) previousLine = lines.get(i-1);
          if(i<lines.size() - 1) nextLine = lines.get(i+1);
          if(lineContainsSymbol(line)){
            List<Integer> symbol_indices = findSymbolindex(line);
            for(int symbol_index:symbol_indices){
              if(symbolHasDigitsOnBothSides(line, symbol_index)){
                res = res + findNumberAroundIndexSameLine(line, symbol_index);
              }
              if(lineContainsNumberAroundSymbol(previousLine, symbol_index)){
                if(lineContainsNumberAroundSymbol(nextLine, symbol_index)){
                  System.out.println("LINE:  " + i);
                  System.out.println("SYMBOL_INDEX:  " + symbol_index);
                  System.out.println(findNumberAroundIndex(previousLine, symbol_index) + "*" + findNumberAroundIndex(nextLine, symbol_index));
                  res = res + findNumberAroundIndex(previousLine, symbol_index) * findNumberAroundIndex(nextLine, symbol_index);
                }
              }
            }
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
      System.out.println("RESULT:  " + res);
  }

  private static int findNumberAroundIndexSameLine(String line, int symbol_index) {
    String numb1 = "";
    String numb2 = "";
    int i = symbol_index + 1;
    int j = symbol_index - 1;
    while(i<line.length() && Character.isDigit(line.charAt(i))){
      numb1 = numb1 + line.charAt(i);
      i++;
    }
    while(j>=0 && Character.isDigit(line.charAt(j))){
      numb2 = line.charAt(j) + numb2;
      j--;
    }    
    System.out.println("SAMELINE:  " + numb1 + "*" + numb2);
    return Integer.parseInt(numb1) * Integer.parseInt(numb2);
  }

  private static boolean symbolHasDigitsOnBothSides(String line, int symbol_index) {
    if(Character.isDigit(line.charAt(symbol_index-1)) && Character.isDigit(line.charAt(symbol_index + 1))){
      return true;
    }
    return false;
  }

  private static int findNumberAroundIndex(String line, int symbol_index) {
    for(int i = symbol_index -1; i <= symbol_index + 1; i++){
      if(Character.isDigit(line.charAt(i))){

        //find rest of number
        String tempString = "";
        int j = i;
        while(j < line.length() && Character.isDigit(line.charAt(j))){
          tempString = tempString + line.charAt(j);
          j++;
        }
        
        int k = i - 1;
        while(k >= 0 && Character.isDigit(line.charAt(k))){
          tempString = line.charAt(k) + tempString;
          k--;
        }

        return Integer.parseInt(tempString);
      }
    }
    return 0;
  }

  private static boolean lineContainsNumberAroundSymbol(String line, int symbol_index) {
    for(int i = symbol_index - 1; i <= symbol_index + 1 && i < line.length() && i >= 0; i++){
      if(Character.isDigit(line.charAt(i))){
        return true;
      }
    }
    return false;
  }

  private static List<Integer> findSymbolindex(String line) {
    List<Integer> symbol_indices = new ArrayList();
    for(int i = 0; i < line.length(); i++){
      if(!Character.isLetterOrDigit(line.charAt(i)) && line.charAt(i) != '.'){
        symbol_indices.add(i);
      }
    }

    return symbol_indices;
  }

  private static boolean lineContainsSymbol(String line) {
    for(int i = 0; i < line.length(); i++){
      if(!Character.isLetterOrDigit(line.charAt(i)) && line.charAt(i) != '.'){
        return true;
      }
    }
    return false;
  }            
}

