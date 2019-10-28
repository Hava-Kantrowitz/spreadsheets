package edu.cs3500.spreadsheets.model;

/**
 * This class models the double value in the cell.
 */
public class DoubleValue implements Value{
  double val;

  /**
   * The constructor for the double value.
   * @param val the input double value
   * @throws IllegalArgumentException if the input is not a double
   */
  public DoubleValue(Double val) throws IllegalArgumentException{
    // making sure not null
    if(val != null){
      this.val = val;
    }
    else{
      throw new IllegalArgumentException("Not a double value.");
    }
  }

  @Override
  public double evaluateCellSum() {
    return val;
  }

  @Override
  public double evaluateCellProduct(Cell...cells) {
    return val;
  }

  @Override
  public double evaluateCellSqrt() {
    return val;
  }

  @Override
  public double evaluateCellDifference() {
    return val;
  }

  @Override
  public double evaluateCellComparison() {
    return val;
  }

  @Override
  public boolean isNum() {
    return true;
  }

  @Override
  public Value evaluateCell() {
    return this;
  }

  @Override
  public boolean equals(Object otherCell){
    boolean isEqual = false;

    if(otherCell instanceof DoubleValue && ((DoubleValue) otherCell).val == this.val){
      isEqual = true;
    }

    return isEqual;
  }
}
