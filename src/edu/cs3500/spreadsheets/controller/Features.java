package edu.cs3500.spreadsheets.controller;

import edu.cs3500.spreadsheets.model.Coord;

/**
 * This is the interface for the features supported by the controller.
 */
public interface Features {


  /**
   * This method is called when the cell contents are changed.
   * Setting the cell in the model.
   * Updating the cell in the view.
   * Updating all cells that rely on the given cell.
   */
  void onCellAffirmed(Coord coord);

  /**
   * This method is called when the cell contents are set back to the original contents.
   * Do not accept changes to the model.
   * Reset the view to the previous contents.
   */
  void onCellReverted(Coord coord);

  /**
   * Method to populate the text field of the view when a cell is selected.
   */
  void onCellSelected(Coord coord);

  /**
   * Method to delete the contents of the given cell.
   */
  void onCellDelete(Coord coord);


  /**
   * Method to activate the new table when the load is selected.
   */
  void onLoadSelect(String filePath);

  /**
   * This is the method called to save the file.
   * @param filePath the given file path to save the file to
   */
  void onSaveSelect(String filePath);
}
