package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is the class to represent a symbol (which is an operation name or a reference to a cell).
 */
public class Reference implements Formula{

  protected String symbol;
  private Spreadsheet spreadsheet; // change to spread sheet getter
  protected int rowOver;
  protected int colOver;
  private String rawContent;

  private ArrayList<Coord> referredCoords = new ArrayList<>();
  private ArrayList<Coord> referredCells = new ArrayList<>();


  public Reference(String symbol, Spreadsheet spreadsheet){

    this.symbol = symbol;
    this.spreadsheet = spreadsheet;
    parseReferredCells();  // gets the cell or cells referred to
  }

  /**
   * This is the constructor for the reference with the raw content.
   * @param symbol the given symbol
   * @param spreadsheet the given spreadsheet
   * @param rawContent the raw contents of the string
   */
  public Reference(String symbol, Spreadsheet spreadsheet, String rawContent){

    this.symbol = symbol;
    this.spreadsheet = spreadsheet;
    this.rawContent = rawContent;
    parseReferredCells(); // gets the cells referred to
  }

  @Override
  public Value evaluateCell() {
    Value output;
    if(referredCoords.size() == 1) { // if there is only one reference
      output = spreadsheet.getCellAt(referredCoords.get(0)).evaluateCell();// eval the given cell
    }
    else{ // multiple references cannot be evaluated alone
      throw new IllegalArgumentException("A section of cells needs an operation to evaluate");
    }
    return output;
  }

  @Override
  public double evaluateCellSum() throws IllegalArgumentException{
    double output;
    if(referredCoords.size() == 1) { // if there is only one reference
      output = spreadsheet.getCellAt(referredCoords.get(0)).evaluateCellSum();// eval the given cell
    }
    else if(referredCoords.size() == 2){  // if there is a range
      output = sum(spreadsheet.getCellSection(referredCoords.get(0),referredCoords.get(1)));
    }
    else{  // no more than two arguments in sum range
      throw new IllegalArgumentException("Illegal number of arguments");
    }
    return output;
  }

  @Override
  public double evaluateCellProduct(List<Formula> formulas) throws IllegalArgumentException {
    double output;
    if(referredCoords.size() == 1) { // if there is only one reference  // eval the given cell
      output = spreadsheet.getCellAt(referredCoords.get(0)).evaluateCellProduct(formulas);
    }
    else if(referredCoords.size() == 2){ // if there is a range
      output = product(getListFormula());
    }
    else{ // no more than two arguments in sum range
      throw new IllegalArgumentException("Illegal number of references.");
    }
    return output;
  }

  @Override
  public double evaluateCellSqrt() throws IllegalArgumentException {
    double output;
    if(referredCoords.size() == 1) { // if there is only one reference  // eval the given cell
      output = spreadsheet.getCellAt(referredCoords.get(0)).evaluateCellSqrt();
    }
    else{ // no more than one argument for sqrt
      throw new IllegalArgumentException("Illegal number of references.");
    }
    return output;
  }

  @Override
  public double evaluateCellDifference() throws IllegalArgumentException {
    double output;
    if(referredCoords.size() == 1) { // if there is only one reference  // eval the given cell
      output = spreadsheet.getCellAt(referredCoords.get(0)).evaluateCellDifference();
    }
    else{ // no more than one argument for difference
      throw new IllegalArgumentException("Illegal number of references.");
    }
    return output;
  }

  @Override
  public double evaluateCellComparison() {
    double output;
    if(referredCoords.size() == 1) { // if there is only one reference  // eval the given cell
      output = spreadsheet.getCellAt(referredCoords.get(0)).evaluateCellComparison();
    }
    else{ // no more than one argument for comparison
      throw new IllegalArgumentException("Illegal number of references.");
    }
    return output;
  }

  @Override
  public String evaluateCellHamilton() {
    String output;
    if(referredCoords.size() == 1) { // if there is only one reference  // eval the given cell
      output = spreadsheet.getCellAt(referredCoords.get(0)).evaluateCellHamilton();
    }
    else{ // no more than one argument for Hamilton
      throw new IllegalArgumentException("Illegal number of references.");
    }
    return output;
  }



  @Override
  public boolean isNum() {
    Boolean output = false;

    if(referredCoords.size() == 1) { // if there is only one reference  // eval the given cell
      output = spreadsheet.getCellAt(referredCoords.get(0)).isNum();
    }
    else{ // no more than one argument for Hamilton
      ArrayList<Cell> cells = spreadsheet.getCellSection(referredCoords.get(0),referredCoords.get(1));
      // going through to see if each is a number
      for(int i = 0; i < cells.size(); i++){
        if(cells.get(i).isNum()){ // checking if any are numbers
          output = true;
        }
      }
    }

    return output;
  }

  @Override
  public boolean isRef() {
    return true;
  }

  @Override
  public boolean isFunction() {
    return false;
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
  public String toString(){
    return this.evaluateCell().toString();
  }

  @Override
  public int hashCode() {
    return symbol.hashCode();
  }

  /**
   * This is a helper that gets the cell that is being referred to.
   * Sets the referred coordinates variable of the class and returns the list of symbols.
   */
  private void parseReferredCells(){
    ArrayList<String> symbolsEntered = new ArrayList<>();
    if(symbol.contains(":")){ // determines if multiple
      int splitIndex = symbol.lastIndexOf(":"); // finds where to split
      String firstSymbol = symbol.substring(0,splitIndex); // splits first half
      String secondSymbol = symbol.substring(splitIndex + 1); // splits second half
      symbolsEntered.add(firstSymbol); // adds first
      symbolsEntered.add(secondSymbol); // adds second
    }
    else{
      symbolsEntered.add(symbol);
    }

    for(String s: symbolsEntered) {
      Scanner scan = new Scanner(s);
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
        colOver = col;
        rowOver = row;
      }
      assert coord1 != null;
      referredCoords.add(coord1);
    }
  }

  /**
   * This is a helper that gets the cell that is being referred to.   // CHANGE TO LIST OF REFERRED CELLS
   * @return the cells corresponding to the given references
   */
  public Cell getReferredCell(){


    return null;
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
   * Gets a list of the formulas in the referred cells.
   *
   * @return the multiplied value of the cells
   */
  private List<Formula> getListFormula() {
    List<Formula> formulas = new ArrayList<>();

    // if there is only one reference then only one referred to cell
    if(referredCoords.size() == 1 &&
            spreadsheet.getCellAt(referredCoords.get(0)) instanceof Formula){
      formulas.add((Formula) spreadsheet.getCellAt(referredCoords.get(0))); //set the value for cells
    }
    // if there are two coordinates in the list go through and get all those lists
    else if(referredCoords.size() == 2) {
      // coordinates first cell
      int rowOne = referredCoords.get(0).row;
      int rowTwo = referredCoords.get(1).row;

      // coordinates last cell
      int colOne = referredCoords.get(0).col;
      int colTwo = referredCoords.get(1).col;
      // going through the section to get all the formulas
      for (int i = rowOne; i <= rowTwo; i++){
        for(int j = colOne; j <= colTwo; j++){
          Coord cellCoord = new Coord(j,i);
          Cell cellRef = spreadsheet.getCellAt(cellCoord);
          // add to the list of formulas
          if(cellRef instanceof Formula){
            formulas.add((Formula) cellRef);
          }
        }
      }
    }
    return formulas;
  }
}
