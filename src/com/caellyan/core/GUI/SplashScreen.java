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

package com.caellyan.core.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created on 21.4.2015. at 15:23.
 */
public class SplashScreen extends JFrame
{
	public SplashScreen(URL image) throws HeadlessException
	{
		try
		{
			this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(image))));
		} catch (IOException e)
		{
			System.err.println("Unable to set splash screen image!");
			e.printStackTrace();
		}
		this.setResizable(false);
		this.setUndecorated(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setTitle("SplashScreen");
		this.pack();
		this.setLocationRelativeTo(null);
	}

	public void showSplash()
	{
		this.setOpacity(0);
		this.setVisible(true);
		for (float transparency = 0; transparency < 1; transparency += 0.0001)
		{
			this.setOpacity(transparency);
		}
	}

	public void hideSplash()
	{
		for (float transparency = 1; transparency > 0; transparency -= 0.01)
		{
			this.setOpacity(transparency);
		}
		this.setVisible(false);
	}
}
