package hydroheniumfunctions;

/**
 * Created by Jurii Tikhonov and Aleksandr Tukallo on 10.04.2016.
 */
// Class processes errors caused by incorrect input.
public class AtomInputException extends Exception {
    private final String atom;

    public AtomInputException(String atom) {
        this.atom = atom;
    }

    public String getName() {
        return atom;
    }
}
