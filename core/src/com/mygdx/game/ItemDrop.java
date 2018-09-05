package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ItemDrop {
	Sprite Img = new Sprite();
	int itemType = 3;
	float dx = 4;
	float x = 0;
	float y = 0;
	float ix;
	float iy;
	float dy = 0;
	int hudDropT = 0;
	int count;
	int taken = 0;
	int currentD = 0;
	boolean transitionReady = true;
	SpriteBatch batch = new SpriteBatch();
	
	ItemDrop(String img, int itemType, int count, float x, float y)
	{
		this.x = x;
		this.y = y;
		Img = new Sprite(new Texture(img));
		this.itemType = itemType;
		this.count= count;
	}
	void pistonPush(float px, float py)
	{
		if (x + 20 > px && x < px + Grid.BLOCKSIZE && y > py && y < py + Grid.BLOCKSIZE)
		{
			dy = 10;
		}
	}
	void draw()
	{
		if (taken == 0 && XYControl.Visible (x,y,0,0,0))
		{
		if (dx > 0)
		{
			dx -= .09f;
		}
		if (dx < 0)
		{
			dx = 0;
		}
		if (dy > -3)
		{
			dy -= .2f;
		}
		//y -= DirectionControl.directionOn2(ix - DirectionControl.current + x, iy + DirectionControl.currentY + y,20,20,5);
		x -= MyGdxGame.dc.realSpeed;
		y -= MyGdxGame.dc.dy();
		if (GridBased.downStop(x, y, 20, 20) == false)
		{
			if (currentD == 0)
			{
				y += dy;
			}
		}
		else
		{
			dy = 0;
			if (dx > 0)
			{
				dx -= .2f;
			}
		}
		Img.setSize(20, 20);
		if (GridBased.pipeD(x, y - 10, currentD, true) == 0)
		{
			transitionReady = true;
		}
		if (GridBased.pipeD(x, y - 10, currentD,transitionReady) != 0)
		{
			if (GridBased.insideX(x, y)!= -1)
			{
				x+= GridBased.insideX( x, y-10) - x;
				y+= GridBased.insideY(x, y-10) - (y - 10);
			}
			currentD = GridBased.pipeD(x, y - 10, currentD,transitionReady);
			transitionReady = false;
		}
		if (GridBased.inputInventoryCheck(x, y - 10, itemType, count))
		{
			taken = 1;
		}
		if (currentD == 1)
		{
			y --;
			if (GridBased.downStop(x, y - 10,2,2))
			{
				currentD = 3;
			}
		}
		if (currentD == 2)
		{
			x++;
			if (GridBased.rightStop(x, y - 10,2,2))
			{
				currentD = 4;
			}
		}
		if (currentD == 3)
		{
			y++;
			if (GridBased.upStop(x, y - 10,2,2))
			{
				currentD = 1;
			}
		}
		if (currentD == 4)
		{
			x--;
			if (GridBased.leftStop(x, y - 10,2,2))
			{
				currentD = 2;
			}
		}
		
		if (GridBased.insideType(x, y) != 21 && GridBased.insideType(x, y) != -1 && GridBased.insideType(x,y) != 22)
		{
			currentD = 0;
		}
		Img.setPosition(x,y - 10);
		batch.begin();
		Img.draw(batch);
		batch.end();
		if (Button.button(x, y - 10, 20, 20, Gdx.input.isButtonPressed(Input.Buttons.LEFT)) && XYControl.Visible(x, y - 10, 0, -950,-400))
		{
			if (HUD.inventoryFull == false || HUD.inventory.item(itemType - 1).count > 0)
			{
			HUD.inventory.item(itemType -1).add(count);
			taken = 1;
			}
		}
		}
	}
}
