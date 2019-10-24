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
