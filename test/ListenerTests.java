import org.junit.Test;

import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.*;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.view.AcceptActionListener;
import edu.cs3500.spreadsheets.view.SpreadsheetEditableView;



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
      model.initializeSpreadsheet(new FileReader("/Users/victoriabowen/Desktop/" +
              "NEU_1st_year/ObjectOriented/CS_3500_Projects/spreadsheets/" +
              "src/edu/cs3500/spreadsheets/testingText.txt"));
    } catch (FileNotFoundException e) {
      System.out.println("Unable to read file in tests");
    }
    view = new SpreadsheetEditableView(model);
    view.render();
    controller = new MockController(view,model);
  }
  // these are the tests to make sure that the events are calling the correct features
  // we are assuming that the swing events correctly activate the listeners and are
  // therefore testing that the correct controller feature is activated with the given swing method.


  // these are the tests for the accept action listener
  @Test
  public void acceptActionCallsAffirm(){
    JTable sheet = new JTable();
    AcceptActionListener accept = new AcceptActionListener(sheet, controller);
    

  }



}
