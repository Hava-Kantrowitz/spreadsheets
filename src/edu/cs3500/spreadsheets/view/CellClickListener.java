package edu.cs3500.spreadsheets.view;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.cs3500.spreadsheets.controller.EditableSheetController;
import edu.cs3500.spreadsheets.model.Coord;

/**
 * This is the listener for every time a cell is selected.
 */
public class CellClickListener implements ListSelectionListener {

  JTable sheet;
  EditableSheetController controller;

  /**
   * The constructor for the cell click listener.
   * @param sheet the given JTable being listened to
   */
  public CellClickListener(JTable sheet, EditableSheetController controller){

    this.sheet = sheet;
    this.controller = controller;
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    // changing the coordinates to the model specified
    int modelCol = sheet.getSelectedColumn() + 1;
    int modelRow = sheet.getSelectedRow() + 1;

    // checking for valid coordinates
    if(modelCol > 0 && modelRow > 0){
      Coord selectedCoord = new Coord(modelCol, modelRow);
      this.controller.onCellSelected(selectedCoord);
    }
  }
}
