package hydroheniumfunctions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Jurii Tikhonov and Aleksandr Tukallo on 04.04.2016.
 */
// Controller knows nothing about exact realisation of a model, but it is able to
// transfer arguments from the view through the interface to the model.
// It's function is to process data correctly and send it to the model.

public class Controller {
    private Model model;
    private final int numberOfElementsSupported = 50;
    private final String nameTable[][] = new String[numberOfElementsSupported][2], nameFile = "src/namesOfElements.in";

    // Gets a realization of a model.
    Controller(Model model) {
        this.model = model;
        loadElementNamesFromFile();
    }

    // First argument in functions below will be used later to support multiple atoms.

    // Input of the atom and its parameters. Function sends its interpretation.
    public void inputAtom(int a, int number, int L, int S, int J) throws AtomInputException {
        if (number <= numberOfElementsSupported) {
            model.inputAtomParameters(0, number, nameTable[number - 1][0], L, S, J);
        } else {
            throw new AtomInputException(String.valueOf(number));
        }
    }
    public void inputAtom(int a, String name, int L, int S, int J) throws AtomInputException {
        boolean flag = false;
        for (int i = 0; i < numberOfElementsSupported; i++) {
            if (name.equals(nameTable[i][0]) || name.equals(nameTable[i][1])) {
                model.inputAtomParameters(0, i + 1, nameTable[i][0], L, S, J);
                flag = true;
            }
        }
        if (!flag) {
            throw new AtomInputException(name);
        }
    }

    // Withdraw or addition (delta < 0 | delta > 0 accordingly) of the electron from the layer or orbital.
    public void changeElectronShell(int a, int level, int orbital, int delta) {
        model.changeElectronShell(0, level, orbital, delta);
    }

    // Loading names from file.
    private void loadElementNamesFromFile() {
        try {
            BufferedReader input = new BufferedReader(new FileReader(nameFile));
            for (int i = 0; i < numberOfElementsSupported; i++) {
                String tmp = input.readLine();
                String[] splitRes = tmp.split(" ");
                nameTable[i][1] = splitRes[2];
                nameTable[i][0] = splitRes[1];
            }
        } catch (FileNotFoundException e) {
            if(GlobalVariables.loggingEnabled)
                System.out.println("File with names of elements wasn't found.");
            System.exit(0);
        } catch (IOException e) {
            if(GlobalVariables.loggingEnabled)
                System.out.println("IO exception while trying to read elements' names.");
            System.exit(0);
        }
    }
}
