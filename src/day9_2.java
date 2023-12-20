import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class day9_2 {

  public static void main(String[] args) throws IOException {

      String filePath = "/home/danielvaseekaran/Documents/workspace/advent/Resources/day9_1.txt";
      Path path = Paths.get(filePath);
      List<String> lines = Files.readAllLines(path);
      Integer result =  lines.stream().mapToInt(day9_2::findNext).sum();
      System.out.println("RESULT:  " + result);
  }

  public static int findNext(String line){
    List<Integer> list = parseLinetoList(line);
    return findNextRecursive(list);
  }

  public static int findNextRecursive(List<Integer> list){
    if(list.stream().allMatch(s -> s==0)){
      return 0;
    }else{
      //create list with diffs
      //call recursively
      List<Integer> diffList = new ArrayList<Integer>();
      for(int i=0; i < list.size()-1; i++){
        diffList.add(list.get(i+1) - list.get(i));
      }
      return list.get(0) - findNextRecursive(diffList);
    }
  }

  public static List<Integer> parseLinetoList(String line){
    String[] array = line.split(" ");
    return Arrays.stream(array)
                .mapToInt(Integer::parseInt) // Convert each string to int
                .boxed() // Convert int to Integer
                .collect(Collectors.toList());
  }
}

/**
 * Oasis produces a report of many values and how they are changing over time
 * Each line is the history of a single value
 * Predict the next value
 * 
 * 
 * 
 */