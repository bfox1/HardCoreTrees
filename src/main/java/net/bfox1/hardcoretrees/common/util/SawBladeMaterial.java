package net.bfox1.hardcoretrees.common.util;

/**
 * Created by bfox1 on 12/11/2014.
 */
public class SawBladeMaterial {

    private String Material;

    private static String combinedMaterial;

    private int durability;

    private int speed;


    public static final SawBladeMaterial woodBlade = new SawBladeMaterial("wood").setDurability(10).setCutSpeed(5);
    public static final SawBladeMaterial stoneBlade = new SawBladeMaterial("stone").setDurability(30).setCutSpeed(10);
    public static final SawBladeMaterial ironBlade = new SawBladeMaterial("iron").setDurability(90).setCutSpeed(30);
    public static final SawBladeMaterial diamondBlade = new SawBladeMaterial("diamond").setDurability(250).setCutSpeed(100);

    public static final SawBladeMaterial stoneTipWood = new SawBladeMaterial("wood", "stone").setDurability(25);





    private static String[] materialList  =
            {
              "stone","iron","diamond"
            };


    private SawBladeMaterial(String material, String combinedMaterial)
    {
        this.Material = material;
        this.combinedMaterial = combinedMaterial;

    }
    private SawBladeMaterial(String material)
    {
        this.Material = material;
    }


    public int getDurability()
    {
        return durability;
    }
    public int getCutSpeed() {return speed;}

    private SawBladeMaterial setDurability(int durability)
    {
        this.durability = durability;
        return this;
    }

    private SawBladeMaterial setCutSpeed(int speed)
    {
        this.speed = speed;
        return this;
    }


    public String getMaterial()
    {
        return this.Material;
    }





}
