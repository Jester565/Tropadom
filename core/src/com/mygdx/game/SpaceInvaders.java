package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class SpaceInvaders {
	public static int widthConstant = 1500;
	ShapeRenderer sr;
	static Player player;
	public static ArrayList<AlienRow>alienRows;
	public static ArrayList<Laser>lasers;
	boolean create = false;
	public static boolean right = false;
	int alienRowAmount = 5;
	int dSwitchTimer = 0;
	static boolean moveDown = false;
SpaceInvaders()
{
	sr = new ShapeRenderer();
	alienRows = new ArrayList<AlienRow>();
	lasers = new ArrayList<Laser>();
	player = new Player();
}
void moveDown()
{
	player.draw();
	if(dSwitchTimer < 0)
	{
	for(int i = 0; i < alienRowAmount;i++)
	{
		if(alienRows.get(i).moveDown() == true)
		{
			moveDown = true;
		}
	}
	if(dSwitchTimer < 0 && moveDown == true && right == true)
	{
		dSwitchTimer = 10;
		right = false;
	}
	else if (dSwitchTimer < 0 && moveDown == true && right == false)
	{
		dSwitchTimer = 10;
		right = true;
	}
	}
}
void draw()
{
	sr.begin(ShapeType.Filled);
	sr.setColor(0,0,0,1);
	sr.rect(0,0,2000,2000);
	sr.end();
	moveDown();
	dSwitchTimer--;
	if(create==false)
	{
		for(int i = 0;i<alienRowAmount;i++)
		{
			AlienRow alienRow;
			alienRow = new AlienRow(i);
			alienRows.add(alienRow);
		}
	}
	for(int i = 0;i<alienRowAmount;i++)
	{
		alienRows.get(i).draw();
	}
	for(int i = 0; i < lasers.size();i++)
	{
		lasers.get(i).draw();
	}
	create = true;
	moveDown = false;
}
}
class AlienRow{
	int jd;
	public static int alienAmount = 30;
	boolean create = false;
	ArrayList<Alien>aliens;
	AlienRow(int jd)
	{
		this.jd = jd;
		aliens = new ArrayList<Alien>();
	}
	boolean moveDown()
	{
		for(int i = 0; i < alienAmount;i++)
		{
			if(aliens.get(i).destroy==false)
			{
			if(aliens.get(i).x + (SpaceInvaders.widthConstant/alienAmount) - (SpaceInvaders.widthConstant/alienAmount)/4> 1900 || aliens.get(i).x < 50)
			{
				return true;
			}
			}
		}
		return false;
	}
	void draw()
	{
		if(create == false)
		{
			for(int i = 0; i < alienAmount;i++)
			{
				Alien alien;
				alien = new Alien(i*(SpaceInvaders.widthConstant/alienAmount)+50,950 - (SpaceInvaders.widthConstant/alienAmount)*jd);
				aliens.add(alien);
			}
		}
		for(int i = 0; i < alienAmount;i++)
		{
			aliens.get(i).draw();
		}
		create = true;
	}
}
class Player{
	ShapeRenderer sr;
	float x;
	boolean destroy = false;
	float y = 100;
	float w = 30;
	float h = 10;
	int laserTimer = 0;
	Player()
	{
		sr = new ShapeRenderer();
	}
	void draw()
	{
		if(destroy == false)
		{
		laserTimer--;
		x = Gdx.input.getX();
		sr.begin(ShapeType.Filled);
		sr.setColor(0,0,1,1);
		sr.rect(Gdx.input.getX(),y,w,h);
		sr.end();
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)&&laserTimer < 0)
		{
			laserTimer = 20;
			Laser laser;
			laser = new Laser(x + w/2,y+h/2,true);
			SpaceInvaders.lasers.add(laser);
		}
		}
	}
	boolean laserHitCheck(float lx, float ly, float lw, float lh)
	{
		if(lx + lw > x  && lx < x + w && ly + lh > y && ly< y +h)
		{
			destroy = true;
			return true;
		}
		return false;
	}
}
class Alien{
	boolean destroy = false;
	float x;
	float y;
	float laserRandom = 0;
	ShapeRenderer sr;
	Alien(float x, float y)
	{
		this.x = x;
		this.y = y;
		sr = new ShapeRenderer();
	}
	void draw()
	{
		if(destroy == false)
		{
			if(SpaceInvaders.moveDown)
			{
				y-= SpaceInvaders.widthConstant/AlienRow.alienAmount;
			}
			if(SpaceInvaders.right)
			{
				x++;
			}
			else
			{
				x--;
			}
		laserRandom = (float) (Math.random() * 4900);
		if(laserRandom < 2)
		{
			Laser laser;
			laser = new Laser(x + (SpaceInvaders.widthConstant/AlienRow.alienAmount)/2,y+(SpaceInvaders.widthConstant/AlienRow.alienAmount)/4,false);
			SpaceInvaders.lasers.add(laser);
		}
		sr.begin(ShapeType.Filled);
		sr.setColor(0,1,0,1);
		sr.rect(x+(1800/AlienRow.alienAmount)/4, y+(SpaceInvaders.widthConstant/AlienRow.alienAmount)/4, (SpaceInvaders.widthConstant/AlienRow.alienAmount)/2, (SpaceInvaders.widthConstant/AlienRow.alienAmount)/2);
		sr.end();
		}
	}
	boolean laserHitCheck(float lx, float ly, float lw, float lh)
	{
		if(lx + lw > x +(SpaceInvaders.widthConstant/AlienRow.alienAmount)/4  && lx < x +(SpaceInvaders.widthConstant/AlienRow.alienAmount)/4+(SpaceInvaders.widthConstant/AlienRow.alienAmount)/2 && ly + lh > y +(SpaceInvaders.widthConstant/AlienRow.alienAmount)/4 && ly < y +(SpaceInvaders.widthConstant/AlienRow.alienAmount)/4+(1800/AlienRow.alienAmount)/2 )
		{
			destroy = true;
			return true;
		}
		return false;
	}
}
class Laser{
	float x;
	float y;
	float w = 3;
	float h = 14;
	boolean destroy = false;
	float laserSpeed = 9;
	boolean friendly;
	ShapeRenderer sr;
	Laser(float x, float y,boolean friendly)
	{
		this.x = x;
		this.y = y;
		this.friendly = friendly;
		sr = new ShapeRenderer();
	}
	void draw()
	{
		if(destroy==false)
		{
		if(friendly)
		{
			y+=laserSpeed;
		}
		else
		{
			y-=laserSpeed;
		}
		sr.begin(ShapeType.Filled);
		sr.setColor(1,0,0,1);
		sr.rect(x, y, w, h);
		sr.end();
		
		if(friendly == true)
		{
		for(int i = 0; i < SpaceInvaders.alienRows.size();i++)
		{
			for(int j = 0; j < SpaceInvaders.alienRows.get(i).aliens.size(); j++)
			{
				if(SpaceInvaders.alienRows.get(i).aliens.get(j).laserHitCheck(x, y, w, h))
				{
					destroy = true;
				}
			}
		}
		}
		else
		{
			SpaceInvaders.player.laserHitCheck(x, y, w, h);
		}
		}
	}
}
