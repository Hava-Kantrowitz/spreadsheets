package edu.cs3500.spreadsheets.model;


import java.util.ArrayList;

public class BasicSpreadsheet implements Spreadsheet {

  private ArrayList<ArrayList<Cell>> sheet;
  private int numRows;
  private int numCols;

  /**
   * This is the blank constructor for a basic spreadsheet. Constructs a 10 by 10 spreadsheet
   * of blank cells.
   */
  public BasicSpreadsheet(){
    sheet = new ArrayList<>();  // initialize the overall array list

    // goes through the columns and rows
    for(int i = 0; i < 10; i++){
      sheet.add(new ArrayList<>());  // adds a new row (initialize each array list)
      for(int j = 0; j < 10; j++){
        // adds an element to the new row
        sheet.get(i).add(new Blank());
      }
    }

    numRows = 10;
    numCols = 10;

  }

  /**
   * This is the constructor that takes in a file and creates the model. Sets the num rows and cols.
   * @param fileName the name of the initial file.
   */
  public BasicSpreadsheet(String fileName){
    initializeSpreadsheet(fileName);
  }


  @Override
  public Cell getCellAt(Coord coord) {
    int givenCol = coord.col - 1;
    int givenRow = coord.row - 1;

    // checking if it is greater than the number of columns (1 based index)
    // expand the board to fit
    if(givenCol >= numCols || givenRow >= numRows){
      expandSheet(givenCol,givenRow);
    }

    return sheet.get(givenRow).get(givenCol);
  }

  @Override
  public void setCellAt(Coord coord, Cell cellVal) {
    int givenCol = coord.col - 1;  // adjust for the 1 based indexing
    int givenRow = coord.row - 1;

    // checking if it is greater than the number of columns (1 based index)
    // expand the board to fit
    if(givenCol >= numCols || givenRow >= numRows){
      expandSheet(givenCol,givenRow);
    }
    // get the given row then set the column of that row



    sheet.get(givenRow).set(givenCol,cellVal);
  }

  @Override
  public ArrayList getCellSection(Coord coord, Coord cord) {
    int givenCol1 = coord.col - 1;
    int givenRow1 = coord.row - 1;
    int givenCol2 = cord.col - 1;
    int givenRow2 = cord.row - 1;

    if (givenCol2 < givenCol1 || givenRow2 < givenRow1) {
      throw new IllegalArgumentException("Cannot find cell range");
    }

    // checking if it is greater than the number of columns (1 based index)
    // expand the board to fit
    if(givenCol1 >= numCols || givenRow1 >= numRows){
      expandSheet(givenCol1,givenRow1);
    }

    if (givenCol2 >= numCols || givenRow2 >= numRows) {
      expandSheet(givenCol1, givenRow1);
    }

    ArrayList multRows = null;

    for (int i = givenCol1; i <= givenCol2; i++) {
      for (int j = givenRow1; j < givenCol2; j++) {
        multRows.add(sheet.get(i).get(j));
      }

    }

    return multRows;
  }


  @Override
  public void evaluateSheet() {

    for (int i = 1; i <= numCols; i++) {
      for (int j = 1; j < numRows; j++) {
        Coord currCor = new Coord(numCols, numRows);
        getCellAt(currCor).evaluateCell();
      }
    }

  }

  @Override
  public void evaluateCellAt(Coord coord) throws IllegalArgumentException {
    getCellAt(coord).evaluateCell();
  }

  /**
   * This is the method to initialize the spreadsheet of the board from the file.
   * @param fileName the name of the given file
   */
  private void initializeSpreadsheet(String fileName){

  }

  /**
   * This is the method to expand the spreadsheet when the coordinates selected are out of bounds.
   * If the column is out of bounds then expand column wise, same with row.
   * If both are out of bounds expand in both directions.
   * When new cells are added they should be blank.
   * @param inputCol the column that was input
   * @param inputRow the row that was input
   */
  private void expandSheet(int inputCol, int inputRow) {

    sheet.ensureCapacity(inputCol);

    for (int i = 0; i < inputCol; i++) {
      sheet.get(i).ensureCapacity(inputRow);
    }

  }
}
