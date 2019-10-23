package edu.cs3500.spreadsheets.model;

/**
 * Models a cell with a string, int, or boolean value in it.
 */
public class Value implements Cell {

  private Object val;

  /**
   * Constructs an instance of a cell with a value.
   * @param val The value in the cell, one of string, int, or boolean
   * @throws IllegalArgumentException if the given value is not a string, int, or boolean
   */
  public Value (Object val) {
    if (val.getClass().equals(String.class) || val.getClass().equals(Integer.class)
            || val.getClass().equals(Boolean.class)) {
      this.val = val;
    }

    else {
      throw new IllegalArgumentException("Not a type of value");
    }
  }

  @Override
  public Value evaluateCell() {
    return null;
  }
}
