package edu.cs3500.spreadsheets.model;

/**
 * Models a blank cell.
 */
public class Blank implements Cell {
  private Object val;
  private Value evalVal;

  /**
   * This is an empty constructor for an instance of Blank.
   */
  public Blank() {
    this.val = null;
    this.evalVal = new Value(0.0);
  }

  @Override
  public Value evaluateCell() {
    return evalVal;
  }

  @Override
  public boolean equals(Object otherCell){
    boolean isEqual = false;
    // checking if the other cell is a blank cell too
    if(otherCell instanceof Blank && ((Blank) otherCell).val == null && this.val == null &&
    ((Blank) otherCell).evalVal.equals(this.evalVal)){
      isEqual = true;
    }
    return isEqual;
  }
}
