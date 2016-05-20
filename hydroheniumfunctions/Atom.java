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

    Atom(int number, String shortName, int L, double S, int J) {
        electrons = new int[6][6];
        fillElectronShell(number);
        this.shortName = shortName;
        this.number = number;
        this.L = L;
        this.S = S;
        this.J = J;
        charge = 0;
    }

    // Default filling according to Klichkovkiy rule (diagonal filling) .
    private void fillElectronShell(int number) {
        int count = number;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j <= i; j++) {
                switch (j) {
                    case 0:
                        electrons[i][j] += Math.min(count, 2);
                        count -= Math.min(count, 2);
                        break;
                    case 1:
                        electrons[i][j] += Math.min(count, 6);
                        count -= Math.min(count, 6);
                        break;
                    case 2:
                        electrons[i][j] += Math.min(count, 10);
                        count -= Math.min(count, 10);
                        break;
                    case 3:
                        electrons[i][j] += Math.min(count, 14);
                        count -= Math.min(count, 14);
                        break;
                    case 4:
                        electrons[i][j] += Math.min(count, 18);
                        count -= Math.min(count, 18);
                        break;
                    case 5:
                        electrons[i][j] += Math.min(count, 22);
                        count -= Math.min(count, 22);
                        break;
                }
            }
        }
    }

    // Returns string with layers and orbitals in html-format.
    public String buildElectronShellString() {
        StringBuilder elShell = new StringBuilder(40);
        elShell.append("<html>");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j <= i; j++) {
                elShell.append(i + 1);
                switch (j) {
                    case 0:
                        elShell.append('s');
                        break;
                    case 1:
                        elShell.append('p');
                        break;
                    case 2:
                        elShell.append('d');
                        break;
                    case 3:
                        elShell.append('f');
                        break;
                    case 4:
                        elShell.append('g');
                        break;
                    case 5:
                        elShell.append('h');
                        break;
                }
                elShell.append("<sup>" + electrons[i][j] + "</sup>");
            }
        }
        return elShell.toString();
    }

    // Returns string LSJ in html-format.
    public String buildLSJString() {
        StringBuilder atomWS = new StringBuilder(40);
        atomWS.append("<html>");
        atomWS.append("<sup>" + S + "</sup>");
        switch (L) {
            case 0:
                atomWS.append("S");
                break;
            case 1:
                atomWS.append("P");
                break;
            case 2:
                atomWS.append("D");
                break;
            case 3:
                atomWS.append("F");
                break;
            case 4:
                atomWS.append("G");
                break;
            case 5:
                atomWS.append("H");
                break;
        }
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
        switch (orbital) {
            case 0:
                electrons[level][orbital] = Math.min(electrons[level][orbital], 2);
                break;
            case 1:
                electrons[level][orbital] = Math.min(electrons[level][orbital], 6);
                break;
            case 2:
                electrons[level][orbital] = Math.min(electrons[level][orbital], 10);
                break;
            case 3:
                electrons[level][orbital] = Math.min(electrons[level][orbital], 14);
                break;
            case 4:
                electrons[level][orbital] = Math.min(electrons[level][orbital], 18);
                break;
            case 5:
                electrons[level][orbital] = Math.min(electrons[level][orbital], 22);
                break;
        }
        // Change of charge according to change of number of electrons.
        if (old != electrons[level][orbital]) {
            charge += electrons[level][orbital] - old;
        }
    }
}
