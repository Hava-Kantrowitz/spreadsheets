import org.junit.Test;

import java.util.HashMap;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;

import static org.junit.Assert.assertEquals;

/**
 * This is the test class for the added resizable feature (testing for the changes to the model,
 * controller, and view).
 */
public class ResizableFeatureTests {


  // THESE ARE THE TEST CASES FOR THE ADDED MODEL FEATURES
  // not checking bounds for cells that should not be there such as negative rows and improper
  // new sizes because this is handled in the controller before passing to the model

  // this is a test for the addChanged row method and get changed rows method
  // when the row and the height is valid
  @Test
  public void changedRowTest() {
    BasicSpreadsheet model = new BasicSpreadsheet();

    // making sure that the changed rows is set to zero when constructing
    assertEquals(0, model.getChangedRows().size());

    model.addChangedRow(2, 50);

    HashMap<Integer, Integer> expectedRows = new HashMap<>();

    expectedRows.put(2, 50);
    // checking that the model is updated with the row that has changed
    assertEquals(expectedRows, model.getChangedRows());
  }


  // this is the test for the addChanged row method when an existing row is changed in size
  // also testing multiple inputs to changed rows
  @Test
  public void changedRowExistsTest() {
    BasicSpreadsheet model = new BasicSpreadsheet();

    // making sure that the changed rows is set to zero when constructing
    assertEquals(0, model.getChangedRows().size());

    model.addChangedRow(5, 50);
    model.addChangedRow(5, 70); // changing a current value
    model.addChangedRow(7, 30);

    HashMap<Integer, Integer> expectedRows = new HashMap<>();

    expectedRows.put(5, 70);
    expectedRows.put(7, 30);

    // checking that the changed values have been included
    assertEquals(expectedRows, model.getChangedRows());

  }

  // this is the test to check when a row is set to the default row size
  @Test
  // WE CHECK FOR DEFAULT IN CONTROLLER INSTEAD
  public void changedRowDefault() {
    BasicSpreadsheet model = new BasicSpreadsheet();

    // making sure the changed rows is set to zero when constructing
    assertEquals(0, model.getChangedRows().size());

    model.addChangedRow(7, 290); // change it to not default
    model.addChangedRow(7, 16);  // change it back to default

    // the size of the returned HashMap should still be zero because of the
    assertEquals(0, model.getChangedRows().size());
  }


  // this is testing the changing of a column size with everything valid
  @Test
  public void changedColTest() {
    BasicSpreadsheet model = new BasicSpreadsheet();

    // making sure the changed columns is set to zero when constructing
    assertEquals(0, model.getChangedCols());

    model.addChangedCol(9, 50);

    HashMap<Integer, Integer> expectedCols = new HashMap<>();

    expectedCols.put(9, 50);

    // check that the column that has changed size has been updated
    assertEquals(expectedCols, model.getChangedCols());
  }

  // this is testing the changing of a column when the size of the same column is changed
  // multiple times (also checking multiple entries)
  @Test
  public void changedColExistsTest() {
    BasicSpreadsheet model = new BasicSpreadsheet();

    // making sure the changed columns size is zero upon constructing
    assertEquals(0, model.getChangedCols());

    model.addChangedCol(10, 200);
    model.addChangedCol(50, 100);
    model.addChangedCol(10, 50);  // setting the same column as before

    HashMap<Integer, Integer> expectedCols = new HashMap<>();

    expectedCols.put(10, 50);
    expectedCols.put(50, 100);

    // checking that the value of the width of the 10th column has changed
    assertEquals(expectedCols, model.getChangedCols());

  }

  // this is testing when the column is set to the default size so that


}
