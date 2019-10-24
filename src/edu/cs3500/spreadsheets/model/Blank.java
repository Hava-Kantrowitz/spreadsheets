package edu.cs3500.spreadsheets.model;

/**
 * Models a blank cell.
 */
public class Blank implements Cell {

  private Value evalVal = null;

  /**
   * This is an empty constructor for an instance of Blank.
   */
  public Blank() {
    //this is an empty constructor because the blank class does not contain anything,
    //it is a placeholder.
  }

  @Override
  public Value evaluateCell() {
    return evalVal;
  }

  @Override
  public boolean equals(Object otherCell){
    boolean isEqual = false;
    // checking if the other cell is a blank cell too
    if(otherCell instanceof Blank && ((Blank) otherCell).evalVal == null && this.evalVal == null){
      isEqual = true;
    }
    return isEqual;
  }
}
