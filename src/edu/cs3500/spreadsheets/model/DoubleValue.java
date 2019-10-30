package edu.cs3500.spreadsheets.model;

/**
 * This class models the double value in the cell.
 */
public class DoubleValue implements Value {

  private double val;
  private String rawContents;

  /**
   * The constructor for the double value.
   *
   * @param val the input double value
   * @throws IllegalArgumentException if the input is not a double
   */
  public DoubleValue(Double val) throws IllegalArgumentException {
    // making sure not null
    if (val != null) {
      this.val = val;
    } else {
      throw new IllegalArgumentException("Not a double value.");
    }
  }

  /**
   * Constructs an instance of the double value with the value and rawContents.
   * @param val the given boolean value
   * @param rawContents the raw contents when cell is added
   */
  public DoubleValue(Double val, String rawContents) {
    // making sure not null
    if (val != null) {
      this.val = val;
    } else {
      throw new IllegalArgumentException("Not a double value.");
    }
    this.rawContents = rawContents;
  }

  @Override
  public double evaluateCellSum() {
    return val;
  }

  @Override
  public double evaluateCellProduct(Formula...formulas) {
    return val;
  }

  @Override
  public double evaluateCellSqrt() {
    return Math.sqrt(val);
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
  public String evaluateCellHamilton() {
    return null;
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
  public boolean equals(Object otherCell) {
    boolean isEqual = false;

    if (otherCell instanceof DoubleValue && ((DoubleValue) otherCell).val == (this.val)) {
      isEqual = true;
    }

    return isEqual;
  }

  @Override
  public String toString(){
    return this.rawContents;
  }

  @Override
  public int hashCode() {
    return (int) val;
  }
}
