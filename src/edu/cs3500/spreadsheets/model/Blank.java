package edu.cs3500.spreadsheets.model;

import java.util.List;

/**
 * Models a blank cell.
 */
public class Blank implements Cell {
  private Value val;


  /**
   * This is the Blank constructor.
   */
  public Blank() {
    this.val = null;
  }

  @Override
  public Value evaluateCell() {
    return val;
  }

  @Override
  public double evaluateCellSum() {
    return 0;
  }

  @Override
  public double evaluateCellProduct(List<Formula> formulas) {
    int product = 0;
    for (Formula c : formulas) {
      //if there are any numbers the cell is being multiplied with
      //it needs to evaluate to 1
      if (c.isNum()) {
        product = 1;
      }
    }
    return product;
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
  public boolean equals(Object otherCell) {
    boolean isEqual = false;
    // checking if the other cell is a blank cell too
    if (otherCell instanceof Blank && ((Blank) otherCell).val == null && this.val == null) {
      isEqual = true;
    }
    return isEqual;
  }

  @Override
  public String toString() {
    return "";
  }

  @Override
  public int hashCode() {
    return 1;
  }

  @Override
  public String getRawContents() {
    return "";
  }
}
