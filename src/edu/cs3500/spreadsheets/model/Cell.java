package edu.cs3500.spreadsheets.model;


import java.util.List;

/**
 * Models a single cell within the current spreadsheet.
 */
public interface Cell {

  /**
   * Evaluates the current cell.
   *
   * @return the contents of the given cell
   */
  Value evaluateCell();

  /**
   * This evaluates the cell for the number used when calculating a sum.
   *
   * @return the number used to calculate the sum
   * @throws IllegalArgumentException when the cell being evaluated in a sum is an ErrorCell
   */
  double evaluateCellSum() throws IllegalArgumentException;

  /**
   * This evaluates the cell for the number used when calculating the product.
   *
   * @return the value of the value when calculating product
   * @throws IllegalArgumentException if the value cannot be used in a product operation
   */
  double evaluateCellProduct(List<Formula> formula) throws IllegalArgumentException;

  /**
   * This evaluates the cell for the number used when calculating the SQRT.
   *
   * @return the number used when calculating square root
   * @throws IllegalArgumentException if the value cannot be used in the square root operation
   */
  double evaluateCellSqrt() throws IllegalArgumentException;

  /**
   * This evaluates the cell for the number used when calculating the difference.
   *
   * @return the value used when the difference is calculated
   * @throws IllegalArgumentException if the value cannot be used in the difference operation
   */
  double evaluateCellDifference() throws IllegalArgumentException;

  /**
   * This evaluates the cell for the number used when calculating a comparison.
   *
   * @return the value used when a comparison is taken
   * @throws IllegalArgumentException when the cell being used in the comparison is an ErrorCell
   */
  double evaluateCellComparison() throws IllegalArgumentException;

  /**
   * This evaluates the cell and determines the coordinating Hamilton lyric.
   * @return the value used when a hamilton is performed
   * @throws IllegalArgumentException if the cell being used in the evaluation is an ErrorCell
   */
  String evaluateCellHamilton() throws IllegalArgumentException;


  /**
   * Determines if the cell is a number.
   * @return true if the cell is a number, false otherwise
   * @throws IllegalArgumentException if the cell being used in the evaluation is an ErrorCell
   */
  boolean isNum() throws IllegalArgumentException;

  /**
   * Gets the raw contents that were entered into the cell.
   * @return the raw contents of the cell
   */
  String getRawContents();

}
