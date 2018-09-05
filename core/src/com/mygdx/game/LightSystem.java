package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class LightSystem {
	static int centerID;
	static int centerJD;
	static boolean night = false;
	int daySwitchTimer = 0;
	ShapeRenderer sr;
	LightSource light;
	static public ArrayList<LightSource>lights;
LightSystem()
{
	lights = new ArrayList<LightSource>();
	light= new LightSource();
}
void draw()
{
	daySwitchTimer--;
	if(daySwitchTimer < 0 && Gdx.input.isKeyPressed(Input.Keys.F))
	{
		if(night == false)
		{
			night = true;
			daySwitchTimer = 20;
		}
		if(night && daySwitchTimer < 0)
		{
			night = false;
			daySwitchTimer = 20;
		}
	}
	if(HUD.typeN == 11)
	{
		light.draw(centerID, centerJD, 10);
	}
	for(int i = 0; i < lights.size(); i++)
	{
		int k = lights.get(i).magnitude;
		for(int j = 0; j < lights.get(i).magnitude/2; j++)
		{
			k--;
			GridBased.setShadow(lights.get(i)._id - j,lights.get(i)._jd, k,j/(lights.get(i).magnitude/1.65f));
			if(GridBased.surroundCheck(lights.get(i)._id - j - 1, lights.get(i)._jd) == false)
			{
				break;
			}
		}
		
		k = lights.get(i).magnitude;
		for(int j = 0; j < lights.get(i).magnitude/2; j++)
		{
			k--;
			GridBased.setShadow(lights.get(i)._id+ j,lights.get(i)._jd, k, j/(lights.get(i).magnitude/1.65f));
			if(GridBased.surroundCheck(lights.get(i)._id + j + 1, lights.get(i)._jd) == false)
			{
				break;
			}
		}
		
	}
	lights.clear();
}
}
