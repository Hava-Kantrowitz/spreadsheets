package edu.cs3500.spreadsheets.model;

public class BooleanValue extends Value {

  // need to add the constructor
  // and change the default values


  @Override
  double evaluateCellSum() {
    return 0;
  }

  @Override
  double evaluateCellProduct() {
    return 0;
  }

  @Override
  double evaluateCellSqrt() {
    return 0;
  }

  @Override
  double evaluateCellDifference() {
    return 0;
  }

  @Override
  double evaluateCellComparison() {
    return 0;
  }

  @Override
  public Value evaluateCell() {
    return null;
  }
}
