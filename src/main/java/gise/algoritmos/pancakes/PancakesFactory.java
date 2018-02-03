package gise.algoritmos.pancakes;

import gise.algoritmos.FromScannedLineToLine;
import gise.algoritmos.ReadFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class PancakesFactory implements FromScannedLineToLine{

  public String allHappy(String currentPancakes, int flipperSize) {
    try {
      return this.beHappy(currentPancakes, flipperSize).toString();
    } catch (NoHappyAllowed e) {
      return "IMPOSSIBLE";
    }
  }

  public Integer beHappy(String pancakes, int size) {
    Flip flip = new Flip(pancakes, size);
    int length = flip.numberOfPancakes();
    for (int i = 0; i < length; i++) {
      char pancake = flip.pancake(i);

      if(flip.nothingPending()){
        if(isHappy(pancake)){
          //next
        }else {
          flip.addPending(i);
        }
      } else {
        if (flip.lastToFlip() == pancake) { //the previous is the same
          flip.addPending(i);
        } else {
          int remainingToFlip = size - flip.getOnFlipper().length();
          if(! flip.hasFrom(remainingToFlip, i)){
            throw new NoHappyAllowed();
          }

          String remaining = pancakes.substring(i, i + remainingToFlip);
          if (remaining.length()==remainingToFlip) {
            for(int j=0; j<remainingToFlip; j++){
              flip.addPending(i+j);
            }
            flip.tryFlip();
            i = -1;
          } else {
            throw new NoHappyAllowed();
          }
        }
      }

      flip.tryFlip();
    }


    return flip.totalFlips();
  }

  private boolean allTheSame(String str) {
    char prev = str.charAt(0);
    for (int i = 1; i < str.length(); i++) {
      if (str.charAt(i) != prev) {
        return false;
      }
    }
    return true;
  }


  private boolean isHappy(char c) {
    return '+' == c;
  }

  protected Integer respuestaCorrecta(char[] s, int k) {
    int res = 0;
    for (int i = 0; i <= s.length - k; i++) {
      if (s[i] == '-') {
        res++;
        for (int j = 0; j < k; j++) {
          s[i + j] = s[i + j] == '+' ? '-' : '+';
        }
      }
    }
    for (int i = 0; i < s.length; i++) {
      if (s[i] == '-') {
        throw new NoHappyAllowed();
      }
    }
    return res;
  }

  @Override
  public String process(Scanner in) {
    String pancakes = in.next();
    int flipperSize = in.nextInt();
    return this.allHappy(pancakes, flipperSize);
  }

  public static void main(String[] args) {
    new ReadFile().process(new PancakesFactory());
  }
}
