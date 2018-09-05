package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Projectile {
	float x;
	float y;
	float w;
	float h;
	float iw;
	float ih;
	float r;
	float dx;
	float dy;
	double destroyRandom = Math.random();
	float centerOfMass;
	float centerOfMassRatio;
	float mass;
	float drag;
	int damage = 5;
	boolean destroy = false;
	boolean attached = false;
	Sprite projectile = new Sprite();
	SpriteBatch batch = new SpriteBatch();
Projectile(String img, float x, float y, float w, float h, float is, float ir,float drag,float mass)
{
	this.x = x;
	this.y = y;
	this.iw = w;
	this.ih = h;
	this.dx = (float) (Math.cos(ir*(Math.PI/180))*is);
	this.r = ir;
	this.dy = (float) (Math.sin(ir*(Math.PI/180))*is);
	this.centerOfMass = centerOfMass;
	this.centerOfMassRatio = centerOfMassRatio;
	this.mass = mass;
	this.drag = drag;
	projectile = new Sprite(new Texture(img));
}
void draw()
{
	w = iw;
	h = ih;
	if(destroy==false)
	{
		if(attached == false)
		{
			damage = (int) (mass*(Math.abs(dx)+Math.abs(dy)));
		}
	if(GridBased.leftStop(x-MyGdxGame.dc.realSpeed, y-MyGdxGame.dc.dy(), w, h) || GridBased.rightStop(x-MyGdxGame.dc.realSpeed, y-MyGdxGame.dc.dy(), w, h)||GridBased.upStop(x-MyGdxGame.dc.realSpeed, y-MyGdxGame.dc.dy(), w, h)
			|| GridBased.downStop(x-MyGdxGame.dc.realSpeed, y-MyGdxGame.dc.dy(), w, h))
	{
		if(attached == false && destroyRandom<.5d)
		{
			destroy = true;
		}
		else
		{
			dx = 0;
			dy = 0;
		}
	}
	else
	{
		r = (float) (Math.atan2(dy, dx)*(180/Math.PI));
		dy -= .09f;
		dx -= drag;
		//dx = dx/drag;
	}
	if(dx == 0 && dy == 0)
	{
		if(XYControl.Visible(x,y,0,0,0)==false)
		{
			destroy = true;
		}
	}
	if(XYControl.Visible(x,y,0,600,600)==false)
	{
		destroy = true;
	}
	x+=dx-MyGdxGame.dc.realSpeed;
	y+=dy-MyGdxGame.dc.dy();
	projectile.setRotation(r);
	projectile.setPosition(x,y);
	batch.begin();
	projectile.draw(batch);
	batch.end();
	if(dx != 0 && dy != 0)
	{
		attached = false;
	for(int i = 0; i < MyGdxGame.chickens.size();i++)
	{
		if(MyGdxGame.chickens.get(i).checkProjectileHit(x-MyGdxGame.dc.realSpeed, y-MyGdxGame.dc.dy(), w,h, dx,dy,damage))
		{
			damage = 0;
			dx = MyGdxGame.chickens.get(i).dx;
			dy = MyGdxGame.chickens.get(i).dy;
			attached = true;
			i+=10000;	
		}
	}
	}
	}
}
}
