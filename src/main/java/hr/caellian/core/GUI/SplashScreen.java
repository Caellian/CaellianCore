/*******************************************************************************
 * Copyright (c) 2015 Contributors.
 * All rights reserved. This program and the accompanying materials are made available under
 * the terms of the GNU Lesser General Public
 * License v3.0 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 ******************************************************************************/

package hr.caellian.core.GUI;

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
	//TODO:Add support for transparent pictures.

	public SplashScreen(URL image)
	{
		this(image, null);
	}

	public SplashScreen(URL image, URL icon) throws HeadlessException
	{
		try
		{
			this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(image))));
		} catch (IOException e)
		{
			System.err.println("Unable to set splash screen image!");
			e.printStackTrace();
		}
		if (icon != null)
		{
			this.setIconImage(new ImageIcon(icon).getImage());
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
