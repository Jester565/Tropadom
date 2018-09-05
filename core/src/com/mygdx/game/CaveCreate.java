package com.mygdx.game;

public class CaveCreate {
	float x = 0;
	float iy;
	float y;
	float xDev = 0;
	float h;
	float goalX;
	float goalY;
	float endX;
	float  ixd;
	float iyd;
	float end;
	float initialRegistered = 0;
CaveCreate( float _iy, float _ixd, float _iyd, float _end)
{
	iy = 20;
	ixd = _ixd;
	iyd = _iyd;
	end = _end;
}
void refresh()
{
	x++;
	endX++;
	h = 2 + (float)(Math.random() * 2);

}


void cordGet()
{
	if (end > endX)
	{
	if (y > GridManager.COBBLEH)
	{
		goalY = 5;
	}
	if (y - h/2 - 10 < 0)
	{
		goalY = 15;
	}
	if (x > goalX)
	{
		x=0;
		
			iy = y;
		
		if (initialRegistered == 1)
		{
		goalX = (float) ( Math.random() * 20);
		goalY = (float)( Math.random() * 20);
		}
		else
		{
			goalX = ixd;
			goalY = iyd;
			initialRegistered = 1;
		}
		
	}
	
	y = ((goalY-10)/goalX)*x + iy;
	for (int i = Math.round(y - h/2); i < Math.round(y + h/2); i++)
	{
		if (i == 1)
		{
			GridManager.Type.set(i, 1);
		}
		else if (i < GridManager.Type.size() && i > 0)
		{
		GridManager.Type.set(i, 0);
		}
	}
	
}
}
}
