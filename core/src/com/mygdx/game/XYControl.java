package com.mygdx.game;

import java.util.ArrayList;
	public class XYControl {
		static int vocsxOff = 600;
		static int vocsyOff = 600;
		XYControl()
		{
			
		}
		float x(float ix, float current,float speed, float factor1)
		{
			return  (ix - current + speed + factor1);
			
		}
		static public boolean Visible(float x, float y,int yDis,float xDev,float yDev)
		{
			if (x < 2100 + xDev&& x > -100 - xDev && yDis == 1)
			{
				return true;
			}
			else if (yDis == 1)
			{
				for(int i = 0; i < MyGdxGame.vocsxSet.size();i++)
				{
					if(x < MyGdxGame.vocsxSet.get(i) + vocsxOff + 200 && x > MyGdxGame.vocsxSet.get(i) - vocsxOff - 200)
					{
						return true;
					}
				}
				return false;
			}
			else if (y > -100 - yDev && y < 1100 + yDev&&x < 2100 + xDev && x > -100 - xDev)
			{
				if (MyGdxGame.display || MyGdxGame.load == false)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else
			{
				if(MyGdxGame.display||MyGdxGame.load==false)
				{
				for(int i = 0;i<MyGdxGame.vocsySet.size();i++)
				{
					if(x < MyGdxGame.vocsxSet.get(i) + vocsxOff && x > MyGdxGame.vocsxSet.get(i) - vocsxOff && y < MyGdxGame.vocsySet.get(i) + vocsyOff && y > MyGdxGame.vocsySet.get(i)-vocsyOff)
					{
						return true;
					}
				}
				}
				return false;
			}
			
		}
		static public void setvocs()
		{
			
			
		}
	}


