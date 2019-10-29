package edu.cs3500.spreadsheets.model;

/**
 * This is the class to represent a symbol (which is an operation name or a reference to a cell).
 */
public class Reference implements Formula{

  String symbol;

  public Reference(String symbol){

  }

  @Override
  public Object evaluateCell() {
    return null;
  }

  @Override
  public double evaluateCellSum() {
    return 0;
  }

  @Override
  public double evaluateCellProduct(Cell... cells) throws IllegalArgumentException {
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
  public boolean isNum() {
    return false;
  }
}
