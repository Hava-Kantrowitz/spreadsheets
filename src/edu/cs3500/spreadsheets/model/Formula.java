package edu.cs3500.spreadsheets.model;

/**
 * Models a cell with a formula.
 */
public class Formula implements Cell {

  private String formula;
  private Value evalVal;

  /**
   * Constructs an instance of a formula cell.
   * @param formula the formula within the cell
   */
  public Formula(String formula) {
    this.formula = formula;
    this.evalVal = evaluateCell();
  }

  @Override
  public Value evaluateCell() {
    return null;
  }

  @Override
  public double evaluateCellSum() {
    return 0;
  }

  @Override
  public double evaluateCellProduct() throws IllegalArgumentException {
    return 0;
  }

  @Override
  public double evaluateCellSqrt() throws IllegalArgumentException {
    return 0;
  }

  @Override
  public double evaluateCellDifference() throws IllegalArgumentException {
    return 0;
  }

  @Override
  public double evaluateCellComparison() {
    return 0;
  }

  /**
   * Adds multiple cells together
   * @param cells the list of cells to add
   * @return the added value of the cells
   */
  private double sum(Cell... cells) {
    int sum = 0;
    for (Cell c : cells) {
      sum += c.evaluateCellSum();
    }

    return sum;
  }

  private double product(Cell...cells) {
    double product = 1;
    for (Cell c : cells) {
      product = product * c.evaluateCellProduct();
    }

    return product;
  }

  private int difference (Cell cell1, Cell cell2) {
    return 0;
  }

  private double sqrt(Cell cell) {
    return cell.evaluateCellSqrt();
  }

  private int comparison(Cell cell1, Cell cell2) {
    return 0;
  }

  private String hamilton(Cell cell) {
    return "";
  }

  @Override
  public boolean equals(Object otherCell){
    boolean isEqual = false;
    // checking that it is a formula and has the same string
    if(otherCell instanceof Formula && ((Formula) otherCell).formula.equals(this.formula)){
      isEqual = true;
    }
    return isEqual;
  }
}
