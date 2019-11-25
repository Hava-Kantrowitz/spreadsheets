package edu.cs3500.spreadsheets.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import edu.cs3500.spreadsheets.controller.EditableSheetController;

/**
 * Represents a listener for the valid input selection of the help menu with the purpose
 * of displaying the valid input message when clicked.
 */
public class ValidInputListener implements ActionListener {
  private SpreadsheetEditableView view;


  /**
   * The constructor for the valid input listener.
   * @param view the view where the message should be displayed
   */
  public ValidInputListener(SpreadsheetEditableView view) {
    this.view = view;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // display a dialog message because showing valid input
    JOptionPane.showMessageDialog(this.view,"Valid input types: \n" +
                    "Functions must be surround by parentheses. \n" +
                    "The supported functions include SUM, SQRT, SUB, PRODUCT, <, HAM \n" +
                    "Ex: =(SUM 3 9) \n" +
                    "Values may be preceded by an equals operand " +
                    "but are not required to have one. \n" +
                    "Ex: 5 or =5 \n" +
                    "Strings must be in quotation marks and " +
                    "may be preceded by an equals operand. \n" +
                    "Ex: \"Hello World\" or =\"Hello World\" \n" +
                    "Boolean values are represented may be preceded by an equals but do" +
                    " not have to be. \n" +
                    "Ex: =true or true \n" +
                    "Cell references can be used on their own or in formulas. \n" +
                    "Ex: A1 or =(SUM A1:B3) ",
            "Valid Input Menu", JOptionPane.PLAIN_MESSAGE);
  }
}
