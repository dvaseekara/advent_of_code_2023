import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class day4_2 {
  public static void main(String[] args) throws IOException {

      String filePath = "Resources/day4_1.txt";
      Path path = Paths.get(filePath);
      int result = 0;      
      List<String> lines = Files.readAllLines(path);

      Map<Integer,Integer> winMap = new HashMap<>();
      int i = 1;
      for(String line:lines){
        winMap.put(i, identifyWinSum(line));
        i++;
      }

      for(int j = 1; j < winMap.size()+1; j++){
        result = result + findRecursivewin(j, winMap);
      }

      System.out.println("RESULT:  " + result);
  }

  public static int findRecursivewin(int i, Map<Integer,Integer> winMap){
    int result = 1;

    for(int j = i + 1; j <= i + winMap.get(i) && winMap.containsKey(j); j++){
      result = result + findRecursivewin(j, winMap);
    }

    return result;
  }

  public static int identifyWinSum(String card){
    int sum = 0;
    String numbers = card.substring(card.indexOf("|") + 2, card.length());
    String winners = card.substring(card.indexOf(":") + 2, card.indexOf("|"));

    Set<String> winnerSet = new HashSet<>();
    for(String winner: winners.trim().split(" ")){
      if(!winner.isBlank() && !winner.isEmpty()){
        winnerSet.add(winner);
      }
    }

    List<String> numberList = Arrays.asList(numbers.trim().split(" "));

    for(String number:numberList){
      if(winnerSet.contains(number)){
        sum++;
      }
    }

    return sum;
  }

}

