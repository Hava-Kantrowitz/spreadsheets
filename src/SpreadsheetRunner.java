import java.io.FileNotFoundException;
import java.io.FileReader;

import edu.cs3500.spreadsheets.controller.EditableSheetController;
import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.view.SpreadsheetEditableView;

/**
 * Runner for the gui view, used solely for testing purposes.
 */
public class SpreadsheetRunner {

  /**
   * Entry into running the gui view. This main is solely for testing purposes.
   * @param args command line arguments to the program
   * @throws FileNotFoundException if the given file does not exist
   */
  public static void main(String[] args) throws FileNotFoundException {
    BasicSpreadsheet sheet = new BasicSpreadsheet();
    sheet.initializeSpreadsheet(new FileReader("/Users/victoriabowen/Desktop/" +
            "NEU_1st_year/ObjectOriented/CS_3500_Projects/spreadsheets/src/edu/cs3500/" +
            "spreadsheets/testingText.txt"));
    EditableSheetController controller = new EditableSheetController(null, sheet);
    SpreadsheetEditableView view = new SpreadsheetEditableView(sheet, controller);
    controller.setView(view);
    view.render();
  }


}
