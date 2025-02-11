package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is the class to represent a symbol (which is an operation name or a reference to a cell).
 */
public class Reference implements Formula {

  private String symbol;
  private Spreadsheet spreadsheet; // change to spread sheet getter
  private String rawContents;

  private ArrayList<Coord> referredCoords = new ArrayList<>();
  private ArrayList<Integer> referredCols = new ArrayList<>();


  /**
   * Creates an instances of the reference class.
   *
   * @param symbol      the cell the reference is referring to
   * @param spreadsheet the spreadsheet the cell exists in
   */
  public Reference(String symbol, Spreadsheet spreadsheet) {

    this.symbol = symbol;
    this.spreadsheet = spreadsheet;
    parseReferredCells();  // gets the cell or cells referred to
  }

  /**
   * This is the constructor for the reference with the raw content.
   *
   * @param symbol      the given symbol
   * @param spreadsheet the given spreadsheet
   * @param rawContent  the raw contents of the string
   */
  public Reference(String symbol, Spreadsheet spreadsheet, String rawContent) {

    this.symbol = symbol;
    this.spreadsheet = spreadsheet;
    this.rawContents = rawContent;
    parseReferredCells(); // gets the cells referred to
  }

  @Override
  public Value evaluateCell() {
    try {
      Value output;
      if (referredCols.size() == 2) {
        throw new IllegalArgumentException("A column of cells needs an operation to evaluate");
      } else if (referredCoords.size() == 1) { // if there is only one reference
        output = spreadsheet.getCellAt(referredCoords.get(0)).evaluateCell();// eval the given cell
      } else { // multiple references cannot be evaluated alone
        throw new IllegalArgumentException("A section of cells needs an operation to evaluate");
      }
      return output;
    } catch (StackOverflowError e) {
      throw new IllegalArgumentException("Self reference error.");
    }
  }

  @Override
  public double evaluateCellSum() throws IllegalArgumentException {
    try {
      double output;

      if (referredCols.size() == 2) {
        output = sum(spreadsheet.getMultipleColumns(referredCols.get(0), referredCols.get(1)));
      } else if (referredCoords.size() == 1) { // if there is only one reference
        output = spreadsheet.getCellAt(referredCoords.get(0)).evaluateCellSum();
        // eval the given cell
      } else if (referredCoords.size() == 2) {  // if there is a range
        output = sum(spreadsheet.getCellSection(referredCoords.get(0), referredCoords.get(1)));
      } else {  // no more than two arguments in sum range
        throw new IllegalArgumentException("Illegal number of arguments");
      }
      return output;

    } catch (StackOverflowError e) {
      throw new IllegalArgumentException("Self reference error.");
    }

  }

  @Override
  public double evaluateCellProduct(List<Formula> formulas) throws IllegalArgumentException {
    try {
      double output;
      if (referredCols.size() == 2) {
        output = sum(spreadsheet.getMultipleColumns(referredCols.get(0), referredCols.get(1)));
        output = colProduct(spreadsheet.getMultipleColumns(referredCols.get(0),
                referredCols.get(1)));
      } else if (referredCoords.size() == 1) { // if there is only one reference
        output = spreadsheet.getCellAt(referredCoords.get(0)).evaluateCellProduct(formulas);
      } else if (referredCoords.size() == 2) { // if there is a range
        output = product(getListFormula());
      } else { // no more than two arguments in sum range
        throw new IllegalArgumentException("Illegal number of references.");
      }
      return output;
    } catch (StackOverflowError e) {
      throw new IllegalArgumentException("Self reference error.");
    }

  }

  @Override
  public double evaluateCellSqrt() throws IllegalArgumentException {
    try {
      double output;
      if (referredCols.size() == 2) {
        throw new IllegalArgumentException("Cannot evaluate");
      } else if (referredCoords.size() == 1) { // if there is only one reference
        output = spreadsheet.getCellAt(referredCoords.get(0)).evaluateCellSqrt();
      } else { // no more than one argument for sqrt
        throw new IllegalArgumentException("Illegal number of references.");
      }
      return output;
    } catch (StackOverflowError e) {
      throw new IllegalArgumentException("Self reference error.");
    }

  }

  @Override
  public double evaluateCellDifference() throws IllegalArgumentException {
    try {
      double output;
      if (referredCols.size() == 2) {
        throw new IllegalArgumentException("Cannot evaluate");
      } else if (referredCoords.size() == 1) { // if there is only one reference
        output = spreadsheet.getCellAt(referredCoords.get(0)).evaluateCellDifference();
      } else { // no more than one argument for difference
        throw new IllegalArgumentException("Illegal number of references.");
      }
      return output;
    } catch (StackOverflowError e) {
      throw new IllegalArgumentException("Self reference error.");
    }

  }

  @Override
  public double evaluateCellComparison() {
    try {
      double output;
      if (referredCols.size() == 2) {
        throw new IllegalArgumentException("Cannot evaluate");
      } else if (referredCoords.size() == 1) { // if there is only one reference
        output = spreadsheet.getCellAt(referredCoords.get(0)).evaluateCellComparison();
      } else { // no more than one argument for comparison
        throw new IllegalArgumentException("Illegal number of references.");
      }
      return output;
    } catch (StackOverflowError e) {
      throw new IllegalArgumentException("Self reference error.");
    }

  }

  @Override
  public String evaluateCellHamilton() {
    try {
      String output;
      if (referredCoords.size() == 1) { // if there is only one reference, eval the given cell
        output = spreadsheet.getCellAt(referredCoords.get(0)).evaluateCellHamilton();
      } else { // no more than one argument for Hamilton
        throw new IllegalArgumentException("Illegal number of references.");
      }
      return output;
    } catch (StackOverflowError e) {
      throw new IllegalArgumentException("Self reference error.");
    }

  }


  @Override
  public boolean isNum() {
    boolean output = false;


    if (referredCoords.size() == 1) { // if there is only one reference  // eval the given cell
      output = spreadsheet.getCellAt(referredCoords.get(0)).isNum();
    } else {
      ArrayList<Cell> cells
              = spreadsheet.getCellSection(referredCoords.get(0), referredCoords.get(1));
      // going through to see if each is a number
      for (Cell cell : cells) {
        if (cell.isNum()) { // checking if any are numbers
          output = true;
        }
      }
    }

    return output;
  }

  @Override
  public boolean equals(Object otherCell) {
    boolean isEqual = false;

    if (otherCell instanceof Reference && ((Reference) otherCell).symbol.equals(this.symbol)) {
      isEqual = true;
    }

    return isEqual;
  }

  @Override
  public String toString() {
    if (this.evaluateCell() != null) {
      return this.evaluateCell().toString();
    } else {
      return "";
    }

  }

  @Override
  public int hashCode() {
    return symbol.hashCode();
  }

  @Override
  public String getRawContents() {
    return this.rawContents;
  }

  /**
   * This is a helper that gets the cell that is being referred to. Sets the referred coordinates
   * variable of the class and returns the list of symbols.
   */
  private void parseReferredCells() {
    ArrayList<String> symbolsEntered = new ArrayList<>();
    if (symbol.contains(":")) { // determines if multiple
      int splitIndex = symbol.lastIndexOf(":"); // finds where to split
      String firstSymbol = symbol.substring(0, splitIndex); // splits first half
      String secondSymbol = symbol.substring(splitIndex + 1); // splits second half
      symbolsEntered.add(firstSymbol); // adds first
      symbolsEntered.add(secondSymbol); // adds second
    } else {
      symbolsEntered.add(symbol);
    }

    for (String s : symbolsEntered) {
      Scanner scan = new Scanner(s);
      final Pattern cellRef = Pattern.compile("([A-Za-z]+)([1-9][0-9]*)");
      final Pattern colRef = Pattern.compile("([A-Za-z]+)");
      scan.useDelimiter("\\s+");
      int col = 0;
      int row;
      Coord coord1 = null;
      Integer column = null;
      while (scan.hasNext()) {
        String cell = scan.next();
        Matcher m = cellRef.matcher(cell);
        Matcher colMatch = colRef.matcher(cell);
        if (m.matches()) {
          col = Coord.colNameToIndex(m.group(1));
          if (m.group(2) != null) {
            row = Integer.parseInt(m.group(2));
            coord1 = new Coord(col, row);
          }

        }
        // checking if it is a cell reference
        else if (colMatch.matches()) {
          column = Coord.colNameToIndex(colMatch.group(1));
        } else {
          throw new IllegalStateException("Expected cell ref");
        }
      }
      // adding the coord to the list of referred coords
      if (coord1 != null) {
        referredCoords.add(coord1);
      }
      // adding the column to the list of referred columns
      if (column != null) {
        referredCols.add(column);
      }
    }

  }

  /**
   * Adds multiple cells together.
   *
   * @param cells the list of cells to add
   * @return the added value of the cells
   */
  private double sum(List<Cell> cells) {
    int sum = 0;
    for (Cell c : cells) {
      sum += c.evaluateCellSum();
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
      product = product * f.evaluateCellProduct(formulas);
    }
    return product;
  }

  /**
   * Calculates the product of all items in a range of columns.
   *
   * @param multipleColumns the columns to calculate the product of
   * @return the product value
   */
  private double colProduct(ArrayList<Cell> multipleColumns) {
    double product = 1;
    for (Cell c : multipleColumns) {
      if (c.isNum()) {
        product = product * c.evaluateCellSum();
      }

    }
    return product;
  }


  /**
   * Gets a list of the formulas in the referred cells.
   *
   * @return the multiplied value of the cells
   */
  private List<Formula> getListFormula() {
    List<Formula> formulas = new ArrayList<>();

    // if there is only one reference then only one referred to cell
    if (referredCoords.size() == 1 &&
            spreadsheet.getCellAt(referredCoords.get(0)) instanceof Formula) {
      //set the value for cells
      formulas.add((Formula) spreadsheet.getCellAt(referredCoords.get(0)));
    }
    // if there are two coordinates in the list go through and get all those lists
    else if (referredCoords.size() == 2) {
      // coordinates first cell
      int rowOne = referredCoords.get(0).row;
      int rowTwo = referredCoords.get(1).row;

      // coordinates last cell
      int colOne = referredCoords.get(0).col;
      int colTwo = referredCoords.get(1).col;
      // going through the section to get all the formulas
      for (int i = rowOne; i <= rowTwo; i++) {
        for (int j = colOne; j <= colTwo; j++) {
          Coord cellCoord = new Coord(j, i);
          Cell cellRef = spreadsheet.getCellAt(cellCoord);
          // add to the list of formulas
          if (cellRef instanceof Formula) {
            formulas.add((Formula) cellRef);
          }
        }
      }
    }
    return formulas;
  }
}
