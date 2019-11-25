import java.util.ArrayList;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.model.Blank;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;



public class MockSpreadsheetModel extends BasicSpreadsheet {



  private String receivedVals;



  @Override
  public Cell getCellAt(Coord coord) {
    receivedVals = "Coord: " + coord.toString();
    return new Blank();   // do not care about this but should not be null
  }

  @Override
  public void setCellAt(Coord coord, String rawContents) {
    receivedVals = "Coord: " + coord.toString() + " Raw contents: " + rawContents;
  }

  @Override
  public void setCellAt(Coord coord, Cell cellVal) {
    // should not be called by controller
  }

  @Override
  public ArrayList getCellSection(Coord upper, Coord lower) {
    receivedVals = "Coord 1: " + upper.toString() + " Coord 2: " + lower.toString();
    return null;
  }



  @Override
  public void evaluateCellAt(Coord coord) {
    receivedVals = "Coord: " + coord.toString();
  }







  /**
   * This is a method to get the log of received values.
   * @return the log of received values
   */
  public String getReceivedVals(){
    return receivedVals;
  }
}
