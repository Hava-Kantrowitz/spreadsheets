package edu.cs3500.spreadsheets.model;

public class BooleanValue implements Cell {

  // need to add the constructor
  // and change the default values


  @Override
  public double evaluateCellSum() {
    return 0;
  }

  @Override
  public double evaluateCellProduct() {
    return 1;
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
  public double evaluateCellComparison() {
    return 0;
  }

  @Override
  public Value evaluateCell() {
    return null;
  }
}
