package edu.cs3500.spreadsheets.model;

import java.util.List;
import java.util.Scanner;

import edu.cs3500.spreadsheets.sexp.Parser;
//import edu.cs3500.spreadsheets.sexp.SList;
import edu.cs3500.spreadsheets.sexp.Sexp;

/**
 * Models a cell with a formula.
 */
public class Formula implements Cell {

  private String formula;


  /**
   * Constructs an instance of a formula cell.
   *
   * @param formula the formula within the cell
   */
  public Formula(String formula) {
    this.formula = formula;
  }

  @Override
  public Value evaluateCell() {
    Parser parser = new Parser();
    Scanner scan = new Scanner(formula);


    Sexp input = parser.parse(formula);


    // checking to ensure there is a next argument
//    if (!scan.hasNext()) {
//      throw new IllegalArgumentException("Not a valid formula");
//    }
//    // checking that there is an equals
//    else if (scan.next() != "=") {
//      throw new IllegalArgumentException("Not a valid formula");
//    }


    //else {


//      while (scan.hasNext()) {
//        if (scan.next() == "(") {
//          String afterParen = scan.next();  // to hold value after
//          List listAfterParen;
//          if (afterParen.equals("SUM")) {
//            listAfterParen = getListAfterParen(scan);
//            //sum(listAfterParen.toArray(new Cell[listAfterParen.size()]));
//          } else if (afterParen.equals("PRODUCT")) {
//            listAfterParen = getListAfterParen(scan);
//
//          } else if (afterParen.equals("SQRT")) {
//            getListAfterParen(scan);
//          } else if (afterParen.equals("SUB")) {
//            getListAfterParen(scan);
//          } else if (afterParen.equals("<")) {
//
//          }
//          // if after paren and does not equal anything else above nor another paren
//          else if (!afterParen.equals("(")) {
//            throw new IllegalArgumentException("Invalid operation input.");
//          }
//        }
//      }
    // }
    return null;
  }

  /**
   * This is the helper method to get the list of inputs after the operation name.
   *
   * @param scan the scanner with the input
   */
  List<Cell> getListAfterParen(Scanner scan) {
    return null;
  }

  @Override
  public double evaluateCellSum() {
    return this.evaluateCell().evaluateCellSum();
  }

  @Override
  public double evaluateCellProduct(Cell... cells) throws IllegalArgumentException {
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
  public boolean isNum() {
    return this.evaluateCell().isNum();
  }

  /**
   * Adds multiple cells together.
   *
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

  /**
   * Multiplies multiple cells together.
   *
   * @param cells the list of cells to multiply
   * @return the multiplied value of the cells
   */
  private double product(Cell... cells) {
    double product = 1;
    for (Cell c : cells) {
      product = product * c.evaluateCellProduct();
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

  private String hamilton(Cell cell) {
    return "";
  }

  @Override
  public boolean equals(Object otherCell) {
    boolean isEqual = false;
    // checking that it is a formula and has the same string
    if (otherCell instanceof Formula && ((Formula) otherCell).formula.equals(this.formula)) {
      isEqual = true;
    }
    return isEqual;
  }

  @Override
  public int hashCode() {
    return formula.hashCode();
  }

}
