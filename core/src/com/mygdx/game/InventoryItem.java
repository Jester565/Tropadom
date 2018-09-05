package com.mygdx.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class InventoryItem {
	SpriteBatch batch = new SpriteBatch();
	Sprite it = new Sprite();
	Sprite is = new Sprite();
	Sprite isr = new Sprite();
	Sprite ib = new Sprite();
	//SpriteBatch batch;
	//SpriteBatch batch2;
	BitmapFont bitmap2;
	HashMap <Integer,Integer> recipeItem;
	String title;
	String img;
	ArrayList<Integer>headArmorRate = null;
	ArrayList<Integer>bodyArmorRate = null;
	ArrayList<Integer>legArmorRate = null;
	ArrayList<Integer>backStats = null;
	String armImg;
	boolean makeItem = false;
	public static final int BLOCK_AMOUNT = 60;
	int craftTime = 0;
	int count = 0;
	int category = 0;
	int blockNumber;
	int itemNumber;
	int buttonTimer = 0;
	int is2;
	int macProduct = 0;
	int imageDrawnCount = 0;
	int fpp;
	int productType;
	int acceptProductType = 0;
	ArrayList<Sprite>partImg;
	ArrayList<Sprite>backImg;
	int madeInCrafting;
	float gX;
	float gY;
	InventoryItem(int _itemNumber, String _img, String _title, int _blockNumber, int _category, int _fpp, int _productType, int _madeInCrafting, int _craftTime)
	{
		craftTime = _craftTime;
		//batch2 = new SpriteBatch();
		int product;
		//bitmap2 = new BitmapFont();
		it = new Sprite(new Texture(_img));
		isr = new Sprite(new Texture("itSlotR.png"));
		is = new Sprite(new Texture("itSlot.png"));
		ib = new Sprite(new Texture("itemBack.png"));
		category = _category;
		backImg = new ArrayList<Sprite>();
		partImg = new ArrayList<Sprite>();
		title = _title;
		recipeItem = new HashMap<Integer,Integer>();
		blockNumber = _blockNumber;
		itemNumber = _itemNumber;
		img = _img;
		fpp = _fpp;
		productType = _productType;
		madeInCrafting = _madeInCrafting;
	}
	void setMaceratorProduct(int product)
	{
		macProduct = product;
	}
	void setHeadArmorRate(List list)
	{
		if(list != null)
		{
			ArrayList _headArmorRate = new ArrayList<Integer>(list);
			headArmorRate = _headArmorRate;
		}
		
	}
	void setBodyArmorRate(List list)
	{
		if(list != null)
		{
			ArrayList _bodyArmorRate = new ArrayList<Integer>(list);
			bodyArmorRate = _bodyArmorRate;
		}
	}
	void setLegArmorRate(List list)
	{
		if(list != null)
		{
			ArrayList _legArmorRate = new ArrayList<Integer>(list);
			legArmorRate = _legArmorRate;
		}
	}
	void setBackStats(List list)
	{
		if(list != null)
		{
			ArrayList _backStats = new ArrayList<Integer>(list);
			backStats = _backStats;
			
		}
	}
	void add(int amount)
	{
		if (count == 0)
		{
			HUD.refreshTitle();
		}
		for (int i = 0; i < amount; i ++)
		{
			count++;
		}
		
	}
	void remove(int amount)
	{
		HUD.refresh();
		for (int i = 0; i < amount; i ++)
		{
			count--;
		}
		if (count == 0)
		{
			HUD.refreshTitle();
		}
	}
	public Boolean equiped()
	{
		if (HUD.hotBarX  > gX - 30&& HUD.hotBarX < gX + 30 && count > 0)
		{
			return true;
		}
		return false;
	}
	public String image()
	{
		if (equiped())
		{
			return (img);
		}
		return null;
	}
	public void title()
	{
		if (equiped() && HUD.hotBarDisplay > 0)
		{
			//LOOK
			//batch2.begin();
			//bitmap.setColor(1, 0, 0, 1);
			//bitmap.drawMultiLine(title, 995 - (title.length()/2 * 6), 905);
			//bitmap.drawMultiLine(title, 1050, 905);
			//batch2.end();
		}
	}
	public int blockN()
	{
		if (equiped())
		{
			return blockNumber;
		}
		
			return -1;
		
	}
	public int itemN()
	{
		if (equiped())
		{
			return itemNumber;
		}
			return -1;
	}
	void addRecipe(int id, int count)
	{
		recipeItem.put(id, count);
	}
	void setArmorArmImg(String s)
	{
		armImg =s;
	}
	boolean hasRecipe(Inventory inventory)
	{
		if(Gdx.input.isKeyPressed(Input.Keys.ALT_LEFT))
		{
			return true;
		}
		 for (Map.Entry part : recipeItem.entrySet()) {
		      int key = (Integer)(part.getKey());
		      int value = (Integer)part.getValue();
		      if (inventory.item(key).count < value || HUD.inventoryFull &&  inventory.item(itemNumber - 1).count == 0) {
		    	 return false;
		      }
		    }
		    return true;
	}
	void createProduct(Inventory inventory)
	{
		if (hasRecipe(inventory))
		{
			 for (Map.Entry part : recipeItem.entrySet()) {
			      int key = (Integer)(part.getKey());
			      int value = (Integer)part.getValue();
			     inventory.item(key).remove(value);
			 }
			 inventory.item(itemNumber - 1).add(madeInCrafting);
		}
	}
	void drawPart( Inventory inventory, int x, int y)
	{
		int i = 0;
	    for (Map.Entry part : recipeItem.entrySet()) {
	      int key = (Integer)(part.getKey());
	      int value = (Integer)part.getValue();

	      inventory.item(key).partDraw(x + HUD.PART_DIFFERENCEX, y,true,25, value);
	      x += HUD.PART_DIFFERENCEX;
	    }
	}
	void chestDraw(float x, float y, int ITEMSIZE)
	{
		//batch2.begin();
		if (GridBased.inventoryOn() == 2)
		{
			acceptProductType = 2;
		}
		else if (GridBased.inventoryOn() == 3||GridBased.inventoryOn()==5)
		{
			acceptProductType = 3;
		}
		
		int xOff = 0;
		int yOff = 0;
		if (fpp == acceptProductType)
		{
			xOff = 200;
			yOff = -80;
		}
		else if (fpp == 1)
		{
			xOff = 330;
			yOff = -237;
		}
		else
		{
			xOff = 475;
			yOff = -80;
		}
		is2 = ITEMSIZE;
		gX = x + xOff;
		gY = y + yOff;
		it.setSize(ITEMSIZE ,ITEMSIZE);
		it.setPosition(x + xOff, y + yOff);
		batch.begin();
		it.draw(batch);
		batch.end();
		//it.draw(batch2);
		//LOOK
		//bitmap.setColor(0, 0, 0, 1);
		//bitmap.drawMultiLine(String.valueOf(count), x + 33 + xOff,y + 10 +yOff);
		//batch2.end();
	}
	void partDraw(float x,float y, boolean backGround,int ITEMSIZE,int amount)
	{
		//batch2.begin();
		if(partImg.size() <= imageDrawnCount)
		{
			Sprite bt = new Sprite();
			Sprite it = new Sprite();
			backImg.add(bt);
			partImg.add(it);
			backImg.set(imageDrawnCount, new Sprite(new Texture("itemBack.png")));
			partImg.set(imageDrawnCount, new Sprite(new Texture(img)));
		}
		buttonTimer --;
		isr.setScale(.6f);
		isr.setPosition(0, y - 550);
		is.setScale(.6f);
		is.setPosition(0, y - 550);
		backImg.get(imageDrawnCount).setPosition(x - 7, y -7);
		backImg.get(imageDrawnCount).setSize(ITEMSIZE + 10,ITEMSIZE + 10);
		gX = x;
		gY = y;
		is2 = ITEMSIZE;
		batch.begin();
		partImg.get(imageDrawnCount).setSize(ITEMSIZE ,ITEMSIZE);
		partImg.get(imageDrawnCount).setPosition(x, y);
			if (Math.round(x) <= Math.round(HUD.IX2) + 5 && Math.round(x) >= Math.round(HUD.IX2) - 5 && HUD.inventoryOn == 1)
			{
				//is.changeLayer(-1);
				is.draw(batch);
				//is.draw(batch2);
				if (Button.overButton(x - 35, y - 13, 650, 60) && hasRecipe(HUD.inventory) && buttonTimer < 0)
				{
					//isr.changeLayer(-1);
					isr.draw(batch);
					//isr.draw(batch2);
					if( Gdx.input.isButtonPressed(Input.Buttons.LEFT))
					{
						//createProduct(HUD.inventory);
						buttonTimer = 20;
					}
				}
			}
			if (backGround)
			{
				//ib.changeLayer(-1);
				backImg.get(imageDrawnCount).draw(batch);
				//ib.draw(batch2);
			}
			//it.changeLayer(-1);
			partImg.get(imageDrawnCount).draw(batch);
			batch.end();
			//it.draw(batch2);
			//LOOK
			//bitmap.setColor(0, 0, 0, 1);
			//bitmap.drawMultiLine(String.valueOf(amount), x + 33,y + 10);
			//batch2.end();
			imageDrawnCount++;
		
	}
	
	void draw(float x, float y, boolean backGround, int ITEMSIZE)
	{
		
		buttonTimer --;
		isr.setScale(.6f);
		isr.setPosition(0, y - 550);
		is.setScale(.6f);
		is.setPosition(0, y - 550);
		ib.setPosition(x - 7, y -7);
		ib.setSize(ITEMSIZE + 10,ITEMSIZE + 10);
		gX = x;
		gY = y;
		armorTransferManager();
		is2 = ITEMSIZE;
			it.setSize(ITEMSIZE ,ITEMSIZE);
			it.setPosition(x, y);
			batch.begin();
			//batch2.begin();
			if (Math.round(x) <= Math.round(HUD.IX2) + 5 && Math.round(x) >= Math.round(HUD.IX2) - 5 && HUD.inventoryOn == 1)
			{
				//is.draw(batch2);
				is.draw(batch);
				if (Button.overButton(x - 35, y - 13, 650, 60) && hasRecipe(HUD.inventory) && buttonTimer < 0 && MyGdxGame.protagonist.craftingTimer < 0)
				{
					//isr.draw(batch2);
					isr.draw(batch);
					if( Gdx.input.isButtonPressed(Input.Buttons.LEFT))
					{
						ArrayList<Integer> partArray = new ArrayList<Integer>();
						int i = 0;
					    for (Map.Entry part : recipeItem.entrySet()) {
					      partArray.add((Integer)(part.getKey()));
					    }
					    if(Gdx.input.isKeyPressed(Input.Keys.ALT_LEFT))
					    {
					    	createProduct(HUD.inventory);
					    }
					    else
					    {
					    	MyGdxGame.protagonist.craftItem(itemNumber,craftTime,partArray);
					    }
						
						buttonTimer = 20;
					}
				}
			}
			if (backGround)
			{
				//ib.draw(batch2);
				ib.draw(batch);
			}
			it.draw(batch);
			batch.end();
			//it.draw(batch2);
			//LOOK
			/*
			bitmap.setColor(0, 0, 0, 1);
			if (HUD.inventoryOn == 1 && y <= HUD.IY2)
			{
				bitmap.drawMultiLine(String.valueOf(madeInCrafting), x + 33,y + 10);
			}
			else
			{
				bitmap.drawMultiLine(String.valueOf(count), x + 33,y + 10);
			}
			*/
		//batch2.end();
	}
	void armorTransferManager()
	{
		if (pressed() && HUD.armorDisplay)
		{
			if (headArmorRate != null && ArmorBox.head == null)
			{
				ArmorBox.head = img;
				ArmorBox.refresh();
				remove(1);
			}
			if (bodyArmorRate != null && ArmorBox.body == null)
			{
				ArmorBox.body = img;
				ArmorBox.arm = armImg;
				ArmorBox.refresh();
				remove(1);
			}
			if (legArmorRate != null && ArmorBox.leg == null)
			{
				ArmorBox.leg = img;
				ArmorBox.refresh();
				remove(1);
			}
			if (backStats != null && ArmorBox.back == null)
			{
				ArmorBox.back = img;
				ArmorBox.backStats = backStats;
				ArmorBox.refresh();
				remove(1);
			}
		}
	}
	boolean pressed()
	{
		if(Button.button(gX, gY, is2, is2, Gdx.input.isButtonPressed(Input.Buttons.RIGHT)))
		{
			return true;
		}
		return false;
	}
	void chestTransferManager(Inventory inventorySend)
	{
		if (inventorySend != HUD.inventory && inventorySend != ArmorBox.inventory|| inventorySend == HUD.inventory&&HUD.inventoryFull == false 
				|| inventorySend == HUD.inventory&&HUD.inventory.item(itemNumber - 1).count > 0 || inventorySend == ArmorBox.inventory&&ArmorBox.backPackFull == false 
				|| inventorySend == ArmorBox.inventory&&ArmorBox.inventory.item(itemNumber-1).count>0)
		{
		if (pressed() && HUD.transferT <= 0 && GridBased.inventoryOn() == 1 || pressed() && HUD.transferT <= 0 && HUD.backPackDisplay == true)
		{
			
			if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || count < 10 && Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
			{
				HUD.transferT = 20;
				inventorySend.item(itemNumber - 1).add(count);
				remove(count);
				
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) && count > 10)
			{
				HUD.transferT = 20;
				remove(10);
				inventorySend.item(itemNumber - 1).add(10);
			}
			else
			{
				HUD.transferT = 20;
				remove(1);
				inventorySend.item(itemNumber - 1).add(1);
			}
		}
		}
		if (pressed() && HUD.transferT <= 0)
		{
			if(gY < 700)
			{
				if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || count < 10 && Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
				{
					HUD.transferT = 20;
					inventorySend.item(itemNumber - 1).add(count);
					remove(count);
					
				}
				else if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) && count > 10)
				{
					HUD.transferT = 20;
					remove(10);
					inventorySend.item(itemNumber - 1).add(10);
				}
				else
				{
					HUD.transferT = 20;
					remove(1);
					inventorySend.item(itemNumber - 1).add(1);
				}
			}
			if (GridBased.inventoryOn() > 1)
			{
			if (GridBased.fuel() == 0||GridBased.fuel() == itemNumber||gY < 700)
			{
				for (int i = 0; i < GridBased.fuelAccept().size();i++)
				{
					
					if (itemNumber == (Integer)(GridBased.fuelAccept().get(i)))
					{
						System.out.println("fuel Passed " + ((Integer)(GridBased.fuelAccept().get(i))));
						GridBased.setFuel(itemNumber);
						if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || count < 10 && Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
						{
							HUD.transferT = 20;
							inventorySend.item(itemNumber - 1).add(count);
							remove(count);
							
						}
						else if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) && count > 10)
						{
							HUD.transferT = 20;
							remove(10);
							inventorySend.item(itemNumber - 1).add(10);
						}
						else
						{
							HUD.transferT = 20;
							remove(1);
							inventorySend.item(itemNumber - 1).add(1);
						}
					}
					
				}
			}
			if (GridBased.parts() == 0|| GridBased.parts() == itemNumber)
			{
				for (int i = 0; i < GridBased.partAccept().size();i++)
				{
					
					if (itemNumber == (Integer)(GridBased.partAccept().get(i)))
					{
						GridBased.setPart(itemNumber);
						if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || count < 10 && Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
						{
							HUD.transferT = 20;
							inventorySend.item(itemNumber - 1).add(count);
							remove(count);
							
						}
						else if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) && count > 10)
						{
							HUD.transferT = 20;
							remove(10);
							inventorySend.item(itemNumber - 1).add(10);
						}
						else
						{
							HUD.transferT = 20;
							remove(1);
							inventorySend.item(itemNumber - 1).add(1);
						}
					}
					
				}
			}
			
		}
	}
	}
}
class stone extends InventoryItem
{
	stone()
	{
		super(1, "cobbleStone.png", "CobbleStone",1,5,0,0,1,3);
	}
}
class dirt extends InventoryItem
{
	dirt()
	{
		super(2, "dirt.png", "Dirt",2, 5,0,0,1,3);
	}
}
class dirtGrass extends InventoryItem
{
	dirtGrass()
	{
		super(3, "dirtGrass.png", "DirtGrass",3,5,0,0,1,3);
	}
}
class lava1 extends InventoryItem
{
	lava1()
	{
		super(4, "lavaBucket.png", "Lava",4,5,0,0,1,3);
	}
}
class lava2 extends InventoryItem
{
	lava2()
	{
		super(5, "lavaBucket.png", "Lava",5,5,1,0,1,3);
	}
}
class log extends InventoryItem
{
	log()
	{
		super(6, "log.png", "Log", 6,5,1,0,1,3);
	}
}
class leaf extends InventoryItem
{
	leaf()
	{
		super(7,"leaf.png", "Leaf", 7,5,0,0,1,3);
	}
}
class water extends InventoryItem
{
	water()
	{
		super(8, "waterBucket.png", "Water", 8,5,0,0,1,3);
	}
}
class sand extends InventoryItem
{
	sand()
	{
		super(9, "sand.png", "Sand", 9,5,3,14,1,3);
	}
}
class sappling extends InventoryItem
{
	sappling()
	{
		super(10, "log.png","Sappling",10,5,0,0,1,3);
	}
}
class torch extends InventoryItem
{
	torch()
	{
		super(11, "torchHand.png", "Torch",11,1,0,0,4,60);
		addRecipe(5,1);
	}
}
class chest extends InventoryItem
{
	chest()
	{
		super(12, "chest.png", "Chest",12,2,1,0,1,300);
		addRecipe(5,10);
		addRecipe(BLOCK_AMOUNT + 11,1);
	}
}
class bonfire extends InventoryItem
{
	bonfire()
	{
		super(13, "bonfire1.png", "Bonfire", 13, 2,0,0,1,150);
		addRecipe(5,3);
	}
}
class glass extends InventoryItem
{
	glass()
	{
		super(14, "glass.png", "Glass", 14, 5,0,0,1,3);
	}
}
class furnace extends InventoryItem
{
	furnace()
	{
		super(15, "furnaceT.png", "Furnace",15,2,0,0,1,320);
		addRecipe(0,10);
		addRecipe(5,2);
	}
}
class coal extends InventoryItem
{
	coal()
	{
		super(16, "coalT.png", "Coal", 16, 6, 1,0,1,3);
	}
}
class iron extends InventoryItem
{
	iron()
	{
		super(17, "ironT.png", "IronOre", 17,6,3, BLOCK_AMOUNT + 12,1,3);
		setMaceratorProduct(BLOCK_AMOUNT + 18);
	}
}
class copper extends InventoryItem
{
	copper()
	{
		super(18, "copperT.png", "Copper Ore",18,6,3, BLOCK_AMOUNT + 11,1,3);
		setMaceratorProduct(BLOCK_AMOUNT + 19);
	}
}
class gold extends InventoryItem
{
	gold()
	{
		super(19, "goldT.png", "Gold Ore", 19,6,3, BLOCK_AMOUNT + 13,1,3);
		setMaceratorProduct(BLOCK_AMOUNT + 20);
	}
}
class macerator extends InventoryItem
{
	macerator()
	{
		super(20, "macerator.png", "Macerator",20,2,0,0,1,400);
		addRecipe(BLOCK_AMOUNT + 11, 5);
		addRecipe(0,2);
		addRecipe(5,2);
	}
}
class stonePipe extends InventoryItem
{
	stonePipe()
	{
		super(21, "stonePipeCenter.png", "Stone Pipe", 21,3,0,0,4, 60);
		addRecipe(0,1);
		addRecipe(13,1);
	}
}
class ironPipe extends InventoryItem
{
	ironPipe()
	{
		super(22, "ironPipeCenter.png", "Iron Pipe", 22,3,0,0,4,80);
		addRecipe(BLOCK_AMOUNT + 11,1);
		addRecipe(13,1);
	}
}
class batBox extends InventoryItem
{
	batBox()
	{
		super(23,"batBox.png", "Battery Box", 23,2,0,0,1,400);
		addRecipe(5,10);
		addRecipe(BLOCK_AMOUNT + 10,3);
	}
}
class generator extends InventoryItem
{
	generator()
	{
		super(24,"generator.png", "Gernerator", 24, 2,0,0,1, 400);
		addRecipe(BLOCK_AMOUNT + 11,5);
		addRecipe(BLOCK_AMOUNT + 10,2);
		addRecipe(0,5);
	}
}
class copperWire extends InventoryItem
{
	copperWire()
	{
		super(25,"copperWireCenter.png","Copper Wire", 25, 3,0,0,4, 80);
	}
}
class plank extends InventoryItem
{
	plank()
	{
		super(26,"plankT.png","Plank",26,2,0,0,4,40);
		addRecipe(5, 1);
	}
}
class door extends InventoryItem
{
	door()
	{
		super(27,"doorItem.png", "Door", 27, 2,0,0,1,200);
		addRecipe(5, 4);
	}
}
class door2 extends InventoryItem
{
	door2()
	{
		super(28,"doorItem.png", "Door", 28, 6,0,0,1,3);
	}
}
class ladder extends InventoryItem
{
	ladder()
	{
		super(29,"ladder.png", "Ladder", 29, 2,0,0,4,200);
		addRecipe(5,2);
	}
}
class hoedDirt extends InventoryItem
{
	hoedDirt()
	{
		super(30, "hoedDirt.png", "Hoed Dirt", 30, 6,0,0,1,3);
	}
}
class rubberLog extends InventoryItem
{
	rubberLog()
	{
		super(31, "rubberLog.png", "Rubber Log", 31, 5, 0,0,1,3);
	}
}
class eFurnace extends InventoryItem
{
	eFurnace()
	{
		super(32, "eFurnace1.png", "Electric Furnace", 32, 2,0,0,1,350);
		addRecipe(BLOCK_AMOUNT + 11, 5);
		addRecipe(0,2);
		addRecipe(5,2);
		addRecipe(28,2);
	}
}
class sapling extends InventoryItem
{
	sapling()
	{
		super(33, "sapling.png", "Sapling", 33, 5,0,0,1,3);
	}
}
class tomato extends InventoryItem
{
	tomato()
	{
		super(34, "sapling.png","Tomato Seeds", 34,1,0,0,3,3);
		addRecipe(BLOCK_AMOUNT + 20,1);
	}
}
class corn extends InventoryItem
{
	corn()
	{
		super (35, "cornPlant2.png", "Corn Seeds", 35, 1, 0,0,3,3);
	}
}
class wire extends InventoryItem
{
	wire()
	{
		super (36, "wire.png", "Wire", 36, 3,0,0,3,40);
	}
}
class lever extends InventoryItem
{
	lever()
	{
		super(37, "lever1.png", "Lever",37,1,0,0,3,60);
	}
}
class advancedWireManager extends InventoryItem
{
	advancedWireManager()
	{
		super (38,"advancedWireManager.png", "Advanced Wire", 38, 3, 0, 0, 3,90);
	}
}
class piston extends InventoryItem
{
	piston()
	{
		super (39, "piston.png", "Piston", 39,3, 0, 0, 3,100);
	}
}
class pistonH extends InventoryItem
{
	pistonH()
	{
		super (40, "pistonHead.png", "Piston Head", 40,6, 0, 0, 3,3);
	}
}
class lamp extends InventoryItem
{
	lamp()
	{
		super(41, "lamp.png", "Lamp", 41, 3,0,0,3,150);
	}
}
class c4 extends InventoryItem
{
	c4()
	{
		super(42, "c4.png", "C4", 42, 3,0,0,3,110);
	}
}
class snow extends InventoryItem
{
	snow()
	{
		super(43,"snow.png","Snow",43,6,0,0,3,0);
	}
}
class ice extends InventoryItem
{
	ice()
	{
		super(44,"ice.png", "Ice",44,6,0,0,3,0);
	}
}
class stoneBrick extends InventoryItem
{
	stoneBrick()
	{
		super(45,"stoneBrick.png","Stone Brick", 45, 3, 0, 0, 4, 10);
		addRecipe(0,4);
	}
}
class stoneButton extends InventoryItem
{
	stoneButton()
	{
		super(46,"Button1.png", "Button", 46, 3, 0, 0, 2,10);
	}
}
class pressurePad extends InventoryItem
{
	pressurePad()
	{
		super(47,"pressurePad1.png", "Pressure Pad", 47,3,0,0,1,30);
	}
}
class signalConverter extends InventoryItem
{
	signalConverter()
	{
		super(48,"signalConverter.png", "Signal Converter", 48,3,0,0,1,30);
	}
}
class rubberBlock extends InventoryItem
{
	rubberBlock()
	{
		super(49,"rubberBlock.png","Rubber Block",49,3,0,0,1,300);
	}
}
class solidWire extends InventoryItem
{
	solidWire()
	{
		super(50,"glassWire.png","Glass Wire", 50, 3,0,0,1,60);
	}
}
class grass extends InventoryItem
{
	grass()
	{
		super(51,"grass.png","Grass",51,3,0,0,1,0);
	}
}
class flowerOne extends InventoryItem
{
	flowerOne()
	{
		super(52,"flower1.png","Red Flower", 52, 3,0,0,1,0);
	}

}
class flowerTwo extends InventoryItem
{
	flowerTwo()
	{
		super(53,"flower2.png","Blue Flower", 53, 3,0,0,1,0);
	}
}
class vine extends InventoryItem
{
	vine()
	{
		super(54,"vine.png","Vine",54,3,0,0,1,0);
	}
}
class jungleLeaf extends InventoryItem
{
	jungleLeaf()
	{
		super(55,"jungleLeaf.png","Jungle Leaf",55,6,0,0,1,0);
	}
}
class jungleLog extends InventoryItem
{
	jungleLog()
	{
		super(56,"jungleLog.png","Jungle Log",56,6,0,0,1,0);
	}
}
class jungleSapling extends InventoryItem
{
	jungleSapling()
	{
		super(57,"sapling.png","Jungle Sapling",57,6,0,0,1,0);
	}
}
class sandStone extends InventoryItem
{
	sandStone()
	{
		super(58,"sandStone.png","Sand Stone",58,5,0,0,1,0);
	}
}
class desertBush extends InventoryItem
{
	desertBush()
	{
		super(59,"desertShrub.png","Desert Shrub", 59,4,0,0,1,0);
	}
}
class cactus extends InventoryItem
{
	cactus()
	{
		super(60,"cactusT.png","Cactus",60,4,0,0,1,0);
	}
}
class emptyBucket extends InventoryItem
{
	emptyBucket()
	{
		super(BLOCK_AMOUNT + 1, "emptyBucket.png", "Bucket", 0,4 ,0,0,2,90);
		addRecipe(BLOCK_AMOUNT + 11, 3);
	}
}
class woodPick extends InventoryItem
{
	woodPick()
	{
		super(BLOCK_AMOUNT + 2, "pickAxeI.png", "Wood Pick", 0,1,0,0,1,97);
		addRecipe(5, 4);
		
	}	
}
class woodShovel extends InventoryItem
{
	woodShovel()
	{
		super(BLOCK_AMOUNT + 3, "shovelI.png", "Wood Shovel", 0, 1,0,0,1,97);
		addRecipe(5, 4);
	}
}
class woodAxe extends InventoryItem
{
	woodAxe()
	{
		super(BLOCK_AMOUNT + 4, "axeI.png", "Wood Axe",0,1,0,0,1,97);
		addRecipe(5, 4);
	}
}

class stonePick extends InventoryItem
{
	stonePick()
	{
		super(BLOCK_AMOUNT + 5, "stonePickT.png", "Stone Pick",0,1,0,0,1,97);
		addRecipe(0, 3);
		addRecipe(5, 1);
	}
}
class stoneShovel extends InventoryItem
{
	stoneShovel()
	{
		super(BLOCK_AMOUNT + 6, "stoneShovelT.png", "Stone Shovel",0,1,0,0,1,97);
		addRecipe(0, 3);
		addRecipe(5, 1);
	}
}
class stoneAxe extends InventoryItem
{
	stoneAxe()
	{
		super(BLOCK_AMOUNT + 7, "stoneAxeT.png", "Stone Axe",0,1,0,0,1,97);
		addRecipe(0, 3);
		addRecipe(5, 1);
	}
}
class ironPick extends InventoryItem
{
	ironPick()
	{
		super(BLOCK_AMOUNT + 8, "ironPickT.png", "Iron Pick",0,1,0,0,1,97);
		addRecipe(BLOCK_AMOUNT + 11, 3);
		addRecipe(5, 1);
	}
}
class ironShovel extends InventoryItem
{
	ironShovel()
	{
		super(BLOCK_AMOUNT + 9, "ironShovelT.png", "Iron Shovel",0,1,0,0,1,97);
		addRecipe(BLOCK_AMOUNT + 11, 3);
		addRecipe(5, 1);
	}
}

class ironAxe extends InventoryItem
{
	ironAxe()
	{
		super(BLOCK_AMOUNT + 10, "ironAxeT.png", "Iron Axe",0,1,0,0,1,97);
		addRecipe(BLOCK_AMOUNT + 11, 3);
		addRecipe(5, 1);
	}
}
class copperIgnot extends InventoryItem
{
	copperIgnot()
	{
		super (BLOCK_AMOUNT + 11, "copperBarT.png", "Copper Ignot", 0, 6,0,0,1,3);
	}
}
class ironIgnot extends InventoryItem
{
	ironIgnot()
	{
		super (BLOCK_AMOUNT + 12, "ironBarT.png", "Iron Ignot", 0, 6,0,0,1,3);
	}
}
class goldIgnot extends InventoryItem
{
	goldIgnot()
	{
		super (BLOCK_AMOUNT + 13, "goldBarT.png", "Gold Ignot", 0, 6,0,0,1,3);
	}
}
class hedgeClippers extends InventoryItem
{
	hedgeClippers()
	{
		super(BLOCK_AMOUNT + 14, "hedgeClippers.png", "Hedge Clippers", 0,1,0,0,1,97);
		addRecipe(5,2);
		addRecipe(BLOCK_AMOUNT + 11,1);
	}
}
class hoe extends InventoryItem
{
	hoe()
	{
		super(BLOCK_AMOUNT + 15, "hoe.png", "Hoe", 0,1,0,0,1,97);
		addRecipe(5,2);
		addRecipe(BLOCK_AMOUNT + 11,1);
	}
}
class spile extends InventoryItem
{
	spile()
	{
		super(BLOCK_AMOUNT + 16, "spile.png", "Spile", 0, 1,0,0,1,40);
		addRecipe(BLOCK_AMOUNT + 11, 1);
	}
}
class sap extends InventoryItem
{
	sap()
	{
		super (BLOCK_AMOUNT + 17, "sap.png", "Sap",0,6,0,0,1,3);
	}
}
class ironDust extends InventoryItem
{
	ironDust()
	{
		super (BLOCK_AMOUNT + 18, "ironDust.png", "Iron Dust",0,6,0,BLOCK_AMOUNT + 11,1,3);
	}
}
class copperDust extends InventoryItem
{
	copperDust()
	{
		super (BLOCK_AMOUNT + 19, "copperDust.png", "Copper Dust",0,6,0,BLOCK_AMOUNT + 10,1,3);
	}
}
class goldDust extends InventoryItem
{
	goldDust()
	{
		super (BLOCK_AMOUNT + 20, "goldDust.png", "Gold Dust",0,6,0,BLOCK_AMOUNT + 12,1,3);
	}
}
class tomatoObj extends InventoryItem
{
	tomatoObj()
	{
		super (BLOCK_AMOUNT + 21, "corn.png", "Tomato", 0,6,0,0,1,3);
	}
}
class cornObj extends InventoryItem
{
	cornObj()
	{
		super (BLOCK_AMOUNT + 22, "corn.png", "Corn", 0, 6, 0, 0, 1,3);
	}
}
class goldHelmet extends InventoryItem
{
	goldHelmet()
	{
		super (BLOCK_AMOUNT + 23, "goldHelmet.png", "Gold Helmet", 0, 4,0,0,1,300);
		setHeadArmorRate(Arrays.asList(200));
	}
}
class ironHelmet extends InventoryItem
{
	ironHelmet()
	{
		super(BLOCK_AMOUNT + 24, "ironHelmet.png", "Iron Helmet", 0, 4,0,0,1,300);
		setHeadArmorRate(Arrays.asList(100));
	}
}
class ironChest extends InventoryItem
{
	ironChest()
	{
		super (BLOCK_AMOUNT + 25, "ironTorso.png", "Iron Chest", 0, 4,0,0,1,350);
		setBodyArmorRate(Arrays.asList(400));
		setArmorArmImg("ironArm.png");
	}
}
class ironLeggings extends InventoryItem
{
	ironLeggings()
	{
		super(BLOCK_AMOUNT + 26, "ironLeg.png", "Iron Leggings", 0, 4,0,0,1,320);
		setLegArmorRate(Arrays.asList(350));
	}
}
class leatherBag extends InventoryItem
{
	leatherBag()
	{
		super(BLOCK_AMOUNT + 27, "leatherBag.png", "Leather Bag", 0, 4, 0 ,0, 1,170);
		setBackStats(Arrays.asList(12));
	}
}
class largeLeatherBag extends InventoryItem
{
	largeLeatherBag()
	{
		super(BLOCK_AMOUNT + 28, "largeLeatherBag.png", "Large Leather Bag", 0, 4, 0 ,0, 1,300);
		setBackStats(Arrays.asList(25));
	}
}
class headLamp extends InventoryItem
{
	headLamp()
	{
		super(BLOCK_AMOUNT + 29,"headLamp.png", "Head Flashlight", 0, 4,0,0,1,100);
		setHeadArmorRate(Arrays.asList(10));
	}
}
class huntingBow extends InventoryItem
{
	huntingBow()
	{
		super(BLOCK_AMOUNT + 30, "bowT.png", "Hunting Bow",0,4,0,0,1,100);
	}
}
class arrow extends InventoryItem
{
	arrow()
	{
		super(BLOCK_AMOUNT + 31, "arrowT.png","Arrow",0,4,0,0,1,100);
	}
}
class arrowBag extends InventoryItem
{
	arrowBag()
	{
		super(BLOCK_AMOUNT+32,"bowBag.png","Arrow Bag",0,4,0,0,1,300);
		setBackStats(Arrays.asList(1));
	}
}
class rawChicken extends InventoryItem
{
	rawChicken()
	{
		super(BLOCK_AMOUNT+33,"rawChicken.png","Raw Chicken",0,4,0,0,1,0);
	}
}
class cookedChicken extends InventoryItem
{
	cookedChicken()
	{
		super(BLOCK_AMOUNT+34,"cookedChicken.png","Cooked Chicken",0,4,0,0,1,0);
	}
}
class woodKnife extends InventoryItem
{
	woodKnife()
	{
		super(BLOCK_AMOUNT+35,"woodKnife.png","Wood Knife",0,4,0,0,1,0);
	}
}
