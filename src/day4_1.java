import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

public class day4_1 {
  public static void main(String[] args) throws IOException {

      String filePath = "Resources/day4_1.txt";
      Path path = Paths.get(filePath);
      int result = 0;      
      List<String> lines = Files.readAllLines(path);
      result =  lines.stream()
                    .mapToInt(day4_1::identifyWinAmount)
                    .sum();


      System.out.println("RESULT:  " + result);
  }

  public static int identifyWinAmount(String card){
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

    int result =  (int) Math.pow(2, sum-1);
    return result;
  }

}

