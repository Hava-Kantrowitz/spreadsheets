package edu.cs3500.spreadsheets.view;

import java.awt.*;

import javax.swing.*;

public class SpreadsheetGraphicsView extends JFrame implements SpreadsheetView {

  public SpreadsheetGraphicsView() {

    super();
    this.setTitle("Spreadsheet");
    this.setSize(600, 600);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //use a borderlayout with drawing panel in center and button panel in south
    this.setLayout(new BorderLayout());
    SpreadsheetPanel spreadsheetPanel = new SpreadsheetPanel();
    spreadsheetPanel.setPreferredSize(new Dimension(500, 500));
    JScrollPane scrollPane = new JScrollPane(spreadsheetPanel);
    this.add(scrollPane, BorderLayout.CENTER);

  }

  @Override
  public void render() {
    SpreadsheetGraphicsView view = new SpreadsheetGraphicsView();
    view.setVisible(true);


  }
}
