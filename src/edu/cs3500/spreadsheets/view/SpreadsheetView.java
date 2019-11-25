package edu.cs3500.spreadsheets.view;

/**
 * This is the spreadsheet interface to specify the functionality of any given view of a
 * spreadsheet.
 */
public interface SpreadsheetView {

  /**
   * This is a function to render the given type of view.
   */
  void render();

  /**
   * This method ensures that the raw contents of the selected cell are displayed correctly (with
   * "correctly" differing based on the view implementation).
   * @param newText the contents to be displayed
   */
  void updateTextField(String newText);

  /**
   * This method returns the contents of the text field if there are any, otherwise an empty string
   * is returned.
   * @return the contents of the text field
   */
  String getTextField();

  /**
   * This method ensures that the cell at the given row and column is set to the intended value. If
   * the view is an uneditable version the current value may not be changed.
   * @param val the given value for the cell to be set to
   * @param row the row of the cell that should be set
   * @param col the column of the cell that should be set
   */
  void setCellAt(String val, int row, int col);

  /**
   * This method displays an alert if there is an error with the file path in load or save.
   */
  void displayFileError();

  /**
   *
   * @param numClicked
   */
  void highlight(int numClicked);

}
