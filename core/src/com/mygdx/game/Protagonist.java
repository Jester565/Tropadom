package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Protagonist extends GridBased{
	ShapeRenderer sr;
	SpriteBatch batch = new SpriteBatch();
	Sprite armPart1 = new Sprite();
	Sprite armPart2 = new Sprite();
	Sprite armArmorPart1 = null;
	Sprite armArmorPart2 = null;
	Sprite armPart1L = new Sprite();
	Sprite armPart2L = new Sprite();
	Sprite armArmorPart1L = null;
	Sprite armArmorPart2L = null;
	Sprite legPart1 = new Sprite();
	Sprite legPart2 = new Sprite();
	Sprite legArmorPart1 = null;
	Sprite legArmorPart2 = null;
	Sprite legPart1L = new Sprite();
	Sprite legPart2L = new Sprite();
	Sprite legArmorPart1L = new Sprite();
	Sprite legArmorPart2L = new Sprite();
	Sprite ci1 = null;
	Sprite ci2 = null;
	Sprite headArmor = null;
	Sprite chestArmor = null;
	Sprite legArmor = null;
	Sprite armArmor = null;
	Sprite legArmor2 = null;
	Sprite armArmor2 = null;
	Sprite back = null;
	Sprite Hair = new Sprite();
	Sprite HairL = new Sprite();
	Sprite Beard = new Sprite();
	Sprite BeardL = new Sprite();
	Sprite Leg = new Sprite();
	Sprite Leg2 = new Sprite();
	Sprite Leg2L = new Sprite();
	Sprite TorsoL = new Sprite();
	Sprite LegL = new Sprite();
	Sprite Torso = new Sprite();
	Sprite Arms = new Sprite();
	Sprite Head = new Sprite();
	Sprite HeadL = new Sprite();
	Sprite Item = new Sprite();
	Sprite ArmsL = new Sprite();
	Sprite Item2 = new Sprite();
	boolean projectileFire = false;
	boolean flipDis = false;
	boolean overRideArmAngle = false;
	boolean eat = false;
	float aorr = 0;
	float aoll = 0;
	float aorrf = 0;
	float aollf = 0;
	int useInStageTimer = 0;
	static int arrowStage = 0;
	int arrowTakeTimer = 0;
	static boolean arrowFire = false;
	int snowCreate = 0;
	int lowerLegRotationBasedOnTorso = 20;
	public static boolean sit = false;
	boolean pickPunch = false;
	static float torsoAngle = 0;
	float torsoSin = 0;
	float torsoCos = 0;
	static boolean skin = false;
	static float torsoHeightOff =0;
	float frontArmAngle = 0;
	float frontArmAngle2 = 0;
	float lowerLegAngle = 0;
	float lowerLegAngle2 = 0;
	public static int sitTimer = 0;
	int crouchTimer = 0;
	boolean crouch = false;
	public static int craftingTimer = 0;
	public static int craftingTimerInitial = 0;
	public static int itemCrafting = 0;
	public ArrayList<Integer>parts;
	public static double headAngle = 0;
	static boolean right;
	float caa = 0;
	float caa2 = 0;
	boolean rightSet;
	boolean itemTwo = false;
	boolean headAngleLock = false;
	static float placeTimer = -1;
	static final float WIDTH = 40;
	static final float HEIGHT = 90;
	static final float _x = (Gdx.graphics.getWidth()/2) - WIDTH/2;
	static final float _y = (Gdx.graphics.getHeight()/2 - HEIGHT/2);
	static int createSet = 10;
	float legAngle = 0;
	int punchD = 0;
	float arrowHeadAngle = 0;
	int craftingStage = 0;
	int punchTimer = 0;
	float armAngle = 0;
	boolean punch = false;
	int legAngleD = 1;
	static public int xOff = 10;
	static public float armAngle2;
	static public float armAngle3;
	
Protagonist()
{
	parts = new ArrayList<Integer>();
	sr = new ShapeRenderer();
	batch = new SpriteBatch();
}
void craftItem(int itemNumber, int craftTime, ArrayList<Integer>parts)
{
	caa = -90;
	caa2 = -90;
	craftingStage = 0;
	craftingTimer = craftTime * 3;
	craftingTimerInitial = craftTime * 3;
	this.parts = parts;
	itemCrafting = itemNumber;
}
void bentArmManager(Sprite armPart1, Sprite armPart2, Sprite armArmorPart1, Sprite armArmorPart2, int negative,float armAngle2, float frontArmAngle)
{
	
	int armLength = 40;
	int armYOff = 40;
	int speedOff;
	if(-headAngle < -30)
	{
		speedOff = 0;
	}
	else if(-headAngle < -10)
	{
		speedOff = 1;
	}
	else if (-headAngle <30)
	{
		speedOff = 2;
	}
	else
	{
		speedOff = 3;
	}
	if(skin)
	{
		flipDis = true;
		if(arrowStage == 0)
		{
			if(crouch == false)
			{
				crouch = true;
				crouchTimer = 20;
				sit = false;
			}
			if(crouchTimer < 0)
			{
				arrowStage = 1;
			}
		}
		else if (arrowStage == 1)
		{
			
			if(overRideArmAngle == false)
			{
			aorr = armAngle2 - 90;
			aoll = armAngle2 * -1 - 90;
			aorrf = frontArmAngle - (armAngle2 * negative)*2;
			aollf = frontArmAngle;
			}
			overRideArmAngle = true;
			arrowStage = 2;
		}
		else if (arrowStage == 2)
		{
			if(aorr < 0)
			{
				aorr++;
				if(aorrf < aorr)
				{
					aorrf+=2;
				}
			}
			else if (aorrf < 90)
			{
				aorrf+=2;
			}
			else
			{
				arrowStage = 3;
			}
		}
		else if (arrowStage == 3)
		{
			if(aorr > -20)
			{
				aorr --;
			}
			if(aorrf > -20)
			{
				aorrf -=6;
			}
			else
			{
				arrowStage = 4;
			}
		}
		else if (arrowStage == 4)
		{
			if(aorr>-120)
			{
				aorr--;
			}
			else
			{
				crouch = false;
				arrowStage = 5;
			}
		}
	}
	else if(eat)
	{
		if(headAngle < 5)
		{
			headAngle++;
		}
		else
		{
			headAngle--;
		}
		if(torsoAngle < -20)
		{
			torsoAngle++;
		}
		else if(torsoAngle > -18)
		{
			torsoAngle--;
		}
		flipDis = true;
		if(arrowStage == 0)
		{
			headAngleLock = true;
			if(overRideArmAngle == false)
			{
				aorr = armAngle2 - 90;
				aoll = armAngle2 * -1 - 90;
				aorrf = frontArmAngle - (armAngle2 * negative)*2;
				aollf = frontArmAngle;
				overRideArmAngle = true;
			}
			arrowStage = 1;
		}
		else if (arrowStage == 1)
		{
			if(aorr < -32)
			{
				aorr++;
			}
			else if(aorr>-30)
			{
				aorr--;
			}
			else
			{
				arrowStage = 2;
			}
			if(aorrf<aorr)
			{
				aorrf+=2;
			}
			else
			{
				aorrf-=2;
			}
		}
		else if(arrowStage==2)
		{
			if(aorrf < 60)
			{
				aorrf++;
			}
			else
			{
				arrowStage = 3;
				useInStageTimer = 100;
			}
		}
		else if(arrowStage == 3)
		{
			aorrf+=.1f;
			if(useInStageTimer < 0)
			{
				arrowStage = 4;
				HUD.inventory.item(HUD.typeN-1).remove(1);
				headAngleLock = false;
			}
		}
		else if(arrowStage == 4)
		{
			if (aorrf > aorr)
			{
				aorrf-=2;
			}
			if(aorr > -90)
			{
				aorr--;
			}
			else
			{
				eat = false;
				arrowStage = 5;
			}
		}
	}
	else if(arrowFire)
	{
		flipDis = true;
		if(arrowStage == 0)
		{
			if(overRideArmAngle == false)
			{
			aorr = armAngle2 - 90;
			aoll = armAngle2 * -1 - 90;
			aorrf = frontArmAngle - (armAngle2 * negative)*2;
			aollf = frontArmAngle;
			}
			if (Gdx.input.getX() > 1000)
			{	
				right = true;
			}
			else
			{
				right = false;
			}
			if(right)
			{
				headAngle = Math.atan2(Gdx.input.getY() - 475 + torsoHeightOff, Gdx.input.getX()-1000)*(180/Math.PI);
			}
			else
			{
				headAngle = Math.atan2(Gdx.input.getY() - 475 + torsoHeightOff, (-Gdx.input.getX()+1000))*(180/Math.PI);
			}
			if(headAngle < -50 - torsoAngle){
				headAngle = -50 - torsoAngle;
			}
			if(headAngle > 34 - torsoAngle)
			{
				headAngle = 34 - torsoAngle;
			}
			arrowHeadAngle = (float) -headAngle;
			overRideArmAngle = true;
			arrowStage = 1;
		}
		else if (arrowStage == 1)
		{
			boolean stageReady1 = false;
			boolean stageReady2 = false;
			boolean stageReady3 = false;
			if(aorr < 70)
			{
				aorr+=2;
			}
			else if (aorr > 75)
			{
				aorr-=2;
			}
			else
			{
				stageReady1=true;
			}
			if(aorrf < aorr+7)
			{
				aorrf+=4;
			}
			else if (aorrf > aorr + 14)
			{
				aorrf-=2;
			}
			else
			{
				stageReady2 = true;
			}
			if(aollf < aoll - 7)
			{
				aollf+=2;
			}
			else if(aollf>aoll-4)
			{
				aollf-=2;
			}
			else
			{
				stageReady3 = true;
			}
			if(aoll < 0+arrowHeadAngle)
			{
				aoll+=1.6f;
			}
			else if(aoll > 2+arrowHeadAngle)
			{
				aoll-=1.6f;
			}
			else if (stageReady1 == true && stageReady2 == true && stageReady3 == true)
			{
				arrowStage = 2;
			}
		}
		else if (arrowStage == 2)
		{
			if(aorr < 70)
			{
				aorr+=2;
			}
			else
			{
				aorr-=2;
			}
			if(aorrf<200)
			{
				aorrf+=4;
			}
			else
			{
				arrowStage = 3;
				if(ArmorBox.inventory.item(InventoryItem.BLOCK_AMOUNT+30).count>0)
				{
					ArmorBox.inventory.item(InventoryItem.BLOCK_AMOUNT+30).remove(1);
				}
				else if (HUD.inventory.item(InventoryItem.BLOCK_AMOUNT+30).count>0)
				{
					HUD.inventory.item(InventoryItem.BLOCK_AMOUNT+30).remove(1);
				}
				else
				{
					arrowFire = false;
					arrowStage = 5;
				}
			}
		}
		else if (arrowStage == 3)
		{
			itemTwo = true;
			if(aorrf > aorr)
			{
				aorrf-=4;
			}
			else
			{
				arrowStage = 4;
				
			}
			if(aorr > aoll)
			{
				aorr-=3;
			}
		}
		else if (arrowStage == 4)
		{
			if(aorr > aoll-180)
			{
				aorr-=3;
			}
			else
			{
				projectileFire = true;
				arrowStage = 5;
				arrowFire = false;
				
			}	
				aorrf = aoll+(-aorr/2);
		}
		
	}
	else if(punch && negative == 1)
	{
		if (pickPunch)
		{
			if(punchD == 0||punchD==-1)
			{
				if(armAngle2 -90< -headAngle+20)
				{
					armAngle2 +=5+speedOff;
					if(frontArmAngle < armAngle2-75)
					{
						frontArmAngle +=7+speedOff;
					}
					else if (frontArmAngle > armAngle2 - 8)
					{
						frontArmAngle -=7+speedOff;
					}
				}
				else
				{
					punchD = 1;
				}
			}
			if(punchD == 1)
			{
				if(frontArmAngle < -headAngle + 120)
				{
					frontArmAngle+=9+speedOff;
					armAngle2+=2+speedOff;
				}
				else
				{
					punchD = 2;
				}
			}
			if(punchD == 2)
			{
				if (punchD == 2 && frontArmAngle > -headAngle-40)
				{
					frontArmAngle -= 12+speedOff;
					armAngle2 -= 9+speedOff;
				}
				else if (punchD == 2)
				{
					punchD = -1;
					punch = false;
					pickPunch = false;
				}
			}
		}
		else
		{
			if(frontArmAngle < -10 && -headAngle > -10)
			{
				frontArmAngle += 30;
			}
			if(frontArmAngle > -headAngle)
			{
				frontArmAngle -=8;
			}
			else
			{
				frontArmAngle+=8;
			}
		}
	}
	else
	{
		if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
		{
			if(frontArmAngle > armAngle2)
			{
				frontArmAngle -=3;
			}
			else if(frontArmAngle < armAngle2-5)
			{
				frontArmAngle+=3;
			}
		}
		else if (crouch)
		{
			if(frontArmAngle > armAngle2-20)
			{
				frontArmAngle -=3;
			}
			else if(frontArmAngle < armAngle2-25)
			{
				frontArmAngle+=3;
			}
		}
		else
		{
			if(MyGdxGame.dc.realSpeed == 0)
			{
				if(frontArmAngle > armAngle2 - 90)
				{
					frontArmAngle -=5;
				}
				else if(frontArmAngle < armAngle2 - 95)
				{
					frontArmAngle+=5;
				}
			}
			else
			{
				if(frontArmAngle > armAngle2 - 80)
				{
					frontArmAngle -=5;
				}
				else if (frontArmAngle < armAngle2-88)
				{
					frontArmAngle+=5;
				}
			}
		}
	}
	if (arrowStage == 5)
	{
		itemTwo = false;
		if(aoll > -armAngle2-90)
		{
			aoll-=3;
		}
		if(aorr < armAngle2 -90)
		{
			aorr+=4;
		}
		if(aorrf > frontArmAngle)
		{
			aorrf-=2;
		}
		else
		{
			overRideArmAngle = false;
			skin = false;
		}
		if(aollf > frontArmAngle + (armAngle2 * negative)*2)
		{
			aollf-=2;
		}
	}
	armPart2.setRegion(new TextureRegion(Arms.getTexture(), 74, 14, 21, 14));
	armPart1.setRegion(new TextureRegion(Arms.getTexture(),0,0,73,50));
	
	if(negative==-1)
	{
		if (HUD.typeN == InventoryItem.BLOCK_AMOUNT+30)
		{
			Item = new Sprite(new Texture("huntingBow.png"));
			Item.setFlip(false, false);
			if(overRideArmAngle == true)
			{
				Item.setPosition((float)((_x - 19 - xOff + torsoCos*armLength)+(Math.cos((aoll)*(Math.PI/180))*18.5f)+(Math.cos((aollf)*(Math.PI/180))*20f))+40,(float)((_y + 54 + torsoHeightOff +torsoSin*armLength-armYOff)+(Math.sin((aoll)*(Math.PI/180))*18.5f)+(Math.sin((aollf)*(Math.PI/180))*20f))+6);
			}
			else
			{
				Item.setPosition((float)((_x - 19 - xOff + torsoCos*armLength)+(Math.cos((-armAngle2-90)*(Math.PI/180))*18.5f)+(Math.cos((frontArmAngle + (armAngle2 * negative)*2)*(Math.PI/180))*20f))+40,(float)((_y + 54 + torsoHeightOff +torsoSin*armLength-armYOff)+(Math.sin((-armAngle2-90)*(Math.PI/180))*18.5f)+(Math.sin((frontArmAngle + (armAngle2 * negative)*2)*(Math.PI/180))*20f))+6);
			}
			Item.setOriginCenter();
			if(overRideArmAngle == false)
			{
				Item.setRotation(frontArmAngle + (armAngle2 * negative)*2);
			}
			else
			{
				Item.setRotation(aollf);
			}
			//Item.setRotation(armAngle2);
			batch.draw(Item, _x + 25 -xOff,_y + 29, 5, 55, 30, 30, 1, 1, armAngle2);
		}
	}
	if(right)
	{
		if(HUD.typeN != -1 && craftingTimer < 0&&negative==1&&HUD.typeN!=InventoryItem.BLOCK_AMOUNT+30)
		{
			if (HUD.typeN >= InventoryItem.BLOCK_AMOUNT + 2 && HUD.typeN <= InventoryItem.BLOCK_AMOUNT + 10 || HUD.typeN == InventoryItem.BLOCK_AMOUNT + 14||HUD.typeN == 11|| HUD.typeN == InventoryItem.BLOCK_AMOUNT + 35)
			{
				Item.setFlip(false, false);
				if(overRideArmAngle == false)
				{
					Item.setPosition((float)((_x - 19 - xOff + torsoCos*armLength)+(Math.cos((armAngle2-90)*(Math.PI/180))*18.5f)+(Math.cos((frontArmAngle)*(Math.PI/180))*20f))+40,(float)((_y + 54 + torsoHeightOff +torsoSin*armLength-armYOff)+(Math.sin((armAngle2-90)*(Math.PI/180))*18.5f)+(Math.sin((frontArmAngle)*(Math.PI/180))*20f))+15);
				}
				else
				{
					Item.setPosition((float)((_x - 19 - xOff + torsoCos*armLength)+(Math.cos((aorr)*(Math.PI/180))*18.5f)+(Math.cos((aorrf)*(Math.PI/180))*20f))+40,(float)((_y + 54 + torsoHeightOff +torsoSin*armLength-armYOff)+(Math.sin((aorr)*(Math.PI/180))*18.5f)+(Math.sin((aorrf)*(Math.PI/180))*20f))+15);
				}
				Item.setSize(30,30);
				Item.setOriginCenter();
				if(overRideArmAngle == false)
				{
					Item.setRotation(frontArmAngle + 90);
				}
				else
				{
					Item.setRotation(aorrf-90);
				}
				Item.draw(batch);
			}
			
			else
			{
				Item.setFlip(false, false);
				if(overRideArmAngle == false)
				{
					Item.setPosition((float)((_x - 19 - xOff + torsoCos*armLength)+(Math.cos((armAngle2-90)*(Math.PI/180))*18.5f)+(Math.cos((frontArmAngle)*(Math.PI/180))*21.5f))+40,(float)((_y + 54 + torsoHeightOff +torsoSin*armLength-armYOff)+(Math.sin((armAngle2-90)*(Math.PI/180))*20f)+(Math.sin((frontArmAngle)*(Math.PI/180))*21.5f))+15);
				}
				else
				{
					Item.setPosition((float)((_x - 19 - xOff + torsoCos*armLength)+(Math.cos((aorr-90)*(Math.PI/180))*18.5f)+(Math.cos((aorrf)*(Math.PI/180))*21.5f))+61,(float)((_y + 54 + torsoHeightOff +torsoSin*armLength-armYOff)+(Math.sin((aorr)*(Math.PI/180))*20f)+(Math.sin((aorrf)*(Math.PI/180))*21.5f))+15);
				}
				Item.setSize(20,20);
				Item.setRotation(armAngle2);
				Item.draw(batch);
			}
		}
		if(itemTwo == true)
		{
			int lowerStringEffect = 22;
			if(aoll < 0)
			{
				lowerStringEffect = 16;
			}
			
			Item2 = new Sprite(new Texture("arrowProjectile.png"));
			Item2.setFlip(false, false);
			Item2.setPosition((float)((_x - 15 - xOff + torsoCos*armLength)+(Math.cos((aorr)*(Math.PI/180))*18.5f)+(Math.cos((aorrf)*(Math.PI/180))*20f))+40,(float)((_y + 65 + torsoHeightOff +torsoSin*armLength-armYOff)+(Math.sin((aorr)*(Math.PI/180))*18.5f)+(Math.sin((aorrf)*(Math.PI/180))*20f))+15);
			Item2.setOriginCenter();
			if(arrowStage == 3)
			{
				Item2.setRotation(aorrf);
			}
			else
			{
				Item2.setRotation(aollf);
			}
			Item2.draw(batch);
		}
		if(projectileFire == true)
		{
			Projectile projectile;
			projectile = new Projectile("arrowProjectile.png",(float)((_x - 15 - xOff + torsoCos*armLength)+(Math.cos((aorr)*(Math.PI/180))*18.5f)+(Math.cos((aorrf)*(Math.PI/180))*20f))+40,(float)((_y + 65 + torsoHeightOff +torsoSin*armLength-armYOff)+(Math.sin((aorr)*(Math.PI/180))*18.5f)+(Math.sin((aorrf)*(Math.PI/180))*20f))+15,28,10,14,arrowHeadAngle,.02f,1);
			MyGdxGame.projectile.add(projectile);
			projectileFire = false;
		}
		armPart2.setSize(25,11f);
		armPart1.setOrigin(54, 30);
		if(overRideArmAngle == false)
		{
			armPart1.setRotation(armAngle2 * negative - 90);
		}
		else
		{
			if(negative == 1)
			{
				armPart1.setRotation(aorr);
			}
			else
			{
				armPart1.setRotation(aoll);
			}
		}
		armPart1.setFlip(false, false);
		armPart2.setFlip(false, false);
		armPart1.setPosition(_x - 19 - xOff + torsoCos*armLength, _y + 54 + torsoHeightOff +torsoSin*armLength-armYOff);
		armPart1.draw(batch);
		if(overRideArmAngle == false)
		{
			armPart2.setPosition((float)(_x - 19f - xOff + 51f + torsoCos*armLength + Math.cos((armAngle2 * negative - 90f)*(Math.PI/180f))*18.5f), (float)(_y + 50f + 28f + torsoSin * armLength +torsoHeightOff + Math.sin((armAngle2  * negative- 90f)*(Math.PI/180f))*18.5f) - armYOff);
		}
		else
		{
			if(negative == 1)
			{
				armPart2.setPosition((float)(_x - 19f - xOff + 51f + torsoCos*armLength + Math.cos((aorr)*(Math.PI/180f))*18.5f), (float)(_y + 50f + 28f + torsoSin * armLength +torsoHeightOff + Math.sin((aorr)*(Math.PI/180f))*18.5f) - armYOff);
			}
			else
			{
				armPart2.setPosition((float)(_x - 19f - xOff + 51f + torsoCos*armLength + Math.cos((aoll)*(Math.PI/180f))*18.5f), (float)(_y + 50f + 28f + torsoSin * armLength +torsoHeightOff + Math.sin((aoll)*(Math.PI/180f))*18.5f) - armYOff);
			}
		}
		armPart2.setOrigin(2, 5);
		if(overRideArmAngle == false)
		{
			if(negative == -1)
			{
				armPart2.setRotation(frontArmAngle + (armAngle2 * negative)*2);
			}
			else
			{
				armPart2.setRotation(frontArmAngle);
			}
		}
		else
		{
			if(negative == 1)
			{
				armPart2.setRotation(aorrf);
			}
			else
			{
				armPart2.setRotation(aollf);
			}
		}
		
		armPart2.draw(batch);
		if(ArmorBox.body != null && armArmorPart2 != null && armArmorPart1 != null)
		{
			armArmorPart2.setSize(25,11f);
			armArmorPart1.setOrigin(54, 30);
			if(overRideArmAngle == false)
			{
				armArmorPart1.setRotation(armAngle2 * negative - 90);
			}
			else
			{
				if(negative == 1)
				{
					armArmorPart1.setRotation(aorr);
				}
				else
				{
					armArmorPart1.setRotation(aoll);
				}
			}
			armArmorPart1.setFlip(false, false);
			armArmorPart2.setFlip(false, false);
			armArmorPart1.setPosition(_x - 19 - xOff + torsoCos*armLength, _y + 54+torsoHeightOff + torsoSin*armLength-armYOff);
			armArmorPart1.draw(batch);
			if(overRideArmAngle == false)
			{
				armArmorPart2.setPosition((float)(_x - 19f - xOff + 51f + torsoCos*armLength + Math.cos((armAngle2 * negative - 90f)*(Math.PI/180f))*18.5f), (float)(_y + 50f +torsoSin*armLength + torsoHeightOff+28f + Math.sin((armAngle2 * negative - 90f)*(Math.PI/180f))*18.5f)-armYOff);
			}
			else
			{
				if(negative == 1)
				{
					armArmorPart2.setPosition((float)(_x - 19f - xOff + 51f + torsoCos*armLength + Math.cos((aorr)*(Math.PI/180f))*18.5f), (float)(_y + 50f +torsoSin*armLength + torsoHeightOff+28f + Math.sin((aorr)*(Math.PI/180f))*18.5f)-armYOff);
				}
				else
				{
					armArmorPart2.setPosition((float)(_x - 19f - xOff + 51f + torsoCos*armLength + Math.cos((aoll)*(Math.PI/180f))*18.5f), (float)(_y + 50f +torsoSin*armLength + torsoHeightOff+28f + Math.sin((aoll)*(Math.PI/180f))*18.5f)-armYOff);
				}
			}
			armArmorPart2.setOrigin(2, 5);
			if(overRideArmAngle == false)
			{
			if(negative == -1)
			{
				armArmorPart2.setRotation(frontArmAngle + (armAngle2 * negative)*2);
			}
			else
			{
				armArmorPart2.setRotation(frontArmAngle);
			}
			}
			else
			{
				if(negative == 1)
				{
					armArmorPart2.setRotation(aorrf);
				}
				else
				{
					armArmorPart2.setRotation(aollf);
				}
			}
			armArmorPart2.draw(batch);
		}
	}
	else
	{
		if(negative==-1)
		{
			if (HUD.typeN == InventoryItem.BLOCK_AMOUNT+30)
			{
				Item = new Sprite(new Texture("huntingBow.png"));
				Item.setFlip(false, false);
				if(overRideArmAngle == true)
				{
					Item.setPosition((float)((_x - 19 - xOff + torsoCos*armLength)+(Math.cos((aoll-180)*(Math.PI/180))*18.5f)+(Math.cos((aollf-180)*(Math.PI/180))*20f))+40,(float)((_y + 54 + torsoHeightOff +torsoSin*armLength-armYOff)+(Math.sin((-aoll-180)*(Math.PI/180))*18.5f)+(Math.sin((-aollf-180)*(Math.PI/180))*20f))+6);
				}
				else
				{
					Item.setPosition((float)((_x - 19 - xOff + torsoCos*armLength)+(Math.cos((armAngle2 * negative - 270f)*(Math.PI/180))*18.5f)+(Math.cos((-frontArmAngle - (armAngle2 * negative)*2-180)*(Math.PI/180))*20f))+40,(float)((_y + 54 + torsoHeightOff +torsoSin*armLength-armYOff)+(Math.sin((-(armAngle2 * negative - 270f))*(Math.PI/180))*18.5f)+(Math.sin(((-frontArmAngle - (armAngle2 * negative)*2)-180)*(Math.PI/180))*20f))+6);
				}
				Item.setOriginCenter();
				if(overRideArmAngle == false)
				{
					Item.setRotation(-(frontArmAngle + (armAngle2 * negative)*2)-180);
				}
				else
				{
					Item.setRotation(-aollf-180);
				}
				//Item.setRotation(armAngle2);
				//batch.draw(Item, _x + 25 -xOff,_y + 29, 5, 55, 30, 30, 1, 1, armAngle2);
				Item.draw(batch);
			}
		}
		if(HUD.typeN != -1 && craftingTimer < 0&&negative==1&&HUD.typeN!= InventoryItem.BLOCK_AMOUNT+30)
		{
			if (HUD.typeN >= InventoryItem.BLOCK_AMOUNT + 2 && HUD.typeN <= InventoryItem.BLOCK_AMOUNT + 10 || HUD.typeN == InventoryItem.BLOCK_AMOUNT + 14||HUD.typeN == 11|| HUD.typeN == InventoryItem.BLOCK_AMOUNT + 35)
			{
				Item.setFlip(false, true);
				if(overRideArmAngle == false)
				{
					Item.setPosition((float)((_x - 19 - xOff + torsoCos*armLength)+(Math.cos((-armAngle2-90)*(Math.PI/180))*18.5f)+(Math.cos((-frontArmAngle-180)*(Math.PI/180))*20f))+35,(float)((_y + 54 + torsoHeightOff +torsoSin*armLength-armYOff)+(Math.sin((-armAngle2-90)*(Math.PI/180))*18.5f)+(Math.sin((-frontArmAngle-180)*(Math.PI/180))*20f))+11);
				}
				else
				{
					Item.setPosition((float)((_x - 19 - xOff + torsoCos*armLength)+(Math.cos((aorr-180)*(Math.PI/180))*18.5f)+(Math.cos((aorrf-180)*(Math.PI/180))*20f))+35,(float)((_y + 54 + torsoHeightOff +torsoSin*armLength-armYOff)+(Math.sin((-aorr-180)*(Math.PI/180))*18.5f)+(Math.sin((-aorrf-180)*(Math.PI/180))*20f))+11);
				}
				Item.setSize(30,30);
				Item.setOriginCenter();
				if(overRideArmAngle == false)
				{
					Item.setRotation(-frontArmAngle + 90);
				}
				else
				{
					Item.setRotation(-aorrf-180-90);
				}
				//Item.setRotation(armAngle2);
				//batch.draw(Item, _x + 25 -xOff,_y + 29, 5, 55, 30, 30, 1, 1, armAngle2);
				Item.draw(batch);
			}
			else
			{
			Item.setFlip(false, false);
			if(overRideArmAngle == false)
			{
				Item.setPosition((float)((_x - 19 - xOff + torsoCos*armLength)+(Math.cos((-armAngle2-90)*(Math.PI/180))*18.5f)+(Math.cos((-frontArmAngle-180)*(Math.PI/180))*21.5f))+40,(float)((_y + 54 + torsoHeightOff +torsoSin*armLength-armYOff)+(Math.sin((-armAngle2-90)*(Math.PI/180))*20f)+(Math.sin((-frontArmAngle-180)*(Math.PI/180))*21.5f))+15);
			}
			else
			{
				Item.setPosition((float)((_x - 19 - xOff + torsoCos*armLength)+(Math.cos((aorr-180)*(Math.PI/180))*18.5f)+(Math.cos((aorrf-180)*(Math.PI/180))*21.5f))+40,(float)((_y + 54 + torsoHeightOff +torsoSin*armLength-armYOff)+(Math.sin((-aorr-180)*(Math.PI/180))*20f)+(Math.sin((-aorrf-180)*(Math.PI/180))*21.5f))+15);
			}
			Item.setSize(20,20);
			//Item.setRotation(armAngle2);
			//batch.draw(Item, _x + 25 -xOff,_y + 29, 5, 55, 30, 30, 1, 1, armAngle2);
			Item.draw(batch);
			}
		}
		if(itemTwo == true)
		{
			//bowString1.setColor(0, 0, 0, 1);
			//bowString2.setColor(0, 0, 0, 1);
			int lowerStringEffect = 22;
			if(aoll < 0)
			{
				lowerStringEffect = 16;
			}
			Item2 = new Sprite(new Texture("arrowProjectile.png"));
			Item2.setFlip(false, false);
			Item2.setPosition((float)((_x - 15 - xOff + torsoCos*armLength)+(Math.cos((aorr-180)*(Math.PI/180))*18.5f)+(Math.cos((aorrf-180)*(Math.PI/180))*20f))+15,(float)((_y + 65 + torsoHeightOff +torsoSin*armLength-armYOff)+(Math.sin((-aorr-180)*(Math.PI/180))*18.5f)+(Math.sin((-aorrf-180)*(Math.PI/180))*20f))+11);
			Item2.setOriginCenter();
			if(arrowStage == 3)
			{
				Item2.setRotation(-aorrf-180);
			}
			else
			{
				Item2.setRotation(-aollf-180);
				//bowString1.drawLfne((float)((_x - 15 - xOff + torsoCos*armLength)+(Math.cos((aorr-180)*(Math.PI/180))*18.5f)+(Math.cos((aorrf-180)*(Math.PI/180))*20f))+40, (float)((_y + 65 + torsoHeightOff +torsoSin*armLength-armYOff)+(Math.sin((-aorr-180)*(Math.PI/180))*18.5f)+(Math.sin((-aorrf-180)*(Math.PI/180))*20f))+15, (float)((_x - 19 - xOff + torsoCos*armLength)+(Math.cos((aoll-180)*(Math.PI/180))*18.5f)+(Math.cos((aollf-180)*(Math.PI/180))*20f)+Math.cos((aollf+90-180) * (Math.PI/180))*22)+49,(float)((_y + 54 + torsoHeightOff +torsoSin*armLength-armYOff)+(Math.sin((aoll)*(Math.PI/180))*18.5f)+(Math.sin((aollf)*(Math.PI/180))*20f)+Math.sin(((aollf+90)) * (Math.PI/180))*22)+28);
				//bowString2.drawLine((float)((_x - 15 - xOff + torsoCos*armLength)+(Math.cos((aorr-180)*(Math.PI/180))*18.5f)+(Math.cos((aorrf-180)*(Math.PI/180))*20f))+40, (float)((_y + 65 + torsoHeightOff +torsoSin*armLength-armYOff)+(Math.sin((-aorr-180)*(Math.PI/180))*18.5f)+(Math.sin((-aorrf-180)*(Math.PI/180))*20f))+15, (float)((_x - 19 - xOff + torsoCos*armLength)+(Math.cos((aoll-180)*(Math.PI/180))*18.5f)+(Math.cos((aollf-180)*(Math.PI/180))*20f)+Math.cos((aollf-90-180) * (Math.PI/180))*22)+49,(float)((_y + 54 + torsoHeightOff +torsoSin*armLength-armYOff)+(Math.sin((aoll)*(Math.PI/180))*18.5f)+(Math.sin((aollf)*(Math.PI/180))*20f)+Math.sin(((aollf-90)) * (Math.PI/180))*lowerStringEffect)+28);
			}
			Item2.draw(batch);
		}
		if(projectileFire == true)
		{
			Projectile projectile;
			projectile = new Projectile("arrowProjectile.png",(float)((_x - 15 - xOff + torsoCos*armLength)+(Math.cos((aorr-180)*(Math.PI/180))*18.5f)+(Math.cos((aorrf-180)*(Math.PI/180))*20f))+15,(float)((_y + 65 + torsoHeightOff +torsoSin*armLength-armYOff)+(Math.sin((-aorr-180)*(Math.PI/180))*18.5f)+(Math.sin((-aorrf-180)*(Math.PI/180))*20f))+11,28,10,14,-arrowHeadAngle-180,.02f,1);
			MyGdxGame.projectile.add(projectile);
			projectileFire = false;
		}
		armPart2.setSize(25,11f);
		armPart1.setOrigin(20, 30);
		if(overRideArmAngle == false)
		{
			armPart1.setRotation((-armAngle2 * negative - 270));
		}
		else
		{
			if(negative == 1)
			{
				armPart1.setRotation(-aorr);
			}
			else
			{
				armPart1.setRotation(-aoll);
			}
		}
		armPart1.setPosition(_x +8 - xOff+ torsoCos*armLength, _y + 54 + torsoHeightOff +torsoSin*armLength-armYOff);
		armPart1.setFlip(true, false);
		//armPart1.draw(batch);
		if(overRideArmAngle == false)
		{
			armPart2.setPosition((float)(_x -45f - xOff + 51f + torsoCos*armLength + Math.cos(((armAngle2 * negative - 270f))*(Math.PI/180f))*18.5f), (float)(_y + 50f +torsoSin*armLength + 28f  + torsoHeightOff + Math.sin((-(armAngle2 * negative - 270f))*(Math.PI/180f))*18.5f)-armYOff);
		}
		else
		{
			if(negative == 1)
			{
				armPart2.setPosition((float)(_x -45f - xOff + 51f + torsoCos*armLength + Math.cos(((aorr-180))*(Math.PI/180f))*18.5f), (float)(_y + 50f +torsoSin*armLength + 28f  + torsoHeightOff + Math.sin(((-aorr-180))*(Math.PI/180f))*18.5f)-armYOff);
			}
			else
			{
				armPart2.setPosition((float)(_x -45f - xOff + 51f + torsoCos*armLength + Math.cos(((aoll-180))*(Math.PI/180f))*18.5f), (float)(_y + 50f +torsoSin*armLength + 28f  + torsoHeightOff + Math.sin(((-aoll-180))*(Math.PI/180f))*18.5f)-armYOff);
			}
		}
		armPart2.setOrigin(23, 5);
		if(overRideArmAngle == false)
		{
		if(negative == -1)
		{
			armPart2.setRotation(-frontArmAngle - (armAngle2 * negative)*2);
		}
		else
		{
			armPart2.setRotation(-frontArmAngle);
		}
		}
		else
		{
			if(negative == 1)
			{
				armPart2.setRotation(-aorrf);
			}
			else
			{
				armPart2.setRotation(-aollf);
			}
		}
		armPart2.setFlip(true, false);
		armPart2.draw(batch);
		
		if(armArmorPart2 != null && armArmorPart1 != null && ArmorBox.body != null)
		{
			armArmorPart2.setSize(25,10.5f);
			armArmorPart1.setOrigin(20, 30);
			if(overRideArmAngle == false)
			{
				armArmorPart1.setRotation((-armAngle2 * negative - 270));
			}
			else
			{
				if(negative == 1)
				{
				armArmorPart1.setRotation((-aorr));
				}
				else
				{
				armArmorPart1.setRotation((-aoll));
				}
			}
			armArmorPart1.setPosition(_x +8 + torsoCos*armLength - xOff, _y + torsoHeightOff+ 54 +torsoSin*armLength-armYOff);
			armArmorPart1.setFlip(true, false);
			armArmorPart1.draw(batch);
			if(overRideArmAngle == false)
			{
				armArmorPart2.setPosition((float)(_x -45f+ torsoCos*armLength - xOff + 51f + Math.cos(((armAngle2 * negative - 270f))*(Math.PI/180f))*20f), (float)(_y + 50f +torsoSin*armLength + 30f  + torsoHeightOff + Math.sin((-(armAngle2 * negative - 270f))*(Math.PI/180f))*20f)-armYOff);
			}
			else
			{
				if(negative == 1)
				{
					armArmorPart2.setPosition((float)(_x -45f+ torsoCos*armLength - xOff + 51f + Math.cos(((aorr-180))*(Math.PI/180f))*20f), (float)(_y + 50f +torsoSin*armLength + 30f  + torsoHeightOff + Math.sin((-aorr-180)*(Math.PI/180f))*20f)-armYOff);
				}
				else
				{
					armArmorPart2.setPosition((float)(_x -45f+ torsoCos*armLength - xOff + 51f + Math.cos(((aoll-180))*(Math.PI/180f))*20f), (float)(_y + 50f +torsoSin*armLength + 30f  + torsoHeightOff + Math.sin((-aoll-180)*(Math.PI/180f))*20f)-armYOff);
				}
			}
			armArmorPart2.setOrigin(23, 5);
			armArmorPart2.setRotation(-(frontArmAngle));
			if(overRideArmAngle == false)
			{
			if(negative == -1)
			{
				armArmorPart2.setRotation(-frontArmAngle - (armAngle2 * negative)*2);
			}
			else
			{
				armArmorPart2.setRotation(-frontArmAngle);
			}
			}
			else
			{
				if(negative == 1)
				{
				armArmorPart2.setRotation(-aorrf);
				}
				else
				{
					armArmorPart2.setRotation(-aollf);
				}
			}
			armArmorPart2.setFlip(true, false);
			armArmorPart2.draw(batch);
		}
	}
	if(negative == 1)
	{
		this.frontArmAngle = frontArmAngle;
		if(pickPunch && negative == 1)
		{
			this.armAngle2 = armAngle2;
		}
	}
	else
	{
		this.frontArmAngle2 = frontArmAngle;
		
	}
}
void bentLegManager(Sprite legPart1, Sprite legPart2, Sprite legArmorPart1,Sprite legArmorPart2, int negative, float lowerLegAngle)
{
	int legRotationBasedOnTorso = 90;
	if(sit)
	{
		if(lowerLegRotationBasedOnTorso < -30)
		{
			lowerLegRotationBasedOnTorso+=2;
		}
		else
		{
			lowerLegRotationBasedOnTorso-=2;
		}
		legRotationBasedOnTorso = 60;
	}
	else
	{
		if(lowerLegRotationBasedOnTorso < 0)
		{
			lowerLegRotationBasedOnTorso+=2;
		}
		else
		{
			lowerLegRotationBasedOnTorso-=2;
		}
	}
	if(Gdx.input.isKeyPressed(Input.Keys.PAGE_UP))
	{
		torsoAngle++;
	}
	if(Gdx.input.isKeyPressed(Input.Keys.PAGE_DOWN))
	{
		torsoAngle--;
	}
	if(Gdx.input.isKeyPressed(Input.Keys.UP))
	{
		torsoHeightOff++;
	}
	if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
	{
		torsoHeightOff--;
	}
	if(legAngle > lowerLegAngle)
	{
		lowerLegAngle++;
	}
	else
	{
		lowerLegAngle--;
	}
	legPart2.setRegion(new TextureRegion(Leg.getTexture(), 0, 112, 64, 29));
	legPart1.setRegion(new TextureRegion(Leg.getTexture(),0,0,64,112));
	if(ArmorBox.leg!=null)
	{
		legArmorPart2 = new Sprite(new TextureRegion(new Texture(ArmorBox.leg), 0, 112, 64, 29));
		legArmorPart1 = new Sprite(new TextureRegion(new Texture(ArmorBox.leg),0,0,64,112));
	}
	if(right)
	{
		legPart1.setFlip(false,false);
		legPart2.setFlip(false,false);
		legPart1.setOrigin(32,26);
		legPart1.setRotation(legAngle *negative - (torsoHeightOff/25)*legRotationBasedOnTorso);
		legPart1.setPosition(_x - xOff, _y+17+torsoHeightOff);
		legPart1.draw(batch);
		legPart2.setOrigin(32,29);
		legPart2.setRotation(lowerLegAngle*negative + (torsoHeightOff/25)*lowerLegRotationBasedOnTorso);
		legPart2.setPosition((float)(_x - xOff+ Math.cos((legAngle*negative-90f - (torsoHeightOff/25)*legRotationBasedOnTorso)*(Math.PI/180f))*24f),(float)( _y +17 + Math.sin((legAngle*negative-90f - (torsoHeightOff/25)*legRotationBasedOnTorso)*(Math.PI/180f))*24f)+torsoHeightOff);
		legPart2.draw(batch);
	if(ArmorBox.leg!=null)
	{
		legArmorPart1.setFlip(false,false);
		legArmorPart2.setFlip(false,false);
		legArmorPart1.setOrigin(32,26);
		legArmorPart1.setRotation(legAngle * negative - (torsoHeightOff/25)*legRotationBasedOnTorso);
		legArmorPart1.setPosition(_x - xOff, _y+17+torsoHeightOff);
		legArmorPart1.draw(batch);
		legArmorPart2.setOrigin(32,29);
		legArmorPart2.setRotation(lowerLegAngle * negative+ (torsoHeightOff/25)*lowerLegRotationBasedOnTorso);
		legArmorPart2.setPosition((float)(_x - xOff+ Math.cos((legAngle*negative-90f - (torsoHeightOff/25)*legRotationBasedOnTorso)*(Math.PI/180f))*24f),(float)( _y +17 + Math.sin((legAngle*negative-90f - (torsoHeightOff/25)*legRotationBasedOnTorso)*(Math.PI/180f))*24f)+torsoHeightOff);
		legArmorPart2.draw(batch);
	}
	}
	else
	{
		legPart1.setFlip(true,false);
		legPart2.setFlip(true,false);
		legPart1.setOrigin(32,26);
		legPart1.setRotation(-(legAngle * negative - (torsoHeightOff/25)*legRotationBasedOnTorso));
		legPart1.setPosition(_x - xOff, _y+17+torsoHeightOff);
		legPart1.draw(batch);
		legPart2.setOrigin(32,29);
		legPart2.setRotation(-(lowerLegAngle * negative + (torsoHeightOff/25)*lowerLegRotationBasedOnTorso));
		legPart2.setPosition((float)(_x - xOff+ Math.cos(-((legAngle*negative-90f - (torsoHeightOff/25)*legRotationBasedOnTorso + 180))*(Math.PI/180f))*24f),(float)( _y +17 + Math.sin(-((legAngle*negative-90f - (torsoHeightOff/25)*legRotationBasedOnTorso) + 180)*(Math.PI/180f))*24f)+torsoHeightOff);
		legPart2.draw(batch);
		if(ArmorBox.leg != null)
		{
			legArmorPart1.setFlip(true,false);
			legArmorPart2.setFlip(true,false);
			legArmorPart1.setOrigin(32,26);
			legArmorPart1.setRotation(-(legAngle * negative - (torsoHeightOff/25)*legRotationBasedOnTorso));
			legArmorPart1.setPosition(_x - xOff, _y+17+torsoHeightOff);
			legArmorPart1.draw(batch);
			legArmorPart2.setOrigin(32,29);
			legArmorPart2.setRotation(-(lowerLegAngle * negative+ (torsoHeightOff/25)*lowerLegRotationBasedOnTorso));
			legArmorPart2.setPosition((float)(_x - xOff+ Math.cos(-((legAngle*negative-90f - (torsoHeightOff/25)*legRotationBasedOnTorso + 180))*(Math.PI/180f))*24f),(float)( _y +17 + Math.sin(-((legAngle*negative-90f - (torsoHeightOff/25)*legRotationBasedOnTorso) + 180)*(Math.PI/180f))*24f)+torsoHeightOff);
			legArmorPart2.draw(batch);
		}
	}
	if(negative == -1)
	{
		this.lowerLegAngle2 = lowerLegAngle;
	}
	else
	{
		this.lowerLegAngle = lowerLegAngle;
		
	}
}
void craftManager()
{
	float speed;
	craftingTimer--;
	
	if(craftingTimer > 0)
	{
		//System.out.println(craftingTimerInitial);
		speed = (float)(280)/(float)(craftingTimerInitial);
	if(craftingStage == 0)
	{
		if(caa < 10)
		{
			caa+=speed;
		}
		else
		{
			craftingStage = 1;
		}
	}
	else if (craftingStage == 1)
	{
		if(caa2 < -10)
		{
			caa2+=speed;
		}
		else
		{
			craftingStage = 2;
		}
	}
	else if (craftingStage == 2)
	{
		if(caa2 < 0)
		{
			caa2 +=speed;
		}
		if(caa > 0)
		{
			caa-=speed;
		}
		else
		{
			ci1 = new Sprite(new Texture(HUD.inventory2.item(itemCrafting-1).img));
			ci2 = new Sprite(new Texture(HUD.inventory2.item(itemCrafting-1).img));
			MyGdxGame.hud.inventory2.item(itemCrafting-1).createProduct(MyGdxGame.hud.inventory);
			craftingStage = 3;
		}
	}
	else
	{
		if(caa2 > -90)
		{
			caa2-=speed;
		}
		if(caa > -90)
		{
			caa-=speed;
		}
	}
	}
}
void draw()
{
	batch.begin();
	flipDis = false;
	useInStageTimer--;
	if(MyGdxGame.timer <= 30)
	{
		if(-TitleManager.bI != 5)
		{
			if(MyGdxGame.timer == 1)
			{
				Beard = new Sprite(TitleManager.beard.get(-TitleManager.bI).getTexture());
				BeardL = new Sprite(TitleManager.beard.get(-TitleManager.bI).getTexture());
			}
			BeardL.setFlip(true, false);
			BeardL.setScale(1);
			Beard.setScale(1);
		}
		if(MyGdxGame.timer == 1)
		{
			Hair = new Sprite(TitleManager.hair.get(-TitleManager.hI).getTexture());
			HairL = new Sprite(TitleManager.hair.get(-TitleManager.hI).getTexture());
			Arms = new Sprite(TitleManager.arms.get(-TitleManager.sI).getTexture());
			ArmsL = new Sprite(TitleManager.arms.get(-TitleManager.sI).getTexture());
			Head = new Sprite(TitleManager.heads.get(0).getTexture());
			HeadL = new Sprite(TitleManager.heads.get(0).getTexture());
			//batch = new SpriteBatch();
			Leg = new Sprite(TitleManager.legs.get(-TitleManager.lI).getTexture());
			LegL = new Sprite(TitleManager.legs.get(-TitleManager.lI).getTexture());
			Leg2 = new Sprite(TitleManager.legs.get(-TitleManager.lI).getTexture());
			Leg2L = new Sprite(TitleManager.legs.get(-TitleManager.lI).getTexture());
			TorsoL = new Sprite(TitleManager.shirt.get(-TitleManager.sI).getTexture());
			Torso = new Sprite(TitleManager.shirt.get(-TitleManager.sI).getTexture());
		}
		if(MyGdxGame.load == true)
		{
			if(SaveManager.beardI > 5)
			{
				if(MyGdxGame.timer == 1)
				{
					Beard = new Sprite(TitleManager.beard.get(-SaveManager.beardI).getTexture());
					BeardL = new Sprite(TitleManager.beard.get(-SaveManager.beardI).getTexture());
				}
			BeardL.setFlip(true, false);
			BeardL.setScale(1);
			Beard.setScale(1);
			}
			if(MyGdxGame.timer == 1)
			{
			Hair = new Sprite(TitleManager.hair.get(-SaveManager.hairI).getTexture());
			HairL = new Sprite(TitleManager.hair.get(-SaveManager.hairI).getTexture());
			Arms = new Sprite(TitleManager.arms.get(-SaveManager.shirtI).getTexture());
			Head = new Sprite(TitleManager.heads.get(0).getTexture());
			HeadL = new Sprite(TitleManager.heads.get(0).getTexture());
			//batch = new SpriteBatch();
			Leg = new Sprite(TitleManager.legs.get(-SaveManager.legI).getTexture());
			LegL = new Sprite(TitleManager.legs.get(-SaveManager.legI).getTexture());
			Leg2 = new Sprite(TitleManager.legs.get(-SaveManager.legI).getTexture());
			Leg2L = new Sprite(TitleManager.legs.get(-SaveManager.legI).getTexture());
			TorsoL = new Sprite(TitleManager.shirt.get(-SaveManager.shirtI).getTexture());
			Torso = new Sprite(TitleManager.shirt.get(-SaveManager.shirtI).getTexture());
			}
		}
		
		HairL.setFlip(true,false);
		HeadL.setFlip(true, false);
		LegL.setFlip(true,false);
		Leg2L.setFlip(true,false);
		TorsoL.setFlip(true, false);
		HairL.setScale(1);
		Hair.setScale(1);
		Head.setScale(1);
		HeadL.setScale(1);
		Arms.setScale(1);
		ArmsL.setScale(1);
		Torso.setScale(1);
		TorsoL.setScale(1);
		LegL.setScale(1);
		Leg.setScale(1);
		Leg2L.setScale(1);
		Leg2.setScale(1);
		Beard.draw(batch);
		BeardL.draw(batch);
		Hair.draw(batch);
		HairL.draw(batch);
		ArmsL.draw(batch);
		Head.draw(batch);
		HeadL.draw(batch);
		Leg.draw(batch);
		Leg2.draw(batch);
		LegL.draw(batch);
		Leg2L.draw(batch);
		TorsoL.draw(batch);
		Torso.draw(batch);
	}
	craftManager();
	if (ArmorBox.refreshing && MyGdxGame.timer > 3 || MyGdxGame.load && MyGdxGame.timer < 3)
	{
		if (ArmorBox.head != null)
		{
			headArmor = new Sprite(new Texture(ArmorBox.head));
		}
		if (ArmorBox.body !=null)
		{
			chestArmor = new Sprite(new Texture(ArmorBox.body));
			armArmor = new Sprite(new Texture(ArmorBox.arm));
			armArmor2 = new Sprite(new Texture(ArmorBox.arm));
		}
		if (ArmorBox.leg != null)
		{
			legArmor = new Sprite(new Texture(ArmorBox.leg));
			legArmor2 = new Sprite(new Texture(ArmorBox.leg));
		}
		if (ArmorBox.back != null)
		{
			back = new Sprite(new Texture(ArmorBox.back));
		}	
	}
	if(ArmorBox.back != null && ArmorBox.back.equals("bowBag.png"))
	{
		if(ArmorBox.inventory.item(InventoryItem.BLOCK_AMOUNT+30).count>=3)
		{
			back = new Sprite(new Texture("bowBag3.png"));
		}
		else if(ArmorBox.inventory.item(InventoryItem.BLOCK_AMOUNT+30).count==2)
		{
			back = new Sprite(new Texture("bowBag2.png"));
		}
		else if(ArmorBox.inventory.item(InventoryItem.BLOCK_AMOUNT+30).count==1)
		{
			back = new Sprite(new Texture("bowBag1.png"));
		}
		else
		{
			back = new Sprite(new Texture("bowBag.png"));
		}
	}
	if (HUD.img != null&&HUD.typeN!=InventoryItem.BLOCK_AMOUNT + 30)
	{
		Item = new Sprite(new Texture(HUD.img));
	}
	else
	{
		//Item.setNull();
	}
	
	createSet = HUD.blockN;
	
	if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT) && createSet > 0 && placeTimer < 0)
	{
		create(Gdx.input.getX(),(1000-Gdx.input.getY()),Gdx.input.isButtonPressed(Input.Buttons.RIGHT),createSet,_x - 20,_y - 20, WIDTH + 20, HEIGHT + 20, false,true);
		
		
	}
	if (eat==false&&skin == false&&Gdx.input.isButtonPressed(Input.Buttons.LEFT)&&sit==false)
	{
		if (HUD.typeN >= InventoryItem.BLOCK_AMOUNT + 2 && HUD.typeN <= InventoryItem.BLOCK_AMOUNT + 10 || HUD.typeN == InventoryItem.BLOCK_AMOUNT + 14||HUD.typeN == 11)
		{
			pickPunch = true;
		}
		else
		{
			pickPunch = false;
		}
		punch = true;
	}
	if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT))
	{
		if(eat==false&&skin==false&&punch==false&&arrowFire==false)
		{
			if(HUD.typeN == InventoryItem.BLOCK_AMOUNT+30)
			{
			if(ArmorBox.inventory.item(InventoryItem.BLOCK_AMOUNT+30).count>0)
			{
				arrowFire = true;
				arrowStage = 0;
			}
			else if (HUD.inventory.item(InventoryItem.BLOCK_AMOUNT+30).count>0&&arrowFire == false)
			{
				arrowFire = true;
				arrowStage = 0;
			}
			}
			if(HUD.typeN == InventoryItem.BLOCK_AMOUNT+33)
			{
				eat = true;
				arrowStage = 0;
			}
		}
		punch = true;
	}
	
	placeTimer--;
	/*sr.begin(ShapeType.Filled);
	sr.setColor(1,0,0,1);
	sr.rect(_x, _y, WIDTH, HEIGHT);
	sr.end();
	*/
	characterArt();
	if(flipDis == false)
	{
	if (Gdx.input.getX() > 1000)
	{
		
		right = true;
	}
	else
	{
		right = false;
	}
	}
	batch.end();
}
Integer craftingImage(int number)
{
	
	if(craftingTimer > 0 && parts != null)
	{
		System.out.println(parts.size());
	if(number == 0)
	{
		if(parts.size()>=1)
		{
			return parts.get(number);
		}
	}
	else
	{
		if(parts.size()>=2)
		{
			return parts.get(number);
		}
	}
	}
	return -1;
}
void characterArt()
{
	
	//batch.begin();
	 float legSpeed = Math.abs(DirectionControl.speed)/2 + 1;
	if (DirectionControl.moveX&& legAngle > 20)
	{
		legAngleD = -1;
	}
	if (DirectionControl.moveX && legAngle < -20)
	{
		legAngleD = 1;
	}
	if (DirectionControl.moveX == false&& legAngle > .2)
	{
		legAngleD = -1;
	}
	if (DirectionControl.moveX == false&& legAngle < .2)
	{
		legAngleD = 1;
	}
	if (legAngleD == 1)
	{
		legAngle += legSpeed;
	}
	else if (legAngleD == -1)
	{
		legAngle-= legSpeed;
	}
	
	leg(Leg2, Leg2L, legArmor,legAngle);
	leg(Leg, LegL,legArmor2, -legAngle);
	arms(Arms, armArmor2,-legAngle * 2, true,caa,craftingImage(0),ci1);	
	arms(ArmsL, armArmor,legAngle * 2, false,caa2,craftingImage(1),ci2);
	head(0);
	bentArmManager(armPart1L,armPart2L,armArmorPart1L,armArmorPart2L,-1,armAngle3,frontArmAngle2);
	bentLegManager(legPart1L,legPart2L,legArmorPart1L,legArmorPart2L,-1,lowerLegAngle2);
	torso(0);
	bentLegManager(legPart1,legPart2,legArmorPart1,legArmorPart2,1,lowerLegAngle);
	bentArmManager(armPart1,armPart2,armArmorPart1,armArmorPart2,1,armAngle2,frontArmAngle);
	}
	//batch.end();
void leg(Sprite sa, Sprite sa2,Sprite legArmor, float legAngle)
{
	if(right)
	{
		
		sa.setRotation(legAngle);
		sa.setPosition(_x - xOff, _y- 10 + torsoHeightOff);
		//sa.addToDraw();
		Leg.draw(batch);
		if (ArmorBox.leg != null && legArmor != null) {
			legArmor.setFlip(false,false);
			legArmor.setRotation(legAngle);
			legArmor.setPosition(_x - xOff, _y - 10 + torsoHeightOff);
			legArmor.draw(batch);
		}
	}
	else
	{
		sa2.setRotation(legAngle);
		sa2.setPosition(_x - xOff, _y- 10 + torsoHeightOff);
		//sa2.addToDraw();
		LegL.draw(batch);
		if (ArmorBox.leg !=null && legArmor != null)
		{
			legArmor.setFlip(true, false);
			legArmor.setRotation(legAngle);
			legArmor.setPosition(_x - xOff, _y- 10 + torsoHeightOff);
			legArmor.draw(batch);
		}
	}
}
void torso(float angle)
{
	crouchTimer--;
	sitTimer--;
	if(Gdx.input.isKeyPressed(Input.Keys.S))
	{
		if(crouch == false && crouchTimer < 0)
		{
			sit = false;
			crouch = true;
			crouchTimer=20;
		}
		else if (crouch == true && crouchTimer < 0)
		{
			crouch = false;
			crouchTimer=20;
		}
	}
	if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
	{
		if(sit == false && crouchTimer < 0)
		{
			sit = true;
			crouch = false;
			crouchTimer=50;
			sitTimer = 50;
		}
		else if (sit == true && crouchTimer < 0)
		{
			sit = false;
			crouchTimer=50;
			sitTimer = 50;
		}
	}
	if(sit)
	{
		if(torsoAngle < 5)
		{
			torsoAngle++;
		}
		if(torsoAngle > 6)
		{
			torsoAngle--;
		}
		if(torsoHeightOff <-42)
		{
			torsoHeightOff++;
		}
		if(torsoHeightOff > -43)
		{
			torsoHeightOff--;
		}
	}
	else if(crouch)
	{
		if(torsoAngle <-40)
		{
			torsoAngle++;
		}
		if(torsoAngle > -41)
		{
			torsoAngle--;
		}
		if(torsoHeightOff <-12)
		{
			torsoHeightOff++;
		}
		if(torsoHeightOff > -13)
		{
			torsoHeightOff--;
		}
	}
	else
	{
		if(torsoAngle > 0)
		{
			torsoAngle--;
		}
		if(torsoAngle < 0)
		{
			torsoAngle++;
		}
		if(torsoHeightOff > 0)
		{
			torsoHeightOff--;
		}
		if(torsoHeightOff < 0)
		{
			torsoHeightOff++;
		}
	}
	if(right)
	{
		torsoSin = (float) (-Math.sin((torsoAngle - 90)*(Math.PI/180f)));
		torsoCos = (float) (-Math.cos((torsoAngle - 90)*(Math.PI/180f)));
	}
	else
	{
		torsoSin = (float) (-Math.sin((torsoAngle - 90)*(Math.PI/180f)));
		torsoCos = (float) (Math.cos((torsoAngle - 90)*(Math.PI/180f)));
	}
	if(right)
	{	
		Torso.setPosition(_x - xOff, _y + HEIGHT - 50 + torsoHeightOff);
		Torso.setOrigin(35, 2);
		Torso.setRotation(torsoAngle);
		//Torso.draw(batch);
		Torso.draw(batch);
		if (chestArmor != null && ArmorBox.body != null) {
			chestArmor.setOrigin(35, 2);
			chestArmor.setRotation(torsoAngle);
			chestArmor.setFlip(false, false);
			chestArmor.setPosition(_x - xOff, _y + HEIGHT - 50 + torsoHeightOff);
			chestArmor.draw(batch);
		}
		if (back != null && ArmorBox.back != null) {
			back.setFlip(false, false);
			back.setOrigin(35, 2);
			back.setRotation(torsoAngle);
			back.setPosition(_x - xOff, _y + HEIGHT - 50 + torsoHeightOff);
			back.draw(batch);
		}
	}
	else
	{
		TorsoL.setPosition(_x - xOff, _y + HEIGHT - 50 + torsoHeightOff);
		TorsoL.setOrigin(35, 2);
		TorsoL.setRotation(-torsoAngle);
		TorsoL.draw(batch);
		if (chestArmor != null && ArmorBox.body != null) {
			chestArmor.setFlip(true, false);
			chestArmor.setOrigin(35, 2);
			chestArmor.setRotation(-torsoAngle);
			chestArmor.setPosition(_x - xOff, _y + HEIGHT - 50 + torsoHeightOff);
			chestArmor.draw(batch);
		}
		if (back != null && ArmorBox.back != null) {
			back.setFlip(true, false);
			back.setOrigin(35, 2);
			back.setRotation(-torsoAngle);
			back.setPosition(_x - xOff, _y + HEIGHT - 50 + torsoHeightOff);
			back.draw(batch);
		}
	}
}
public static float headAngle()
{
	float headAngle2 = 0;
	if(right)
	{
		headAngle2 = (float)(-(Math.atan2(Gdx.input.getY() - 475 + torsoHeightOff, Gdx.input.getX()-1000)*(180/Math.PI)));
	}
	else
	{
		headAngle2 = (float)(Math.atan2(Gdx.input.getY() - 475 + torsoHeightOff, (-Gdx.input.getX()+1000))*(180/Math.PI)+180);
	}
	if(right)
	{
		
		if(headAngle2 > 50 + torsoAngle)
		{
			headAngle2 = 50 + torsoAngle;
		}
		if(headAngle2 < -38 + torsoAngle)
		{
			headAngle2 = -38 + torsoAngle;
		}
	}
	else
	{
		if(headAngle2 < 130 + torsoAngle - 90)
		{
			headAngle2 = 130;
		}
		if(headAngle2 >218 + torsoAngle - 90)
		{
			headAngle2 = 218;
		}
	}
	return headAngle2;
}
void head(float angle)
{
	int headLength = 45;
	int headYOff = -45;
	if(headAngleLock == false)
	{
	if(right)
	{
		headAngle = Math.atan2(Gdx.input.getY() - 475 + torsoHeightOff, Gdx.input.getX()-1000)*(180/Math.PI);
	}
	else
	{
		headAngle = Math.atan2(Gdx.input.getY() - 475 + torsoHeightOff, (-Gdx.input.getX()+1000))*(180/Math.PI);
	}
	if(headAngle < -50 - torsoAngle){
		headAngle = -50 - torsoAngle;
	}
	if(headAngle > 34 - torsoAngle)
	{
		headAngle = 34 - torsoAngle;
	}
	}
	if(right)
	{
	Head.setPosition(_x - xOff +torsoCos*headLength, _y + HEIGHT - 36 + torsoHeightOff+torsoSin*headLength + headYOff);
	Head.setOriginCenter();
	Head.setRotation((float)(-headAngle));
	Head.draw(batch);
	//Head.draw(batch);
	Beard.setPosition(_x - xOff+torsoCos*headLength, _y + HEIGHT - 36 + torsoHeightOff+torsoSin*headLength + headYOff);
	Beard.setOriginCenter();
	Beard.setRotation((float)(-headAngle));
	Beard.draw(batch);
	//Beard.draw(batch);
	Hair.setPosition(_x - xOff+torsoCos*headLength, _y + HEIGHT - 36 + torsoHeightOff+torsoSin*headLength + headYOff);
	Hair.setOriginCenter();
	Hair.setRotation((float)(-headAngle));
	Hair.draw(batch);
	//Hair.draw(batch);
	if (headArmor != null) {
		headArmor.setFlip(false, false);
		headArmor.setPosition(_x - xOff+torsoCos*headLength, _y + HEIGHT - 36 + torsoHeightOff+torsoSin*headLength + headYOff);
		headArmor.setOriginCenter();
		headArmor.setRotation((float)(-headAngle));
		if (ArmorBox.head !=null)
		{
		headArmor.draw(batch);
		}
		//headArmor.draw(batch);
	}
	}
	else
	{
		HeadL.setPosition(_x - xOff+torsoCos*headLength, _y + HEIGHT - 36 + torsoHeightOff+torsoSin*headLength + headYOff);
		HeadL.setOriginCenter();
		HeadL.setRotation((float) headAngle);
		HeadL.draw(batch);
		//HeadL.draw(batch);
		BeardL.setPosition(_x - xOff+torsoCos*headLength, _y + HEIGHT - 36 + torsoHeightOff+torsoSin*headLength + headYOff);
		BeardL.setOriginCenter();
		BeardL.setRotation((float) headAngle);
		BeardL.draw(batch);
		//BeardL.draw(batch);
		HairL.setPosition(_x - xOff+torsoCos*headLength, _y + HEIGHT - 36 + torsoHeightOff+torsoSin*headLength + headYOff);
		HairL.setOriginCenter();
		HairL.setRotation((float) headAngle);
		HairL.draw(batch);
		//HairL.draw(batch);
		if (headArmor != null) {
			headArmor.setFlip(true, false);
			headArmor.setPosition(_x - xOff+torsoCos*headLength, _y + HEIGHT - 36 + torsoHeightOff+torsoSin*headLength + headYOff);
			headArmor.setOriginCenter();
			headArmor.setRotation((float) headAngle);
			//headArmor.draw(batch);
			if (ArmorBox.head !=null)
			{
				headArmor.draw(batch);
			}
		}
	}
	
}
float angleFind(float x, float y, float x2,float y2)
{
	return ((x2 - x)/(y2 - y));
}
void arms(Sprite sa, Sprite armArmor, float legAngle, boolean back, float caa,int craftingImageID,Sprite ci)
{
	if (punch&& back == false&&pickPunch == false)
	{
		armAngle2 = armAngle;
		punchTimer++;
		float punchSpeedOffSet;
		if(-headAngle < -30)
		{
			punchSpeedOffSet = -3;
		}
		else if(-headAngle < -10)
		{
			punchSpeedOffSet = -2;
		}
		else if (-headAngle <30)
		{
			punchSpeedOffSet = 1;
		}
		else
		{
			punchSpeedOffSet = 3;
		}
		if (punchTimer == 1)
		{
			armAngle = legAngle;
		}
		if (punchD == -1 && armAngle<-51-headAngle&&pickPunch==false)
		{
			armAngle+=10;
		}
		else if (punchD == -1 && armAngle>-39-headAngle&&pickPunch==false)
		{
			armAngle-=10;
		}
		else if(punchD == -1)
		{
			punchD = 0;
		}
		if (armAngle < 100 - headAngle && punchD == 0)
		{
			armAngle += 14 + punchSpeedOffSet;
		}
		else if (punchD == 0)
		{
			punchD = 1;
		}
		if (armAngle > 80 - headAngle && punchD == 1)
		{
			armAngle -= 16;
		}
		else if (punchD == 1)
		{
			punchD = 2;
		}
		if (armAngle > legAngle && punchD == 2)
		{
			armAngle-= 8;
		}
		else if (punchD == 2)
		{
			punch = false;
			punchD = -1;
		}
	}
	
		else if(pickPunch==false)
	{
		armAngle2 = legAngle;
		
	}
	armAngle3 = legAngle;
	sa.setOrigin(54, 30);
	if(craftingTimer > 0)
	{
		if(craftingTimer  > craftingTimerInitial - 3 && craftingImageID > -1)
		{
		ci = new Sprite(new Texture(HUD.inventory2.item(craftingImageID).img));
		}
		if(craftingImageID > -1 && ci != null)
		{
		ci.setFlip(false, false);
		//batch.draw(Item, _x + 15 -xOff,_y + 10, 10, 60, 30, 30, 1, 1, armAngle2);
		ci.setPosition(_x + 22 - xOff, _y + 20 + torsoHeightOff);
		ci.setOrigin(10, 60);
		ci.setSize(20,20);
		ci.setRotation(caa+90);
		ci.draw(batch);
		}
		
	}
	if (right)
	{
		
		if(craftingTimer < 0)
		{
		sa.setRotation(armAngle2 - 90);
		}
		else
		{
			sa.setRotation(caa);
		}
		sa.setPosition(_x - 19 - xOff, _y + 54 + torsoHeightOff);
		//sa.addToDraw();
		Arms.draw(batch);
		if (armArmor != null && ArmorBox.body != null) {
			if(craftingTimer < 0)
			{
				armArmor.setRotation(armAngle2 - 90);
			}
			else
			{
				armArmor.setRotation(caa);
			}
			armArmor.setPosition(_x - 19 - xOff, _y + 54 + torsoHeightOff);
			armArmor.draw(batch);
		}
	}
	else
	{
		if(craftingTimer < 0)
		{
		sa.setRotation(-armAngle2 - 90);
		}
		else
		{
			sa.setRotation(caa);
		}
		sa.setPosition(_x - 23 - xOff, _y + 54 + torsoHeightOff);
		//sa.addToDraw();
		Arms.draw(batch);
		if (armArmor != null && ArmorBox.body != null) {
			if(craftingTimer < 0)
			{
				armArmor.setRotation(-armAngle2 - 90);
			}
			else
			{
				armArmor.setRotation(caa);
			}
			armArmor.setPosition(_x - 23 - xOff, _y + 54 + torsoHeightOff);
			armArmor.draw(batch);
		}
	}
}
public static void remove()
{
	if (createSet > 0 && placeTimer < 0 && Gdx.input.isButtonPressed(Input.Buttons.RIGHT))
	{
		HUD.inventory.item(createSet - 1).remove(1);
		placeTimer = 20;
	}
}
}
