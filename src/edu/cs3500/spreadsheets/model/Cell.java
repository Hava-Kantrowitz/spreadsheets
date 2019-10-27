package edu.cs3500.spreadsheets.model;

/**
 * Models a single cell within the current spreadsheet.
 */
public interface Cell {

  /**
   * Evaluates the current cell. As it is with no methods being called.
   * @return the contents of the given cell
   */
  Value evaluateCell();


}
