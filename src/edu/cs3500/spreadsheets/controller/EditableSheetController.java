package edu.cs3500.spreadsheets.controller;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import edu.cs3500.spreadsheets.view.SpreadsheetEditableView;

public class EditableSheetController implements Features {

  private SpreadsheetEditableView view;
  private Spreadsheet model;

  public EditableSheetController(SpreadsheetEditableView view, Spreadsheet model){
    this.view = view;
    this.model = model;
  }


  @Override
  public void onCellAffirmed(Coord coord) {
    model.setCellAt(coord, view.getTextField());  // sets the cell in the model
    // sets within the view (subtracting 1 because column based)
    // setting to the now evaluated value of the cells
    view.setCellAt(model.getCellAt(coord).toString(),coord.row - 1, coord.col - 1);
    //loop through whole sheet and reset
    model.evaluateSheet();
    for (Coord c : model.getListCoords()) {
      view.setCellAt(model.getCellAt(c).toString(), c.row - 1, c.col - 1);
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
