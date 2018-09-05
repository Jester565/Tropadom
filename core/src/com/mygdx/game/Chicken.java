package com.mygdx.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Chicken {
	SpriteBatch batch = new SpriteBatch();
	ArrayList<Integer> canSkin;
	ArrayList<Integer> alertItems;
	HashMap<Integer,Integer> playerRecord;
	int followTimer = -10;
	boolean spot = false;
	boolean destroy = false;
	boolean losVisible = false;
	int headAngleTimer = 100;
	boolean topScan = false;
	int detectionMeter = 0;
	int scanReady = 0;
	double scanAngle = 0;
	boolean headRight = false;
	boolean detected = false;
	boolean grudge = false;
	float x = 0;
	float y = 0;
	float headAngle = 0;
	float width = 35;
	float height = 32;
	float rdx = 0;
	float rdy = 0;
	float iy;
	int losSwitchTimer = 0;
	float ix;
	int projectileShotTimer = 0;
	float speed = 0;
	float dy = 0;
	float dx = 0;
	int mass = 8;
	boolean skinBegin = false;
	float legAngle = 0;
	boolean legMoveRight = false;
	int headD = 0;
	int direction = 1;
	int wanderTimer = 100;
	int wanderRandom = 0;
	int health = 40;
	float torsoAngleChange = 0;
	float torsoAngle = 0;
	Sprite torso = new Sprite();
	Sprite leg1 = new Sprite();
	Sprite leg2 = new Sprite();
	Sprite head = new Sprite();
	Chicken(float ix, float iy)
	{
		playerRecord = new HashMap<Integer,Integer>();
		alertItems = new ArrayList<Integer>(Arrays.asList(InventoryItem.BLOCK_AMOUNT+30,InventoryItem.BLOCK_AMOUNT+31,InventoryItem.BLOCK_AMOUNT+35,InventoryItem.BLOCK_AMOUNT+33,InventoryItem.BLOCK_AMOUNT+34));
		canSkin = new ArrayList<Integer>(Arrays.asList(InventoryItem.BLOCK_AMOUNT+35));
		torso = new Sprite(new Texture("chickenTorso.png"));
		head = new Sprite(new Texture("chickenHead.png"));
		leg1 = new Sprite(new Texture("chickenLeg.png"));
		leg2 = new Sprite(new Texture("chickenLeg.png"));
		x=ix;
		y = iy;
	}
	void followManager()
	{
		if(XYControl.Visible(x,y,0,-1000,-500))
		{
			followTimer = 300;
		}
		else
		{
			followTimer--;
		}
		playerRecord.put((int)DirectionControl.current,(int)DirectionControl.realSpeed);
	}
	void skin()
	{
		boolean skinAble = false;
		for(int i = 0; i < canSkin.size();i++)
		{
			if(HUD.typeN == canSkin.get(i))
			{
				skinAble = true;
			}
		}
		if(skinAble && Button.button(x, y, width, height, Gdx.input.isButtonPressed(Input.Buttons.LEFT))&&XYControl.Visible(x, y, 0, -1020, -500))
		{
			if(HUD.inventoryFull == false)
			{
				Protagonist.arrowStage = 0;
				Protagonist.skin = true;
				skinBegin = true;
			}
		}
		if(HUD.inventoryFull == false&&skinBegin&&Protagonist.arrowStage == 5)
		{
			HUD.inventory.item(InventoryItem.BLOCK_AMOUNT+32).add(1);
			destroy = true;
		}
	}
	void draw()
	{
		batch.begin();
		if(destroy == false)
		{
		
		losSwitchTimer--;
		if(Gdx.input.isKeyPressed(Input.Keys.B))
		{
			if(losSwitchTimer<0&&losVisible==false)
			{
			losVisible = true;
			losSwitchTimer = 20;
			}
			else if(losSwitchTimer<0&&losVisible==true)
			{
				losVisible=false;
				losSwitchTimer = 20;
			}
		}
		directionControl();
		if(health > 0)
		{
		if(dx !=0)
		{
			if(legAngle>20)
			{
				legMoveRight = false;
			}
			if(legAngle<-20)
			{
				legMoveRight = true;
			}
			if(legMoveRight)
			{
				legAngle++;
			}
			else
			{
				legAngle--;
			}
		}
		else if (legAngle>0)
		{
			legAngle--;
		}
		else
		{
			legAngle++;
		}
		
		}
		else
		{
			skin();
		}
		if(headAngle<-90 || headAngle>90)
		{
			torso.setFlip(true,false);
			leg1.setFlip(true,false);
			head.setFlip(false,true);
			leg2.setFlip(true,false);
			head.setPosition(x-7,y+23);
		}
		else
		{
			torso.setFlip(false,false);
			leg1.setFlip(false,false);
			head.setFlip(false,false);
			leg2.setFlip(false,false);
			head.setPosition(x+20,y+23);
		}
		if(health > 0)
		{
			leg1.setScale(Grid.BLOCKSIZE/20);
			leg2.setScale(Grid.BLOCKSIZE/20);
			torso.setScale(Grid.BLOCKSIZE/30);
			head.setScale(Grid.BLOCKSIZE/25);
			leg1.setOriginCenter();
			leg2.setOriginCenter();
			head.setOriginCenter();
			head.setRotation(headAngle);
			torso.setPosition(x,y+10);
			leg1.setPosition(x+11,y+3);
			leg2.setPosition(x+11,y+3);
			leg1.setRotation(legAngle);
			leg2.setRotation(-legAngle);
			leg2.draw(batch);
			head.draw(batch);
			leg1.draw(batch);
			torso.draw(batch);
		}
		else
		{
			torsoAngle+=torsoAngleChange;
			torso = new Sprite(new Texture("fullChicken.png"));
			torso.setScale(Grid.BLOCKSIZE/30);
			torso.setPosition(x, y+10);
			torso.setRotation(torsoAngle);
			torso.draw(batch);
		}
		alertOther(x+10,y+10);
		}
		batch.end();
	}
	boolean checkProjectileHit(float x, float y, float width, float height, float dx, float dy, float damage)
	{
		if(health>0 && x + width > this.x && x < this.x+this.width&& y + height > this.y && y < this.y+this.height)
		{
			if(damage > 1)
			{
				grudge = true;
				projectileShotTimer = 10;
				health -= damage;
				dy = 3;
				if(dx > 0)
				{
					direction = 1;
				}
				else
				{
					direction = -1;
				}
			}
			if(health <= 0)
			{
				torsoAngleChange = dx;
				this.dx = (dx/mass);
				this.dy = (dy/mass)+3;
			}
			detectionMeter = 100000000;
			return true;
		}
		return false;
	}
	float makeJump(float jumpHeight,float ay)
	{
		for (int i = 0; i < (jumpHeight * jumpHeight)/(2*ay);i++)
		{
			if (GridBased.upStop(x-MyGdxGame.dc.realSpeed,y - MyGdxGame.dc.dy() + i, width,height))
			{
				return 0;
			}
			if(direction == 1)
			{
			if(GridBased.rightStop(x-MyGdxGame.dc.realSpeed,y - MyGdxGame.dc.dy() + i, width,height) == false)
			{
				return (float) (Math.sqrt(ay*i*2)) * 1.01f;
			}
			}
			else if (direction == -1)
			{
				if(GridBased.leftStop(x-MyGdxGame.dc.realSpeed,y - MyGdxGame.dc.dy() + i, width,height) == false)
				{
					return (float) (Math.sqrt(ay*i*2)) * 1.01f;
				}
			}
		}
		return 0;
	}
	void resetDirection()
	{
		headAngleTimer = 1;
		int prevType = direction;
		if(Math.random() * 3 > 1)
		{
			if (prevType != 0)
			{
				direction = -direction;
			}
			else
			{
				if (Math.random() * 4 <= 2)
				{
					direction = 1;
				}
				else
				{
					direction = -1;
				}
			}
		}
		else
		{
			direction = 0;
		}
		
	}
	void wander(int speed, int wanderRange)
	{
		wanderTimer++;
		if (GridBased.rightStop(x-MyGdxGame.dc.realSpeed,y - MyGdxGame.dc.dy(), width,height) == false && direction ==1 || GridBased.leftStop(x-MyGdxGame.dc.realSpeed,y - MyGdxGame.dc.dy(), width,height) == false && direction ==-1 )
		{
			if(direction == 1)
			{
				dx = speed;
			}
			else if (direction == -1)
			{
				dx = -speed;
			}
			else
			{
				dx = 0;
			}
		}
		else
		{
			dx = 0;
		}
		if( wanderTimer > wanderRandom && GridBased.downStop(x-MyGdxGame.dc.realSpeed,y - MyGdxGame.dc.dy(), width,height))
		{
			wanderRandom = (int) (Math.random()*wanderRange) + 100;
			wanderTimer = 0;
			resetDirection();
		}
	}
	void alertOther(float x, float y)
	{
		if(detected)
		{
			for(int i = 0; i < MyGdxGame.chickens.size();i++)
			{
				if(x != MyGdxGame.chickens.get(i).x&&x > MyGdxGame.chickens.get(i).x - 100&& x < MyGdxGame.chickens.get(i).x+100&&y > MyGdxGame.chickens.get(i).y - 100&& y < MyGdxGame.chickens.get(i).y + 100)
				{
					if(health < 0)
					{
						MyGdxGame.chickens.get(i).grudge = true;
						MyGdxGame.chickens.get(i).detectionMeter+=1000;
						if(MyGdxGame.chickens.get(i).detectionMeter >= 90000&&MyGdxGame.chickens.get(i).detected == false)
						{
							if(MyGdxGame.chickens.get(i).x < x)
							{
								MyGdxGame.chickens.get(i).direction = -1;
							}
							else
							{
								MyGdxGame.chickens.get(i).direction = 1;
							}
						}
					}
					else if(grudge == true)
					{
						MyGdxGame.chickens.get(i).detectionMeter+=1000;
						if(MyGdxGame.chickens.get(i).detectionMeter >= 90000&&MyGdxGame.chickens.get(i).detected ==false)
						{
							MyGdxGame.chickens.get(i).direction = direction;
						}
					}
					else
					{
						MyGdxGame.chickens.get(i).detectionMeter+=600;
						if(MyGdxGame.chickens.get(i).detectionMeter >= 90000&&MyGdxGame.chickens.get(i).detected ==false)
						{
							MyGdxGame.chickens.get(i).direction = direction;
						}
					}
				}
			}
		}
	}
	void alerted(int speed)
	{
		projectileShotTimer--;
		if(1000 > x && direction != -1 && spot||projectileShotTimer>0&&direction!=-1&&1000>x)
		{
			direction = -1;
		}
		else if (1000<x&&direction!=1&&spot||projectileShotTimer>0&&direction!=1&&1000<x)
		{
			direction = 1;
		}
		if (GridBased.rightStop(x-MyGdxGame.dc.realSpeed,y - MyGdxGame.dc.dy(), width,height) == false && direction ==1 || GridBased.leftStop(x-MyGdxGame.dc.realSpeed,y - MyGdxGame.dc.dy(), width,height) == false && direction ==-1 )
		{
			if(direction == 1)
			{
				dx = speed;
			}
			else if (direction == -1)
			{
				dx = -speed;
			}
			else
			{
				dx = 0;
			}
		}
		else if (projectileShotTimer > 0)
		{
			if(Math.random()<.5d)
			{
				direction = 1;
			}
			else
			{
				direction = -1;
			}
		}
	}
	void losManager(float xDis, float yDis, float angle, float angleRange, float examineRange, int detectionGain)
	{
		if(topScan==false)
		{
		if(los(x + xDis,y + yDis,Protagonist.WIDTH/2,0,examineRange,detectionGain,90) == 0)
		{
			//sr2.setColor(1,0,1,.5f);
			topScan = true;
		}
		else
		{
			//sr2.setColor(0,1,1,.5f);
		}
		}
		else
		{
		if(los(x + xDis,y + yDis,Protagonist.WIDTH/2,Protagonist.HEIGHT,examineRange,detectionGain,90) == 0)
		{
			//sr2.setColor(1,0,1,.5f);
			topScan = false;
		}
		else
		{
			//sr2.setColor(0,1,1,.5f);
		}
		}
		if(losVisible)
		{
		//sr2.drawLine(x+xDis, y+ yDis, x+xDis+ (float)(Math.cos(scanAngle)*examineRange), (float)(y + yDis + Math.sin(scanAngle)*examineRange));
		//sr3.drawLine(x+xDis, y+ yDis, x+xDis+ (float)(Math.cos(headAngle*(Math.PI/180))*examineRange), (float)(y + yDis + Math.sin(headAngle*(Math.PI/180))*examineRange));
		}
		detectionManager();
	}
	void detectionManager()
	{
		if (detectionMeter > 1000)
		{
			detectionMeter -= 2;
		}
		if (detectionMeter > 100000 && detected == false)
		{
			detected = true;
			direction = -headD;
		}
	}
	int los(float x, float y, float pxOff,float pyOff,float examineRange,float detectionGain,float headAngleRange)
	{
		boolean itemAlert = false;
		if(grudge == false)
		{
			for(int i = 0; i < alertItems.size();i++)
			{
				if(HUD.typeN == alertItems.get(i))
				{
					itemAlert = true;
				}
			}
		}
		if(detectionMeter>50000||grudge||itemAlert == true)
		{
		int pHeight = 0;
		if(XYControl.Visible(x, y, 0, examineRange-1050, examineRange-450))
		{
		
		if(MyGdxGame.protagonist.crouch == false)
		{
			pHeight = 40;
			scanAngle=(Math.atan2(((Protagonist._y+pyOff)-y),((Protagonist._x+pxOff)-x)));
		}
		else
		{
			detectionGain = detectionGain/2;
			pHeight = 10;
			if(pyOff>0)
			{
				scanAngle=(Math.atan2(((Protagonist._y+pyOff-30)-y),((Protagonist._x+pxOff)-x)));
			}
			else
			{
				scanAngle=(Math.atan2(((Protagonist._y+pyOff)-y),((Protagonist._x+pxOff)-x)));
			}
		}
		if(headAngle>0&&scanAngle<(headAngle+headAngleRange/2)*(Math.PI/180)&&scanAngle>(headAngle-headAngleRange/2)*(Math.PI/180)|| headAngle < 0&&scanAngle<(headAngle+headAngleRange/2+360)*(Math.PI/180)&&scanAngle>(headAngle-headAngleRange/2+360)*(Math.PI/180))
		{
			for (int j = 0; j < examineRange; j+=5)
			{
				if(GridBased.sightBlocked((float)(x+Math.cos(scanAngle) * j), (float)(y+Math.sin(scanAngle) * j )))
				{
					return 0;
				}
				else if (y+Math.sin(scanAngle) * j > Protagonist._y && y+Math.sin(scanAngle) * j < Protagonist._y + Protagonist.HEIGHT + pHeight)
				{
					if (x+Math.cos(scanAngle) * j > Protagonist._x && x+Math.cos(scanAngle) * j < Protagonist._x + Protagonist.WIDTH - 10)
					{
						detectionMeter+=(int)(detectionGain/(j/examineRange));
						headAngle = (float) (scanAngle*(180/Math.PI));
						headAngleTimer = 60;
						if(wanderTimer < 60)
						{
							wanderTimer = 60;
						}
						spot = true;
						return 1;
					}
				}
			}
		}
		}
		}
		spot = false;
		return 0;
	}
	void headAngleManager()
	{
		headAngleTimer--;
		if(headAngleTimer < 0)
		{
			if(direction==1)
			{
				headAngle=(float) (Math.random()*100-30);
			}
			if(direction==-1)
			{
				headAngle=(float) (-Math.random()*100-140);
			}
			if(direction==0)
			{
				if(Math.random()<.5f)
				{
					headAngle=(float) (Math.random()*100-30);
				}
				else
				{
					headAngle=(float) (-Math.random()*100-140);
				}
			}
			headAngleTimer = (int) (20+Math.random()*200);
		}
	}
	void directionControl()
	{
		float jumpHeight = 9f;
		float ay = .12f;
		if(health > 0)
		{
		
		if (direction == 1 &&GridBased.rightStop(x-MyGdxGame.dc.realSpeed,y - MyGdxGame.dc.dy(), width,height) && GridBased.downStop(x-MyGdxGame.dc.realSpeed,y - MyGdxGame.dc.dy(),width,height) ||
				direction == -1 && GridBased.leftStop(x-MyGdxGame.dc.realSpeed,y - MyGdxGame.dc.dy(),  width,height) && GridBased.downStop(x-MyGdxGame.dc.realSpeed,y - MyGdxGame.dc.dy(),  width,height))
		{
			dx = 0;
			if(makeJump (jumpHeight,ay) != 0)
			{
				dy = makeJump(jumpHeight,ay);
			}
			else
			{
				if (detected == false)
				{
					resetDirection();
				}
				else
				{
					direction = 0;
				}
			}
		}
		else
		{
			if (detected)
			{
				alerted(2);
			}
			else
			{
				wander(1,1000);
			}
			losManager(10,30,0,30, 300,700);
			
		}
		}
		else
		{
		if(GridBased.rightStop(x-MyGdxGame.dc.realSpeed,y - MyGdxGame.dc.dy(), width,height)
				||GridBased.leftStop(x-MyGdxGame.dc.realSpeed,y - MyGdxGame.dc.dy(),  width,height))
		{
			dx = 0;
		}
		else
		{
			if(dx > 0)
			{
				dx-=.01f;
			}
			else if(dx < 0)
			{
				dx+=.01f;
			}
		}
		}
		if(GridBased.rightStop(x-MyGdxGame.dc.realSpeed,y - MyGdxGame.dc.dy(), width,height)&&dx>0)
		{
			dx = 0;
		}
		else if(GridBased.leftStop(x-MyGdxGame.dc.realSpeed,y - MyGdxGame.dc.dy(), width,height)&&dx<0)
		{
			dx = 0;
		}
		if(GridBased.upStop(x-MyGdxGame.dc.realSpeed,y - MyGdxGame.dc.dy(),  width,height))
		{
			dy = 0;
		}
		if (GridBased.downStop(x-MyGdxGame.dc.realSpeed,y - MyGdxGame.dc.dy(), width,height) == false)
		{
			if(dy > -8)
			{
				dy -= ay;
			}
		}
		else if (dy <= 0)
		{
			dy = 0;
			if(health<=0)
			{
				dx = 0;
				torsoAngleChange = 0;
			}
		}
		
		if(health > 0)
		{
			headAngleManager();
		}
			x -= MyGdxGame.dc.realSpeed - dx;
			y -= MyGdxGame.dc.dy() - dy;
	}

}
