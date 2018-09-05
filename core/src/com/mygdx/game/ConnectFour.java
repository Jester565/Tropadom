package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ConnectFour {
	BitmapFont bitmap;
	static int difficulty = 0;
	static boolean breakPoint = false;
	int computerScore = 0;
	int playerScore = 0;
	int winDisplayTimer = 0;
	int backGroundX = 0;
	int backGroundX2 = -2000;
	boolean motionOff = false;
	public static ArrayList<SlotStack>slotStacks;
	boolean created = false;
	double turnPick = Math.random();
	public static boolean playerWin = false;
	public static boolean win = false;
	public static boolean played = false;
	public static int playerPlaceTimer = 0;
	public static int computerRandomPick = (int)(Math.random()*7+1);
	public static boolean playerTurn = false;
	SpriteBatch batch;
	Texture _template;
	Sprite template;
	Sprite playerWins;
	Sprite alexWins;
	Sprite backGround;
	Sprite difficulty0;
	Sprite difficulty1;
	Sprite difficulty2;
	Sprite difficulty3;
	Sprite difficultyBack;
	ConnectFour()
	{
		difficultyBack = new Sprite(new Texture("connect4DifficultyBack.png"));
		bitmap = new BitmapFont();
		alexWins = new Sprite(new Texture("connect4AlexWins.png"));
		playerWins = new Sprite(new Texture("connect4PlayerWins.png"));
		slotStacks = new ArrayList<SlotStack>();
		batch = new SpriteBatch();
		_template = new Texture("connect4Template.png");
		template = new Sprite(_template);
		backGround = new Sprite(new Texture("connect4BackGround.png"));
		difficulty0 = new Sprite(new Texture("connect4Difficulty.png"));
		difficulty1 = new Sprite(new Texture("connect4Difficulty1.png"));
		difficulty2 = new Sprite(new Texture("connect4Difficulty2.png"));
		difficulty3 = new Sprite(new Texture("connect4Difficulty3.png"));
		if(turnPick <= .5f)
		{
			playerTurn = true;
		}
	}
void difficultyMenuManager()
{
	int xOff = 150;
	difficultyBack.setPosition(xOff,0);
	difficulty1.setPosition(xOff,0);
	difficulty2.setPosition(xOff,0);
	difficulty3.setPosition(xOff,0);
	difficulty0.setPosition(xOff,0);
	batch.begin();
	difficultyBack.draw(batch);
	if(Button.overButton(250+xOff, 413, 321, 225))
	{
		difficulty1.draw(batch);
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT))
		{
			difficulty = 1;
		}
	}
	else if (Button.overButton(580+xOff, 413, 321, 225))
	{
		difficulty2.draw(batch);
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT))
		{
			difficulty = 2;
		}
	}
	else if (Button.overButton(910+xOff, 413, 321, 225))
	{
		difficulty3.draw(batch);
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT))
		{
			difficulty = 3;
		}
	}
	else
	{
		difficulty0.draw(batch);
	}
	batch.end();
	playerPlaceTimer = 20;
}
void backGroundManager()
{
	if(motionOff == false)
	{
	backGroundX++;
	backGroundX2++;
	}
	if(backGroundX > 2000)
	{
		backGroundX = -2000;
	}
	if(backGroundX2 > 2000)
	{
		backGroundX2 = -2000;
	}
	batch.begin();
	backGround.setPosition(backGroundX, 0);	
	backGround.draw(batch);
	backGround.setPosition(backGroundX2, 0);
	backGround.draw(batch);
	batch.end();
	if(Gdx.input.isKeyPressed(Input.Keys.W))
	{
		motionOff = true;
	}
}
void winCheck()
{
	for(int i = 0; i < 7; i++)
	{
		slotStacks.get(i).winCheck(1);
	}
	if(win && winDisplayTimer < 0)
	{
		playerWin = true;
		winDisplayTimer = 9000;
		playerScore++;
	}
	for(int i = 0; i < 7; i++)
	{
		slotStacks.get(i).winCheck(2);
	}
	if(win && winDisplayTimer < 0)
	{
		playerWin = false;
		winDisplayTimer = 9000;
		computerScore++;
	}
	
}
void resetBoard()
{
	for(int i = 0; i < 7; i++)
	{
		for(int j = 0; j < 6;j++)
		{
			slotStacks.get(i).slots.get(j).type = 0;
		}
	}
	win = false;
}
void computerManager()
{
	if(playerTurn == false && difficulty > 0 && win == false && playerPlaceTimer == 18)
	{
		slotStacks.get(computerSlotChooser()).addSlot(2);
	}
}
int computerSlotChooser()
{
	int randomSeven;
	int randomNegative;
	if(Math.random()<.5f)
	{
		 randomSeven = 6;
		 randomNegative = -1;
	}
	else
	{
		randomSeven = 0;
		randomNegative = 1;
	}
	for(int i = 0; i < 7; i++)
	{
		if(slotStacks.get(i*randomNegative+randomSeven).computerManager(2,3,0))
		{
			System.out.println("0");
			return i*randomNegative+randomSeven;	
		}
	}
	for(int i = 0; i < 7; i++)
	{
		if(slotStacks.get(i*randomNegative+randomSeven).computerManager(1, 3,0))
		{
			System.out.println("1");
			return i*randomNegative+randomSeven;
		}
	}
	for(int i = 0; i < 7; i++)
	{
		if(slotStacks.get(i*randomNegative+randomSeven).computerManager(1, 2,0))
		{
			if(slotStacks.get(i*randomNegative+randomSeven).computerManager(1, 2, 1) == false)
			{
				return i*randomNegative+randomSeven;
			}
		}
	}
	for(int i = 0; i < 7; i++)
	{
		if(slotStacks.get(i*randomNegative+randomSeven).computerManager(2, 2, 0))
		{
			
			if(slotStacks.get(i*randomNegative+randomSeven).computerManager(1,3,1)==false)
			{
				
				System.out.println("3");
				return i*randomNegative+randomSeven;
			}
		}
	}
	for(int i = 0; i < 7; i++)
	{
		if(slotStacks.get(i*randomNegative+randomSeven).computerManager(2, 2, 0))
		{
			if(slotStacks.get(i*randomNegative+randomSeven).computerManager(1,3,1)==false)
			{
				System.out.println("4");
				return i*randomNegative+randomSeven;
			}
		}
	}
	int row = (int)(Math.random() * 7d);
	while(slotStacks.get(row).slotsFilled() >= 7){
		row = (int)(Math.random() * 7d);
	}
	return row;
}
void draw()
{
	backGroundManager();
	if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && winDisplayTimer < -8 || Gdx.input.isKeyPressed(Input.Keys.SPACE) && winDisplayTimer > 2 )
	{
		winDisplayTimer = 2;
		resetBoard();
	}
	winDisplayTimer--;
	playerPlaceTimer--;
	if(created == false)
	{
		for(int i = 0; i < 7;i++)
		{
			SlotStack slotStack;
			slotStack = new SlotStack(i);
			slotStacks.add(slotStack);
		}
	}
	batch.begin();
	template.draw(batch);
	batch.end();
	for(int i = 0; i < 7;i++)
	{
		slotStacks.get(i).draw();
	}
	created = true;
	computerManager();
	if(played)
	{
		winCheck();
	}
	if(win)
	{
		playerPlaceTimer = 20;
		batch.begin();
		if(playerWin == false)
		{
			alexWins.draw(batch);
		}
		else
		{
			playerWins.draw(batch);
		}
		batch.end();
		if(winDisplayTimer < 0)
		{
			resetBoard();
			win = false;
		} 
	}
	played = false;
	batch.begin();
	//bitmap.setScale(2f);
	bitmap.setColor(0,0,1,1);
	//bitmap.drawMultiLine(batch, Integer.toString(computerScore), 1850, 730);
	bitmap.setColor(1,0,0,1);
	//bitmap.drawMultiLine(batch, Integer.toString(playerScore), 1800, 810);
	batch.end();
	if(difficulty == 0)
	{
		difficultyMenuManager();
	}
}
public static int getType(int id, int jd)
{
	return slotStacks.get(id).getType(jd);
}
void developerControls()
{
	if(Gdx.input.isKeyPressed(Input.Keys.B))
	{
		breakPoint = true;
	}
}
}
class SlotStack {
	int id;
	ArrayList<Slot>slots;
	SlotStack(int id)
	{
		this.id = id;
		slots = new ArrayList<Slot>();
		for(int j = 0; j < 6;j++)
		{
			Slot slot;
			slot = new Slot(id,j);
			slots.add(slot);
		}
	}
	int slotsFilled(){
		int amount = 0;
		for(int i = 0; i < slots.size();i++){
			if(slots.get(i).type != 0){
				amount++;
			}
		}
		return amount;
	}
	int slotPlacePredict()
	{
		for(int i = 0; i < 6; i++)
		{
			if(slots.get(i).type == 0)
			{
				return i;
			}
		}
		return 50;
	}
	void winCheck(int type)
	{
		for(int i = 0; i < 6; i++)
		{
			slots.get(i).winCheck(type);
		}
	}
	boolean computerManager(int type, int length, int heightOff)
	{
		
		if(slotPlacePredict() < 10)
		{
				if(heightOff == 0)
				{
					return slots.get(slotPlacePredict()).computerCheck(type, length,0,false);
				}
				else if (slotPlacePredict()+1<6)
				{
					System.out.println(slots.get(slotPlacePredict()+1).computerCheck2(type, length, 0, false));
					return slots.get(slotPlacePredict()+1).computerCheck(type, length, 0, false);
				}
		}
		return false;
	}
	void playerDropManager()
	{
		if(ConnectFour.playerTurn && ConnectFour.playerPlaceTimer < 0)
		{
			if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && ConnectFour.difficulty > 0)
			{
				if(Gdx.input.getX()>300+180*id && Gdx.input.getX()<480+180*id && slotsFilled() < 7)
				{
					addSlot(1);
				}
			}
		}
	}
	int getType(int jd)
	{
		return slots.get(jd).type;
	}
	void addSlot(int type)
	{
		for(int i = 0; i < 6;i++)
		{
			if(slots.get(i).type==0)
			{
				slots.get(i).type = type;
				if(type == 1)
				{
					ConnectFour.playerTurn = false;
					ConnectFour.playerPlaceTimer = 30;
				}
				if(type == 2)
				{
					ConnectFour.playerTurn = true;
				}
				ConnectFour.played = true;
				i = 10;
			}
		}
	}
	void draw()
	{
		playerDropManager();
		for(int i = 0; i < 6; i++)
		{
			slots.get(i).draw();
		}
	}
}
class Slot{
	int type = 0;
	int id;
	int jd;
	int scanNumber = 0;
	SpriteBatch batch;
	Texture _template;
	Texture _emptySlot;
	Texture _blueSlot;
	Texture _redSlot;
	Sprite template;
	Sprite emptySlot;
	Sprite blueSlot;
	Sprite redSlot;
	Sprite blueWin;
	Sprite redWin;
	Slot(int id, int jd)
	{
		this.id = id;
		this.jd = jd;
		batch = new SpriteBatch();
		_emptySlot = new Texture("connect4Slot.png");
		_blueSlot = new Texture("connect4SlotBlue.png");
		_redSlot = new Texture("connect4SlotRed.png");
		emptySlot = new Sprite(_emptySlot);
		blueSlot = new Sprite(_blueSlot);
		redSlot = new Sprite(_redSlot);
		blueWin = new Sprite(new Texture("connect4SlotBlueWin.png"));
		redWin = new Sprite(new Texture("connect4SlotRedWin.png"));
	}
	void draw()
	{
		batch.begin();
		if(type == 1)
		{
			blueSlot.setScale(.8f);
			blueSlot.setPosition(300+180*id,94+130*jd);
			blueSlot.draw(batch);
		}
		else if(type == 2)
		{
			redSlot.setScale(.8f);
			redSlot.setPosition(300+180*id,94+130*jd);
			redSlot.draw(batch);
		}
		else
		{
			emptySlot.setScale(.8f);
			emptySlot.setPosition(300+180*id,94+130*jd);
			emptySlot.draw(batch);
		}
		batch.end();
	}
	boolean computerCheck(int type, int length, int heightOff, boolean winDetect)
	{
		if(ConnectFour.breakPoint)
		{
			System.out.println("active");
		}
		boolean oneDis = false;
		boolean twoDis = false;
		int typeTrueAmount = 0;
		for(int j = 0; j < length+3;j++)
		{
			if(oneDis == false && j + jd >= 0 && j + jd < 6 && ConnectFour.getType(id,jd+j)==type)
			{
				typeTrueAmount++;
			}
			else if (j != 0)
			{
				oneDis = true;
			}
			if(twoDis == false && -j + jd>= 0 && -j + jd< 6 && ConnectFour.getType(id,jd-j)==type)
			{
				typeTrueAmount++;
			}
			else if (j != 0)
			{
				twoDis = true;
			}
			if(typeTrueAmount >= length)
			{
				return true;
			}
			
		}
		oneDis = false;
		twoDis = false;
		typeTrueAmount = 0;
		for(int j = 0; j < length+7; j++)
		{
			if(oneDis == false && j + id >= 0 && j + id < 7 && jd  < 6 && ConnectFour.getType(id+j,jd)==type)
			{
				typeTrueAmount++;
			}
			else if (j != 0)
			{
				oneDis = true;
			}
			if(twoDis == false && id-j >= 0 && id - j < 7 && jd < 6 && ConnectFour.getType(id-j,jd)==type)
			{
				typeTrueAmount++;
			}
			else if (j != 0)
			{
				twoDis = true;
			}
			if(typeTrueAmount >= length)
			{
				return true;
			}
		}
		oneDis = false;
		twoDis = false;
		typeTrueAmount = 0;
		for(int j = 0; j < length+3; j++)
		{
			if(oneDis == false && j + id >= 0 && j + id < 7 && j + jd>= 0 && j + jd < 6 && ConnectFour.getType(id+j,jd+j)==type)
			{
				typeTrueAmount++;
			}
			else if (j != 0)
			{
				oneDis = true;
			}
			if(twoDis == false && -j + id >= 0 && -j + id < 7 && -j + jd >= 0 && -j + jd  < 6 && ConnectFour.getType(id-j,jd-j )==type)
			{
				typeTrueAmount++;
			}
			else if (j != 0)
			{
				twoDis = true;
			}
			if(typeTrueAmount >= length)
			{
				return true;
			}
		}
		oneDis = false;
		twoDis = false;
		typeTrueAmount = 0;
		for(int j = 0; j < length+3; j++)
		{
			if(oneDis == false && j + id >= 0 && j + id < 7 && -j + jd  >= 0 && -j + jd  < 6 && ConnectFour.getType(id+j,jd-j  )==type)
			{
				typeTrueAmount++;
			}
			else if (j != 0)
			{
				oneDis = true;
			}
			if(twoDis == false && -j + id >= 0 && -j + id < 7 && j + jd  >= 0 && j + jd  < 6 && ConnectFour.getType(id-j,jd+j  )==type)
			{
				typeTrueAmount++;
			}
			else if (j != 0)
			{
				twoDis = true;
			}
			if(typeTrueAmount >= length)
			{
				return true;
			}
		}
		typeTrueAmount = 0;
		return false;
	}
	boolean computerCheck2(int type, int length, int heightOff, boolean winDetect)
	{
		int typeTrueAmount = 0;
		for(int j = -3; j < 3;j++)
		{
			if(j + jd + heightOff>= 0 && j + jd + heightOff< 6 && ConnectFour.getType(id,jd+j+heightOff)==type)
			{
				typeTrueAmount++;
			}
			else if (j != 0||winDetect)
			{
				typeTrueAmount = 0;
			}
			if(typeTrueAmount >= length)
			{
				return true;
			}
			
		}
		
		typeTrueAmount = 0;
		for(int j = -3; j < 3; j++)
		{
			if(j + id >= 0 && j + id < 7 && jd + heightOff < 6 && ConnectFour.getType(id+j,jd + heightOff)==type)
			{
				typeTrueAmount++;
			}
			else if (j != 0||winDetect)
			{
				typeTrueAmount = 0;
			}
			if(typeTrueAmount >= length)
			{
				return true;
			}
		}
		
		typeTrueAmount = 0;
		for(int j = -3; j < 3; j++)
		{
			if(j + id >= 0 && j + id < 7 && j + jd  + heightOff>= 0 && j + jd  + heightOff< 6 && ConnectFour.getType(id+j,jd+j + heightOff)==type)
			{
				typeTrueAmount++;
			}
			else if (j != 0||winDetect)
			{
				typeTrueAmount = 0;
			}
			if(typeTrueAmount >= length)
			{
				return true;
			}
		}
		
		typeTrueAmount = 0;
		for(int j = -3; j < 3; j++)
		{
			if(j + id >= 0 && j + id < 7 && -j + jd  + heightOff>= 0 && -j + jd  + heightOff< 6 && ConnectFour.getType(id+j,jd-j + heightOff)==type)
			{
				typeTrueAmount++;
			}
			else if (j != 0||winDetect)
			{
				typeTrueAmount = 0;
			}
			if(typeTrueAmount >= length)
			{
				return true;
			}
		}
		typeTrueAmount = 0;
		return false;
	}
	void winCheck(int type){
		if(computerCheck2(type,4,0,true))
		{
			ConnectFour.win = true;
		}
	}
}