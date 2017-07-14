package weissmoon.core.control;

import java.io.*;
import java.util.Map;

public class KeyInputMap{
    static Map<String, KeyInputMap> playerInput = new java.util.HashMap();
    public KeyInputMap lastSentMap;

    public static KeyInputMap getInputMapFor (String playerName){
        KeyInputMap map = (KeyInputMap)playerInput.get(playerName);
        if (map == null){
            map = new KeyInputMap(playerName);
        }
        return map;
    }


    public boolean sneakKey;

    public boolean propelKey;

    public KeyInputMap (KeyInputMap master){
        setTo(master);
    }

    public KeyInputMap (String playerName){
        playerInput.put(playerName, this);
        this.lastSentMap = new KeyInputMap(this);
    }

    public boolean writeToStream (DataOutputStream stream){
        try{
            stream.writeBoolean(this.sneakKey);
            stream.writeBoolean(this.propelKey);
            return true;
        }catch( IOException e ) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean readFromStream (DataInputStream stream){
        try{
            this.sneakKey = stream.readBoolean();
            this.propelKey = stream.readBoolean();
            return true;
        }catch( IOException e ) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean equals (Object obj){
        try{
            KeyInputMap other = (KeyInputMap)obj;
            return (other.sneakKey == this.sneakKey) && (other.propelKey == this.propelKey);
        }catch( ClassCastException e ) {
        }

        return false;
    }

    public void setTo (KeyInputMap master){
        this.sneakKey = master.sneakKey;
        this.propelKey = master.propelKey;
    }

    public boolean hasChanged (){
        return equals(this.lastSentMap);
    }

    public void refresh (){
        this.lastSentMap.setTo(this);
    }
}
