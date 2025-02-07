package edu.cs3500.spreadsheets.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import edu.cs3500.spreadsheets.controller.Features;
import edu.cs3500.spreadsheets.model.Spreadsheet;

/**
 * Models the editable GUI view of the spreadsheet which a user is able to interact with.
 */
public class SpreadsheetEditableView extends JFrame implements SpreadsheetView {

  private JTextField text;
  private NoEditTable sheet;   // the table representation of the spreadsheet
  private int prevSelectedRow = -1;
  private int prevSelectedCol = -1;

  /**
   * Constructs an instance of the editable GUI spreadsheet view.
   *
   * @param model the model to render
   */
  public SpreadsheetEditableView(Spreadsheet model, Features controller) {

    super();


    this.setTitle("Spreadsheet");
    this.setSize(1000, 1000);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    SpreadsheetTable table = new SpreadsheetTable(model);

    //use a borderlayout with drawing panel in center and button panel in south
    this.setLayout(new BorderLayout());
    sheet = table.getTable();
    sheet.setGridColor(Color.black);
    sheet.getTableHeader().setReorderingAllowed(false);


    sheet.setGridColor(Color.BLACK);

    sheet.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    JScrollPane scrollPane = new JScrollPane();
    SpreadsheetRowHeaderTable rows = new SpreadsheetRowHeaderTable(model);
    JTable myRows = rows.getTable();

    myRows.setRowSelectionAllowed(false);  // this makes it so you cannot select a whole row
    // myRows.setEnabled(false);


    // ENABLING LISTENER FOR ROW SIZE CHANGING
    MouseListener rowChangeListener = new RowSizeListener(myRows, sheet, controller);
    myRows.addMouseListener(rowChangeListener);


    JScrollPane rowScroller = new JScrollPane();
    scrollPane.getViewport().add(sheet);
    rowScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
    rowScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    rowScroller.getViewport().setPreferredSize(new Dimension(50, 4800));
    rowScroller.getViewport().add(myRows);


    // listener takes in the scroll bar and the default table
    // so that it will change what is displayed
    AdjustmentListener scrollListener = new HorizontalScrollListener(sheet, table.getModel(),
            myRows, controller);
    AdjustmentListener vertScrollListener
            = new VerticalScrollListener(table.getModel(), (DefaultTableModel) myRows.getModel(),
            rowScroller.getVerticalScrollBar(), controller);


    scrollPane.getHorizontalScrollBar().addAdjustmentListener(scrollListener);
    scrollPane.getVerticalScrollBar().addAdjustmentListener(vertScrollListener);


    // BUTTONS
    // the button to accept the input to the cell
    JButton acceptChangeB = new JButton("Accept");
    acceptChangeB.setBackground(Color.GREEN);
    // the button to revert back to the original output
    JButton revertBackB = new JButton("Revert");
    revertBackB.setBackground(Color.RED);

    // Listeners for the buttons
    ActionListener buttonAcceptListener = new AcceptActionListener(sheet, controller);
    acceptChangeB.addActionListener(buttonAcceptListener);
    ActionListener buttonRevertListener = new RevertActionListener(sheet, controller);
    revertBackB.addActionListener(buttonRevertListener);

    // the user input
    text = new JTextField();
    text.setPreferredSize(new Dimension(500, 30));


    // setting up the panel of buttons
    JPanel buttons = new JPanel();
    buttons.setLayout(new FlowLayout());
    buttons.add(acceptChangeB);
    buttons.add(revertBackB);

    // separate panel with search and buttons
    JPanel userInput = new JPanel();
    userInput.add(text, BorderLayout.NORTH);
    userInput.add(buttons, BorderLayout.EAST);


    // adds the upper panel (user input) to the whole panel
    this.add(userInput, BorderLayout.NORTH);


    // listening for when something new is clicked
    ListSelectionListener cellClickListener = new CellClickListener(sheet, controller);
    sheet.setRowSelectionAllowed(false);

    // adding the listeners to the table to get changes in columns and rows
    sheet.getSelectionModel().addListSelectionListener(cellClickListener);
    sheet.getColumnModel().getSelectionModel().addListSelectionListener(cellClickListener);


    // adding listener for delete key
    KeyListener deleteListener = new DeleteListener(sheet, controller);
    sheet.addKeyListener(deleteListener);


    // SETTING THE MENUBAR
    JMenuBar menuBar = new JMenuBar();  // create the whole menu bar


    JMenu addLoadMenu = new JMenu("File");    // create the individual menu

    JMenuItem load = new JMenuItem("Load"); // adding the load option to file menu
    ActionListener loadListener = new LoadListener(this, controller);
    load.addActionListener(loadListener);
    addLoadMenu.add(load);

    JMenuItem save = new JMenuItem("Save"); // adding the save option to file menu
    ActionListener saveListener = new SaveListener(this, controller);
    save.addActionListener(saveListener);
    addLoadMenu.add(save);


    JMenu helpMenu = new JMenu("Help"); // creating the help menu

    // adding options to the help menu
    JMenuItem validInput = new JMenuItem("Valid Input"); // adding the load option to file menu
    ActionListener validInputListener = new ValidInputListener(this);
    validInput.addActionListener(validInputListener);
    helpMenu.add(validInput);

    menuBar.add(addLoadMenu);           // add the file menu
    menuBar.add(helpMenu);             // add the help menu


    // setting the correct row sizes
    // get the rows that are changed
    HashMap<Integer, Integer> changedRows = model.getChangedRows();
    // going through the rows and setting the sizes in the view
    for (Integer row : changedRows.keySet()) {
      // updating appearance in the view (with negative 1 adjustment to view parameters)
      this.changeRowSize(row - 1, changedRows.get(row));
      myRows.setRowHeight(row - 1, changedRows.get(row)); // updating the header as well
    }

    // get the columns that are changed
    HashMap<Integer, Integer> changedCols = model.getChangedCols();
    // going through the rows and setting the sizes in the view
    for (Integer col : changedCols.keySet()) {
      // updating appearance in the view (with negative 1 adjustment to view parameters)
      this.changeColSize(col - 1, changedCols.get(col));
    }


    // setting up the listener for the change in column size
    TableColumnModelListener colSizeListener = new ColSizeListener(sheet, controller);
    sheet.getColumnModel().addColumnModelListener(colSizeListener);


    this.setJMenuBar(menuBar);
    this.add(rowScroller, BorderLayout.WEST);
    this.add(scrollPane, BorderLayout.CENTER);

  }

  @Override
  public void render() {
    this.setVisible(true);

  }

  @Override
  public void updateTextField(String newText) {
    this.text.setText(newText);
  }

  @Override
  public String getTextField() {
    return this.text.getText();
  }

  @Override
  public void setCellAt(String val, int row, int col) {
    sheet.setValueAt(val, row, col);
  }


  @Override
  public void displayFileError() {
    // display a dialog error message because of invalid input
    JOptionPane.showMessageDialog(this, "Invalid file selected.",
            "File Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void highlight() {

    // checking if it is a different column
    if (prevSelectedCol != -1 && prevSelectedCol != sheet.getSelectedColumn()) {
      // creates a renderer for no border to be applied to the previous cell
      DefaultTableCellRenderer noBorderRender =
              new NoBorderRenderer(prevSelectedRow, prevSelectedCol);
      // keeping the center feature
      noBorderRender.setHorizontalAlignment(JLabel.CENTER);
      // setting the previous column so that the cells do not have a cell border (with prev column)
      sheet.getColumnModel().getColumn(prevSelectedCol).setCellRenderer(noBorderRender);
    }

    if (sheet.getSelectedColumn() >= 0) {
      // create a border renderer to be applied to the currently selected cell
      DefaultTableCellRenderer borderRender =
              new ClickedCellRenderer(sheet.getSelectedRow(), sheet.getSelectedColumn());
      // keep the centering
      borderRender.setHorizontalAlignment(JLabel.CENTER);
      //  going through the selected column to add the border
      sheet.getColumnModel().getColumn(sheet.getSelectedColumn()).setCellRenderer(borderRender);


      prevSelectedRow = sheet.getSelectedRow();
      prevSelectedCol = sheet.getSelectedColumn();
    }
  }

  @Override
  public void changeRowSize(int row, Integer newSize) {
    // making sure that it is greater than or equal to 16 (default)
    if(newSize >= 16) {
      sheet.setRowHeight(row, newSize);
    }
  }

  @Override
  public void changeColSize(int col, Integer newSize) {
    // setting to a new preferred width every time so it does not default back when scrolling
    // when scroll sets it the value stays
      sheet.getColumnModel().getColumn(col).setPreferredWidth(newSize);
  }

}
