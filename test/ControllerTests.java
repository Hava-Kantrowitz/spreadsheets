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

public class ControllerTests {

  // the whole controller testing variables
  private BasicSpreadsheet model = new BasicSpreadsheet();
  private SpreadsheetEditableView view;
  private EditableSheetController controller;

  private void beginMethod() {
    try {
      model.initializeSpreadsheet(new FileReader("C:\\Users\\havak\\IdeaProjects\\nextTry" +
              "\\src\\edu\\cs3500\\spreadsheets\\testFiles\\testingText.txt"));
    } catch (FileNotFoundException e) {
      System.out.println("Unable to read file in tests");
    }
    view = new SpreadsheetEditableView(model);
    view.render();
    controller = new EditableSheetController(view,model);
  }



  // these are the tests to ensure that the controller as a whole updates the model as intended

  // THESE ARE THE TESTS FOR ON CELL AFFIRMED

  // this is to check when the text field is a valid formula
  @Test
  public void onCellAffirmedFormula(){
    beginMethod();
    Coord coord = new Coord(1,5);
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
  public void onCellAffirmedNull(){
    beginMethod();
    Coord coord = new Coord(1, 5);
    controller.onCellAffirmed(coord);
    assertEquals(new Blank(), model.getCellAt(coord));
  }

  // this is to check when there is a double value in the text field
  @Test
  public void onCellAffirmedDouble(){
    beginMethod();
    Coord coord = new Coord(1,5);
    view.updateTextField("12");  // this is the user input
    controller.onCellAffirmed(coord);            // calling the method

    assertEquals(new DoubleValue(12.0), model.getCellAt(coord));  // checking model updated
  }

  // this is to check when there is a string value in the text field
  @Test
  public void onCellAffirmedString(){
    beginMethod();
    Coord coord = new Coord(1,5);
    view.updateTextField("\"hello\"");  // this is the user input
    controller.onCellAffirmed(coord);            // calling the method

    assertEquals(new StringValue("hello"), model.getCellAt(coord));  // checking model updated
  }

  // this is to check when there is a boolean value in the text field
  @Test
  public void onCellAffirmedBoolean(){
    beginMethod();
    Coord coord = new Coord(1,5);
    view.updateTextField("= true");  // this is the user input
    controller.onCellAffirmed(coord);            // calling the method

    assertEquals(new BooleanValue(true), model.getCellAt(coord));  // checking model updated
  }

  // this is to check when there is not a valid s expression
  @Test
  public void onCellAffirmedInvalidExpression(){
    beginMethod();
    Coord coord = new Coord(1,5);
    view.updateTextField("SUM 5 6");  // this is the user input
    controller.onCellAffirmed(coord);            // calling the method

    assertEquals(new ErrorCell(new StringValue("#NAME?"),"SUM 5 6"),
            model.getCellAt(coord));  // checking model updated
  }

  // this is to check when there is a valid s expression but an invalid argument in formula
  @Test
  public void onCellAffirmedInvalidArg(){
    beginMethod();
    Coord coord = new Coord(1,5);
    view.updateTextField("= (SQRT \"hello\")");  // this is the user input
    controller.onCellAffirmed(coord);            // calling the method

    assertEquals(new ErrorCell(new StringValue("#VALUE!"),"= (SQRT \"hello\")"),
            model.getCellAt(coord));  // checking model updated
  }

  // this is to check when a cell refers to another cell that is an error
  // (the cell itself should stay intact in the model)
  @Test
  public void onCellAffirmedRefError(){
    beginMethod();
    Coord coord = new Coord(1,5);
    view.updateTextField("= (SQRT \"hello\")");  // this is the user input
    controller.onCellAffirmed(coord);            // calling the method


    Coord coord2 = new Coord(1,1);
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
    assertEquals(new Function("SUM",formulas), model.getCellAt(coord2));

  }

  // this is to check when the text input contains both a reference to and invalid cell and an
  // is an invalid cell itself
  @Test
  public void onCellAffirmedErrorAndRefError(){
    beginMethod();
    Coord coord = new Coord(1,5);
    view.updateTextField("= (SQRT \"hello\")");  // this is the user input
    controller.onCellAffirmed(coord);            // calling the method


    Coord coord2 = new Coord(1,1);
    view.updateTextField("= SUM A5 7");  // this is the user input
    controller.onCellAffirmed(coord2);            // calling the method that refers to error cell



    assertEquals(new ErrorCell(new StringValue("#NAME?"),"= SUM A5 7"),
            model.getCellAt(coord2));  // checking model updated to error


    view.updateTextField("= 4");  // this is the user input
    controller.onCellAffirmed(coord);            // calling the method

    assertEquals(new DoubleValue(4.0), model.getCellAt(coord));  // checking model updated with
    assertEquals(new ErrorCell(new StringValue("#NAME?"),"= SUM A5 7"),
            model.getCellAt(coord2));    // second one should still be error
  }


  // this is to check that when a cell is run with a reference to an error cell that
  // an exception is thrown so that the view can represent it correctly
  @Test(expected = IllegalArgumentException.class)
  public void onCellAffirmedRefErrorEval(){
    beginMethod();
    Coord coord = new Coord(1,5);
    view.updateTextField("= (SQRT \"hello\")");  // this is the user input
    controller.onCellAffirmed(coord);            // calling the method


    Coord coord2 = new Coord(1,1);
    view.updateTextField("= (SUM A5 7)");  // this is the user input
    controller.onCellAffirmed(coord2);            // calling the method that refers to error cell

    model.getCellAt(coord2).evaluateCell();

  }



  // check when the cell with the reference is set before the cell referred to is set
  // making sure that it throws the error when evaluating
  @Test(expected = IllegalArgumentException.class)
  public void onCellAffirmedRefBeforeError(){
    beginMethod();

    Coord coord = new Coord(1,5);
    view.updateTextField("4");
    controller.onCellAffirmed(coord);  // setting to an acceptable value

    assertEquals(new DoubleValue(4.0),model.getCellAt(coord));


    Coord coord2 = new Coord(1,1);
    view.updateTextField("= (SUM A5 7)");  // this is the user input
    controller.onCellAffirmed(coord2);            // calling the method that refers to error cell


    assertEquals(new DoubleValue(11.0),model.getCellAt(coord2).evaluateCell());

    view.updateTextField("= (SQRT \"hello\")");  // this is the user input
    controller.onCellAffirmed(coord);            // calling the method


    model.getCellAt(coord2).evaluateCell(); // expected to throw the error

  }

  //THESE ARE THE TESTS FOR ON CELL SELECTED

  // this is to check when there is a double value in the text field
  @Test
  public void onCellSelectedDouble(){
    beginMethod();
    Coord coord = new Coord(1,1);
    controller.onCellSelected(coord);            // calling the method

    assertEquals(new DoubleValue(3.0), model.getCellAt(coord));  // checking model updated
  }

  // this is to check when there is a string value in the text field
  @Test
  public void onCellSelectedString(){
    beginMethod();
    Coord coord = new Coord(1,200);
    controller.onCellSelected(coord);            // calling the method

    assertEquals(new StringValue("hello"), model.getCellAt(coord));  // checking model updated
  }

  // this is to check when there is a boolean value in the text field
  @Test
  public void onCellSelectedBoolean(){
    beginMethod();
    Coord coord = new Coord(81,4);
    controller.onCellSelected(coord);            // calling the method

    assertEquals(new BooleanValue(false), model.getCellAt(coord));  // checking model updated
  }

  // this is to check when the text field is a valid formula
  @Test
  public void onCellSelectedFormula(){
    beginMethod();
    Coord coord = new Coord(1,20);
    controller.onCellSelected(coord);            // calling the method

    List<Formula> formulas = new ArrayList<Formula>();  // setting up expected output
    formulas.add(new DoubleValue(2.0));
    formulas.add(new DoubleValue(3.0));
    Cell expectedCell = new Function("SUM", formulas);

    assertEquals(expectedCell, model.getCellAt(coord));  // checking model updated
  }

  // this is to check when there is nothing in the text field
  @Test
  public void onCellSelectedNull(){
    beginMethod();
    Coord coord = new Coord(50, 3);
    controller.onCellSelected(coord);
    assertEquals(new Blank(), model.getCellAt(coord));
  }




  // these are the tests to ensure that the model is receiving the correct values (mock model)


}
