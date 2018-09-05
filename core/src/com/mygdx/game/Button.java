package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Button {
Button()
{
	
}
public static boolean overButton(float x, float y, float width, float height)
{
	if (1000 - Gdx.input.getY() > y && 1000 - Gdx.input.getY() < y + height && Gdx.input.getX() > x && Gdx.input.getX() < x + width)
	{
		return true;
	}
	return false;
}
public static boolean button(float x, float y, float width, float height, boolean active)
{
	if (active && 1000 - Gdx.input.getY() > y && 1000 - Gdx.input.getY() < y + height && Gdx.input.getX() > x && Gdx.input.getX() < x + width)
	{
		return true;
	}
	return false;
}
}
