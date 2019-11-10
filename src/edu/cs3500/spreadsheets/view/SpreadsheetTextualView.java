package edu.cs3500.spreadsheets.view;

import java.io.PrintWriter;
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

    outputFile.close();  // closes the file once written to
  }

}
