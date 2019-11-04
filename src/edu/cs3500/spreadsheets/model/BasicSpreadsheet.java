package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

import edu.cs3500.spreadsheets.sexp.CreatorVisitor;
import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;

/**
 * This class models a basic spreadsheet.
 */
public class BasicSpreadsheet implements Spreadsheet {

  private static final int MAXINT = 2147483647;
  private ArrayList<ArrayList<Cell>> sheet;
  private int numRows;
  private int numCols;
  public ArrayList<String> badReferences = new ArrayList<>();

  @Override
  public void initializeSpreadsheet(Readable fileName) {
    sheet = new ArrayList<ArrayList<Cell>>();   // setting the values back to zero
    numRows = 0;
    numCols = 0;
    // calling the builder to actually initialize
    WorksheetBuild builder = new WorksheetBuild(this);
    WorksheetReader.read(builder, fileName);  // reading from the file and passing it in
  }


  @Override
  public Cell getCellAt(Coord coord) {
    int givenCol = coord.col - 1;
    int givenRow = coord.row - 1;

    // checking if it is greater than the number of columns (1 based index)
    // expand the board to fit
    if (givenCol >= numCols || givenRow >= numRows) {
      expandSheet(givenCol, givenRow);
    }

    return sheet.get(givenRow).get(givenCol);
  }

  @Override
  public void setCellAt(Coord coord, Cell cellVal) {
    int givenCol = coord.col - 1;  // adjust for the 1 based indexing
    int givenRow = coord.row - 1;

    // checking if it is greater than the number of columns (1 based index)
    // expand the board to fit
    if (givenCol >= numCols || givenRow >= numRows) {
      expandSheet(givenCol, givenRow);
    }
    // get the given row then set the column of that row
    sheet.get(givenRow).set(givenCol, cellVal);

  }

  @Override
  public void setCellAt(Coord coord, String rawContents) {
    String contentCopy = rawContents;
    char[] arrayForm = rawContents.toCharArray();

    // checking if it is a formula to get only the s expression
    if (arrayForm[0] == '=') {
      rawContents = rawContents.substring(1);
    }

    //create visitor and parse the raw contents of the added cell
    SexpVisitor visit = new CreatorVisitor(this);
    Cell addedCell = (Cell) Parser.parse(rawContents).accept(visit, contentCopy);

    setCellAt(coord, addedCell);

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

    // checking if it is greater than the number of columns (1 based index) or rows for the
    // second input, expand the board to fit if needed
    if (givenCol2 >= numCols || givenRow2 >= numRows) {
      expandSheet(givenCol2, givenRow2);
    }

    ArrayList multRows = new ArrayList<>();

    for (int i = givenRow1; i <= givenRow2; i++) {
      for (int j = givenCol1; j <= givenCol2; j++) {
        multRows.add(sheet.get(i).get(j));
      }

    }


    return multRows;
  }


  @Override
  public void evaluateSheet() throws IllegalArgumentException {
    int rows = numRows;
    int cols = numCols;

    for (int i = 1; i < rows; i++) {
      for (int j = 1; j < cols; j++) {
        Coord currCor = new Coord(j, i);
        try {
          getCellAt(currCor).evaluateCell();
        } catch (StackOverflowError e) {
          //if it overflows, there is a self reference within the given cell
          //add the cell to a list of bad references
          badReferences.add(currCor.toString());
          throw new IllegalArgumentException("There is a self reference in cell "
                  + currCor.toString());
        }


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


  /**
   * This is the method to expand the spreadsheet when the coordinates selected are out of bounds.
   * If the column is out of bounds then expand column wise, same with row. If both are out of
   * bounds expand in both directions. When new cells are added they should be blank.
   *
   * @param inputCol the column that was input
   * @param inputRow the row that was input
   */
  private void expandSheet(int inputCol, int inputRow) {

    //first check if the requested cell is out of reasonable bounds
    if (inputCol >= MAXINT || inputRow >= MAXINT) {
      throw new IllegalArgumentException("Requested spreadsheet exceeds available space");
    }
    else {
      int oldSheetSize = numRows;
      sheet.ensureCapacity(inputRow + 1);
      numRows = sheet.size();


      for (int i = oldSheetSize; i <= inputRow + 1; i++) {  // fill in the number of rows needed
        sheet.add(new ArrayList<Cell>());       // rows from the old number of rows to the new
        numRows++;
      }

      int newCols = numCols;

      if (inputCol > numCols) {
        newCols = inputCol;
      }


      for (int i = 0; i < numRows; i++) {        // all rows
        for (int j = 0; j <= newCols; j++) {  // go through from where
          sheet.get(i).add(new Blank());
          if (j > numCols && i == 0) {
            numCols++;
          }

        }
      }

    }


  }

  @Override
  public boolean equals(Object otherSheet) {
    boolean isEqual = false;

    if (otherSheet instanceof
            BasicSpreadsheet && ((BasicSpreadsheet) otherSheet).sheet.equals(this.sheet)) {
      isEqual = true;
    }

    return isEqual;
  }

  @Override
  public int hashCode() {
    return numRows + 1;
  }


}
