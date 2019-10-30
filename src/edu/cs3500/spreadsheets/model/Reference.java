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
  private String rawContents;


  /**
   * Constructs a reference to another cell.
   * @param symbol the symbol for the given reference
   * @param spreadsheet the spreadsheet to compare the reference to
   */
  public Reference(String symbol, Spreadsheet spreadsheet){

    this.symbol = symbol;
    this.spreadsheet = spreadsheet;
  }

  /**
   * Constructs a reference to another cell.
   * @param symbol the symbol for the given reference
   * @param spreadsheet the spreadsheet to compare the reference to
   * @param rawContents
   */
  public Reference(String symbol, Spreadsheet spreadsheet, String rawContents){

    this.symbol = symbol;
    this.spreadsheet = spreadsheet;
    this.rawContents = rawContents;
  }

  @Override
  public Value evaluateCell() {
      return getReferredCell().evaluateCell();
  }

  @Override
  public double evaluateCellSum() throws IllegalArgumentException{
    return getReferredCell().evaluateCellSum();
  }

  @Override
  public double evaluateCellProduct(Formula...formulas) throws IllegalArgumentException {
    return getReferredCell().evaluateCellProduct();
  }

  @Override
  public double evaluateCellSqrt() throws IllegalArgumentException {
    return getReferredCell().evaluateCellSqrt();
  }

  @Override
  public double evaluateCellDifference() throws IllegalArgumentException {
    return getReferredCell().evaluateCellDifference();
  }

  @Override
  public double evaluateCellComparison() {
    return getReferredCell().evaluateCellComparison();
  }

  @Override
  public String evaluateCellHamilton() {
    return getReferredCell().evaluateCellHamilton();
  }



  @Override
  public boolean isNum() {
    return getReferredCell().isNum();
  }

  @Override
  public boolean equals(Object otherCell) {
    boolean isEqual = false;

    if (otherCell instanceof Reference && ((Reference) otherCell).symbol.equals((this.symbol))) {
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
    return symbol.hashCode();
  }

  /**
   * This is a helper that gets the cell that is being referred to.
   * @return The cell corresponding to the string symbol of cell
   */
  private Cell getReferredCell(){
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
}
