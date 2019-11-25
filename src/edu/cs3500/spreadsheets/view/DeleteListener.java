package edu.cs3500.spreadsheets.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import edu.cs3500.spreadsheets.controller.EditableSheetController;
import edu.cs3500.spreadsheets.model.Coord;

/**
 * Represents a key listener for the delete key with the purpose of alerting the controller to
 * perform the corresponding action.
 */
public class DeleteListener implements KeyListener {

  private EditableSheetController controller;
  private NoEditTable sheet;

  /**
   * This is the constructor for the delete key listener.
   * @param sheet the JTable for the cell information
   * @param controller the controller for the view and model of the application
   */
  public DeleteListener(NoEditTable sheet, EditableSheetController controller){
    this.controller = controller;
    this.sheet = sheet;
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // No functionality needed at this time when key is typed
  }

  @Override
  public void keyPressed(KeyEvent e) {
    // No functionality needed at this time when key is pressed
  }

  @Override
  public void keyReleased(KeyEvent e) {

    // checking if delete key was pressed (which is backspace on Mac)
    if(e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
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
