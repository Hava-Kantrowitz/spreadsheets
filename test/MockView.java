import edu.cs3500.spreadsheets.controller.Features;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import edu.cs3500.spreadsheets.view.SpreadsheetEditableView;

/**
 * This is the mock of the model to test that the controller is calling the intended method.
 */
public class MockView extends SpreadsheetEditableView {

  private String receivedVals = "";

  /**
   * Constructs an instance of the GUI spreadsheet view.
   *
   * @param model the model to render
   */
  public MockView(Spreadsheet model, Features controller) {
    super(model, controller);
  }

  @Override
  public void setCellAt(String val, int row, int col) {

    receivedVals = receivedVals.concat("Value is " + val + " row is " + row
            + " col is " + col + " ");

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

  @Override
  public void changeRowSize(int rowToChange, Integer newHeight){
    receivedVals = receivedVals.concat("Row: " + rowToChange + " Height: " + newHeight);
  }

  @Override
  public void changeColSize(int colToChange, Integer newWidth){
    receivedVals = receivedVals.concat("Col: " + colToChange + " Width: " + newWidth);
  }


  /**
   * This is a method to get the values that have been received.
   * @return the values received through the mock view
   */
  public String getReceivedVals() {
    return receivedVals;
  }


}
