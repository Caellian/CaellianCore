/*******************************************************************************
 * Copyright (c) 2015 Contributors.
 * All rights reserved. This program and the accompanying materials are made available under
 * the terms of the GNU Lesser General Public
 * License v3.0 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 ******************************************************************************/

package hr.caellian.core.processManagement;

import java.util.Stack;

/**
 * @author Caellian
 */
public class ExecutionTimeline {
    private Stack<Command> past = new Stack<>();
    private Stack<Command> future = new Stack<>();

    public void commandExecuted(Command command) {
        past.push(command);
        future.clear();
    }

    public void redo() {
        future.pop().redo();
    }

    public void undo() {
        Command command = past.pop();
        future.push(command);
        command.undo();
    }
}
