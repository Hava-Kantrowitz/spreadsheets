import org.junit.Test;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import edu.cs3500.spreadsheets.controller.EditableSheetController;
import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.view.AcceptActionListener;
import edu.cs3500.spreadsheets.view.CellClickListener;
import edu.cs3500.spreadsheets.view.DeleteListener;
import edu.cs3500.spreadsheets.view.NoEditTable;
import edu.cs3500.spreadsheets.view.SpreadsheetEditableView;

import static org.junit.Assert.assertEquals;

/**
 * This is the test class to ensure that the methods of each listener activate the correct feature
 * in the controller (with the use of a mock controller).
 */
public class ListenerTests {

  private BasicSpreadsheet model = new BasicSpreadsheet();
  private SpreadsheetEditableView view;
  private MockController controller;

  private void beginMethod() {
    try {
      model.initializeSpreadsheet(new FileReader("C:\\Users\\havak\\IdeaProjects\\" +
              "nextTry\\src\\edu\\cs3500\\spreadsheets\\testFiles\\testingText.txt"));
    } catch (FileNotFoundException e) {
      System.out.println("Unable to read file in tests");
    }
    view = new SpreadsheetEditableView(model);
    view.render();
    controller = new MockController(view, model);
  }
  // these are the tests to make sure that the events are calling the correct features
  // we are assuming that the swing events correctly activate the listeners and are
  // therefore testing that the correct controller feature is activated with the given swing method.


  // these are the tests for the accept action listener
  @Test
  public void acceptActionCallsAffirm() {
    JTable sheet = new JTable();
    AcceptActionListener accept = new AcceptActionListener(sheet, controller);
    beginMethod();
    ActionEvent e = new ActionEvent(controller, 1, "affirm");
    accept.actionPerformed(e);

    assertEquals("hey", controller.getOutputLog());

  }

  //cell click mock triggers on cell selected
  @Test
  public void cellClick() {
    JTable sheet = new JTable();
    CellClickListener click = new CellClickListener(sheet, controller);
    beginMethod();
    ListSelectionEvent e =
            new ListSelectionEvent(controller, 1, 3, false);
    click.valueChanged(e);

    assertEquals("hey", controller.getOutputLog());

  }

  //delete listener mock triggers on cell delete
  @Test
  public void deleteListenerMock() {
    DefaultTableModel sheet = new DefaultTableModel();
    NoEditTable table = new NoEditTable(sheet);
    DeleteListener delete = new DeleteListener(table, controller);
    beginMethod();
    KeyEvent e = new KeyEvent(table, 1, 1, 1, KeyEvent.VK_DELETE, ' ');
    delete.keyTyped(e);

    assertEquals("hey", controller.getOutputLog());

  }

  // delete listener with backspace key
  @Test
  public void deleteBackSpaceListenerMock() {
    DefaultTableModel sheet = new DefaultTableModel();
    NoEditTable table = new NoEditTable(sheet);
    DeleteListener delete = new DeleteListener(table, controller);
    beginMethod();
    KeyEvent e = new KeyEvent(table, 1, 1, 1,
            KeyEvent.VK_BACK_SPACE, ' ');
    delete.keyTyped(e);

    assertEquals("hey", controller.getOutputLog());

  }

  //horizontal scroll listener mock

  //load listener mock

  //revert action listener mock

  //save listener mock

  //valid input listener mock

  //vertical scroll listener


}
