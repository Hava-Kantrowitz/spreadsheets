package edu.cs3500.spreadsheets.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

import edu.cs3500.spreadsheets.controller.EditableSheetController;
import edu.cs3500.spreadsheets.model.Coord;

/**
 * This is the class to respond to the click of the revert listener
 */
public class RevertActionListener implements ActionListener {

  private JTable sheet;
  private EditableSheetController controller;

  RevertActionListener(JTable sheet, EditableSheetController controller){
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
