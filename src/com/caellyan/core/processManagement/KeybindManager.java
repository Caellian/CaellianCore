/*
 * CaellianCore, universal library
 * Copyright (C) 2015 Caellian
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.caellyan.core.processManagement;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Caellian on 27.6.2015., at 15:38.
 */
public class KeybindManager implements KeyListener
{
	private HashMap<List<Integer>, Command> keybinds = new HashMap<>();

	private ArrayList<Integer> pressedKeys = new ArrayList<>();

	public KeybindManager(Component component)
	{
		component.addKeyListener(this);
	}

	public KeybindManager addKeybind(Command command, Integer... keys)
	{
		keybinds.put(Arrays.asList(keys), command);
		return this;
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		pressedKeys.add(e.getKeyCode());
		check();
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		pressedKeys.remove(e.getKeyCode());
	}

	private void check()
	{
		Thread checkThread = new Thread(()->{
			keybinds.forEach((integers, command)->{
				if (integers.containsAll(pressedKeys))
				{
					command.execute();
				}
			});
		});
		checkThread.setDaemon(true);
		checkThread.run();
		try
		{
			checkThread.join();
		} catch (InterruptedException ie)
		{
			ie.printStackTrace();
		}
	}
}
