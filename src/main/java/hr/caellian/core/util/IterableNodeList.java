/*******************************************************************************
 * Copyright (c) 2015 Contributors.
 * All rights reserved. This program and the accompanying materials are made available under
 * the terms of the GNU Lesser General Public
 * License v3.0 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 ******************************************************************************/

package hr.caellian.core.util;

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
public class IterableNodeList extends ArrayList<Node> implements NodeList {
    public IterableNodeList(NodeList nodeList) {
        for (int node = 0; node < nodeList.getLength(); node++) {
            super.add(node, nodeList.item(node));
        }
    }

    @Override
    public Node item(int index) {
        return super.get(index);
    }

    @Override
    public int getLength() {
        return super.size();
    }
}
