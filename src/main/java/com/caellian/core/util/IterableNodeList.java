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
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.caellian.core.util;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
 * Convenience method created to make Nodes from {@code org.w3c.dom.Node} a lot easier to use
 * and more compact.
 * <p>
 *
 * @author Caellian
 */
public class IterableNodeList extends ArrayList<Node> implements NodeList
{
	public IterableNodeList(NodeList nodeList)
	{
		for (int node = 0; node < nodeList.getLength(); node++)
		{
			super.add(node, nodeList.item(node));
		}
	}

	@Override
	public Node item(int index)
	{
		return super.get(index);
	}

	@Override
	public int getLength()
	{
		return super.size();
	}
}
