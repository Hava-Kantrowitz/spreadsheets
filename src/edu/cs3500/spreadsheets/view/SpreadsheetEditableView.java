package edu.cs3500.spreadsheets.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import edu.cs3500.spreadsheets.controller.EditableSheetController;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import edu.cs3500.spreadsheets.model.SpreadsheetReadOnlyAdapter;
import edu.cs3500.spreadsheets.model.Value;

/**
 * Models the GUI view of the spreadsheet.
 */
public class SpreadsheetEditableView extends JFrame implements SpreadsheetView {

  private JTextField text;
  private NoEditTable sheet;   // the table representation of the spreadsheet

  /**
   * Constructs an instance of the GUI spreadsheet view.
   *
   * @param model the model to render
   */
  public SpreadsheetEditableView(Spreadsheet model) {

    super();

    EditableSheetController controller = new EditableSheetController(this,model);

    this.setTitle("Spreadsheet");
    this.setSize(1000, 1000);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    SpreadsheetReadOnlyAdapter modelRead = new SpreadsheetReadOnlyAdapter(model);
    SpreadsheetTable table = new SpreadsheetTable(modelRead);

    //use a borderlayout with drawing panel in center and button panel in south
    this.setLayout(new BorderLayout());
    sheet = table.getTable();
    sheet.setGridColor(Color.black);
    sheet.getTableHeader().setReorderingAllowed(false);

    sheet.setGridColor(Color.BLACK);

    sheet.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    JScrollPane scrollPane = new JScrollPane();
    SpreadsheetRowHeaderTable rows = new SpreadsheetRowHeaderTable(modelRead);
    JTable myRows = rows.getTable();
    myRows.setEnabled(false);

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


    // SETTING THE MENU
    JMenuBar menuBar = new JMenuBar();  // create the whole menu bar
    JMenu addLoadMenu = new JMenu("File");    // create the individual menu


    JMenuItem load = new JMenuItem("Load"); // adding the load option
    ActionListener loadListener = new LoadListener(this, controller);
    load.addActionListener(loadListener);
    addLoadMenu.add(load);



    JMenuItem save = new JMenuItem("Save"); // adding the save option
    ActionListener saveListener = new SaveListener(this, controller);
    save.addActionListener(saveListener);
    addLoadMenu.add(save);

    menuBar.add(addLoadMenu);           // add the menu



    this.setJMenuBar(menuBar);
    this.add(rowScroller, BorderLayout.WEST);
    this.add(scrollPane, BorderLayout.CENTER);


  }

  @Override
  public void render() {
    this.setVisible(true);

  }


  /**
   * This is the method to update the text of the text field of the view.
   * @param newText the new text field
   */
  public void updateTextField(String newText){
    this.text.setText(newText);
  }

  /**
   * This is the method to get the text field of the current model.
   */
  public String getTextField(){
    return this.text.getText();
  }

  /**
   * This is to set the value at a given location in the view.
   * @param val the given value in string form
   * @param row the row where it is being set
   * @param col the column where it is being set
   */
  public void setCellAt(String val, int row, int col){
    sheet.setValueAt(val,row,col);
  }


  /**
   * This is to display a warning message when there is an error in the file.
   */
  public void displayFileError() {
    // display a dialog error message because of invalid input
    JOptionPane.showMessageDialog(this,"Invalid file selected.",
            "File Error", JOptionPane.ERROR_MESSAGE);
  }

//  public void highlight(int numClicked) {
//    if (sheet.getGridColor() == Color.BLACK && numClicked % 4 == 0) {
//      sheet.setGridColor(Color.PINK);
//    }
//
//    else if (sheet.getGridColor() == Color.PINK && numClicked % 4 == 0) {
//      sheet.setGridColor(Color.BLACK);
//    }
//  }

  public void highlight(int numClicked, int row, int col) {
    DefaultTableCellRenderer origRenderer = (DefaultTableCellRenderer) sheet.getCellRenderer(row, col);
    DefaultTableCellRenderer newRenderer = new ClickedCellRenderer(row, col-1);

    sheet.getColumnModel().getColumn(col-1).setCellRenderer(newRenderer);

    if (sheet.getCellRenderer(row, col) == newRenderer) {
      sheet.getColumnModel().getColumn(col-1).setCellRenderer(origRenderer);
    }

    int origRow = row;
    int origCol = col;
  }

//  public void highlight(int numClicked, int row, int col) {
//
//  }
}
