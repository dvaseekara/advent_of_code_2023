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


public class day9_1 {

  public static void main(String[] args) throws IOException {

      String filePath = "/home/danielvaseekaran/Documents/workspace/advent/Resources/day8_2.txt";
      Path path = Paths.get(filePath);
      Long result = 0L;      
      List<String> lines = Files.readAllLines(path);


      System.out.println("RESULT:  " + result);
  }

  

}

