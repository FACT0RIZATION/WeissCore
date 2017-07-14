package weissmoon.core.entity;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.*;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import weissmoon.core.WeissCore;
import weissmoon.core.compat.RWBYCompat;
import weissmoon.core.handler.ConfigurationHandler;
import weissmoon.core.lib.WeissMods;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for linear and falling projectiles.
 */
public abstract class EntityBulletCore extends Entity implements IEntityAdditionalSpawnData{


    protected EntityLivingBase thrower;
    protected String throwerName;
    protected int ticksInAir;
    /**Collide with liquids */
    protected boolean collideLiquid = false;
    private static final String COLLIDELIQUIDS = "liquids";
    /**Phase through certain blocks */
    protected boolean phase = true;
    private static final String PHASESTRING = "phase";
    /** Stun enemies */
    protected boolean ice = false;
    private static final String ICESTRING = "ice";
    protected boolean crystallize = false;
    /**Item appears in collided player's inventory */
    protected boolean michaelis = false;
    private static final String MICHAELISSTRING = "michaelis";
    /**Apply gravity for motion*/
    protected boolean gravity = false;
    protected float gravityFloat = 0.03F;
    private static final String GRAVATYSTRING = "gravity";
    private static final String GRAVATYFLOATSTRING = "gravityFloat";
    /**Apply drag for motion*/
    protected boolean drag = false;
    protected float motionFactor = 0.95F;
    private static final String DRAGSTRING = "drag";
    private static final String MOTIONSTRING = "motion";
    /**Water drag*/
    protected boolean waterDrag = false;
    protected float waterDragFactor = 0.8F;
    private static final String WATERDRAGSTRING = "waterDrag";
    private static final String WATERMOTIONSTRING = "waterMotion";
    /**Entity phase list*/
    protected List<Material> phaseList;

    public EntityBulletCore (World p_i1582_1_){
        super(p_i1582_1_);
        setSize(1.0F, 1.0F);
    }

    //reverse shoot backwards
    public EntityBulletCore (World world, EntityLivingBase entity, boolean reverse){
        this(world);
        this.thrower = entity;
        this.throwerName = entity.getName();
        if (reverse){
            this.posX += MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * 0.2F;
            this.posZ += MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * 0.15F;
        }else{
            this.posX -= MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * 0.2F;
            this.posZ -= MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * 0.15F;
        }
        this.posY -= 0.10000000149011612D;
        setLocationAndAngles(entity.posX, entity.posY + entity.getEyeHeight(), entity.posZ, entity.rotationYaw, entity.rotationPitch);
        if(reverse){
            this.motionX = (MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F));
            this.motionZ = (-MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F));
            this.motionY = (MathHelper.sin(this.rotationPitch / 180.0F * 3.1415927F));
        }else{
            this.motionX = (-MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F));
            this.motionZ = (MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F));
            this.motionY = (-MathHelper.sin(this.rotationPitch / 180.0F * 3.1415927F));
        }
        setHeading(this.motionX, this.motionY, this.motionZ, (float) getSpeed(this), (float)getBulletAccuracy());
    }

    @Override
    public void onUpdate (){
        if (((this.getThrower() != null) && (this.getThrower().isDead))){
            setDead();
        }else{
            lastTickPosX = posX;
            lastTickPosY = posY;
            lastTickPosZ = posZ;

            super.onEntityUpdate();

            ++this.ticksInAir;

            Vec3d vec3 = new Vec3d(this.posX, this.posY, this.posZ);
            Vec3d vec31 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            RayTraceResult movingobjectposition = this.worldObj.rayTraceBlocks(vec3, vec31, collideLiquid);
            vec3 = new Vec3d(this.posX, this.posY, this.posZ);
            vec31 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

            //Phase through some Blocks
            if (movingobjectposition != null && movingobjectposition.typeOfHit == RayTraceResult.Type.BLOCK){
                IBlockState clollided = this.worldObj.getBlockState(movingobjectposition.getBlockPos());
                if (this.phase){
                    List<Material> list = this.phaseList;
                    for (int i = 0; i < list.size(); i++){
                        if (clollided.getMaterial() == list.get(i)){
                            WeissCore.proxy.spawnBlockparticles(movingobjectposition, this.dimension, new Vec3d(Math.abs(this.motionX), Math.abs(this.motionY), Math.abs(this.motionZ)), false);
                            movingobjectposition = null;
                            break;
                        }
                    }
                }
            }

            if (movingobjectposition != null){
                vec31 = new Vec3d(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
            }

            if (!this.worldObj.isRemote){
                Entity entity = null;
                List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
                double d0 = 0.0D;

                for (int i = 0; i < list.size(); i++){
                    Entity entity1 = (Entity)list.get(i);

                    if ((entity1.canBeCollidedWith()) && ((!entity1.isEntityEqual(this.getThrower())) || (this.ticksInAir >= 25))){
                        float f = 0.3F;
                        AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().expand(f, f, f);
                        RayTraceResult movingobjectposition1 = axisalignedbb.calculateIntercept(vec3, vec31);

                        if (movingobjectposition1 != null){
                            double d1 = vec3.distanceTo(movingobjectposition1.hitVec);

                            if ((d1 < d0) || (d0 == 0.0D)){
                                entity = entity1;
                                d0 = d1;
                            }
                        }
                    }
                }

                if (entity != null){
                    movingobjectposition = new RayTraceResult(entity);
                }
            }

            //Crystallize
            if (movingobjectposition != null) {
                if (WeissMods.instance.isRWBYLoaded() && RWBYCompat.isEntityCrystallizer(movingobjectposition) && (!this.ice) && this.crystallize) {
                    this.ice = true;
                    return;
                }
            }

            //Michaelis
            if ((movingobjectposition != null) && (movingobjectposition.entityHit != null) && ((movingobjectposition.entityHit instanceof EntityPlayer))){
                EntityPlayer entityplayer = (EntityPlayer)movingobjectposition.entityHit;

                if ((entityplayer.capabilities.disableDamage) || (this.getThrower().getName().equals(entityplayer.getName())) || (((this.getThrower() instanceof EntityPlayer)) && (!((EntityPlayer)this.getThrower()).canAttackPlayer(entityplayer)))){
                    movingobjectposition = null;
                }
                if ((!ConfigurationHandler.michaelis) && this.michaelis){
                    this.michaelisColision(movingobjectposition);
                }
            }

            if (movingobjectposition != null){
                onImpact(movingobjectposition);
            }

            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
            float f1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.rotationYaw = ((float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / 3.141592653589793D) + 90.0F);

            for (this.rotationPitch = (float)(Math.atan2((double)f1, this.motionY) * 180.0D / Math.PI) - 90.0F; this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F){
                ;
            }

            while(this.rotationPitch - this.prevRotationPitch >= 180.0F){
                this.prevRotationPitch += 360.0F;
            }

            while(this.rotationYaw - this.prevRotationYaw < -180.0F){
                this.prevRotationYaw -= 360.0F;
            }

            while(this.rotationYaw - this.prevRotationYaw >= 180.0F){
                this.prevRotationYaw += 360.0F;
            }

            this.rotationPitch = (this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F);
            this.rotationYaw = (this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F);
            float f2 = getMotionFactor();
            float f4 = getGravityVelocity();

            if (this.waterDrag) {
                if (isInWater()) {
                    for (int j = 0; j < 4; j++) {
                        float f3 = 0.25F;
                        this.worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * f3, this.posY - this.motionY * f3, this.posZ - this.motionZ * f3, this.motionX, this.motionY, this.motionZ);
                    }
                    f2 = getMotionFactor() * getWaterDragFactor();
                }
            }

            if (this.drag) {
                this.motionX *= f2;
                this.motionY *= f2;
                this.motionZ *= f2;
            }
            if (this.gravity)
                this.motionY -= f4;
            setPosition(this.posX, this.posY, this.posZ);
        }
    }

    public void setHeading (double moX, double moY, double moZ, float vel, float acu){
        moX += this.rand.nextGaussian() * acu;
        moY += this.rand.nextGaussian() * acu;
        moZ += this.rand.nextGaussian() * acu;
        this.motionX = moX * vel;
        this.motionY = moY * vel;
        this.motionZ = moZ * vel;
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;
    }

    protected void onImpact (RayTraceResult p_70184_1_){
        setDead();
    }

    public float getMotionFactor (){
        return this.motionFactor;
    }

    public float getWaterDragFactor(){
        return this.waterDragFactor;
    }

    @Override
    protected void entityInit (){
        this.setupPhaseList();
    }

    public ResourceLocation getTexture (){
        return null;
    }

    public float getBulletAccuracy (){
        return 0.001F;
    }

    //Gradle issues
    protected void writeEntityToNBT (NBTTagCompound compound){
        writeEntityToNBTCore(compound);
    }

    protected void writeEntityToNBTCore (NBTTagCompound compound){
        compound.setTag("direction", newDoubleNBTList(new double[]{this.motionX, this.motionY, this.motionZ}));

        if (((this.throwerName == null) || (this.throwerName.length() == 0)) && (this.thrower != null) && ((this.thrower instanceof EntityPlayer))){
            this.throwerName = this.thrower.getName();
        }

        compound.setString("ownerName", this.throwerName == null ? "" : this.throwerName);
        compound.setBoolean(COLLIDELIQUIDS, this.collideLiquid);
        compound.setBoolean(PHASESTRING, this.phase);
        compound.setBoolean(ICESTRING, this.ice);
        compound.setBoolean(MICHAELISSTRING, this.michaelis);
        compound.setBoolean(DRAGSTRING, this.drag);
        compound.setFloat(MOTIONSTRING, this.motionFactor);
        compound.setBoolean(GRAVATYSTRING, this.gravity);
        compound.setFloat(GRAVATYFLOATSTRING, this.gravityFloat);
        compound.setBoolean(WATERDRAGSTRING, this.waterDrag);
        compound.setFloat(WATERMOTIONSTRING, this.waterDragFactor);
    }

    //Gradle issues
    protected void readEntityFromNBT (NBTTagCompound compound){
        readEntityFromNBTCore(compound);
    }

    protected void readEntityFromNBTCore (NBTTagCompound compound){

        if ((this.throwerName != null) && (this.throwerName.length() == 0)){
            this.throwerName = null;
            this.setDead();
        }

        if (compound.hasKey("direction", 9)){
            NBTTagList nbttaglist = compound.getTagList("direction", 6);
            this.motionX = nbttaglist.getDoubleAt(0);
            this.motionY = nbttaglist.getDoubleAt(1);
            this.motionZ = nbttaglist.getDoubleAt(2);
        }else{
            setDead();
        }

        this.ice = compound.getBoolean(ICESTRING);
        this.michaelis = compound.getBoolean(MICHAELISSTRING);
        this.drag = compound.getBoolean(DRAGSTRING);
        this.motionFactor = compound.getFloat(MOTIONSTRING);
        this.gravity = compound.getBoolean(GRAVATYSTRING);
        this.gravityFloat = compound.getFloat(GRAVATYFLOATSTRING);
        this.waterDrag = compound.getBoolean(WATERDRAGSTRING);
        this.waterDragFactor = compound.getFloat(WATERMOTIONSTRING);
    }

    @Override
    public void writeSpawnData (ByteBuf buffer){
        buffer.writeDouble(this.motionX);
        buffer.writeDouble(this.motionY);
        buffer.writeDouble(this.motionZ);
        buffer.writeBoolean(this.drag);
        buffer.writeFloat(this.motionFactor);
        buffer.writeBoolean(this.gravity);
        buffer.writeFloat(this.gravityFloat);
        buffer.writeBoolean(this.waterDrag);
        buffer.writeFloat(this.waterDragFactor);
    }

    @Override
    public void readSpawnData (ByteBuf additionalData){
        this.motionX = additionalData.readDouble();
        this.motionY = additionalData.readDouble();
        this.motionZ = additionalData.readDouble();
        float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(this.motionY, (double)f) * 180.0D / Math.PI);
        this.drag = additionalData.readBoolean();
        this.motionFactor = additionalData.readFloat();
        this.gravity = additionalData.readBoolean();
        this.gravityFloat = additionalData.readFloat();
        this.waterDrag = additionalData.readBoolean();
        this.waterDragFactor = additionalData.readFloat();
    }

    public EntityLivingBase getThrower()
    {
        if (this.thrower == null && this.throwerName != null && this.throwerName.length() > 0)
        {
            this.thrower = this.worldObj.getPlayerEntityByName(this.throwerName);
        }

        return this.thrower;
    }

    public float getGravityVelocity(){
        return this.gravityFloat;
    }

    public void setCollideLiquid(boolean bol1){
        this.collideLiquid = bol1;
    }
    public void setPhase(boolean bol1){
        this.phase = bol1;
    }
    public void setIce(boolean bol1){
        this.ice = bol1;
    }
    public void setMichaelis(boolean bol1){
        this.michaelis = bol1;
    }
    public void setDrag(boolean bol1){
        this.drag = bol1;
    }
    public void setGravity(boolean bol1){
        this.gravity = bol1;
    }
    public float getSpeed(EntityBulletCore entity){
        return 1;
    }

    protected void setupPhaseList(){
        this.phaseList = new ArrayList<Material>();
    }

    protected abstract void michaelisColision(RayTraceResult p_70184_1_);
}
