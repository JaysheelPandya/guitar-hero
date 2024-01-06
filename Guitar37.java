import java.util.*;
// skeleton version of the class


public class Guitar37 implements Guitar {
   public static final String KEYBOARD =
      "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout  
   private GuitarString[] notesArray;
   private int numberOfTics;
   
   public Guitar37() {
      notesArray = new GuitarString[37];
      for (int i = 0; i < notesArray.length; i++) {
         notesArray[i] = new GuitarString(440.0 * Math.pow(2.0, (i-24.0) / 12.0));
      }
   }
   
   public void playNote(int pitch) {
      if (pitch >= -24 && pitch <= 12) {
         notesArray[pitch + 24].pluck();
      }
   }
   
   public boolean hasString(char key) {
      for (int i = 0; i < KEYBOARD.length(); i++) {
         if (KEYBOARD.charAt(i) == key) {
            return true;
         }
      }
      return false;
   }
   
   public void pluck(char key) {
      if (!hasString(key)) {
         throw new IllegalArgumentException();
      }
      notesArray[KEYBOARD.indexOf(key)].pluck();
   }
   
   public double sample() {
      double sum = 0;
      for (int i = 0; i < KEYBOARD.length(); i++) {
         sum += notesArray[i].sample();
      }
      return sum;
   }
   
   public void tic() {
      for (int i = 0; i < KEYBOARD.length(); i++) {
         notesArray[i].tic();
      }  
      numberOfTics++;    
   }
   
   public int time() {
      return numberOfTics;
   }
}