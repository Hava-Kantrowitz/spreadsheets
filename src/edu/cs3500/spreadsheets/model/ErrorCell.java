package edu.cs3500.spreadsheets.model;

import java.util.List;

/**
 * This is the class to represent the cell which has an error. The error could have come from an
 * invalid sexp or an invalid argument in a formula. Once the cell has an error a new error cell is
 * created to take its place.
 * Throws IllegalArgumentExceptions in all the methods when the cell is trying to be used in a
 * function to show that the cell is unable to be used
 */
public class ErrorCell implements Cell {

  private String rawContents;
  private Value error;

  /**
   * This is the constructor for the error cell.
   * @param error the type of error to evaluate to
   * @param rawContents the raw contents of the cell
   */
  public ErrorCell(Value error, String rawContents){
    this.rawContents = rawContents;
    this.error = error;
  }

  @Override
  public Value evaluateCell() {
    return error;
  }

  @Override
  public double evaluateCellSum() {
    throw new IllegalArgumentException("Error in cell.");
  }

  @Override
  public double evaluateCellProduct(List<Formula> formula) throws IllegalArgumentException {
    throw new IllegalArgumentException("Error in cell.");
  }

  @Override
  public double evaluateCellSqrt() throws IllegalArgumentException {
    throw new IllegalArgumentException("Error in cell.");
  }

  @Override
  public double evaluateCellDifference() throws IllegalArgumentException {
    throw new IllegalArgumentException("Error in cell.");
  }

  @Override
  public double evaluateCellComparison() {
    throw new IllegalArgumentException("Error in cell.");
  }

  @Override
  public String evaluateCellHamilton() {
    throw new IllegalArgumentException("Error in cell.");
  }

  @Override
  public boolean isNum() {
    throw new IllegalArgumentException("Error in cell.");
  }

  @Override
  public String getRawContents() {
    return this.rawContents;
  }

  @Override
  public String toString(){
    return this.evaluateCell().toString();
  }
}
