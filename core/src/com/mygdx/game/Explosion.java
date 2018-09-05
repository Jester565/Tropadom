package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Explosion {
int x;
int y;
Texture d;
float dx;
TextureRegion tr;
float dy;
boolean on;
int timer = 0;
//ShapeAdapter sr;
	Explosion(int x, int y, float dx, float dy)
	{
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		//d = new Texture("log.png");
		//tr = new TextureRegion();
		//tr = new Sprite(d);
		//int height = (int)(Math.random() * 10);
		//int width = (int)(Math.random() * 10);
		//tr.setRegion((int)(Math.random()*(Grid.BLOCKSIZE - width)), (int)(Math.random() * (Grid.BLOCKSIZE - height)), width, height);
		on = true;
		//sr = new ShapeAdapter();
	}
	void draw(int size)
	{
		if(on)
		{
		timer++;
		if(dx > 0)
		{
			dx-=dx/30;
		}
		else
		{
			dx+=dx/30;
		}
		if(dy > 0)
		{
			dy-=dy/30;
		}
		else
		{
			dy+=dy/30;
		}
		x+= (dx)- MyGdxGame.dc.realSpeed;
		y+= (dy) - (MyGdxGame.dc.dy()/MyGdxGame.dc.slowFactor);
			if(dx + dy == 0)
			{
				on = false;
			}
			if(GridBased.damage(x, y, dx, dy, size))
			{
				on = false;
			}
			//sr.setColor(1,0,0,1);
			//sr.rect(false,x, y, size, size);
		}
	}
}
