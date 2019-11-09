import java.io.FileNotFoundException;
import java.io.FileReader;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.view.SpreadsheetGraphicsView;
import edu.cs3500.spreadsheets.view.SpreadsheetView;

public class SpreadsheetRunner {
  public static void main(String[] args) throws FileNotFoundException {
    BasicSpreadsheet sheet = new BasicSpreadsheet();
    sheet.initializeSpreadsheet(new FileReader("/Users/victoriabowen/Desktop/" +
            "NEU_1st_year/ObjectOriented/CS_3500_Projects/spreadsheets/" +
            "src/edu/cs3500/spreadsheets/testFiles/testingSpecial"));
    SpreadsheetView view = new SpreadsheetGraphicsView(sheet);
    view.render();
  }


}
