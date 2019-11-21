package edu.cs3500.spreadsheets.controller;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import edu.cs3500.spreadsheets.view.SpreadsheetEditableView;

public class EditableSheetController implements Features {

  SpreadsheetEditableView view;
  Spreadsheet model;

  public EditableSheetController(SpreadsheetEditableView view, Spreadsheet model){
    this.view = view;
    this.model = model;
  }


  @Override
  public void onCellAffirmed(Coord coord) {
    try {
      model.setCellAt(coord, view.getTextField());  // sets the cell in the model
      // sets within the view (subtracting 1 because column based)
      // setting to the now evaluated value of the cells
      view.setCellAt(model.getCellAt(coord).evaluateCell().toString(),
              coord.row - 1, coord.col - 1);
    }
    catch(IllegalStateException e){
      // if the input to parse ws expecting a cell reference (strings no quotes)
      view.setCellAt("#NAME?", coord.row - 1, coord.col - 1);
    }
    catch(IllegalArgumentException e){
      // if there was a badly formatted sexp                                              // should we send a message to user or keep this
      if(e.getMessage().contains("Badly formatted sexp")){
        view.setCellAt("#NAME?", coord.row - 1, coord.col - 1);
      }
      // setting the cell to value if an illegal argument exception caught upon evaluation
      else {
        view.setCellAt("#VALUE!", coord.row - 1, coord.col - 1);
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
    this.view.updateTextField(model.getCellAt(modelCoord).getRawContents()); // update text field
  }
}
