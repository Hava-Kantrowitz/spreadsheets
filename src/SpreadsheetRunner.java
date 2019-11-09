import java.io.FileNotFoundException;
import java.io.FileReader;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.view.SpreadsheetGraphicsView;
import edu.cs3500.spreadsheets.view.SpreadsheetView;

public class SpreadsheetRunner {
  public static void main(String[] args) throws FileNotFoundException {
    BasicSpreadsheet sheet = new BasicSpreadsheet();
    sheet.initializeSpreadsheet(new FileReader("C:\\Users\\havak\\IdeaProjects\\nextTry\\" +
            "src\\edu\\cs3500\\spreadsheets\\testFiles\\testingSpecial"));
    SpreadsheetView view = new SpreadsheetGraphicsView(sheet);
    view.render();
  }


}
