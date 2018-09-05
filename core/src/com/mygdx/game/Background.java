package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Background {
ShapeRenderer sr;
Background()
{
	sr = new ShapeRenderer();
}
void draw(float type)
{
	sr.begin(ShapeType.Filled);
	sr.setColor(0,0,1,1);
	sr.rect(0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
	sr.end();
}
}
