import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import edu.cs3500.spreadsheets.controller.EditableSheetController;
import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.model.Blank;
import edu.cs3500.spreadsheets.model.BooleanValue;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.DoubleValue;
import edu.cs3500.spreadsheets.model.ErrorCell;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.Function;
import edu.cs3500.spreadsheets.model.Reference;
import edu.cs3500.spreadsheets.model.StringValue;
import edu.cs3500.spreadsheets.view.SpreadsheetEditableView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests the controller and its interactions.
 */
public class ControllerTests {

  // the whole controller testing variables
  private BasicSpreadsheet model = new BasicSpreadsheet();
  private SpreadsheetEditableView view;
  private EditableSheetController controller;

  private void beginMethod() {
    try {
      model.initializeSpreadsheet(new FileReader("/Users/victoriabowen/Desktop/" +
              "NEU_1st_year/ObjectOriented/CS_3500_Projects/spreadsheets/" +
              "src/edu/cs3500/spreadsheets/testFiles/testingText.txt"));
    } catch (FileNotFoundException e) {
      System.out.println("Unable to read file in tests");
    }
    view = new SpreadsheetEditableView(model);
    view.render();
    controller = new EditableSheetController(view, model);
  }


  // these are the tests to ensure that the controller as a whole updates the model as intended

  // THESE ARE THE TESTS FOR ON CELL AFFIRMED

  // this is to check when the text field is a valid formula
  @Test
  public void onCellAffirmedFormula() {
    beginMethod();
    Coord coord = new Coord(1, 5);
    view.updateTextField("=(SUM 1 2)");  // this is the user input
    controller.onCellAffirmed(coord);            // calling the method

    List<Formula> formulas = new ArrayList<Formula>();  // setting up expected output
    formulas.add(new DoubleValue(1.0));
    formulas.add(new DoubleValue(2.0));
    Cell expectedCell = new Function("SUM", formulas);

    assertEquals(expectedCell, model.getCellAt(coord));  // checking model updated
  }

  // this is to check when there is nothing in the text field
  @Test
  public void onCellAffirmedNull() {
    beginMethod();
    Coord coord = new Coord(1, 5);
    controller.onCellAffirmed(coord);
    assertEquals(new Blank(), model.getCellAt(coord));
  }

  // this is to check when there is a double value in the text field
  @Test
  public void onCellAffirmedDouble() {
    beginMethod();
    Coord coord = new Coord(1, 5);
    view.updateTextField("12");  // this is the user input
    controller.onCellAffirmed(coord);            // calling the method

    assertEquals(new DoubleValue(12.0), model.getCellAt(coord));  // checking model updated
  }

  // this is to check when there is a string value in the text field
  @Test
  public void onCellAffirmedString() {
    beginMethod();
    Coord coord = new Coord(1, 5);
    view.updateTextField("\"hello\"");  // this is the user input
    controller.onCellAffirmed(coord);            // calling the method

    assertEquals(new StringValue("hello"), model.getCellAt(coord));  // checking model updated
  }

  // this is to check when there is a boolean value in the text field
  @Test
  public void onCellAffirmedBoolean() {
    beginMethod();
    Coord coord = new Coord(1, 5);
    view.updateTextField("= true");  // this is the user input
    controller.onCellAffirmed(coord);            // calling the method

    assertEquals(new BooleanValue(true), model.getCellAt(coord));  // checking model updated
  }

  // this is to check when there is not a valid s expression
  @Test
  public void onCellAffirmedInvalidExpression() {
    beginMethod();
    Coord coord = new Coord(1, 5);
    view.updateTextField("SUM 5 6");  // this is the user input
    controller.onCellAffirmed(coord);            // calling the method

    assertEquals(new ErrorCell(new StringValue("#NAME?"), "SUM 5 6"),
            model.getCellAt(coord));  // checking model updated
  }

  // this is to check when there is a valid s expression but an invalid argument in formula
  @Test
  public void onCellAffirmedInvalidArg() {
    beginMethod();
    Coord coord = new Coord(1, 5);
    view.updateTextField("= (SQRT \"hello\")");  // this is the user input
    controller.onCellAffirmed(coord);            // calling the method

    assertEquals(new ErrorCell(new StringValue("#VALUE!"), "= (SQRT \"hello\")"),
            model.getCellAt(coord));  // checking model updated
  }

  // this is to check when a cell refers to another cell that is an error
  // (the cell itself should stay intact in the model)
  @Test
  public void onCellAffirmedRefError() {
    beginMethod();
    Coord coord = new Coord(1, 5);
    view.updateTextField("= (SQRT \"hello\")");  // this is the user input
    controller.onCellAffirmed(coord);            // calling the method


    Coord coord2 = new Coord(1, 1);
    view.updateTextField("= (SUM A5 7)");  // this is the user input
    controller.onCellAffirmed(coord2);            // calling the method that refers to error cell


    List<Formula> formulas = new ArrayList<Formula>();  // setting up expected output
    formulas.add(new Reference("A5", model));
    formulas.add(new DoubleValue(7.0));
    assertEquals(new Function("SUM", formulas),
            model.getCellAt(coord2));  // checking model updated


    view.updateTextField("= 4");  // this is the user input
    controller.onCellAffirmed(coord);            // calling the method

    assertEquals(new DoubleValue(4.0), model.getCellAt(coord));  // checking model updated with
    assertEquals(new Function("SUM", formulas), model.getCellAt(coord2));

  }

  // this is to check when the text input contains both a reference to and invalid cell and an
  // is an invalid cell itself
  @Test
  public void onCellAffirmedErrorAndRefError() {
    beginMethod();
    Coord coord = new Coord(1, 5);
    view.updateTextField("= (SQRT \"hello\")");  // this is the user input
    controller.onCellAffirmed(coord);            // calling the method


    Coord coord2 = new Coord(1, 1);
    view.updateTextField("= SUM A5 7");  // this is the user input
    controller.onCellAffirmed(coord2);            // calling the method that refers to error cell


    assertEquals(new ErrorCell(new StringValue("#NAME?"), "= SUM A5 7"),
            model.getCellAt(coord2));  // checking model updated to error


    view.updateTextField("= 4");  // this is the user input
    controller.onCellAffirmed(coord);            // calling the method

    assertEquals(new DoubleValue(4.0), model.getCellAt(coord));  // checking model updated with
    assertEquals(new ErrorCell(new StringValue("#NAME?"), "= SUM A5 7"),
            model.getCellAt(coord2));    // second one should still be error

  }


  // this is to check that when a cell is run with a reference to an error cell that
  // an exception is thrown so that the view can represent it correctly
  @Test(expected = IllegalArgumentException.class)
  public void onCellAffirmedRefErrorEval() {
    beginMethod();
    Coord coord = new Coord(1, 5);
    view.updateTextField("= (SQRT \"hello\")");  // this is the user input
    controller.onCellAffirmed(coord);            // calling the method


    Coord coord2 = new Coord(1, 1);
    view.updateTextField("= (SUM A5 7)");  // this is the user input
    controller.onCellAffirmed(coord2);            // calling the method that refers to error cell

    model.getCellAt(coord2).evaluateCell();

  }


  // check when the cell with the reference is set before the cell referred to is set
  // making sure that it throws the error when evaluating
  @Test(expected = IllegalArgumentException.class)
  public void onCellAffirmedRefBeforeError() {
    beginMethod();

    Coord coord = new Coord(1, 5);
    view.updateTextField("4");
    controller.onCellAffirmed(coord);  // setting to an acceptable value

    assertEquals(new DoubleValue(4.0), model.getCellAt(coord));


    Coord coord2 = new Coord(1, 1);
    view.updateTextField("= (SUM A5 7)");  // this is the user input
    controller.onCellAffirmed(coord2);            // calling the method that refers to error cell


    assertEquals(new DoubleValue(11.0), model.getCellAt(coord2).evaluateCell());

    view.updateTextField("= (SQRT \"hello\")");  // this is the user input
    controller.onCellAffirmed(coord);            // calling the method


    model.getCellAt(coord2).evaluateCell(); // expected to throw the error

  }


  // this is to check when a cell is set with a direct self reference
  @Test
  public void onCellAffirmedDirectReference() {
    beginMethod();

    Coord coord = new Coord(1, 5);
    view.updateTextField("= A5");
    controller.onCellAffirmed(coord);  // setting to an acceptable value

    assertEquals(new ErrorCell(new StringValue("#VALUE!"), "= A5"),
            model.getCellAt(coord));

  }

  // this is to check when a cell is set with an indirect self reference
  @Test
  public void onCellAffirmedIndirectSelfReference() {
    beginMethod();

    Coord coord = new Coord(1, 5);
    view.updateTextField("= (SUM A1 5 6)");
    controller.onCellAffirmed(coord);  // setting to an acceptable value


    Coord coord2 = new Coord(1, 1);
    view.updateTextField("= A5");
    controller.onCellAffirmed(coord2);

    assertEquals(new ErrorCell(new StringValue("#VALUE!"), "= A5"),
            model.getCellAt(coord2));

  }


  // this is to check when a cell is set with a self reference and then another cell refers to
  // the cell with the self reference
  @Test
  public void onCellAffirmedReferenceToSelfRef() {
    beginMethod();

    Coord coord = new Coord(1, 5);
    view.updateTextField("= (SUM A1 5 6)");
    controller.onCellAffirmed(coord);  // setting to an acceptable value


    Coord coord2 = new Coord(1, 1);
    view.updateTextField("= A5");
    controller.onCellAffirmed(coord2);

    assertEquals(new ErrorCell(new StringValue("#VALUE!"), "= A5"),
            model.getCellAt(coord2));

    Coord coord3 = new Coord(1, 8);
    view.updateTextField("= A1");
    controller.onCellAffirmed(coord3);

    // the cell itself should remain intact
    assertEquals(new Reference("A1", model), model.getCellAt(coord3));

  }


  // this is to check that the cell that refers to the self ref has VALUE!
  @Test
  public void onCellAffirmedReferenceToSelfRefError() {
    beginMethod();

    Coord coord = new Coord(1, 5);
    view.updateTextField("= (SUM A1 5 6)");
    controller.onCellAffirmed(coord);  // setting to an acceptable value


    Coord coord2 = new Coord(1, 1);
    view.updateTextField("= A5");
    controller.onCellAffirmed(coord2);


    Coord coord3 = new Coord(1, 8);
    view.updateTextField("= A1");
    controller.onCellAffirmed(coord3);


    // should be value when evaluated
    assertEquals("#VALUE!", model.getCellAt(coord3).evaluateCell().toString());

  }


  // this is to check a cell referring to a self reference cell with more than just a reference
  @Test(expected = IllegalArgumentException.class)
  public void onCellAffirmedRefToSelfRef() {
    beginMethod();

    Coord coord = new Coord(1, 5);
    view.updateTextField("= (SUM A1 5 6)");
    controller.onCellAffirmed(coord);  // setting to an acceptable value


    Coord coord2 = new Coord(1, 1);
    view.updateTextField("= A5");
    controller.onCellAffirmed(coord2);


    Coord coord3 = new Coord(1, 8);
    view.updateTextField("= (SUM A1 2)");
    controller.onCellAffirmed(coord3);


    // should be value when evaluated
    model.getCellAt(coord3).evaluateCell().toString();

  }

  //THESE ARE THE TESTS FOR ON CELL SELECTED

  // this is to check when there is a double value in the text field
  @Test
  public void onCellSelectedDouble() {
    beginMethod();
    Coord coord = new Coord(1, 1);
    controller.onCellSelected(coord);            // calling the method

    assertEquals(new DoubleValue(3.0), model.getCellAt(coord));  // checking model updated
  }

  // this is to check when there is a string value in the text field
  @Test
  public void onCellSelectedString() {
    beginMethod();
    Coord coord = new Coord(1, 200);
    controller.onCellSelected(coord);            // calling the method

    assertEquals(new StringValue("hello"), model.getCellAt(coord));  // checking model updated
  }

  // this is to check when there is a boolean value in the text field
  @Test
  public void onCellSelectedBoolean() {
    beginMethod();
    Coord coord = new Coord(81, 4);
    controller.onCellSelected(coord);            // calling the method

    assertEquals(new BooleanValue(false), model.getCellAt(coord));  // checking model updated
  }

  // this is to check when the text field is a valid formula
  @Test
  public void onCellSelectedFormula() {
    beginMethod();
    Coord coord = new Coord(1, 20);
    controller.onCellSelected(coord);            // calling the method

    List<Formula> formulas = new ArrayList<Formula>();  // setting up expected output
    formulas.add(new DoubleValue(2.0));
    formulas.add(new DoubleValue(3.0));
    Cell expectedCell = new Function("SUM", formulas);

    assertEquals(expectedCell, model.getCellAt(coord));  // checking model updated
  }

  // this is to check when there is nothing in the text field
  @Test
  public void onCellSelectedNull() {
    beginMethod();
    Coord coord = new Coord(50, 3);
    controller.onCellSelected(coord);
    assertEquals(new Blank(), model.getCellAt(coord));
  }

  //TESTS FOR ON CELL REVERTED

  // this is to check when there is a double value in the text field
  @Test
  public void onCellRevertedDouble() {
    beginMethod();
    Coord coord = new Coord(1, 1);
    view.updateTextField("12");  // this is the user input
    controller.onCellReverted(coord);            // calling the method

    assertEquals(new DoubleValue(3.0), model.getCellAt(coord));  // checking model updated
  }

  // this is to check when there is a string value in the text field
  @Test
  public void onCellRevertedString() {
    beginMethod();
    Coord coord = new Coord(1, 200);
    view.updateTextField("Testing");  // this is the user input
    controller.onCellReverted(coord);            // calling the method

    assertEquals(new StringValue("hello"), model.getCellAt(coord));  // checking model updated
  }

  // this is to check when there is a boolean value in the text field
  @Test
  public void onCellRevertedBoolean() {
    beginMethod();
    Coord coord = new Coord(81, 4);
    view.updateTextField("5");  // this is the user input
    controller.onCellReverted(coord);            // calling the method

    assertEquals(new BooleanValue(false), model.getCellAt(coord));  // checking model updated
  }

  // this is to check when the text field is a valid formula
  @Test
  public void onCellRevertedFormula() {
    beginMethod();
    Coord coord = new Coord(1, 20);
    view.updateTextField("hey");  // this is the user input
    controller.onCellReverted(coord);            // calling the method

    List<Formula> formulas = new ArrayList<Formula>();  // setting up expected output
    formulas.add(new DoubleValue(2.0));
    formulas.add(new DoubleValue(3.0));
    Cell expectedCell = new Function("SUM", formulas);

    assertEquals(expectedCell, model.getCellAt(coord));  // checking model updated
  }

  // this is to check when there is a blank in the text field
  @Test
  public void onCellRevertedBlank() {
    beginMethod();
    Coord coord = new Coord(200, 4);
    view.updateTextField("5");  // this is the user input
    controller.onCellReverted(coord);            // calling the method

    assertEquals(new Blank(), model.getCellAt(coord));  // checking model updated
  }

  //TESTS FOR ON CELL DELETE

  // this is to check when there is a double value in the text field
  @Test
  public void onCellDeleteDouble() {
    beginMethod();
    Coord coord = new Coord(1, 1);
    controller.onCellDelete(coord);            // calling the method

    assertEquals(new Blank(), model.getCellAt(coord));  // checking model updated
  }

  // this is to check when there is a string value in the text field
  @Test
  public void onCellDeleteString() {
    beginMethod();
    Coord coord = new Coord(1, 200);
    controller.onCellDelete(coord);            // calling the method

    assertEquals(new Blank(), model.getCellAt(coord));  // checking model updated
  }

  // this is to check when there is a boolean value in the text field
  @Test
  public void onCellDeleteBoolean() {
    beginMethod();
    Coord coord = new Coord(81, 4);
    controller.onCellDelete(coord);            // calling the method

    assertEquals(new Blank(), model.getCellAt(coord));  // checking model updated
  }

  // this is to check when there is a blank value in the text field
  @Test
  public void onCellDeleteBlank() {
    beginMethod();
    Coord coord = new Coord(200, 5);
    controller.onCellDelete(coord);            // calling the method

    assertEquals(new Blank(), model.getCellAt(coord));  // checking model updated
  }

  // this is to check when there is a formula value in the text field
  @Test
  public void onCellDeleteFormula() {
    beginMethod();
    Coord coord = new Coord(1, 5);
    controller.onCellDelete(coord);            // calling the method

    assertEquals(new Blank(), model.getCellAt(coord));  // checking model updated
  }

  //TESTS FOR ON LOAD SELECT

  // this is to check that a working sheet is correctly loaded
  @Test
  public void onCellLoadGood() {
    beginMethod();
    controller.onLoadSelect("/Users/victoriabowen/Desktop/NEU_1st_year/" +
            "ObjectOriented/CS_3500_Projects/spreadsheets/src/edu/cs3500/" +
            "spreadsheets/testFiles/testingSpecial");

    //test with string
    Coord coord = new Coord(1, 5);
    view.updateTextField("\"hello\"");  // this is the user input
    controller.onCellAffirmed(coord);            // calling the method

    assertNotEquals(new StringValue("hello"), model.getCellAt(coord));
    // checking model itself not updated

    //test with double
    Coord doubleCoord = new Coord(1, 5);
    view.updateTextField("3.0");  // this is the user input
    controller.onCellAffirmed(coord);            // calling the method

    assertNotEquals(new DoubleValue(3.0), model.getCellAt(doubleCoord));
    // checking model itself not updated

    //test with boolean
    Coord boolCoord = new Coord(1, 5);
    view.updateTextField("true");  // this is the user input
    controller.onCellAffirmed(coord);            // calling the method

    assertNotEquals(new BooleanValue(true), model.getCellAt(boolCoord));
    // checking model itself not updated
  }

  //test that a bad file still loads
  @Test
  public void testBadFileLoad() {
    beginMethod();
    controller.onLoadSelect("/Users/victoriabowen/Desktop/" +
            "NEU_1st_year/ObjectOriented/CS_3500_Projects/spreadsheets/src/edu/cs3500/" +
            "spreadsheets/testFiles/testingBad");

    //test with string
    Coord coord = new Coord(1, 5);
    view.updateTextField("\"hello\"");  // this is the user input
    controller.onCellAffirmed(coord);            // calling the method

    assertNotEquals(new StringValue("hello"), model.getCellAt(coord));
    // checking model itself not updated

    //test with double
    Coord doubleCoord = new Coord(1, 5);
    view.updateTextField("3.0");  // this is the user input
    controller.onCellAffirmed(coord);            // calling the method

    assertNotEquals(new DoubleValue(3.0), model.getCellAt(doubleCoord));
    // checking model itself not updated

    //test with boolean
    Coord boolCoord = new Coord(1, 5);
    view.updateTextField("true");  // this is the user input
    controller.onCellAffirmed(coord);            // calling the method

    assertNotEquals(new BooleanValue(true), model.getCellAt(boolCoord));
    // checking model itself not updated
  }

  //TESTS FOR ON FILE SAVE

  @Test
  public void testFileSave() {

    beginMethod();
    Coord coord = new Coord(5, 5);
    view.updateTextField("hey");
    controller.onCellSelected(coord);

    //The file here is a file that doesn't currently exist
    controller.onSaveSelect("/Users/victoriabowen/Desktop/NEU_1st_year/" +
            "ObjectOriented/CS_3500_Projects/spreadsheets/src/edu/" +
            "cs3500/spreadsheets/testingHamilton.txt");

    controller.onLoadSelect("/Users/victoriabowen/Desktop/NEU_1st_year/ObjectOriented/" +
            "CS_3500_Projects/spreadsheets/src/edu/cs3500/spreadsheets/testingHamilton.txt");

    assertNotEquals(new StringValue("hey"), model.getCellAt(coord));
    // checking model itself not updated
  }

  // TESTS USING MOCK TO ENSURE MODEL IS RECEIVING CORRECT VALUES

  //test inputs for single call affirm
  @Test
  public void testAffirmCallsMock() throws FileNotFoundException {

    MockSpreadsheetModel mockSheet = new MockSpreadsheetModel();
    mockSheet.initializeSpreadsheet(new FileReader("/Users/victoriabowen/Desktop" +
            "/NEU_1st_year/ObjectOriented/CS_3500_Projects/spreadsheets/src/edu/" +
            "cs3500/spreadsheets/testFiles/testingEmpty"));
    view = new SpreadsheetEditableView(mockSheet);
    controller = new EditableSheetController(view, mockSheet);
    Coord coord = new Coord(1, 5);
    controller.onCellAffirmed(coord);            // calling the method

    //makes sure that get cell at, set cell at are both triggered
    assertEquals("Coord: A5 Raw contents: Coord: A5", mockSheet.getReceivedVals());
    // checking model updated
  }

  //test inputs for single call delete
  @Test
  public void testDeleteCallsMock() throws FileNotFoundException {

    MockSpreadsheetModel mockSheet = new MockSpreadsheetModel();
    mockSheet.initializeSpreadsheet(new FileReader("/Users/victoriabowen/Desktop/" +
            "NEU_1st_year/ObjectOriented/CS_3500_Projects/spreadsheets/src/edu/cs3500/" +
            "spreadsheets/testFiles/testingEmpty"));
    view = new SpreadsheetEditableView(mockSheet);
    controller = new EditableSheetController(view, mockSheet);
    Coord coord = new Coord(1, 5);
    controller.onCellDelete(coord);            // calling the method

    //makes sure that get cell at, set cell at is triggered
    assertEquals("Coord: A5 Raw contents: Coord: A5", mockSheet.getReceivedVals());
    // checking model updated
  }

  //test inputs for single call select
  @Test
  public void testSelectCallsMock() throws FileNotFoundException {

    MockSpreadsheetModel mockSheet = new MockSpreadsheetModel();
    mockSheet.initializeSpreadsheet(new FileReader("/Users/victoriabowen/Desktop/" +
            "NEU_1st_year/ObjectOriented/CS_3500_Projects/spreadsheets/src/edu/cs3500/" +
            "spreadsheets/testFiles/testingEmpty"));
    view = new SpreadsheetEditableView(mockSheet);
    controller = new EditableSheetController(view, mockSheet);
    Coord coord = new Coord(1, 5);
    controller.onCellSelected(coord);            // calling the method

    //makes sure that just get cell at is triggered
    assertEquals("Coord: A5", mockSheet.getReceivedVals());  // checking model updated
  }

  //test inputs for single call revert
  @Test
  public void testRevertCallsMock() throws FileNotFoundException {

    MockSpreadsheetModel mockSheet = new MockSpreadsheetModel();
    mockSheet.initializeSpreadsheet(new FileReader("/Users/victoriabowen/Desktop/" +
            "NEU_1st_year/ObjectOriented/CS_3500_Projects/spreadsheets/src/edu/" +
            "cs3500/spreadsheets/testFiles/testingEmpty"));
    view = new SpreadsheetEditableView(mockSheet);
    controller = new EditableSheetController(view, mockSheet);
    Coord coord = new Coord(1, 5);
    controller.onCellReverted(coord);            // calling the method

    //makes sure that just get cell at is triggered
    assertEquals("Coord: A5", mockSheet.getReceivedVals());
    // checking model updated
  }

  //test inputs for multiple calls affirm
  @Test
  public void testAffirmCallsMultipleMock() throws FileNotFoundException {

    MockSpreadsheetModel mockSheet = new MockSpreadsheetModel();
    mockSheet.initializeSpreadsheet(new FileReader("/Users/victoriabowen/Desktop/" +
            "NEU_1st_year/ObjectOriented/CS_3500_Projects/spreadsheets/src/edu/cs3500/" +
            "spreadsheets/testFiles/testingSpecial"));
    view = new SpreadsheetEditableView(mockSheet);
    controller = new EditableSheetController(view, mockSheet);
    Coord coord = new Coord(1, 5);
    controller.onCellAffirmed(coord);            // calling the method

    //makes sure that get cell at, set cell at are both triggered
    assertEquals("Coord: A1 Raw contents: 3Coord: A2 Raw contents: " +
            "4Coord: A3 Raw contents: 7Coord: B1 Raw contents: \"Vicky\"Coord: " +
            "B2 Raw contents: \"Hava\"Coord: B3 Raw contents: \"Ham\"Coord: C8 Raw " +
            "contents: =(SUM 2 3)Coord: F12 Raw contents: =A3Coord: AB200 Raw contents: " +
            "50Coord: A5 Raw contents: Coord: A5", mockSheet.getReceivedVals());
    // checking model updated
  }

  //test inputs for multiple calls select
  @Test
  public void testSelectCallsMultipleMock() throws FileNotFoundException {

    MockSpreadsheetModel mockSheet = new MockSpreadsheetModel();
    mockSheet.initializeSpreadsheet(new FileReader("/Users/victoriabowen/Desktop/" +
            "NEU_1st_year/ObjectOriented/CS_3500_Projects/spreadsheets/src/edu/" +
            "cs3500/spreadsheets/testFiles/testingSpecial"));
    view = new SpreadsheetEditableView(mockSheet);
    controller = new EditableSheetController(view, mockSheet);
    Coord coord = new Coord(1, 5);
    controller.onCellSelected(coord);            // calling the method

    //makes sure that get cell at, set cell at are both triggered
    assertEquals("Coord: A1 Raw contents: 3Coord: A2 Raw contents: " +
            "4Coord: A3 Raw contents: 7Coord: B1 Raw contents: \"Vicky\"Coord: " +
            "B2 Raw contents: \"Hava\"Coord: B3 Raw contents: \"Ham\"Coord: C8 " +
            "Raw contents: =(SUM 2 3)Coord: F12 Raw contents: =A3Coord: AB200 Raw " +
            "contents: 50Coord: A5", mockSheet.getReceivedVals());  // checking model updated
  }

  //test inputs for multiple calls revert
  @Test
  public void testRevertCallsMultipleMock() throws FileNotFoundException {

    MockSpreadsheetModel mockSheet = new MockSpreadsheetModel();
    mockSheet.initializeSpreadsheet(new FileReader("/Users/victoriabowen/Desktop/" +
            "NEU_1st_year/ObjectOriented/CS_3500_Projects/spreadsheets/src/edu/" +
            "cs3500/spreadsheets/testFiles/testingSpecial"));
    view = new SpreadsheetEditableView(mockSheet);
    controller = new EditableSheetController(view, mockSheet);
    Coord coord = new Coord(1, 5);
    controller.onCellReverted(coord);            // calling the method

    //makes sure that get cell at, set cell at are both triggered
    assertEquals("Coord: A1 Raw contents: 3Coord: A2 Raw contents: " +
            "4Coord: A3 Raw contents: 7Coord: B1 Raw contents: \"Vicky\"Coord: " +
            "B2 Raw contents: \"Hava\"Coord: B3 Raw contents: \"Ham\"Coord: C8 " +
            "Raw contents: =(SUM 2 3)Coord: F12 Raw contents: =A3Coord: AB200 Raw " +
            "contents: 50Coord: A5", mockSheet.getReceivedVals());  // checking model updated
  }

  //test inputs for multiple calls delete
  @Test
  public void testDeleteCallsMultipleMock() throws FileNotFoundException {

    MockSpreadsheetModel mockSheet = new MockSpreadsheetModel();
    mockSheet.initializeSpreadsheet(new FileReader("/Users/victoriabowen/Desktop/" +
            "NEU_1st_year/ObjectOriented/CS_3500_Projects/spreadsheets/src/edu/cs3500/" +
            "spreadsheets/testFiles/testingSpecial"));
    view = new SpreadsheetEditableView(mockSheet);
    controller = new EditableSheetController(view, mockSheet);
    Coord coord = new Coord(1, 5);
    controller.onCellDelete(coord);            // calling the method

    //makes sure that get cell at, set cell at are both triggered
    assertEquals("Coord: A1 Raw contents: 3Coord: A2 Raw contents: 4Coord: " +
            "A3 Raw contents: 7Coord: B1 Raw contents: \"Vicky\"Coord: B2 Raw contents: " +
            "\"Hava\"Coord: B3 Raw contents: \"Ham\"Coord: C8 Raw contents: =(SUM 2 3)Coord: " +
            "F12 Raw contents: =A3Coord: AB200 Raw contents: 50Coord: A5 Raw contents: Coord:" +
            " A5", mockSheet.getReceivedVals());  // checking model updated
  }

  //TESTING THE MOCK VIEW

  //test inputs for view affirm
  @Test
  public void testAffirmViewMock() throws FileNotFoundException {

    BasicSpreadsheet model = new BasicSpreadsheet();
    model.initializeSpreadsheet(new FileReader("/Users/victoriabowen/Desktop/NEU_1st_year" +
            "/ObjectOriented/CS_3500_Projects/spreadsheets/src/edu/cs3500/s" +
            "preadsheets/testFiles/testingSpecial"));
    MockView mockView = new MockView(model);
    controller = new EditableSheetController(mockView, model);
    Coord coord = new Coord(1, 5);
    controller.onCellAffirmed(coord);            // calling the method

    //makes sure that get cell at, set cell at are both triggered
    assertEquals("Value is  row is 4 col is 0 Value is 4.0 row is 1 col is 0 Value" +
            " is Ham row is 2 col is 1 Value is 3.0 row is 0 col is 0 Value is Hava row is 1 col " +
            "is 1 Value is Vicky row is 0 col is 1 Value is 50.0 row is 199 col is 27 Value is " +
            "7.0 row is 11 col is 5 Value is 5.0 row is 7 col is 2 Value is 7.0 row is 2 " +
            "col is 0 ", mockView.getReceivedVals());  // checking model updated
  }

  //test inputs for view revert
  @Test
  public void testRevertViewMock() throws FileNotFoundException {

    BasicSpreadsheet model = new BasicSpreadsheet();
    model.initializeSpreadsheet(new FileReader("/Users/victoriabowen/Desktop/" +
            "NEU_1st_year/ObjectOriented/CS_3500_Projects/spreadsheets/src/edu/cs3500/s" +
            "preadsheets/testFiles/testingSpecial"));
    MockView mockView = new MockView(model);
    controller = new EditableSheetController(mockView, model);
    Coord coord = new Coord(1, 5);
    controller.onCellReverted(coord);            // calling the method

    //makes sure that get cell at, set cell at are both triggered
    assertEquals("New text is  ", mockView.getReceivedVals());  // checking model updated
  }

  //test inputs for view select
  @Test
  public void testSelectViewMock() throws FileNotFoundException {

    BasicSpreadsheet model = new BasicSpreadsheet();
    model.initializeSpreadsheet(new FileReader("/Users/victoriabowen/Desktop/" +
            "NEU_1st_year/ObjectOriented/CS_3500_Projects/spreadsheets/src/edu/" +
            "cs3500/spreadsheets/testFiles/testingSpecial"));
    MockView mockView = new MockView(model);
    controller = new EditableSheetController(mockView, model);
    Coord coord = new Coord(1, 5);
    controller.onCellSelected(coord);            // calling the method

    //makes sure that get cell at, set cell at are both triggered
    assertEquals("We are highlighting now New text is  ", mockView.getReceivedVals());
    // checking model updated
  }

  //test inputs for view delete
  @Test
  public void testDeleteViewMock() throws FileNotFoundException {

    BasicSpreadsheet model = new BasicSpreadsheet();
    model.initializeSpreadsheet(new FileReader("/Users/victoriabowen/Desktop/NEU_1st_year/" +
            "ObjectOriented/CS_3500_Projects/spreadsheets/src/edu/cs3500/spreadsheets/" +
            "testFiles/testingSpecial"));
    MockView mockView = new MockView(model);
    controller = new EditableSheetController(mockView, model);
    Coord coord = new Coord(1, 5);
    controller.onCellDelete(coord);            // calling the method

    //makes sure that get cell at, set cell at are both triggered
    assertEquals("New text is  Value is  row is 4 col is 0 Value is 4.0 row is 1 col " +
            "is 0 Value is Ham row is 2 col is 1 Value is 3.0 row is 0 col is 0 Value is Hava " +
            "row is 1 col is 1 Value is Vicky row is 0 col is 1 Value is 50.0 row is 199 col " +
            "is 27 Value is 7.0 row is 11 col is 5 Value is 5.0 row is 7 col is 2 Value is 7.0 " +
            "row is 2 col is 0 ", mockView.getReceivedVals());  // checking model updated
  }

  //tests loading existing sheet mock view inputs
  @Test
  public void testLoadGoodSheetViewMock() throws FileNotFoundException {

    BasicSpreadsheet model = new BasicSpreadsheet();
    model.initializeSpreadsheet(new FileReader("/Users/victoriabowen/Desktop/NEU_1st_year/" +
            "ObjectOriented/CS_3500_Projects/spreadsheets/src/edu/cs3500/" +
            "spreadsheets/testFiles/testingSpecial"));
    MockView mockView = new MockView(model);
    controller = new EditableSheetController(mockView, model);
    Coord coord = new Coord(1, 5);
    controller.onLoadSelect("/Users/victoriabowen/Desktop/NEU_1st_year/" +
            "ObjectOriented/CS_3500_Projects/spreadsheets/src/edu/cs3500/" +
            "spreadsheets/testFiles/testingText.txt");
    // calling the method

    //makes sure that get cell at, set cell at are both triggered
    assertEquals("", mockView.getReceivedVals());  // checking model updated
  }

  //tests loading nonexistent sheet view inputs
  @Test
  public void testLoadNonexistentSheetViewMock() throws FileNotFoundException {

    BasicSpreadsheet model = new BasicSpreadsheet();
    model.initializeSpreadsheet(new FileReader("/Users/victoriabowen/Desktop/" +
            "NEU_1st_year/ObjectOriented/CS_3500_Projects/spreadsheets/src/edu/cs3500/" +
            "spreadsheets/testFiles/testingSpecial"));
    MockView mockView = new MockView(model);
    controller = new EditableSheetController(mockView, model);
    controller.onLoadSelect("thisSheetDoesntExist");            // calling the method

    //makes sure that get cell at, set cell at are both triggered
    assertEquals("We are displaying a file error now ", mockView.getReceivedVals());
    // checking model updated
  }


}
