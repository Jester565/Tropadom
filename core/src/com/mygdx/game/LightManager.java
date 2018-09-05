package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import box2dLight.ConeLight;
import box2dLight.DirectionalLight;
import box2dLight.PointLight;
import box2dLight.RayHandler;

public class LightManager {
		static float widthl,heightl;
		static public int day;
		int gridX = 0;
		public static int timer = 0;
		static DirectionalLight sun;
		Camera camera;
		ShapeRenderer sr;
		int BLOCKAMOUNT = 35;
		static World world;
		static Box2DDebugRenderer renderer;
	    static RayHandler dayHandler;
	 
	LightManager()
	{
		widthl = Gdx.graphics.getWidth()/5;
		heightl = Gdx.graphics.getHeight()/5;
		camera = new OrthographicCamera(widthl,heightl);
		camera.position.set(widthl*0.5f,heightl*0.5f,0);
		camera.update();
	}
	void nightLights()
	{
		day = 0;
	}
	void dayLights()
	{
		day = 1;	
	}
	void headLamp()
	{
		if(ArmorBox.head == "headLamp.png")
		{
			new ConeLight(dayHandler, 100, null,150, widthl/2, heightl/2+11f, (float) Protagonist.headAngle(), 30);
		}
	}
	void characterLight()
	{
		if(HUD.typeN != 11)
		{
			if(dayHandler.pointAtLight(widthl/2, heightl/2)||day == 0)
			{
				new PointLight(dayHandler,100,null,30,widthl/2,heightl/2);
			}
		}
	}
	void mainLights()
	{
		headLamp();
		if (HUD.typeN == 11)
		{
			new PointLight(dayHandler, 100, null, 100,widthl/2, heightl/2);
		}
		if (day == 1)
		{
			new DirectionalLight(dayHandler,600,null,270);
		}
	}
	void lightManagerOn2()
	{
		mainLights();
		if (Gdx.input.isKeyPressed(Input.Keys.F))
		{
			nightLights();
		}
		else
		{
			dayLights();
		}
		characterLight();
		dayHandler.updateAndRender();	
		world.step(1/2000f,6, 2);
		
			for (int i = 0; i < MyGdxGame.gridStack.size(); i++) {
				MyGdxGame.gridStack.get(i).destroyLight();
			}
		
		dayHandler.removeAll();
		if (timer == 500)
		{
			dayHandler.dispose();
			world.dispose();
			System.gc();
			timer = 0;
		}
	}
	void lightManagerOn1()
	{
		if (timer == 0)
		{
			world = new World(new Vector2(0,0),false);
			renderer = new Box2DDebugRenderer();
			dayHandler = new RayHandler(world);
			
			dayHandler.setCombinedMatrix(camera.combined); 
			
		}
		 Gdx.gl.glClearColor(0,0,day,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		renderer.render(world, camera.combined);
		dayHandler.removeAll();
		timer ++;
	}
}
