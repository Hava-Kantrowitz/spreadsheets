package edu.cs3500.spreadsheets.view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import edu.cs3500.spreadsheets.model.Blank;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Spreadsheet;

/**
 * This is the class for the textual representation of a spreadsheet.
 * The purpose of this view is to take a model and render a text file for it to be saved as.
 */
public class SpreadsheetTextualView implements SpreadsheetView {
  private Spreadsheet sheet;
  private PrintWriter outputFile;

  /**
   * The constructor to create an instance of the textual view.
   * @param sheet the model that will be rendered to the view
   * @param outputFile the file in which the view will be saved as
   */
  public SpreadsheetTextualView(Spreadsheet sheet, PrintWriter outputFile){
    this.sheet = sheet;
    this.outputFile = outputFile;
  }

  @Override
  public void render(){
    // goes through the rows and columns to make the sheet

    for (int row = 1; row <= sheet.getNumRows(); row++) {    // starting at 1 b/c sheet 1 index based
      for (int col = 1; col <= sheet.getNumCols(); col++) {
        Coord currCoord = new Coord(col, row);
        Cell currCell = sheet.getCellAt(currCoord);
        if (!(currCell instanceof Blank)) {   // SWITCH THIS INSTANCE OF TO AN IS BLANK BUT NEED TO ADD THAT TO THE CELL INTERFACE
          // adds the coordinate to the output file
          outputFile.append(currCoord.toString() + " ");   // writing the coordinate to file
          outputFile.append(currCell.getRawContents() + "\n"); // writing the raw contents
        }

      }
    }
    outputFile.close();  // closes the file once written to
  }

  public Appendable getOutputFile(){
    return outputFile;
  }
}
