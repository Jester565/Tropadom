package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class BombEffect {
	int x;
	int y;
	float radius;
	float trans = .3f;
	ShapeRenderer sr;
BombEffect(int x, int y)
{
	this.x = x;
	this.y = y;
	sr = new ShapeRenderer();
}
void draw()
{
	radius+=80;
	trans-=.02f;
	sr.begin(ShapeType.Line);
	sr.setColor(1, 1, 1, .001f);
	sr.circle(x, y, radius);
	sr.end();
}
}
