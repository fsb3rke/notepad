import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class form1 extends JFrame {
    private JPanel panel1;
    private JTextField texHeaderName;
    private JTextArea texNoteMain;
    private JButton btnSave;
    private JTextField texWriteFile;
    private JTextField texReadFile;

    private boolean isRead = false;
    private boolean isWrite = false;

    private void CreateFile() {
        try {
            File myObj = new File(texWriteFile.getText());
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException b) {
            System.out.println("An error occurred.");
            b.printStackTrace();
        }
    }

    private void WriteFile(int headerSpace) {
        boolean isUseSpace = false;
        try {
            FileWriter myWriter = new FileWriter(texWriteFile.getText());
            if (texHeaderName.getText().length() > 0) {
                myWriter.write("_-_-_-_"+texHeaderName.getText()+"_-_-_-_");
                isUseSpace = true;
            }
            else {
                isUseSpace = false;
            }
            if (isUseSpace) {
                for (int i = 0; i <= headerSpace; i++) {
                    //"\n\n\n\n"
                    System.out.print("\n");
                }
            }

            myWriter.write(texNoteMain.getText());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void CreateWriteFile() {
        CreateFile();
        WriteFile(4);
    }

    private void ReadFile() {
        try {
            File myObj = new File(texReadFile.getText());
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
                texNoteMain.setText(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public form1() {
        add(panel1);
        setSize(700,500);
        setTitle("emoşi");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        String getReadd = texReadFile.getText();
        String getWritee = texWriteFile.getText();

        if (getReadd.length() == 0) {
            btnSave.setText("Write");
        }
        else if (getWritee.length() == 0) {
            btnSave.setText("Read");
        }
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(texNoteMain.getText());
                String getRead = texReadFile.getText();
                String getWrite = texWriteFile.getText();

                if (getRead.length() == 0 && getWrite.length() == 0) {
                    isRead = false;
                    isWrite = true;
                }
                else if (getWrite.length() == 0 && getRead.length() == 0) {
                    isWrite = false;
                    isRead = true;
                }

                if (isRead && !isWrite) {
                    texNoteMain.setText("");
                    ReadFile();
                }
                else if (isWrite && !isRead) {
                    CreateWriteFile();
                }
            }
        });
    }
}
