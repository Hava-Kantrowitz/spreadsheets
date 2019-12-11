package edu.cs3500.spreadsheets.view;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Set;


import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Spreadsheet;

/**
 * This is the class for the textual representation of a spreadsheet. The purpose of this view is to
 * take a model and render a text file for it to be saved as.
 */
public class SpreadsheetTextualView implements SpreadsheetView {
  private Spreadsheet sheet;
  private PrintWriter outputFile;

  /**
   * The constructor to create an instance of the textual view.
   *
   * @param sheet      the model that will be rendered to the view
   * @param outputFile the file in which the view will be saved as
   */
  public SpreadsheetTextualView(Spreadsheet sheet, PrintWriter outputFile) {
    this.sheet = sheet;
    this.outputFile = outputFile;
  }

  @Override
  public void render() {
    // gets the list of the coordinates in the given model
    Set<Coord> coords = sheet.getListCoords();

    for (Coord c : coords) {
      outputFile.append(c.toString() + " "); // append the coordinate and a space
      outputFile.append(sheet.getCellAt(c).getRawContents() + "\n");
    }

    // go through the list of rows that have changed
    HashMap<Integer, Integer> rowsChanged = sheet.getChangedRows();
    for (Integer row : rowsChanged.keySet()) {
      outputFile.append(row + "R " + rowsChanged.get(row) + "\n");
    }

    // go through the list of cols that have changed
    HashMap<Integer, Integer> colsChanged = sheet.getChangedCols();
    for (Integer col : colsChanged.keySet()) {
      outputFile.append(col + "C " + colsChanged.get(col) + "\n");
    }

    outputFile.close();  // closes the file once written to
  }

  @Override
  public void updateTextField(String newText) {
    // Nothing should happen here because there is no text view to be updated
  }

  @Override
  public String getTextField() {
    return "";
  }

  @Override
  public void setCellAt(String val, int row, int col) {
    // Nothing should happen here because the cells are all already set
  }

  @Override
  public void displayFileError() {
    // nothing should happen here because an error from a file cannot occur
  }

  @Override
  public void highlight() {
    // nothing should happen here because cells cannot be selected
  }

  @Override
  public void changeRowSize(int row, Integer newSize) {
    // nothing should happen here because the rows will always have an accurate size
  }

  @Override
  public void changeColSize(int col, Integer newSize) {
    // nothing should happen here because the columns will always have an accurate size
  }

}
