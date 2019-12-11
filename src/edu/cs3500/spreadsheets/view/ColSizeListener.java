package edu.cs3500.spreadsheets.view;


import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.TableColumn;

import edu.cs3500.spreadsheets.controller.Features;


/**
 * This is a class representing a listener to detect the resizing of columns in the view of the
 * spreadsheet with the purpose of alerting the controller of the change.
 */
public class ColSizeListener implements TableColumnModelListener {


  private NoEditTable viewGrid;
  private Features controller;

  /**
   * This is the constructor for the column resizing listener.
   *
   * @param viewGrid   the grid (JTable) component of the view
   * @param controller the controller for the program
   */
  public ColSizeListener(NoEditTable viewGrid, Features controller) {
    this.viewGrid = viewGrid;
    this.controller = controller;
  }

  @Override
  public void columnAdded(TableColumnModelEvent e) {
    // nothing happens when a column is added
  }

  @Override
  public void columnRemoved(TableColumnModelEvent e) {
    // nothing happens when a column is removed
  }

  @Override
  public void columnMoved(TableColumnModelEvent e) {
    // nothing happens when a column is moved
  }

  @Override
  public void columnMarginChanged(ChangeEvent e) {
    // get the column that is being changed
    TableColumn changedCol = viewGrid.getTableHeader().getResizingColumn();
    // check to make sure it is not null
    if (changedCol != null) {
      // get the index
      int colIndex = changedCol.getModelIndex();
      int newSize = viewGrid.getColumnModel().getColumn(colIndex).getWidth();
      // making the change in the controller
      controller.onColumnResized(colIndex, newSize);
    }


  }

  @Override
  public void columnSelectionChanged(ListSelectionEvent e) {
    // nothing happens when the column selected changes
  }
}
