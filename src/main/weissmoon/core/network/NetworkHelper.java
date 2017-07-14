package weissmoon.core.network;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

public class NetworkHelper{
    public static void writeString (String string, ByteBuf buf){
        try{
            buf.writeShort(string.length());
            writeChars(string, buf);
        }catch( IOException e ) {
            e.printStackTrace();
        }
    }

    public static void writeChars (String s, ByteBuf buf) throws IOException{
        int len = s.length();
        for (int i = 0; i < len; i++){
            writeChar(s.charAt(i), buf);
        }
    }

    public static void writeChar (int v, ByteBuf buf) throws IOException{
        writeShort((short)v, buf);
    }

    public static void writeShort (int v, ByteBuf buf) throws IOException{
        buf.writeShort((short)v);
    }
}
