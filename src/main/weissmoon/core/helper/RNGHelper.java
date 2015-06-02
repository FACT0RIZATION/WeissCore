package weissmoon.core.helper;

import java.util.Random;

public class RNGHelper {
    public static Random random = new Random();

    public static int getRNGInt(){
        return random.nextInt();
    }

    public static int getRNGIntClamp(int top){
        return random.nextInt(top);
    }

        /**
            Returns an integer in a certain range.
         */
    public static int getRNGIntClamp(int lower, int upper){
        int i = getRNGIntClamp(upper);
        while (i < lower){
            i = getRNGIntClamp(upper);
        }
        return i;
    }

    public static Boolean getRNGBoolean(){
        return random.nextBoolean();
    }

    public static Float getRNGFloat(){
        return random.nextFloat();
    }

    public static Double getRNGGaussian(){
        return random.nextGaussian();
    }

        /**
            Return Double Gaussian.
            shift sets the median.
            range sets the size of the of the range for both sides from the middle.
         */
    public static Double getRNGGaussianR(int shift, int range){
        Double results = (getRNGGaussian() * range / 2) + shift;
        return results;
    }
}
