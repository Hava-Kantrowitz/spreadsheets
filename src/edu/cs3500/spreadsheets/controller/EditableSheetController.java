package edu.cs3500.spreadsheets.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.ErrorCell;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import edu.cs3500.spreadsheets.model.StringValue;
import edu.cs3500.spreadsheets.view.SpreadsheetEditableView;
import edu.cs3500.spreadsheets.view.SpreadsheetTextualView;


/**
 * Represents a controller for the editable version of the spreadsheet views. The purpose is to
 * enable the connection (when changes are made) between the editable view and a given model.
 */
public class EditableSheetController implements Features {

  private SpreadsheetEditableView view;
  private Spreadsheet model;

  /**
   * This is the constructor for the controller of the editable view.
   *
   * @param view  the given spreadsheet view
   * @param model the given spreadsheet model
   */
  public EditableSheetController(SpreadsheetEditableView view, Spreadsheet model) {
    this.view = view;
    this.model = model;
  }


  @Override
  public void onCellAffirmed(Coord coord) {
    try {
      model.setCellAt(coord, view.getTextField());  // sets the cell in the model
      // sets within the view (subtracting 1 because column based)
      // setting to the now evaluated value of the cells
      view.setCellAt(model.getCellAt(coord).toString(), coord.row - 1, coord.col - 1);
    }
    // checking for error in evaluation of the cell being set
    // error checking for parser is done in the model set cell at
    catch (IllegalArgumentException e) {
      view.setCellAt("#VALUE!", coord.row - 1, coord.col - 1);
      // setting the given cell to an error cell (if problem not caused by error cell)
      // preserves cells that are set with reference to an error
      if (e.getMessage() != null && !e.getMessage().contains("Error in cell.")) {
        model.setCellAt(coord, new ErrorCell(new StringValue("#VALUE!"), view.getTextField()));
      }

    }


    for (Coord c : model.getListCoords()) {
      try {
        view.setCellAt(model.getCellAt(c).toString(), c.row - 1, c.col - 1);
      }
      // catching if there was an error in the evaluation because a cell referred to has an error
      catch (IllegalArgumentException | StackOverflowError e) {
        // not setting this cell to an error in model but showing error caused by previous cell
        view.setCellAt("#VALUE!", c.row - 1, c.col - 1);
      }

    }

  }

  @Override
  public void onCellReverted(Coord coord) {
    // reverting the text field back to the raw contents and not changing anything about the cell
    // in the view or model
    view.updateTextField(model.getCellAt(coord).getRawContents());
  }

  @Override
  public void onCellSelected(Coord modelCoord) {
    this.view.highlight();
    this.view.updateTextField(model.getCellAt(modelCoord).getRawContents()); // update text field
  }

  @Override
  public void onCellDelete(Coord coord) {
    // set the text field
    this.view.updateTextField("");

    // set the view and model as if on cell affirmed (cause references to change)
    this.onCellAffirmed(coord);
  }

  @Override
  public void onLoadSelect(String filePath) {

    BasicSpreadsheet newSheet = new BasicSpreadsheet();
    try {
      newSheet.initializeSpreadsheet(new FileReader(filePath));
      this.view.setVisible(false); // close the current view
      EditableSheetController newController = new EditableSheetController(null, newSheet);
      SpreadsheetEditableView newView =
              new SpreadsheetEditableView(newSheet, newController); // setting up the view
      newController.setView(newView);

      newView.render(); // display the new view
    } catch (FileNotFoundException e) {
      // show error if unable to load
      this.view.displayFileError();
    }
  }

  @Override
  public void onSaveSelect(String filePath) {

    try {
      // get the print writer form of the file
      PrintWriter fileToSaveTo = new PrintWriter(filePath);
      // create the textual view
      SpreadsheetTextualView savedFile = new SpreadsheetTextualView(this.model, fileToSaveTo);
      // actually save the file
      savedFile.render();
    } catch (IOException e) {
      // show error if unable to save
      view.displayFileError();
    }
  }

  @Override
  public void setView(SpreadsheetEditableView view) {
    this.view = view;
  }

  @Override
  public void onRowResized(int changedRow, int newHeight) {
    // update the row size in the model (adding 1 so it is in terms of the model)
    model.addChangedRow(changedRow + 1, newHeight);
  }

  @Override
  public void onScroll() {
    // setting the correct row sizes
    // get the rows that are changed
    HashMap<Integer, Integer> changedRows = model.getChangedRows();


    // going through the rows and setting the sizes in the view
    for (Integer row : changedRows.keySet()) {
      // updating appearance in the view (with negative 1 adjustment to view parameters)
      view.changeRowSize(row - 1, changedRows.get(row));
    }

    // get the columns that are changed
    HashMap<Integer, Integer> changedCols = model.getChangedCols();
    // going through the rows and setting the sizes in the view
    for (Integer col : changedCols.keySet()) {
      // updating appearance in the view (with negative 1 adjustment to view parameters)
      view.changeColSize(col - 1, changedCols.get(col));
    }

  }

  @Override
  public void onColumnResized(int changedCol, int newWidth) {
    // adds the changed column with the adjustment for the model representation
    model.addChangedCol(changedCol + 1, newWidth);
  }
}
