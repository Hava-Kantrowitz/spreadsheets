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
  }

  @Override
  public Value evaluateCell() {
    return evalVal;
  }

  @Override
  public double evaluateCellSum() {
    return 0;
  }

  @Override
  public double evaluateCellProduct() throws IllegalArgumentException {
    return 1;
  }

  @Override
  public double evaluateCellSqrt() throws IllegalArgumentException {
    throw new IllegalArgumentException("Cannot take the square root of a blank cell");
  }

  @Override
  public double evaluateCellDifference() throws IllegalArgumentException {
    return 0;
  }

  @Override
  public double evaluateCellComparison() {
    return 0;
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
