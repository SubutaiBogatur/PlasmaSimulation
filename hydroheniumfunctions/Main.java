package hydroheniumfunctions;

import java.awt.*;

/**
 * Created by Jurii Tikhonov and Aleksandr Tukallo on 10.04.2016.
 */
public class Main{
    public static void main(String[] arg) {
        EventQueue.invokeLater(() -> {
            HydrFunctionsModel h = new HydrFunctionsModel();
            Controller c = new Controller(h);
            View v = new View(c, h);
            h.addObserver(v);
        });
    }
}
