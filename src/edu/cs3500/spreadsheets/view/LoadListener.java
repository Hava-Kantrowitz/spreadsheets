package edu.cs3500.spreadsheets.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;

import edu.cs3500.spreadsheets.controller.EditableSheetController;

public class LoadListener implements ActionListener {

  private JFrame mainFrame;
  private EditableSheetController controller;

  LoadListener(JFrame mainFrame, EditableSheetController controller){
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
    if(optionMenu.getValue() != null && optionMenu.getValue().equals("Load")){
      // activating the controller now that the load method has been selected
      controller.onLoadSelect(text.getText());
    }

  }
}