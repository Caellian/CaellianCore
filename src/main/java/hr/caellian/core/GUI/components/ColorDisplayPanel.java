/*******************************************************************************
 * Copyright (c) 2015 Contributors.
 * All rights reserved. This program and the accompanying materials are made available under
 * the terms of the GNU Lesser General Public
 * License v3.0 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 ******************************************************************************/

package hr.caellian.core.GUI.components;

import hr.caellian.core.processManagement.Command;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This component is used to visualize colors in GUIs.
 * It's color can be changed by clicking on it, and it's color can ba later
 * applied to other elements.
 *
 * @author Caellian
 */
public class ColorDisplayPanel extends JPanel
{
	final String csString;
	ColorDisplayPanel self;
	Command executeAfter = null;

	public ColorDisplayPanel(String title)
	{
		super(null);
		self = this;
		csString = title;
		this.setBackground(Color.WHITE);
		this.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				super.mouseClicked(e);
				self.setBackground(JColorChooser.showDialog(self, csString, null));
				if (executeAfter != null)
				{
					executeAfter.execute();
				}
			}
		});
	}

	public ColorDisplayPanel executeCommandAfter(Command command)
	{
		executeAfter = command;
		return this;
	}
}
