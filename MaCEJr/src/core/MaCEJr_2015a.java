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

import math.mVector;
import physics.mAbstractWorldObject;
import physics.mWorld;
import processing.core.PApplet;

/**
 * @author Sonny Ruff
 */
public class MaCEJr_2015a extends mAbstractWorldObject
{
	public Cell[] cells;
	public int[][] connections;
	
	public boolean[] activeCells;
	public boolean[][] activeConnections;
	
	// CONSTRUCTOR
	public MaCEJr_2015a(mWorld world, mVector position, Cell[] cells, int[][] connections) throws RuntimeException
	{
		super(world, position);
		
		this.cells = cells;
		
		if(connections.length != connections[0].length)
			throw new RuntimeException("Kun je eigenlijk wel dingen? - Connections matrix isn't square");
		
		for(int i = 0; i < connections.length; i++)
		{
			for(int j = 0; j < connections[i].length; j++)
			{
				if(connections[i][j] != 1 &&
				   connections[i][j] != 0 &&
				   connections[i][j] != -1
				)
					throw new RuntimeException("Kun je eigenlijk wel dingen? - Unknown entry");
			}
		}
		this.connections = connections;
		
		activeCells = new boolean[connections.length];
		activeConnections = new boolean[connections.length][connections.length];
	}
	
	public void update()
	{
		// Input to connections
		for(int i = 0; i < cells.length; i++) // TODO Kan ook met matrix operaties waarschijnlijk
		{
			for(int j = 0; j < cells.length; j++)
			{
				activeConnections[i][j] = false;
				
				switch(connections[i][j])
				{
					case 1:
						if(activeCells[j])
							activeConnections[i][j] = true;
						break;
					case -1:
						if(!activeCells[j])
							activeConnections[i][j] = true;
						break;
				}
			}
		}
		
		// Connections to output
		for(int i = 0; i < cells.length; i++)
		{
			activeCells[i] = false;
			
			for(int j = 0; j < cells.length; j++)
			{
				if(activeConnections[i][j])
					activeCells[i] = true;
			}
		}
		
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// VISUALS ====================================================
	public int cellRad = 30;
	public int margin = 40;
	
	public void draw(PApplet applet, int x, int y)
	{
		applet.pushMatrix();
		applet.translate(x, y);
		
		applet.strokeWeight(3);
		applet.stroke(200, 200, 200);
		
		for(int i = 0; i < cells.length; i++)
		{
			for(int j = 0; j < cells.length; j++)
			{
				if(activeConnections[i][j])
				{
					applet.fill(200, 200, 0);
				} else {
					applet.fill(100, 100, 100);
				}
				applet.ellipse(
						j * margin,
						i * margin,
						cellRad,
						cellRad
					);
			}
		}
		
		applet.pushMatrix();
		applet.translate(200, 200);
		
		applet.strokeWeight(1);
		applet.stroke(200, 200, 200);
		applet.noFill();
		
		applet.ellipse(
				0,
				0,
				200,
				200
			);
		
		applet.strokeWeight(2);
		applet.stroke(200, 200, 200);
		applet.noFill();
		
		for(int i = 0; i < cells.length; i++)
		{
			for(int j = 0; j < cells.length; j++)
			{
				if(connections[i][j] == 1)
				{
					double thetaI = i * 2 * Math.PI / cells.length;
					double thetaJ = j * 2 * Math.PI / cells.length;
					
					applet.bezier(
							(float)Math.cos(thetaI) * 100, (float)Math.sin(thetaI) * 100,
							(float)Math.cos(thetaI) * 20, (float)Math.sin(thetaI) * 20,
							(float)Math.cos(thetaJ) * 20, (float)Math.sin(thetaJ) * 20,
							(float)Math.cos(thetaJ) * 100, (float)Math.sin(thetaJ) * 100
						);
				}
				if(connections[i][j] == -1)
				{
					double thetaI = i * 2 * Math.PI / cells.length;
					double thetaJ = j * 2 * Math.PI / cells.length;
					
					applet.bezier(
							(float)Math.cos(thetaI) * 100, (float)Math.sin(thetaI) * 100,
							(float)Math.cos(thetaI) * 20, (float)Math.sin(thetaI) * 20,
							(float)Math.cos(thetaJ) * 20, (float)Math.sin(thetaJ) * 20,
							(float)Math.cos(thetaJ) * 100, (float)Math.sin(thetaJ) * 100
						);
				}
			}
		}
		
		applet.strokeWeight(3);
		applet.stroke(200, 200, 200);
		applet.fill(100, 100, 100);
		
		for(int i = 0; i < cells.length; i++)
		{
			double theta = i * 2 * Math.PI / cells.length;
			
			applet.ellipse(
					(float)Math.cos(theta) * 100,
					(float)Math.sin(theta) * 100,
					cellRad,
					cellRad
				);
		}
		
		applet.popMatrix();
		applet.popMatrix();
	}
	
	//===================================================
	// NESTED CLASSES
	//===================================================
	public static class Cell
	{
		
	}
}