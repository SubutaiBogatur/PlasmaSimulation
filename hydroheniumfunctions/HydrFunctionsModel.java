package hydroheniumfunctions;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jurii Tikhonov and Aleksandr Tukallo on 04.04.2016.
 */
public class HydrFunctionsModel implements Model, Observable {
    // The atom we work with (currently only one) and array of names of different elements (full and short).
    private Atom atom;
    private List<Observer> observers;

    public HydrFunctionsModel() {
        observers = new LinkedList();
    }

    @Override
    public void inputAtomParameters(int a, int number, String shortName, int L, double S, int J) {
        atom = new Atom(number, shortName,L, S, J);
        create();
    }

    @Override
    public void changeElectronShell(int a, int level, int orbital, int delta) {
        atom.changeElectronShell(level, orbital, delta);
        changeElLayer();
    }

    @Override
    public String buildElectronShellString(int a) {
        return atom.buildElectronShellString();
    }

    @Override
    public String buildLSJString(int a) {
        return atom.buildLSJString();
    }

    @Override
    public String buildIonString(int a) {
        return atom.buildIonString();
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void create() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    @Override
    public void changeElLayer() {
        for (Observer observer : observers) {
            observer.updateElShell();
        }
    }
}
