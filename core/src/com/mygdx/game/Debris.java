package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Debris {
	SpriteBatch batch;
	TextureRegion tr;
	//Texture texture;
	Sprite sprite = new Sprite();
	int x;
	int y;
	int width;
	int height;
	float dx;
	float dy;
	float alpha = 1;
	float rotationSpeed;
	float rotation;
Debris(String s, int x, int y, float dx, float dy, float rotationSpeed)
{
	//batch = new SpriteBatch();
	this.rotationSpeed = rotationSpeed;
	//tr = new TextureRegion();
	this.x = x;
	this.y = y;
	this.dx = dx;
	this.dy = dy;
	/*
	tr = new TextureRegion();
	tr = new Sprite(t);
	height = (int)(Math.random() * 40);
	width = (int)(Math.random() * 40);
	tr.setRegion((int)(Math.random()*(14)), (int)(Math.random() * 14), (int)(Math.random() * 15) + 1, (int)(Math.random() * 15) + 1);
	*/
	//sprite.setToTextureRegion(s,(int)Math.random()*(14), (int)(Math.random() * 14), (int)(Math.random() * 15) + 1, (int)(Math.random() * 15) + 1);
}
void draw()
{
	alpha -= .006f;
	if(GridBased.leftStop(x, y, width, height) || GridBased.rightStop(x, y, width, height))
	{
		dx = -dx;
		if(dx > 0)
		{
			dx -= 5f;
		}
		else
		{
			dx += 5f;
		}
	}
	if(GridBased.upStop(x, y, width, height) || GridBased.downStop(x, y, width, height))
	{
		dy = -dy;
		if(dy > 0)
		{
			dy -= 5f;
		}
		else
		{
			dy += 5f;
		}
	}
	if(rotationSpeed > 0)
	{
		rotationSpeed -= rotationSpeed/30;
	}
	else
	{
		rotationSpeed += rotationSpeed/30;
	}
	if(dx > 0)
	{
		dx -= dx/30;
	}
	else
	{
		dx += dx/30;
	}
	if(dy > 0)
	{
		dy -= dy/30;
	}
	else
	{
		dy += dy/30;
	}
	dy -=  DirectionControl.GRAVITY;
	x+= (dx)- MyGdxGame.dc.realSpeed;
	y+= (dy) - (MyGdxGame.dc.dy()/MyGdxGame.dc.slowFactor);
	rotation += rotationSpeed;
	sprite.setAlpha(alpha);
	sprite.setScale(3f);
	sprite.setOriginCenter();
	sprite.setRotation(rotation);
	sprite.setPosition(x, y);
	batch.begin();
	sprite.draw(batch);
	batch.end();
}
}
