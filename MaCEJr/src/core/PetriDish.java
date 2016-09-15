/*
 * Copyright (c) 2015, Sonny Ruff
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 3. Neither the name of Sonny Ruff nor the names
 *    of its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 */

package core;

import core.MaCEJr_2015a.Cell;
import math.mVector;
import physics.mAbstractWorldObject;
import physics.mWorld;
import processing.core.PApplet;

public class PetriDish extends PApplet
{
	private mWorld world;
	
	private GenerationData[] log;
	
	private mAbstractActionSet actionSet = new BasicActionSet();
	
	public void settings()
	{
		size(1280, 720);
		smooth(3);
	}
	public void setup()
	{
		frameRate(1);
		
		world = new mWorld();
		
		Cell[] cells =
		{
			new Cell(),
			new Cell(),
			new Cell()
		};
		int[][] connections = 
		{
			{0, 0,-1},
			{1, 0, 0},
			{0, 1, 0}
		};
		world.addObject(new MaCEJr_2015a(world, new mVector(), cells, connections));
	}
	public void draw()
	{
		background(20);
		
		for(mAbstractWorldObject o : world.getObjects())
		{
			o.update();
		}
		
		for(mAbstractWorldObject o : world.getObjects())
		{
			o.draw(this, 30, 30);
		}
	}

	//===================================================
	// NESTED CLASSES
	//===================================================
	private class GenerationData
	{
		public String id;
		public long startTime;
		public long endTime;
		
		public int[][] connections;
	}
	
	//===================================================||======
	// MAIN
	//===================================================||======
	public static void main(String[] args)
	{
		PApplet.main(new String[] {PetriDish.class.getName()});
	}
}