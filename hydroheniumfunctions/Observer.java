package hydroheniumfunctions;

/**
 * Created by Jurii Tikhonov and Aleksandr Tukallo on 04.04.2016.
 */
public interface Observer {
    // Updating info about atom in observers, methods are being called from the observing one.
    void update();
    void updateElShell();
}
