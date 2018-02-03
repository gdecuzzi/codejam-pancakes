package gise.algoritmos.pancakes;

public class Flip {
  private final String originalPancakes;
  private final int size;


  private int flips;
  private String currentPancakes;

  private String onFlipper;
  private int beginOnFlipperIndex;

  public Flip(String pancakes, int size) {
    this.onFlipper = "";
    this.flips = 0;
    this.originalPancakes = pancakes;
    this.currentPancakes = this.originalPancakes;
    beginOnFlipperIndex = -1;
    this.size = size;
  }

  private void flip(){
    flips += 1;
    String newPancakes = currentPancakes.substring(0, beginOnFlipperIndex);
    newPancakes += reverse(onFlipper);
    newPancakes += currentPancakes.substring(beginOnFlipperIndex + size);
    this.currentPancakes = newPancakes;
    this.beginOnFlipperIndex = -1;
    onFlipper = "";
  }

  private String reverse(String toReverse) {
    String reversed = "";
    for (int i = 0; i < toReverse.length(); i++) {
      if(toReverse.charAt(i) == '+'){
        reversed += '-';
      }else{
        reversed += '+';
      }
    }
    return reversed;
  }


  public int numberOfPancakes() {
    return this.originalPancakes.length();
  }

  public char pancake(int i) {
    return  currentPancakes.charAt(i);
  }

  public void tryFlip() {
    if (onFlipper.length() == size) {
      flip();
    }
  }

  public boolean nothingPending() {
    return onFlipper.isEmpty();
  }

  public void addPending(int i) {
    if(beginOnFlipperIndex == -1){
      beginOnFlipperIndex = i;
    }
    onFlipper += this.pancake(i);
  }

  public char lastToFlip() {
    return onFlipper.charAt(onFlipper.length() - 1);
  }

  public String getOnFlipper() {
    return onFlipper;
  }

  public Integer totalFlips() {
    if(onFlipper.isEmpty()) {
      return flips;
    }else {
      throw new NoHappyAllowed();
    }
  }

  public boolean hasFrom(int needed, int since) {
    return this.currentPancakes.length() >= since + needed;
  }
}
