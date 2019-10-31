package edu.cs3500.spreadsheets.model;

import java.util.List;

/**
 * Models a boolean value.
 */
public class BooleanValue extends Value {

  private Boolean bool;
  private String rawContents;

  /**
   * Constructs an instance of the boolean value class with the value only.
   * @param val the given boolean value
   */
  public BooleanValue(Boolean val) {
    this.bool = val;
    this.rawContents = "";
  }

  /**
   * Constructs an instance of the boolean with the value and rawContents.
   * @param val the given boolean value
   * @param rawContents the raw contents when cell is added
   */
  public BooleanValue(Boolean val, String rawContents) {
    this.bool = val;
    this.rawContents = rawContents;
  }



  @Override
  public double evaluateCellSum() {
    return 0;
  }

  @Override
  public double evaluateCellProduct(List<Formula> formulas) {
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
    int output = 0;
    if (bool) { // if it is true then one
      output = 1;
    }
    return output;
  }

  @Override
  public double evaluateCellDifference() {
    int output = 0;
    if (bool) { // if it is true then one
      output = 1;
    }
    return output;
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
  public boolean isRef() {
    return false;
  }

  @Override
  public boolean isFunction() {
    return false;
  }

  @Override
  public Value evaluateCell() {
    return new BooleanValue(this.bool);
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
  public String toString() {
    return this.rawContents;
  }

  @Override
  public int hashCode() {
    return bool.hashCode();
  }
}
