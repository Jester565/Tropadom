package com.mygdx.game;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;

import box2dLight.ConeLight;
import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class MyGdxGame extends ApplicationAdapter implements ApplicationListener{
	static SpriteBatch batch;
	XYControl xyControl;
	Sprite shadow = null;
	Sprite light = null;
	static ArrayList<Integer>vocsxSet;
	static ArrayList<Integer>vocsySet;
	static ArrayList<Projectile>projectile;
	static int snowCreateMaxTime = 1000;
	int weatherChangeTimer;
	public static int snowTimer = 0;
	public static int notSnowingTimer = 0;
	static int weatherMode = 0;
	static public LightSystem lightSystem;
	public static SpriteBatch batch2;
	public static ArrayList<Sprite> sprites;
	public static boolean newG = false;
	public static boolean load = false;
	public static int timer = 0;
	Texture loading;
	Sprite Load;
	static int itemDropT = 0;
	public static int worldWidth = 1000;
	public static int gi = 0;
	float gridX = 0;
	int loadTimer = 0;
	boolean blockGameOn =  false;
	static public ArrayList <Debris>debris;
	static public ArrayList <GridStack>gridStack;
	static public ArrayList <ItemDrop>itemDrops;
	static public ArrayList<Chicken>chickens;
	static public ArrayList<BombEffect>bombEffects;
	static public ArrayList<Thread>threads;
	public static boolean display = false;
	boolean spaceInvaders = false;
	BitmapFont bitmap;
	boolean connectFourOn = false;
    public static DirectionControl dc;
    static Protagonist protagonist;
    Background background;
    GridBased gridBased;
    GridManager gridManager;
    GridManager2 gridManager2;
    FPSLogger fps;
    BallBlock blockGame;
    SaveManager saveManager;
    ConnectFour connectFour;
	public static ShapeRenderer sr;
	static LightManager lightManager;
	TitleManager titleManager;
	SpaceInvaders spaceInvaderGame;
	static HUD hud;
	public void create() {
		projectile = new ArrayList<Projectile>();
		spaceInvaderGame = new SpaceInvaders();
		blockGame = new BallBlock();
		connectFour = new ConnectFour();
		vocsxSet = new ArrayList<Integer>();
		vocsySet = new ArrayList<Integer>();
		xyControl = new XYControl();
		lightSystem = new LightSystem();
		debris = new ArrayList<Debris>();
		bitmap = new BitmapFont();
		batch = new SpriteBatch();
		fps = new FPSLogger();
		chickens = new ArrayList<Chicken>();
		loading = new Texture("jesterGames.png");
		shadow = new Sprite(new Texture("shadow.png"));
		light = new Sprite(new Texture("light.png"));
		Load = new Sprite(loading);	
		sprites = new ArrayList<Sprite>();
		bombEffects = new ArrayList<BombEffect>();
	}
	ArrayList<GridStack>getGridStack()
	{
		return gridStack;
	}
	
	public void render() {
		loadManager();
		if (loadTimer > 9)
		{
			if(timer == 0)
			{
				titleManager.draw();
			}
			if(timer > 300)
			{
				display = true;
			}
			
			if (SaveManager.loaded == true)
			{
				load = true;
			}
			if (Gdx.input.isKeyPressed(Input.Keys.F3))
			{
				newG = true;
			}
			if (Gdx.input.isKeyPressed(Input.Keys.F4))
			{
				System.out.println(Gdx.input.getX());
				System.out.println(1000-Gdx.input.getY());
			}
			
			if (load||newG)
			{
				timer ++;
				if(display)
				{
					Gdx.gl.glClearColor(0,0,1,1);
					Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
					//lightManager.lightManagerOn1();
				}
				fps.log();
				if (timer == 1)
				{
					int j = 0;
					for (int i = 1; i < worldWidth; i ++)
					{
						gridX = (i - worldWidth/2) * Grid.BLOCKSIZE;
						GridStack grid;
						if(load && SaveManager.registered.get(i-1) == true)
						{
							System.out.println(SaveManager.registered.get(i-1) +":  " + SaveManager.blockType.get(j));
							grid = new GridStack (batch, Math.round(gridX), dc.current(),dc.currentY(),i,SaveManager.registered.get(i-1),SaveManager.blockType.get(j),SaveManager.range.get(j), SaveManager.sourceD.get(j), 
									SaveManager.tGrowth.get(j), SaveManager.iType.get(j), SaveManager.pistonAngle.get(j),SaveManager.awdc.get(j),SaveManager.pistonOut.get(j),SaveManager.imageFlip.get(j),
									SaveManager.eOn.get(j),SaveManager.awd.get(j),SaveManager.eOnD.get(j),SaveManager.eOnChange.get(j), SaveManager.signalUsed.get(j),SaveManager.mj.get(j),SaveManager.cookTimer.get(j),
									SaveManager.cookTimer2.get(j),SaveManager.parts.get(j),SaveManager.fuel.get(j),SaveManager.awsr.get(j),SaveManager.awdct.get(j));
							j++;
							//grid = new GridStack (Math.round(gridX), dc.current(),dc.currentY(),i, false,null);
						}
						else
						{
							grid = new GridStack (batch, Math.round(gridX), dc.current(),dc.currentY(),i, false,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
						}
						gridStack.add(grid);
					}
				}
				for(int i = 0; i < debris.size(); i++)
				{
					if(debris.get(i).alpha > -.1f)
					{
						debris.get(i).draw();
					}
					else
					{
						//debris.get(i).batch.dispose();
						debris.remove(i);
					}
				}
				protagonist.draw();
				
				for (int i = 0; i < gridStack.size(); i++) {
					gridStack.get(i).createLight();
					gridStack.get(i).draw(dc.speed(),dc.dy(),dc.currentY());
				}
				
				for (int i = 0; i < gridStack.size(); i++) {
					gridStack.get(i).drawRect();
				}
				dc.directionOn(Protagonist._x,Protagonist._y, Protagonist.WIDTH, Protagonist.HEIGHT + 10);
				dc.directionYOn(Protagonist._x,Protagonist._y, Protagonist.WIDTH, Protagonist.HEIGHT + 10);
				itemDropManager();
				chickenManager();
				for (int i = 0; i < projectile.size(); i++) {
					projectile.get(i).draw();
				}
				
				if (load)
				{
					dc.loadCurrent();
				}
				
				for(int i = 0; i < bombEffects.size();i++)
				{
					bombEffects.get(i).draw();
				}
				if(timer == 1 && load)
				{
					SaveManager.loadInventory(HUD.inventory);
				}
			}
			
			
		}
		if (display == false && newG || display == false && load)
		{
			batch.begin();
			Load.draw(batch);
			batch.end();
			sr.begin(ShapeType.Filled);
			sr.setColor(1,1,0,6f);
			sr.rect(848 , 98, 308, 44);
			sr.setColor(1,0,0,6f);
			sr.rect(850 , 100, timer, 40);
			sr.end();		
		}	
		if(MyGdxGame.display)
		{
			lightSystem.draw();
		}
		if(display)
		{
			batch.begin();
			for(int i = 0; i < gridStack.size(); i++)
			{
				for(int k = 0; k < gridStack.get(i).Grids().size();k++)
				{
					gridStack.get(i).Grids().get(k).drawShadow(sr,batch,shadow,light);
				}
			}
			batch.end();
		}
		if(timer >= 1)
		{
			for (int i = 0; i < gridStack.size(); i++) {
				gridStack.get(i).updateCords();
			}
		}
		weatherManager();
		if(display)
		{
			//lightManager.lightManagerOn2();
			hud.draw();
			hud.drawSR();
		}
		if(loadTimer > 9)
		{
			SaveManager.saveManagment();
			if (load)
			{
				dc.loadCurrent();
			}
			if(timer == 1 && load)
			{
				SaveManager.loadInventory(HUD.inventory);
			}
		}
		vocsxSet.clear();
		vocsySet.clear();
		/*
		if(display)
		{
			for(int i = 0; i < gridStack.size(); i++)
			{
				if(gridStack.get(i).done )
				{
					
					for(int j = 0; j < gridStack.get(i).Grids.size();j++)
					{
						if(gridStack.get(i).Grids.get(j).visibleOverride)
						{
							//vocsxSet.add(gridStack.get(i).Grids.get(j)._x);
							//vocsySet.add(gridStack.get(i).Grids.get(j)._y);
						}
					}
					a
					gridStack.remove(i);
					i--;
				}
			}
		}
		*/
		//batch.dispose();
		
	}
	private String getValue(double temp2) {
		// TODO Auto-generated method stub
		return null;
	}
	void weatherManager()
	{
		weatherChangeTimer--;
		if(Gdx.input.isKeyPressed(Input.Keys.T))
		{
			weatherMode = 1;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.Y))
		{
			weatherMode = 2;
		}
		if(weatherMode == 2)
		{
			if(snowTimer < snowCreateMaxTime)
			{
				snowTimer++;
			}
			if(notSnowingTimer > 0)
			{
				notSnowingTimer--;
			}
		}
		else
		{
			if(notSnowingTimer < snowCreateMaxTime)
			{
				notSnowingTimer++;
			}
			if(snowTimer > 0)
			{
				snowTimer--;
			}
		}
		if(Gdx.input.isKeyPressed(Input.Keys.U))
		{
			weatherMode = 0;
		}
		if(weatherMode > 0)
		{
			if(display)
			{
			for(int i = 0;i<gridStack.size();i++)
			{
				if(gridStack.get(i).done)
				{
					if(weatherMode == 1)
					{
						gridStack.get(i).rain();
					}
					else
					{
						gridStack.get(i).snow();
					}
				}
			}
			}
		}
	}
	void loadManager()
	{
		batch.begin();
		Load.draw(batch);
		//bitmap.setScale(2f);
		bitmap.setColor(1, 1, 0, 1);
		if (loadTimer == 0)
		{
			//bitmap.drawMultiLine(batch, "Creating Grid Managing Classes...", 940, 100);
		}
		if (loadTimer == 1)
		{
			
			gridManager = new GridManager();
			gridManager2 = new GridManager2();
			//bitmap.drawMultiLine(batch, "Creating Grids...", 940, 100);
		}
		if(loadTimer == 2)
		{
			
			gridStack = new ArrayList<GridStack>();
			//bitmap.drawMultiLine(batch, "Creating Grids...", 940, 100);
		}
		if (loadTimer == 3)
		{
			
			itemDrops = new ArrayList<ItemDrop>();
			//bitmap.drawMultiLine(batch, "Creating Character...", 940, 100);
		}
		if (loadTimer == 4)
		{
			
			dc = new DirectionControl();
			background = new Background();
			protagonist = new Protagonist();
			//bitmap.drawMultiLine(batch, "Creating Grid Based Class...", 940, 100);
		}
		if (loadTimer == 5)
		{
			gridBased = new GridBased();
			//bitmap.drawMultiLine(batch, "Creating Lighting...", 940, 100);
		}
		if(loadTimer == 6)
		{
		
			sr = new ShapeRenderer();
			lightManager = new LightManager();
			//bitmap.drawMultiLine(batch, "Creating Inventory...", 940, 100);
		}
		if(loadTimer == 7)
		{
			
			hud = new HUD();
			//bitmap.drawMultiLine(batch, "Creating Title...", 940, 100);
		}
		if(loadTimer == 8)
		{
			
			saveManager = new SaveManager();
			batch2 = new SpriteBatch();
			titleManager = new TitleManager();
		}
		batch.end();
		loadTimer ++;
	}
	static void itemDropManager()
	{
		itemDropT--;
		if (Gdx.input.isKeyPressed(Input.Keys.Q) && itemDropT < 0)
		{
			if (HUD.typeN > -1)
			{
				int addAmount = 1;
				if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) && HUD.inventory.item(HUD.typeN -1).count <= 10)
				{
					addAmount = HUD.inventory.item(HUD.typeN -1).count;
				}
				else if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
				{
					addAmount = 10;
				}
				
				ItemDrop id;
				id = new ItemDrop (HUD.img,HUD.typeN,addAmount, Protagonist._x, Protagonist._y);
				itemDrops.add(id);
				itemDropT = 20;
				HUD.inventory.item(HUD.typeN -1).remove(addAmount);
			}
		}
		for (int i = 0; i < itemDrops.size();i++)
		{
			itemDrops.get(i).draw();
		}
	}
	static void chickenManager()
	{
		itemDropT--;
		if (Gdx.input.isKeyPressed(Input.Keys.C) && itemDropT < 0)
		{
				Chicken id;
				id = new Chicken (Protagonist._x, Protagonist._y);
				chickens.add(id);
				itemDropT = 20;
		}
		for (int i = 0; i < chickens.size();i++)
		{
			chickens.get(i).draw();
		}
	}
}
