package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ArmorBox {
	//static BitmapFont bm;
	static SpriteBatch batch = new SpriteBatch();
	public static boolean refreshing = false;
	static int refreshTimer = 0;
	public static int backPackRows = 0;
	public static int backPackSlots = 0;
	public static String head = null;
	public static String body = null;
	public static String arm = null;
	public static String leg = null;
	public static String back = null;
	static int backPackErrorTimer = 0;
	public static boolean backPackEmpty = true;
	public static ArrayList<Integer>headStats;
	public static ArrayList<Integer>bodyStats;
	public static ArrayList<Integer>legStats;
	public static ArrayList<Integer>backStats;
	static Inventory inventory;
	Sprite Box = new Sprite();
	static boolean backPackFull = false;
	static Sprite HeadImg = new Sprite();
	static Sprite BodyImg = new Sprite();
	static Sprite LegImg = new Sprite();
	static Sprite BackImg = new Sprite();
	ArmorBox()
	{
		Box = new Sprite(new Texture("armorBox.png"));
		//batch2 = new SpriteBatch();
		//bm = new BitmapFont();
		inventory = new Inventory();
	}
	void draw()
	{
		batch.begin();
		backPackErrorTimer--;
		if(MyGdxGame.timer == 1 && MyGdxGame.load)
		{
			head = SaveManager.armorHeadImg;
			body = SaveManager.armorTorsoImg;
			leg = SaveManager.armorLegImg;
			back = SaveManager.backImg;
			arm = SaveManager.armorArmImg;
			headStats = SaveManager.armorHeadStats;
			bodyStats = SaveManager.armorTorsoStats;
			legStats = SaveManager.armorLegStats;
			backStats = SaveManager.backStats;
			for(int i = 0; i < inventory.size(); i++)
			{
				inventory.item(i).add(SaveManager.backPackInventory.get(i));
			}
			refresh();
		}
		int yOff = -90;
		Box.setScale(.6f);
		Box.setPosition(-123,-HUD.armorY + 636);
		Box.draw(batch);
		refreshTimer --;
		box(HeadImg,head,1280,1373 - HUD.armorY + yOff,100);
		box(BodyImg,body,1295,1357 - HUD.armorY + yOff,60);
		box(LegImg,leg,1280,1305 - HUD.armorY + yOff,100);
		box(BackImg,back,1310,1237 - HUD.armorY + yOff,60 );
		if(back!= null)
		{
			drawBackPack();
		}
		if (refreshTimer >= 0)
		{
			refreshing = true;
		}
		else
		{
			refreshing = false;
		}
		batch.end();
	}
	static void refresh()
	{
		refreshTimer = 3;
		if(head != null)
		{
			HeadImg = new Sprite(new Texture(head));
		}
		if(body != null)
		{
			BodyImg = new Sprite(new Texture(body));
		}
		if(leg != null)
		{
			LegImg = new Sprite(new Texture(leg));
		}
		if(back != null)
		{
			BackImg = new Sprite(new Texture(back));
		}
	}
	void box(Sprite sp, String s, int x, int y, float size)
	{
		if(s != null)
		{
			sp.setSize(size,size);
			sp.setPosition(x, y);
			sp.draw(batch);
			if(Button.button(x, y, size, size, Gdx.input.isButtonPressed(Input.Buttons.LEFT)) && HUD.armorDisplay || Button.button(x, y, size, size, Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) && HUD.armorDisplay)
			{
				for (int i = 0; i < HUD.inventory.size();i++)
				{
					if (s == HUD.inventory.item(i).img)
					{
						
						if(HUD.inventory.item(i).headArmorRate != null)
						{
							HUD.inventory.item(i).add(1);
							head = null;
						}
						if(HUD.inventory.item(i).bodyArmorRate != null)
						{
							HUD.inventory.item(i).add(1);
							body = null;
						}
						if(HUD.inventory.item(i).legArmorRate != null)
						{
							HUD.inventory.item(i).add(1);
							leg = null;
						}
						if(HUD.inventory.item(i).backStats != null)
						{
							if(backPackEmpty == true)
							{
								HUD.inventory.item(i).add(1);
								back = null;
							}
							else
							{
								backPackErrorTimer = 60;
							}
						}
						refresh();
					}
				}
			}
		}
		if(backPackErrorTimer > 0)
		{
			//bm.setColor(1,0,1,1);
			//bm.setScale(1);
			//bm.drawMultiLine(batch2, "Must Empty BackPack Before Storing", 70, 970);
		}
	}
	void drawBackPack()
	{
		if(backStats!=null)
		{
			backPackSlots = backStats.get(0);
			backPackRows = (int)(backPackSlots/10) + 1;
		}
	}
	/*
	Boolean drawable(SpriteAdapter s)
	{
		if (s != null)
		{
			return true;
		}
		return false;
	}
	
	public static Sprite setBox(String s)
	{
		Texture t;
		Sprite sp;
		if (s != null)
		{
			t = new Texture(s);
			sp = new Sprite(t);
			return sp;
		}
		return null;
	}
	*/
	public static void drawInventory()
	{
		int x = 683;
		int y = 847;
		inventory.reset();
	    InventoryItem item = inventory.next(false);
	    while (item != null) {
	      item.draw(x, y, false, 25);
	      item.chestTransferManager(HUD.inventory); 
	      item = inventory.next(false);
	      x += 71;
	      if (x >= 71 * 10 + 666)
		   {
			   y-= 60;
			   x = 683;
		   }
	     
	    }
		 if(y <= 849 - 60 * (backPackRows-1))
	     {
	   	  if(x > 656 + 71 * (ArmorBox.backPackSlots - (ArmorBox.backPackRows-1) * 10))
	   	  {
	   		  backPackFull = true;
	   	  }
	   	  else
	   	  {
	   		  backPackFull = false;
	   	  }
	     }
	     else
	     {
	   	  backPackFull = false;
	     }
		 if(x == 683 && y == 847)
	     {
	   	  backPackEmpty = true;
	     }
	     else
	     {
	   	  backPackEmpty = false;
	     } 
	}
 
}
