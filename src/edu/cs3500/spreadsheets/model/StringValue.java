package edu.cs3500.spreadsheets.model;

/**
 * This class models the string value of a cell.
 */
public class StringValue implements Value {

  String val;

  /**
   * This is the constructor for a StringValue.
   * @param val the value of the String
   * @throws IllegalArgumentException if the value is null
   */
  public StringValue(String val) throws IllegalArgumentException{
    // making sure not null
    if(val != null){
      this.val = val;
    }
    else{
      throw new IllegalArgumentException("Not a string value.");
    }
  }

  @Override
  public double evaluateCellSum() {
    return 0;
  }

  @Override
  public double evaluateCellProduct(){
    // if all strings then 0
    // if not all strings then 1
    return 0;
  }

  @Override
  public double evaluateCellSqrt() throws IllegalArgumentException{
    throw new IllegalArgumentException("Square root with a string cannot be computed");
  }

  @Override
  public double evaluateCellDifference() throws IllegalArgumentException{
    throw new IllegalArgumentException("Difference with a string cannot be computed");
  }

  @Override
  public double evaluateCellComparison() {
    throw new IllegalArgumentException("Comparison with a string cannot be computed.");
  }

  @Override
  public Value evaluateCell() {
    return null;
  }
}
