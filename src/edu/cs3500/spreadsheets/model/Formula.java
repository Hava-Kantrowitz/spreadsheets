package edu.cs3500.spreadsheets.model;

/**
 * Models a cell with a formula.
 */
public class Formula implements Cell {

  private String formula;

  /**
   * Constructs an instance of a formula cell.
   * @param formula the formula within the cell
   */
  public Formula(String formula) {
    this.formula = formula;
  }

  @Override
  public Value evaluateCell() {
    return null;
  }

  private int sum(Cell... cells) {
    return 0;
  }

  private int product(Cell...cells) {
    return 0;
  }

  private int difference (Cell cell1, Cell cell2) {
    return 0;
  }

  private int sqrt(Cell cell) {
    return 0;
  }

  private int comparison(Cell cell1, Cell cell2) {
    return 0;
  }

  private String hamilton(Cell cell) {
    return "";
  }
}
