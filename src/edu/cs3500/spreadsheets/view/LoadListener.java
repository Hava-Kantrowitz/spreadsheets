package edu.cs3500.spreadsheets.view;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import edu.cs3500.spreadsheets.controller.Features;

/**
 * Represents an action listener for the load button in the file menu with the purpose of alerting
 * the controller to load a new file if the load is selected.
 */
public class LoadListener implements ActionListener {

  private JFrame mainFrame;
  private Features controller;

  /**
   * This is the constructor for the load listener.
   *
   * @param mainFrame  the main JFrame of the application for reference position
   * @param controller the controller for connection with the model and view changes
   */
  public LoadListener(JFrame mainFrame, Features controller) {
    this.mainFrame = mainFrame;
    this.controller = controller;
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    // the option menu
    JOptionPane optionMenu = new JOptionPane("Enter the file path of a file from your computer:");
    String[] options = {"Cancel", "Load"};
    optionMenu.setOptions(options);
    JTextField text = new JTextField();
    optionMenu.add(text);


    // making the option menu into a Dialog window
    Dialog message = optionMenu.createDialog(optionMenu, "Load Menu");

    // setting to the center horizontally
    message.setLocation((int) ((int) (mainFrame.getWidth() * .5) - optionMenu.getWidth() * .5),
            mainFrame.getY());  // setting to the top
    message.setVisible(true);


    // once closing dialog if load has been clicked (not on close)
    if (optionMenu.getValue() != null && optionMenu.getValue().equals("Load")) {
      // activating the controller now that the load method has been selected
      controller.onLoadSelect(text.getText());
    }

  }
}
