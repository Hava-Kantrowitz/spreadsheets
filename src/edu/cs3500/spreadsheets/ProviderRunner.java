package edu.cs3500.spreadsheets;

import java.io.FileNotFoundException;
import java.io.FileReader;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.model.ProviderAdapter;
import edu.cs3500.spreadsheets.provider.model.IViewWorksheet;
import edu.cs3500.spreadsheets.view.ProviderViewExtender;
import edu.cs3500.spreadsheets.view.ProviderViewRenderer;

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
    sheet.initializeSpreadsheet(new FileReader("/Users/victoriabowen/Desktop/" +
            "NEU_1st_year/ObjectOriented/CS_3500_Projects/spreadsheets/src/" +
            "edu/cs3500/spreadsheets/testFiles/testingSpecial"));
    IViewWorksheet worker = new ProviderAdapter(sheet);
    ProviderViewExtender view = new ProviderViewExtender(worker);
    ProviderViewRenderer render = new ProviderViewRenderer(view);
    render.render();
  }
}
