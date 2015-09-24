/*******************************************************************************
 * Copyright (c) 2015 Contributors.
 * All rights reserved. This program and the accompanying materials are made available under
 * the terms of the GNU Lesser General Public
 * License v3.0 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 ******************************************************************************/

package hr.caellian.core.processManagement;

import java.util.ArrayList;

/**
 * <p>
 * This is an alternative method of execution management. It is highly unstable
 * and is recommended only if you're using it as a layer to build you own execution management on top.
 * <p>
 * This method can also be used for more flexible execution management, but it might cause unpredicted
 * behaviour.
 *
 * @author Caellian
 */
public class ExecutionManagement {
    private ArrayList<Command> commandList = new ArrayList<>();

    public void commandExecuted(Command command) {
        commandList.add(command);
    }

    public void redo(int position) {
        commandList.get(position).redo();
    }

    public void undo(int position) {
        commandList.get(position).undo();
    }
}
