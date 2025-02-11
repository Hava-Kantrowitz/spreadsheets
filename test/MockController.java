import edu.cs3500.spreadsheets.controller.EditableSheetController;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import edu.cs3500.spreadsheets.view.SpreadsheetEditableView;

/**
 * This is the mock of the controller to test that the listeners are calling the intended method.
 */
public class MockController extends EditableSheetController {

  private String outputLog = "";

  /**
   * This is the constructor for the controller of the editable view.
   *
   * @param view  the given spreadsheet view
   * @param model the given spreadsheet model
   */
  public MockController(SpreadsheetEditableView view, Spreadsheet model) {
    super(view, model);
  }


  @Override
  public void onCellAffirmed(Coord coord) {
    outputLog = outputLog.concat("onCellAffirmed called with " + coord.toString());
  }

  @Override
  public void onCellReverted(Coord coord) {
    outputLog = outputLog.concat("onCellReverted called with " + coord.toString());
  }

  @Override
  public void onCellSelected(Coord coord) {
    outputLog = outputLog.concat("onCellSelected called with " + coord.toString());
  }

  @Override
  public void onCellDelete(Coord coord) {
    outputLog = outputLog.concat("onCellDelete called with " + coord.toString());
  }

  @Override
  public void onLoadSelect(String filePath) {
    outputLog = outputLog.concat("onLoadSelect called with " + filePath);
  }

  @Override
  public void onSaveSelect(String filePath) {
    outputLog = outputLog.concat("onSaveSelect called with " + filePath);
  }

  @Override
  public void onColumnResized(int changedCol, int newWidth){
    outputLog = outputLog.concat("Col: " + changedCol + " Width: " + newWidth);
  }

  @Override
  public void onRowResized(int changedRow, int newHeight){
    outputLog = outputLog.concat("Row: " + changedRow + " Height:" + newHeight);
  }

  /**
   * This is the method to get the output log of the program.
   *
   * @return the current output log
   */
  public String getOutputLog() {
    return outputLog;
  }
}
