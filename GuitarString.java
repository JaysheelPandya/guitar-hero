// Jaysheel Pandya
// CSE 143 DD with Xunmei Liu
// Homework 2
// The GuitarString class models the vibrating guitar string
// of a given frequency.

import java.util.*;

public class GuitarString {
   
   // stores a list of frequencies
   private Queue<Double> ringBuffer;

   // stores the energy decay that occurs when playing
   public static final double ENERGY_DECAY_FACTOR = 0.996;

   // PRE: Given frequency must be greater than zero (throws 
   // IllegalArgumentException otherwise). Capacity must be greater
   // than or equal to 2 (throws IllegalArgumentException otherwise).
   // POST: Constructs a guitar string of the given frequency
   public GuitarString(double frequency) {
      if (frequency <= 0) {
         throw new IllegalArgumentException();
      }
      int capacity = (int) Math.round(StdAudio.SAMPLE_RATE / frequency);
      if (capacity < 2) {
         throw new IllegalArgumentException();
      }
      ringBuffer = new LinkedList<>();
      for (int i = 0; i < capacity; i++) {
         ringBuffer.add(0.0);
      }
   }
   
   // PRE: Length of init must be greater than or 
   // equal to 2 (throws IllegalArgumentException otherwise).
   // POST: Constructs the guitar string's ringBuffer with
   // the values of init
   public GuitarString(double[] init) {
      if (init.length < 2) {
         throw new IllegalArgumentException();
      }
      ringBuffer = new LinkedList<>();
      for (int i = 0; i < init.length; i++) {
         ringBuffer.add(init[i]);
      }
   }
   
   // Simulates a guitar string being plucked by
   // adding white noise
   public void pluck() {
      Random rand = new Random();
      for (int i = 0; i < ringBuffer.size(); i++) {
         ringBuffer.add(rand.nextDouble() - 0.5);
         ringBuffer.remove();
      }
   }
   
   // Uses Karplus-Strong algorithm to simulate the vibration
   // of a plucked guitar string
   public void tic() {
      ringBuffer.add(ENERGY_DECAY_FACTOR * 
         (ringBuffer.remove() + ringBuffer.peek()) / 2);
   }
   
   // Returns the current sample
   public double sample() {
      return ringBuffer.peek();
   }
}