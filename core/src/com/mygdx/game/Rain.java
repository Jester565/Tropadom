package com.mygdx.game;

public class Rain {
	int ix = 0;
	int y;
Rain(int x, int y)
{
	ix = x;
	this.y = y;
}
void resetRain()
{
	y = 1000 + (int)(Math.random() * Grid.BLOCKSIZE);
}
void draw(int speed)
{
	y-= speed;
	y-= DirectionControl.dy;
}
int totalY()
{
	return y;
}
int totalX()
{
	return ix;
}
}
