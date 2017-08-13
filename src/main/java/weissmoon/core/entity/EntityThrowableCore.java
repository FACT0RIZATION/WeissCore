package weissmoon.core.entity;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import java.util.List;

/**
 * Base class for falling projectiles.
 * Similar to Potions.
 */

public class EntityThrowableCore extends EntityBulletCore{
    private int ticksInGround;
    protected Block field_145796_h;

    public EntityThrowableCore (World world){
        super(world);
        setSize(1.0F, 1.0F);
    }

    public EntityThrowableCore (World world, EntityLivingBase shootingEntity, boolean reverse){
        super(world, shootingEntity, reverse);
    }


    public void onUpdate (){
        this.lastTickPosX = this.posX;
        this.lastTickPosY = this.posY;
        this.lastTickPosZ = this.posZ;
        super.onEntityUpdate();

        if (this.throwableShake > 0){
            this.throwableShake -= 1;
        }

        if (this.inGround){
            if (this.worldObj.getBlockState(new BlockPos((int)this.posX, (int)this.posY, (int)this.posZ)).getBlock() == this.field_145796_h){
                this.ticksInGround += 1;

                if (this.ticksInGround == 1200){
                    setDead();
                }

                return;
            }

            this.inGround = false;
            this.motionX *= this.rand.nextFloat() * 0.2F;
            this.motionY *= this.rand.nextFloat() * 0.2F;
            this.motionZ *= this.rand.nextFloat() * 0.2F;
            this.ticksInGround = 0;
            this.ticksInAir = 0;
        }else{
            this.ticksInAir += 1;
        }

        Vec3d vec3 = new Vec3d(this.posX, this.posY, this.posZ);
        Vec3d vec31 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        RayTraceResult movingobjectposition = this.worldObj.rayTraceBlocks(vec3, vec31);
        vec3 = new Vec3d(this.posX, this.posY, this.posZ);
        vec31 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

        if (movingobjectposition != null){
            vec31 = new Vec3d(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
        }

        if (!this.worldObj.isRemote){
            Entity entity = null;
            List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
            double d0 = 0.0D;
            EntityLivingBase entitylivingbase = this.getThrower();

            for (int j = 0; j < list.size(); j++){
                Entity entity1 = (Entity)list.get(j);

                if ((entity1.canBeCollidedWith()) && ((entity1 != entitylivingbase) || (this.ticksInAir >= 5))){
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

        if ((movingobjectposition != null) && (movingobjectposition.entityHit != null) && ((movingobjectposition.entityHit instanceof EntityPlayer))){
            EntityPlayer entityplayer = (EntityPlayer)movingobjectposition.entityHit;

            if ((entityplayer.capabilities.disableDamage) || (this.throwerC == entityplayer) || (((this.throwerC instanceof EntityPlayer)) && (!((EntityPlayer)this.throwerC).canAttackPlayer(entityplayer)))){
                movingobjectposition = null;
            }
        }

        if (movingobjectposition != null){
            if ((movingobjectposition.typeOfHit == RayTraceResult.Type.BLOCK) && (this.worldObj.getBlockState(movingobjectposition.getBlockPos()).getBlock() == Blocks.PORTAL)){
                this.setPortal(movingobjectposition.getBlockPos());
            }else{
                onImpact(movingobjectposition);
            }
        }

        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;
        float f1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.rotationYaw = ((float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / 3.141592653589793D));

        for (this.rotationPitch = ((float)(Math.atan2(this.motionY, f1) * 180.0D / 3.141592653589793D)); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F){
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
        float f2 = 0.99F;
        float f3 = getGravityVelocity();

        if (isInWater()){
            for (int i = 0; i < 4; i++){
                float f4 = 0.25F;
                this.worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * f4, this.posY - this.motionY * f4, this.posZ - this.motionZ * f4, this.motionX, this.motionY, this.motionZ);
            }

            f2 = 0.8F;
        }

        this.motionX *= f2;
        this.motionY *= f2;
        this.motionZ *= f2;
        this.motionY -= f3;
        setPosition(this.posX, this.posY, this.posZ);
    }

    public void onImpact (RayTraceResult movingObjectPosition){
        setDead();
    }

    public void writeSpawnData (ByteBuf buffer){
        super.writeSpawnData(buffer);
    }

    public void readSpawnData (ByteBuf additionalData){
        super.readSpawnData(additionalData);
    }
}
