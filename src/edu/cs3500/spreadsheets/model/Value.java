package edu.cs3500.spreadsheets.model;

/**
 * Models a cell with a string, int, or boolean value in it.
 */
public class Value implements Cell {

  private Object val;
  private Value evalVal;

  /**
   * Constructs an instance of a cell with a value.
   * @param val The value in the cell, one of string, int, or boolean
   * @throws IllegalArgumentException if the given value is not a string, int, or boolean
   */
  public Value (Object val) {
    if(val == null){
      throw new IllegalArgumentException("Not a type of value");
    }
    else if (val.getClass().equals(String.class) || val.getClass().equals(Integer.class)
            || val.getClass().equals(Boolean.class)) {
      this.val = val;
      this.evalVal = this;
    }

    else {
      throw new IllegalArgumentException("Not a type of value");
    }
  }

  @Override
  public Value evaluateCell() {
    return null;
  }

  @Override
  public boolean equals(Object otherCell){
    boolean isEqual = false;
    // checks that it is an instance of value and the values are equal
    if(otherCell instanceof Value && ((Value) otherCell).val.equals(this.val)){
      isEqual = true;
    }
    return isEqual;
  }
}
