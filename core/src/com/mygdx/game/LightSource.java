package com.mygdx.game;

import java.util.ArrayList;

public class LightSource {
	int _id;
	int _jd;
	int magnitude;
	
LightSource()
{
	
}
void draw(int _id, int _jd, int magnitude)
{
	this._id = _id;
	this._jd = _jd;
	this.magnitude = magnitude;
	MyGdxGame.lightSystem.lights.add(this);
}
}
