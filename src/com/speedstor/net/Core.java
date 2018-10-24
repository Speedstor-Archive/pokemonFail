package com.speedstor.net;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Core extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;

	//Initial Variables
		//Public
			public static float width = 1080F, height = width / 16 * 9;
			public boolean running = false;
			
		//Private
			Handler handler;
			Random r;

			private Thread thread;
			
		//Game Engine (HAHA)
			public static int time;
			public static int runTime;
			
	//Source - Core -----------------------------------------------------//
	public static void main(String[] args) {
		new Core();
	}
	
	public Core() {
		handler = new Handler();
		
		//Keyboard / Mouse Input
		//addKeyListener(new Input(handler());
		
		//Window Creation
		new Window((int)width, (int)height, "Pokemon going to fail", this);	
	}
	
	
	//Game Engine ----------------------------------------------------------//
	public void run() {
		requestFocus();
		System.out.println(System.currentTimeMillis());
		long lastTime = System.nanoTime();
		double amountOfTicks = 60;
		double ns = 1/amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			System.out.println(running);
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta>= 1) {
				tick();
				delta -= 1;
			}
			render();
			frames++;
			
			
			
			/* if(System.currentTimeMillis() - timer > 1000) {
				timer = System.currentTimeMillis();
				System.out.println("FPS: " + frames);
				frames = 0;
				time += 1;
				if(time > 60) {
					runTime += 1;
					System.out.println("Minutes :" + runTime);
					time = 0;
				}
			}*/
		}
		stop();
	}
	public synchronized void start() {
		this.thread = new Thread(this);
		thread.start();
		running = true;
		System.out.println(running);
	}
	public void stop() {
		try {
			System.out.println("you suck, there's a bug");
			this.thread.join();
			running = false;
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	//tick & render ----------------------------------------------------------//
	public void tick() {
		handler.tick();
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		Color black = Color.black;
		
		g.setColor(black);
		g.fillRect(0, 0, (int)width, (int)height);
		
		handler.render(g);
		
		g.dispose();
		bs.show();
		
	}
	
}
