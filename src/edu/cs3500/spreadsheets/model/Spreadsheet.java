package edu.cs3500.spreadsheets.model;

import java.util.List;

/**
 * Models the representation of a basic spreadsheet.
 */
public interface Spreadsheet {

  /**
   * This method initializes the spreadsheet to an empty grid of rows and columns.
   */
  void initSheet();

  /**
   * Gets the cell at the given coordinate.
   * If the given cell is outside the size of the board, the board will be resized
   * until it can be accommodated.
   * @param coord The x-y coordinate of the given cell
   * @return the inhabitant of the cell
   */
  Cell getCellAt(Coord coord);

  /**
   * Sets the cell at the given coordinate to the given inhabitant.
   * If the given cell is outside the size of the board, the board will be resized
   * until it can be accommodated.
   * @param coord The x-y coordinate of the given cell
   * @param cellVal The wanted inhabitant of the cell
   */
  void setCellAt(Coord coord, Cell cellVal);

  /**
   * Gets the cells at the given coordinates.
   * If the given cells are outside the size of the board, the board will be resized
   * until they can be accommodated.
   * @param coords The list of x-y coordinates of the given cells.
   * @return List of the inhabitants of the cells.
   */
  List<Cell> getCellSection(Coord...coords);

  /**
   * Doubles the board in both x and y directions.
   */
  void expandSheet();

  /**
   * Evaluates all cells within the sheet.
   */
  void evaluateSheet();
}
