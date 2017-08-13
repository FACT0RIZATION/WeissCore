package weissmoon.core.lib;

public class WeissMods{
    public static final WeissMods instance = new WeissMods();
    private boolean RWBY;
    private final boolean GreenSolars = false;
    private boolean RandAni;
    private final boolean Weisscore = true;
    private boolean WeissRandom;
    private boolean MusicGun;
    private boolean Omnomnomnicon;
    private boolean NanoNailer;
    private boolean PotatoGun;
    private final boolean PolyArmor = false;
    private boolean PiAdditions;
    private boolean VeneerBaubles;
    public static final int RWBYID = 100;
    public static final int GREENSOLARSID = 200;
    public static final int RANDANIID = 300;
    public static final int WEISSCOREID = 400;
    public static final int WEISSRANDOM = 500;
    public static final int MUSICGUNID = 600;
    public static final int OMNOMNOMNICONID = 700;
    public static final int NANONAILERID = 800;
    public static final int POTATOGUNID = 900;
    public static final int POLYARMORID = 1000;
    public static final int PIADDITIONS = 1100;
    public static final int VENEERBAUBLES = 1200;

    public boolean isRWBYLoaded (){
        return this.RWBY;
    }

    public boolean isGreenSolarsLoaded (){
        return this.GreenSolars;
    }

    public boolean isRandAniLoaded (){
        return this.RandAni;
    }

    public boolean isWeisscoreLoaded (){
        return this.Weisscore;
    }

    public boolean isMusicGunLoaded (){
        return this.MusicGun;
    }

    public boolean isOmnomnomniconLoaded (){
        return this.Omnomnomnicon;
    }

    public boolean isNanoNailerLoaded (){
        return this.NanoNailer;
    }

    public boolean isPotatoGunLoaded (){
        return this.PotatoGun;
    }

    public boolean isPolyArmorLoaded (){
        return this.PolyArmor;
    }

    public void setRWBY (){
        this.RWBY = true;
    }

    public void setRandAni (){
        this.RandAni = true;
    }

    public void setMusicGun (){
        this.MusicGun = true;
    }

    public void setOmnomnomnicon (){
        this.Omnomnomnicon = true;
    }

    public void setNanoNailer (){
        this.NanoNailer = true;
    }

    public void setPotatoGun (){
        this.PotatoGun = true;
    }
}
