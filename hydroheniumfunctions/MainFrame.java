package hydroheniumfunctions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by Jurii Tikhonov and Aleksandr Tukallo on 09.04.2016.
 */
// TODO: Imrove class style
public class MainFrame extends JFrame implements MouseListener {
    private JTextField inputBoxElementName, inputLSJ, inputShellAndLevel;
    private JLabel electronicLayersString, informingString, echange, information1, information2, information3, ion, symbolsWithLSJ;
    private JButton okButton, plusButton, minusButton, saveButton, loadButton, plotButton;
    private static final Font font15 = new Font("Verdana", Font.PLAIN, 15), font20 = new Font("Verdana", Font.PLAIN, 20);
    private final Controller controller;

    public MainFrame(Controller controller) {
        super("Plasma Module");
        this.controller = controller;
        displayInput();
        displayDifferentInformation();
        displayButtons();
        this.setVisible(true);
        this.setBounds(0,0,800,500);
        this.setLayout(null);
        if(GlobalVariables.loggingEnabled)
           System.out.println("Main running");
    }

    public void update(String ionString, String lsj, String elLayer) {
        if(GlobalVariables.loggingEnabled)
         System.out.println(ionString);
        ion.setText(ionString);
        symbolsWithLSJ.setText(lsj);
        electronicLayersString.setText(elLayer);
    }

    public void updateElShell(String ionString, String elLayer) {
        ion.setText(ionString);
        electronicLayersString.setText(elLayer);
    }

    private void displayInput() {
        // inputBoxElementName for name of element or number of electrons
        inputBoxElementName = new JTextField();
        inputBoxElementName.setBounds(330, 90, 100, 40);
        this.getContentPane().add(inputBoxElementName);
        // inputLSJ for L, S, J
        inputLSJ = new JTextField();
        inputLSJ.setBounds(330, 150, 100, 40);
        this.getContentPane().add(inputLSJ);
        //
        inputShellAndLevel = new JTextField();
        inputShellAndLevel.setBounds(330, 210, 100, 40);
        this.getContentPane().add(inputShellAndLevel);
    }

    private void displayDifferentInformation() {
        // ion is an element's name with oxidation state
        ion = new JLabel();
        ion.setBounds(20, 248, 250, 50);
        ion.setSize(700, 100);
        ion.setFont(font20);
        this.getContentPane().add(ion);
        // electronicLayersString is a string with all electronic layers or a mistake if elements is not correct
        electronicLayersString = new JLabel();
        electronicLayersString.setBounds(120, 250, 800, 40);
        electronicLayersString.setSize(800, 100);
        electronicLayersString.setFont(font15);
        this.getContentPane().add(electronicLayersString);
        // informingString is a string under electronicLayersString mostly informing about correctness of input
        informingString = new JLabel();
        informingString.setBounds(120, 280, 400, 40);
        informingString.setSize(700, 100);
        informingString.setFont(font15);
        this.getContentPane().add(informingString);
        // echange informs about mistakes while changing number of electrons on layers
        echange = new JLabel();
        echange.setBounds(120, 310, 400, 40);
        echange.setSize(700, 100);
        echange.setFont(font15);
        this.getContentPane().add(echange);
        //
        information1 = new JLabel("Enter the name or number of electrons:");
        information1.setBounds(20, 60, 250, 50);
        information1.setSize(400, 100);
        information1.setFont(font15);
        this.getContentPane().add(information1);
        //
        information2 = new JLabel("Enter the 'L', 'S' and 'J' parameters:");
        information2.setBounds(20, 120, 250, 50);
        information2.setSize(400, 100);
        information2.setFont(font15);
        this.getContentPane().add(information2);
        //
        information3 = new JLabel("Enter the orbital change:");
        information3.setBounds(20, 180, 250, 50);
        information3.setSize(400, 100);
        information3.setFont(font15);
        this.getContentPane().add(information3);
        // symbolsWithLSJ are 3 symbols to the right from electronicLayersString
        symbolsWithLSJ = new JLabel();
        symbolsWithLSJ.setBounds(720, 248, 250, 50);
        symbolsWithLSJ.setSize(700, 100);
        symbolsWithLSJ.setFont(font20);
        this.getContentPane().add(symbolsWithLSJ);
    }

    private void displayButtons() {
        //
        okButton = new JButton("Ok");
        okButton.setBounds(450, 155, 50, 30);
        okButton.addMouseListener(this);
        this.getContentPane().add(okButton);
        //
        plusButton = new JButton("+");
        plusButton.setBounds(450, 215, 50, 30);
        plusButton.addMouseListener(this);
        plusButton.setFont(font15);
        plusButton.setEnabled(false);
        this.getContentPane().add(plusButton);
        //
        minusButton = new JButton("-");
        minusButton.setBounds(510, 215, 50, 30);
        minusButton.addMouseListener(this);
        minusButton.setFont(font15);
        minusButton.setEnabled(false);
        this.getContentPane().add(minusButton);
        //
        saveButton = new JButton("Save");
        saveButton.setBounds(640, 400, 100, 30);
        saveButton.addMouseListener(this);
        saveButton.setFont(font15);
        this.getContentPane().add(saveButton);
        //
        loadButton = new JButton("Load");
        loadButton.setFont(font15);
        loadButton.setBounds(640, 360, 100, 30);
        loadButton.addMouseListener(this);
        this.getContentPane().add(loadButton);
        //
        plotButton = new JButton("Plot");
        plotButton.setFont(font15);
        plotButton.setBounds(530, 360, 100, 30);
        plotButton.addMouseListener(this);
        this.getContentPane().add(plotButton);
    }

    // Метод для обработки +/-. (Вынес, чтоб не было дубликации кода)
    private void pmButtonClick(int delta) {
        if (inputShellAndLevel.getText().length() != 2) {
            informingString.setText("Incorrect input.");
        } else {
            char sl[] = inputShellAndLevel.getText().toCharArray();
            int a[] = new int[2];
            a[0] = sl[0] - '1';
            a[1] = -1;
            switch (sl[1]) {
                case 's':
                    a[1] = 0;
                    break;
                case 'p':
                    a[1] = 1;
                    break;
                case 'd':
                    a[1] = 2;
                    break;
                case 'f':
                    a[1] = 3;
                    break;
                case 'g':
                    a[1] = 4;
                    break;
                case 'h':
                    a[1] = 5;
                    break;
            }
            // Проверка на правильность ввода уровня и оболочки.
            if (a[0] > -1 && a[0] < 6 && a[1] > -1 && a[1] <= a[0]) {
                controller.changeElectronShell(0, a[0], a[1], delta);
            } else {
                informingString.setText("Incorrect input.");
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (clicked.equals(okButton)) {
            int number = 0, L, S, J;
            boolean flag = true;
            try {
                number = Integer.parseInt(inputBoxElementName.getText());
            } catch (NumberFormatException exc) {
                flag = false;
            }
            try {
                Scanner scan = new Scanner(inputLSJ.getText());
                L = scan.nextInt();
                S = scan.nextInt();
                J = scan.nextInt();
                if (flag) {
                    controller.inputAtom(0, number, L, S, J);
                } else {
                    controller.inputAtom(0, inputBoxElementName.getText(), L, S, J);
                }
                informingString.setText("");
                plusButton.setEnabled(true);
                minusButton.setEnabled(true);
            } catch (AtomInputException exc) {
                informingString.setText("Incorrect or unsupported atom.");
                electronicLayersString.setText("");
                symbolsWithLSJ.setText("");
                ion.setText("");
                plusButton.setEnabled(false);
                minusButton.setEnabled(false);
            } catch (NoSuchElementException exc) {
                informingString.setText("Incorrect input.");
                electronicLayersString.setText("");
                symbolsWithLSJ.setText("");
                ion.setText("");
                plusButton.setEnabled(false);
                minusButton.setEnabled(false);
            }
        } else if (clicked.equals(plusButton)) {
            pmButtonClick(1);
        } else if (clicked.equals(minusButton)) {
            pmButtonClick(-1);
        } else if (clicked.equals(saveButton)) {

        } else if (clicked.equals(loadButton)) {

        } else if (clicked.equals(plotButton)) {

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
