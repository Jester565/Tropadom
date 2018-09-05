package com.mygdx.game;

import java.util.ArrayList;
import java.util.Arrays;

public class GridBased {
	int _i;
	
	ArrayList <GridStack>_gridStack;
	ArrayList <Grid>_grids;
	static ArrayList<Integer>solidTypes;
	MyGdxGame mgdx;
GridBased()
{
	//solidTypes = new ArrayList<Integer>(Arrays.asList(1, 2, 3,6, 10,12, 9,15,16,17,18,19,20,23,24,26,30,31));
	solidTypes = new ArrayList<Integer>(Arrays.asList(1,2,3));
}

public static boolean rightStop(float x,float y,float width,float height)
{
	for (int i = 0; i <  MyGdxGame.gridStack.size(); i++) {
		if(MyGdxGame.gridStack.get(i).done)
		{
		for(int j = 0; j < 89; j++){
			if ( MyGdxGame.gridStack.get(i).Grids().get(j).rightStop(x,y,width,height))
			{
				return true;
			}
		}
		}
	}
		
	return false;
}
public static boolean leftStop(float x,float y,float width,float height)
{
	for (int i = 0; i <  MyGdxGame.gridStack.size(); i++) {
		if(MyGdxGame.gridStack.get(i).done)
		{
		for(int j = 0; j <  89; j++){
			if ( MyGdxGame.gridStack.get(i).Grids().get(j).leftStop(x,y,width,height))
			{
				return true;
			}
		}
		}
	}
		
	return false;
}
public static boolean downStop(float x,float y,float width,float height)
{
	for (int i = 0; i <  MyGdxGame.gridStack.size(); i++) {
		if(MyGdxGame.gridStack.get(i).done)
		{
		for(int j = 0; j <  89; j++){
			if ( MyGdxGame.gridStack.get(i).Grids().get(j).downStop(x,y,width,height))
			{
				return true;
			}
		}
		}
	}
		
	return false;
}
public static int downStopHeight(float x,float y,float width,float height)
{
	for (int i = 0; i <  MyGdxGame.gridStack.size(); i++) {
		if(MyGdxGame.gridStack.get(i).done)
		{
		for(int j = 0; j <  89; j++){
			if ( MyGdxGame.gridStack.get(i).Grids().get(j).downStopHeight(x,y,width,height) != 0)
			{
				return MyGdxGame.gridStack.get(i).Grids().get(j).downStopHeight(x,y,width,height);
			}
		}
		}
	}
		
	return 0;
}
public static void snowLayerCreate(int id, int jd, boolean snowing)
{
	if(MyGdxGame.gridStack.get(id).done);
	{
		MyGdxGame.gridStack.get(id).Grids().get(jd).snowLayerCreate(snowing);
	}
}
public static Inventory inventory2()
{
	for (int i = 0; i < MyGdxGame.gridStack.size(); i++) {
		if(MyGdxGame.gridStack.get(i).done)
		{
		for(int j = 0; j < 89; j++){
			if (MyGdxGame.gridStack.get(i).Grids().get(j).inventory2() != null && MyGdxGame.gridStack.get(i).Grids().get(j).inventoryOn != 0)
			{
				return MyGdxGame.gridStack.get(i).Grids().get(j).inventory2();
			}
		}
		}
	}
	return null;
}
public static int inventoryOn()
{
	for (int i = 0; i < MyGdxGame.gridStack.size(); i++) {
		if(MyGdxGame.gridStack.get(i).done)
		{
		for(int j = 0; j < 89; j++){
			if (MyGdxGame.gridStack.get(i).Grids().get(j).inventoryOn != 0)
			{
				return MyGdxGame.gridStack.get(i).Grids().get(j).inventoryOn;
			}
		}
		}
	}
	return 0;
}
public static boolean awd()
{
	for (int i = 0; i < MyGdxGame.gridStack.size(); i++) {
		if(MyGdxGame.gridStack.get(i).done)
		{
		for(int j = 0; j < 89; j++){
			if (MyGdxGame.gridStack.get(i).Grids().get(j).inventoryOn == 6)
			{
				return MyGdxGame.gridStack.get(i).Grids().get(j).awd;
			}
		}
		}
	}
	return false;
}
public static boolean signalUsed()
{
	for (int i = 0; i < MyGdxGame.gridStack.size(); i++) {
		if(MyGdxGame.gridStack.get(i).done)
		{
		for(int j = 0; j < 89; j++){
			if (MyGdxGame.gridStack.get(i).Grids().get(j).inventoryOn > 0)
			{
				return MyGdxGame.gridStack.get(i).Grids().get(j).signalUsed;
			}
		}
		}
	}
	return false;
}
public static int awdc()
{
	for (int i = 0; i < MyGdxGame.gridStack.size(); i++) {
		if(MyGdxGame.gridStack.get(i).done)
		{
		for(int j = 0; j < 89; j++){
			if (MyGdxGame.gridStack.get(i).Grids().get(j).inventoryOn == 6)
			{
				return MyGdxGame.gridStack.get(i).Grids().get(j).awdc;
			}
		}
		}
	}
	return 0;
}
public static boolean upStop(float x,float y,float width,float height)
{
	for (int i = 0; i <  MyGdxGame.gridStack.size(); i++) {
		if(MyGdxGame.gridStack.get(i).done)
		{
		for(int j = 0; j <  89; j++){
			if ( MyGdxGame.gridStack.get(i).Grids().get(j).upStop(x,y,width,height))
			{
				return true;
			}
		}
		}
	}
		
	return false;
}
public static boolean sightBlocked(float x, float y)
{
	for (int i = 0; i <  MyGdxGame.gridStack.size(); i++) {
		if(MyGdxGame.gridStack.get(i).done)
		{
		for(int j = 0; j <  89; j++){
			if ( MyGdxGame.gridStack.get(i).Grids().get(j).sightBlocked(x,y))
			{
				return true;
			}
		}
		}
	}
		
	return false;
}
void destroy(float x, float y, float width,float height, boolean active, boolean protagonist)
{
	_gridStack = MyGdxGame.gridStack;
	for (int i = 0; i < _gridStack.size(); i++) {
		if(MyGdxGame.gridStack.get(i).done)
		{
		for(int j = 0; j < 89; j++){
			_gridStack.get(i).Grids().get(j).destroy(x,y,width,height,active, protagonist);
			
		}
		}
	}	
}
public static void changeBlock(int id, int jd,int type)
{
	if (id > 0 && id < MyGdxGame.gridStack.size() && jd > 0 && jd < MyGdxGame.gridStack.get(id).Grids().size()) {
		MyGdxGame.gridStack.get(id).Grids().get(jd).destroyID();
		MyGdxGame.gridStack.get(id).Grids().get(jd).createID(type);
	}
}
public static void setPistonAngle(int id, int jd,int angle)
{
	MyGdxGame.gridStack.get(id).Grids().get(jd).pistonAngle = angle;
}
public static boolean swimming(float x, float y, float width,float height)
{
	for (int i = 0; i <  MyGdxGame.gridStack.size(); i++) {
		if(MyGdxGame.gridStack.get(i).done)
		{
		for(int j = 0; j <  89; j++){
			if ( MyGdxGame.gridStack.get(i).Grids().get(j).swimming(x,y,height, width))
			{
				return true;
			}
			
		}
		}
	}	
	return false;
}
public static boolean damage(float x, float y, float dx, float dy,float size)
{
	for (int i = 0; i <  MyGdxGame.gridStack.size(); i++) {
		if(MyGdxGame.gridStack.get(i).done)
		{
		for(int j = 0; j <  89; j++){
			if(MyGdxGame.gridStack.get(i).Grids().get(j).damage(x,y, dx, dy, size))
			{
				return true;
			}
		}
		}
	}	
	return false;
}
public static int fuel()
{
	for (int i = 0; i < MyGdxGame.gridStack.size(); i++) {
		if(MyGdxGame.gridStack.get(i).done)
		{
		for(int j = 0; j < 89; j++){
			if (MyGdxGame.gridStack.get(i).Grids().get(j).fuel != 0 && MyGdxGame.gridStack.get(i).Grids().get(j).inventoryOn != 0)
			{
				return MyGdxGame.gridStack.get(i).Grids().get(j).fuel;
			}
			
		}
		}
	}	
	return 0;
}
public static float mjCount()
{
	for (int i = 0; i < MyGdxGame.gridStack.size(); i++) {
		if(MyGdxGame.gridStack.get(i).done)
		{
		for(int j = 0; j < 89; j++){
			if (MyGdxGame.gridStack.get(i).Grids().get(j).inventoryOn >= 4)
			{
				return MyGdxGame.gridStack.get(i).Grids().get(j).mj;
			}
			
		}
		}
	}	
	return 0;
}
public static int cookingTimer()
{
	for (int i = 0; i < MyGdxGame.gridStack.size(); i++) {
		if(MyGdxGame.gridStack.get(i).done)
		{
		for(int j = 0; j < 89; j++){
			if (MyGdxGame.gridStack.get(i).Grids().get(j).inventoryOn != 0)
			{
				return MyGdxGame.gridStack.get(i).Grids().get(j).cookingTimer;
			}
			
		}
		}
	}	
	return 0;
}
public static boolean cooking()
{
	for (int i = 0; i < MyGdxGame.gridStack.size(); i++) {
		if(MyGdxGame.gridStack.get(i).done)
		{
		for(int j = 0; j < 89; j++){
			if (MyGdxGame.gridStack.get(i).Grids().get(j).cooking)
			{
				return true;
			}
		}
		}
	}	
	return false;
}
public static void setFuel(int type)
{
	for (int i = 0; i < MyGdxGame.gridStack.size(); i++) {
		if(MyGdxGame.gridStack.get(i).done)
		{
		for(int j = 0; j < 89; j++){
			if (MyGdxGame.gridStack.get(i).Grids().get(j).fuel == 0 && MyGdxGame.gridStack.get(i).Grids().get(j).inventoryOn != 0)
			{
				MyGdxGame.gridStack.get(i).Grids().get(j).fuel = type;
			}
			
		}
	}
	}
}
public static int parts()
{
	for (int i = 0; i < MyGdxGame.gridStack.size(); i++) {
		if(MyGdxGame.gridStack.get(i).done)
		{
		for(int j = 0; j < 89; j++){
			if (MyGdxGame.gridStack.get(i).Grids().get(j).parts != 0 && MyGdxGame.gridStack.get(i).Grids().get(j).inventoryOn != 0)
			{
				return MyGdxGame.gridStack.get(i).Grids().get(j).parts;
			}
			
		}
	}	
	}
	return 0;
}
public static void setPart(int type)
{
	for (int i = 0; i < MyGdxGame.gridStack.size(); i++) {
		if(MyGdxGame.gridStack.get(i).done)
		{
		for(int j = 0; j < 89; j++){
			if (MyGdxGame.gridStack.get(i).Grids().get(j).parts == 0 && MyGdxGame.gridStack.get(i).Grids().get(j).inventoryOn != 0)
			{
				MyGdxGame.gridStack.get(i).Grids().get(j).parts = type;
			}
			
		}
	}
	}
}
public static void InventoryOff()
{
	for (int i = 0; i < MyGdxGame.gridStack.size(); i++) {
		if(MyGdxGame.gridStack.get(i).done)
		{
		for(int j = 0; j < 89; j++){
			if (MyGdxGame.gridStack.get(i).Grids().get(j).inventoryOn != 0)
			{
				MyGdxGame.gridStack.get(i).Grids().get(j).inventoryOn = 0;
			}
			
		}
		}
	}	
}
public static void create(float x, float y,  boolean active,int type,float xRic, float yRic,float wRic, float hRic, boolean ricOff, boolean protagonist)
{
	
	for (int i = 0; i < MyGdxGame.gridStack.size(); i++) {
		if(MyGdxGame.gridStack.get(i).done)
		{
		for(int j = 0; j < 89; j++){
			MyGdxGame.gridStack.get(i).Grids().get(j).create(x,y,active,type,xRic,yRic,wRic,hRic, ricOff, protagonist);
			
		}
	}
	}
	
}
public static int insideType(float x, float y)
{

	for (int i = 0; i < MyGdxGame.gridStack.size(); i++) {
		if(MyGdxGame.gridStack.get(i).done)
		{
		for(int j = 0; j < 89; j++){
			if (MyGdxGame.gridStack.get(i).Grids().get(j).insideType(x,y) != -1)
			{
			return MyGdxGame.gridStack.get(i).Grids().get(j).insideType(x,y);
			}
			
		}
		}
	}
	return -1;
}
public static boolean inputInventoryCheck(float x, float y, int type,int amount)
{
	for (int i = 0; i < MyGdxGame.gridStack.size(); i++) {
		if(MyGdxGame.gridStack.get(i).done)
		{
		for(int j = 0; j < 89; j++){
			if (MyGdxGame.gridStack.get(i).Grids().get(j).inputInventoryCheck(x,y,type,amount) != false)
			{
				return true;
			}
		}
			
		}
	}
	return false;
}
public static int insideX(float x, float y)
{

	for (int i = 0; i < MyGdxGame.gridStack.size(); i++) {
		if(MyGdxGame.gridStack.get(i).done)
		{
		for(int j = 0; j < 89; j++){
			if (MyGdxGame.gridStack.get(i).Grids().get(j).insideX(x,y) != -1)
			{
			return MyGdxGame.gridStack.get(i).Grids().get(j).insideX(x,y);
			}
		}
			
		}
	}
	return -1;
}
public static int insideY(float x, float y)
{

	for (int i = 0; i < MyGdxGame.gridStack.size(); i++) {
		if(MyGdxGame.gridStack.get(i).done)
		{
		for(int j = 0; j < 89; j++){
			if (MyGdxGame.gridStack.get(i).Grids().get(j).insideY(x,y) != -1)
			{
			return MyGdxGame.gridStack.get(i).Grids().get(j).insideY(x,y);
			}
			
		}
		}
	}
	return -1;
}
public static int pipeD(float x, float y, int currentD, boolean transitionReady)
{
	
	for (int i = 0; i < MyGdxGame.gridStack.size(); i++) {
		if(MyGdxGame.gridStack.get(i).done)
		{
		for(int j = 0; j < 89; j++){
			if (MyGdxGame.gridStack.get(i).Grids().get(j).stonePipeD(x,y,currentD, transitionReady) != 0)
			{
			return MyGdxGame.gridStack.get(i).Grids().get(j).stonePipeD(x,y, currentD, transitionReady);
			}
			
		}
		}
	}
	return 0;
}
public static boolean inLadder(float x, float y, float width, float height)
{
	
	for (int i = 0; i < MyGdxGame.gridStack.size(); i++) {
		if(MyGdxGame.gridStack.get(i).done)
		{
		for(int j = 0; j < 89; j++){
			if (MyGdxGame.gridStack.get(i).Grids().get(j).inLadder(x,y,width, height) != false)
			{
				return MyGdxGame.gridStack.get(i).Grids().get(j).inLadder(x,y,width, height);
			}
			
		}
		}
	}
	return false;
}
public static void setRange(int id, int jd, int range,boolean xOn, float x, float y)
{
	if (xOn == false)
	{
		if(range > MyGdxGame.gridStack.get(id).Grids().get(jd).range ||  MyGdxGame.gridStack.get(id).Grids().get(jd).range == Grid.LAVARANGE + 1)
		{
			MyGdxGame.gridStack.get(id).Grids().get(jd).range = range;
			MyGdxGame.gridStack.get(id).Grids().get(jd).liquidPlaceTimerB = 0;
			MyGdxGame.gridStack.get(id).Grids().get(jd).liquidPlaceTimerL = 0;
			MyGdxGame.gridStack.get(id).Grids().get(jd).liquidPlaceTimerR = 0;
		}
	}
	else
	{
		for (int i = 0; i < MyGdxGame.gridStack.size(); i++) {
			for(int j = 0; j < 89; j++){
				MyGdxGame.gridStack.get(i).Grids().get(j).setRange(x,y,range);
				
			}
		}
	}
}
public static int getRange(int id, int jd)
{
	return MyGdxGame.gridStack.get(id).Grids().get(jd).range;
}
public static void setSourceD(int id, int jd, int direction)
{
	MyGdxGame.gridStack.get(id).Grids().get(jd).sourceD = direction;
}
static public boolean surroundCheck(int id, int jd)
{
	
	if (id != 0 && jd > 0 && jd < GridStack.HEIGHT && id != MyGdxGame.worldWidth -1 && id != MyGdxGame.worldWidth&&id != MyGdxGame.worldWidth*2 -1 && id != MyGdxGame.worldWidth*2)
	{
	if (isSolid(id,jd+1) 
			&& isSolid(id,jd-1) 
			&& isSolid(id - 1,jd)
			&& isSolid(id+1,jd)
			&& isSolid(id + 1,jd + 1)
			&& isSolid(id + 1,jd -1)
			&& isSolid(id-1,jd+1)
			&& isSolid(id-1,jd-1))
		{
			return false;
		}
	}
	return true;
}
static public boolean isSolid(int id, int jd)
{
		if(MyGdxGame.gridStack.get(id).done == true && MyGdxGame.gridStack.get(id).Grids().get(jd).block == 1)
		{
			return true;
		}
	return false;
}
static public void setShadow(int id, int jd, float h, float shadowAlphaMin)
{
	for(int i = 0; i < (int)(h/2); i++)
	{
		if (id > 1 && jd > 0 && jd < GridStack.HEIGHT - 2)
		{
			if(MyGdxGame.gridStack.get(id).Grids.get(jd + i).shadowAlpha > i/(h/2)+shadowAlphaMin)
			{
				MyGdxGame.gridStack.get(id).Grids().get(jd + i).shadowAlpha -= (1-(i/(h/2) + shadowAlphaMin));
			}
			else if (MyGdxGame.gridStack.get(id).Grids.get(jd + i).shadowAlpha <= 0)
			{
				//MyGdxGame.gridStack.get(id).Grids().get(jd + i).setLightAlpha(i/(h/2)+shadowAlphaMin);
			}
			if(GridBased.surroundCheck(id, jd + i + 1) == false)
			{
				break;
			}
		}
	}
	for(int i = 0; i < (int)(h/2); i++)
	{
		if (id > 1 && jd > 0 && jd < GridStack.HEIGHT - 2)
		{
			if(MyGdxGame.gridStack.get(id).Grids.get(jd - i).shadowAlpha > i/(h/2)+shadowAlphaMin)
			{
				MyGdxGame.gridStack.get(id).Grids().get(jd - i).shadowAlpha -= (1 - (i/(h/2) + shadowAlphaMin));
			}
			else if (MyGdxGame.gridStack.get(id).Grids.get(jd - i).shadowAlpha <= 0)
			{
				//MyGdxGame.gridStack.get(id).Grids().get(jd - i).setLightAlpha(i/(h/2)+shadowAlphaMin);
			}
			if(GridBased.surroundCheck(id, jd - i - 1) == false)
			{
				break;
			}
		}
	}
}
static public int getSourceD(int id, int jd)
{
	if (id > 0 && jd > 0 && jd < GridStack.HEIGHT - 2)
	{
		return MyGdxGame.gridStack.get(id).Grids().get(jd).sourceD;	
	}
	else
	{
		return 0;
	}
}
static public boolean getawd(int id, int jd)
{
	if (id > 0 && jd > 0 && jd < GridStack.HEIGHT - 2)
	{
		return MyGdxGame.gridStack.get(id).Grids().get(jd).awd;	
	}
	else
	{
		return false;
	}
}
static public int aboveType(int id, int jd)
{
	if (id > 0 && jd > 0 && jd + 1 < GridStack.HEIGHT - 2 && id < MyGdxGame.gridStack.size())
	{
		if (MyGdxGame.gridStack.get(id).Grids().size() > jd + 1) {
			return MyGdxGame.gridStack.get(id).Grids().get(jd + 1)._type;
		}
		return 0;
	}
	else
	{
		return 0;
	}

}
static public int belowType(int id, int jd)
{
	if (id > 0 && jd > 0 && jd < GridStack.HEIGHT - 2)
	{
		return MyGdxGame.gridStack.get(id).Grids().get(jd - 1)._type;
		
			
		}
	else
	{
		return 0;
	}

}
static public int rightType(int id, int jd)
{
	if (id > 0 && jd > 0 && jd < GridStack.HEIGHT - 2 && id + 1 < MyGdxGame.gridStack.size())
	{
		return MyGdxGame.gridStack.get(id + 1).Grids().get(jd)._type;
		
			
		}
	else
	{
		return 0;
	}
}
static public int leftType(int id,int jd)
{
	if (id > 0 && jd > 0 && jd < GridStack.HEIGHT - 2)
	{

				return MyGdxGame.gridStack.get(id - 1).Grids().get(jd )._type;
			
		}
	else
	{
		return 0;
	}
}
static public int pipeUse(int id, int jd)
{
	if (id > 0 && jd > 0 && jd < GridStack.HEIGHT - 2)
	{
		return MyGdxGame.gridStack.get(id).Grids().get(jd).pipeUse;
	}
	return 0;
}
static public int wireUse(int id, int jd)
{
	if (id > 0 && jd > 0 && jd < GridStack.HEIGHT - 2)
	{
		return MyGdxGame.gridStack.get(id).Grids().get(jd).wireUse;
	}
	return 0;
}
static public boolean energyUse(int id, int jd)
{
	if (id > 0 && jd > 0 && jd < GridStack.HEIGHT - 2)
	{
		return MyGdxGame.gridStack.get(id).Grids().get(jd).energyUse;
	}
	return false;
}
static public void addMj(int id, int jd, float mj, int direction)
{
	if (id > 0 && jd > 0 && jd < GridStack.HEIGHT - 2)
	{
		MyGdxGame.gridStack.get(id).Grids().get(jd).addMj(mj,direction);
	}	
}
static public float getMj(int id, int jd)
{
	if (id > 0 && jd > 0 && jd < GridStack.HEIGHT - 2)
	{
		return MyGdxGame.gridStack.get(id).Grids().get(jd).mj;
	}
	return 0;
}

static public int getBlock(int id, int jd)
{
	if (id > 0 && jd > 0 && jd < GridStack.HEIGHT - 2)
	{
		return MyGdxGame.gridStack.get(id).Grids().get(jd).block;
	}
	return 0;
}
static public boolean getImageFlip(int id, int jd)
{
	if (id > 0 && jd > 0 && jd < GridStack.HEIGHT - 2)
	{
		return MyGdxGame.gridStack.get(id).Grids().get(jd).imageFlip;
	}
	return false;
}
static public boolean geteOn(int id, int jd)
{
	if (id > 0 && jd > 0 && jd < GridStack.HEIGHT - 2)
	{
		return MyGdxGame.gridStack.get(id).Grids().get(jd).eOn;
	}
	return false;
}
static public boolean geteOnWire(int id, int jd)
{
	if (id > 0 && jd > 0 && jd < GridStack.HEIGHT - 2)
	{
		return MyGdxGame.gridStack.get(id).Grids().get(jd).eOnWire;
	}
	return false;
}
static public ArrayList fuelAccept()
{
	for (int i = 0; i < MyGdxGame.gridStack.size(); i++) {
		if(MyGdxGame.gridStack.get(i).done)
		{
		for(int j = 0; j < 89; j++){
			if (MyGdxGame.gridStack.get(i).Grids().get(j).inventoryOn != 0)
			{
				return MyGdxGame.gridStack.get(i).Grids().get(j).acceptFuel;
			}
			
		}
		}
	}	
	return null;
}
static public ArrayList partAccept()
{
	for (int i = 0; i < MyGdxGame.gridStack.size(); i++) {
		if(MyGdxGame.gridStack.get(i).done)
		{
		for(int j = 0; j < 89; j++){
			if (MyGdxGame.gridStack.get(i).Grids().get(j).inventoryOn != 0)
			{
				return MyGdxGame.gridStack.get(i).Grids().get(j).acceptParts;
			}
			
		}
		}
	}	
	return null;
}
}


	

