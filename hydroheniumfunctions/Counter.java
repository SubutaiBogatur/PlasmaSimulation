package hydroheniumfunctions;

import static java.lang.Math.exp;
import static java.lang.Math.pow;
import static java.lang.StrictMath.abs;
import static java.lang.StrictMath.signum;

/**
 * Created by Jurii Tikhonov and Aleksandr Tukallo on 08.04.2016
 */

//TODO: Class must be integrated

//Inv:
// numberOfDots > 0 and is int;
// n > 0 and is int;
// nEffective is a n value got from the experiment. For hydrogen nEffective = n;
// z is a number of element in a periodic table.

public class Counter {

    private static double countNextOperand(int n, double nEffective, double a_1, double xdata, double z) {
        double ydata = 0;
        double a_k = a_1;
        double a_kp1;
        for (int k = 1; k <= n; k++) {
            a_kp1 = (a_k * ((2 * (((double) k / nEffective) - z)) / ((double) k * ((double) k + 1.0))));
            ydata +=
                    ((exp(xdata * ((-1) / nEffective))) / (xdata)) *
                            (pow(xdata, (double) k)) *
                            a_k;
            a_k = a_kp1;
        }
        return ydata;
    }

    //Pre:
    // numberOfDots is number of points be counted;
    // n is main quantum number, it is a number of layer;
    // a1 is a first operand in a row, defaultly it's 1;
    // nEffective is effective quantum number got from experiment. For H: n = (int) nEffective;
    //Post:
    // 2 arrays of values are returned
    public static double[][] hydrogenLikeCounter(int n, double nEffective, double z, double a_1, int numberOfDots, double closenessToFinish) {  //not static
        double xData[] = new double[numberOfDots], yData[] = new double[numberOfDots];
        double sign = 1.0;
        int countZeroCrossings = -2; //-1 cause in zero it's nan
        double optimalLengthOfPlot = -1;
        for (int i = 0; i < numberOfDots; i++) {
            xData[i] = i;
            yData[i] = countNextOperand(n, nEffective, a_1, xData[i],z);
            double tmp = signum(yData[i]);
            if ((sign != tmp) && (tmp != 0.0))
                countZeroCrossings++;
            sign = tmp;

            if ((countZeroCrossings == (n - 1)) && (abs(yData[i]) < closenessToFinish) && (abs(yData[i]) < abs(yData[i - 1]))) { //checks to cut a plot: if a zero is crossed required number of times; if ydata[i] is close to zero; if the plot is getting closer and closer to zero
                optimalLengthOfPlot = i;
                break;
            }
            System.out.println(xData[i] + " " + yData[i] + " " + countZeroCrossings);
        }
        System.out.println(optimalLengthOfPlot);
        //
        xData = new double[numberOfDots];
        yData = new double[numberOfDots];
        for (int i = 0; i < numberOfDots; i++) {
            xData[i] = ((double) i * optimalLengthOfPlot) / ((double) numberOfDots); //xdata is taken in a way to count all the dots in required interval
            yData[i] = countNextOperand(n, nEffective, a_1, xData[i], z);
            System.out.println(xData[i] + " " + yData[i]);
        }
        return new double[][] {xData, yData};
    }
}
