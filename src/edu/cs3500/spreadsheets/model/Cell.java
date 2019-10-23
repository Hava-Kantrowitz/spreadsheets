package edu.cs3500.spreadsheets.model;

/**
 * Models a single cell within the current spreadsheet.
 */
public interface Cell {

  /**
   * Evaluates the current cell.
   */
  Value evaluateCell();
}
