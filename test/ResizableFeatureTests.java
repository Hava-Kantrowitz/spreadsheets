import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import edu.cs3500.spreadsheets.controller.EditableSheetController;
import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.view.SpreadsheetEditableView;

import static org.junit.Assert.assertEquals;

/**
 * This is the test class for the added resizable feature (testing for the changes to the model,
 * controller, and view).
 */
public class ResizableFeatureTests {


  // THESE ARE THE TEST CASES FOR THE ADDED MODEL FEATURES
  // not checking bounds for cells that should not be there such as negative rows and improper
  // new sizes because this is handled with the view selecting

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
    assertEquals(0, model.getChangedCols().size());

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
    assertEquals(0, model.getChangedCols().size());

    model.addChangedCol(10, 200);
    model.addChangedCol(50, 100);
    model.addChangedCol(10, 50);  // setting the same column as before

    HashMap<Integer, Integer> expectedCols = new HashMap<>();

    expectedCols.put(10, 50);
    expectedCols.put(50, 100);

    // checking that the value of the width of the 10th column has changed
    assertEquals(expectedCols, model.getChangedCols());

  }

  // this is testing when the column is set to the default size so that it is removed       // THIS MAY BE MOVED TO A REMOVED METHOD
  // from the list of changed columns
  @Test
  public void changedColsDefault(){
    BasicSpreadsheet model = new BasicSpreadsheet();

    // making sure the changed column size is zero upon constructing
    assertEquals(0, model.getChangedCols().size());

    model.addChangedCol(5, 100); // changing from the default

    // making sure that the changing from the default went through
    assertEquals(1, model.getChangedCols().size());

    // setting it back to the default
    model.addChangedCol(5, 75);

    // making sure that the changed element was removed now that it is default
    assertEquals(0, model.getChangedCols().size());
  }


  // variables used throughout controller testing
  BasicSpreadsheet model;
  EditableSheetController controller;
  SpreadsheetEditableView view;
  /**
   * This is a method to set up the controller testing.
   * @throws IOException if unable to create a blank file
   */
  private void setUpControllerTests(){
    model = new BasicSpreadsheet();
    File file = new File("spreadsheet.txt");
    try {
      file.createNewFile();
      model.initializeSpreadsheet(new FileReader(file.getAbsolutePath()));

      controller = new EditableSheetController(null, model);
      view = new SpreadsheetEditableView(model, controller);
      controller.setView(view);
    }
    catch(IOException e){
      System.out.println("Unable to initialize file in cell resize testing");
    }
  }

  // THESE ARE THE TESTS FOR THE CONTROLLER PORTION
  // not checking for out of bounds because that is handled in the view (not able to get invalid
  // values in the controller)


  // these tests are to check that the controller is working properly (with error checking)
  // onColumnResized() onRowResized() onScroll()

  // ON ROW RESIZED TESTING

  // this is to check that when the row is resized through the controller this is reflected in the
  // model
  @Test
  public void rowResizedControllerTest(){
    setUpControllerTests();
    controller.onRowResized(10, 50);

    HashMap<Integer, Integer> expectedRows = new HashMap<>();
    expectedRows.put(11, 50);

    assertEquals(expectedRows, model.getChangedRows());
  }

  // this is to check that when the row is resized through the controller that multiple go through
  // and changes can be made to existing
  @Test
  public void rowResizedMultipleController(){
    setUpControllerTests();
    controller.onRowResized(10, 50);
    controller.onRowResized(12, 30);
    controller.onRowResized(10, 100);  // changing existing height

    HashMap<Integer, Integer> expectedRows = new HashMap<>();
    expectedRows.put(11, 100);
    expectedRows.put(13, 30);

    assertEquals(expectedRows, model.getChangedRows());
  }

  // this is to check that when the column is resized through the controller it goes through to
  // to the model and has the correct result
  @Test
  public void colResizedControllerTest(){
    setUpControllerTests();
    controller.onColumnResized(10, 50);

    HashMap<Integer, Integer> expectedCols = new HashMap<>();
    expectedCols.put(11, 50);

    assertEquals(expectedCols, model.getChangedCols());
  }

  // this is to check that when the column is resized through the controller it goes through to the
  // model with multiple inputs and same column inputs
  @Test
  public void colResizedMultipleController(){
    setUpControllerTests();
    controller.onColumnResized(10, 50);
    controller.onColumnResized(50, 300);
    controller.onColumnResized(10, 20);

    HashMap<Integer, Integer> expectedCols = new HashMap<>();
    expectedCols.put(11, 20);
    expectedCols.put(51, 300);


    assertEquals(expectedCols, model.getChangedCols());
  }

  // MOCK MODEL TESTING
  // these tests are to check that the controller passes the correct values to the model
  // onColumnResized() onRowResized() (mock controller)

  MockSpreadsheetModel mockModel;


  private void mockModelTestingSetUp(){
    mockModel = new MockSpreadsheetModel();
    File file = new File("spreadsheet.txt");
    try {
      file.createNewFile();
      mockModel.initializeSpreadsheet(new FileReader(file.getAbsolutePath()));

      controller = new EditableSheetController(null, mockModel);
      view = new SpreadsheetEditableView(mockModel, controller);
      controller.setView(view);
    }
    catch(IOException e){
      System.out.println("Unable to initialize file in cell resize testing");
    }
  }

  // this it to check that when the onRowResized() is called the mock model receives the correct
  // results

  @Test
  public void mockModelRowResized(){
    mockModelTestingSetUp();
    controller.onRowResized(5, 30);

    // one should be added to put it in terms of the controller
    assertEquals("Row: 6 Height: 30", mockModel.getReceivedVals());
  }

  // this is to check when the onColResized() is called the mock model receives the correct inputs
  @Test
  public void mockModelColResized(){
    mockModelTestingSetUp();
    controller.onColumnResized(20, 50);

    // one should be added to put it in terms of the controller
    assertEquals("Col: 21 Width: 50", mockModel.getReceivedVals());
  }


  // MOCK VIEW TESTING
  MockView mockView;

  private void mockViewTestingSetUp(){
    model = new BasicSpreadsheet();
    File file = new File("spreadsheet.txt");
    try {
      file.createNewFile();
      model.initializeSpreadsheet(new FileReader(file.getAbsolutePath()));

      controller = new EditableSheetController(null, model);
      mockView = new MockView(model, controller);
      controller.setView(mockView);
    }
    catch(IOException e){
      System.out.println("Unable to initialize file in cell resize testing");
    }
  }

  // these tests are to check that the controller passes the correct values to the view
  // onScroll()

  // this is to check that when onScroll is called the row and column sizes change to the correct
  // values from the model (with a changed row)
  @Test
  public void mockViewOnScrollRow(){
    mockViewTestingSetUp();
    controller.onRowResized(5, 30);
    controller.onScroll();
    assertEquals("Row: 5 Height: 30", mockView.getReceivedVals());

  }

  // this is to check that when onScroll is called the row and column sizes change to the correct
  // values from the model (with a changed column)
  @Test
  public void mockViewOnScrollCol(){
    mockViewTestingSetUp();
    controller.onColumnResized(10, 50);
    controller.onScroll();
    assertEquals("Col: 10 Width: 50", mockView.getReceivedVals());

  }




  // LOAD AND SAVE TESTING
  // test onLoadSelect() and onSaveSelect() as well for added functionality with saving and loading
  // column and row sizes


  // also test the initializing spreadsheet


  // THESE ARE THE TESTS TO CHECK THE UPDATED TEXTUAL VIEW FOR SAVING THE FILE (LISTENER TESTING)









}
