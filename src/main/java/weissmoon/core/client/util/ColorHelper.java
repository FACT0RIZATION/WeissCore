package weissmoon.core.client.util;

import java.awt.*;

public class ColorHelper{

    public static void interpolatedColor (Color origin, Color destination, float factor){
        float x = (1 - factor) * origin.getRed() + factor * destination.getRed();
        float y = (1 - factor) * origin.getGreen() + factor * destination.getGreen();
        float z = (1 - factor) * origin.getBlue() + factor * destination.getBlue();
        float w = (1 - factor) * origin.getAlpha() + factor * destination.getAlpha();
        origin = new Color(x, y, z, w);
    }
}
