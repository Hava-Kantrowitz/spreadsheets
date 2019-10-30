package edu.cs3500.spreadsheets.model;

import java.text.Normalizer;
import java.util.List;
import java.util.Scanner;


public class Function implements Formula {

  private String functionName;
  List<Formula> funParams;
  String rawContents;


  /**
   * Constructs an instance of a formula cell with the function name and list of parameters.
   *
   * @param functionName the name of the function
   * @param funParams    the list of all the function parameters
   */
  public Function(String functionName, List<Formula> funParams) {
    this.functionName = functionName;
    this.funParams = funParams;
  }

  /**
   * Constructs an instance of a formula cell with the raw contents included.
   *
   * @param functionName the name of the function
   * @param funParams    the list of all the function parameters
   * @param rawContents  the string entered to the cell
   */
  public Function(String functionName, List<Formula> funParams, String rawContents) {
    this.functionName = functionName;
    this.funParams = funParams;
    this.rawContents = rawContents;

  }


  @Override
  public Value evaluateCell() throws IllegalArgumentException {
    Value output;

    // making sure the function parameters are at least greater than zero
    if (funParams.size() < 1) {
      throw new IllegalArgumentException("There must be at least one parameter for any function");
    }

    if (functionName.equals("SUM")) {
      output = new DoubleValue(sum(funParams));
    } else if (functionName.equals("PRODUCT")) {
      output = new DoubleValue(product(funParams));
    } else if (functionName.equals("SUB")) {
      // making sure there are two inputs
      if (funParams.size() != 2) {
        throw new IllegalArgumentException("Subtraction cannot have more than two " +
                "function parameters");
      }
      output = new DoubleValue(difference(funParams.get(0), funParams.get(1)));
    } else if (functionName.equals("<")) {
      // making sure there are two inputs
      if (funParams.size() != 2) {
        throw new IllegalArgumentException("Subtraction cannot have more than two " +
                "function parameters");
      }
      output = new BooleanValue(comparison(funParams.get(0), funParams.get(1)));
    } else if (functionName.equals("SQRT")) {
      //making sure the input is only one
      if (funParams.size() != 1) {
        throw new IllegalArgumentException("Square root must have one parameter");
      }
      output = new DoubleValue(sqrt(funParams.get(0)));
    } else if (functionName.equals("HAM")) {
      if (funParams.size() != 1) { // making sure one input
        throw new IllegalArgumentException("Hamilton function must only have one input.");
      }
      output = new StringValue(hamilton(funParams.get(0)));
    } else {
      throw new IllegalArgumentException("Function entered does not exist" + functionName);
    }

    return output;
  }


  @Override
  public double evaluateCellSum() {
    return this.evaluateCell().evaluateCellSum();
  }

  @Override
  public double evaluateCellProduct(Formula... formulas) throws IllegalArgumentException {
    return this.evaluateCell().evaluateCellProduct();
  }

  @Override
  public double evaluateCellSqrt() throws IllegalArgumentException {
    return this.evaluateCell().evaluateCellSqrt();
  }

  @Override
  public double evaluateCellDifference() throws IllegalArgumentException {
    return this.evaluateCell().evaluateCellDifference();
  }

  @Override
  public double evaluateCellComparison() {
    return this.evaluateCell().evaluateCellComparison();
  }

  @Override
  public String evaluateCellHamilton() {
    return null;
  }

  @Override
  public boolean isNum() {
    return this.evaluateCell().isNum();
  }

  /**
   * Adds multiple cells together.
   *
   * @param formulas the list of cells to add
   * @return the added value of the cells
   */
  private double sum(List<Formula> formulas) {
    int sum = 0;
    for (Formula f : formulas) {
      sum += f.evaluateCellSum();
    }

    return sum;
  }

  /**
   * Multiplies multiple cells together.
   *
   * @param formulas the list of cells to multiply
   * @return the multiplied value of the cells
   */
  private double product(List<Formula> formulas) {
    double product = 1;
    for (Formula f : formulas) {
      product = product * f.evaluateCellProduct();
    }

    return product;
  }

  /**
   * This computes the difference between cell1 and cell2.
   *
   * @param cell1 the first given cell
   * @param cell2 the second given cell
   * @return the difference between cell1 and cell2
   */
  private double difference(Cell cell1, Cell cell2) {
    return cell1.evaluateCellDifference() - cell2.evaluateCellDifference();
  }

  /**
   * This computes the square root of the given cell.
   *
   * @param cell the given cell
   * @return the square root of the given cell
   */
  private double sqrt(Cell cell) {
    return cell.evaluateCellSqrt();
  }

  /**
   * Compares cell1 to determine if it is less than cell2.
   *
   * @param cell1 the first given cell
   * @param cell2 the second given cell
   * @return true if cell1 is less than cell2
   */
  private boolean comparison(Cell cell1, Cell cell2) {
    boolean isLessThan = false;

    if (cell1.evaluateCellComparison() < cell2.evaluateCellComparison()) {
      isLessThan = true;
    }
    return isLessThan;
  }

  private String hamilton(Formula formula) {
    return "";
  }

  @Override
  public boolean equals(Object otherCell) {
    boolean isEqual = false;
    // checking that it is a formula and has the same string
    if (otherCell instanceof Function
            && ((Function) otherCell).functionName.equals(this.functionName)
            && ((Function) otherCell).funParams.equals(this.funParams)
            && ((Function) otherCell).rawContents.equals(this.rawContents)) {
      isEqual = true;
    }
    return isEqual;
  }

  @Override
  public String toString() {
    return this.rawContents;
  }

  @Override
  public int hashCode() {
    return functionName.hashCode();
  }
}
