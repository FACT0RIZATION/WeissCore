package weissmoon.core.client.util;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.Tessellator;

import java.awt.*;

public class ItemRender{
    public static void renderBar2 (int y, double maxDam, double dispDamage, int full, int empty, int back){
        double ratio = dispDamage / maxDam;
        Color fg = new Color(full);
        Color ec = new Color(empty);
        ColorHelper.interpolatedColor(fg, ec, (float)ratio);
        Color bg = new Color(back);
        ColorHelper.interpolatedColor(bg, fg, 0.15f);

        int barLength = (int)Math.round(12.0 * (1 - ratio));

        renderQuad2D(2, y, 0, 12, 1, bg);
        renderQuad2D(2, y, 0, barLength, 1, fg);
    }

    public static void renderQuad2D (double x, double y, double z, double width, double height, Color colorRGBA){
        GL11.glColor4f(colorRGBA.getRed(), colorRGBA.getGreen(), colorRGBA.getBlue(), colorRGBA.getAlpha());
        GL11.glDisable(GL11.GL_TEXTURE_2D);
//        Tessellator tessellator = Tessellator.instance;
//        tessellator.startDrawingQuads();
//        tessellator.addVertex(x, y + height, z);
//        tessellator.addVertex(x + width, y + height, z);
//        tessellator.addVertex(x + width, y, z);
//        tessellator.addVertex(x, y, z);
//        tessellator.draw();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }
}
