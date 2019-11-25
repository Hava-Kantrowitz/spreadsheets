package edu.cs3500.spreadsheets.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

import edu.cs3500.spreadsheets.controller.EditableSheetController;
import edu.cs3500.spreadsheets.model.Coord;

/**
 * Represents an action listener for the revert button component of the application with the
 * purpose of alerting the controller that the cell should be reverted.
 */
public class RevertActionListener implements ActionListener {

  private JTable sheet;
  private EditableSheetController controller;

  /**
   * This is the constructor for the revert action listener.
   * @param sheet the JTable for reference to the current cell
   * @param controller the controller for connection with the model
   */
  public RevertActionListener(JTable sheet, EditableSheetController controller){
    this.sheet = sheet;
    this.controller = controller;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    int modelCol = sheet.getSelectedColumn() + 1;
    int modelRow = sheet.getSelectedRow() + 1;

    // checking that the row and column are greater than zero
    if(modelCol > 0 && modelRow > 0){
      Coord selectedCoord = new Coord(modelCol, modelRow);
      controller.onCellReverted(selectedCoord);
    }
  }
}
