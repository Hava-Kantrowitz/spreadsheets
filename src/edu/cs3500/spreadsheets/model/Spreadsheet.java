package edu.cs3500.spreadsheets.model;

import java.util.List;

/**
 * Models the representation of a basic spreadsheet.
 */
public interface Spreadsheet {


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
   * @param upper the coordinate of the upper bound.
   * @param lower the coordinate of the lower bound.
   * @return List of the inhabitants of the cells.
   */
  List<Cell> getCellSection(Coord upper, Coord lower);


  /**
   * Evaluates all cells within the sheet.
   */
  void evaluateSheet();

  /**
   * Evaluates a specified cell.
   * @param coord the coordinate of the cell to be evaluated
   */
  void evaluateCellAt(Coord coord);
}
