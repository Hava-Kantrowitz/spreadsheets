import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import edu.cs3500.spreadsheets.view.SpreadsheetEditableView;

public class MockViewModel extends SpreadsheetEditableView {

  private String receivedVals = "";

  /**
   * Constructs an instance of the GUI spreadsheet view.
   *
   * @param model the model to render
   */
  public MockViewModel(Spreadsheet model) {
    super(model);
  }

  @Override
  public void setCellAt(String val, int row, int col) {

    receivedVals = receivedVals.concat("Value is " + val + " row is " + row + " col is " + col + " ");

  }

  @Override
  public void highlight() {

    receivedVals = receivedVals.concat("We are highlighting now ");

  }

  @Override
  public void updateTextField(String newText) {
    receivedVals = receivedVals.concat("New text is " + newText + " ");
  }

  @Override
  public void displayFileError() {
    receivedVals = receivedVals.concat("We are displaying a file error now ");
  }

  @Override
  public void render() {
    receivedVals = receivedVals.concat("We are rendering now ");
  }

  public String getReceivedVals() {
    return receivedVals;
  }


}
