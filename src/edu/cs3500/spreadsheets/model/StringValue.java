package edu.cs3500.spreadsheets.model;

/**
 * This class models the string value of a cell.
 */
public class StringValue implements Value {

  private String val;

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
  public double evaluateCellProduct(Cell...cells){
    int numCount = 0; // the number of other numbers
    double evalNum;

    for(Cell c: cells){
      if(c.isNum()){
        numCount++;
      }
    }
    // if all strings then 0
    if(numCount == 1){
      evalNum = 0;
    }
    // if not all strings then 1
    else{
      evalNum = 1;
    }
    return evalNum;
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
  public boolean isNum() {
    return false;
  }

  @Override
  public Value evaluateCell() {
    return this;
  }

  @Override
  public boolean equals(Object otherString){
    boolean isEqual = false;

    if(otherString instanceof StringValue && ((StringValue) otherString).val.equals(this.val)){
      isEqual = true;
    }
    return isEqual;
  }
}
