package hydroheniumfunctions;

/**
 * Created by Jurii Tikhonov and Aleksandr Tukallo on 09.04.2016.
 */
public class View implements Observer {
    private final MainFrame mf;
    private final Model model;
    private String lsj, elLayer, ion;

    public View(Controller controller, Model model) {
        mf = new MainFrame(controller);
        this.model = model;
    }

    @Override
    public void update() {
        lsj = model.buildLSJString(0);
        elLayer = model.buildElectronShellString(0);
        ion = model.buildIonString(0);
        mf.update(ion, lsj, elLayer);
    }

    @Override
    public void updateElShell() {
        elLayer = model.buildElectronShellString(0);
        ion = model.buildIonString(0);
        mf.updateElShell(ion, elLayer);
    }
}
