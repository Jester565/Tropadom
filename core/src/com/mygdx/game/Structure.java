package com.mygdx.game;

public class Structure {
	int jd;
	int id;
	int type;
	int next = 0;
	int y;
	int height2;
	int height = 0;
	int slope;
	int flat;
	int sandLength;
	int sandLength2;
	boolean manager1;
	Structure(int _id, int _jd, int _type,boolean manager1)
	{
		id = _id;
		jd = _jd;
		type = _type;
		this.manager1 = manager1;
	}
	void tree()
	{
		y = jd + 2;
		height2 = 6;
		if (manager1)
		{
			if (next == 0)
			{	
				GridManager.Type.set(jd +2, 10);
			}
		}
		else
		{
			if (next == 0)
			{	
				GridManager2.Type.set(jd +2, 10);
			}
		}
		
	}
	void lake(int SLOPER, int FLATER)
	{
		if (next == 0)
		{
			 slope = (int) (Math.random() * SLOPER);
			 flat = (int) (Math.random() * FLATER);
			 sandLength = (int)(Math.random() * 5);
			 sandLength2 = (int)(Math.random() * 5);
		}
		y = jd - 5;
		height2 = 40;
		int floorType = (int) (Math.random() * 4);
		int hOff = (int) (Math.random() * 4);
		if (next >= 0 && next < sandLength)
		{
			clear(jd);
			if (manager1)
			{
				for (int i = 0; i < hOff + 1; i++)
				{
					GridManager.Type.set(jd -i, 9);
				}
			}
			else
			{
				for (int i = 0; i < hOff + 1; i++)
				{
					
					GridManager2.Type.set(jd -i, 9);
				}
			}
		}
		if (next >=  sandLength&& next <= slope + sandLength)
		{
			height ++;
			clear(jd);
			
			for (int i = 0; i < height + hOff; i++)
			{
			if (i == height + hOff - 2)
			{
				if(manager1)
				{
					if (floorType <= 3)
					{
						GridManager.Type.set(jd -i, 9);
					}
					else
					{
						GridManager.Type.set(jd -i, 2);
					}
				}
				else
				{
					if (floorType <= 3)
					{
						GridManager2.Type.set(jd -i, 9);
					}
					else
					{
						GridManager2.Type.set(jd -i, 2);
					}
					
				}
			}
			if (i == height + hOff -1)
			{
				if(manager1)
				{
				if (floorType < 2)
				{
					GridManager.Type.set(jd -i, 9);
				}
				else
				{
					GridManager.Type.set(jd -i, 2);
				}
				}
				else
				{
					if (floorType < 2)
					{
						GridManager2.Type.set(jd -i, 9);
					}
					else
					{
						GridManager2.Type.set(jd -i, 2);
					}
				}
			}
			else
			{
				if(manager1)
				{
					GridManager.Type.set(jd -i, 8);
				}
				else
				{
					GridManager2.Type.set(jd -i, 8);
				}
			}
			}
		}
		if (next >= slope + sandLength&& next <= flat + slope + sandLength)
		{
			clear(jd);
			for (int i = 0; i < height + hOff; i++)
			{
				if (i == height + hOff -2)
				{
					if(manager1)
					{
					if (floorType <= 3)
					{
						GridManager.Type.set(jd -i, 9);
					}
					else
					{
						GridManager.Type.set(jd -i, 2);
					}
					}
					else
					{
						if (floorType <= 3)
						{
							GridManager2.Type.set(jd -i, 9);
						}
						else
						{
							GridManager2.Type.set(jd -i, 2);
						}
					}
				}
				if (i == height + hOff - 1)
				{
					if(manager1)
					{
						if (floorType < 2)
						{
							GridManager.Type.set(jd -i, 9);
						}
						else
						{
							GridManager.Type.set(jd -i, 2);
						}
					}
					else
					{
						if (floorType < 2)
						{
							GridManager2.Type.set(jd -i, 9);
						}
						else
						{
							GridManager2.Type.set(jd -i, 2);
						}
					}
				}
				else
				{
					if(manager1)
					{
						GridManager.Type.set(jd -i, 8);
					}
					else
					{
						GridManager2.Type.set(jd -i, 8);
					}
				}
			}
		}
		if (next > slope + flat + sandLength&& next <= slope *2 + flat + sandLength)
		{
			height --;
			clear(jd);
			
			for (int i = 0; i < height + hOff; i++)
			{
				if (i == height + hOff - 2)
				{
					if (manager1)
					{
					if (floorType <= 3)
					{
						GridManager.Type.set(jd -i, 9);
					}
					else
					{
						GridManager.Type.set(jd -i, 2);
					}
					}
					else
					{
						if (floorType <= 3)
						{
							GridManager2.Type.set(jd -i, 9);
						}
						else
						{
							GridManager.Type.set(jd -i, 2);
						}
					}
				}
				if (i == height + hOff - 1)
				{
					if (manager1)
					{
					if (floorType < 2)
					{
						GridManager.Type.set(jd -i, 9);
					}
					else
					{
						GridManager.Type.set(jd -i, 2);
					}
					}
					else
					{
						if (floorType < 2)
						{
							GridManager2.Type.set(jd -i, 9);
						}
						else
						{
							GridManager2.Type.set(jd -i, 2);
						}
					}
				}
				else
				{
					if (manager1)
					{
						GridManager.Type.set(jd -i, 8);
					}
					else
					{
						GridManager2.Type.set(jd -i, 8);
					}
				}
			}
		}
		if (next > slope *2 + flat + sandLength && next < slope *2 + flat + sandLength + sandLength2)
		{
			clear(jd);
			for (int i = 0; i < hOff + 1; i++)
			{
				if (manager1)
				{
					GridManager.Type.set(jd -i, 9);
				}
				else
				{
					GridManager2.Type.set(jd -i, 9);
				}
			}
		}
		
	}
	void create()
	{
		
		if (type == 1)
		{
			tree();
		}
		else if (type == 2)
		{
			lake(5,15);
		}
		else if (type == 3)
		{
			lake(15,40);
		}
		else if (type == 4)
		{
			lake(5,10);
		}
		next++;
	}
	void clear(int y)
	{
		
		for (int i = y; i < height2 + y; i++)
		{
			if (i < 88)
			{
				if (manager1)
				{
					GridManager.Type.set(i, 0);
				}
				else
				{
					GridManager2.Type.set(i,0);
				}
			}
		}
	}
	void setBase(int topType)
	{
		for (int i = GridManager.COBBLEH + 1; i < y; i++)
		{
			if (i < 88)
			{
			if (i == y -1)
			{
				if (manager1)
				{
					GridManager.Type.set(i, 3);
				}
				else
				{
					GridManager2.Type.set(i, 3);
				}
			}
			else
			{
				if (manager1)
				{
					GridManager.Type.set(i, 2);
				}
				else
				{
					GridManager2.Type.set(i, 2);
				}
			}
			}
		}
	}
}

