package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import edu.cs3500.spreadsheets.sexp.CreatorVisitor;
import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;

/**
 * This class models a basic spreadsheet.
 */
public class BasicSpreadsheet implements Spreadsheet {

  private HashMap<Coord, Cell> sheet;
  private int numRows;
  private int numCols;
  private ArrayList<String> badReferences = new ArrayList<>();

  private HashMap<Integer, Integer> changedRows = new HashMap<>();
  private HashMap<Integer, Integer> changedCols = new HashMap<>();

  /**
   * The empty constructor for a basic spreadsheet.
   */
  public BasicSpreadsheet() {
    // empty constructor ready to initialize the sheet
  }

  @Override
  public void initializeSpreadsheet(Readable fileName) {
    sheet = new HashMap<>();   // setting the values back to zero
    numRows = 0;
    numCols = 0;
    // calling the builder to actually initialize


    WorksheetBuild builder = new WorksheetBuild(this);
    WorksheetReader.read(builder, fileName);  // reading from the file and passing it in



  }


  @Override
  public Cell getCellAt(Coord coord) {
    return sheet.getOrDefault(coord, new Blank());
  }

  @Override
  public void setCellAt(Coord coord, Cell cellVal) {
    sheet.put(coord, cellVal);
  }

  @Override
  public void setCellAt(Coord coord, String rawContents) {
    String contentCopy = rawContents;
    char[] arrayForm = rawContents.toCharArray();
    Cell addedCell;

    // if the contents are an empty string set it to a blank cell
    if (arrayForm.length == 0) {
      sheet.remove(coord); // remove the element no longer in the set of occupied cells
    }
    // if are raw contents there that are not blank
    else {
      // checking if it is a formula to get only the s expression without equals
      if (arrayForm[0] == '=') {
        rawContents = rawContents.substring(1);
      }
      //create visitor and parse the raw contents of the added cell
      SexpVisitor visit = new CreatorVisitor(this);
      try {
        addedCell = (Cell) Parser.parse(rawContents).accept(visit, contentCopy);
      }
      // checking if expected a cell reference error in the sexp
      // so setting an error here
      catch (IllegalStateException e) {
        addedCell = new ErrorCell(new StringValue("#NAME?"), contentCopy);
      }
      // checking if there is an invalid sexp
      catch (IllegalArgumentException e) {
        addedCell = new ErrorCell(new StringValue("#NAME?"), contentCopy);
      }
      setCellAt(coord, addedCell);  // setting the value of the cell
    }

  }

  @Override
  public ArrayList<Cell> getCellSection(Coord coord, Coord cord) {
    int givenCol1 = coord.col - 1;
    int givenRow1 = coord.row - 1;
    int givenCol2 = cord.col - 1;
    int givenRow2 = cord.row - 1;

    if (givenCol2 < givenCol1 || givenRow2 < givenRow1) {
      throw new IllegalArgumentException("Cannot find cell range");
    }

    ArrayList multRows = new ArrayList<>();

    for (int i = givenRow1; i <= givenRow2; i++) {
      for (int j = givenCol1; j <= givenCol2; j++) {
        Coord newCoord = new Coord(j + 1, i + 1);
        multRows.add(sheet.getOrDefault(newCoord, new Blank()));
      }
    }
    return multRows;
  }


  @Override
  public void evaluateSheet() throws IllegalArgumentException {

    for (Coord coord : sheet.keySet()) {
      try {
        sheet.getOrDefault(coord, new Blank()).evaluateCell();
      } catch (StackOverflowError e) {
        //if it overflows, there is a self reference within the given cell
        //add the cell to a list of bad references
        badReferences.add(coord.toString());
        throw new IllegalArgumentException("There is a self reference in cell. "
                + coord.toString());
      }
    }

  }

  @Override
  public void evaluateCellAt(Coord coord) throws IllegalArgumentException {
    getCellAt(coord).evaluateCell();
  }

  @Override
  public int getNumRows() {
    return numRows;
  }

  @Override
  public int getNumCols() {
    return numCols;
  }

  @Override
  public boolean equals(Object otherSheet) {
    boolean isEqual = false;
    BasicSpreadsheet otherS = null;
    Set otherListCoords = null;

    // checking that they are the same class
    if (otherSheet instanceof BasicSpreadsheet) {
      otherS = (BasicSpreadsheet) otherSheet;
      otherListCoords = otherS.getListCoords();
      // checking if the sheet has the correct number of cells
      if (otherS.getListCoords().size() == this.sheet.size()) {
        isEqual = true;
      }
    }
    // if the other sheet is an instance of basic spreadsheet
    // and has the same number of cells
    // now checking that all the cells are equal
    if (isEqual) {
      // going through the cells of the first sheet
      // gets all the coords of the first set
      Set<Coord> coords = this.getListCoords();
      // this goes through all the keys
      for (Coord c : coords) {
        // if the other sheet does not contain the key
        if (!otherListCoords.contains(c)) {
          isEqual = false;
        }
        // if the cell at the given coord does not equal the
        else if (!(otherS.getCellAt(c).equals(this.getCellAt(c)))) {
          isEqual = false;
        }
      }
    }

    return isEqual;
  }

  @Override
  public int hashCode() {
    return numRows + 1;
  }

  @Override
  public Set<Coord> getListCoords() {
    HashMap<Coord, Cell> copyMap = new HashMap<Coord, Cell>(); // creates the new hash map
    copyMap.putAll(this.sheet); // copies all of the mappings from this sheet to a new one
    return copyMap.keySet(); // returns the key set
  }

  /**
   * Method to return a copy of the bad references.
   *
   * @return the bad references in the given file
   */
  public ArrayList<String> getBadReferences() {
    ArrayList<String> outputList = new ArrayList<String>(badReferences.size());
    for (int i = 0; i < badReferences.size(); i++) {
      outputList.add("");
    }
    Collections.copy(outputList, this.badReferences);
    return outputList;
  }


  @Override
  public void addChangedRow(int rowToChange, int newHeight){
    // if the default size then remove
    if(newHeight == 16) {
      this.changedRows.remove(rowToChange);
    }
    // this sets the row to the changed value
    else{
      this.changedRows.put(rowToChange, newHeight);
    }

  }

  @Override
  public void addChangedCol(int colToChange, int newWidth){
    this.changedCols.put(colToChange, newWidth);
  }

  @Override
  public HashMap<Integer, Integer> getChangedRows(){
    HashMap<Integer, Integer> copyMap = new HashMap<>();
    copyMap.putAll(this.changedRows);
    return copyMap;
  }

  @Override
  public ArrayList<Cell> getCellColumn(int column) {
    ArrayList<Cell> coordsInCol = new ArrayList<>();
    for (Coord c : sheet.keySet()) {
      if (c.col == column) {
        coordsInCol.add(sheet.get(c));
      }
    }

    return coordsInCol;

  }

  @Override
  public ArrayList<Cell> getMultipleColumns(int rightBound, int leftBound) {
    ArrayList<Cell> coordsInCols = new ArrayList<>();
    for (int i = rightBound; i <= leftBound; i++) {
      for (Coord c : sheet.keySet()) {
        if (c.col == i) {
          coordsInCols.add(sheet.get(c));
        }
      }
    }
    return coordsInCols;
  }

  @Override
  public HashMap<Integer, Integer> getChangedCols(){
    HashMap<Integer, Integer> copyMap = new HashMap<>();
    copyMap.putAll(this.changedCols);
    return copyMap;
  }


}
