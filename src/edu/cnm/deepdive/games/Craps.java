package edu.cnm.deepdive.games;

import java.util.Random;
import javax.swing.plaf.nimbus.State;

/**
 * What not how
 */

public class Craps {
  /**  Fragment, not full sentence (let alone paragraph. */

  public static final int DEFAULT_NUM_PLAYS = 1000;
  public static final String OUTCOME_MESSAGE = "Plays: %d; wins: %d; losses: %d; winning percentage: %.2f%%";

  /**
   * What this does, as a descriptive (not imperative).
   * e.g. Simulates multiple plays of Craps, tallying the pass line bet outcomes.
   * @param args
   */

  public static void main(String[] args) {
    int plays = (args.length > 0) ? Integer.parseInt(args[0]): DEFAULT_NUM_PLAYS;
    int wins = 0;
    int losses = 0;
    Random rng = new Random();
    for (int i = 0; i < plays; i++){
      State state = State.COME_OUT;

      int point = 0;
      while (state != State.WIN && state != State.LOSE) {
        int die1 = 1 + rng.nextInt(6);  //shift
        int die2 = 1 + rng.nextInt(6);
        int total = die1 + die2;
        state = state.newState(total, point);
        if (state == State.POINT && point == 0) {
          point = total;
        }
      }
      System.out.printf("Outcome: %s%n", state);
      if (state == State.WIN) {
        wins++;
      } else {
        losses++;
      }

    }
    System.out.printf(OUTCOME_MESSAGE, plays, wins, losses, 100.00 * wins / plays);
  }

  public enum State {
    COME_OUT{
      @Override
      public State newState(int roll) {
        switch (roll) {
          case 7:
          case 11:
            return WIN;
          case 2:
          case 3:
          case 12:
            return LOSE;
          default:
            return POINT;
        }
      }
      @Override
      public State newState(int roll, int point){
        return newState(roll);
      }
    },

    POINT {
      @Override
      public State newState(int roll)
        throws IllegalArgumentException {
        if (roll != 7) {
          throw new IllegalArgumentException();
        }
        return LOSE;
      }

      @Override
      public State newState(int roll, int point) {
        if (roll == point) {
          return WIN;
        } else if (roll == 7){
              return LOSE;
          }
          return POINT;
          }
    },


    WIN,

    LOSE{};

    public State newState(int roll){
      return this;

    }

    /**
     * description (not imperative command).
     * e.g. Computes and returns next state instance, based on this instance and
     * roll and point values.
     *
     * @param roll
     * @param point
     * @return
     */
    public State newState(int roll, int point){
      return this;

    }
  }


}
