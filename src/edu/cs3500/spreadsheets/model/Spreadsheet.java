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


}
