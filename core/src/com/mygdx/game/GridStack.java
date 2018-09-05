package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class GridStack extends XYControl{
//ShapeRenderer sr;
SpriteBatch batch;
ShapeRenderer sr;
ArrayList <Grid> Grids;
ArrayList<Integer> parts;
ArrayList<Integer> fuel;
ArrayList<Integer> cookTimer;
ArrayList<Integer> cookTimer2;
ArrayList<Boolean> signalUsed;
ArrayList<Integer> awsr;
ArrayList<Integer> awdct;
ArrayList<Integer> mj;
ArrayList <Integer> Type;
ArrayList <Integer> sourceD;
ArrayList<Integer> range;
ArrayList<Integer>tGrowth;
ArrayList<Boolean>imageFlip;
ArrayList<Boolean>pistonOut;
ArrayList<Boolean>eOn;
ArrayList<Integer>eOnChange;
ArrayList<Integer>eOnD;
ArrayList<Boolean>awd;
ArrayList<Integer>iType;
ArrayList<Integer>pistonAngle;
ArrayList<Integer>awdc;
ArrayList<ArrayList<Integer>>inventory;
ArrayList<Rain>rainList;
boolean done = false;
boolean registered = false;
boolean createdOnce = false;
int _x;
int _ix;
int rainHeight = 0;
int _id;
static public int HEIGHT = 90;
int k = 0;
int _y;
int timer = 0;
int _yHeight = 0;
int _yHeightID = 0;
int _lightCreated = 0;
BodyDef groundBodyDef = new BodyDef();
Body blockBody;
GridStack(SpriteBatch batch, float gridX, float current, float currentY, int id, boolean registered, ArrayList<Integer> Type2, ArrayList<Integer> Range2, ArrayList<Integer> SourceD2, ArrayList<Integer> TGrowth2
		, ArrayList<Integer>iType2, ArrayList<Integer>pistonAngle,ArrayList<Integer>awdc,ArrayList<Boolean>pistonOut,ArrayList<Boolean>imageFlip,ArrayList<Boolean>eOn,ArrayList<Boolean>awd,ArrayList<Integer>eOnD,
		ArrayList<Integer>eOnChange, ArrayList<Boolean>signalUsed,ArrayList<Integer>mj,ArrayList<Integer>cookTimer, ArrayList<Integer>cookTimer2,ArrayList<Integer>parts, ArrayList<Integer>fuel,
		ArrayList<Integer>awsr, ArrayList<Integer>awdct)
{
	this.batch = batch;
	sr = new ShapeRenderer();
	rainList = new ArrayList<Rain>();
	int rainY = 0;
	for(int i = 0; i < 100; i++)
	{
		Rain rain;
		rain = new Rain((int)(Math.random() * Grid.BLOCKSIZE), rainY + (int)(Math.random() * 10));
		rainY += (1300/100);
		rainList.add(rain);
	}
	this.awsr = new ArrayList<Integer>();
	this.awdct = new ArrayList<Integer>();
	this.parts = new ArrayList<Integer>();
	this.fuel = new ArrayList<Integer>();
	this.cookTimer = new ArrayList<Integer>();
	this.cookTimer2 = new ArrayList<Integer>();
	this.mj = new ArrayList<Integer>();
	this.signalUsed = new ArrayList<Boolean>();
	this.imageFlip = new ArrayList<Boolean>();
	this.pistonOut = new ArrayList<Boolean>();
	this.awdc = new ArrayList<Integer>();
	this.awd = new ArrayList<Boolean>();
	this.eOnD = new ArrayList<Integer>();
	this.eOn = new ArrayList<Boolean>();
	this.eOnChange = new ArrayList<Integer>();
	Type = new ArrayList<Integer>();
	sourceD = new ArrayList<Integer>();
	Grids = new ArrayList<Grid>();
	range = new ArrayList<Integer>();
	tGrowth = new ArrayList<Integer>();
	iType = new ArrayList<Integer>();
	this.pistonAngle = new ArrayList<Integer>();
	inventory = new ArrayList<ArrayList<Integer>>();
	_ix = Math.round (gridX );
	_y = Math.round (0 - currentY);
	_id = id;
	
	if(Type2 != null)
	{
		this.awsr = awsr;
		this.awdct = awdct;
		this.eOn = eOn;
		this.eOnD = eOnD;
		this.eOnChange = eOnChange;
		this.awd = awd;
		this.pistonOut = pistonOut;
		this.imageFlip = imageFlip;
		this.awdc = awdc;
		this.pistonAngle = pistonAngle;
		this.registered = true;
		this.Type = Type2;
		this.range = Range2;
		this.sourceD = SourceD2;
		this.tGrowth = TGrowth2;
		this.iType = iType2;
		this.signalUsed = signalUsed;
		this.mj = mj;
		this.cookTimer = cookTimer;
		this.cookTimer2 = cookTimer2;
		this.parts = parts;
		this.fuel = fuel;
	}
}

ArrayList<Grid>Grids()
{
	return Grids;
}
void sunBlock()
{
	/*
	for (int i = 0; i < Grids.size()-1; i ++)
	{
		if (i > 0 && Grids.get(i - 1).block != 0 && Grids.get(i -1)._type != 7 && Grids.get(i -1)._type != 14 && GridBased.surroundCheck(_id - 1, i - 1) == false)
		{
			_yHeight = Grids.get(i)._y;
			_yHeightID = i;
		}
	}	
	*/
}
void draw(float dx, float dy, float currentY)
{
	_x = (int) (_ix - DirectionControl.current);
	if(MyGdxGame.timer == 1 && MyGdxGame.load)
	{
		for(int i =0;i <iType.size();i++)
		{
			if (iType.get(i) > 0)
			{
				inventory.add(SaveManager.gridInventory.get(MyGdxGame.gi));
				MyGdxGame.gi ++;
			}
		}
	}
	if (Visible(_x, 100,1, 1500, 10000))
	{
		//System.out.println(_x);
		if (registered == false)
		{
			if (_ix > 0)
			{
			for (int i = 0; i < HEIGHT; i++)
			{
				if (GridManager.refreshed == false && MyGdxGame.load == true)
				{
					Type.add(SaveManager.type.get(i));
				}
				else
				{
					Type.add(GridManager.Type.get(i));
				}
				pistonOut.add(false);
				imageFlip.add(false);
				sourceD.add(0);
				pistonAngle.add(0);
				range.add(Grid.LAVARANGE + 1);
				tGrowth.add(0);
				iType.add(0);
				awdc.add(0);
				awd.add(false);
				eOn.add(false);
				eOnChange.add(20);
				eOnD.add(0);
				parts.add(0);
				fuel.add(0);
				mj.add(0);
				signalUsed.add(false);
				cookTimer.add(10000);
				cookTimer2.add(20000);
				awsr.add(0);
				awdct.add(0);
			}
			}else
			{
				for (int i = 0; i < HEIGHT; i++)
				{
					if(GridManager2.refreshed == false && MyGdxGame.load == true)
					{
						Type.add(SaveManager.type2.get(i));
					}
					else
					{
						Type.add(GridManager2.Type.get(i));
					}
					pistonOut.add(false);
					imageFlip.add(false);
					awdc.add(0);
					sourceD.add(0);
					pistonAngle.add(0);
					range.add(Grid.LAVARANGE + 1);
					tGrowth.add(0);
					iType.add(0);
					awd.add(false);
					eOn.add(false);
					eOnChange.add(20);
					eOnD.add(0);
					parts.add(0);
					fuel.add(0);
					mj.add(0);
					signalUsed.add(false);
					cookTimer.add(10000);
					cookTimer2.add(20000);
					awsr.add(0);
					awdct.add(0);
				}
			}
			
			
				
		if (_ix > 0)
		{
			GridManager.refresh();
		}
		else
		{
			GridManager2.refresh();
		}
		registered = true;
		}
		
		timer ++;
	if (timer < HEIGHT && Grids.size()<HEIGHT-1)
	{
	
		_y = (int) (((timer * Grid.BLOCKSIZE)) - HEIGHT * Grid.BLOCKSIZE);
		Grid grid;
		grid = new Grid (batch, _x, _y, Type.get(timer - 1), _id, timer, sourceD.get(timer-1),range.get(timer-1),tGrowth.get(timer - 1),null,pistonAngle.get(timer-1),awdc.get(timer-1),pistonOut.get(timer-1),imageFlip.get(timer-1),
				eOn.get(timer-1),awd.get(timer-1),eOnD.get(timer-1),eOnChange.get(timer -1),signalUsed.get(timer-1),mj.get(timer-1),cookTimer.get(timer-1),cookTimer2.get(timer-1),parts.get(timer-1),fuel.get(timer-1)
				,awsr.get(timer-1),awdct.get(timer-1));
		if (iType.get(timer - 1) > 0 && createdOnce == false)
		{
			grid = new Grid (batch, _x, _y, Type.get(timer - 1), _id, timer, sourceD.get(timer-1),range.get(timer-1),tGrowth.get(timer - 1), inventory.get(k),pistonAngle.get(timer-1),awdc.get(timer-1),pistonOut.get(timer-1),imageFlip.get(timer-1),
					eOn.get(timer-1),awd.get(timer-1),eOnD.get(timer-1),eOnChange.get(timer -1),signalUsed.get(timer-1),mj.get(timer-1),cookTimer.get(timer-1),cookTimer2.get(timer-1),parts.get(timer-1),fuel.get(timer-1)
					,awsr.get(timer-1),awdct.get(timer-1));
			k++;
		}
		else
		{
			grid = new Grid (batch, _x, _y, Type.get(timer - 1), _id, timer, sourceD.get(timer-1),range.get(timer-1),tGrowth.get(timer - 1), null,pistonAngle.get(timer-1),awdc.get(timer-1),pistonOut.get(timer-1),imageFlip.get(timer-1),
					eOn.get(timer-1),awd.get(timer-1),eOnD.get(timer-1),eOnChange.get(timer -1),signalUsed.get(timer-1),mj.get(timer-1),cookTimer.get(timer-1),cookTimer2.get(timer-1),parts.get(timer-1),fuel.get(timer-1)
					,awsr.get(timer-1),awdct.get(timer-1));
		}
		Grids.add(grid);
	}
	else
	{
		createdOnce = true;
		done = true;
	}
	batch.begin();
	for (int i = 0;i < Grids.size(); i++) {
		Grids.get(i).draw(_x, _yHeightID());
	}
	batch.end();
	if (Grids.size() >= HEIGHT - 1)
	{
		
		for (int i = 0; i < HEIGHT - 1; i++)
		{
			Type.set(i,Grids.get(i)._type);
			
				sourceD.set(i, Grids.get(i).sourceD);
				range.set(i,Grids.get(i).range);
				tGrowth.set(i, Grids.get(i).growthT);
				iType.set(i, Grids.get(i).iType);
				pistonAngle.set(i,Grids.get(i).pistonAngle);
				awdc.set(i,Grids.get(i).awdc);
				pistonOut.set(i,Grids.get(i).pistonOut);
				imageFlip.set(i, Grids.get(i).imageFlip);
				awd.set(i, Grids.get(i).awd);
				eOn.set(i, Grids.get(i).eOn);
				eOnD.set(i, Grids.get(i).eOnD);
				eOnChange.set(i,Grids.get(i).eOnChange);
				signalUsed.set(i,Grids.get(i).signalUsed);
				mj.set(i, (int) Grids.get(i).mj);
				cookTimer.set(i,Grids.get(i).cookingTimer);
				cookTimer2.set(i,Grids.get(i).cookingTimer2);
				parts.set(i,Grids.get(i).parts);
				fuel.set(i,Grids.get(i).fuel);
				awsr.set(i,Grids.get(i).awsr);
				awdct.set(i,Grids.get(i).awdct);
		}
	}
	if(done)
	{
		boolean snowing;
		if(MyGdxGame.weatherMode == 2)
		{
			snowing = true;
		}
		else
		{
			snowing = false;
		}
		batch.begin();
		GridBased.snowLayerCreate(_id-1,rainID(),snowing);
		batch.end();
	}
	//snow();
	//rain();
	}
	else if(done)
	{
		done = false;
		/*
		for (int i = 0; i < Grids.size();i++)
		{
			if (Grids.get(i)._type != 12 && Grids.get(i)._type != 13&& Grids.get(i)._type != 24&& Grids.get(i)._type != 20&& Grids.get(i)._type != 33 && Grids.get(i)._type != 15)
			{
				
				Grids.remove(i);	
			}
			
		}
		*/
		Grids.clear();
		timer = 0;
		k = 0;
	}
	
	for (int i = 0;i < Grids.size(); i++) {
		if (Grids.get(i)._type == 33 || Grids.get(i)._type == 34|| Grids.get(i)._type == 35)
		{
			Grids.get(i).draw(_x, _yHeightID());
		}
	}
	if (timer >= HEIGHT && Visible(_x, 200,1, -15, 10000) )
	{
		sunBlock();
	}
}
void drawRect()
{
	for (int i = 0;i < Grids.size(); i++) {
		Grids.get(i).drawRect();
	}
}
void createLight()
{
}
void updateCords()
{
	_x = (int) (_ix - DirectionControl.current);
	for (int i = 0;i < Grids.size(); i++) {
		Grids.get(i).updateCords(_x);
	}
}
void destroyLight()
{
	for (int i = 0;i < Grids.size(); i++) {
		Grids.get(i).destroyLight();
		}
	if (_lightCreated == 1)
	{
		LightManager.world.destroyBody(blockBody);
		
		_lightCreated = 0;
	}
		
}
int rainY()
{
	
	for(int i = Grids.size(); i > 2; i --)
	{
		if ( i > 0 && Grids.get(i-1).block != 0||Grids.get(i-1)._type == 8)
		{
			if(Grids.get(i-1)._y != 2126)
			{
				if(Grids.get(i-1)._type != 0)
				{
					return Grids.get(i-1)._y + Grid.BLOCKSIZE;
				}
			}
		}
	}
	
	return 0;
}
int rainID()
{
	
	for(int i = Grids.size(); i > 2; i --)
	{
		if(Grids.get(i-1)._type != 0)
		{
		if (Grids.size() > i && i > 0 && (Grids.get(i-1).block != 0 ||Grids.get(i-1)._type == 8||Grids.get(i)._type == 51||Grids.get(i)._type==52||Grids.get(i)._type==53))
		{
			if(Grids.get(i-1)._y != 2126)
			{
				if(Grids.get(i-1)._type != 43)
				{
					return i;
				}
			}
		}
		}
	}
	
	return 88;
}
int _yHeightID()
{
	if(done && Visible(_x, 100,1, 0, 10000))
	{
	for(int i = Grids.size() - 3; i > 2; i --)
	{
		if ( i > 0 && Grids.get(i-1).block != 0 && GridBased.surroundCheck(_id - 1, i-2) == false)
		{
			if(Grids.get(i-1)._type != 0)
			{
				return i -1;
			}
			}
		}
	}
	return 0;
}
void snow()
{
	sr.begin(ShapeType.Filled);
	sr.setColor(1,1,1,1);
	for(int i = 0; i < rainList.size(); i++)
	{
		rainList.get(i).draw(5);
		if(rainList.get(i).totalY() > rainY())
		{
			sr.rect(rainList.get(i).totalX() + _x, rainList.get(i).totalY(), 3 + (int)(Math.random() * 4), 3 + (int)(Math.random() * 4));
		}
		if(rainList.get(i).totalY() < 0)
		{
			rainList.get(i).resetRain();
		}
	}
	sr.end();
	
}
void rain()
{ 
	sr.begin(ShapeType.Filled);
	sr.setColor(0,1,1,1);
	for(int i = 0; i < rainList.size(); i++)
	{
		rainList.get(i).draw(12);
		if(rainList.get(i).totalY() > rainY())
		{
			sr.rect(rainList.get(i).totalX() + _x, rainList.get(i).totalY(), 3, 5);
		}
		if(rainList.get(i).totalY() < 0)
		{
			rainList.get(i).resetRain();
		}
	}
	sr.end();
}
}
