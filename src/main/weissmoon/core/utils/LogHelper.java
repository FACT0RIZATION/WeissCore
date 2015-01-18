package weissmoon.core.utils;

import cpw.mods.fml.common.FMLLog;
import org.apache.logging.log4j.Level;

public class LogHelper {


    public static void log (String modID, Level level, Object object){

        FMLLog.log(modID, level, String.valueOf(object));
    }
}
