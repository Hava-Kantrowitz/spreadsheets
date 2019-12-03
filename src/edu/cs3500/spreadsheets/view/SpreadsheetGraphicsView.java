package edu.cs3500.spreadsheets.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.AdjustmentListener;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import edu.cs3500.spreadsheets.model.Spreadsheet;
import edu.cs3500.spreadsheets.model.SpreadsheetReadOnlyAdapter;

/**
 * Models the GUI view of the spreadsheet to allow the user to view the data of a given model
 * without the ability to edit it.
 */
public class SpreadsheetGraphicsView extends JFrame implements SpreadsheetView {

  /**
   * Constructs an instance of the GUI spreadsheet view.
   *
   * @param model the model to render
   */
  public SpreadsheetGraphicsView(Spreadsheet model) {

    super();

    this.setTitle("Spreadsheet");
    this.setSize(1000, 1000);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    SpreadsheetTable table = new SpreadsheetTable(model);

    //use a borderlayout with drawing panel in center and button panel in south
    this.setLayout(new BorderLayout());
    JTable sheet = table.getTable();
    sheet.setGridColor(Color.black);
    sheet.getTableHeader().setReorderingAllowed(false);
    sheet.setRowSelectionAllowed(false);
    sheet.setColumnSelectionAllowed(false);

    sheet.setGridColor(Color.BLACK);

    sheet.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    JScrollPane scrollPane = new JScrollPane();
    SpreadsheetRowHeaderTable rows = new SpreadsheetRowHeaderTable(model);
    JTable myRows = rows.getTable();
    myRows.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    JScrollPane rowScroller = new JScrollPane();
    scrollPane.getViewport().add(sheet);
    rowScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
    rowScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    rowScroller.getViewport().setPreferredSize(new Dimension(50, 4800));
    rowScroller.getViewport().add(myRows);


    // listener takes in the scroll bar and the default table
    // so that it will change what is displayed
    AdjustmentListener scrollListener = new HorizontalScrollListener(sheet, table.getModel(),
            myRows);
    AdjustmentListener vertScrollListener
            = new VerticalScrollListener(table.getModel(), (DefaultTableModel) myRows.getModel(),
            rowScroller.getVerticalScrollBar());


    scrollPane.getHorizontalScrollBar().addAdjustmentListener(scrollListener);
    scrollPane.getVerticalScrollBar().addAdjustmentListener(vertScrollListener);
    rowScroller.getVerticalScrollBar().addAdjustmentListener(vertScrollListener);



    this.add(rowScroller, BorderLayout.WEST);
    this.add(scrollPane, BorderLayout.CENTER);

  }

  @Override
  public void render() {
    this.setVisible(true);

  }

  @Override
  public void updateTextField(String newText) {
    // Nothing should happen here because the text field is always "updated" because there is none
  }

  @Override
  public String getTextField() {
    return "";
  }

  @Override
  public void setCellAt(String val, int row, int col) {
    // Nothing should happen here because this view is not editable
  }

  @Override
  public void displayFileError() {
    // There cannot be a file error so nothing can go wrong
  }

  @Override
  public void highlight() {
    // nothing should happen here because the cells cannot be edited
  }
}
