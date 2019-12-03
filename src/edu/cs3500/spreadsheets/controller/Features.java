package edu.cs3500.spreadsheets.controller;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.view.SpreadsheetEditableView;

/**
 * This is the interface for the features supported by the controller of an editable spreadsheet
 * view and corresponding model. The features are included as part of the controller because
 * the methods represent changes that affect both the model and the view.
 */
public interface Features {


  /**
   * This method is called when the contents of a given cell should be changed. The change sets the
   * cell in the model and the view. It also reflects any changes this has on any cells that refer
   * to the given cell.
   * @param coord the coordinate of the given cell
   */
  void onCellAffirmed(Coord coord);

  /**
   * This method is called when the given cell contents are set back to the original contents.
   * The text field changes are not applied to the model and the view representation is
   * reverted to its previous contents.
   * @param coord the coordinate of the given cell
   */
  void onCellReverted(Coord coord);

  /**
   * Method to populate the text field of the view with the raw contents of the selected cell.
   * @param coord the coordinate of the cell that has been selected
   */
  void onCellSelected(Coord coord);

  /**
   * Method to delete the contents of the selected cell. The selected cell is removed from the model
   * and the changes are reflected in the visual representation.
   * @param coord the coordinate of the selected cell
   */
  void onCellDelete(Coord coord);


  /**
   * Method to activate the new table when the load is selected. This sets the old view so that it
   * is no longer active and replaces the model with model represented by the given file.
   * @param filePath the given file path for the textual representation of the new view
   */
  void onLoadSelect(String filePath);

  /**
   * This is the method called to save the data in the view to the given file.
   * @param filePath the given file path to save the file to
   */
  void onSaveSelect(String filePath);


  /**
   * Sets the view of the controller so that the controller and view can be constructed because the
   * controller takes in a view and the editable view takes in a controller.
   * @param view the new view to be set
   */
  void setView(SpreadsheetEditableView view);
}
