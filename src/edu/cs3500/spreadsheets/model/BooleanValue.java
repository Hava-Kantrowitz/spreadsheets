package edu.cs3500.spreadsheets.model;

public class BooleanValue implements Cell {

  private Boolean bool;

  // need to add the constructor
  public BooleanValue(Boolean val) {
    this.bool = val;
  }


  @Override
  public double evaluateCellSum() {
    return 0;
  }

  @Override
  public double evaluateCellProduct(Cell...cells) {
    int product = 0;
    for (Cell c : cells) {
      if (c.isNum()) {
        product = 1;
      }
    }
    return product;
  }

  @Override
  public double evaluateCellSqrt() throws IllegalArgumentException{
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
  public boolean isNum() {
    return false;
  }

  @Override
  public Object evaluateCell() {
    return bool;
  }
}
