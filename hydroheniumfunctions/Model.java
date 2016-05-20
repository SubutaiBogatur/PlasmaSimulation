package hydroheniumfunctions;

/**
 * Created by Jurii Tikhonov and Aleksandr Tukallo on 04.04.2016.
 */
// TODO: 1. LD/ST 2. LSJ - проверка 3. Exc если нет файла с именами.
public interface Model {
    // First argument in functions below will be used later to support multiple atoms.

    // Gets number of the atom.
    void inputAtomParameters(int a, int number, String shortName, int L, double S, int J);

    // Gets level, orbital and change: addition (delta > 0) or removal (delta < 0) of 1 electron.
    void changeElectronShell(int a, int level, int orbital, int delta);

    // Запросы.
    String buildElectronShellString(int a);
    String buildLSJString(int a);
    String buildIonString(int a);
}
