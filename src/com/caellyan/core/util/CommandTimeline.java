package com.caellyan.core.util;

import java.util.Stack;

/**
 * Created on 19.5.2015. at 0:06.
 */
public class CommandTimeline
{
	public Stack<Command> undoStack = new Stack<>();
	public Stack<Command> redoStack = new Stack<>();
}