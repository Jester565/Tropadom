package com.mygdx.game;

import java.util.ArrayList;

public class Inventory {
	private ArrayList<InventoryItem> _items;
	int _index = 0;
Inventory ()
{
	   _items = new ArrayList<InventoryItem>();
	   _items.add(new stone());
	   _items.add(new dirt());
	   _items.add(new dirtGrass());
	   _items.add(new lava1());
	   _items.add(new lava2());
	   _items.add(new log());
	   _items.add(new leaf());
	   _items.add(new water());
	   _items.add(new sand());
	   _items.add(new sappling());
	   _items.add(new torch());
	   _items.add(new chest());
	   _items.add(new bonfire());
	   _items.add(new glass());
	   _items.add(new furnace());
	   _items.add(new coal());
	   _items.add(new iron());
	   _items.add(new copper());
	   _items.add(new gold());
	   _items.add(new macerator());
	   _items.add(new stonePipe());
	   _items.add(new ironPipe());
	   _items.add(new batBox());
	   _items.add(new generator());
	   _items.add(new copperWire());
	   _items.add(new plank());
	   _items.add(new door());
	   _items.add(new door2());
	   _items.add(new ladder());
	   _items.add(new hoedDirt());
	   _items.add(new rubberLog());
	   _items.add(new eFurnace());
	   _items.add(new sapling());
	   _items.add(new tomato());
	   _items.add(new corn());
	   _items.add(new wire());
	   _items.add(new lever());
	   _items.add(new advancedWireManager());
	   _items.add(new piston());
	   _items.add(new pistonH());
	   _items.add(new lamp());
	   _items.add(new c4());
	   _items.add(new snow());
	   _items.add(new ice());
	   _items.add(new stoneBrick());
	   _items.add(new stoneButton());
	   _items.add(new pressurePad());
	   _items.add(new signalConverter());
	   _items.add(new rubberBlock());
	   _items.add(new solidWire());
	   _items.add(new grass());
	   _items.add(new flowerOne());
	   _items.add(new flowerTwo());
	   _items.add(new vine());
	   _items.add(new jungleLeaf());
	   _items.add(new jungleLog());
	   _items.add(new jungleSapling());
	   _items.add(new sandStone());
	   _items.add(new desertBush());
	   _items.add(new cactus());
	   _items.add(new emptyBucket());
	   _items.add(new woodPick());
	   _items.add(new woodShovel());
	   _items.add(new woodAxe());
	   _items.add(new stonePick());
	   _items.add(new stoneShovel());
	   _items.add(new stoneAxe());
	   _items.add(new ironPick());
	   _items.add(new ironShovel());
	   _items.add(new ironAxe());
	   _items.add(new copperIgnot());
	   _items.add(new ironIgnot());
	   _items.add(new goldIgnot());
	   _items.add(new hedgeClippers());
	   _items.add(new hoe());
	  _items.add(new spile());
	  _items.add(new sap());
	  _items.add(new ironDust());
	  _items.add(new copperDust());
	  _items.add(new goldDust());
	  _items.add(new tomatoObj());
	  _items.add(new cornObj());
	  _items.add(new goldHelmet());
	  _items.add(new ironHelmet());
	  _items.add(new ironChest());
	  _items.add(new ironLeggings());
	  _items.add(new leatherBag());
	  _items.add(new largeLeatherBag());
	  _items.add(new headLamp());
	  _items.add(new huntingBow());
	  _items.add(new arrow());
	  _items.add(new arrowBag());
	  _items.add(new rawChicken());
	  _items.add(new cookedChicken());
	  _items.add(new woodKnife());
}
public InventoryItem item(int index)
{
  if (index < _items.size()) {
    return _items.get(index);
  }

  return null;
}
public int amountScan()
{
	int amountScan = 0;
	for(int i = 0; i < _items.size();i++)
	{
		if(_items.get(i).count > 0)
		{
			amountScan++;
		}
	}
	System.out.println(amountScan);
	return amountScan;
}
public void reset()
{
	_index = 0;
	for(int i = 0; i < _items.size(); i++)
	{
		_items.get(i).imageDrawnCount = 0;
	}
}
public int size()
{
	return _items.size();
}
public InventoryItem next(boolean all)
{
	for (; _index < _items.size(); _index++)
	{
		 if (_index + InventoryItem.BLOCK_AMOUNT< size() && _items.get(_index + InventoryItem.BLOCK_AMOUNT).count > 0 || all || _index + InventoryItem.BLOCK_AMOUNT >= size() && _items.get(_index - (size()-InventoryItem.BLOCK_AMOUNT)).count > 0 ) {
			 if (HUD.category == _items.get(_index).category||all == false)
			 {
			 	_index++;
			 	if (all == false)
			 	{
			 	if (_index + InventoryItem.BLOCK_AMOUNT -1< size())
			 	{
			 		return _items.get(_index - 1 + InventoryItem.BLOCK_AMOUNT);
			 	}
			 	else
			 	{
			 		
			 		return _items.get(_index - (size()-InventoryItem.BLOCK_AMOUNT) - 1);
			 	}
			 	}
			 	else
			 	{
			 		return _items.get(_index - 1);
			 	}
			 }
		  }
	}
	return null;
}
}
