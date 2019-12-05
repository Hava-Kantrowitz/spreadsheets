package edu.cs3500.spreadsheets.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * This is the class that makes a read only version of the spreadsheet. Defaults to the observation
 * methods of a Basic Spreadsheet and does nothing when trying to mutate the data.
 */
public class SpreadsheetReadOnlyAdapter implements Spreadsheet {

  private Spreadsheet sheet;

  /**
   * The constructor for the given read only spreadsheet.
   *
   * @param sheet the given mutable spreadsheet
   */
  public SpreadsheetReadOnlyAdapter(Spreadsheet sheet) {
    this.sheet = sheet;
  }

  @Override
  public void initializeSpreadsheet(Readable fileName) {
    sheet.initializeSpreadsheet(fileName);
  }

  @Override
  public Cell getCellAt(Coord coord) {
    return sheet.getCellAt(coord);
  }

  @Override
  public void setCellAt(Coord coord, String rawContents) {
    // should not do anything here
  }

  @Override
  public void setCellAt(Coord coord, Cell cellVal) {
    // should not do anything here
  }

  @Override
  public ArrayList getCellSection(Coord upper, Coord lower) {
    return sheet.getCellSection(upper, lower);
  }

  @Override
  public void evaluateSheet() {
    sheet.evaluateSheet();
  }

  @Override
  public void evaluateCellAt(Coord coord) {
    sheet.evaluateCellAt(coord);
  }

  @Override
  public int getNumRows() {
    return sheet.getNumRows();
  }

  @Override
  public int getNumCols() {
    return sheet.getNumCols();
  }

  @Override
  public Set<Coord> getListCoords() {
    return sheet.getListCoords();
  }

  @Override
  public void addChangedRow(int rowToChange, int newHeight) {
    // should do nothing here because this modifies the model
  }

  @Override
  public void addChangedCol(int colToChange, int newWidth) {
    // should do nothing here because this modifies the model
  }

  @Override
  public HashMap<Integer, Integer> getChangedCols() {
    return sheet.getChangedCols();
  }

  @Override
  public HashMap<Integer, Integer> getChangedRows() {
    return sheet.getChangedRows();
  }
}
