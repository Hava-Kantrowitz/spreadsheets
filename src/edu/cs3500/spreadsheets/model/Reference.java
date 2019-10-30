package edu.cs3500.spreadsheets.model;

import java.sql.Ref;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is the class to represent a symbol (which is an operation name or a reference to a cell).
 */
public class Reference implements Formula{

  private String symbol;
  private Spreadsheet spreadsheet; // change to spread sheet getter


  public Reference(String symbol, Spreadsheet spreadsheet){

    this.symbol = symbol;
  }

  @Override
  public Object evaluateCell() {
    Scanner scan = new Scanner(symbol);
    final Pattern cellRef = Pattern.compile("([A-Za-z]+)([1-9][0-9]*)");
    scan.useDelimiter("\\s+");
    int col;
    int row;
    Coord coord1 = null;
    while (scan.hasNext()) {
      String cell = scan.next();
      Matcher m = cellRef.matcher(cell);
      if (m.matches()) {
        col = Coord.colNameToIndex(m.group(1));
        row = Integer.parseInt(m.group(2));
        coord1 = new Coord(col, row);
      } else {
        throw new IllegalStateException("Expected cell ref");
      }
    }

    assert coord1 != null;
    return spreadsheet.getCellAt(coord1);
  }

  @Override
  public double evaluateCellSum() throws IllegalArgumentException{
    try {
      Double.parseDouble(this.evaluateCell().toString());
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Cannot evaluate a sum of this cell");
    }

    return (double) this.evaluateCell();

  }

  @Override
  public double evaluateCellProduct(Formula...formulas) throws IllegalArgumentException {
    try {
      Double.parseDouble(this.evaluateCell().toString());
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Cannot evaluate a product of this cell");
    }

    return (double) this.evaluateCell();
  }

  @Override
  public double evaluateCellSqrt() throws IllegalArgumentException {
    try {
      Double.parseDouble(this.evaluateCell().toString());
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Cannot evaluate a square root of this cell");
    }

    return (double) this.evaluateCell();
  }

  @Override
  public double evaluateCellDifference() throws IllegalArgumentException {
    try {
      Double.parseDouble(this.evaluateCell().toString());
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Cannot evaluate a difference of this cell");
    }

    return (double) this.evaluateCell();
  }

  @Override
  public double evaluateCellComparison() {
    try {

      Double.parseDouble(this.evaluateCell().toString());
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Cannot evaluate a comparison of this cell");
    }

    return 0;
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
  public boolean equals(Object otherCell) {
    boolean isEqual = false;

    if (otherCell instanceof Reference && ((Reference) otherCell).symbol == (this.symbol)) {
      isEqual = true;
    }

    return isEqual;
  }

  @Override
  public int hashCode() {
    return symbol.hashCode();
  }
}
