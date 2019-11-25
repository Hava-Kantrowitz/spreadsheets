package edu.cs3500.spreadsheets.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

import edu.cs3500.spreadsheets.controller.EditableSheetController;
import edu.cs3500.spreadsheets.model.Coord;


/**
 * This is the class of an action listener to alert the controller when the accept button
 * (accepting the changes in the text field) has been selected.
 */
public class AcceptActionListener implements ActionListener {

  private JTable sheet;
  private EditableSheetController controller;

  /**
   * This is the constructor for an an action listener for the accept button
   * @param sheet the JTable for the cell information
   * @param controller the controller for the view and model
   */
  public AcceptActionListener(JTable sheet, EditableSheetController controller){
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
      controller.onCellAffirmed(selectedCoord);
    }
  }
}
