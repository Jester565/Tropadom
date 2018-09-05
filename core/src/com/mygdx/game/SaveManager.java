package com.mygdx.game;


import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class SaveManager {
	static int writing = 0;
	static String fileName1 = "Empty";
	static String fileName2 = "Empty";
	static String fileName3 = "Empty";
	static String fileName4 = "Empty";
	static String armorArmImg = null;
	static String armorTorsoImg = null;
	static String armorLegImg = null;
	static String armorHeadImg = null;
	static String backImg = null;
	static SpriteBatch batch;
	static Texture saveBox;
	static Texture saveMenuI;
	static Texture loadMenuI;
	 static Texture saveBoxR;
	static Sprite saveBox1;
	static Sprite saveBox2;
	static Sprite saveBox3;
	static Sprite saveBox4;
	static Sprite SaveMenuI;
	static Sprite LoadMenuI;
	static Sprite SaveBoxR;
	static ArrayList<Boolean> registered;
	static ArrayList<ArrayList<Integer>> awsr;
	static ArrayList<ArrayList<Integer>> awdct;
	static ArrayList<ArrayList<Integer>> cookTimer;
	static ArrayList<ArrayList<Integer>> cookTimer2;
	static ArrayList<ArrayList<Integer>> parts;
	static ArrayList<ArrayList<Integer>> fuel;
	static ArrayList<ArrayList<Boolean>> signalUsed;
	static ArrayList<ArrayList<Integer>> mj;
	static ArrayList<ArrayList<Integer>> eOnD;
	static ArrayList<ArrayList<Integer>> eOnChange;
	static ArrayList<ArrayList<Boolean>> eOn;
	static ArrayList<ArrayList<Integer>> pistonAngle;
	static ArrayList<ArrayList<Integer>> blockType;
	static ArrayList<ArrayList<Integer>> range;
	static ArrayList<ArrayList<Integer>> tGrowth;
	static ArrayList<ArrayList<Integer>> sourceD;
	static ArrayList<ArrayList<Integer>> iType;
	static ArrayList<ArrayList<Integer>> gridInventory;
	static ArrayList<ArrayList<Boolean>> imageFlip;
	static ArrayList<ArrayList<Boolean>> pistonOut;
	static ArrayList<ArrayList<Boolean>> awd;
	static ArrayList<Integer>prevType;
	static ArrayList<Integer>type;
	static ArrayList<Integer>type2;
	static ArrayList<Integer>prevType2;
	static ArrayList<Integer> mainInventory;
	static ArrayList<Integer>armorTorsoStats;
	static ArrayList<Integer>armorLegStats;
	static ArrayList<Integer>armorHeadStats;
	static ArrayList<Integer>backStats;
	static ArrayList<Integer>backPackInventory;
	static ArrayList<ArrayList<Integer>>awdc;
	public static int beardI;
	public static int headI;
	public static int shirtI;
	public static int hairI;
	public static int legI;
	static Float xCurrent;
	static Float yCurrent;
	static boolean saved = false;
	static boolean loaded = false;
	static int saveTimer = 0;
	static int saveTimer2 = 0;
	static boolean saveMenu = false;
	static boolean loadMenu = false;
	static ShapeRenderer sr;
	static Scanner sc;
	static Console console;
	static int xOff = 40;
	static BitmapFont bitmap;
	SaveManager()
	{
		bitmap = new BitmapFont();
		sc = new Scanner(System.in);
		console = System.console();
		 saveBox = new Texture("saveBox.png");
		saveMenuI = new Texture("saveMenu.png");
		loadMenuI = new Texture("loadMenu.png");
		saveBoxR = new Texture("saveBox2.png");
		SaveBoxR = new Sprite(saveBoxR);
		 saveBox1 = new Sprite(saveBox);
		 saveBox2 = new Sprite(saveBox);
		 saveBox3 = new Sprite(saveBox);
		 saveBox4 = new Sprite(saveBox);
		 SaveMenuI = new Sprite(saveMenuI);
		 LoadMenuI = new Sprite(loadMenuI);
		batch = new SpriteBatch();
		awsr = new ArrayList<ArrayList<Integer>>();
		awdct = new ArrayList<ArrayList<Integer>>();
		parts = new ArrayList<ArrayList<Integer>>();
		fuel = new ArrayList<ArrayList<Integer>>();
		cookTimer = new ArrayList<ArrayList<Integer>>();
		cookTimer2 = new ArrayList<ArrayList<Integer>>();
		signalUsed = new ArrayList<ArrayList<Boolean>>();
		mj = new ArrayList<ArrayList<Integer>>();
		eOn = new ArrayList<ArrayList<Boolean>>();
		eOnChange = new ArrayList<ArrayList<Integer>>();
		eOnD = new ArrayList<ArrayList<Integer>>();
		imageFlip = new ArrayList<ArrayList<Boolean>>();
		awd = new ArrayList<ArrayList<Boolean>>();
		pistonOut = new ArrayList<ArrayList<Boolean>>();
		awdc = new ArrayList<ArrayList<Integer>>();
		pistonAngle = new ArrayList<ArrayList<Integer>>();
		backPackInventory = new ArrayList<Integer>();
		armorTorsoStats = new ArrayList<Integer>();
		armorLegStats = new ArrayList<Integer>();
		armorHeadStats = new ArrayList<Integer>();
		backStats = new ArrayList<Integer>();
		registered = new ArrayList<Boolean>();
		blockType = new ArrayList<ArrayList<Integer>>();
		range = new ArrayList<ArrayList<Integer>>();
		tGrowth = new ArrayList<ArrayList<Integer>>();
		sourceD = new ArrayList<ArrayList<Integer>>();
		mainInventory = new ArrayList<Integer>();
		iType = new ArrayList<ArrayList<Integer>>();
		gridInventory = new ArrayList<ArrayList<Integer>>();
		type = new ArrayList<Integer>();
		prevType = new ArrayList<Integer>();
		type2 = new ArrayList<Integer>();
		prevType2 = new ArrayList<Integer>();
		sr = new ShapeRenderer();
	}
	static void saveManagment()
	{
		SaveMenuI.setScale(.6f);
		SaveMenuI.setPosition(-30, 180);
		LoadMenuI.setScale(.6f);
		LoadMenuI.setPosition(-30, 180);
		saveBox1.setScale(.6f);
		saveBox1.setPosition(-30+xOff, 155);
		saveBox2.setScale(.6f);
		saveBox2.setPosition(-30+xOff, 80);
		saveBox3.setScale(.6f);
		saveBox3.setPosition(-30+xOff, 5);
		saveBox4.setScale(.6f);
		saveBox4.setPosition(-30+xOff, -70);
		SaveBoxR.setScale(.6f);
	
		saveTimer--;
		if (Gdx.input.isKeyPressed(Input.Keys.F1)||HUD.inventoryOn > 0)
		{
			if (saveTimer < 0 && saveMenu == true||HUD.inventoryOn > 0)
			{
				saveMenu = false;
				saveTimer = 60;
			}
			if (saveMenu == false && saveTimer < 0 && loadMenu == false)
			{
				saveMenu = true;
				saveTimer = 60;
			}
			
			
			//saveToFile(file);
			//saved = true;
		}
		if (saveMenu)
		{
			saveMenu(true);
		}
		if(MyGdxGame.timer > 1)
		{
			loadMenu = false;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.F2) && loaded == false||HUD.inventoryOn > 0 || MyGdxGame.timer == 0)
		{
			if(MyGdxGame.timer == 0)
			{
				loadMenu = TitleManager.loadMenu;
			}
			else
			{
			if (saveTimer < 0 && loadMenu == true||HUD.inventoryOn > 0)
			{
				loadMenu = false;
				saveTimer = 60;
			}
			if (loadMenu == false && saveTimer < 0 && saveMenu == false)
			{
				loadMenu = true;
				saveTimer = 60;
			}
			}
		}
		if(loadMenu)
		{
			saveMenu(false);
		}
	}
	static void saveMenu(boolean saveOn)
	{
		if (MyGdxGame.timer > 0)
		{
			xOff = 0;
		}
		batch.begin();
		if(saveOn)
		{
			SaveMenuI.draw(batch);
		}
		else
		{
			//LoadMenuI.draw(batch);
		}
		saveBox1.draw(batch);
		saveBox2.draw(batch);
		saveBox3.draw(batch);
		saveBox4.draw(batch);
		if (saveSlotActive("C:/Users/HP/Desktop/TropSave/saveObj.sav"))
		{
			fileName1 = "Save 1";
		}
		else
		{
			fileName1 = "Empty";
		}
		if (saveSlotActive("C:/Users/HP/Desktop/TropSave/saveObj2.sav"))
		{
			fileName2 = "Save 2";
		}
		else
		{
			fileName2 = "Empty";
		}
		if (saveSlotActive("C:/Users/HP/Desktop/TropSave/saveObj3.sav"))
		{
			fileName3 = "Save 3";
		}
		else
		{
			fileName3 = "Empty";
		}
		if (saveSlotActive("C:/Users/HP/Desktop/TropSave/saveObj4.sav"))
		{
			fileName4 = "Save 4";
		}
		else
		{
			fileName4 = "Empty";
		}
		saveTimer2--;
		
		if (saveTimer2 < 0)
		{
		if (Button.overButton(634+xOff, 614, 610, 76) && saveSlotActive("C:/Users/HP/Desktop/TropSave/saveObj.sav") || saveOn && Button.overButton(634+xOff, 614, 610, 76))
		{
			SaveBoxR.setPosition(-30 + xOff, 155);
			SaveBoxR.draw(batch);
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT))
			{
				//writingExecute(fileName1);
				//writing = 1;
				//fileName1 = sc.nextLine();
				//String s = console.readLine();
				if(saveOn)
				{
					saveToFile("C:/Users/HP/Desktop/TropSave/saveObj.sav");
				}
				else
				{
					loadFromFile("C:/Users/HP/Desktop/TropSave/saveObj.sav");
				}
				saveTimer2 = 60;
			}
			
		}
		else if (Button.overButton(634+xOff, 539, 610, 76) && saveSlotActive("C:/Users/HP/Desktop/TropSave/saveObj2.sav") || saveOn && Button.overButton(634+xOff, 539, 610, 76))
		{
			SaveBoxR.setPosition(-30+xOff, 80);
			SaveBoxR.draw(batch);
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT))
			{
				//writingExecute(fileName2);
				//writing = 2;
				//fileName2 = sc.nextLine();
				if(saveOn)
				{
				saveToFile("C:/Users/HP/Desktop/TropSave/saveObj2.sav");
				}
				else
				{
					loadFromFile("C:/Users/HP/Desktop/TropSave/saveObj2.sav");
				}
				saveTimer2 = 60;
			}
			
		}
		else if (Button.overButton(634+xOff, 464, 610, 76) && saveSlotActive("C:/Users/HP/Desktop/TropSave/saveObj3.sav")||saveOn && Button.overButton(634+xOff, 464, 610, 76))
		{
			SaveBoxR.setPosition(-30+xOff, 5);
			SaveBoxR.draw(batch);
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT))
			{
				//writingExecute(fileName3);
				//writing = 3;
				//fileName3 = sc.nextLine();
				if(saveOn)
				{
				saveToFile("C:/Users/HP/Desktop/TropSave/saveObj3.sav");
				}
				else
				{
					loadFromFile("C:/Users/HP/Desktop/TropSave/saveObj3.sav");
				}
				saveTimer2 = 60;
			}
		}
		else if (Button.overButton(634+xOff, 389, 610, 76) &&saveSlotActive("C:/Users/HP/Desktop/TropSave/saveObj4.sav") || saveOn && Button.overButton(634+xOff, 389, 610, 76))
		{
			SaveBoxR.setPosition(-30 + xOff, -70);
			SaveBoxR.draw(batch);
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT))
			{
				//writingExecute(fileName4);
				//writing = 4;
				//fileName4 = sc.nextLine();
				if(saveOn)
				{
				saveToFile("C:/Users/HP/Desktop/TropSave/saveObj4.sav");
				}
				else
				{
					loadFromFile("C:/Users/HP/Desktop/TropSave/saveObj4.sav");
				}
				saveTimer2 = 60;
			}
			
		}
		}
		else
		{
			bitmap.setColor(1,0,0,1);
			//bitmap.drawMultiLine(batch, "Saving...", 40, 1950);
		}
		/*
		bitmap.setColor(0, 0, 1, 1);
		bitmap.drawMultiLine(batch, fileName1, 670+xOff, 660);
		bitmap.setColor(0, 0, 1, 1);
		bitmap.drawMultiLine(batch, fileName2, 670+xOff, 585);
		bitmap.setColor(0, 0, 1, 1);
		bitmap.drawMultiLine(batch, fileName3, 670+xOff, 510);
		bitmap.setColor(0, 0, 1, 1);
		bitmap.drawMultiLine(batch, fileName4, 670+xOff , 435);
		if (fileDate("C:/Users/HP/Desktop/TropSave/saveObj.sav") != null)
		{
		bitmap.setColor(0, 1, 1, 1);
		bitmap.drawMultiLine(batch, fileDate("C:/Users/HP/Desktop/TropSave/saveObj.sav"), 720+xOff, 660);
		}
		if (fileDate("C:/Users/HP/Desktop/TropSave/saveObj2.sav") != null)
		{
		bitmap.setColor(0, 1, 1, 1);
		bitmap.drawMultiLine(batch, fileDate("C:/Users/HP/Desktop/TropSave/saveObj2.sav"), 720+xOff, 585);
		}
		if (fileDate("C:/Users/HP/Desktop/TropSave/saveObj3.sav") != null)
		{
		bitmap.setColor(0, 1, 1, 1);
		bitmap.drawMultiLine(batch, fileDate("C:/Users/HP/Desktop/TropSave/saveObj3.sav"), 720+xOff, 510);
		}
		if (fileDate("C:/Users/HP/Desktop/TropSave/saveObj4.sav") != null)
		{
		bitmap.setColor(0, 1, 1, 1);
		bitmap.drawMultiLine(batch,fileDate("C:/Users/HP/Desktop/TropSave/saveObj4.sav"), 720+xOff, 435);
		}
		*/
		batch.end();
	}
	static void deleteFile(String file)
	{
		String textPath = file;
		Path path = Paths.get(textPath);
		try {
		    Files.delete(path);
		}catch (NoSuchFileException x) {
		} catch (DirectoryNotEmptyException x) { 
		} catch (IOException x) {
		}
	}
	static void saveToFile(String file)
	{
		deleteFile(file);
		DateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
		Date date = new Date();
		String dateS = dateFormat.format(date);
		
		gridStackSavePrep(MyGdxGame.gridStack);
		inventorySavePrep(HUD.inventory);
		otherSavePrep();
		try {
			FileOutputStream saveFile = new FileOutputStream(file);
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
			save.writeObject(dateS);
			save.writeObject(registered);
			save.writeObject(blockType);
			save.writeObject(range);
			save.writeObject(tGrowth);
			save.writeObject(sourceD);
			save.writeObject(mainInventory);
			save.writeObject(iType);
			save.writeObject(gridInventory);
			save.writeObject(xCurrent);
			save.writeObject(yCurrent);
			save.writeObject(shirtI);
			save.writeObject(headI);
			save.writeObject(hairI);
			save.writeObject(beardI);
			save.writeObject(legI);
			save.writeObject(type);
			save.writeObject(prevType);
			save.writeObject(type2);
			save.writeObject(prevType2);
			save.writeObject(armorHeadImg);
			save.writeObject(armorTorsoImg);
			save.writeObject(armorLegImg);
			save.writeObject(backImg);
			save.writeObject(armorArmImg);
			save.writeObject(armorHeadStats);
			save.writeObject(armorTorsoStats);
			save.writeObject(armorLegStats);
			save.writeObject(backStats);
			save.writeObject(backPackInventory);
			save.writeObject(pistonAngle);
			save.writeObject(awdc);
			save.writeObject(pistonOut);
			save.writeObject(imageFlip);
			save.writeObject(eOn);
			save.writeObject(awd);
			save.writeObject(eOnD);
			save.writeObject(eOnChange);
			save.writeObject(signalUsed);
			save.writeObject(mj);
			save.writeObject(cookTimer);
			save.writeObject(cookTimer2);
			save.writeObject(parts);
			save.writeObject(fuel);
			save.writeObject(awsr);
			save.writeObject(awdct);
			save.close();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	static void otherSavePrep()
	{
		xCurrent = DirectionControl.current;
		yCurrent = DirectionControl.currentY;
		shirtI = TitleManager.sI;
		headI = 0;
		hairI = TitleManager.hI;
		beardI = TitleManager.bI;
		legI = TitleManager.lI;
		armorHeadImg = ArmorBox.head;
		armorTorsoImg = ArmorBox.body;
		armorLegImg = ArmorBox.leg;
		backImg = ArmorBox.back;
		armorArmImg = ArmorBox.arm;
		armorHeadStats = ArmorBox.headStats;
		armorTorsoStats = ArmorBox.bodyStats;
		armorLegStats = ArmorBox.legStats;
		backStats = ArmorBox.backStats;
		backPackInventory.clear();
		for (int i =0; i <ArmorBox.inventory.size(); i++)
		{
			backPackInventory.add(ArmorBox.inventory.item(i).count);
		}
	}
	static void gridStackSavePrep(ArrayList<GridStack>gridStacks)
	{
		registered.clear();
		blockType.clear();
		range.clear();
		tGrowth.clear();
		gridInventory.clear();
		iType.clear();
		type.clear();
		eOn.clear();
		awd.clear();
		awdc.clear();
		eOnD.clear();
		eOnChange.clear();
		signalUsed.clear();
		mj.clear();
		cookTimer.clear();
		awsr.clear();
		awdct.clear();
		cookTimer2.clear();
		parts.clear();
		fuel.clear();
		prevType.clear();
		type = GridManager.Type;
		prevType = GridManager.PrevType;
		type2 = GridManager2.Type;
		prevType2 = GridManager2.PrevType;
		for(int i = 0; i < gridStacks.size(); i ++)
		{
			registered.add(gridStacks.get(i).registered);
			if (gridStacks.get(i).Type.size() > GridStack.HEIGHT - 2)
			{
				awsr.add(gridStacks.get(i).awsr);
				awdct.add(gridStacks.get(i).awdct);
				signalUsed.add(gridStacks.get(i).signalUsed);
				mj.add(gridStacks.get(i).mj);
				cookTimer.add(gridStacks.get(i).cookTimer);
				cookTimer2.add(gridStacks.get(i).cookTimer2);
				parts.add(gridStacks.get(i).parts);
				fuel.add(gridStacks.get(i).fuel);
				blockType.add(gridStacks.get(i).Type);
				range.add(gridStacks.get(i).range);
				tGrowth.add(gridStacks.get(i).tGrowth);
				sourceD.add(gridStacks.get(i).sourceD);
				iType.add(gridStacks.get(i).iType);
				pistonAngle.add(gridStacks.get(i).pistonAngle);
				awdc.add(gridStacks.get(i).awdc);
				pistonOut.add(gridStacks.get(i).pistonOut);
				imageFlip.add(gridStacks.get(i).imageFlip);
				awd.add(gridStacks.get(i).awd);
				eOn.add(gridStacks.get(i).eOn);
				eOnChange.add(gridStacks.get(i).eOnChange);
				eOnD.add(gridStacks.get(i).eOnChange);
				for(int k = 0; k < gridStacks.get(i).iType.size(); k++)
				{
					if (gridStacks.get(i).iType.get(k) > 0)
					{
						gridInventory.add(gridStacks.get(i).Grids.get(k).inventoryArrayConverter());
					}
				}
			}
		}
	}
	static void inventorySavePrep(Inventory inventory)
	{
		mainInventory.clear();
		for (int i =0; i <inventory.size(); i++)
		{
			mainInventory.add(inventory.item(i).count);
		}
	}
	
	public static boolean saveSlotActive(String file)
	{
		String textPath = file;
		Path path = Paths.get(textPath);
		File f = new File(textPath);
		if(f.exists() && !f.isDirectory()) 
		{
			return true;
		}
		return false;
	}
	public static String fileDate(String file)
	{
		String date = null;
		try {
			FileInputStream loadFile = new FileInputStream(file);
			ObjectInputStream load = new ObjectInputStream(loadFile);
			date = (String) load.readObject();
			load.close();
		} catch (Exception exc) {
			//exc.printStackTrace();
		}
		if (date != null)
		{
			return date;
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public static void loadFromFile(String file)
	{
		String date;
		registered.clear();
		blockType.clear();
		range.clear();
		sourceD.clear();
		tGrowth.clear();
		iType.clear();
		mainInventory.clear();
		gridInventory.clear();
		pistonAngle.clear();
		try {
			FileInputStream loadFile = new FileInputStream(file);
			ObjectInputStream load = new ObjectInputStream(loadFile);
			date = (String) load.readObject();
			registered = (ArrayList<Boolean>) load.readObject();
			blockType = (ArrayList<ArrayList<Integer>>) load.readObject();
			range = (ArrayList<ArrayList<Integer>>) load.readObject();
			tGrowth = (ArrayList<ArrayList<Integer>>) load.readObject();
			sourceD = (ArrayList<ArrayList<Integer>>) load.readObject();
			mainInventory = (ArrayList<Integer>) load.readObject();
			iType = (ArrayList<ArrayList<Integer>>) load.readObject();
			gridInventory = (ArrayList<ArrayList<Integer>>) load.readObject();
			xCurrent = (Float)load.readObject();
			yCurrent = (Float)load.readObject();
			shirtI = (Integer)load.readObject();
			headI = (Integer)load.readObject();
			hairI = (Integer)load.readObject();
			beardI = (Integer)load.readObject();
			legI = (Integer)load.readObject();
			type = (ArrayList<Integer>)load.readObject();
			prevType = (ArrayList<Integer>)load.readObject();
			type2 = (ArrayList<Integer>)load.readObject();
			prevType2 = (ArrayList<Integer>)load.readObject();
			armorHeadImg = (String)load.readObject();
			armorTorsoImg = (String)load.readObject();
			armorLegImg = (String)load.readObject();
			backImg = (String)load.readObject();
			armorArmImg = (String)load.readObject();
			armorHeadStats = (ArrayList<Integer>)load.readObject();
			armorTorsoStats = (ArrayList<Integer>)load.readObject();
			armorLegStats = (ArrayList<Integer>)load.readObject();
			backStats = (ArrayList<Integer>)load.readObject();
			backPackInventory = (ArrayList<Integer>)load.readObject();
			pistonAngle = (ArrayList<ArrayList<Integer>>)load.readObject();
			awdc = (ArrayList<ArrayList<Integer>>)load.readObject();
			pistonOut = (ArrayList<ArrayList<Boolean>>)load.readObject();
			imageFlip = (ArrayList<ArrayList<Boolean>>)load.readObject();
			eOn = (ArrayList<ArrayList<Boolean>>)load.readObject();
			awd = (ArrayList<ArrayList<Boolean>>)load.readObject();
			eOnD = (ArrayList<ArrayList<Integer>>)load.readObject();
			eOnChange = (ArrayList<ArrayList<Integer>>)load.readObject();
			signalUsed = (ArrayList<ArrayList<Boolean>>)load.readObject();
			mj = (ArrayList<ArrayList<Integer>>)load.readObject();
			cookTimer = (ArrayList<ArrayList<Integer>>)load.readObject();
			cookTimer2 = (ArrayList<ArrayList<Integer>>)load.readObject();
			parts = (ArrayList<ArrayList<Integer>>)load.readObject();
			fuel = (ArrayList<ArrayList<Integer>>)load.readObject();
			awsr = (ArrayList<ArrayList<Integer>>)load.readObject();
			awdct = (ArrayList<ArrayList<Integer>>)load.readObject();
			load.close();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		for(int i = 0; i < registered.size(); i++)
		{
			System.out.println(registered.get(i));
		}
		for(int i = 0; i < gridInventory.size(); i++)
		{
			System.out.println(gridInventory.get(i));
		}
		loaded = true;
	}
	public static void loadInventory(Inventory inventory)
	{
		for (int i = 0; i < inventory.size(); i++)
		{
			inventory.item(i).add(mainInventory.get(i));
		}
	}
}
