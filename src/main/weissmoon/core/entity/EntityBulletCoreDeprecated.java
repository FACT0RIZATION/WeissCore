package weissmoon.core.entity;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.*;
import net.minecraft.util.*;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.*;
import weissmoon.core.WeissCore;
import weissmoon.core.handler.ConfigurationHandler;

import java.util.List;

/**
 * Base class for linear projectiles.
 * Similar to fireball.
 */
public class EntityBulletCoreDeprecated extends EntityThrowable implements IEntityAdditionalSpawnData{
    protected int posXN = -1;
    protected int posYN = -1;
    protected int posZN = -1;
    protected Block field_145796_h;
    protected boolean inGround;
    public EntityLivingBase thrower;
    private String throwerName;
    private int ticksAlive;
    protected int ticksInAir;
    public double accelerationX;
    public double accelerationY;
    public double accelerationZ;
    /**
     * Phase through certain blocks
     */
    protected boolean phase;
    /**
     * Stun enemies
     */
    public boolean ice;
    /**
     * Item appears in collided player's inventory
     */
    protected boolean michaelis = false;

    public EntityBulletCoreDeprecated (World world){
        super(world);
        setSize(1.0F, 1.0F);
    }

    public EntityBulletCoreDeprecated (World world, EntityLivingBase entity, boolean reverse){
        super(world);
        this.thrower = entity;
        setSize(1.0F, 1.0F);
        setLocationAndAngles(entity.posX, entity.posY + entity.getEyeHeight(), entity.posZ, entity.rotationYaw, entity.rotationPitch);
        if (reverse){
            this.posX += MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * 0.2F;
            this.posZ += MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * 0.15F;
        }else{
            this.posX -= MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * 0.2F;
            this.posZ -= MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * 0.15F;
        }
        this.posY -= 0.10000000149011612D;
        setPosition(this.posX, this.posY, this.posZ);
        this.yOffset = 0.0F;
        float f = 0.4F;
        this.motionX = (-MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F));
        this.motionZ = (MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F));
        this.motionY = (-MathHelper.sin(this.rotationPitch / 180.0F * 3.1415927F));
        if (reverse){
            this.motionX = (0.0D - this.motionX);
            this.motionY = (0.0D - this.motionY);
            this.motionZ = (0.0D - this.motionZ);
        }
        setThrowableHeading(this.motionX, this.motionY, this.motionZ, 0.0F, 0.0F);
    }

    public EntityBulletCoreDeprecated (World p_i1778_1_, double p_i1778_2_, double p_i1778_4_, double p_i1778_6_){
        super(p_i1778_1_);
        this.ticksInAir = 0;
        setSize(0.25F, 0.25F);
        setPosition(p_i1778_2_, p_i1778_4_, p_i1778_6_);
        this.yOffset = 0.0F;
    }

    public void setThrowableHeading (double moX, double moY, double moZ, float acu, float per){
        double d3 = MathHelper.sqrt_double(moX * moX + moY * moY + moZ * moZ);
        moX += this.rand.nextGaussian() * getBulletAccuracy();
        moY += this.rand.nextGaussian() * getBulletAccuracy();
        moZ += this.rand.nextGaussian() * getBulletAccuracy();
        this.accelerationX = (moX / d3 * 0.09D);
        this.accelerationY = (moY / d3 * 0.09D);
        this.accelerationZ = (moZ / d3 * 0.09D);
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;
    }

    @SideOnly(Side.CLIENT)
    public void setVelocity (double p_70016_1_, double p_70016_3_, double p_70016_5_){
        this.motionX = p_70016_1_;
        this.motionY = p_70016_3_;
        this.motionZ = p_70016_5_;

        if ((this.prevRotationPitch == 0.0F) && (this.prevRotationYaw == 0.0F)){
            float f = MathHelper.sqrt_double(p_70016_1_ * p_70016_1_ + p_70016_5_ * p_70016_5_);
            this.prevRotationYaw = (this.rotationYaw = (float)(Math.atan2(p_70016_1_, p_70016_5_) * 180.0D / 3.141592653589793D));
            this.prevRotationPitch = (this.rotationPitch = (float)(Math.atan2(p_70016_3_, f) * 180.0D / 3.141592653589793D));
        }
    }


    public void onUpdate (){
        if (((getThrower() != null) && (getThrower().isDead)) || (!this.worldObj.blockExists((int)this.posX, (int)this.posY, (int)this.posZ))){
            boolean dead = true;
            List<Material> list = WeissCore.instance.phaseList.phaseList;
            for (int i = 0; i < list.size(); i++){
                if (this.worldObj.getBlock((int)this.posX, (int)this.posY, (int)this.posZ).getMaterial() == list.get(i)){
                    dead = false;
                }
            }
            if (dead){
                setDead();
            }
        }else{
            this.lastTickPosX = this.posX;
            this.lastTickPosY = this.posY;
            this.lastTickPosZ = this.posZ;

            super.onEntityUpdate();

            if (this.inGround){
                if (this.worldObj.getBlock(this.posXN, this.posYN, this.posZN) == this.field_145796_h){
                    this.ticksAlive += 1;

                    if (this.ticksAlive == 600){
                        setDead();
                    }

                    return;
                }

                this.inGround = false;
                this.motionX *= this.rand.nextFloat() * 0.2F;
                this.motionY *= this.rand.nextFloat() * 0.2F;
                this.motionZ *= this.rand.nextFloat() * 0.2F;
                this.ticksAlive = 0;
                this.ticksInAir = 0;
            }else{
                this.ticksInAir += 1;
            }

            Vec3 vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
            Vec3 vec31 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            MovingObjectPosition movingobjectposition = this.worldObj.rayTraceBlocks(vec3, vec31);
            vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
            vec31 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

            if (movingobjectposition != null){
                vec31 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
            }

            if (movingobjectposition != null && movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK){
                Block clib = this.worldObj.getBlock(movingobjectposition.blockX, movingobjectposition.blockY, movingobjectposition.blockZ);
                if (this.phase){
                    List<Material> list = WeissCore.instance.phaseList.phaseList;
                    for (int i = 0; i < list.size(); i++){
                        if (clib.getMaterial() == list.get(i)){
                            movingobjectposition = null;
                            break;
                        }
                    }
                }
            }
            if (!this.worldObj.isRemote){
                Entity entity = null;
                List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
                double d0 = 0.0D;

                for (int i = 0; i < list.size(); i++){
                    Entity entity1 = (Entity)list.get(i);

                    if ((entity1.canBeCollidedWith()) && ((!entity1.isEntityEqual(getThrower())) || (this.ticksInAir >= 25))){
                        float f = 0.3F;
                        AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(f, f, f);
                        MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(vec3, vec31);

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
                    movingobjectposition = new MovingObjectPosition(entity);
                }
            }

            if ((movingobjectposition != null) && (movingobjectposition.entityHit != null) && ((movingobjectposition.entityHit instanceof EntityPlayer))){
                EntityPlayer entityplayer = (EntityPlayer)movingobjectposition.entityHit;

                if ((entityplayer.capabilities.disableDamage) || (this.thrower == entityplayer) || (((this.thrower instanceof EntityPlayer)) && (!((EntityPlayer)this.thrower).canAttackPlayer(entityplayer)))){
                    movingobjectposition = null;
                }
                if ((!ConfigurationHandler.michaelis) && this.michaelis){
                    movingobjectposition = null;
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

            for (this.rotationPitch = ((float)(Math.atan2(f1, this.motionY) * 180.0D / 3.141592653589793D) - 90.0F); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F){
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

            if (isInWater()){
                for (int j = 0; j < 4; j++){
                    float f3 = 0.25F;
                    this.worldObj.spawnParticle("bubble", this.posX - this.motionX * f3, this.posY - this.motionY * f3, this.posZ - this.motionZ * f3, this.motionX, this.motionY, this.motionZ);
                }
                f2 = getMotionFactor() * 0.8F;
            }

            this.motionX += this.accelerationX;
            this.motionY += this.accelerationY;
            this.motionZ += this.accelerationZ;
            this.motionX *= f2;
            this.motionY *= f2;
            this.motionZ *= f2;
            setPosition(this.posX, this.posY, this.posZ);
        }
    }


    @SideOnly(Side.CLIENT)
    public void setPositionAndRotation2 (double p_70056_1_, double p_70056_3_, double p_70056_5_, float p_70056_7_,
                                         float p_70056_8_, int p_70056_9_)
    {
        setPosition(p_70056_1_, p_70056_3_, p_70056_5_);
        setRotation(p_70056_7_, p_70056_8_);
    }


    protected void entityInit (){
    }


    public float getMotionFactor (){
        return 0.95F;
    }


    public void writeEntityToNBT (NBTTagCompound compound){
        compound.setShort("xTile", (short)this.posXN);
        compound.setShort("yTile", (short)this.posYN);
        compound.setShort("zTile", (short)this.posZN);
        compound.setByte("inTile", (byte)Block.getIdFromBlock(this.field_145796_h));
        compound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
        compound.setTag("direction", newDoubleNBTList(new double[]{this.motionX, this.motionY, this.motionZ}));

        if (((this.throwerName == null) || (this.throwerName.length() == 0)) && (this.thrower != null) && ((this.thrower instanceof EntityPlayer))){
            this.throwerName = this.thrower.getCommandSenderName();
        }

        compound.setString("ownerName", this.throwerName == null ? "" : this.throwerName);
    }


    public void readEntityFromNBT (NBTTagCompound compound){
        this.posXN = compound.getShort("xTile");
        this.posYN = compound.getShort("yTile");
        this.posZN = compound.getShort("zTile");
        this.field_145796_h = Block.getBlockById(compound.getByte("inTile")&0xFF);
        this.inGround = (compound.getByte("inGround") == 1);

        if ((this.throwerName != null) && (this.throwerName.length() == 0)){
            this.throwerName = null;
        }

        if (compound.hasKey("direction", 9)){
            NBTTagList nbttaglist = compound.getTagList("direction", 6);
            this.motionX = nbttaglist.func_150309_d(0);
            this.motionY = nbttaglist.func_150309_d(1);
            this.motionZ = nbttaglist.func_150309_d(2);
        }else{
            setDead();
        }
    }

    public void onImpact (MovingObjectPosition movingObjectPosition){
        setDead();
    }

    public ResourceLocation getTexture (){
        return null;
    }

    public Double getBulletAccuracy (){
        return Double.valueOf(0.001D);
    }


    public boolean canBeCollidedWith (){
        return true;
    }

    public void writeSpawnData (ByteBuf buffer){
        buffer.writeDouble(this.accelerationX);
        buffer.writeDouble(this.accelerationY);
        buffer.writeDouble(this.accelerationZ);
    }

    public void readSpawnData (ByteBuf additionalData){
        this.accelerationX = additionalData.readDouble();
        this.accelerationY = additionalData.readDouble();
        this.accelerationZ = additionalData.readDouble();
    }
}
