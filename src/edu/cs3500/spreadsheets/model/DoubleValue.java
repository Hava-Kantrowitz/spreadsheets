package edu.cs3500.spreadsheets.model;

import java.util.List;

/**
 * This class models the double value in the cell.
 */
public class DoubleValue extends Value {
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
   * The constructor for the double value.
   *
   * @param val the input double value
   * @param rawContent the raw content of the cell
   * @throws IllegalArgumentException if the input is not a double
   */
  public DoubleValue(Double val, String rawContent) throws IllegalArgumentException {
    // making sure not null
    if (val != null) {
      this.val = val;
    } else {
      throw new IllegalArgumentException("Not a double value.");
    }
    this.rawContents = rawContent;
  }

  @Override
  public double evaluateCellSum() {
    return val;
  }

  @Override
  public double evaluateCellProduct(List<Formula> formulas) {
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
    return "There's a million things we haven't done, just you wait!";
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
  public String toString() {
    return Double.toString(this.val);
  }

  @Override
  public int hashCode() {
    return (int) val;
  }

  @Override
  public String getRawContents() {
    return this.rawContents;
  }

}
