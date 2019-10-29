package edu.cs3500.spreadsheets.model;

/**
 * Models a boolean value.
 */
public class BooleanValue implements Value {

  private Boolean bool;

  /**
   * Constructs an instance of the boolean value class.
   * @param val the given boolean value
   */
  public BooleanValue(Boolean val) {
    this.bool = val;
  }


  @Override
  public double evaluateCellSum() {
    return 0;
  }

  @Override
  public double evaluateCellProduct(Formula...formulas) {
    int product = 0;
    for (Formula c : formulas) {
      if (c.isNum()) {
        product = 1;
      }
    }
    return product;
  }

  @Override
  public double evaluateCellSqrt() throws IllegalArgumentException {
    throw new IllegalArgumentException("Cannot take the square root ");
  }

  @Override
  public double evaluateCellDifference() {
    return 0;
  }

  @Override
  public double evaluateCellComparison() throws IllegalArgumentException {
    throw new IllegalArgumentException("Cannot compare a blank cell");
  }

  @Override
  public String evaluateCellHamilton() {
    return null;
  }

  @Override
  public boolean isNum() {
    return false;
  }

  @Override
  public Value evaluateCell() {
    return this;
  }

  @Override
  public boolean equals(Object otherCell) {
    boolean isEqual = false;

    if (otherCell instanceof BooleanValue && ((BooleanValue) otherCell).bool.equals(this.bool)) {
      isEqual = true;
    }

    return isEqual;
  }

  @Override
  public int hashCode() {
    return bool.hashCode();
  }
}
