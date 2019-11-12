package edu.cs3500.spreadsheets.view;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.AdjustmentListener;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;

/**
 * Models the GUI view of the spreadsheet.
 */
public class SpreadsheetGraphicsView extends JFrame implements SpreadsheetView {

  JTable sheet;

  /**
   * Constructs an instance of the GUI spreadsheet view.
   * @param model the model to render
   */
  public SpreadsheetGraphicsView(BasicSpreadsheet model) {

    super();
    this.setTitle("Spreadsheet");
    this.setSize(1000, 1000);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    SpreadsheetTable table = new SpreadsheetTable(model);

    //use a borderlayout with drawing panel in center and button panel in south
    this.setLayout(new BorderLayout());
    sheet = table.getTable();
    sheet.setGridColor(Color.black);

    sheet.setGridColor(Color.BLACK);

    sheet.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    JScrollPane scrollPane = new JScrollPane(sheet);

    // listener takes in the scroll bar and the default table
    // so that it will change what is displayed
    AdjustmentListener scrollListener = new HorizontalScrollListener(sheet, table.getModel());
    AdjustmentListener vertScrollListener
            = new VerticalScrollListener(scrollPane, table.getModel());

    scrollPane.getHorizontalScrollBar().addAdjustmentListener(scrollListener);
    scrollPane.getVerticalScrollBar().addAdjustmentListener(vertScrollListener);

    DefaultTableCellRenderer rowRenderer = new DefaultTableCellRenderer();
    sheet.getColumnModel().getColumn(0).setCellRenderer(rowRenderer);

    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(JLabel.CENTER);

    for (int i = 0; i < sheet.getColumnCount(); i++) {
      sheet.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
    }

    rowRenderer.setBackground(Color.LIGHT_GRAY);
    rowRenderer.setHorizontalAlignment(JLabel.CENTER);
    sheet.getColumnModel().getColumn(0).setCellRenderer(rowRenderer);


    this.add(scrollPane, BorderLayout.CENTER);

  }

  @Override
  public void render() {
    this.setVisible(true);
  }
}
