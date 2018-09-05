package com.mygdx.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import box2dLight.PointLight;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;

public class Grid extends XYControl {
	SpriteBatch batch;
	ArrayList acceptFuel = null;
	ArrayList acceptParts = null;
	float lec = (float) Math.random() * 100;
	Sprite BreakImg = new Sprite();
	ArrayList<Explosion> explosions;
	int randomGrassSize = (int)(Math.random()*(BLOCKSIZE/1.25f)+ BLOCKSIZE*.4378f);
	boolean visibleOverride = false;
	double randomVisibleOverride = Math.random();
	boolean pistonPushed = false;
	boolean vineCreated = false;
	boolean protagonist = false;
	boolean waterOn = false;
	int vineExtendTimer = 0;
	int snowTimer = 0;
	int blockAttached = 0;
	boolean snowLayerHere = false;
	int buttonSignalTimer = 0;
	boolean pistonOut = false;
	LightSource lightSource;
	int snowCreateDestroyTimer = (int)(Math.random()*900 + 100);
	int breakLimit;
	float shadowAlphaRandom = 0;
	float lightAlpha = 0;
	boolean damaged = false;
	boolean lightCreated3 = false;
	Sprite Img1 = new Sprite();
	Sprite Img2 = new Sprite();
	Sprite Img3 = new Sprite();
	Sprite Img4 = new Sprite();
	Sprite Img5 = new Sprite();
	Body groundBody;
	Body groundBody2;
	boolean jungleFound = false;
	int eTimer = 2000;
	int growthT;
	int awsr = 0;
	double vineRandom = Math.random();
	boolean awd = false;
	boolean doorOpen = false;
	boolean energyUse = false;
	boolean rectOn = false;
	boolean eOnWire = false;
	float mj = 0;
	int awdc = 0;
	int signalUseSwitchTimer = 0;
	int awdct = 0;
	int machineOutputT = 0;
	int mjCap = 0;
	int wireInputD = 0;
	float pipeAngle = -1;
	float rotateAmount = 0;
	float pipeAngle2 = -1;
	float pipeAngle3 = -1;
	float pipeAngle4 = -1;
	int ironPipeT = 0;
	int leverTimer = 20;
	int iType = 0;
	int pipeUse = 0;
	int fuel = 0;
	int cookingTimer2 = 10000;
	boolean bottomTypeCheck = false;
	int transferT = 0;
	int doorT = 0;
	int parts = 0;
	int _x;
	int liquidDestroyTimer = 0;
	boolean imageFlip = false;
	int pictureTransitionTimer = 0;
	static int lightCreated2 = 0;
	int lightCreated = 0;
	int liquidMoveTimer = 0;
	int _y;
	int _iy;
	boolean treeCreated = false;
	int found = 0;
	static final int LAVA_RANGE_DECREASE = 2;
	int _ix;
	int _type;
	int XOFFSET = 8;
	int block = 1;
	int pistonAngle = 0;
	int sourceD = 0;
	int _id;
	int _jd;
	int inventoryTimer = 0;
	int inventoryOn = 0;
	float directionChosen = (float) (Math.random() * 100);
	int lightMade = 0;
	int liquidPlaceTimerB = 0;
	int liquidPlaceTimerL = 0;
	int liquidPlaceTimerR = 0;
	static final int BLOCKSIZE = 70;
	int liquidTimer = 0;
	static final int LAVARANGE = 10;
	int range = LAVARANGE + 1;
	static final int LAVA_VISCOCITY = 100;
	int imgrt = 0;
	int iActive = 0;
	int cookingTimer = 10000;
	int breakTimer = 0;
	int wireUse = 0;
	int ironPipeSet = 0;
	float rectx;
	float recty;
	float rectw;
	float rectl;
	int eOnD = 0;
	int eOnChange = 20;
	boolean cooking = false;
	boolean cookCheck = false;
	boolean eOn = false;
	boolean signalUsed = false;
	float shadowAlpha = 1;
	ArrayList<Integer> inventoryCounts;
	Inventory inventory;
	BodyDef groundBodyDef = new BodyDef();
	BodyDef groundBodyDef2 = new BodyDef();

	// ShapeRenderer sr;
	Grid(SpriteBatch batch, int x, int y, int type, int id, int jd, int _sd, int _range, int _tGrowth, ArrayList<Integer> inventoryCounts2, float pistonAngle, int awdc, boolean pistonOut, boolean imageFlip, boolean eOn, boolean awd,
			int eOnD, int eOnChange, boolean signalUsed, int mj, int cookingTimer, int cookingTimer2, int parts, int fuel, int awsr, int awdct) {
		this.batch = batch;
		this.mj = mj;
		this.awsr = awsr;
		this.awdct = awdct;
		this.signalUsed = signalUsed;
		this.cookingTimer = cookingTimer;
		this.cookingTimer2 = cookingTimer2;
		this.parts = parts;
		this.fuel = fuel;
		this.pistonAngle = (int) pistonAngle;
		this.eOn = eOn;
		this.awd = awd;
		this.eOnD = eOnD;
		this.eOnChange = eOnChange;
		explosions = new ArrayList<Explosion>();
		_id = id;
		_jd = jd;
		// batch = new SpriteBatch();
		this.pistonOut = pistonOut;
		this.imageFlip = imageFlip;
		_iy = y;
		_type = type;
		sourceD = _sd;
		this.awdc = awdc;
		range = _range;
		growthT = _tGrowth;
		if (inventoryCounts2 != null) {
			inventoryCounts = inventoryCounts2;
		}
	}

	boolean rightStop(float x, float y, float width, float height) {
		if (block == 1) {
			if (x >= _x - width - XOFFSET && x <= _x - width + (BLOCKSIZE / 2)) {
				if (y >= _y - height && y <= _y + BLOCKSIZE) {
					return true;
				}
			}
		}
		return false;
	}

	boolean leftStop(float x, float y, float width, float height) {
		if (block == 1) {
			if (x >= _x + (BLOCKSIZE / 2) && x <= _x + BLOCKSIZE + XOFFSET) {
				if (y >= _y - height && y <= _y + BLOCKSIZE) {
					return true;
				}
			}
		}
		return false;
	}

	boolean sightBlocked(float x, float y) {

		if (Visible(_x, _y, 0, 200, 570)) {
			ArrayList<Integer> visibles = new ArrayList<Integer>(Arrays.asList(0, 7, 8, 10, 11, 13, 14, 21, 22, 25, 29));
			for (int i = 0; i < visibles.size(); i++) {
				if (block==0||_type == visibles.get(i)) {
					return false;
				}
			}
			if (x >= _x && x <= _x + BLOCKSIZE) {
				if (y >= _y && y <= _y + BLOCKSIZE) {
					return true;
				}
			}
			return false;
		}
		return false;
	}
	int downStopHeight(float x, float y, float width, float height)
	{
		if(block == 1)
		{
			if (x >= _x - width && x <= _x + BLOCKSIZE) {
				if (y >= _y + (BLOCKSIZE)-12 && y <= _y + BLOCKSIZE + 4) {
					return _y+BLOCKSIZE+3;
				}
			}
		}
		return 0;
	}
	boolean downStop(float x, float y, float width, float height) {
		if (block == 1) {
			if (x >= _x - width && x <= _x + BLOCKSIZE) {
				if (y >= _y + (BLOCKSIZE)-12 && y <= _y + BLOCKSIZE + 4) {
					return true;
				}
			}
		}
		return false;
	}

	boolean upStop(float x, float y, float width, float height) {
		if (block == 1) {
			if (x >= _x - width && x <= _x + BLOCKSIZE) {
				if (y + height <= _y + 15 && y + height >= _y - 5) {
					return true;
				}
			}
		}
		return false;
	}

	void hoeManager() {
		if (HUD.typeN == InventoryItem.BLOCK_AMOUNT + 15) {
			if (Button.button(_x, _y, BLOCKSIZE, BLOCKSIZE,
					Gdx.input.isButtonPressed(Input.Buttons.RIGHT))) {
				_type = 30;
			}
		}
		if (_type == 30 && GridBased.getBlock(_id - 1, _jd) != 0) {
			_type = 2;
		}
	}

	void destroy(float x, float y, float width, float height, boolean active,
			boolean protagonist) {

		if (_type != 0 && _type != 5 && _type != 8 || _type == 5
				&& HUD.typeN == InventoryItem.BLOCK_AMOUNT + 1 || _type == 8
				&& HUD.typeN == InventoryItem.BLOCK_AMOUNT + 1) {

			if (active && x > _x && x < _x + BLOCKSIZE && y > _y
					&& y < _y + BLOCKSIZE) {
				if (protagonist == false || GridBased.inventoryOn() == 0) {
					if (HUD.typeN == InventoryItem.BLOCK_AMOUNT + 1
							&& _type == 5 && protagonist
							|| HUD.typeN == InventoryItem.BLOCK_AMOUNT + 1
							&& _type == 8 && protagonist) {
						HUD.inventory.item(InventoryItem.BLOCK_AMOUNT)
								.remove(1);
					}
					if (HUD.inventoryFull == false
							|| HUD.inventory.item(_type - 1).count > 0) {
						HUD.inventory.item(_type - 1).add(1);
						if (_type == 34 && imgrt == 408) {
							HUD.inventory.item(_type - 1).add(1);
							HUD.inventory.item(InventoryItem.BLOCK_AMOUNT + 20)
									.add(2);
						}
						if (_type == 35 && imgrt == 508) {
							HUD.inventory.item(_type - 1).add(1);
							HUD.inventory.item(InventoryItem.BLOCK_AMOUNT + 21)
									.add(2);
						}
					} else {
						ItemDrop id;
						id = new ItemDrop(HUD.inventory2.item(_type - 1).img,
								_type, 1, _x + BLOCKSIZE / 2, _y + BLOCKSIZE
										/ 2);
						MyGdxGame.itemDrops.add(id);
						if (_type == 34 && imgrt == 408) {
							ItemDrop id2;
							id2 = new ItemDrop(
									HUD.inventory2.item(_type - 1).img, _type,
									1, _x + BLOCKSIZE / 2, _y + BLOCKSIZE / 2);
							MyGdxGame.itemDrops.add(id2);
							ItemDrop id3;
							id3 = new ItemDrop(
									HUD.inventory2
											.item(InventoryItem.BLOCK_AMOUNT + 20).img,
									InventoryItem.BLOCK_AMOUNT + 21, 1, _x
											+ BLOCKSIZE / 2, _y + BLOCKSIZE / 2);
							MyGdxGame.itemDrops.add(id3);
						}
						if (_type == 34 && imgrt == 408) {
							ItemDrop id2;
							id2 = new ItemDrop(
									HUD.inventory2.item(_type - 1).img, _type,
									1, _x + BLOCKSIZE / 2, _y + BLOCKSIZE / 2);
							MyGdxGame.itemDrops.add(id2);
							ItemDrop id3;
							id3 = new ItemDrop(
									HUD.inventory2
											.item(InventoryItem.BLOCK_AMOUNT + 21).img,
									InventoryItem.BLOCK_AMOUNT + 21, 1, _x
											+ BLOCKSIZE / 2, _y + BLOCKSIZE / 2);
							MyGdxGame.itemDrops.add(id3);
						}
					}
					snowTimer = 600;
					_type = 0;
					liquidTimer = 0;
					range = LAVARANGE + 1;
					sourceD = 0;
					liquidPlaceTimerB = 0;
					liquidPlaceTimerL = 0;
					liquidPlaceTimerR = 0;

					HUD.refresh();
				}
			}
		}
	}

	void destroyID() {
		_type = 0;
		liquidTimer = 0;
		range = LAVARANGE + 1;
		sourceD = 0;
		liquidPlaceTimerB = 0;
		liquidPlaceTimerL = 0;
		liquidPlaceTimerR = 0;
	}

	void createID(int type) {
		_type = type;
	}

	void create(float x, float y, boolean active, int type, float xRic,
			float yRic, float wRic, float hRic, boolean ricFalse,
			boolean protagonist) {

		if (block == 0 && _type != 13 && _type != 21 && _type != 22
				&& _type != 36 && _type != 37 && _type != 38 && _type != 6
				&& _type != 31 && _type != 7) {
			if (active && x > _x && x < _x + BLOCKSIZE && y > _y
					&& y < _y + BLOCKSIZE) {
				if (protagonist == false
						|| GridBased.rightType(_id - 1, _jd - 1) != 0
						|| GridBased.leftType(_id - 1, _jd - 1) != 0
						|| GridBased.belowType(_id - 1, _jd - 1) != 0
						|| GridBased.aboveType(_id - 1, _jd - 1) != 0) {
					if (_x + BLOCKSIZE < xRic || _x > xRic + wRic || ricFalse
							|| _y + BLOCKSIZE < yRic || _y > yRic + hRic) {
						if (protagonist == false
								|| GridBased.inventoryOn() == 0) {
							if (protagonist == false
									|| XYControl.Visible(_x, _y, 0, -870, -390)) {
								_type = type;
								if (protagonist) {
									this.protagonist = true;
								} else {
									this.protagonist = false;
								}
							}
							if (protagonist
									&& XYControl.Visible(_x, _y, 0, -870, -390)) {
								Protagonist.remove();
							}
							if (protagonist && _type == 5 || protagonist
									&& _type == 8) {
								HUD.inventory.item(InventoryItem.BLOCK_AMOUNT)
										.add(1);
							}
						}
					}
				}
				HUD.refresh();
			}
		}
	}

	void updateCords(float x) {
		_x = (int) x;
		_y = Math.round(_iy + DirectionControl.currentY);
	}

	void draw(float x, float groundJd) {
		shadowAlpha = 0;
		lightAlpha = 0;
		_x = (int) x;
		_y = Math.round(_iy + DirectionControl.currentY);
		if(_type == 55||_type==56||_type==54)
		{
			block = 0;
		}
		if (found == 0 && Visible(_x, _y, 0, 200, 570)) {
			found = 1;
			if (_type == 10) {
				sapling();
			}

		}
		if (found == 0 && Visible(_x, _y, 0, 200, 570)) {
			found = 1;
			if (_type == 10) {
				sapling();
			}

		}
		if(jungleFound == false && _type >= 54 && _type <= 56 && Visible(_x,_y,0,50,50))
		{
			jungleFound = true;
		}
		if (_type == 33) {
			sapling2();

		}
		if (_type == 34) {
			tomato();
		}
		if (_type == 35) {
			corn();
		}
		if (Visible(_x, _y, 0, 500, 200)||visibleOverride) {
			if(_x + BLOCKSIZE > 1000 && _x < 1000 && _y + BLOCKSIZE > 500 && _y < 500)
			{
				LightSystem.centerJD = _jd-1;
				LightSystem.centerID = _id-1;
			}
			if (visibleOverride||_type != 0 && _type != 5 && _type != 7 && _type != 8
					&& _type != 11 && _type != 13
					&& GridBased.surroundCheck(_id - 1, _jd - 1) && _type != 14) {
				shadowAlpha = 0;
				if (_type == 1) {
					cobbleStone();
				}
				if (_type == 2) {
					dirt();
				}
				if (_type == 3) {
					dirtGrass();
				}
				if (_type == 6) {
					log();
				}
				if (_type == 9) {
					sand();
				}
				if (_type == 12) {
					chest();
				}
				if (_type == 15) {
					furnace();
					visibleOverride = true;
				}
				if (_type == 16) {
					coal();
				}
				if (_type == 17) {
					iron();
				}
				if (_type == 18) {
					copper();
				}
				if (_type == 19) {
					gold();
				}
				if (_type == 20) {
					macerator();
					visibleOverride = true;
				}
				if (_type == 23) {
					batBox();
					visibleOverride = true;
				}
				if (_type == 24) {
					generator();
					visibleOverride = true;
				}
				if (_type == 26) {
					plank();
				}
				if (_type == 27) {
					door1();
				}
				if (_type == 28) {
					door2();
				}
				if (_type == 30) {
					hoedDirt();
				}
				if (_type == 31) {
					rubberLog();
				}
				if (_type == 32) {
					eFurnace();
					visibleOverride = true;
				}
				if (_type == 39) {
					piston();
					visibleOverride = true;
				}
				if (_type == 41) {
					lamp();
					visibleOverride = true;
				}
				if(_type == 43)
				{
					snow();
				}
				if(_type == 44)
				{
					ice();
				}
				if(_type == 45)
				{
					stoneBrick();
				}
				if(_type == 49)
				{
					rubberBlock();
				}
				if(_type == 58)
				{
					sandStone();
				}
			} else {
				shadowAlpha = 1;
			}
			if (_type == 0) {
				air();
				shadowAlpha = 0;
			}
			if (_type == 4) {

			}
			if (_type == 5) {
				lava2();
				shadowAlpha = 0;
			}
			if (_type == 7) {
				leaf();
				shadowAlpha = 0;
			}
			if (_type == 8) {
				water();
				shadowAlpha = 0;
			}
			if (_type == 10) {
				sapling();
				shadowAlpha = 0;
			}
			if (_type == 11) {
				torch();
				shadowAlpha = 0;
			}
			if (_type == 13) {
				fire();
				visibleOverride = true;
				shadowAlpha = 0;
			}
			if (_type == 14) {
				glass();
				shadowAlpha = 0;
			}
			if (_type == 21) {
				stonePipe();
				shadowAlpha = 0;
			}
			if (_type == 22) {
				ironPipe();
				visibleOverride = true;
				shadowAlpha = 0;
			}
			if (_type == 25) {
				copperWire();
				shadowAlpha = 0;
			}
			if (_type == 29) {
				ladder();
				shadowAlpha = 0;
			}
			if (_type == 36) {
				wire();
				shadowAlpha = 0;
			}
			if (_type == 37) {
				lever();
				visibleOverride = true;
				shadowAlpha = 0;
			}
			if (_type == 38) {
				advancedWire();
				visibleOverride = true;
				shadowAlpha = 0;
			}
			if (_type == 40) {
				pistonHead();
				visibleOverride = true;
				shadowAlpha = 0;
			}
			if (_type == 42) {
				c4();
				shadowAlpha = 0;
			}
			if(_type == 46){
				button();
				shadowAlpha = 0;
			}
			if(_type == 47)
			{
				pressurePad();
				shadowAlpha = 0;
			}
			if(_type == 48)
			{
				signalConverter();
				visibleOverride = true;
				shadowAlpha = 0;
			}
			if(_type == 50)
			{
				solidWire();
				visibleOverride = true;
				shadowAlpha = 0;
			}
			if(_type == 51)
			{
				grass();
				shadowAlpha = 0;
			}
			if(_type == 52)
			{
				flowerOne();
				shadowAlpha = 0;
			}
			if(_type == 53)
			{
				flowerTwo();
				shadowAlpha = 0;
			}
			if(_type == 54)
			{
				vine();
				shadowAlpha = 0;
			}
			if(_type == 55)
			{
				jungleLeaf();
				shadowAlpha = 0;
			}
			if(_type == 56)
			{
				jungleLog();
				shadowAlpha = 0;
			}
			if(_type == 57)
			{
				jungleSapling();
				shadowAlpha = 0;
			}
			if(_type == 59)
			{
				desertBush();
			}
			if(_type == 60)
			{
				cactus();
			}
			if (_jd < groundJd) {
				shadowAlpha = 1;
			}
			if(LightSystem.night)
			{
				shadowAlpha = 1;
			}
		}
		if(randomVisibleOverride > .2f)
		{
			visibleOverride = false;
		}
		explosion(150,50);
		damaged = false;
	}
	void setLightAlpha(float alpha)
	{
		if(alpha < .3f)
		{
			alpha  = .4f;
		}
		lightAlpha = (1/(alpha +.01f))/25;
	}
	
	void drawShadow(ShapeRenderer _sr, SpriteBatch batch, Sprite sprite, Sprite sprite2) {
		if (shadowAlpha > 0) {
			if (shadowAlpha >= 1) {
				_sr.begin(ShapeType.Filled);
				_sr.setColor(0, 0, 0, .5f);
				_sr.rect(_x, _y, BLOCKSIZE, BLOCKSIZE);
				_sr.end();
			} else {
				if ((Math.random()) < .02d) {
					shadowAlphaRandom = (float) ((Math.random() - .5d) / 12);
				}
				if (sprite != null) {
					sprite.setPosition(_x, _y);
					sprite.setAlpha(shadowAlpha + shadowAlphaRandom);
					sprite.setSize(BLOCKSIZE, BLOCKSIZE);
					sprite.draw(batch);
				}
			}
		} else {
			if (lightAlpha > 0)
			{
				if ((Math.random()) < .02d) {
					shadowAlphaRandom = (float) ((Math.random() - .5d) / 12);
				}
				if (sprite2 != null) {
					sprite2.setPosition(_x, _y);
					sprite2.setAlpha(lightAlpha + (shadowAlphaRandom/12));
					sprite2.setSize(BLOCKSIZE, BLOCKSIZE);
					sprite2.draw(batch);
				}
			}
		}
	}

	ArrayList<Integer> inventoryArrayConverter() {
		if (inventory != null) {
			ArrayList<Integer> inventoryItems = new ArrayList<Integer>();
			for (int i = 0; i < inventory.size(); i++) {
				inventoryItems.add(inventory.item(i).count);
			}
			return inventoryItems;
		}
		return null;

	}

	void createLight(int max) {
		/*
		 * int yOffSet = 1; if(Visible(_x,_y,0,0,0) ) { if (block !=0 &&
		 * GridBased.surroundCheck(_id - 1, _jd - 1) == false&& _type != 7 &&
		 * _type != 14) { groundBodyDef.position.set((_x/5) + 6.92f
		 * ,(float)((_y/5) + (7.2f/yOffSet))); groundBodyDef.type =
		 * BodyType.StaticBody; groundBody2
		 * =LightManager.world.createBody(groundBodyDef); PolygonShape
		 * groundBox2 = new PolygonShape(); groundBox2.setAsBox(10f, 10f
		 * /yOffSet); groundBody2.createFixture(groundBox2,0.0f); lightCreated2
		 * = 1; lightCreated = 1; groundBox2.dispose(); }
		 * 
		 * }
		 */
	}

	void destroyLight() {
		if (lightCreated == 1) {
			lightCreated = 0;
			// LightManager.world.destroyBody(groundBody2);

		}
	}

	void standardBlock(String string, int type, int breakLimit, int breakType) {
		if (imgrt != type) {
			Img1 = new Sprite(new Texture(string));
			imgrt = type;
		}
		if (_type == 39 || _type == 712 || _type == 40 || _type == 42 || _type == 46) {
			Img1.setOriginCenter();
			Img1.setRotation(pistonAngle);
		}
		if (breakLimit > -1) {
			breakManager(breakLimit, breakType);
		}
		block = 1;
		if(_type != 51)
		{
			Img1.setSize(BLOCKSIZE, BLOCKSIZE);
		}else
		{
			Img1.setSize(BLOCKSIZE, randomGrassSize);
		}
		Img1.setPosition(_x, _y);
		if(_type == 43)
		{
			Img1.setPosition(_x,_y-12);
		}
		if(_type >= 51 && _type <= 53)
		{
			Img1.setPosition(_x,_y-3);
		}
		
		Img1.draw(batch);
		
	}
	void cactus()
	{
		standardBlock("cactusT.png",60,60,3);
		block = 0;
		if(GridBased.getBlock(_id-1, _jd-2)==0)
		{
			_type = 0;
		}
	}
	void desertBush()
	{
		standardBlock("desertShrub.png",59,40,3);
		block = 0;
		if(GridBased.getBlock(_id-1, _jd-2)==0)
		{
			_type = 0;
		}
	}
	void grass()
	{
		if(snowLayerHere)
		{
			standardBlock("grassSnow.png",9923,10,3);
		}
		else
		{
			standardBlock("grass.png",51,10,3);
		}
		block = 0;
		if(GridBased.getBlock(_id-1, _jd-2)==0)
		{
			_type = 0;
		}
	}
	void sandStone()
	{
		standardBlock("sandStone.png",58,300,3);
	}
	void flowerOne()
	{
		if(snowLayerHere)
		{
			standardBlock("flowerSnow1.png",9924,10,3);
		}
		else
		{
			standardBlock("flower1.png",52,10,2);
		}
		block = 0;
		if(GridBased.getBlock(_id-1, _jd-2)==0)
		{
			_type = 0;
		}
	}
	void flowerTwo()
	{
		if(snowLayerHere)
		{
			standardBlock("flowerSnow2.png",9925,10,3);
		}
		else
		{
		standardBlock("flower2.png",53,10,3);
		}
		block = 0;
		if(GridBased.getBlock(_id-1, _jd-2)==0)
		{
			_type = 0;
		}
	}
	void rubberBlock()
	{
		standardBlock("rubberBlock.png",49,300,1);
	}
	void pressurePad()
	{
		buttonSignalTimer--;
		if (Protagonist._x + Protagonist.WIDTH > _x && Protagonist._x < _x + BLOCKSIZE && Protagonist._y > _y && Protagonist._y < _y + 10) {
				buttonSignalTimer = 60;
		}
		if(buttonSignalTimer > 0)
		{
			eOn = true;
			standardBlock("pressurePad2.png",46897,10,3);
		}
		else
		{
			eOn = false;
			standardBlock("pressurePad1.png",46,10,3);
		}
		block = 0;
	}
	void stoneBrick()
	{
		standardBlock("stoneBrick.png",45,450,1);
	}
	void tomato() {
		if (growthT < 4998) {
			plantManager("tomatoSeed.png", 34, 50, 2, 5000, 10,Arrays.asList(30), 4, 3);
		} else if (growthT < 9998) {
			plantManager("tomatoPlant1.png", 402, 50, 2, 10000, 10,Arrays.asList(30), 4, 3);
		} else {
			standardBlock("tomatoPlant2.png", 408, 100, 2);
		}

	}

	void sapling2() {
		plantManager("sapling.png", 33, 50, 2, 3000, 10, Arrays.asList(2, 3),10, 3);
	}

	void corn() {
		if (growthT < 4998) {
			plantManager("tomatoSeed.png", 35, 50, 2, 5000, 10,Arrays.asList(30), 4, 3);
		} else if (growthT < 9998) {
			plantManager("cornPlant1.png", 502, 50, 2, 10000, 10,Arrays.asList(30), 4, 3);
		} else {
			standardBlock("cornPlant2.png", 508, 100, 2);
		}
	}

	void plantManager(String string, int type, int breakLimit, int breakType,
			int growthTime, int fullGrowth, List<Integer> belowBlocks,
			int waterRangeX, int waterRangeY) {
		if (XYControl.Visible(_x, _y, 1, 0, 20)) {
			waterOn = false;
			bottomTypeCheck = false;
		}
		standardBlock(string, type, breakLimit, breakType);
		ArrayList belowBlock = new ArrayList<Integer>(belowBlocks);
		if (XYControl.Visible(_x, _y, 1, 0, 20)) {
			if (waterRangeX != 0) {
				for (int i = 0; i < waterRangeX; i++) {
					for (int j = 0; j < waterRangeY; j++) {
						if (GridBased.belowType(_id + (-i - 1), _jd - j) == 8
								|| GridBased.belowType(_id + (i - 1), _jd - j) == 8) {
							waterOn = true;
						}
					}
				}
			}
			block = 0;
		}

		if (waterRangeX == 0 && waterRangeY == 0) {
			waterOn = true;
		}
		if (XYControl.Visible(_x, _y, 1, 600, 20)) {
			if (waterOn) {
				for (int i = 0; i < belowBlock.size(); i++) {
					if (GridBased.belowType(_id - 1, _jd - 1) == (Integer) (belowBlock
							.get(i))) {
						bottomTypeCheck = true;
					}
				}
			}
		}
		if (bottomTypeCheck) {
			growthT++;
		}
		if (growthT > growthTime) {
			_type = fullGrowth;
		}
	}
	boolean basicSignalFinder() {
		if (GridBased.geteOn(_id - 2, _jd - 1) == true) {
			return true;
		}
		if (GridBased.geteOn(_id, _jd - 1) == true) {
			return true;
		}
		if (GridBased.geteOn(_id, _jd) == true) {
			return true;
		}
		if (GridBased.geteOn(_id - 2, _jd) == true) {
			return true;
		}
		if (GridBased.geteOn(_id - 1, _jd - 2) == true) {
			return true;
		}
		return false;
	}
	void breakManager(int breakLimit, int breakType) {
		this.breakLimit = breakLimit;
		int breakDivide = 1;
		if (HUD.typeN == InventoryItem.BLOCK_AMOUNT + 2) {
			if (breakType == 1) {
				breakDivide = 5;
			}
		} else if (HUD.typeN == InventoryItem.BLOCK_AMOUNT + 5) {
			if (breakType == 1) {
				breakDivide = 10;
			}
		} else if (HUD.typeN == InventoryItem.BLOCK_AMOUNT + 8) {
			if (breakType == 1) {
				breakDivide = 15;
			}
		} else if (HUD.typeN == InventoryItem.BLOCK_AMOUNT + 3) {
			if (breakType == 2) {
				breakDivide = 3;
			}
		} else if (HUD.typeN == InventoryItem.BLOCK_AMOUNT + 6) {
			if (breakType == 2) {
				breakDivide = 6;
			}
		} else if (HUD.typeN == InventoryItem.BLOCK_AMOUNT + 9) {
			if (breakType == 2) {
				breakDivide = 9;
			}
		} else if (HUD.typeN == InventoryItem.BLOCK_AMOUNT + 4) {
			if (breakType == 3) {
				breakDivide = 3;
			}
		} else if (HUD.typeN == InventoryItem.BLOCK_AMOUNT + 7) {
			if (breakType == 3) {
				breakDivide = 6;
			}
		} else if (HUD.typeN == InventoryItem.BLOCK_AMOUNT + 10) {
			if (breakType == 3) {
				breakDivide = 9;
			}
		} else if (_type == 7 && HUD.typeN == InventoryItem.BLOCK_AMOUNT + 14) {
			breakDivide = 7;
		} else {
			breakDivide = 1;
		}
		if (Button.button(_x, _y, BLOCKSIZE, BLOCKSIZE,Gdx.input.isButtonPressed(Input.Buttons.LEFT))&& _type != 27&& _type != 28|| Button.button(_x, _y, BLOCKSIZE, BLOCKSIZE * 2,Gdx.input.isButtonPressed(Input.Buttons.LEFT))&& _type == 27|| Button.button(_x, _y - BLOCKSIZE, BLOCKSIZE, BLOCKSIZE * 2,Gdx.input.isButtonPressed(Input.Buttons.LEFT))&& _type == 28) {
			if (XYControl.Visible(_x, _y, 0, -870, -390)&&HUD.inventoryOn == 0 && GridBased.inventoryOn()==0) {
				breakTimer += breakDivide;
			} else if (breakTimer > 0) {
				breakTimer -= .25f;
			}
		} else if (breakTimer > 0) {
			breakTimer -= .25f;
		}
		if (breakTimer > breakLimit || Gdx.input.isKeyPressed(Input.Keys.R)) {
			destroy(Gdx.input.getX(), (1000 - Gdx.input.getY()), 0, 0,
			Gdx.input.isButtonPressed(Input.Buttons.LEFT), true);
			breakTimer = 0;
		}
		if (breakTimer < breakLimit * .2) {
			// BreakImg.setNull();
		} else if (breakTimer < breakLimit * .4) {
			BreakImg = new Sprite(new Texture("blockBreaking1.png"));
		} else if (breakTimer < breakLimit * .6) {
			BreakImg = new Sprite(new Texture("blockBreaking2.png"));
		} else if (breakLimit >= breakLimit * .8) {
			BreakImg = new Sprite(new Texture("blockBreaking3.png"));
		}
		BreakImg.setSize(BLOCKSIZE, BLOCKSIZE);
		BreakImg.setPosition(_x, _y);
		if (breakTimer >= breakLimit * .2) {
			
			BreakImg.draw(batch);
			
		}
	}

	void standardBlock2(String string, String string2, int switchTimer,
			int type, int breakLimit, int breakType) {
		pictureTransitionTimer++;
		if (imgrt != type) {
			Img1 = new Sprite(new Texture(string));
			Img2 = new Sprite(new Texture(string2));
			imgrt = type;
		}
		block = 1;
		Img1.setSize(BLOCKSIZE, BLOCKSIZE);
		Img1.setPosition(_x, _y);
		Img2.setSize(BLOCKSIZE, BLOCKSIZE);
		Img2.setPosition(_x, _y);
		
		if (pictureTransitionTimer < switchTimer / 2) {
			Img1.draw(batch);
		} else {
			Img2.draw(batch);
		}
		
		if (pictureTransitionTimer > switchTimer) {
			pictureTransitionTimer = 0;
		}

		if (breakLimit > -1) {
			breakManager(breakLimit, breakType);
		}
	}

	void door1() {
		if (imgrt != _type) {
			GridBased.create(_x + 15, _y + BLOCKSIZE + 5, true, 28, 0, 0, 0, 0,
					true, false);
		}
		doorT--;
		if (doorT < 0
				&& Button.button(_x, _y, BLOCKSIZE, BLOCKSIZE * 2,
						Gdx.input.isButtonPressed(Input.Buttons.LEFT))
				&& doorOpen == false) {
			doorOpen = true;
			doorT = 20;
		}
		if (doorT < 0
				&& Button.button(_x, _y, BLOCKSIZE, BLOCKSIZE * 2,
						Gdx.input.isButtonPressed(Input.Buttons.LEFT))
				&& doorOpen == true) {
			doorOpen = false;
			doorT = 20;
		}
		if (doorOpen == true) {

			standardBlock("door2T2.png", 27, 250, 3);
			block = 0;
		} else {

			standardBlock("door1.png", 90, 250, 3);
			block = 1;
		}
	}

	boolean pistonCheck(int id, int jd) {
		ArrayList<Integer> notMovable = new ArrayList<Integer>(Arrays.asList(12, 15, 20, 23, 24, 27, 28, 32, 40,13,21,22,25,29,36,37,38,40,42,46));
		for (int i = 0; i < notMovable.size(); i++) {
			if (GridBased.aboveType(id, jd - 1) == notMovable.get(i)) {
				return false;
			}
		}
		if (GridBased.getBlock(id, jd) == 0) {
			blockAttached = 0;
			return true;
		}
		
		if(GridBased.aboveType(id, jd-1)==49)
		{
			blockAttached = 2;
		}
		else
		{
			blockAttached = 1;
		}
		return true;
	}
	void snowLayerCreate(boolean snowing)
	{
		snowTimer--;
		if(snowing&&_type == 0)
		{
			if(MyGdxGame.snowTimer > snowCreateDestroyTimer && snowTimer < 0)
			{
				if(GridBased.getBlock(_id-1,_jd-2)==1)
				{
					_type = 43;
				}
			}	
		}
		else if (snowing == false && _type ==43)
		{
			if(MyGdxGame.notSnowingTimer > snowCreateDestroyTimer)
			{
				_type = 0;
			}
		}
		if(snowing && _type == 0 && MyGdxGame.snowTimer > snowCreateDestroyTimer && GridBased.belowType(_id-1, _jd-1)==8)
		{
			GridBased.changeBlock(_id-1, _jd-2, 44);
		}
		else if (_type >= 51&&_type<=53 &&MyGdxGame.snowTimer>snowCreateDestroyTimer)
		{
			Img2= new Sprite(new Texture("snow.png"));
			Img2.setSize(BLOCKSIZE, BLOCKSIZE);
			Img2.setPosition(_x, _y-12);
			
			Img2.draw(batch);
			
			snowLayerHere = true;
		}
		else
		{
			snowLayerHere = false;
		}
	}
	void ice()
	{
		standardBlock("ice.png",44,60,3);
		if(MyGdxGame.weatherMode != 2)
		{
			if(MyGdxGame.notSnowingTimer > snowCreateDestroyTimer)
			{
				_type = 8;
			}
		}
	}
	void pistonHead() {
		standardBlock("pistonHead.png", 40, 100, 3);
	}
	void snow()
	{
		standardBlock("snow.png",43,10,2);
		block = 0;
		if(GridBased.getBlock(_id-1,_jd-2)==0)
		{
			_type = 0;
		}
	}
	void lamp() {
		standardBlock("lamp.png", 41, 100, 3);
		if (basicSignalFinder()) {
			light(_x, _y, BLOCKSIZE, BLOCKSIZE, 100, 900);
		}
	}
	void c4() {
		leverTimer--;
		standardBlock("c4.png", 42, 10, 1);
		if (Button.button(_x, _y, BLOCKSIZE, BLOCKSIZE,
				Gdx.input.isButtonPressed(Input.Buttons.RIGHT))
				&& pistonOut == false) {
			if (leverTimer < 0) {
				pistonAngle += 90;
				if (pistonAngle > 358) {
					pistonAngle = 0;
				}
				leverTimer = 20;
			}
		}
		if (GridBased.getBlock(_id - 1, _jd - 2) == 0 && pistonAngle == 0) {
			pistonAngle += 90;
		}
		if (GridBased.getBlock(_id, _jd - 1) == 0 && pistonAngle == 90) {
			pistonAngle += 90;
		}
		if (GridBased.getBlock(_id - 1, _jd) == 0 && pistonAngle == 180) {
			pistonAngle += 90;
		}
		if (GridBased.getBlock(_id - 2, _jd - 1) == 0 && pistonAngle == 270) {
			pistonAngle += 90;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && eTimer > 300) {
			eTimer = 0;
			_type = 0;
		}
	}

	void explosion(int magnitude, int projectileVelocity) {
		eTimer++;
		if (explosions.size()<magnitude && eTimer < 10) {
			int angle = 0;
			for (int i = 0; i < magnitude; i++) {
				angle += 360 / magnitude;
				Explosion explosion;
				explosion = new Explosion(_x + BLOCKSIZE / 2,_y + BLOCKSIZE / 2,(float) (Math.cos(angle * (180 / Math.PI)) * projectileVelocity),(float) (Math.sin(angle * (180 / Math.PI)) * projectileVelocity));
				explosions.add(explosion);
				//BombEffect bombEffect;
				//bombEffect = new BombEffect(_x + BLOCKSIZE / 2, _y + BLOCKSIZE/ 2);
				//MyGdxGame.bombEffects.add(bombEffect);
			}
		}
		if (eTimer < 100) {
			for (int i = 0; i < magnitude; i++) {
				explosions.get(i).draw(5);
			}

		}
		if (eTimer > 100) {
			explosions.clear();
		}
	}

	void piston() {
		leverTimer--;
		boolean moveAble = false;
		if (Button.button(_x, _y, BLOCKSIZE, BLOCKSIZE,
				Gdx.input.isButtonPressed(Input.Buttons.RIGHT))
				&& pistonOut == false) {
			if (leverTimer < 0) {
				pistonAngle += 90;
				if (pistonAngle > 358) {
					pistonAngle = 0;
				}
				leverTimer = 20;
			}
		}
		if (pistonAngle <= 3) {
			if (GridBased.getBlock(_id - 1, _jd + 1) == 0) {
				if (pistonCheck(_id - 1, _jd)) {
					moveAble = true;
				}
			}
		} else if (pistonAngle <= 95) {
			if (GridBased.getBlock(_id -3, _jd - 1) == 0) {
				if (pistonCheck(_id-2, _jd - 1)) {
					moveAble = true;
				}
			}
		} else if (pistonAngle <= 186) {
			if (GridBased.getBlock(_id - 1, _jd - 3) == 0) {
				if (pistonCheck(_id - 1, _jd - 2)) {
					moveAble = true;
				}
			}
		} else if (pistonAngle <= 279) {
			if (GridBased.getBlock(_id + 1, _jd - 1) == 0) {
				if (pistonCheck(_id, _jd - 1)) {
					moveAble = true;
				}
			}
		}
		int pistonPushSpeed = 10;
		if (basicSignalFinder() && moveAble && pistonOut == false) {
			pistonOut = true;
			if (pistonAngle <= 3) {
				GridBased.changeBlock(_id - 1, _jd + 1,GridBased.aboveType(_id - 1, _jd - 1));
				GridBased.changeBlock(_id - 1, _jd, 40);
				for(int i = 0; i < MyGdxGame.itemDrops.size(); i++)
				{
					MyGdxGame.itemDrops.get(i).pistonPush(_x, _y);
				}
				MyGdxGame.dc.pistonPush(_x,_y,blockAttached,pistonAngle,0,pistonPushSpeed);
				GridBased.setPistonAngle(_id - 1, _jd, pistonAngle);
			} else if (pistonAngle <= 96) {
				GridBased.changeBlock(_id - 3, _jd - 1,GridBased.leftType(_id - 1, _jd - 1));
				GridBased.changeBlock(_id - 2, _jd - 1, 40);
				for(int i = 0; i < MyGdxGame.itemDrops.size(); i++)
				{
					MyGdxGame.itemDrops.get(i).pistonPush(_x, _y);
				}
				MyGdxGame.dc.pistonPush(_x,_y, blockAttached, pistonAngle, -pistonPushSpeed,0);
				GridBased.setPistonAngle(_id - 2, _jd - 1, pistonAngle);
			} else if (pistonAngle <= 186) {
				GridBased.changeBlock(_id - 1, _jd - 3,
				GridBased.belowType(_id - 1, _jd - 1));
				GridBased.changeBlock(_id - 1, _jd - 2, 40);
				GridBased.setPistonAngle(_id - 1, _jd - 2, pistonAngle);
			} else if (pistonAngle <= 279) {
				GridBased.changeBlock(_id + 1, _jd - 1,
				GridBased.rightType(_id - 1, _jd - 1));
				GridBased.changeBlock(_id, _jd - 1, 40);
				for(int i = 0; i < MyGdxGame.itemDrops.size(); i++)
				{
					MyGdxGame.itemDrops.get(i).pistonPush(_x, _y);
				}
				MyGdxGame.dc.pistonPush(_x,_y, blockAttached, pistonAngle,pistonPushSpeed,0);
				GridBased.setPistonAngle(_id, _jd - 1, pistonAngle);
			}
		} else if (basicSignalFinder() == false) {
			if (pistonOut == true) {
				pistonOut = false;
				if (pistonAngle <= 3) {
					GridBased.changeBlock(_id - 1, _jd,
							GridBased.aboveType(_id - 1, _jd));
					GridBased.changeBlock(_id - 1, _jd + 1, 0);
				} else if (pistonAngle <= 96) {
					GridBased.changeBlock(_id - 2, _jd - 1,
							GridBased.leftType(_id - 2, _jd - 1));
					GridBased.changeBlock(_id - 3, _jd - 1, 0);
				} else if (pistonAngle <= 186) {
					GridBased.changeBlock(_id - 1, _jd - 2,
							GridBased.belowType(_id - 1, _jd - 2));
					GridBased.changeBlock(_id - 1, _jd - 3, 0);
				} else if (pistonAngle <= 279) {
					GridBased.changeBlock(_id, _jd - 1,
							GridBased.rightType(_id, _jd - 1));
					GridBased.changeBlock(_id + 1, _jd - 1, 0);
				}
			}
		}
		if (pistonOut == false) {
			standardBlock("piston.png", 39, 250, 3);
		} else {
			standardBlock("piston2.png", 712, 200, 3);
		}

	}

	void door2() {
		doorT--;
		if (doorOpen == false
				&& doorT < 0
				&& Button.button(_x, _y - BLOCKSIZE, BLOCKSIZE, BLOCKSIZE * 2,
						Gdx.input.isButtonPressed(Input.Buttons.LEFT))) {
			doorOpen = true;
			doorT = 20;
		}
		if (doorT < 0
				&& Button.button(_x, _y - BLOCKSIZE, BLOCKSIZE, BLOCKSIZE * 2,
						Gdx.input.isButtonPressed(Input.Buttons.LEFT))
				&& doorOpen == true) {

			doorOpen = false;
			doorT = 20;

		}
		if (doorOpen == true) {
			standardBlock("door2T.png", 28, 250, 3);
			block = 0;

		} else {
			standardBlock("doorItem.png", 90, 250, 3);

			block = 1;
		}
	}

	void ladder() {
		if (GridBased.getBlock(_id, _jd - 1) == 1) {
			standardBlock("ladder1.png", 29, 250, 1);
		} else if (GridBased.getBlock(_id - 2, _jd - 1) == 1) {
			standardBlock("ladder2.png", 70, 250, 1);
		}
		block = 0;
	}

	boolean inLadder(float x, float y, float width, float height) {
		if (_type == 29 && Visible(_x, _y, 0, 200, 200) ) {
			if (GridBased.getBlock(_id, _jd - 1) == 1) {
				if (_type == 29) {
					if (x >= _x - width + BLOCKSIZE * .8&& x <= _x + (BLOCKSIZE)) {
						if (y >= _y - height && y <= _y + (BLOCKSIZE)) {
							return true;
						}
					}
				}
			} else if (GridBased.getBlock(_id - 2, _jd - 1) == 1) {
				if (_type == 29) {
					if (x >= _x - width && x <= _x + (BLOCKSIZE * .2)) {
						if (y >= _y - height && y <= _y + (BLOCKSIZE)) {
							return true;
						}
					}
				}
			}
		}
		if( _type == 54&&Visible(_x, _y, 0, 200, 200)||_type == 55&&Visible(_x, _y, 0, 200, 200))
		{
			if(x+ width >= _x && x <= _x + (BLOCKSIZE))
			{
				if (y >= _y - height && y <= _y + (BLOCKSIZE)) {
					return true;
				}
			}
		}
		return false;
	}

	void sand() {
		standardBlock("sand.png", 9, 250, 2);
	}

	void plank() {
		standardBlock("plankT.png", 26, 200, 3);
	}

	void leaf() {
		if(MyGdxGame.snowTimer > snowCreateDestroyTimer && snowTimer < 0)
		{
			standardBlock("leafSnow.png",165,50,4);
		}
		else
		{
			standardBlock("leaf.png", 7, 50, 4);
		}
		
		if (protagonist) {
			block = 1;
		} else {
			block = 0;
		}
	}

	void chest() {
		inventoryTimer--;
		standardBlock("chest.png", 12, 400, 3);
		inventoryTrigger(1);
		pipeUseManager();
	}

	void glass() {
		standardBlock("glass.png", 14, 70, 1);
	}

	void log() {
		standardBlock("log.png", 6, 300, 3);
		if (protagonist) {
			block = 1;
		} else {
			block = 0;
		}
	}
	void jungleLog() {
		standardBlock("jungleLog.png", 56, 300, 3);
		if (protagonist) {
			block = 1;
		} else {
			block = 0;
		}
	}
	void jungleLeaf(){
		standardBlock("jungleLeaf.png", 55, 40, 3);
		if (protagonist) {
			block = 1;
		} else {
			block = 0;
		}
		if(vineRandom<.42d&&GridBased.belowType(_id-1, _jd-1)==0)
		{
			vineExtendTimer++;
		}
		else
		{
			vineExtendTimer = 0;
		}
		if(vineExtendTimer > 300||vineRandom<.42d&&vineCreated == false && jungleFound == true  && GridBased.belowType(_id-1, _jd-1)==0)
		{
			GridBased.changeBlock(_id-1, _jd-2, 54);
			vineCreated = true;
		}
	}
	void vine()
	{
		standardBlock("vine.png",54,40,3);
		block = 0;
		if(GridBased.belowType(_id-1, _jd-1)==0)
		{
			vineExtendTimer++;
		}
		else
		{
			vineExtendTimer = 0;
		}
		if(vineRandom<.8f&&vineExtendTimer > 300||vineRandom<.8f&&vineCreated == false && jungleFound == true && GridBased.belowType(_id-1, _jd-1)==0)
		{
			GridBased.changeBlock(_id-1, _jd-2, 54);
			vineCreated = true;
		}
	}
	void dirtGrass() {
		standardBlock("dirtGrass.png", 3, 210, 2);
		hoeManager();
	}

	void dirt() {
		visibleOverride = true;
		standardBlock("dirt.png", 2, 200, 2);
		hoeManager();
	}

	void cobbleStone() {
		standardBlock("cobbleStone.png", 1, 450, 1);
	}

	void hoedDirt() {
		standardBlock("hoedDirt.png", 30, 200, 2);
		hoeManager();
	}

	void rubberLog() {
		standardBlock("rubberLog.png", 31, 300, 3);
		if (Button.button(_x, _y, BLOCKSIZE, BLOCKSIZE,
				Gdx.input.isButtonPressed(Input.Buttons.RIGHT))
				&& HUD.typeN == InventoryItem.BLOCK_AMOUNT + 16) {
			if (HUD.inventoryFull == false) {
				HUD.inventory.item(InventoryItem.BLOCK_AMOUNT + 16).add(1);
			} else {
				ItemDrop id;
				id = new ItemDrop(HUD.inventory2.item(_type - 1).img, _type, 1,
						_x + BLOCKSIZE / 2, _y + BLOCKSIZE / 2);
				MyGdxGame.itemDrops.add(id);
			}
			_type = 6;
		}
		if (protagonist) {
			block = 1;
		} else {
			block = 0;
		}
	}

	void furnace() {
		/*
		 * inventoryTimer--; inventoryTrigger(3); cookManager(false,false); if
		 * (cooking == false) { standardBlock("furnaceT.png",15,100,3); } else {
		 * standardBlock2("furnace1T.png", "furnace2T.png", 40, -2,100,3);
		 * light(_x,_y,BLOCKSIZE,BLOCKSIZE,30,200); }
		 */
		inventoryCreator(15, Arrays.asList(9, 17, 18, 19),
				Arrays.asList(6, 16), Arrays.asList(14,
						InventoryItem.BLOCK_AMOUNT + 12,
						InventoryItem.BLOCK_AMOUNT + 11,
						InventoryItem.BLOCK_AMOUNT + 13), 3, "furnaceT.png",
				"furnace1T.png", "furnace2T.png", false, true, true, false,
				500, 0, 0, 0, 0);
	}

	void inventoryCreator(int blockNumber, List<Integer> partAccept,List<Integer> fuelAccept, List<Integer> productsCreated,int inventoryType, String notCookingImage, String cookingImage1,
			String cookingImage2, boolean power, boolean light,boolean pipeUse, boolean powerCreate, int MJCAP, float rx,float ry, float rw, float rh) {
		ArrayList fuelList = new ArrayList<Integer>(fuelAccept);
		ArrayList productList = new ArrayList<Integer>(productsCreated);
		ArrayList partAcceptList = new ArrayList<Integer>(partAccept);
		HashMap partToProduct = new HashMap<Integer, Integer>();
		for (int i = 0; i < partAcceptList.size(); i++) {
			partToProduct.put(partAcceptList.get(i), productList.get(i));
		}
		signalUseSwitchTimer--;
		if (power == false) {
			signalUsed = false;
		}
		if (signalUseSwitchTimer < 0 && power) {
			if (Button.button(619, 762, 60, 22,
					Gdx.input.isButtonPressed(Input.Buttons.LEFT))) {
				if (signalUsed) {
					signalUsed = false;
				} else {
					signalUsed = true;
				}
				signalUseSwitchTimer = 20;
			}
		}
		if (power || powerCreate) {
			mjCap = MJCAP;
			wireUse = 1;
			if (power) {
				if (mj < MJCAP) {
					energyUse = true;
				} else {
					energyUse = false;
				}
			}
		}
		acceptFuel = fuelList;
		acceptParts = partAcceptList;
		inventoryTimer--;
		inventoryTrigger(inventoryType);
		if (signalUsed == false || basicSignalFinder()) {
			cookManager(powerCreate, power, partToProduct);
		}
		if (cooking == false || notCookingImage == cookingImage1
				|| cooking == true && basicSignalFinder() == false
				&& signalUsed == true) {
			standardBlock(notCookingImage, blockNumber, 100, 3);
		} else {
			if (signalUsed == false || basicSignalFinder()) {
				standardBlock2(cookingImage1, cookingImage2, 40, -2, 100, 3);
				if (light) {
					light(_x, _y, BLOCKSIZE, BLOCKSIZE, 30, 200);
				}
			}
		}
		if (mj > 0 && power && rectx > 0) {
			if (imgrt != _type) {
				// sr = new ShapeRenderer();
			}
			rectOn = true;
			rectx = _x + BLOCKSIZE - 10;
			recty = _y + 25;
			rectw = 4;
			rectl = (mj * 39) / MJCAP;
		}
		if (power || powerCreate) {
			wireUse = 1;
		}
		if (powerCreate && cooking) {
			if (signalUsed == false || basicSignalFinder()) {
				mj++;
			}
		}
		if (pipeUse && signalUsed == false || powerCreate&& basicSignalFinder() || pipeUse && basicSignalFinder()|| powerCreate && signalUsed == false) {
			pipeUseManager();
		}
		if (powerCreate && signalUsed == false || powerCreate && basicSignalFinder()) {
			upperWireOutputManager();
		}
	}

	boolean inputInventoryCheck(float x, float y, int type, int amount) {
		if (pipeUse == 1 && x > _x - BLOCKSIZE && x < _x + 5 && y > _y
				&& y < _y + BLOCKSIZE) {
			if (iType == 1&&inventory!=null) {
				inventory.item(type - 1).add(amount);
				return true;
			}
			if (type == fuel &&inventory!=null|| fuel == 0&&inventory!=null) {
				for (int i = 0; i < acceptFuel.size(); i++) {
					if (type == (Integer) (acceptFuel.get(i))) {
						inventory.item(type - 1).add(amount);
						fuel = type;
						return true;
					}
				}
			}
			if (type == parts &&inventory!=null|| parts == 0&&inventory!=null) {
				for (int i = 0; i < acceptParts.size(); i++) {
					if (type == (Integer) (acceptParts.get(i))) {
						inventory.item(type - 1).add(amount);
						parts = type;
						return true;
					}
				}
			}
		}
		return false;
	}

	void macerator() {
		if (imgrt != _type) {
			Img2= new Sprite(new Texture("maceratorBlades.png"));
		}
		Img2.setSize(BLOCKSIZE, BLOCKSIZE);
		Img2.setPosition(_x, _y);
		Img2.setOriginCenter();
		Img2.setRotation(rotateAmount);
		if (cooking && signalUsed == false || signalUsed == true
				&& basicSignalFinder() && cooking) {
			rotateAmount += 5;
		}
		inventoryCreator(20, Arrays.asList(17, 18, 19), Arrays.asList(-2),
				Arrays.asList(InventoryItem.BLOCK_AMOUNT + 18,
						InventoryItem.BLOCK_AMOUNT + 19,
						InventoryItem.BLOCK_AMOUNT + 20), 5, "macerator.png",
				"macerator.png", "macerator.png", true, false, true, false,
				500, _x + BLOCKSIZE - 10, _y + 25, 4, (mj * 39) / mjCap);
		
		Img2.draw(batch);
		
		/*
		 * int MJCAP = 500; mjCap = MJCAP; wireUse = 1; inventoryTimer--;
		 * inventoryTrigger(5); //cookManager(false,true); if (imgrt != _type) {
		 * img2 = new Texture("maceratorBlades.png"); Img2 = new Sprite(img2);
		 * sr = new ShapeRenderer(); rectOn = true; } Img2.setSize(BLOCKSIZE,
		 * BLOCKSIZE); Img2.setPosition(_x,_y); Img2.setOriginCenter();
		 * Img2.setRotation(rotateAmount); if (cooking == false) {
		 * standardBlock("macerator.png",20,100,3); } else {
		 * standardBlock("macerator.png",20,100,3); rotateAmount+=5;
		 * 
		 * }  Img2.draw(batch);  if (mj > 0) { rectx =
		 * _x + BLOCKSIZE - 10; recty = _y + 25; rectw = 4; rectl = (mj *
		 * 39)/MJCAP; } if (mj < MJCAP) { energyUse = true; } else { energyUse =
		 * false; } pipeUseManager();
		 */

	}

	void eFurnace() {
		inventoryCreator(32, Arrays.asList(17, 18, 19), Arrays.asList(-2),
				Arrays.asList(InventoryItem.BLOCK_AMOUNT + 12,
						InventoryItem.BLOCK_AMOUNT + 11,
						InventoryItem.BLOCK_AMOUNT + 13), 5, "efurnace.png",
				"efurnace1.png", "efurnace2.png", true, true, true, false, 500,
				_x + BLOCKSIZE - 10, _y + 25, 4, (mj * 39) / mjCap);
	}

	void pipeUseManager() {
		// int MJCAP = 500;
		machineOutputT--;
		pipeUse = 1;
		if (GridBased.pipeUse(_id, _jd - 1) > 0 && Visible(_x, _y, 0, 40, 40)&&inventory!=null) {
			for (int i = 0; i < inventory.size(); i++) {
				if (inventory.item(i).count > 0 && i != parts - 1
						&& i != fuel - 1 || iType == 1
						&& inventory.item(i).count > 0) {
					if (machineOutputT < 0) {
						ItemDrop id;
						id = new ItemDrop(inventory.item(i).img, i + 1, 1, _x
								+ BLOCKSIZE + BLOCKSIZE/2 - 10, _y + BLOCKSIZE - 17);
						MyGdxGame.itemDrops.add(id);
						inventory.item(i).remove(1);
						machineOutputT = 40;
					}
				}
			}
		}
	}

	void fire() {
		/*
		 * inventoryTimer--; inventoryTrigger(2); //cookManager(false,false); if
		 * (cooking == false) { standardBlock("bonfire.png",13,100,3); } else {
		 * standardBlock2("bonfire1.png", "bonfire2.png", 40, -1,100,3);
		 * light(_x,_y,BLOCKSIZE,BLOCKSIZE,30,200); }
		 */
		inventoryCreator(13, Arrays.asList(9), Arrays.asList(6, 16),
				Arrays.asList(14), 3, "bonfire.png", "bonfire1.png",
				"bonfire2.png", false, true, false, false, 500, 0, 0, 0, 0);
		block = 0;
	}

	void coal() {
		standardBlock("coalT.png", 16, 1200, 1);
	}

	void iron() {
		standardBlock("ironT.png", 17, 1500, 1);
	}

	void copper() {
		standardBlock("copperT.png", 18, 1200, 1);
	}

	void gold() {
		standardBlock("goldT.png", 19, 2000, 1);
	}

	void inventoryTrigger(int iType) {
		if(MyGdxGame.timer == 303 && MyGdxGame.load)
		{
			inventoryOn();
		}
		if (Button.button(_x, _y, BLOCKSIZE, BLOCKSIZE,Gdx.input.isButtonPressed(Input.Buttons.RIGHT))&& inventoryTimer <= 0&& inventoryOn == 0&& GridBased.inventoryOn() == 0&& XYControl.Visible(_x, _y, 0, -600, -250)
				&& HUD.inventoryOn == 0&& HUD.armorDisplay == false&& HUD.backPackDisplay == false) {
			inventoryTimer = 20;
			inventoryOn = iType;
		}
		if (XYControl.Visible(_x, _y, 0, -600, -250) == false&& inventoryOn != 0) {
			inventoryOn = 0;
		}
		if (inventoryOn == iType) {
			inventoryOn();
		}
		this.iType = iType;
	}

	void torch() {
		if (GridBased.leftType(_id - 1, _jd - 1) != 0
				&& GridBased.leftType(_id - 1, _jd - 1) != 5
				&& GridBased.leftType(_id - 1, _jd - 1) != 8) {
			standardBlock2("torch1L.png", "torch2L.png", 40, 11, 50, 3);
		} else if (GridBased.rightType(_id - 1, _jd - 1) != 0
				&& GridBased.rightType(_id - 1, _jd - 1) != 5
				&& GridBased.rightType(_id - 1, _jd - 1) != 8) {
			standardBlock2("torch1.png", "torch2.png", 40, 1210, 50, 3);
		} else if (GridBased.belowType(_id - 1, _jd - 1) != 0
				&& GridBased.belowType(_id - 1, _jd - 1) != 5
				&& GridBased.belowType(_id - 1, _jd - 1) != 8) {
			standardBlock2("torch2B.png", "torch2B.png", 40, 1220, 50, 3);
		} else {
			_type = 0;
		}
		light(_x, _y, BLOCKSIZE, BLOCKSIZE, 100, 900);
		block = 0;
	}

	void cookManager(boolean powerGenerator, boolean powerUser, HashMap products) {
		if (Gdx.input.isKeyPressed(Input.Keys.L)) {
			System.out.println("Gpart " + parts);
			System.out.println("Gfuel " + fuel);
			System.out.println("GProduct "
					+ ((Integer) products.get(parts) - 1));
		}
		if (powerGenerator == false) {
			cookingTimer++;
		}
		cookingTimer2++;
		if (cookingTimer > 4) {
			cookCheck = true;
		}
		if (cookingTimer2 <= 1001) {
			cooking = true;
		} else {
			cooking = false;
		}
		if (cookingTimer == 499) {
			inventory.item((Integer) products.get(parts) - 1).add(1);
		}
		if (cookingTimer >= 500&&inventory!=null) {
			if (parts != 0&& inventory.item(parts - 1).count <= 0&& inventory.item((Integer) products.get(parts) - 1).count <= 0) {
				parts = 0;
			}
			if (parts != 0 && inventory.item(parts - 1).count > 0 && fuel != 0&& cookingTimer2 <= 500 || parts != 0&& inventory.item(parts - 1).count > 0 && fuel != 0
					&& cookingTimer2 >= 1000 || parts != 0&& inventory.item(parts - 1).count > 0 && powerUser&& cookingTimer2 >= 500 && mj > 400) {
				cookingTimer = 0;
				inventory.item(parts - 1).remove(1);

			}

		}
		if (cookingTimer2 >= 1000 && fuel > 0 || cookingTimer2 >= 500&& powerUser) {
			if (cookingTimer == 0 && powerUser == false || powerGenerator|| cookingTimer == 0 && powerUser && mj >= 400) {
				cookingTimer2 = 0;
				if (powerUser == false && inventory!=null) {
					inventory.item(fuel - 1).remove(1);
				}
				if (powerUser) {
					mj -= 400;
				}
			}
			if (powerUser == false && fuel != 0
					&& inventory.item(fuel - 1).count <= 0) {
				fuel = 0;
			}
		}
		// System.out.println(parts);
	}

	void lava2() {
		light(_x, _y, BLOCKSIZE, BLOCKSIZE, 20, 50);
		liquid("lava.png", "lava2.png", 5, 10, 1);
	}

	void inventoryOn() {
		if (iActive == 0) {
			inventory = new Inventory();
		}
		if (inventoryCounts != null && iActive == 0) {
			for (int i = 0; i < inventoryCounts.size(); i++) {
				inventory.item(i).add(inventoryCounts.get(i));
			}
		}
		iActive = 1;
	}

	Inventory inventory2() {
		if (iActive == 1 && inventoryOn == 1)
			;
		{
			return inventory;
		}
	}

	void light(float x, float y, float width, float height, float brightness,
			int rayCount) {
		// new PointLight(LightManager.dayHandler, rayCount, null, brightness,
		// (x/5)+ (width/2)/5, (y/5) + (height/2)/5);
		if (lightCreated3 == false) {
			lightSource = new LightSource();
			lightCreated3 = true;
		}
		lightSource.draw(_id - 1, _jd - 1, 8);
	}

	void water() {
		liquid("water1.png", "water2.png", 8, 10, 100);
	}
	void sapling()
	{
		saplingSystem(6,7,true,0,3,3,1,5,4,7,3,100,100);
	}
	void jungleSapling()
	{
		saplingSystem(56,55,false,.9f,2,10,1,6,6,15,3,4,9);
	}
	void saplingSystem(int woodType, int leafType,boolean rubberChance, float leafHeightTrunk, int minTrunkHeight, int randomTrunkHeight, int trunkWidth,int minimumLeafHeight, int randomLeafHeight, int minLeafWidth, int randomLeafWidth,int trunkWidthIncreaseHeight1, int trunkWidthIncreaseHeight2) {
		int height = minTrunkHeight;
		int heightR = (int) (Math.random() * randomTrunkHeight);
		int heightL = minimumLeafHeight;
		int heightLR = (int) ((Math.random() * randomLeafHeight));
		int tWidth = minLeafWidth;
		int tWidthR = (int) (Math.random() * randomLeafWidth);
		if(leafHeightTrunk > 0)
		{
			heightL = (int) (height*leafHeightTrunk+4);
			heightLR = (int) (heightR*leafHeightTrunk);
		}
		protagonist = false;
		if(height+heightR > trunkWidthIncreaseHeight1)
		{
			trunkWidth++;
		}
		if(height+heightR > trunkWidthIncreaseHeight2)
		{
			trunkWidth++;
		}
		if (Visible(_x, _y, 0, -300, 0))

		{
			_type = woodType;
		}
		standardBlock("log.png", 10, 30, 3);
		block = 0;
		if(treeCreated == false)
		{
			
		for(int h = 0; h < trunkWidth;h++)
		{
			GridBased.changeBlock(_id - 1 - (int)(trunkWidth/2)+h, _jd - 1, woodType);
		for (int i = 0; i < height + heightR; i++) {
			float rubberRandom = (float) (Math.random() * 100);
				GridBased.setRange(_id - 1, _jd, range,false,0,0);
				if (rubberChance == false||rubberRandom >= 10) {
					GridBased.create(_x + 15, _y + BLOCKSIZE * (i + 1) + 15,
							true,6,0,0,0,0,true,false);
					GridBased.changeBlock(_id - 1 - (int)(trunkWidth/2)+h, _jd + i, woodType);
				} else {
					GridBased.create(_x + 15, _y + BLOCKSIZE * (i + 1) + 15,
							true,31,0,0,0,0,true,false);
					GridBased.changeBlock(_id - 1 - (int)(trunkWidth/2)+h, _jd + i, 31);
				}
		}
		}
		for (int i = height + heightR; i < heightL + heightLR + height+ heightR; i++) {
			tWidth -= (minLeafWidth+tWidthR)/(heightL+heightLR);
			for (int j = 0; j < tWidth + tWidthR; j++) {
				GridBased.create(_x + 15 + (BLOCKSIZE * (j-1)) - ((tWidth/2)
				 * BLOCKSIZE) + BLOCKSIZE - ((tWidthR/2) * BLOCKSIZE), _y +
				 BLOCKSIZE * (i + 1) + 15, true,7,0,0,0,0,true,false);
				
				if(_jd+i-1<87&&GridBased.aboveType(_id+j-1-(tWidth/2)-(tWidthR/2), _jd+i-1-1)==0)
				{
					GridBased.changeBlock(_id + (j) - 1 - (tWidth / 2) - (tWidthR / 2), _jd + i - 1, leafType);
				}
				
			}
		}
		}
		treeCreated = true;
	}
	
	int stonePipeD(float x, float y, int currentD, boolean transitionReady) {
		if (_type == 22 && transitionReady) {
			if (x > _x + 2 * (BLOCKSIZE / 32) && x < _x + 29 * (BLOCKSIZE / 32)
					&& y < _y + 28 * (BLOCKSIZE / 30)
					&& y > _y + 5 * (BLOCKSIZE / 30) && currentD == 0
					|| x > _x + 11 * (BLOCKSIZE / 32)
					&& x < _x + 12 * (BLOCKSIZE / 32)
					&& y < _y + 13 * (BLOCKSIZE / 30)
					&& y > _y + 12 * (BLOCKSIZE / 30)) {
				if (ironPipeSet != 0) {
					return ironPipeSet;
				} else {
					return 4;
				}
			}
		}
		if (_type == 21 && transitionReady) {
			if (x > _x + 0 * (BLOCKSIZE / 32) && x < _x + 33 * (BLOCKSIZE / 32)
					&& y < _y + 30 * (BLOCKSIZE / 30)
					&& y > _y + 0 * (BLOCKSIZE / 30) && currentD == 0
					|| x > _x + 11 * (BLOCKSIZE / 32)
					&& x < _x + 12 * (BLOCKSIZE / 32)
					&& y < _y + 13 * (BLOCKSIZE / 30)
					&& y > _y + 12 * (BLOCKSIZE / 30)) {
				int pipeD = 0;
				for (int i = 0; i < 100; i++) {
					pipeD = (int) (Math.random() * 5);
					if (pipeD != currentD + 2 && pipeD != 0
							&& pipeD != currentD - 2 || currentD == 0) {
						if (pipeAngle == pipeD * 90 || pipeAngle2 == pipeD * 90
								|| pipeAngle3 == pipeD * 90
								|| pipeAngle4 == pipeD * 90 || pipeD == 4
								&& pipeAngle == 0) {
							return pipeD;
						}
					}
				}
			}
		}
		return 0;
	}
	void button()
	{
		leverTimer--;
		buttonSignalTimer--;
		if (Button.button(_x, _y, BLOCKSIZE, BLOCKSIZE,Gdx.input.isButtonPressed(Input.Buttons.RIGHT))) {
			if (buttonSignalTimer < 0) 
			{
				buttonSignalTimer = 110;
			}
		}
		if(buttonSignalTimer > 0)
		{
			eOn = true;
			standardBlock("Button2.png",46897,10,3);
		}
		else
		{
			eOn = false;
			standardBlock("Button1.png",46,10,3);
		}
		if (GridBased.getBlock(_id - 1, _jd - 2) == 0 && pistonAngle == 0) {
			pistonAngle += 90;
		}
		if (GridBased.getBlock(_id, _jd - 1) == 0 && pistonAngle == 90) {
			pistonAngle += 90;
		}
		if (GridBased.getBlock(_id - 1, _jd) == 0 && pistonAngle == 180) {
			pistonAngle += 90;
		}
		if (GridBased.getBlock(_id - 2, _jd - 1) == 0 && pistonAngle == 270) {
			pistonAngle += 90;
		}
		block = 0;
	}
	void lever() {
		leverTimer--;
		if (GridBased.getBlock(_id - 1, _jd - 2) == 0) {
			destroy(_x + 10, _y + 10, 0, 0, true, true);
			_type = 0;
		}
		if (Button.button(_x, _y, BLOCKSIZE, BLOCKSIZE,
				Gdx.input.isButtonPressed(Input.Buttons.LEFT))
				|| Button.button(_x, _y, BLOCKSIZE, BLOCKSIZE,
						Gdx.input.isButtonPressed(Input.Buttons.RIGHT))) {
			if (leverTimer < 0) {
				if (eOn == false) {
					eOn = true;
					leverTimer = 10;
				} else {
					eOn = false;
					leverTimer = 10;
				}
			}
		}
		if (eOn) {
			standardBlock("lever2.png", 37, 10, 3);
		} else {
			standardBlock("lever1.png", 390, 10, 3);
		}
		block = 0;
	}

	void advancedWire() {

		if (Button.button(_x, _y, BLOCKSIZE, BLOCKSIZE,
				Gdx.input.isButtonPressed(Input.Buttons.RIGHT))) {
			inventoryOn = 6;
		}
		if (inventoryOn == 6) {
			if (Button.button(653, 573, 25, 25,
					Gdx.input.isButtonPressed(Input.Buttons.LEFT))) {
				awd = false;
			}
			if (Button.button(829, 573, 25, 25,
					Gdx.input.isButtonPressed(Input.Buttons.LEFT))) {
				awd = true;
			}
			if (Button.button(1175, 629, 23, 20,
					Gdx.input.isButtonPressed(Input.Buttons.LEFT))) {
				if (awdc <= 10000) {
					awdc++;
				}
			}
			if (Button.button(1175, 508, 25, 20,
					Gdx.input.isButtonPressed(Input.Buttons.LEFT))) {
				if (awdc > 0) {
					awdc--;
				}
			}
		}
		if (awdct <= 0) {
			standardBlock("advancedWireManager.png", 38, 10, 3);
		} else {
			standardBlock("advancedWireManager2.png", 540, 10, 3);
		}
		if (awsr >= 1) {
			awdct++;
		}
		if (awdct > awdc) {
			if (awsr > 0) {
				awsr--;
			} else {
				awdct = 0;
			}
			eOn = true;
			eOnChange = 3;
		} else {
			eOn = false;
			eOnChange--;
		}
		if (GridBased.geteOn(_id - 2, _jd - 1) == true && awd == false) {
			awsr++;
		}
		if (GridBased.geteOn(_id, _jd - 1) == true && awd == true) {
			awsr++;
		}
		if (XYControl.Visible(_x, _y, 0, -600, -250) == false
				&& inventoryOn != 0) {
			inventoryOn = 0;
		}
		block = 0;
	}
	void signalConverter()
	{
		leverTimer--;
		
		if(imageFlip)
		{
			Img1.setFlip(true, false);
			if(GridBased.geteOn(_id, _jd-1))
			{
				eOn = true;
			}
			else
			{
				eOn = false;
			}
		}
		else
		{
			Img1.setFlip(false, false);
			if(GridBased.geteOn(_id-2, _jd-1))
			{
				eOn = true;
			}
			else
			{
				eOn = false;
			}
		}
		standardBlock("signalConverter.png",48,20,3);
		//Img1.setFlip(false, false);
		if (Button.button(_x, _y, BLOCKSIZE, BLOCKSIZE,Gdx.input.isButtonPressed(Input.Buttons.RIGHT))) 
		{
			if(imageFlip == false && leverTimer < 0)
			{
				imageFlip = true;
				leverTimer = 20;
			}
			if(imageFlip == true && leverTimer < 0)
			{
				imageFlip = false;
				leverTimer = 20;
			}
		}
		block = 0;
	}
	void wire() {
		eOnWire = true;
		if (eOn == false) {
			eOnChange--;
		}
		if (eOnChange < 0) {
			eOnD = 0;
		}
		if (eOn == false) {
			if (imgrt != 36) {
				Img2= new Sprite(new Texture("wire.png"));
				Img3= new Sprite(new Texture("wire.png"));
			}
			standardBlock("wire.png", 36, 10, 3);
		} else {
			if (imgrt != 394) {
				Img2= new Sprite(new Texture("wireOn.png"));
				Img3= new Sprite(new Texture("wireOn.png"));
			}
			standardBlock("wireOn.png", 394, 10, 3);
		}
		eOn = false;
		// 
		Img2.setSize(BLOCKSIZE, BLOCKSIZE);
		if(GridBased.getBlock(_id-1,_jd-2)==1)
		{
			if(GridBased.geteOnWire(_id-2, _jd-1) == false)
			{
		if (GridBased.getBlock(_id - 2, _jd - 1) == 1
				&& GridBased.getBlock(_id - 1, _jd) == 0
				&& GridBased.geteOnWire(_id - 2, _jd)) {
			Img2.setRotation(90);
			Img2.setPosition(_x - 23, _y);
			// Img2.draw(batch);
			if (GridBased.geteOn(_id - 2, _jd) == true && eOnD == 0) {
				eOn = true;
				eOnD = 1;
				eOnChange = 3;
			}
			if (eOnD == 1) {
				eOn = GridBased.geteOn(_id - 2, _jd);
			}
		} else if (GridBased.getBlock(_id - 2, _jd - 1) == 0
				&& GridBased.geteOnWire(_id - 2, _jd - 2)) {
			Img2.setRotation(90);
			Img2.setPosition(_x - 23, _y - BLOCKSIZE);
			
			Img2.draw(batch);
			
			if (GridBased.geteOn(_id - 2, _jd - 2) == true && eOnD == 0) {
				eOn = true;
				eOnD = 2;
				eOnChange = 3;
			}
			if (eOnD == 2) {
				eOn = GridBased.geteOn(_id - 2, _jd - 2);
			}
		}
			}
		// 
		if (GridBased.getBlock(_id, _jd - 1) == 1
				&& GridBased.getBlock(_id - 1, _jd) == 0) {
			if (GridBased.geteOn(_id, _jd) == true && eOnD == 0) {
				eOn = true;
				eOnD = 3;
				eOnChange = 3;
			}
			if (eOnD == 3) {
				eOn = GridBased.geteOn(_id, _jd);
			}
		} else if (GridBased.getBlock(_id, _jd - 1) == 0) {
			if (GridBased.geteOn(_id, _jd - 2) == true && eOnD == 0) {
				eOnD = 4;
				eOn = true;
				eOnChange = 3;
			}
			if (eOnD == 4) {
				eOn = GridBased.geteOn(_id, _jd - 2);
			}
		}
		}
		if (GridBased.geteOn(_id - 2, _jd - 1) == true && eOnD == 0
				&& GridBased.leftType(_id - 1, _jd - 1) != 38 && GridBased.leftType(_id-1, _jd-1) != 48
				|| GridBased.leftType(_id - 1, _jd - 1) == 38
				&& GridBased.getawd(_id - 2, _jd - 1) == false && eOnD == 0
				&& GridBased.geteOn(_id - 2, _jd - 1) == true||GridBased.leftType(_id -1, _jd-1)==48 && GridBased.getImageFlip(_id - 2, _jd -1) == false && GridBased.geteOn(_id - 2, _jd - 1) == false) {
			eOnD = 5;
			eOn = true;
			eOnChange = 3;
		}
		if (eOnD == 5) {
			if(GridBased.leftType(_id-1, _jd-1)!=48)
			{
			eOn = GridBased.geteOn(_id - 2, _jd - 1);
			}
			else
			{
				if(GridBased.leftType(_id -1, _jd-1)==48 && GridBased.getImageFlip(_id - 2, _jd -1) == false && GridBased.geteOn(_id - 2, _jd - 1) == false )
				{
					eOn = true;
				}
				else
				{
					eOn = false;
				}
						
			}
		}
		if (GridBased.geteOn(_id, _jd - 1) == true && eOnD == 0
				&& GridBased.rightType(_id - 1, _jd - 1) != 38
				|| GridBased.rightType(_id - 1, _jd - 1) == 38
				&& GridBased.getawd(_id, _jd - 1) == true && eOnD == 0
				&& GridBased.geteOn(_id, _jd - 1) == true||GridBased.rightType(_id -1, _jd-1)==48 && GridBased.getImageFlip(_id, _jd -1) == true && GridBased.geteOn(_id, _jd - 1) == false) {
			eOnD = 6;
			eOn = true;
			eOnChange = 3;
		}
		if (eOnD == 6) {
			if(GridBased.rightType(_id -1, _jd-1) != 48)
			{
			eOn = GridBased.geteOn(_id, _jd - 1);
			}
			else
			{
			if(GridBased.rightType(_id -1, _jd-1)==48 && GridBased.getImageFlip(_id, _jd -1) == true && GridBased.geteOn(_id, _jd - 1) == false )
			{
				eOn = true;
			}
			else
			{
				eOn = false;
			}
			}
		}
		
		if (GridBased.geteOnWire(_id - 1, _jd)) {
			Img3.setSize(BLOCKSIZE, BLOCKSIZE);
			Img3.setRotation(90);
			Img3.setPosition(_x + 10 , _y+4);
			
			Img3.draw(batch);
			
			// Img2.draw(batch);
			if (GridBased.geteOn(_id - 1, _jd) == true && eOnD == 0) {
				eOn = true;
				eOnD = 7;
				eOnChange = 3;
			}
			if (eOnD == 7) {
				eOn = GridBased.geteOn(_id - 1, _jd);
			}
		}
		if (GridBased.geteOnWire(_id - 1, _jd-2)) {
			// Img2.draw(batch);
			if (GridBased.geteOn(_id - 1, _jd-2) == true && eOnD == 0) {
				eOn = true;
				eOnD = 8;
				eOnChange = 3;
			}
			if (eOnD == 8) {
				eOn = GridBased.geteOn(_id - 1, _jd-2);
			}
		}
		block = 0;
	}
	void solidWire()
	{
			eOnWire = true;
			if (eOn == false) {
				eOnChange--;
			}
			if (eOnChange < 0) {
				eOnD = 0;
			}
			if (eOn == false) {
				if (imgrt != 36) {
					Img2= new Sprite(new Texture("wire.png"));
					Img3= new Sprite(new Texture("wire.png"));
				}
				standardBlock("glassWire.png", 50, 10, 3);
			} else {
				if (imgrt != 394) {
					Img2= new Sprite(new Texture("wireOn.png"));
					Img3= new Sprite(new Texture("wireOn.png"));
				}
				standardBlock("glassWireOn.png", 398, 10, 3);
			}
			eOn = false;
			
			Img2.setSize(BLOCKSIZE, BLOCKSIZE);
			if(GridBased.getBlock(_id-1,_jd-2)==1)
			{
				if(GridBased.geteOnWire(_id-2, _jd-1) == false)
				{
			if (GridBased.getBlock(_id - 2, _jd - 1) == 1
					&& GridBased.getBlock(_id - 1, _jd) == 0
					&& GridBased.geteOnWire(_id - 2, _jd)) {
				Img2.setRotation(90);
				Img2.setPosition(_x - 23, _y);
					Img2.draw(batch);
				// Img2.draw(batch);
				if (GridBased.geteOn(_id - 2, _jd) == true && eOnD == 0) {
					eOn = true;
					eOnD = 1;
					eOnChange = 3;
				}
				if (eOnD == 1) {
					eOn = GridBased.geteOn(_id - 2, _jd);
				}
			} else if (GridBased.getBlock(_id - 2, _jd - 1) == 0
					&& GridBased.geteOnWire(_id - 2, _jd - 2)) {
				Img2.setRotation(90);
				Img2.setPosition(_x - 23, _y - BLOCKSIZE);
				Img2.draw(batch);
				// Img2.draw(batch);
				if (GridBased.geteOn(_id - 2, _jd - 2) == true && eOnD == 0) {
					eOn = true;
					eOnD = 2;
					eOnChange = 3;
				}
				if (eOnD == 2) {
					eOn = GridBased.geteOn(_id - 2, _jd - 2);
				}
			}
				}
			// 
			if (GridBased.getBlock(_id, _jd - 1) == 1
					&& GridBased.getBlock(_id - 1, _jd) == 0) {
				if (GridBased.geteOn(_id, _jd) == true && eOnD == 0) {
					eOn = true;
					eOnD = 3;
					eOnChange = 3;
				}
				if (eOnD == 3) {
					eOn = GridBased.geteOn(_id, _jd);
				}
			} else if (GridBased.getBlock(_id, _jd - 1) == 0) {
				if (GridBased.geteOn(_id, _jd - 2) == true && eOnD == 0) {
					eOnD = 4;
					eOn = true;
					eOnChange = 3;
				}
				if (eOnD == 4) {
					eOn = GridBased.geteOn(_id, _jd - 2);
				}
			}
			}
			if (GridBased.geteOn(_id - 2, _jd - 1) == true && eOnD == 0
					&& GridBased.leftType(_id - 1, _jd - 1) != 38 && GridBased.leftType(_id-1, _jd-1) != 48
					|| GridBased.leftType(_id - 1, _jd - 1) == 38
					&& GridBased.getawd(_id - 2, _jd - 1) == false && eOnD == 0
					&& GridBased.geteOn(_id - 2, _jd - 1) == true||GridBased.leftType(_id -1, _jd-1)==48 && GridBased.getImageFlip(_id - 2, _jd -1) == false && GridBased.geteOn(_id - 2, _jd - 1) == false) {
				eOnD = 5;
				eOn = true;
				eOnChange = 3;
			}
			if (eOnD == 5) {
				if(GridBased.leftType(_id-1, _jd-1)!=48)
				{
				eOn = GridBased.geteOn(_id - 2, _jd - 1);
				}
				else
				{
					if(GridBased.leftType(_id -1, _jd-1)==48 && GridBased.getImageFlip(_id - 2, _jd -1) == false && GridBased.geteOn(_id - 2, _jd - 1) == false )
					{
						eOn = true;
					}
					else
					{
						eOn = false;
					}
							
				}
			}
			if (GridBased.geteOn(_id, _jd - 1) == true && eOnD == 0
					&& GridBased.rightType(_id - 1, _jd - 1) != 38
					|| GridBased.rightType(_id - 1, _jd - 1) == 38
					&& GridBased.getawd(_id, _jd - 1) == true && eOnD == 0
					&& GridBased.geteOn(_id, _jd - 1) == true||GridBased.rightType(_id -1, _jd-1)==48 && GridBased.getImageFlip(_id, _jd -1) == true && GridBased.geteOn(_id, _jd - 1) == false) {
				eOnD = 6;
				eOn = true;
				eOnChange = 3;
			}
			if (eOnD == 6) {
				if(GridBased.rightType(_id -1, _jd-1) != 48)
				{
				eOn = GridBased.geteOn(_id, _jd - 1);
				}
				else
				{
				if(GridBased.rightType(_id -1, _jd-1)==48 && GridBased.getImageFlip(_id, _jd -1) == true && GridBased.geteOn(_id, _jd - 1) == false )
				{
					eOn = true;
				}
				else
				{
					eOn = false;
				}
				}
			}
			
			if (GridBased.geteOnWire(_id - 1, _jd)) {
				Img3.setSize(BLOCKSIZE, BLOCKSIZE);
				Img3.setRotation(90);
				Img3.setPosition(_x + 10 , _y+4);
				Img3.draw(batch);
				Img2.draw(batch);
				if (GridBased.geteOn(_id - 1, _jd) == true && eOnD == 0) {
					eOn = true;
					eOnD = 7;
					eOnChange = 3;
				}
				if (eOnD == 7) {
					eOn = GridBased.geteOn(_id - 1, _jd);
				}
			}
			if (GridBased.geteOnWire(_id - 1, _jd-2)) {
				Img2.draw(batch);
				if (GridBased.geteOn(_id - 1, _jd-2) == true && eOnD == 0) {
					eOn = true;
					eOnD = 8;
					eOnChange = 3;
				}
				if (eOnD == 8) {
					eOn = GridBased.geteOn(_id - 1, _jd-2);
				}
			}
			
	}
	void copperWire() {
		float pipeAngle = -1;
		float pipeAngle2 = -1;
		float pipeAngle3 = -1;
		float pipeAngle4 = -1;
		mjCap = 5;
		standardBlock("copperWireCenter.png", 25, 10, 3);
		if (wireUse != 2) {
			Img2= new Sprite(new Texture("copperWire.png"));
			Img3= new Sprite(new Texture("copperWire.png"));
			Img4= new Sprite(new Texture("copperWire.png"));
			Img5= new Sprite(new Texture("copperWire.png"));
		}
		Img2.setSize(BLOCKSIZE, BLOCKSIZE);
		Img4.setSize(BLOCKSIZE, BLOCKSIZE);
		Img5.setSize(BLOCKSIZE, BLOCKSIZE);
		wireUse = 2;
		Img3.setSize(BLOCKSIZE, BLOCKSIZE);
		if (GridBased.wireUse(_id - 2, _jd - 1) > 1) {
			pipeAngle = 0;
			Img2.setPosition(_x, _y);
		}
		if (GridBased.wireUse(_id - 1, _jd - 2) > 0) {
			if (pipeAngle == -1) {
				pipeAngle = 90;
				Img2.setPosition(_x, _y);
			} else {
				pipeAngle2 = 90;
				Img3.setPosition(_x, _y);
			}
		}
		if (GridBased.wireUse(_id, _jd - 1) > 1) {
			if (pipeAngle == -1) {
				pipeAngle = 180;
				Img2.setPosition(_x, _y + 3);
			} else if (pipeAngle2 == -1) {
				pipeAngle2 = 180;
				Img3.setPosition(_x, _y + 3);
			} else {
				pipeAngle3 = 180;
				Img4.setPosition(_x, _y + 3);
			}
		}
		if (GridBased.wireUse(_id - 1, _jd) > 0) {
			if (pipeAngle == -1) {
				pipeAngle = 270;
				Img2.setPosition(_x - 3, _y);
			} else if (pipeAngle2 == -1) {
				pipeAngle2 = 270;
				Img3.setPosition(_x - 3, _y);
			} else if (pipeAngle3 == -1) {
				pipeAngle3 = 270;
				Img4.setPosition(_x - 3, _y);
			} else {
				pipeAngle4 = 270;
				Img5.setPosition(_x - 3, _y);
			}
		}
		Img2.setOriginCenter();
		Img3.setOriginCenter();
		Img4.setOriginCenter();
		Img5.setOriginCenter();
		Img2.setRotation(pipeAngle);
		Img3.setRotation(pipeAngle2);
		Img4.setRotation(pipeAngle3);
		Img5.setRotation(pipeAngle4);
		
		if (pipeAngle != -1) {
			Img2.draw(batch);
		}
		if (pipeAngle2 != -1) {
			Img3.draw(batch);
		}
		if (pipeAngle3 != -1) {
			Img4.draw(batch);
		}
		if (pipeAngle4 != -1) {
			Img5.draw(batch);
		}
		
		this.pipeAngle = pipeAngle;
		this.pipeAngle2 = pipeAngle2;
		this.pipeAngle3 = pipeAngle3;
		this.pipeAngle4 = pipeAngle4;
		block = 0;
		fullWireOutputManager();
	}

	void upperWireOutputManager() {
		if (mj > 0 && GridBased.wireUse(_id - 1, _jd) > 0
				&& GridBased.energyUse(_id - 1, _jd)) {
			// if (GridBased.aboveType(_id -1, _jd) == 25)
			// {
			mj -= 1;
			GridBased.addMj(_id - 1, _jd, 1, 4);
			// }
		}
	}

	void fullWireOutputManager() {

		int mjDistribution = -1;
		if (GridBased.wireUse(_id - 1, _jd) > 0
				&& GridBased.energyUse(_id - 1, _jd) && wireInputD != 1) {
			mjDistribution++;
		}

		if (GridBased.wireUse(_id - 2, _jd - 1) > 1
				&& GridBased.energyUse(_id - 2, _jd - 1) && wireInputD != 2) {
			mjDistribution++;
		}
		if (GridBased.wireUse(_id - 1, _jd - 2) > 0
				&& GridBased.energyUse(_id - 1, _jd - 2) && wireInputD != 4) {
			mjDistribution++;
		}
		if (GridBased.wireUse(_id, _jd - 1) > 1
				&& GridBased.energyUse(_id, _jd - 1) && wireInputD != 3) {
			mjDistribution++;
		}
		if (mjDistribution > -1) {
			energyUse = true;
		} else {
			energyUse = false;
		}
		if (mj > 0 && GridBased.wireUse(_id - 1, _jd - 2) > 0
				&& wireInputD != 4 && GridBased.energyUse(_id - 1, _jd - 2)) {
			GridBased.addMj(_id - 1, _jd - 2, (mj / (mjDistribution + 1)), 1);
			// mj -= mj/mjDistribution;
		}
		if (mj > 0 && GridBased.wireUse(_id, _jd - 1) > 1 && wireInputD != 3
				&& GridBased.energyUse(_id, _jd - 1)) {
			// if (GridBased.wireUse(_id, _jd - 1) == 2)
			// {
			// GridBased.addMj(_id, _jd -1, (mj/mjDistribution));
			// }
			GridBased.addMj(_id, _jd - 1, (mj / (mjDistribution + 1)), 2);
			// mj -= mj/mjDistribution;
		}

		if (mj > 0 && GridBased.wireUse(_id - 2, _jd - 1) > 1
				&& wireInputD != 2 && GridBased.energyUse(_id - 2, _jd - 1)) {
			GridBased.addMj(_id - 2, _jd - 1, (mj / (mjDistribution + 1)), 3);
			// mj -= mj/mjDistribution;
		}
		if (mj > 0 && GridBased.wireUse(_id - 1, _jd) > 0 && wireInputD != 1
				&& GridBased.energyUse(_id - 1, _jd)) {
			GridBased.addMj(_id - 1, _jd, (mj / (mjDistribution + 1)), 4);
			// mj -= mj/mjDistribution;
		}
		mj -= mj;

	}

	void addMj(float mj2, int direction) {
		if (mj < mjCap) {
			mj += mj2;
			wireInputD = direction;
		}
	}

	void generator() {
		inventoryCreator(24, Arrays.asList(-2), Arrays.asList(6, 16),
				Arrays.asList(1), 4, "generator.png", "generator1.png","generator2.png", false, true, true, true, 1000, _x + BLOCKSIZE- 10, _y + 25, 4, (mj * 39) / mjCap);
		/*
		 * int MJCAP = 1000; inventoryTimer--; inventoryTrigger(4);
		 * //cookManager(true,false); pipeUse = 1; wireUse = 1; if (imgrt != 24)
		 * { sr = new ShapeRenderer(); rectOn = true; } if (mj < MJCAP) {
		 * //energyUse = true; } else { //energyUse = false; } if (cooking ==
		 * false) { standardBlock("generator.png",24,100,3); } else { if (mj <
		 * MJCAP) { mj++;
		 * 
		 * } standardBlock2("generator1.png", "generator2.png", 43, -2,100,3);
		 * light(_x,_y,BLOCKSIZE,BLOCKSIZE,30,200); } if (mj > 0) { rectx = _x +
		 * BLOCKSIZE - 10; recty = _y + 25; rectw = 4; rectl = (mj * 39)/MJCAP;
		 * } mjCap =MJCAP;
		 */
		// upperWireOutputManager();
	}

	void batBox() {
		int MJCAP = 5000;
		if (mj < MJCAP) {
			energyUse = true;
		} else {
			energyUse = false;
		}
		wireUse = 1;
		if (imgrt != 23) {
			// sr = new ShapeRenderer();
			rectOn = true;
		}
		standardBlock("batBox.png", 23, 100, 3);
		if (mj > 0) {
			rectx = _x + BLOCKSIZE - 16;
			recty = _y + 12;
			rectw = 6;
			rectl = (mj * 41) / MJCAP;

		}
		mjCap = MJCAP;
		upperWireOutputManager();
	}

	void stonePipe() {

		float pipeAngle = -1;
		float pipeAngle2 = -1;
		float pipeAngle3 = -1;
		float pipeAngle4 = -1;
		standardBlock("stonePipeCenter.png", 21, 10, 3);
		if (pipeUse != 2) {
			Img2= new Sprite(new Texture("stonePipe.png"));
			Img3= new Sprite(new Texture("stonePipe.png"));
			Img4= new Sprite(new Texture("stonePipe.png"));
			Img5= new Sprite(new Texture("stonePipe.png"));
		}
		Img2.setSize(BLOCKSIZE, BLOCKSIZE);
		Img4.setSize(BLOCKSIZE, BLOCKSIZE);
		Img5.setSize(BLOCKSIZE, BLOCKSIZE);
		pipeUse = 2;
		Img3.setSize(BLOCKSIZE, BLOCKSIZE);
		if (GridBased.pipeUse(_id - 2, _jd - 1) > 0) {
			pipeAngle = 0;
			Img2.setPosition(_x, _y);
		}
		if (GridBased.pipeUse(_id - 1, _jd - 2) > 1) {
			if (pipeAngle == -1) {
				pipeAngle = 90;
				Img2.setPosition(_x, _y);
			} else {
				pipeAngle2 = 90;
				Img3.setPosition(_x, _y);
			}
		}
		if (GridBased.pipeUse(_id, _jd - 1) > 0) {
			if (pipeAngle == -1) {
				pipeAngle = 180;
				Img2.setPosition(_x, _y + 3);
			} else if (pipeAngle2 == -1) {
				pipeAngle2 = 180;
				Img3.setPosition(_x, _y + 3);
			} else {
				pipeAngle3 = 180;
				Img4.setPosition(_x, _y + 3);
			}
		}
		if (GridBased.pipeUse(_id - 1, _jd) > 1) {
			if (pipeAngle == -1) {
				pipeAngle = 270;
				Img2.setPosition(_x - 3, _y);
			} else if (pipeAngle2 == -1) {
				pipeAngle2 = 270;
				Img3.setPosition(_x - 3, _y);
			} else if (pipeAngle3 == -1) {
				pipeAngle3 = 270;
				Img4.setPosition(_x - 3, _y);
			} else {
				pipeAngle4 = 270;
				Img5.setPosition(_x - 3, _y);
			}
		}
		Img2.setOriginCenter();
		Img3.setOriginCenter();
		Img4.setOriginCenter();
		Img5.setOriginCenter();
		Img2.setRotation(pipeAngle);
		Img3.setRotation(pipeAngle2);
		Img4.setRotation(pipeAngle3);
		Img5.setRotation(pipeAngle4);
		
		if (pipeAngle != -1) {
			Img2.draw(batch);
		}
		if (pipeAngle2 != -1) {
			Img3.draw(batch);
		}
		if (pipeAngle3 != -1) {
			Img4.draw(batch);
		}
		if (pipeAngle4 != -1) {
			Img5.draw(batch);
		}
		
		this.pipeAngle = pipeAngle;
		this.pipeAngle2 = pipeAngle2;
		this.pipeAngle3 = pipeAngle3;
		this.pipeAngle4 = pipeAngle4;
		block = 0;
	}

	void ironPipe() {
		ironPipeT--;
		float pipeAngle = -1;
		float pipeAngle2 = -1;
		float pipeAngle3 = -1;
		float pipeAngle4 = -1;
		standardBlock("ironPipeCenter.png", 22, 10, 3);
		if (pipeUse != 2) {
			Img2= new Sprite(new Texture("ironPipe2.png"));
			Img3= new Sprite(new Texture("ironPipe.png"));
			Img4= new Sprite(new Texture("ironPipe.png"));
			Img5= new Sprite(new Texture("ironPipe.png"));
		}
		Img2.setSize(BLOCKSIZE, BLOCKSIZE);
		Img4.setSize(BLOCKSIZE, BLOCKSIZE);
		Img5.setSize(BLOCKSIZE, BLOCKSIZE);
		pipeUse = 2;
		Img3.setSize(BLOCKSIZE, BLOCKSIZE);
		if (Button.button(_x, _y, BLOCKSIZE, BLOCKSIZE,
				Gdx.input.isButtonPressed(Input.Buttons.RIGHT))
				&& ironPipeT < 0) {
			ironPipeSet++;
			if (ironPipeSet > 3) {
				ironPipeSet = 0;
			}
			ironPipeT = 20;
		}
		if (GridBased.pipeUse(_id - 2, _jd - 1) > 0) {
			if (ironPipeSet == 0) {
				pipeAngle = 0;
				Img2.setPosition(_x, _y);
			} else {
				pipeAngle2 = 0;
				Img3.setPosition(_x, _y);
			}
		}
		if (GridBased.pipeUse(_id - 1, _jd - 2) > 1) {
			if (ironPipeSet == 1) {
				pipeAngle = 90;
				Img2.setPosition(_x, _y);
			} else if (pipeAngle2 == -1) {
				pipeAngle2 = 90;
				Img3.setPosition(_x, _y);
			} else if (pipeAngle3 == -1) {
				pipeAngle3 = 90;
				Img4.setPosition(_x, _y);
			}
		}
		if (GridBased.pipeUse(_id, _jd - 1) > 0) {
			if (ironPipeSet == 2) {
				pipeAngle = 180;
				Img2.setPosition(_x, _y + 3);
			} else if (pipeAngle2 == -1) {
				pipeAngle2 = 180;
				Img3.setPosition(_x, _y + 3);
			} else if (pipeAngle3 == -1) {
				pipeAngle3 = 180;
				Img4.setPosition(_x, _y + 3);
			} else if (pipeAngle4 == -1) {
				pipeAngle4 = 180;
				Img5.setPosition(_x, _y + 3);
			}
		}
		if (GridBased.pipeUse(_id - 1, _jd) > 1) {
			if (ironPipeSet == 3) {
				pipeAngle = 270;
				Img2.setPosition(_x - 3, _y);
			} else if (pipeAngle2 == -1) {
				pipeAngle2 = 270;
				Img3.setPosition(_x - 3, _y);
			} else if (pipeAngle3 == -1) {
				pipeAngle3 = 270;
				Img4.setPosition(_x - 3, _y);
			} else if (pipeAngle4 == -1) {
				pipeAngle4 = 270;
				Img5.setPosition(_x - 3, _y);
			}
		}
		Img2.setOriginCenter();
		Img3.setOriginCenter();
		Img4.setOriginCenter();
		Img5.setOriginCenter();
		Img2.setRotation(pipeAngle);
		Img3.setRotation(pipeAngle2);
		Img4.setRotation(pipeAngle3);
		Img5.setRotation(pipeAngle4);
		
		if (pipeAngle != -1) {
			Img2.draw(batch);
		}
		if (pipeAngle2 != -1) {
			Img3.draw(batch);
		}
		if (pipeAngle3 != -1) {
			Img4.draw(batch);
		}
		if (pipeAngle4 != -1) {
			Img5.draw(batch);
		}
		
		this.pipeAngle = pipeAngle;
		this.pipeAngle2 = pipeAngle2;
		this.pipeAngle3 = pipeAngle3;
		this.pipeAngle4 = pipeAngle4;
		block = 0;
	}

	int insideType(float x, float y) {
		if (x > _x + 2 && x <= _x + 31 * (BLOCKSIZE / 32) - 2
				&& y < _y + 29 * (BLOCKSIZE / 30) - 3 && y > _y + 2) {
			return _type;
		}
		return -1;
	}

	int insideX(float x, float y) {
		if (x > _x && x < _x + BLOCKSIZE && y < _y + BLOCKSIZE && y > _y) {
			return _x + 11 * (BLOCKSIZE / 32) + 1;
		}
		return -1;
	}

	int insideY(float x, float y) {
		if (x > _x && x < _x + BLOCKSIZE && y < _y + BLOCKSIZE && y > _y) {
			return _y + 12 * (BLOCKSIZE / 30) + 1;
		}
		return -1;
	}

	void liquid(String texture, String texture2, int typeSet, int range2,
			int viscocity) {
		destroy(Gdx.input.getX(), (1000 - Gdx.input.getY()), 0, 0,
				Gdx.input.isButtonPressed(Input.Buttons.LEFT), true);
		if (imgrt != typeSet) {
			Img1= new Sprite(new Texture(texture));
			Img2= new Sprite(new Texture(texture2));
			imgrt = typeSet;
		}
		if (sourceBlockConnect(typeSet) == false) {
			liquidDestroyTimer++;
			if (liquidDestroyTimer > 50) {
				_type = 0;
				liquidPlaceTimerB = 0;
				liquidPlaceTimerR = 0;
				liquidPlaceTimerL = 0;
				range = LAVARANGE + 1;
				sourceD = 0;
				liquidDestroyTimer = 0;
			}
		} else {
			liquidDestroyTimer = 0;
		}
		block = 0;
		Img1.setSize(BLOCKSIZE, BLOCKSIZE
				* ((float) (range) / ((float) (range2))));
		Img1.setPosition(_x, _y);
		Img2.setSize(BLOCKSIZE, BLOCKSIZE
				* ((float) (range) / ((float) (range2))));
		Img2.setPosition(_x, _y);
		liquidMoveTimer++;
		if (liquidMoveTimer >= 60) {
			liquidMoveTimer = 0;
		}

		
		if (liquidMoveTimer < 30) {
			Img1.setAlpha(.6f);
			Img1.draw(batch);
		}

		else if (liquidMoveTimer < 60) {
			Img2.setAlpha(.6f);
			Img2.draw(batch);
		}
		

		if (range > 1) {
			if (GridBased.belowType(_id - 1, _jd - 1) == 0
					|| GridBased.belowType(_id - 1, _jd - 1) == typeSet) {

				GridBased.setRange(_id - 1, _jd - 2, range2, false, 0, 0);
				if (GridBased.belowType(_id - 1, _jd - 1) == 0) {
					if (liquidPlaceTimerB > viscocity || found == 0) {
						GridBased.setSourceD(_id - 1, _jd - 2, 1);
						GridBased.create(_x + 15, _y - 15, true, typeSet, 0, 0,
								0, 0, true, false);
					}
					liquidPlaceTimerB++;
				} else {
					liquidPlaceTimerB = 0;
				}
			}

		}
		if (range > 3) {
			if (GridBased.belowType(_id - 1, _jd - 1) != typeSet
					&& GridBased.belowType(_id - 1, _jd - 1) != 0
					|| GridBased.belowType(_id - 1, _jd - 1) == typeSet
					&& GridBased.belowType(_id - 2, _jd - 1) != typeSet
					&& GridBased.belowType(_id - 2, _jd - 1) != 0) {
				if (GridBased.leftType(_id - 1, _jd - 1) == 0 && sourceD != 3
						|| GridBased.leftType(_id - 1, _jd - 1) == typeSet
						&& sourceD != 3) {
					liquidPlaceTimerL++;
					if (liquidPlaceTimerL > viscocity || found == 0) {
						GridBased.setRange(_id - 2, _jd - 1, range
								- LAVA_RANGE_DECREASE, false, 0, 0);
						GridBased.setSourceD(_id - 2, _jd - 1, 2);
						if (GridBased.leftType(_id - 1, _jd - 1) == 0) {
							GridBased.create(_x - 15, _y + 15, true, typeSet,
									0, 0, 0, 0, true, false);
						}
					}
				} else {
					liquidPlaceTimerL = 0;
				}

			}

			if (GridBased.belowType(_id - 1, _jd - 1) != typeSet
					&& GridBased.belowType(_id - 1, _jd - 1) != 0
					|| GridBased.belowType(_id - 1, _jd - 1) == typeSet
					&& GridBased.belowType(_id, _jd - 1) != typeSet
					&& GridBased.belowType(_id, _jd - 1) != 0) {

				if (GridBased.rightType(_id - 1, _jd - 1) == 0 && sourceD != 2
						|| GridBased.rightType(_id - 1, _jd - 1) == typeSet
						&& sourceD != 2) {
					liquidPlaceTimerR++;
					if (liquidPlaceTimerR > viscocity || found == 0) {
						GridBased.setSourceD(_id, _jd - 1, 3);
						GridBased.setRange(_id, _jd - 1, range
								- LAVA_RANGE_DECREASE, false, 0, 0);
						if (GridBased.rightType(_id - 1, _jd - 1) == 0) {
							GridBased.create(_x + BLOCKSIZE + 15, _y + 15,
									true, typeSet, 0, 0, 0, 0, true, false);
						}

					}

				} else {
					liquidPlaceTimerR = 0;
				}
			}
		}
	}

	void air() {
		pistonPushed = false;
		block = 0;
		pipeUse = 0;
		wireUse = 0;
		mj = 0;
		mjCap = 0;
		rectOn = false;
		eOnWire = false;
		eOn = false;
	}

	void drawRect() {
		if (rectOn) {
			//sa.setColor(1, 0, 0, 1);
			//sa.rect(false, rectx, recty, rectw, rectl);
			// sr.begin(ShapeType.Filled);
			// sr.setColor(1,0,0,1);
			// sr.rect(rectx, recty, rectw, rectl);
			// sr.end();
		}
	}

	boolean sourceBlockConnect(int typeSet) {
		if (sourceD == 1 && GridBased.aboveType(_id - 1, _jd - 1) != typeSet) {
			return false;
		} else if (sourceD == 2
				&& GridBased.rightType(_id - 1, _jd - 1) != typeSet) {
			return false;
		} else if (sourceD == 3
				&& GridBased.leftType(_id - 1, _jd - 1) != typeSet) {
			return false;
		} else {
			return true;
		}
	}

	boolean swimming(float x, float y, float height, float width) {

		if (_type == 5 || _type == 8) {
			if (x >= _x - width && x <= _x + (BLOCKSIZE)) {
				if (y >= _y - height
						&& y <= _y
								+ (BLOCKSIZE * ((float) (range) / ((float) (LAVARANGE))))) {
					return true;
				}
			}
		}
		return false;
	}

	boolean damage(float x, float y, float dx, float dy, float size) {
		if (block == 1 && _type != 42 && damaged == false) {
			if (x >= _x - size && x <= _x + (BLOCKSIZE)) {
				if (y >= _y - size && y <= _y + (BLOCKSIZE)) {
					breakTimer += 100;
					if (breakTimer > breakLimit) {
						if(MyGdxGame.debris.size() < 20)
						{
							//Debris debris;
							//debris = new Debris(Img1.string, _x+ BLOCKSIZE / 2, _y + BLOCKSIZE / 2,dx / 2, dy / 2,(float) (Math.random() * 30 - 15));
							//MyGdxGame.debris.add(debris);
						}
						_type = 0;
						damaged = true;
					}
					return true;
				}
			}
		}
		return false;
	}

	void setRange(float x, float y, int _range) {

		if (x > _x && x < _x + BLOCKSIZE && y > _y && y < _y + BLOCKSIZE
				&& _range > range || x > _x && x < _x + BLOCKSIZE && y > _y
				&& y < _y + BLOCKSIZE && range == LAVARANGE + 1) {
			range = _range;
		}
	}
}