package weissmoon.core.helper;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Weissmoon on 3/27/17.
 */
public class PlayerHelper {

    public static void removeExperience(EntityPlayer player, int amount){
        int j = 0 + player.experienceTotal;
        if (amount > j)
        {
            amount = j;
        }

        if(player.experienceTotal == 0 && player.experience != 0)
            player.experience = 0;

            player.experience -= (float)amount / (float)player.xpBarCap();

        for (player.experienceTotal -= amount; player.experience <= 0.0F; player.experience /= (float)player.xpBarCap())
        {
            player.experience = (player.experience + 1.0F) * (float)player.xpBarCap();
            player.removeExperienceLevel(1);
        }
    }
}
