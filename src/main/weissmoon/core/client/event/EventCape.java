package weissmoon.core.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.util.ResourceLocation;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

    /**
        Originally Tinkerers' Construct code but was adapted.
        Doesn't seem very efficient on memory.
     */
public class EventCape {

    //private HashMap<String, String> capes = new HashMap<String, String>();
    private List<String> users = new ArrayList<String>();
    private List<String> capes = new ArrayList<String>();
    
    //public static EventCape instance;

    //@SubscribeEvent
    //public void EntityJoinWorldEvent(entity entity, World world){
    //    if (entity instanceof AbstractClientPlayer){
   //         addCapes(((EntityPlayer) entity).getDisplayName());
//
  //  }

    public EventCape(String url){
        buildCapes(url);
    }
    
    public void buildCapes(String capeURL){
        URL url;
        try{
            url = new URL(capeURL);
            URLConnection con = url.openConnection();
            InputStream i = con.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(i));
            String str;
            while((str = reader.readLine()) != null){
                if(str.contains(":")){
                    String usrn = str.substring(0, str.indexOf(":"));
                    String cape = str.substring(str.indexOf(":") + 1);
                    //capes.put(usrn, cape);
                    users.add(usrn);
                    capes.add(cape);
                    addCapes(usrn, cape);
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        } 
    }

    public void addCapes(String user, String urlcape){
        //for (int i = 0; i < users.size(); i++){
            //if (user == users.get(i)){
                //String capestring =  capes.get(i);
                ThreadDownloadImageData cape = new ThreadDownloadImageData(null, urlcape, null, null);
                Minecraft.getMinecraft().renderEngine.loadTexture(new ResourceLocation("cloaks/" + user), (ITextureObject)cape);
                //break;
            }
      //  }
    //}

    public void addCape(String user){
        for(int i = 0; i < users.size(); i++){
            if (user == users.get(i)){
                String capestring =  capes.get(i);
                ThreadDownloadImageData cape = new ThreadDownloadImageData(null, capestring, null, null);
                Minecraft.getMinecraft().renderEngine.loadTexture(new ResourceLocation("cloaks/" + user), (ITextureObject)cape);
            }
        }
    }

}
