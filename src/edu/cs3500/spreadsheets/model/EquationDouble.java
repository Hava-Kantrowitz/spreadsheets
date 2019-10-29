package edu.cs3500.spreadsheets.model;

public class EquationDouble implements EquationValue {
  double val;

  /**
   * The constructor for the double value.
   *
   * @param val the input double value
   * @throws IllegalArgumentException if the input is not a double
   */
  public EquationDouble(Double val) throws IllegalArgumentException {
    // making sure not null
    if (val != null) {
      this.val = val;
    } else {
      throw new IllegalArgumentException("Not a double value.");
    }
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
  public String evaluateCellHamilton() {
    return null;
  }

  @Override
  public boolean isNum() {
    return true;
  }

  @Override
  public Value evaluateCell() {
    return (Value) this;
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
  public int hashCode() {
    return (int) val;
  }
}
