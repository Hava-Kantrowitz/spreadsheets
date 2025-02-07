import java.util.ArrayList;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.model.Blank;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;


/**
 * This is the mock of the model to test that the controller is calling the intended method.
 */
public class MockSpreadsheetModel extends BasicSpreadsheet {



  private String receivedVals = "";



  @Override
  public Cell getCellAt(Coord coord) {
    receivedVals = receivedVals.concat("Coord: " + coord.toString());
    return new Blank();   // do not care about this but should not be null
  }

  @Override
  public void setCellAt(Coord coord, String rawContents) {
    receivedVals = receivedVals.concat("Coord: " + coord.toString()
            + " Raw contents: " + rawContents);
  }

  @Override
  public void setCellAt(Coord coord, Cell cellVal) {
    // should not be called by controller
  }

  @Override
  public ArrayList getCellSection(Coord upper, Coord lower) {
    receivedVals = receivedVals.concat("Coord 1: " + upper.toString()
            + " Coord 2: " + lower.toString());
    return null;
  }



  @Override
  public void evaluateCellAt(Coord coord) {
    receivedVals = receivedVals.concat("Eval Coord: " + coord.toString());
  }


  @Override
  public void addChangedRow(int rowToChange, int newHeight){
    receivedVals = receivedVals.concat("Row: " + rowToChange + " Height: " + newHeight);
  }

  @Override
  public void addChangedCol(int colToChange, int newWidth){
    receivedVals = receivedVals.concat("Col: " + colToChange + " Width: " + newWidth);
  }


  /**
   * This is a method to get the log of received values.
   * @return the log of received values
   */
  public String getReceivedVals() {
    return receivedVals;
  }
}
