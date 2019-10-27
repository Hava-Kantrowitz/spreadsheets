package edu.cs3500.spreadsheets.model;

/**
 * This class models the double value in the cell.
 */
public class DoubleValue extends Value{
  double val;

  /**
   * The constructor for the double value.
   * @param val the input double value
   * @throws IllegalArgumentException if the input is not a double
   */
  public DoubleValue(Double val) throws IllegalArgumentException{
    // making sure not null
    if(val != null){
      this.val = val;
    }
    else{
      throw new IllegalArgumentException("Not a double value.");
    }
  }

  @Override
  double evaluateCellSum() {
    return val;
  }

  @Override
  double evaluateCellProduct() {
    return val;
  }

  @Override
  double evaluateCellSqrt() {
    return val;
  }

  @Override
  double evaluateCellDifference() {
    return val;
  }

  @Override
  double evaluateCellComparison() {
    return val;
  }

  @Override
  public Value evaluateCell() {
    return this;
  }


// THIS IS WHAT WAS ORIGINALLY IN THE VALUE CLASS
//  private Object val;
//
//  /**
//   * Constructs an instance of a cell with a value.
//   * @param val The value in the cell, one of string, int, or boolean
//   * @throws IllegalArgumentException if the given value is not a string, int, or boolean
//   */
//  public Value (Object val) {
//    if(val == null){
//      throw new IllegalArgumentException("Not a type of value");
//    }
//    else if (val.getClass().equals(String.class) || val.getClass().equals(Double.class)
//            || val.getClass().equals(Boolean.class)) {
//      this.val = val;
//    }
//    else if(val.getClass().equals(Integer.class)){
//      this.val = ((Integer)val).doubleValue();
//    }
//    else {
//      throw new IllegalArgumentException("Not a type of value");
//    }
//  }
//
//  @Override
//  public Value evaluateCellSum() {
//    return evalVal;
//  }
//
//  /**
//   * Evaluates the value of the cell based on the formula it is called with.
//   * Not what is being displayed but what is used in different calculations.
//   * @param operationType the method that is being called with the value
//   * @return the evaluated value of the given cell with the given operation
//   */
//  public double evaluateCell(String operationType){
//
//    double outputVal;
//
//    // checking if the value is a string
//    if(val instanceof String){
//      if(operationType.equals("SUM")){
//        outputVal = 0;
//      }
//      else if(operationType.equals("PRODUCT")){
//
//      }
//    }
//    // checking if the value is a boolean
//    else if(val instanceof Boolean){
//
//    }
//    // checking is the value is a Double
//    else if(val instanceof Double){
//      outputVal = (double) val;
//    }
//  }
//
//  @Override
//  public boolean equals(Object otherCell){
//    boolean isEqual = false;
//    // checks that it is an instance of value and the values are equal
//    if(otherCell instanceof Value && ((Value) otherCell).val.equals(this.val)){
//      isEqual = true;
//    }
//    return isEqual;
//  }
}
