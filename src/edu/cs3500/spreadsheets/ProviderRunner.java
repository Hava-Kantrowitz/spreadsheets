package edu.cs3500.spreadsheets;

import java.io.FileNotFoundException;
import java.io.FileReader;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.provider.view.IView;
import edu.cs3500.spreadsheets.provider.view.JPanelGrid;
import edu.cs3500.spreadsheets.view.SpreadsheetEditableView;

/**
 * Runner for the provider's gui view. Used solely for testing purposes.
 */
public class ProviderRunner {
  /**
   * Entry into running the gui view. This main is solely for testing purposes.
   * @param args command line arguments to the program
   * @throws FileNotFoundException if the given file does not exist
   */
  public static void main(String[] args) throws FileNotFoundException {
    BasicSpreadsheet sheet = new BasicSpreadsheet();
    sheet.initializeSpreadsheet(new FileReader("C:\\Users\\havak\\IdeaProjects\\nextTry\\src\\edu\\cs3500\\spreadsheets\\testFiles\\testingSpecial"));
    //IView view = new JPanelGrid(sheet);
    //view.render();
  }
}
