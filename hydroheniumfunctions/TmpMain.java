package hydroheniumfunctions;

import org.knowm.xchart.*;

/**
 * Created by Jurii Tikhonov and Aleksandr Tukallo on 08.04.2016
 */

//TODO: Class must be integrated
public class TmpMain {
    public static void main(String[] args) throws Exception {
        //int numberOfDots = 10000;
        int n = 2;
        double z = 1.0;
        double nEffective = 3;
        double a_1 = 1.0;
        double closenessToFinish = 1E-3;

        double[][] series = Counter.hydrogenLikeCounter(n, (double)n, z, a_1, n * 100, closenessToFinish);
        XYChart chart = QuickChart.getChart("Chart of wave function for " + n + "S1 for Hydrogen", "X", "Y", "y(x)", series[0], series[1]);
        new SwingWrapper(chart).displayChart();

/*
        for (int i = 1; i <= 10; i++) {
            double[][] series = Counter.hydrogenLikeCounter(i, (double) i, z, a_1, i * 100, closenessToFinish);
            Chart_XY chart = QuickChart.getChart("Chart of " + i + "S1", "X", "Y", "y(x)", series[0], series[1]);
            new SwingWrapper(chart).displayChart();
        }
*/
    }
}
