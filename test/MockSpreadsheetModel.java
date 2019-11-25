import java.util.ArrayList;
import java.util.Set;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Spreadsheet;

public class MockSpreadsheetModel implements Spreadsheet {

  String receivedVals;

  @Override
  public void initializeSpreadsheet(Readable fileName) {
    receivedVals = fileName.toString();
  }

  @Override
  public Cell getCellAt(Coord coord) {
    receivedVals = "Coord:" + coord.toString();
    return null;
  }

  @Override
  public void setCellAt(Coord coord, String rawContents) {
    receivedVals = "Coord:" + coord.toString() + " Raw contents: " + rawContents;
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
  public void evaluateSheet() {
    // nothing to receive
  }

  @Override
  public void evaluateCellAt(Coord coord) {
    receivedVals = "Coord: " + coord.toString();
  }

  @Override
  public int getNumRows() {
    // nothing to receive
    return 0;
  }

  @Override
  public int getNumCols() {
    // nothing to receive
    return 0;
  }

  @Override
  public Set<Coord> getListCoords() {
    // nothing to receive
    return null;
  }
}
