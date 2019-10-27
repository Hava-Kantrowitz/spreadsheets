package edu.cs3500.spreadsheets.model;

public class Symbol implements Value {

  private Object evalVal;

  public Symbol(String evalVal) {
    this.evalVal = evalVal;
  }

  @Override
  public Value evaluateCell() {
    return null;
  }

  @Override
  public double evaluateCellSum() {
    return 0;
  }

  @Override
  public double evaluateCellProduct() throws IllegalArgumentException {
    return 0;
  }

  @Override
  public double evaluateCellSqrt() throws IllegalArgumentException {
    return 0;
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
  public boolean equals(Object otherCell) {
    boolean isEqual = false;
    // checking if the other cell is a blank cell too
    if(otherCell instanceof Symbol && ((Symbol) otherCell).evalVal.equals(this.evalVal)){
      isEqual = true;
    }
    return isEqual;
  }
}
