package edu.cs3500.spreadsheets.model;

/**
 * This class models the string value of a cell.
 */
public class StringValue implements Value {

  private String val;
  private String rawContents;

  /**
   * This is the constructor for a StringValue without the raw contents.
   *
   * @param val the value of the String
   * @throws IllegalArgumentException if the value is null
   */
  public StringValue(String val) throws IllegalArgumentException {
    // making sure not null
    if (val != null) {
      this.val = val;
    } else {
      throw new IllegalArgumentException("Not a string value.");
    }
  }

  /**
   * This is the constructor for a StringValue with raw contents.
   *
   * @param val the value of the String
   * @param rawContents the raw input for the string value
   * @throws IllegalArgumentException if the value is null
   */
  public StringValue(String val, String rawContents) throws IllegalArgumentException {
    // making sure not null
    if (val != null) {
      this.val = val;
    } else {
      throw new IllegalArgumentException("Not a string value.");
    }
    this.rawContents = rawContents;
  }

  @Override
  public double evaluateCellSum() {
    return 0;
  }

  @Override
  public double evaluateCellProduct(Formula...formulas) {
    int numCount = 0;
    double evalNum;

    for (Formula c : formulas) {
      if (c.isNum()) {
        numCount++;
      }
    }

    if (numCount >= 1) {
      evalNum = 1;
    } else {
      evalNum = 0;
    }

    return evalNum;
  }

  @Override
  public double evaluateCellSqrt() throws IllegalArgumentException {
    throw new IllegalArgumentException("Square root with a string cannot be computed");
  }

  @Override
  public double evaluateCellDifference() throws IllegalArgumentException {
    throw new IllegalArgumentException("Difference with a string cannot be computed");
  }

  @Override
  public double evaluateCellComparison() {
    throw new IllegalArgumentException("Comparison with a string cannot be computed.");
  }

  @Override
  public String evaluateCellHamilton() {
    return null;
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
  public boolean equals(Object otherString) {
    boolean isEqual = false;

    if (otherString instanceof StringValue && ((StringValue) otherString).val.equals(this.val)
    && ((StringValue) otherString).rawContents.equals(this.rawContents)) {
      isEqual = true;
    }
    return isEqual;
  }

  @Override
  public String toString(){
    return this.rawContents;
  }

  @Override
  public int hashCode() {
    return val.hashCode();
  }
}
