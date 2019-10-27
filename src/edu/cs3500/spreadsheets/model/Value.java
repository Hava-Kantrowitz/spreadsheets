package edu.cs3500.spreadsheets.model;

/**
 * Models a cell with a string, int, or boolean value in it.
 */
public abstract class Value implements Cell {

  /**
   * This evaluates the cell for the number used when calculating a sum.
   * @return the number used to calculate the sum
   */
  abstract double evaluateCellSum();

  /**
   * This evaluates the cell for the number used when calculating the product.
   * @return the value of the value when calculating product
   * @throws IllegalArgumentException if the value cannot be used in a product operation
   */
  abstract double evaluateCellProduct() throws IllegalArgumentException;

  /**
   * This evaluates the cell for the number used when calculating the SQRT.
   * @return the number used when calculating square root
   * @throws IllegalArgumentException if the value cannot be used in the square root operation
   */
  abstract double evaluateCellSqrt() throws IllegalArgumentException;

  /**
   * This evaluates the cell for the number used when calculating the difference.
   * @return the value used when the difference is calculated
   * @throws IllegalArgumentException if the value cannot be used in the difference operation
   */
  abstract double evaluateCellDifference() throws IllegalArgumentException;

  /**
   * This evaluates the cell for the number used when calculating a comparison.
   * @return the value used when a comparison is taken
   */
  abstract double evaluateCellComparison();



}
