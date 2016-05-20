package hydroheniumfunctions;

/**
 * Created by Jurii Tikhonov and Aleksandr Tukallo on 04.04.2016.
 */
public interface Observable {
    // Addition of an observer.
    void addObserver(Observer o);

    // Removal of an observer.
    void removeObserver(Observer o);

    // Notifying the observer about the change of the parameters.
    void create();
    void changeElLayer();

    // TODO: other merhods to notify observers.
}
