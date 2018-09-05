package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TitleManager {
	SpriteBatch batch;
	Texture cMenu3;
	Sprite CMenu3;
	Texture arm;
	Texture arm2;
	Texture arm3;
	Texture arm4;
	Texture arm5;
	Texture arm6;
	Texture arm7;
	Texture l1;
	Texture l2;
	Texture l3;
	Texture l4;
	Texture l5;
	Texture l6;
	Texture l7;
	Texture b1;
	Texture b2;
	Texture b3;
	Texture b4;
	Texture b5;
	Texture h1;
	Texture h2;
	Texture ha1;
	Texture ha2;
	Texture ha3;
	Texture ha4;
	Texture ha5;
	Texture s1;
	Texture s2;
	Texture s3;
	Texture s4;
	Texture s5;
	Texture s6;
	Texture s7;
	Texture title3;
	Sprite Title3;
	Texture cMenuB;
	Sprite CMenuB;
	Texture cMenu;
	Sprite CMenu;
	Texture cMenu2;
	Sprite CMenu2;
	Texture border;
	Sprite Border;
	Texture lm;
	Sprite LM;
	Texture lm2;
	Sprite LM2;
	Texture ng;
	Sprite NG;
	Texture lg;
	Sprite LG;
	Texture options;
	Sprite OPTIONS;
	Texture ngr;
	Sprite NGR;
	Texture lgr;
	Sprite LGR;
	Texture optionsR;
	Sprite OPTIONSR;
	Texture title;
	Sprite Title;
	Texture scroll;
	Sprite Scroll;
	Texture scroll2;
	Sprite Scroll2;
	public static int hI = 0;
	float hX = 0;
	float bX = 0;
	public static int bI = 0;
	float sX = 0;
	public static int sI = 0;
	float lX = 0;
	public static int lI = 0;
	public static boolean loadMenu = false;
	int timer = 0;
	int bTimer = 0;
	float x = 0;
	float y = 0;
	boolean newG = false;
	public static ArrayList<Sprite>heads;
	public static ArrayList<Sprite>hair;
	public static ArrayList<Sprite>beard;
	public static ArrayList<Sprite>shirt;
	public static ArrayList<Sprite>arms;
	public static ArrayList<Sprite>legs;
TitleManager()
{
	title3 = new Texture("title.png");
	Title3 = new Sprite(title3);
	cMenu3 = new Texture("characterBackGround3.png");
	CMenu3 = new Sprite(cMenu3);
	l1 = new Texture("leg.png");
	l2 = new Texture("leg2.png");
	l3 = new Texture("leg3.png");
	l4 = new Texture("leg4.png");
	l5 = new Texture("leg5.png");
	l6 = new Texture("leg6.png");
	l7 = new Texture("leg7.png");
	arm = new Texture("arm.png");
	arm2 = new Texture("arm2.png");
	arm3 = new Texture("arm3.png");
	arm4 = new Texture("arm4.png");
	arm5 = new Texture("arm5.png");
	arm6 = new Texture("arm6.png");
	arm7 = new Texture("arm7.png");
	h1 = new Texture("head1.png");
	ha1 = new Texture("hair1.png");
	ha2 = new Texture("hair2.png");
	ha3 = new Texture("hair3.png");
	ha4 = new Texture("hair4.png");
	ha5 = new Texture("hair5.png");
	b1 = new Texture("beard1.png");
	b2 = new Texture("beard2.png");
	b3 = new Texture("beard3.png");
	b4 = new Texture("beard4.png");
	b5 = new Texture("beard5.png");
	s1 = new Texture("torso.png");
	s2 = new Texture("torso2.png");
	s3 = new Texture("torso3.png");
	s4 = new Texture("torso4.png");
	s5 = new Texture("torso5.png");
	s6 = new Texture("torso6.png");
	s7 = new Texture("torso7.png");
	
	shirt = new ArrayList<Sprite>();
	heads = new ArrayList<Sprite>();
	hair = new ArrayList<Sprite>();
	beard = new ArrayList<Sprite>();
	arms = new ArrayList<Sprite>();
	legs = new ArrayList<Sprite>();
	legs.add(new Sprite(l1));
	legs.add(new Sprite(l2));
	legs.add(new Sprite(l3));
	legs.add(new Sprite(l4));
	legs.add(new Sprite(l5));
	legs.add(new Sprite(l6));
	legs.add(new Sprite(l7));
	arms.add(new Sprite(arm));
	arms.add(new Sprite(arm2));
	arms.add(new Sprite(arm3));
	arms.add(new Sprite(arm4));
	arms.add(new Sprite(arm5));
	arms.add(new Sprite(arm7));
	arms.add(new Sprite(arm6));
	heads.add(new Sprite(h1));
	hair.add(new Sprite (ha1));
	hair.add(new Sprite (ha2));
	hair.add(new Sprite (ha3));
	hair.add(new Sprite (ha4));
	hair.add(new Sprite (ha5));
	beard.add(new Sprite(b1));
	beard.add(new Sprite(b2));
	beard.add(new Sprite(b3));
	beard.add(new Sprite(b4));
	beard.add(new Sprite(b5));
	shirt.add(new Sprite(s1));
	shirt.add(new Sprite(s2));
	shirt.add(new Sprite(s3));
	shirt.add(new Sprite(s4));
	shirt.add(new Sprite(s5));
	shirt.add(new Sprite(s6));
	shirt.add(new Sprite(s7));
	cMenuB = new Texture("characterBackGroundBack.png");
	CMenuB = new Sprite(cMenuB);
	batch = new SpriteBatch();
	cMenu = new Texture("characterBackGround.png");
	CMenu = new Sprite(cMenu);
	cMenu2 = new Texture("characterBackGround2.png");
	CMenu2 = new Sprite(cMenu2);
	border = new Texture("titleBorder.png");
	lm = new Texture("loadBackGround.png");
	LM = new Sprite (lm);
	lm2 = new Texture("loadBackGround2.png");
	LM2 = new Sprite(lm2);
	Border = new Sprite(border);
	title = new Texture("titleMenu.png");
	Title = new Sprite(title);
	scroll = new Texture("titleMenuScroll.png");
	Scroll = new Sprite(scroll);
	scroll2 = new Texture("titleMenuScroll2.png");
	Scroll2 = new Sprite(scroll2);
	ng = new Texture("newGameTitle.png");
	NG = new Sprite(ng);
	ngr = new Texture("newGameTitleR.png");
	NGR = new Sprite(ngr);
	lg = new Texture("loadGameTitleR.png");
	LG = new Sprite(lg);
	lgr = new Texture("loadGameTitle.png");
	LGR = new Sprite(lgr);
	options = new Texture("optionsGameTitle.png");
	OPTIONS = new Sprite(options);
	optionsR = new Texture("optionsGameTitleR.png");
	OPTIONSR = new Sprite(optionsR);
	
}
void draw()
{
	if (hI * 300 > hX)
	{
		hX += 10;
	}
	if (hI * 300 < hX)
	{
		hX -= 10;
	}
	if (bI * 300 > bX)
	{
		bX += 10;
	}
	if (bI * 300 < bX)
	{
		bX -= 10;
	}
	if (sI * 300 > sX)
	{
		sX += 10;
	}
	if (sI * 300 < sX)
	{
		sX -= 10;
	}
	if (lI * 300 > lX)
	{
		lX += 10;
	}
	if (lI * 300 < lX)
	{
		lX -= 10;
	}
	timer--;
	bTimer--;
	Scroll.setScale((float) .7);
	Scroll.setPosition(-100, -50);
	Scroll2.setScale((float) .7);
	Scroll2.setPosition(-100, -50);
	if (newG)
	{
		if(Button.overButton(1221,678,74, 64))
		{
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && bTimer < 0 && hI > -hair.size()+1)
			{
				hI--;
				bTimer = 20;
			}
		}
		if(Button.overButton(572,678,74, 64))
		{
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && bTimer < 0 && bI < 0)
			{
				bI++;
				bTimer = 20;
			}
		}
		if(Button.overButton(1320,678,74, 64))
		{
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && bTimer < 0 && bI > -beard.size())
			{
				bI--;
				bTimer = 20;
			}
		}
		if(Button.overButton(658,678,74, 64))
		{
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && bTimer < 0 && hI < 0)
			{
				hI++;
				bTimer = 20;
			}
		}
		if(Button.overButton(660,435,74, 64))
		{
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && bTimer < 0 && sI < 0)
			{
				sI++;
				bTimer = 20;
			}
		}
		if(Button.overButton(1221,435,74, 64))
		{
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && bTimer < 0 && sI > -shirt.size()+1)
			{
				sI--;
				bTimer = 20;
			}
		}
		if(Button.overButton(660,166,74, 64))
		{
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && bTimer < 0 && lI < 0)
			{
				lI++;
				bTimer = 20;
			}
		}
		if(Button.overButton(1221,166,74, 64))
		{
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && bTimer < 0 && lI > -legs.size()+1)
			{
				lI--;
				bTimer = 20;
			}
		}
	}
	if (loadMenu)
	{
		if(x > -2000)
		{
			x-=40;
		}
		
	}
	else
	{
		
		if(x< 0)
		{
			x+=40;
		}
	}
	if(newG)
	{
		if(y > -1000)
		{
			y-=20;
		}
	}
	else
	{
		if(y < 0)
		{
			y +=20;
		}
	}
	batch.enableBlending();
	batch.begin();
	Border.draw(batch);
	Border.setPosition(x - 10, y);
	NG.setPosition(x-10, y);
	NGR.setPosition(x-10, y);
	LG.setPosition(x-10, y);
	LGR.setPosition(x-10, y);
	Title.setPosition(x - 10, y);
	LM.setPosition(x + 1990, y);
	LM2.setPosition(x+1990, y);
	CMenu.setPosition(x - 10, y + 1000);
	CMenu2.setPosition(x-10,y+1000);
	CMenu3.setPosition(x-10, y+ 1000);
	OPTIONS.setPosition(x -10,y);
	OPTIONSR.setPosition(x-10, y);
	Title.draw(batch);
	CMenuB.setPosition(x-10, y+1000);
	CMenuB.draw(batch);
	Title3.setPosition(x-10, y);
	Title3.draw(batch);
	for(int i = 0; i < heads.size(); i ++)
	{
		heads.get(i).setScale(6);
		heads.get(i).setPosition(i * 300 + 900 + x , y + 600+1000);
		heads.get(i).draw(batch);
	}
	for(int i = 0; i < beard.size(); i ++)
	{
		beard.get(i).setScale(6);
		beard.get(i).setPosition(i * 300 + 900 + x + bX , y + 600+1000);
		beard.get(i).draw(batch);
	}
	for(int i = 0; i < hair.size(); i ++)
	{
		hair.get(i).setScale(6);
		hair.get(i).setPosition(i * 300 + 900 + x + hX , y + 600+1000);
		hair.get(i).draw(batch);
	}
	for(int i = 0; i <legs.size(); i ++)
	{
		legs.get(i).setScale(6.3f);
		legs.get(i).setPosition(i * 300 + 900 + x + lX , y + 410+1000);
		legs.get(i).draw(batch);
	}
	for(int i = 0; i < shirt.size(); i ++)
	{
		shirt.get(i).setScale(6);
		shirt.get(i).setPosition(i * 300 + 900 + x + sX , y + 520+1000);
		shirt.get(i).draw(batch);
		arms.get(i).setScale(6);
		arms.get(i).setPosition(i * 300 + 880 + x + sX, y + 1630);
		arms.get(i).setRotation(-70);
		arms.get(i).draw(batch);
	}
	CMenu2.draw(batch);
	SaveManager.xOff = (int) (x + 2040);
	if (Button.overButton(837 + x - 10,456 + y, 476, 108)||Button.overButton(930 + x - 10,23 + y + 1000, 109, 46))
	{
		NGR.draw(batch);
		CMenu.draw(batch);
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT))
		{
			if (newG && bTimer < 0)
			{
				newG = false;
				bTimer = 20;
			}
			if(newG == false && bTimer < 0)
			{
			newG = true;
			bTimer = 20;
			}
			
		}
	}
	else if (Button.overButton(754 + x,894 + y + 1000, 450, 67))
	{
		CMenu3.draw(batch);
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT))
		{
			MyGdxGame.newG = true;
		}
	}
	else
	{
		
		NG.draw(batch);
	}
	
	if (Button.overButton(837 + x - 10,326 + y, 476, 108)||Button.overButton(x + 2000 + 105, y+440, 145, 77))
	{
		LM.draw(batch);
		LGR.draw(batch);
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT))
		{
			if(loadMenu && bTimer < 0)
			{
				loadMenu = false;
				bTimer = 20;
			}
			if(loadMenu == false && bTimer < 0)
			{
				loadMenu = true;
				bTimer = 20;
			}
		}
	}
	else
	{
		LM2.draw(batch);
		LG.draw(batch);
	}
	if (Button.overButton(837 + x - 10,193 + y, 476, 108))
	{
		OPTIONSR.draw(batch);
	}
	else
	{
		OPTIONS.draw(batch);
	}
	if (timer < 100)
	{
		//Scroll.draw(batch);
	}
	else
	{
		//Scroll2.draw(batch);
	}
	if(timer < 0)
	{
		timer = 200;
	}
	batch.end();
}
}
