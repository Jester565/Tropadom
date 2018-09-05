package com.mygdx.game;

import java.awt.Color;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class BallBlock {
	public static boolean gameBegin = false;
	public static boolean loose = false;
	public static boolean win = false;
	boolean created = false;
	int rows = 2;
	ArrayList<BlockRow>blockRows;
	ShapeRenderer sr;
	Paddle paddle;
	Ball ball;
BallBlock()
{
	sr = new ShapeRenderer();
	blockRows = new ArrayList<BlockRow>();
	paddle = new Paddle();
	ball = new Ball();
}
boolean winScan()
{
	for(int i = 0; i < blockRows.size();i++)
	{
		for(int j = 0; j < blockRows.get(i).blocks.size();j++)
		{
			if(blockRows.get(i).blocks.get(j).destroyed == false)
			{
				return false;
			}
		}
	}
	return true;
}
void draw()
{
	if(loose)
	{
		blockRows.clear();
		created = false;
		rows = 2;
		Ball.dx = 3;
		Ball.dy = -3;
		Ball.x = 60;
		Ball.y = 300;
		gameBegin = false;
		loose=false;
		win = false;
	}
	if(win)
	{
		blockRows.clear();
		created = false;
		rows++;
		Ball.dx = 3;
		Ball.dy = -3;
		Ball.x = 60;
		Ball.y = 300;
		gameBegin = false;
		win=false;
		loose = false;
	}
	sr.begin(ShapeType.Filled);
	sr.setColor(.2f,.4f,.6f,1);
	sr.rect(0, 0, 2000, 1000);
	sr.setColor(1,1,1,1);
	sr.rect(0, 0, 50, 1000);
	sr.rect(0, 0, 2000, 50);
	sr.rect(1850, 0, 100, 1000);
	sr.rect(0, 950, 2000, 50);
	sr.end();
	if(created == false)
	{
		for(int i = 0; i < rows; i++)
		{
			BlockRow blockRow;
			blockRow = new BlockRow(i);
			blockRows.add(blockRow);
		}
	}
	for(int i = 0; i < blockRows.size();i++)
	{
		blockRows.get(i).draw();
	}
	paddle.draw();
	if(Gdx.input.isButtonPressed(Input.Buttons.LEFT))
	{
		gameBegin = true;
	}
	ball.draw();
	created = true;
	win = winScan();
	
}
}
class BlockRow{
	boolean created = false;
	int rowAmount = 30;
	int jd;
	int rowColor = (int)(Math.random()*2.9);
	int blockHeight = 50;
	float y;
	ArrayList<Block>blocks;
	BlockRow(int jd)
	{
		blocks = new ArrayList<Block>();
		this.jd = jd;
	}
	void draw()
	{
		y = 900 - jd*blockHeight - blockHeight;
		if(created == false)
		{
			for(int i = 0; i < rowAmount; i++)
			{
				float x = 1800/rowAmount * i+50;
				Block block;
				block = new Block(x,y,1800/rowAmount,blockHeight,rowColor);
				blocks.add(block);
			}
		}
		for(int i = 0; i <blocks.size();i++)
		{
			blocks.get(i).draw();
		}
		created = true;
	}
}
class Ball{
	public static int directionChangeEffectTimer = 0;
	public static float x=60;
	public static float dx = 3;
	public static float dy = -3;
	public static float y=300;
	public static int diameter = 20;
	ShapeRenderer sr;
	Ball()
	{
		sr = new ShapeRenderer();
	}
	void draw()
	{
		directionChangeEffectTimer--;
		if(BallBlock.gameBegin)
		{
			x+=dx;
			y+=dy;
		}
		if(x < 50||x + diameter > 1850)
		{
			changeDx(0,true);
		}
		if(y + diameter/2 > 950)
		{
			changeDy();
		}
		if(y<50)
		{
			BallBlock.loose = true;
		}
		sr.begin(ShapeType.Filled);
		sr.setColor(0,0,0,1);
		sr.rect(x,y,diameter,diameter);
		sr.end();
	}
	public static void changeDx(float change, boolean flip)
	{
		if(flip)
		{
		if(dx < 0)
		{
			dx = -(dx-change/30);
		}
		else
		{
			dx = -(dx+change/30);
		}
		}
		else
		{
		if(dx < 0)
		{
			dx -= change/40;
		}
		}
	}
	public static void changeDy()
	{
		if(directionChangeEffectTimer < 0)
		{
		if(dy < 0)
		{
			dy = -(dy-.15f);
			directionChangeEffectTimer = 1;
		}
		else
		{
			dy = -(dy+.15f);
			directionChangeEffectTimer = 1;
		}
		}
	}
	

}
class Paddle{
	public static int x;
	public static int changeBallTimer = 0;
	public static int y = 50;
	public static int height = 30;
	public static int width = 70;
	ShapeRenderer sr;
	Paddle()
	{
		sr = new ShapeRenderer();
	}
	void draw()
	{
		changeBallTimer--;
		if(Gdx.input.getX() < 50)
		{
			x = 50;
		}
		else if (Gdx.input.getX() + width > 1850)
		{
			x = 1850 - width;
		}
		else
		{
			x = Gdx.input.getX();
		}
		if(changeBallTimer < 0 && Ball.x + Ball.diameter > x && Ball.x < x + width && Ball.y < y + height)
		{
			changeBallTimer = 6;
			Ball.changeDy();
			Ball.changeDx((x+width/2-(Ball.x))*15f,false);
		}
		sr.begin(ShapeType.Filled);
		sr.setColor(1,1,1,1);
		sr.rect(x, y, width, height);
		sr.end();
		sr.begin(ShapeType.Line);
		sr.setColor(0,0,0,0);
		sr.rect(x, y, width, height);
		sr.end();
	}
}
class Block{
	float x;
	float y;
	float w;
	float h;
	int color;
	boolean destroyed = false;
	ShapeRenderer sr;
	Block(float x, float y,float w, float h, int color)
	{
		sr = new ShapeRenderer();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.color = color;
	}
	void draw()
	{
		if(destroyed == false)
		{
			
			if(Ball.x  + Ball.diameter> x && Ball.x < x + w && Ball.y < y + h/1.5 && Ball.y+Ball.diameter> y+h/3)
			{
				Ball.changeDx(0,true);
				destroyed = true;
			}
		if(Ball.x  + Ball.diameter> x && Ball.x < x + w && Ball.y < y + h && Ball.y+Ball.diameter> y)
		{
			Ball.changeDy();
			Ball.changeDx((x+w/2-(Ball.x)),false);
			destroyed = true;
		}
		sr.begin(ShapeType.Filled);
		if(color == 0)
		{
			sr.setColor(1,0,0,1);
		}
		if(color == 1)
		{
			sr.setColor(0,1,0,1);
		}
		if(color == 2)
		{
			sr.setColor(0,0,1,1);
		}
		sr.rect(x,y,w,h);
		sr.end();
		sr.begin(ShapeType.Line);
		sr.setColor(0,0,0,1);
		sr.rect(x,y,w,h);
		sr.end();
		}
	}
}
