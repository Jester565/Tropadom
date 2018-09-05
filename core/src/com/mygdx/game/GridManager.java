package com.mygdx.game;

import java.util.ArrayList;

public class GridManager {
	static public int biomeLengthCounter = 0;
	static public int COBBLEH = 60;
	static public int LAVAH = 15;
	static public int COPPERH = 50;
	static public int IRONH = 40;
	static public int GOLDH= 25;
	static public int COALH = 60;
	static public int biome = 4;
	static public int flatHeightCounter = 0;
	static public int flatHeight = 0;
	static public int flatHeightAmount = 6;
	static public boolean refreshed = false;
	public static int mountainHeight = 20;
	public static boolean mountainUp = true;
	static public ArrayList<Integer> Type;
	static public ArrayList<Integer> PrevType;
	static public CaveCreate caveCreate;
	static public ArrayList<CaveCreate> miniCaveCreate;
	static public ArrayList<Structure> lake;
	static public ArrayList<Structure> tree;
	GridManager() {
		PrevType = new ArrayList<Integer>();
		Type = new ArrayList<Integer>();
		for (int i = 0; i < GridStack.HEIGHT * .85; i++) {
			Type.add(1);
		}
		for (int i = 0; i < GridStack.HEIGHT * .15; i++) {
			Type.add(0);
		}
		
		caveCreate = new CaveCreate(COBBLEH/2, 2, 10, 2000);
		miniCaveCreate = new ArrayList<CaveCreate>();
		lake = new ArrayList<Structure>();
		tree = new ArrayList<Structure>();
	}
	public static void refresh() {
		biomeLengthCounter--;
		if(biomeLengthCounter<0)
		{
			biomeLengthCounter = (int) (Math.random()*100+20);
			biome = (int) Math.round(Math.random()*4);
		}
		refreshed = true;
		flatHeightCounter++;
		if(biome==2&&flatHeightCounter>flatHeightAmount)
		{
			flatHeightAmount = (int)(Math.random()*20+5);
			flatHeightCounter = 0;
			flatHeight = (int)(Math.random()*5);
		}
		else if (biome == 3&&flatHeightCounter>flatHeightAmount)
		{
			flatHeightAmount = (int)(Math.random()*6);
			flatHeightCounter = 0;
			if(mountainUp == true && flatHeight < mountainHeight)
			{
				flatHeight++;
			}
			else
			{
				mountainUp = false;
				flatHeightAmount = (int)(Math.random()*20);
			}
			if(mountainUp == false && flatHeight > 0)
			{
				flatHeight--;
				
			}
			else if(mountainUp==false)
			{
				mountainUp=true;
				flatHeightAmount = (int)(Math.random()*10);
			}
		}
		else if (biome == 4&&flatHeightCounter>flatHeightAmount)
		{
			flatHeightAmount = (int)(Math.random()*2);
			flatHeightCounter = 0;
			if(mountainUp == true && flatHeight < mountainHeight)
			{
				flatHeight+=Math.round(Math.random()*2)+1;
			}
			else if(mountainUp==true)
			{
				mountainUp = false;
				flatHeightAmount = (int)(Math.random()*10);
			}
			if(mountainUp == false && flatHeight > 0)
			{
				flatHeight-=Math.round(Math.random()*2)+1;
				
			}
			else
			{
				mountainUp=true;
				flatHeightAmount = (int)(Math.random()*10);
			}
		}
		caveCreate.refresh();
		float cavePercentR = (float)(Math.random() * 100);
		int typeSet;
		float ACP = .9f;
		int DirtGrass = 0;
		int CACTUS_PERCENT = 30;
		int SHRUB_PERCENT = 60;
		int COBBLE_PERCENT = 99;
		int LAVA_PERCENT = 4;
		int CAVE_PERCENT = 2;
		int TREE_PERCENT = 50;
		int JUNGLE_TREE_PERCENT = 170;
		int DIRT_PERCENT = 3;
		int COPPER_PERCENT = 7;
		int IRON_PERCENT = 5;
		int GOLD_PERCENT = 3;
		int COAL_PERCENT = 12;
		int ORE_SPAWN_LIMITER = 8;
		int GRASS_PERCENT = 70;
		int FLOWER_ONE_PERCENT = 5;
		int FLOWER_TWO_PERCENT = 3;
		int JUNGLE_LEAF_PERCENT = 20;
		PrevType = Type;
		if (cavePercentR < CAVE_PERCENT)
		{
			CaveCreate caveCreate2;
			caveCreate2 = new CaveCreate (caveCreate.y, caveCreate.goalX,-caveCreate.goalY,100);
			miniCaveCreate.add(caveCreate2);
		}
		for (int i = 0; i < miniCaveCreate.size(); i++) {
			miniCaveCreate.get(i).refresh();
		}
		for (int i = 0; i < GridStack.HEIGHT; i++) {
			float typeRandom = (float) Math.random() * 100;
			if (i > COBBLEH) {
				if(biome != 2&&biome!=3&&biome!=4)
				{
				COBBLE_PERCENT = COBBLE_PERCENT - 20;
				}
				else if (biome==2&&i-COBBLEH>flatHeight)
				{
					COBBLE_PERCENT = 0;
				}
				else if(biome==3&&i-COBBLEH>flatHeight||biome==4&&i-COBBLEH>flatHeight)
				{
					COBBLE_PERCENT-=70;
				}
				DIRT_PERCENT =DIRT_PERCENT - 3;
			}
			if (i > LAVAH) {
				LAVA_PERCENT --;
			}
			if (i > COPPERH)
			{
				COPPER_PERCENT -= 2/ORE_SPAWN_LIMITER;
			}
			if (i > IRONH)
			{
				IRON_PERCENT -= 2/ORE_SPAWN_LIMITER;
			}
			if (i >GOLDH)
			{
				GOLD_PERCENT -= 1/ORE_SPAWN_LIMITER;
			}
			if (i > COALH)
			{
				COAL_PERCENT -= 3/ORE_SPAWN_LIMITER;
			}
			if (i > 0 &&  i != GridStack.HEIGHT && i < COBBLEH) {
				if (i == 0)
				{
					typeSet = 1;
				}
				else if (PrevType.get(i) != 0 && typeRandom < LAVA_PERCENT && Type.get(i - 1) == 0)
				{
					typeSet = 5;	
				}
				else if (typeRandom < COAL_PERCENT/ORE_SPAWN_LIMITER)
				{
					typeSet = 16;
				}
				else if (typeRandom < (GOLD_PERCENT * 9)/ORE_SPAWN_LIMITER && typeRandom > (GOLD_PERCENT * 8)/ORE_SPAWN_LIMITER)
				{
					typeSet = 19;
				}
				else if (typeRandom < (IRON_PERCENT * 4) /ORE_SPAWN_LIMITER&& typeRandom > (IRON_PERCENT * 3)/ORE_SPAWN_LIMITER)
				{
					typeSet = 17;
				}
				else if (typeRandom < (COPPER_PERCENT * 6)/ORE_SPAWN_LIMITER && typeRandom > (COPPER_PERCENT * 5)/ORE_SPAWN_LIMITER)
				{
					typeSet = 18;
				}
				else if (PrevType.get(i) != 0 && typeRandom < DIRT_PERCENT)
				{
					typeSet = 2;
				}
				else if (typeRandom < COBBLE_PERCENT * (ACP) && PrevType.get(i) == 0|| PrevType.get(i) != 0 && typeRandom < COBBLE_PERCENT&& Type.get(i - 1) != 0 && PrevType.get(i + 1) != 0
						|| typeRandom < COBBLE_PERCENT * (ACP) && Type.get(i - 1) == 0|| typeRandom < COBBLE_PERCENT * (ACP) && PrevType.get(i + 1) == 0) {
					typeSet = 1;
				}
				else 
				{
					typeSet = 0;
				}
			}

			else if (i < COBBLEH)
			{
				if (i == 0)
				{
					typeSet = 1;
				}
				else if (PrevType.get(i) == 1 && typeRandom < LAVA_PERCENT )
				{
					typeSet = 5;	
				}
				else if (typeRandom < IRON_PERCENT)
				{
					typeSet = 17;
				}
				else if (typeRandom < COPPER_PERCENT)
				{
					typeSet = 18;
				}
				else if (typeRandom < COBBLE_PERCENT * .4 && PrevType.get(i) == 0 || PrevType.get(i) == 1 && typeRandom < COBBLE_PERCENT) 
				{
					typeSet = 1;
				} else {
					typeSet = 0;
				}
			}
			else if (i == COBBLEH)
			{
				if (typeRandom < COBBLE_PERCENT - 30)
				{
					typeSet =1;
				}
				else if (typeRandom < COBBLE_PERCENT - 5)
				{
					typeSet = 2;
				}
				else if(biome==2)
				{
					typeSet = 58;
				}
				else
				{
					typeSet = 0;
				}
			}
			else if (i == COBBLEH + 1)
			{
				if (typeRandom < COBBLE_PERCENT + 10)
				{
					typeSet = 2;
				}
				else if(biome == 2)
				{
					typeSet = 58;
				}
				else
				{
					typeSet = 0;
				}
			}
			else
			{
				if (i == 0)
				{
					typeSet = 1;
				}else
				if (typeRandom < COBBLE_PERCENT * .001 &&Type.get(i - 1) == 0 || typeRandom < COBBLE_PERCENT && Type.get(i-1) != 0 &&PrevType.get(i)!=0 && Type.get(i-1)!=3 
						||typeRandom < COBBLE_PERCENT*.6&& PrevType.get(i) == 0 &&Type.get(i-1) != 0&&Type.get(i-1) != 3||biome==3 && i-COBBLEH<flatHeight-2||biome==4 && i-COBBLEH<flatHeight-2) {
					if(DirtGrass == 0)
					{
						if(biome==3 && i-COBBLEH<flatHeight-2||biome==4 && i-COBBLEH<flatHeight-2)
						{
							typeSet=1;
						}
						else if(biome!=2)
						{
							typeSet = 2;
						}
						else
						{
							typeSet = 58;
						}
					}
					else
					{
						typeSet = 0;
					}
				}
				else if (DirtGrass == 0)
				{
					int treeRandom = (int) (Math.random() * 1000);
					if (treeRandom >= 50 && treeRandom <= 60&&biome == 0)
					{
						Structure structure;
						structure = new Structure (0, i - 1, 2,true);
						lake.add(structure);
					}
					if (treeRandom == 42&&biome == 0)
					{
						Structure structure;
						structure = new Structure (0, i - 1, 3,true);
						lake.add(structure);	
					}
					if(biome==1&&treeRandom >= 50 && treeRandom <= 55)
					{
						Structure structure;
						structure = new Structure (0, i - 1, 4,true);
						lake.add(structure);
					}
					if(biome != 2)
					{
						typeSet = 3;
					}
					else
					{
						typeSet = 9;
					}
					
					DirtGrass++;
				}
				else if (DirtGrass == 1)
				{
					int treeRandom = (int) (Math.random() * 1000);
					if(biome==0&&treeRandom < TREE_PERCENT||biome==1&&treeRandom < JUNGLE_TREE_PERCENT)
					{
						if(biome == 0)
						{
							typeSet = 10;
						}
						else if (biome == 1)
						{
							typeSet = 57;
						}
						else
						{
							typeSet = 0;
						}
					}
					else if(biome == 1&&Math.random()*100 < JUNGLE_LEAF_PERCENT)
					{
						typeSet = 55;
					}
					else if(biome!=2&&Math.random()*100 < FLOWER_ONE_PERCENT)
					{
						typeSet = 52;
					}
					else if (biome!=2&&Math.random()*100 < FLOWER_TWO_PERCENT)
					{
						typeSet = 53;
					}
					else if(biome!=2&&Math.random()*100 < GRASS_PERCENT)
					{
						typeSet = 51;
					}
					else if(biome==2&&Math.random()*1000<CACTUS_PERCENT)
					{
						typeSet = 60;
					}
					else if(biome==2&&Math.random()*1000<SHRUB_PERCENT)
					{
						typeSet = 59;
					}
					else
					{
						typeSet = 0;
					}
					DirtGrass++;
				}
				else {
					typeSet = 0;
				}
			}
			Type.set(i, typeSet);

		}
		caveCreate.cordGet();
		
		for (int i = 0; i < miniCaveCreate.size(); i++) {
			miniCaveCreate.get(i).cordGet();
		}
		for (int i = 0; i < tree.size(); i++) {
			tree.get(i).create();
			
		}
		for (int i = 0; i < lake.size(); i++) {
			
			lake.get(i).create();
		}
	}
}
