package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Models the representation of a basic spreadsheet.
 */
public interface Spreadsheet {

  /**
   * This is the method to initialize the spreadsheet of the board from the file.
   *
   * @param fileName the name of the given file
   */
  void initializeSpreadsheet(Readable fileName);

  /**
   * Gets the cell at the given coordinate. If the given cell is outside the size of the board, the
   * board will be doubled until it can be accommodated.
   *
   * @param coord The x-y coordinate of the given cell
   * @return the inhabitant of the cell
   */
  Cell getCellAt(Coord coord);


  /**
   * Sets the cell at the given coordinates to the raw contents given.
   * @param coord the x-y coordinate of the given cell
   * @param rawContents the wanted inhabitant of the cell in the form of a raw string
   */
  void setCellAt(Coord coord, String rawContents);

  /**
   * Sets the cell at the given coordinates to the cell given.
   * @param coord the x-y coordinate of the given cell
   * @param cellVal the wanted inhabitant of the cell in cell form
   */
  void setCellAt(Coord coord, Cell cellVal);

  /**
   * Gets the cells at the given coordinates. If the given cells are outside the size of the board,
   * the board will be resized until they can be accommodated.
   * @param upper the coordinate of the upper bound.
   * @param lower the coordinate of the lower bound.
   * @return List of the inhabitants of the cells.
   */
  ArrayList getCellSection(Coord upper, Coord lower);


  /**
   * Evaluates all cells within the sheet.
   */
  void evaluateSheet();

  /**
   * Evaluates a specified cell.
   * @param coord the coordinate of the cell to be evaluated
   */
  void evaluateCellAt(Coord coord);

  /**
   * To get the number of rows in the sheet.
   * @return the number of rows
   */
  int getNumRows();

  /**
   * To get the number of columns in the sheet.
   * @return the number of columns
   */
  int getNumCols();

  /**
   * This is the method to get the list of coordinates that have cells.
   * @return the set of occupied coordinates
   */
  Set<Coord> getListCoords();

  /**
   * This is to add a change in the height of a row to the model.
   * @param rowToChange the row that has changed height
   * @param newHeight the new height of the row
   */
  void addChangedRow(int rowToChange, int newHeight);


  /**
   * This is to add a change in the width of a column to the model.
   * @param colToChange the column that has changed
   * @param newWidth the new width of the column
   */
  void addChangedCol(int colToChange, int newWidth);


  /**
   * This is a getter for the column sizes that have changed from the default.
   * @return the HashMap of the columns and corresponding new sizes if they have changed
   */
  HashMap<Integer, Integer> getChangedCols();

  /**
   * This is a getter for the row sizes that have changed from the default.
   * @return the HashMap of the rows and corresponding new sizes if they have changed
   */
  HashMap<Integer, Integer> getChangedRows();

  /**
   * This gets all the cells in a column.
   * @param column the column to get cells in
   * @return list of all cells in the column
   */
  ArrayList getCellColumn(int column);


  /**
   * This gets multiple columns of the spreadsheet.
   * @param rightBound the rightmost column to get
   * @param leftBound the leftmost column to get
   * @return the list of cells in the columns
   */
  ArrayList<Cell> getMultipleColumns(int rightBound, int leftBound);
}
