package weissmoon.core.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.util.ResourceLocation;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Originally Tinkerers' Construct code but was adapted.
 */
public class EventCape{
    //private HashMap<String, String> capes = new HashMap<String, String>();
    private List<String> users = new ArrayList();
    private List<String> capes = new ArrayList();

    //public static EventCape instance;

    //@SubscribeEvent
    //public void EntityJoinWorldEvent(entity entity, World world){
    //    if (entity instanceof AbstractClientPlayer){
    //         addCapes(((EntityPlayer) entity).getDisplayName());
    //
    //  }

    public EventCape (String url){
        buildCapes(url);
    }

    public void buildCapes (String capeURL){
        try{
            URL url = new URL(capeURL);
            URLConnection con = url.openConnection();
            InputStream i = con.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(i));
            String str;
            while((str = reader.readLine()) != null){
                if (str.contains(":")){
                    String usrn = str.substring(0, str.indexOf(":"));
                    String cape = str.substring(str.indexOf(":") + 1);
                    //capes.put(usrn, cape);
                    this.users.add(usrn);
                    this.capes.add(cape);
                    addCapes(usrn, cape);
                }
            }
        }catch( IOException e ) {
            e.printStackTrace();
        }
    }

    public void addCapes (String user, String urlcape){
        //for (int i = 0; i < users.size(); i++){
        //if (user == users.get(i)){
        //String capestring =  capes.get(i);
        ThreadDownloadImageData cape = new ThreadDownloadImageData(null, urlcape, null, null);
        Minecraft.getMinecraft().renderEngine.loadTexture(new ResourceLocation("cloaks/" + user), cape);
        //break;

    }
    //}
    //}

    public void addCape (String user){
        for (int i = 0; i < this.users.size(); i++){
            if (user == this.users.get(i)){
                String capestring = (String)this.capes.get(i);
                ThreadDownloadImageData cape = new ThreadDownloadImageData(null, capestring, null, null);
                Minecraft.getMinecraft().renderEngine.loadTexture(new ResourceLocation("cloaks/" + user), cape);
            }
        }
    }
}
