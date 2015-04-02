package weissmoon.core.lib;

public class WeissMods {

    public static WeissMods instance = new WeissMods();

    private boolean RWBY;
    private boolean GreenSolars;
    private boolean RandAni;
    private boolean Weisscore = true;
    private boolean MusicGun;
    private boolean Omnomnomnicon;
    private boolean NanoNailer;
    private boolean PotatoGun;

    public boolean isRWBYLoaded(){
        return this.RWBY;
    }
    public boolean isGreenSolarsLoaded(){
        return this.GreenSolars;
    }
    public boolean isRandAniLoaded(){
        return this.RandAni;
    }
    public boolean isWeisscoreLoaded(){
        return this.Weisscore;
    }
    public boolean isMusicGunLoaded(){
        return this.MusicGun;
    }
    public boolean isOmnomnomniconLoaded(){
        return this.Omnomnomnicon;
    }
    public boolean isNanoNailerLoaded(){
        return this.NanoNailer;
    }
    public boolean isPotatoGunLoaded(){
        return this.PotatoGun;
    }

    public void setRWBY(){
        this.RWBY = true;
    }
    public void setGreenSolars(){
        this.GreenSolars = true;
    }
    public void setRandAni(){
        this.RandAni = true;
    }
    public void setMusicGun(){
        this.MusicGun = true;
    }
    public void setOmnomnomnicon(){
        this.Omnomnomnicon = true;
    }
    public void setNanoNailer(){
        this.NanoNailer = true;
    }
}
