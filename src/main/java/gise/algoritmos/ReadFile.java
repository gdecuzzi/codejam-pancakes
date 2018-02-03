package gise.algoritmos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.function.Supplier;

public class ReadFile {

  public void process(FromScannedLineToLine action){
    Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    int cases = in.nextInt();
    for (int i = 1; i <= cases; ++i) {
      String result = action.process(in);
      String solution = String.format("Case #%d: %s", i, result);
      System.out.println(solution);
    }
  }
}
