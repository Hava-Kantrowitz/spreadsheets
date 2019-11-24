package edu.cs3500.spreadsheets.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import edu.cs3500.spreadsheets.controller.EditableSheetController;
import edu.cs3500.spreadsheets.model.Coord;

public class DeleteListener implements KeyListener {

  private static final int DELETE_CODE = 127;

  private EditableSheetController controller;
  private NoEditTable sheet;

  DeleteListener(NoEditTable sheet, EditableSheetController controller){
    this.controller = controller;
    this.sheet = sheet;
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {

  }

  @Override
  public void keyReleased(KeyEvent e) {
    // checking if delete key was pressed
    if(e.getKeyCode() == DELETE_CODE){
      int modelCol = sheet.getSelectedColumn() + 1;
      int modelRow = sheet.getSelectedRow() + 1;

      // checking that the row and column are greater than zero
      if(modelCol > 0 && modelRow > 0){
        Coord selectedCoord = new Coord(modelCol, modelRow);
        controller.onCellDelete(selectedCoord);    // call the delete method
      }
    }
  }
}
