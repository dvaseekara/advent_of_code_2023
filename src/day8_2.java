import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.w3c.dom.Node;


public class day8_2 {

  //Create multiple trees?
  //Traverse all the trees simultaneously?
    //Check if all current nodes
  //List of Trees?
  public static void main(String[] args) throws IOException {

      String filePath = "/home/danielvaseekaran/Documents/workspace/advent/Resources/day8_2.txt";
      Path path = Paths.get(filePath);
      Long result = 0L;      
      List<String> lines = Files.readAllLines(path);
      String directions = lines.get(0);
      lines.remove(0);
      Map<String, String[]> map = mapify(lines);
      List<String> startingNodes = findStartingNodes(map);
      startingNodes.stream().forEach(System.out::println);
      result = findPath(directions, map, startingNodes);

      //

      System.out.println("RESULT:  " + result);
  }

  private static List<String> findStartingNodes(Map<String, String[]> map){
    List<String> startingNodes = map.keySet()
                                    .stream()
                                    .filter(s -> s.charAt(2) == 'A')
                                    .collect(Collectors.toList());
    return startingNodes;
  }

  private static Long findPath(String directions, 
                              Map<String, String[]> map, 
                              List<String> startingNodes) {
    int count = 0;
    Map<String,String> solutionMap = new HashMap<>();
    Map<String,Long> finalMap = new HashMap<>();
    for(String node:startingNodes){
      solutionMap.put(node.trim(), node.trim());
      finalMap.put(node.trim(),0L);
    }

    int i = 0;    
    while(i < directions.toCharArray().length){
      if(directions.charAt(i) == 'R'){
        for(String node : solutionMap.keySet()){
          String value = solutionMap.get(node);
          String nextValue = map.get(value)[1];          
          solutionMap.put(node, nextValue.trim());
          if(endInZ(nextValue.trim())){ 
            finalMap.put(node, new Long(count + 1));
            System.out.println("NODE:  "+node+", EndValue:  " + nextValue.trim() + ", Count:  " + count);
          }
        }
      }else if(directions.charAt(i)  == 'L'){
        for(String node : solutionMap.keySet()){
          String value = solutionMap.get(node);
          String nextValue = map.get(value)[0];

          solutionMap.put(node, nextValue.trim());
          if(endInZ(nextValue.trim())){ 
            finalMap.put(node, new Long(count + 1));
            System.out.println("NODE:  "+node+", EndValue:  " + nextValue.trim() + ", Count:  " + count);
          }
        }
      }
      count++; 
      // finalMap.values().stream().forEach(System.out::println);
      // System.out.println("-------"+count+"------");
      if(finalMap.values().stream().allMatch(s -> s != 0)) break;

      i++;
      if(i == directions.toCharArray().length) i = 0;         
    }
    return findLCM(new ArrayList<>(finalMap.values()));
  }

  private static Boolean endInZ(String s) {
    if(s.endsWith("Z")) return true;
    return false;
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

  private static Long findGCD(Long a, Long b) {
    while (b != 0) {
        Long temp = b;
        b = a % b;
        a = temp;
    }
    System.out.println("GCD: " + a);
    return a;
}

  // Function to find the LCM (Lowest Common Multiple) of a list of numbers
  private static Long findLCM(List<Long> numbers) {
      Long lcm = 1L;
      for (Long number : numbers) {
          System.out.println("NUMBER:  " + number);
          System.out.println("LCM:  " + lcm);
          lcm = (lcm * number) / findGCD(lcm, number);
      }
      return lcm;
  }

}

