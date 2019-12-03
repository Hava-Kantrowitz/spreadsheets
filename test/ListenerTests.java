import org.junit.Test;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.view.AcceptActionListener;
import edu.cs3500.spreadsheets.view.CellClickListener;
import edu.cs3500.spreadsheets.view.DeleteListener;
import edu.cs3500.spreadsheets.view.LoadListener;
import edu.cs3500.spreadsheets.view.RevertActionListener;
import edu.cs3500.spreadsheets.view.SaveListener;
import edu.cs3500.spreadsheets.view.SpreadsheetEditableView;

import static org.junit.Assert.assertEquals;

/**
 * This is the test class to ensure that the methods of each listener activate the correct feature
 * in the controller (with the use of a mock controller).
 */
public class ListenerTests {

  private BasicSpreadsheet model = new BasicSpreadsheet();
  private MockController controller;

  /**
   * Initializes the testing conditions.
   */
  private void beginMethod() {
    try {
      model.initializeSpreadsheet(new FileReader("/Users/victoriabowen/Desktop/" +
              "NEU_1st_year/ObjectOriented/CS_3500_Projects/spreadsheets/src/edu/cs3500/" +
              "spreadsheets/testFiles/testingText.txt"));
    } catch (FileNotFoundException e) {
      System.out.println("Unable to read file in tests");
    }
    controller = new MockController(null, model);
    SpreadsheetEditableView view = new SpreadsheetEditableView(model, controller);
    controller.setView(view);

    view.render();
  }
  // these are the tests to make sure that the events are calling the correct features
  // we are assuming that the swing events correctly activate the listeners and are
  // therefore testing that the correct controller feature is activated with the given swing method.


  // these are the tests for the accept action listener
  @Test
  public void acceptActionCallsAffirm() {
    beginMethod();
    DefaultTableModel table = new DefaultTableModel();
    MockTable sheet = new MockTable(table);
    AcceptActionListener accept = new AcceptActionListener(sheet, controller);
    ActionEvent e = new ActionEvent(controller, 1, "affirm");
    accept.actionPerformed(e);

    assertEquals("onCellAffirmed called with B2", controller.getOutputLog());

  }

  //cell click mock triggers on cell selected
  @Test
  public void cellClickMock() {
    beginMethod();
    DefaultTableModel table = new DefaultTableModel();
    MockTable sheet = new MockTable(table);
    CellClickListener click = new CellClickListener(sheet, controller);
    ListSelectionEvent e =
            new ListSelectionEvent(controller, 1, 3, false);
    click.valueChanged(e);

    assertEquals("onCellSelected called with B2", controller.getOutputLog());

  }

  //delete listener mock triggers on cell delete
  @Test
  public void deleteListenerMock() {
    beginMethod();
    DefaultTableModel table = new DefaultTableModel();
    MockTable sheet = new MockTable(table);
    DeleteListener delete = new DeleteListener(sheet, controller);
    KeyEvent e =
            new KeyEvent(sheet, 1, 1, 1, KeyEvent.VK_DELETE, ' ');
    delete.keyReleased(e);

    assertEquals("onCellDelete called with B2", controller.getOutputLog());

  }

  // delete listener with backspace key
  @Test
  public void deleteBackSpaceListenerMock() {
    beginMethod();
    DefaultTableModel table = new DefaultTableModel();
    MockTable sheet = new MockTable(table);
    DeleteListener delete = new DeleteListener(sheet, controller);
    KeyEvent e =
            new KeyEvent(sheet, 1, 1, 1, KeyEvent.VK_BACK_SPACE, ' ');
    delete.keyReleased(e);

    assertEquals("onCellDelete called with B2", controller.getOutputLog());

  }

  //load listener mock
  @Test
  public void loadListenerMock() {
    beginMethod();
    JFrame frame = new JFrame();
    LoadListener load = new LoadListener(frame, controller);
    ActionEvent e = new ActionEvent(controller, 1, "load");
    load.actionPerformed(e);

    assertEquals("onLoadSelect called with ", controller.getOutputLog());

  }

  //revert action listener mock
  @Test
  public void revertListenerMock() {
    beginMethod();
    DefaultTableModel table = new DefaultTableModel();
    MockTable sheet = new MockTable(table);
    RevertActionListener revert = new RevertActionListener(sheet, controller);
    ActionEvent e = new ActionEvent(controller, 1, "revert");
    revert.actionPerformed(e);

    assertEquals("onCellReverted called with B2", controller.getOutputLog());

  }

  //save listener mock
  @Test
  public void saveListenerMock() {
    beginMethod();
    JFrame frame = new JFrame();
    SaveListener save = new SaveListener(frame, controller);
    ActionEvent e = new ActionEvent(controller, 1, "save");
    save.actionPerformed(e);

    assertEquals("onSaveSelect called with ", controller.getOutputLog());

  }


}
