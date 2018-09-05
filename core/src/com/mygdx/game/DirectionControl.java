package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class DirectionControl  extends GridBased{
	
	public static float speed;
	public static float realSpeed;
	boolean pistonXInfluence = false;
	static float dy= 0;
	float ground = 0;
	boolean onlyXSlow = false;
	public static float JUMP = 10.6f;
	float STANDARD_SPEED = 4;
	public static float GRAVITY = .25f;
	float SPRINT_SPEED = 1;
	float slowFactor = .75f;
	float dy2;
	int jumpTimeLimit = 1;
	public static boolean moveX = false;
	int pistonInfluenceTimer = 0;
	static float current = 0;
	int jumpTimer = jumpTimeLimit+3;
	static float currentY = 0;
	boolean currentSet = false;
DirectionControl()
{
}
float speed()
{
	return speed;
}
float current()
{
	return current;
}
float currentY()
{
	return currentY;
}
float dy()
{
	return dy2;
}
void loadCurrent()
{
	if (currentSet == false)
	{
		current = SaveManager.xCurrent;
		currentY = SaveManager.yCurrent;
		currentSet = true;
	}
}
void directionOn(float x,float y,float w,float h)
{
	
	if (swimming(x,y, w, h))
	{
		slowFactor = 2f;
		onlyXSlow = false;
	}
	else if (Protagonist.arrowFire)
	{
		slowFactor = 2f;
		onlyXSlow = true;
	}
	else
	{
		slowFactor = 1;
	}
	if(pistonXInfluence&&pistonInfluenceTimer<0)
	{
		if(GridBased.rightStop(x, y, w, h)||GridBased.leftStop(x, y, w, h)||GridBased.upStop(x, y, w, h)||GridBased.downStop(x, y, w, h))
		{
			pistonXInfluence = false;
		}
	}
	if (Gdx.input.isKeyPressed(Input.Keys.D) && rightStop(x,y,w,h) == false && pistonInfluenceTimer < 0 && pistonXInfluence == false)
	{
		if(Protagonist.sit==false && Protagonist.sitTimer<0)
		{
		moveX = true;
		speed = (STANDARD_SPEED/slowFactor)/2.5f;
		current += STANDARD_SPEED/slowFactor;
		realSpeed =(STANDARD_SPEED/slowFactor); 
			if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
			{
				speed = ((SPRINT_SPEED +STANDARD_SPEED)/slowFactor)/2.5f;
				current += SPRINT_SPEED/slowFactor;
				realSpeed = ((SPRINT_SPEED +STANDARD_SPEED)/slowFactor);
			}
		}
			
	}
	else if (Gdx.input.isKeyPressed(Input.Keys.A) && leftStop(x,y,w,h) == false&& pistonInfluenceTimer < 0&& pistonXInfluence == false)
	{
		if(Protagonist.sit==false && Protagonist.sitTimer<0)
		{
		speed = -(STANDARD_SPEED/slowFactor)/2.5f;
		current -= STANDARD_SPEED/slowFactor;
		realSpeed = -(STANDARD_SPEED/slowFactor);
		moveX = true;
		if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
		{
			speed = -((SPRINT_SPEED +STANDARD_SPEED)/slowFactor)/2.5f;
			current -= SPRINT_SPEED/slowFactor;
			realSpeed =  -((SPRINT_SPEED +STANDARD_SPEED)/slowFactor);
		}
		}
	}
	else if (pistonXInfluence == false)
	{
		speed = 0;
		realSpeed = 0;
		moveX = false;
	}
	else
	{
		current+=speed;
		realSpeed = speed;
	}
}
void pistonPush(float px, float py,int blockAttached, float angle, int vx, int vy)
{
	System.out.println(blockAttached);
	if(blockAttached == 0)
	{
	if (Protagonist._x + Protagonist.WIDTH > px + Math.cos((angle+90)*(Math.PI/180))*Grid.BLOCKSIZE&& Protagonist._x < px + Grid.BLOCKSIZE + Math.cos((angle+90)*(Math.PI/180))*Grid.BLOCKSIZE && Protagonist._y > py + Math.sin((angle+90)*(Math.PI/180))*Grid.BLOCKSIZE&& Protagonist._y < py + Grid.BLOCKSIZE+ Math.sin((angle+90)*(Math.PI/180))*Grid.BLOCKSIZE)
	{
		dy = 12;
		if(vx!=0)
		{
			pistonXInfluence = true;
		}
		speed = vx;
		pistonInfluenceTimer = 8;
	}
	}
	else
	{
		if (Protagonist._x + Protagonist.WIDTH > px + Math.cos((angle+90)*(Math.PI/180))*Grid.BLOCKSIZE*2&& Protagonist._x < px + Grid.BLOCKSIZE*2 + Math.cos((angle+90)*(Math.PI/180))*Grid.BLOCKSIZE*2 && Protagonist._y > py + Math.sin((angle+90)*(Math.PI/180))*Grid.BLOCKSIZE*2&& Protagonist._y < py + Grid.BLOCKSIZE+ Math.sin((angle+90)*(Math.PI/180))*Grid.BLOCKSIZE*2)
		{
			if(blockAttached == 2)
			{
				dy = 18;
				pistonInfluenceTimer = 14;
				if(vx!=0)
				{
					speed = vx*2;
					pistonXInfluence = true;
				}
			}
			else
			{
				dy = 12;
				speed = vx;
				if(vx!=0)
				{
					pistonXInfluence = true;
				}
				pistonInfluenceTimer = 14;
			}
			speed = vx;
			
		}
	}
}
void directionYOn(float x,float y,float w,float h)
{
	jumpTimer++;
	pistonInfluenceTimer--;
	if (upStop(x,y,w,h)&&pistonInfluenceTimer < 0)
	{
		dy = -.2f;
	}
	if (jumpTimer == jumpTimeLimit&& upStop(x,y,w,h) == false && GridBased.inLadder(x,y, w,h) == false && swimming(x,y,w,h)==false&&downStop(x,y,w,h))
	{
		if(Protagonist.sit==false && Protagonist.sitTimer<0)
		{
		if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
		{
			dy = ((JUMP/slowFactor)/3) * 1.76f;
		}	
		else
		{
			dy = ((JUMP/slowFactor)/3) * 2.43f;
		}
		}
	}
	else if (Gdx.input.isKeyPressed(Input.Keys.W)&& upStop(x,y,w,h) == false && inLadder(x,y,w,h) || Gdx.input.isKeyPressed(Input.Keys.W) && upStop(x,y,w,h) == false && swimming(x,y,w,h)|| Gdx.input.isKeyPressed(Input.Keys.ALT_LEFT))
	{
		if(Protagonist.sit==false && Protagonist.sitTimer<0)
		{
			dy = 3;
		}
	}
	else if (downStop(x,y,w,h) == false && GridBased.inLadder(x,y,w,h)==false)
	{
		if (dy > -10)
		{
			if(onlyXSlow == false)
			{
				dy -= GRAVITY/slowFactor;
			}
			else
			{
				dy -= GRAVITY;
			}
		}
	
	}
	else if (Gdx.input.isKeyPressed(Input.Keys.S) && downStop(x,y,w,h) == false&&pistonInfluenceTimer < 0)
	{
		dy = -3;
	}
	else if(y < downStopHeight(x,y,w,h)-1&&pistonInfluenceTimer < 0)
	{
		dy = 1;
	}
	else if(y > downStopHeight(x,y,w,h)+1&&pistonInfluenceTimer < 0)
	{
		dy = -1;
	}
	else if (pistonInfluenceTimer < 0)
	{
		dy = 0;	
	}
	if (Gdx.input.isKeyPressed(Input.Keys.W) && upStop(x,y,w,h) == false && GridBased.inLadder(x,y, w,h) == false && swimming(x,y,w,h)==false&&downStop(x,y,w,h) && jumpTimer > jumpTimeLimit)
	{
		jumpTimer = 0;
	}
	if(onlyXSlow == false)
	{
		currentY -= dy/slowFactor;
		dy2 = dy/slowFactor;
	}
	else
	{
		currentY -= dy;
		dy2 = dy;
	}
	}
public static float directionYOn2(float x,float y,float w,float h,float yInfluence, float prevSpeed)
{
	float speed = prevSpeed;
	
	if (downStop(x,y + dy,w,h) == false)
	{
		if(yInfluence <= 0)
		{
			if (speed > yInfluence)
			{
			speed += yInfluence;
			}
		}
		if(speed > -8)
		{
			speed -= 3;
		}
	}
	
	
	if (upStop(x,y + dy,w,h) == false)
	{
		if(yInfluence > 0)
		{
			if(speed < yInfluence)
			{
				speed += yInfluence;
			}
		}
	}
	if(speed > 0 && upStop(x,y +dy,w,h))
	{
		speed = 0;
	}
	if (speed < 0 && downStop(x,y+dy,w,h))
	{
		speed = 0;
	}
	return speed;
}
public static float directionOn2(float x,float y,float w,float h,float xInfluence)
{
	float speed = 0;
	float slowFactor = 0;
	if (swimming(x,y, w, h))
	{
		slowFactor = 2f;
	}
	else
	{
		slowFactor = 1;
	}
	if (xInfluence > 0 && rightStop(x,y,w,h) == false)
	{
		speed += xInfluence/slowFactor;
	}
	if (xInfluence < 0 && leftStop(x,y,w,h) == false)
	{
		speed += xInfluence/slowFactor;
	}
	return speed;
}
}
