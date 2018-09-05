package com.mygdx.game;

import java.awt.event.MouseWheelEvent;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class HUD {
	public static Inventory inventory;
	public static Inventory inventory2;
	SpriteBatch batch = new SpriteBatch();
	MouseWheelEvent mwe;
	Sprite TabR2 = new Sprite();
	Sprite TabB2 = new Sprite();
	Sprite TabR = new Sprite();
	Sprite TabB = new Sprite();
	Sprite Su = new Sprite();
	Sprite Su2 = new Sprite();
	Sprite Awb = new Sprite();
	Sprite craftingDisplay = new Sprite();
	Sprite PwrBack = new Sprite();
	Sprite GenBack = new Sprite();
	Sprite Bar = new Sprite();
	Sprite IBar = new Sprite();
	Sprite IBarR = new Sprite();
	Sprite IMenu = new Sprite();
	Sprite iu = new Sprite();
	Sprite id = new Sprite();
	Sprite iur = new Sprite();
	Sprite idr = new Sprite();
	Sprite ChestMenu = new Sprite();
	Sprite FireMenu = new Sprite();
	Sprite FireMenuOff = new Sprite();
	Sprite MenuExitR = new Sprite();
	Sprite MenuExit = new Sprite();
	//SpriteBatch batch;
	ShapeRenderer sr;
	static boolean partsCalled = false;
	int backPackY = -500;
	int hotBarItem = 8;
	int hotBarOff = 0;
	int backPackX = 0;
	int IX = 609;
	int IY = 930;
	int buttonTimer = 0;
	public static boolean backPackDisplay = false;
	public static int armorY = 0;
	public static boolean armorDisplay = false;
	public static int category = 2;
	public static int page = 1;
	public static int categoryTimer = 0;
	public static final int SLOT_DIFFERENCEY =80;
	public static final int ITEM_DIFFERENCEX = 83;
	public static final int PART_DIFFERENCEX = 130;
	public static int inventoryOn = 0;
	public static int inventoryOnTimer = 0;
	public static final int BARX = 920;
	public static final int BARWIDTH = 160;
	public static int IX2 = 700;
	public static String img = null;
	public static int IY2 = 760;
	public static final int BARY = 880;
	public static final int BARHEIGHT = 40;
	public static int hotBarX = 0;
	public static int hotBarDisplay = 0;
	public static int blockN = -1;
	public static int typeN = -1;
	public static int transferT = 0;
	static int TITLESHOW = 40;
	public static boolean inventoryFull = false;
	ArmorBox armorBox;
HUD()
{
	//batch2 = new SpriteBatch();
	TabR = new Sprite(new Texture("itTabR.png"));
	TabB = new Sprite(new Texture("itTabG.png"));
	TabR2 = new Sprite(new Texture("itTab2R.png"));
	TabB2 = new Sprite(new Texture("itTab2G.png"));
	armorBox = new ArmorBox();
	sr = new ShapeRenderer();
	inventory = new Inventory();
	inventory2 = new Inventory();
	Su = new Sprite(new Texture("signalUsed.png"));
	Su2 = new Sprite(new Texture("signalUsed2.png"));
	Awb = new Sprite(new Texture("awb.png"));
	Bar = new Sprite(new Texture("it.png"));
	IBar = new Sprite(new Texture("itBar.png"));
	IBarR = new Sprite(new Texture("itBarR.png"));
	IMenu = new Sprite(new Texture("itPage.png"));
	iu = new Sprite(new Texture("itUp.png"));
	id = new Sprite(new Texture("itDown.png"));
	idr = new Sprite(new Texture("itDownR.png"));
	iur = new Sprite(new Texture("itUpR.png"));
	ChestMenu = new Sprite(new Texture("chestBack.png"));
	FireMenu = new Sprite(new Texture("fireBack1.png"));
	FireMenuOff = new Sprite(new Texture("fireBack.png"));
	MenuExit = new Sprite(new Texture("exitMenu.png"));
	MenuExitR = new Sprite(new Texture("exitMenuR.png"));
	GenBack = new Sprite(new Texture("genBack.png"));
	PwrBack = new Sprite(new Texture("powerBack1.png"));
	craftingDisplay = new Sprite(new Texture("craftingDisplay.png"));
	//batch = new SpriteBatch();
}
void draw()
{
	//batch2.begin();
	transferT--;
	int x = IX;
	int y = IY;
	int x2 = IX2;
	int y2 = IY2;
	craftingDisplay.setScale(.6f);
	craftingDisplay.setPosition(1500,900);
	Awb.setScale(.6f);
	Awb.setPosition(-30, 180);
	Bar.setScale(.6f);
	Bar.setPosition(0, 200);
	TabR.setScale(.6f);
	TabB.setScale(.6f);
	TabR.setPosition(0, 200);
	TabB.setPosition(0, 200);
	TabR2.setScale(.6f);
	TabB2.setScale(.6f);
	TabR2.setPosition(0, 200);
	TabB2.setPosition(0, 200);
	IBar.setScale(.6f);
	IBar.setPosition(0, 200);
	IBarR.setScale(.6f);
	IBarR.setPosition(0, 200);
	IMenu.setScale(.6f);
	IMenu.setPosition(0, 200);
	iu.setScale(.6f);
	iu.setPosition( - 30, 35);
	id.setScale(.6f);
	id.setPosition(-30, 180);
	iur.setScale(.6f);
	iur.setPosition( - 30, 35);
	idr.setScale(.6f);
	PwrBack.setScale(.6f);
	idr.setPosition(-30, 180);
	Su.setScale(.6f);
	Su2.setScale(.6f);
	Su.setPosition(-30, 180);
	Su2.setPosition(-30, 180);
	GenBack.setScale(.6f);
	ChestMenu.setScale(.6f);
	ChestMenu.setPosition(-30, 180);
	FireMenu.setScale(.6f);
	FireMenu.setPosition(-30, 180);
	FireMenuOff.setScale(.6f);
	FireMenuOff.setPosition(-30, 180);
	MenuExit.setScale(.6f);
	MenuExitR.setScale(.6f);
	MenuExit.setPosition(-30,180);
	GenBack.setPosition(-30,180);
	PwrBack.setPosition(-30, 180);
	MenuExitR.setPosition(-30,180);
	
	buttonTimer--;
	
	drawSR();
	
	if (inventoryOn == 1)
	{	batch.begin();
		//IMenu.draw( batch2);
		IMenu.draw(batch);
		if(page > 1)
		{
		//iu.draw( batch2);
			iu.draw(batch);
		}
		//id.draw( batch2);
		id.draw(batch);
		batch.end();
		if (Button.overButton(920, 380, 148, 30) && buttonTimer <= 0)
		{
			batch.begin();
			//idr.draw( batch2);
			idr.draw(batch);
			batch.end();
			if (Button.button(920, 380, 148, 30, Gdx.input.isButtonPressed(Input.Buttons.LEFT)))
			{
				page ++;
				buttonTimer = 20;
			}
		}
		if (Button.overButton(920, 800, 148, 30)&& buttonTimer <= 0 && page > 1)
		{
			//iur.draw( batch2);
			batch.begin();
			iur.draw(batch);
			batch.end();
			if (Button.button(920, 800, 148, 30, Gdx.input.isButtonPressed(Input.Buttons.LEFT)))
			{
				page --;
				buttonTimer = 20;
			}
		}
	}
	batch.begin();
	//IBar.draw( batch2);
	IBar.draw(batch);
	batch.end();
	if (Gdx.input.getX() > BARX && Gdx.input.getX() < BARX + BARWIDTH && 1000 - Gdx.input.getY() > BARY && 1000 - Gdx.input.getY() < BARY + BARHEIGHT ||GridBased.inventoryOn() != 0)
	{
		if(backPackDisplay == false && armorDisplay == false)
		{
		//IBarR.draw( batch2);
			batch.begin();
			IBarR.draw(batch);
			batch.end();
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && inventoryOn == 0 && inventoryOnTimer < 0 && GridBased.inventoryOn() == 0)
			{
				inventoryOn = 1;
				inventoryOnTimer = 20;
			}
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && inventoryOn == 1 && inventoryOnTimer < 0||GridBased.inventoryOn() != 0)
			{
				inventoryOn = 0;
				inventoryOnTimer = 20;
			}
		}
	}
	//batch2.end();
	armorBox.draw();
	//batch2.begin();
	if(Button.overButton(1289, 892, 109,25))
	{
		//TabR.draw( batch2);
		batch.begin();
		TabR.draw(batch);
		batch.end();
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && inventoryOn == 0 && GridBased.inventoryOn() == 0 && inventoryOnTimer < 0 && backPackDisplay == false)
		{
			inventoryOnTimer = 20;
			if(armorDisplay == false)
			{
				armorDisplay = true;
			}
			else
			{
				armorDisplay = false;
			}
		}
	}
	else
	{
		batch.begin();
		//TabB.draw( batch2);
		TabB.draw(batch);
		batch.end();
	}
	if(Button.overButton(571, 892, 109,25))
	{
		//TabR2.draw( batch2);
		batch.begin();
		TabR2.draw(batch);
		batch.end();
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && inventoryOn == 0 && GridBased.inventoryOn() == 0 && inventoryOnTimer < 0 && armorDisplay == false)
		{
			inventoryOnTimer = 20;
			if(backPackDisplay == false)
			{
				backPackDisplay = true;
			}
			else
			{
				backPackDisplay = false;
			}
		}
	}
	else
	{
		batch.begin();
		TabB2.draw(batch);
		batch.end();
	}
	if(backPackDisplay == true)
	{
		if(backPackY > 0)
		{
			backPackY -= 10;
		}
		if(backPackY < 2 && backPackX < 712)
		{
			backPackX += 20;
		}
	}
	else if (backPackY < 500)
	{
		if(backPackX > 0)
		{
			backPackX -= 20;
		}
		if (backPackX < 2)
		{
			backPackY += 10;
		}
	}
	if(armorDisplay == true)
	{
		if(armorY < 500)
		{
			armorY+= 10;
		}
	}
	else if (armorY > 0)
	{
		armorY-=10;
	}
	
	//Bar.draw( batch2);
	batch.begin();
	Bar.draw(batch);
	batch.end();
	//batch2.end();
	 if (inventoryOn == 1)
	    {
	    	inventory2.reset();
	        InventoryItem item2 = inventory2.next(true);
	        while (item2 != null) {
	        	if (y2 + (page - 1) * SLOT_DIFFERENCEY * 5 < IY2 + 15)
        		{
        			
	        		item2.draw(x2, y2 + (page - 1) * SLOT_DIFFERENCEY * 5, true, 25 );
	        		item2.drawPart(inventory2,x2, y2 + (page - 1) * SLOT_DIFFERENCEY * 5);
        		}
	        		item2 = inventory2.next(true);
	        		y2 -= SLOT_DIFFERENCEY;
	        		if (y2  + (page - 1 )* (SLOT_DIFFERENCEY * 4.999f) <  IY2 - (4.999f * SLOT_DIFFERENCEY))
	        		{
	        			y2 -= 500;
	        		}
	        		
	        }
	        partsCalled = true;
	    }
	 
	
	inventoryOnTimer--;
	//batch2.begin();
	batch.begin();
	if (GridBased.inventoryOn() == 1)
	{
		//ChestMenu.draw( batch2);
		ChestMenu.draw(batch);
	}
	if (GridBased.inventoryOn()==2 && GridBased.cooking()||GridBased.inventoryOn()==3 && GridBased.cooking())
	{
		//FireMenu.draw( batch2);
		FireMenu.draw(batch);
	}
	else if (GridBased.inventoryOn()==2 || GridBased.inventoryOn()== 3)
	{
		//FireMenuOff.draw( batch2);
		FireMenuOff.draw(batch);
	}
	else if (GridBased.inventoryOn() == 4)
	{
		Integer mjCount = (int) GridBased.mjCount();
		//GenBack.draw( batch2);
		GenBack.draw(batch);
		/*
		bitmap.setColor(1, 0, 0, 1);
		bitmap.drawMultiLine("Power: " +Integer.toString(mjCount) + " mj", 940, 650);
		*/
	}
	else if (GridBased.inventoryOn()==5)
	{
		Integer mjCount = (int) GridBased.mjCount();
		//PwrBack.draw( batch2);
		PwrBack.draw(batch);
		/*
		bitmap.setColor(0, 0, 1, 1);
		bitmap.drawMultiLine( "Power: " +Integer.toString(mjCount) + " mj", 940, 500);
		*/
	}
	if (GridBased.inventoryOn() == 6)
	{
		//Awb.draw( batch2);
		Awb.draw(batch);
		/*
		bitmap.setColor(1,1,0,1);
		bitmap.setScale(1.5f);
		bitmap.drawMultiLine(Integer.toString(GridBased.awdc()), 1210, 582);
		bitmap.setScale(1);
		*/
	}
	batch.end();
	if (GridBased.inventoryOn() > 0)
	{
		//MenuExit.draw( batch2);
		batch.begin();
		MenuExit.draw(batch);
		batch.end();
		if(Button.overButton(1247, 760, 50,23))
		{
			//MenuExitR.draw( batch2);
			batch.begin();
			MenuExitR.draw(batch);
			batch.end();
			if (Button.button(1247, 760, 50, 23, Gdx.input.isButtonPressed(Input.Buttons.LEFT)))
			{
				GridBased.InventoryOff();
			}
		}
	}
	if (GridBased.inventoryOn() > 1 && GridBased.inventoryOn() != 6)
	{
		//Su.draw( batch2);
		batch.begin();
		Su.draw(batch);
			if (GridBased.signalUsed() == true)
			{
				//Su2.draw( batch2);
				Su2.draw(batch);
			}
			batch.end();
		
	}
	
	if (Button.button(640,825,50,50,Gdx.input.isButtonPressed(Input.Buttons.LEFT)))
	{
		if (categoryTimer < 20)
		{
			category = 1;
			page = 1;
		}
	}
	if (Button.button(755,825,50,50,Gdx.input.isButtonPressed(Input.Buttons.LEFT)))
	{
		if (categoryTimer < 20)
		{
			category = 2;
			page = 1;
		}
	}
	if (Button.button(870,825,50,50,Gdx.input.isButtonPressed(Input.Buttons.LEFT)))
	{
		if (categoryTimer < 20)
		{
			category = 3;
			page = 1;
		}
	}
	if (Button.button(985,825,50,50,Gdx.input.isButtonPressed(Input.Buttons.LEFT)))
	{
		if (categoryTimer < 20)
		{
			category = 4;
			page = 1;
		}
	}
	if (Button.button(1100,825,50,50,Gdx.input.isButtonPressed(Input.Buttons.LEFT)))
	{
		if (categoryTimer < 20)
		{
			category = 5;
			page = 1;
		}
	}
	if (Button.button(1215,825,50,50,Gdx.input.isButtonPressed(Input.Buttons.LEFT)))
	{
		if (categoryTimer < 20)
		{
			category = 6;
			page = 1;
		}
	}
	//batch2.end();
	inventory.reset();
    InventoryItem item = inventory.next(false);
    while (item != null) {
      item.draw(x, y, false, 25);
      if (GridBased.inventory2() != null)
      {
    	  item.chestTransferManager(GridBased.inventory2());
      }
      if(backPackX > 700)
      {
    	  item.chestTransferManager(ArmorBox.inventory);
      }
      if (item.blockN() > -1)
      {
    	  blockN = item.blockN();
      }
      if (item.itemN() > -1)
      {
    	  typeN = item.itemN();
      }
      if (item.image() != null)
      {
    	  img = item.image();
      }
     
      item.title();
      item = inventory.next(false);
      x += ITEM_DIFFERENCEX;
    }
   if (x >= ITEM_DIFFERENCEX * 10 + IX)
   {
	   inventoryFull = true;
   }
   else
   {
	   inventoryFull = false;
   }
    if (hotBarItem > 6)
    {
    	hotBarOff = 2;
    }
    else
    {
    	hotBarOff = 0;
    }
    if (hotBarDisplay == TITLESHOW - 1)
    {
    	refresh();
    }
    
    if(Protagonist.arrowFire==false)
    {
    if (Gdx.input.isKeyPressed(Input.Keys.NUM_1))
	{
    	hotBarItem = 0;
     	refreshTitle();
	}
    if (Gdx.input.isKeyPressed(Input.Keys.NUM_2))
   	{
       	hotBarItem = 1;
     	refreshTitle();
   	}
    if (Gdx.input.isKeyPressed(Input.Keys.NUM_3))
   	{
       	hotBarItem = 2;
     	refreshTitle();
   	}
    if (Gdx.input.isKeyPressed(Input.Keys.NUM_4))
   	{
       	hotBarItem = 3;
     	refreshTitle();
   	}
    if (Gdx.input.isKeyPressed(Input.Keys.NUM_5))
   	{
       	hotBarItem = 4;
     	refreshTitle();
   	}
    if (Gdx.input.isKeyPressed(Input.Keys.NUM_6))
   	{
       	hotBarItem = 5;
     	refreshTitle();
   	}
    if (Gdx.input.isKeyPressed(Input.Keys.NUM_7))
   	{
       	hotBarItem = 6;
     	refreshTitle();
   	}
    if (Gdx.input.isKeyPressed(Input.Keys.NUM_8))
   	{
       	hotBarItem = 7;
     	refreshTitle();
   	}
    if (Gdx.input.isKeyPressed(Input.Keys.NUM_9))
   	{
       	hotBarItem = 8;
     	refreshTitle();
   	}
    if (Gdx.input.isKeyPressed(Input.Keys.NUM_0))
   	{
       	hotBarItem = 9;
       	refreshTitle();
   	}
    }
    hotBarDisplay--;
    hotBarX = hotBarItem * 83 + IX ;
    if(Protagonist.craftingTimer > 0)
    {
    	/*
    	shapeAdapter.setBeforeShadow();
    	shapeAdapter.setColor(0, 1, 0, 1);
    	shapeAdapter.rect(false, 1572, 924, 150-(((float)(MyGdxGame.protagonist.craftingTimer)/(float)(MyGdxGame.protagonist.craftingTimerInitial)) * 150), 12);
    	*/
    	batch.begin();
    	craftingDisplay.draw(batch);
    	batch.end();
    }
	chestDraw();
	
	if (typeN <= 0)
	{
		img = null;
	}		
}
void drawSR()
{
	sr.begin(ShapeType.Line);
		sr.setColor(1,0,0,6f);
		sr.ellipse(IX - 58 + hotBarItem * 83 - hotBarOff + 50, IY - 6, 41, 41);
		sr.ellipse(IX - 57 + hotBarItem * 83 - hotBarOff + 50, IY - 5, 39, 39);
		sr.ellipse(IX - 56 + hotBarItem * 83 - hotBarOff + 50, IY - 4, 37, 37);
		 if (GridBased.inventoryOn() == 6)
		 {
			 if (GridBased.awd() == false)
			 {
				 sr.rect(653, 572, 26, 26);
			 }
			 else
			 {
				 sr.rect(829, 572, 26, 26);
			 }
		 }
		 sr.end();
	    sr.begin(ShapeType.Filled);
	    sr.setColor(.1f,.1f,.1f,1);
		sr.rect(576, 882 - ArmorBox.backPackRows * 60 + backPackY, 815, ArmorBox.backPackRows*60 + 20);
		 if (GridBased.cookingTimer() < 502 && GridBased.inventoryOn()==2 && GridBased.cooking()||GridBased.cookingTimer() < 502 && GridBased.inventoryOn()==3 && GridBased.cooking())
			{
				sr.setColor(1,0,0,1);
				sr.rect(885, 660, (float)(GridBased.cookingTimer()/2.4), 7);
			}
			
			for(int i = 0; i < ArmorBox.backPackRows; i++)
			{
				if(backPackY < 5)
				{
				sr.setColor(0,0,.4f,1);
				sr.rect(666,892 - (i+1) * 60,710, 60);
				for(int j = 0; j < 10; j++)
				{
					if(i>=ArmorBox.backPackRows -1)
					{
					if(j < ArmorBox.backPackSlots - ((ArmorBox.backPackRows-1) * 10))
					{
						sr.setColor(.2f,.2f,.2f,1);
						sr.circle(666+71*(j) + 35.5f, 892 - (i+1)*60 + 30f, 27f);
					}
					}
					else
					{
						sr.setColor(.2f,.2f,.2f,1);
						sr.circle(666+71*(j) + 35.5f, 892 - (i+1)*60 + 30f, 27f);
					}
				}
				
				}
			}
			sr.setColor(.2f,.2f,.2f,1);
			sr.rect(586, 892 + backPackY - ArmorBox.backPackRows * 60, 80, ArmorBox.backPackRows * 60);
			sr.end();
			sr.begin(ShapeType.Line);
			sr.setColor(0,0,0,1);
			sr.rect(586, 892 + backPackY - ArmorBox.backPackRows * 60, 80, ArmorBox.backPackRows * 60);
			for(int i = 0; i < ArmorBox.backPackRows; i++)
			{
				if(backPackY < 5)
				{
					sr.rect(666,892 - (i+1) * 60,710, 60);
					for(int j = 0; j < 10; j++)
					{
						if(i>=ArmorBox.backPackRows -1)
						{
							if(j < ArmorBox.backPackSlots - ((ArmorBox.backPackRows-1) * 10))
							{
								sr.circle(666+71*(j) + 35.5f, 892 - (i+1)*60 + 30f, 27f);
							}
						}
						else
						{
							sr.circle(666+71*(j) + 35.5f, 892 - (i+1)*60 + 30f, 27f);
						}
					}
				}
			}
			sr.end();
			 //batch2.begin();
			if(backPackX > 2)
			{
				ArmorBox.drawInventory();
			}
			// batch2.end();
			sr.begin(ShapeType.Filled);
			 sr.setColor(.1f,.1f,.1f,1);
			sr.rect(666 + backPackX, 892 - ArmorBox.backPackRows * 60 + backPackY, 710 - backPackX, ArmorBox.backPackRows*60);
			sr.end();
}
public static void refresh()
{
	blockN = -1;
	typeN = -1;
}
public static void refreshTitle()
{
	hotBarDisplay = TITLESHOW;
}
public static void chestDraw()
{
	if (GridBased.inventoryOn() == 1)
	{
		int x2 = 630;
		int y2 = 730;
		GridBased.inventory2().reset();
	    InventoryItem item = GridBased.inventory2().next(false);
	    while (item != null) {
	    
	      item.draw(x2, y2, false, 35);
	      if (GridBased.inventory2() != null)
	      {
	    	  item.chestTransferManager(inventory);
	      }
	      item = GridBased.inventory2().next(false);
	      x2 += 120;
	      if (x2 > 1325)
	      {
	    	  x2 = 630;
	    	  y2 -= 140;
	      }
	    }
	}
	if (GridBased.inventoryOn() >= 2 && GridBased.inventoryOn() != 6)
	{
		int x2 = 630;
		int y2 = 730;
		GridBased.inventory2().reset();
	    InventoryItem item = GridBased.inventory2().next(false);
	    while (item != null) {
	    
	      item.chestDraw(x2, y2, 35);
	      if (GridBased.inventory2() != null)
	      {
	    	  item.chestTransferManager(inventory);
	      }
	      item = GridBased.inventory2().next(false);
	    }
	}
}
}
