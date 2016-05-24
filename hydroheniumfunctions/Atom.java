package hydroheniumfunctions;

/**
 * Created by Jurii Tikhonov and Aleksandr Tukallo on 04.04.2016.
 */
public class Atom {
    // TODO: Think about size of table for electrons

    // Array stores number of electrons in different electronic layers
    private int electrons[][];
    private int number, L, J, charge;
    private double S;
    private String shortName, fullName;

    public static final int defaultElectronsByLayers[] = {2, 6, 10, 14, 18, 22};
    public static final char orbitalNames[] = {'s', 'p', 'd', 'f', 'g', 'h'};

    Atom(int number, String shortName, int L, double S, int J) {
        electrons = new int[GlobalVariables.sizeOfTable][GlobalVariables.sizeOfTable];
        fillElectronShell(number);
        this.shortName = shortName;
        this.number = number;
        this.L = L;
        this.S = S;
        this.J = J;
        charge = 0;
    }

    // Default filling according to aufbau principle (diagonal filling) .
    private void fillElectronShell(int number) {
        int count = number;

        for (int i = 0; i < GlobalVariables.sizeOfTable; i++) {
            for (int j = 0; j <= i; j++) {
                int min = Math.min(count, defaultElectronsByLayers[j]);
                electrons[i][j] += min;
                count -= min;
            }
        }
    }

    // Returns string with layers and orbitals in html-format.
    public String buildElectronShellString() {
        StringBuilder elShell = new StringBuilder(40);
        elShell.append("<html>");
        for (int i = 0; i < GlobalVariables.sizeOfTable; i++) {
            for (int j = 0; j <= i; j++) {
                elShell.append(i + 1);
                elShell.append(orbitalNames[j]);
                elShell.append("<sup>" + electrons[i][j] + "</sup>");
            }
        }
        return elShell.toString();
    }

    // Returns string LSJ in html-format.
    public String buildLSJString() {
        char orbitalCapitalNames[] = {'S', 'P', 'D', 'F', 'G', 'H'};
        StringBuilder atomWS = new StringBuilder(40);
        atomWS.append("<html>");
        atomWS.append("<sup>" + S + "</sup>");
        atomWS.append(orbitalCapitalNames[L]);
        atomWS.append("<sub>" + J + "</sub>");
        return atomWS.toString();
    }

    // Returns ion string (atom and its charge).
    public String buildIonString() {
        return "<html>" + shortName + "<sup>" + Math.abs(charge) + (charge == 0 ? "" : charge > 0 ? "+" : "-") + "</sup>";
    }

    // Adds electron to an exact layer and orbital.
    // By the way it checks overflow of electrons (disables negative values and overflow of positives)
    void changeElectronShell(int level, int orbital, int delta) {
        if (delta > 0) {
            delta = 1;
        } else {
            delta = -1;
        }
        int old = electrons[level][orbital];
        electrons[level][orbital] += delta;
        electrons[level][orbital] = Math.max(electrons[level][orbital], 0);
        electrons[level][orbital] = Math.min(electrons[level][orbital], defaultElectronsByLayers[orbital]);
        // Change of charge according to change of number of electrons.
        if (old != electrons[level][orbital]) {
            charge += electrons[level][orbital] - old;
        }
    }
}
