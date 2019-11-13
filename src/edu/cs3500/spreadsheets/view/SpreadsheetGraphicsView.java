package edu.cs3500.spreadsheets.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.AdjustmentListener;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import edu.cs3500.spreadsheets.model.SpreadsheetReadOnlyAdapter;

/**
 * Models the GUI view of the spreadsheet.
 */
public class SpreadsheetGraphicsView extends JFrame implements SpreadsheetView {

  /**
   * Constructs an instance of the GUI spreadsheet view.
   * @param model the model to render
   */
  public SpreadsheetGraphicsView(Spreadsheet model) {

    super();

    this.setTitle("Spreadsheet");
    this.setSize(1000, 1000);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    SpreadsheetReadOnlyAdapter modelRead = new SpreadsheetReadOnlyAdapter(model);
    SpreadsheetTable table = new SpreadsheetTable(modelRead);

    //use a borderlayout with drawing panel in center and button panel in south
    this.setLayout(new BorderLayout());
    JTable sheet = table.getTable();
    sheet.setGridColor(Color.black);
    sheet.getTableHeader().setReorderingAllowed(false);

    sheet.setGridColor(Color.BLACK);

    sheet.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    JScrollPane scrollPane = new JScrollPane(sheet);

    // listener takes in the scroll bar and the default table
    // so that it will change what is displayed
    AdjustmentListener scrollListener = new HorizontalScrollListener(sheet, table.getModel());
    AdjustmentListener vertScrollListener
            = new VerticalScrollListener(table.getModel());

    scrollPane.getHorizontalScrollBar().addAdjustmentListener(scrollListener);
    scrollPane.getVerticalScrollBar().addAdjustmentListener(vertScrollListener);

    this.add(scrollPane, BorderLayout.CENTER);

  }

  @Override
  public void render() {
    this.setVisible(true);

  }
}
