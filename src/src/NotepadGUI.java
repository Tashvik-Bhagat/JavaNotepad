package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class NotepadGUI extends JFrame implements ActionListener {
    private JTextArea textArea;
    private File currentFile;

    public NotepadGUI() {
        textArea = new JTextArea();
        setTitle("Notepad");
        setSize(600, 400); // Changed size to 600x400
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        JButton newButton = new JButton("New");
        newButton.addActionListener(this);
        newButton.setFocusPainted(false);
        buttonPanel.add(newButton);

        JButton openButton = new JButton("Open");
        openButton.addActionListener(this);
        openButton.setFocusPainted(false);
        buttonPanel.add(openButton);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        saveButton.setFocusPainted(false);
        buttonPanel.add(saveButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        exitButton.setFocusPainted(false);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.NORTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("New")) {
            textArea.setText("");
        } else if (command.equals("Open")) {
            openFile();
        } else if (command.equals("Save")) {
            saveFile();
        } else if (command.equals("Exit")) {
            System.exit(0);
        }
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(currentFile));
                textArea.read(reader, null);
                reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void saveFile() {
        if (currentFile == null) {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                currentFile = fileChooser.getSelectedFile();
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile));
            textArea.write(writer);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
