/*
 * Copyright (c) 2016, Sonny Ruff
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

public class BasicActionSet extends mAbstractActionSet
{
	{
		names = new String[]
			{
				"increase value",
				"increase value by amount",
				"decrease value",
				"decrease value by amount"
			};
		
		actions.put(names[0], new Class<?>[]{Integer.class});
		actions.put(names[1], new Class<?>[]{Integer.class, Integer.class});
		actions.put(names[2], new Class<?>[]{Integer.class});
		actions.put(names[3], new Class<?>[]{Integer.class, Integer.class});
	}
	
	public static void perform(String methodName, Object[] var)
	{
		if(methodName.equals(names[0]))
		{
			increaseValue((int)var[0]);
		}
		else if(methodName.equals(names[1]))
		{
			increaseValue((int)var[0], (int)var[1]);
		}
		else if(methodName.equals(names[2]))
		{
			decreaseValue((int)var[0]);
		}
		else if(methodName.equals(names[3]))
		{
			decreaseValue((int)var[0], (int)var[1]);
		}
	}

	private static void increaseValue(int var)
	{
		var++;
	}
	private static void increaseValue(int var, int num)
	{
		var += num;
	}
	private static void decreaseValue(int var)
	{
		var--;
	}
	private static void decreaseValue(int var, int num)
	{
		var -= num;
	}
}