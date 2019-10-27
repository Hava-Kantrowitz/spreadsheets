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
    //if all nonnumeric, 0
    //if at least 1 nonnumeric, 1
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
  public double evaluateCellComparison() throws IllegalArgumentException {
    throw new IllegalArgumentException("Cannot compare a blank cell");
  }

  @Override
  public Value evaluateCell() {
    return null;
  }
}
