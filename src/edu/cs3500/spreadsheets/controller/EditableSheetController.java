package edu.cs3500.spreadsheets.controller;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.ErrorCell;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import edu.cs3500.spreadsheets.model.StringValue;
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
    try {
      model.setCellAt(coord, view.getTextField());  // sets the cell in the model
      // sets within the view (subtracting 1 because column based)
      // setting to the now evaluated value of the cells
      view.setCellAt(model.getCellAt(coord).toString(), coord.row - 1, coord.col - 1);
    }
    // checking for error in evaluation of the cell being set
    // error checking for parser is done in the model set cell at
    catch(IllegalArgumentException e){
        view.setCellAt("#VALUE!", coord.row - 1, coord.col - 1);
        // setting the given cell to an error cell
        model.setCellAt(coord, new ErrorCell(new StringValue("#VALUE!"), view.getTextField()));
    }

    // still need to deal with self reference
    for (Coord c : model.getListCoords()) {
      try{
        view.setCellAt(model.getCellAt(c).toString(), c.row - 1, c.col - 1);       // THE toString has evaluate within we may want to change this tho so it is more explicit that we evaluate
      }
      // catching if there was an error in the evaluation because a cell referred to has an error
      catch(IllegalArgumentException e){
        // not setting this cell to an error in model but showing error caused by previous cell
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

  @Override
  public void onCellDelete(Coord coord) {
    // set the value in the view (adjust for zero based)
    this.view.setCellAt("",coord.row - 1, coord.col - 1);

    // set in the model
    this.model.setCellAt(coord, "");

    // set the text field
    this.view.updateTextField("");
  }
}
