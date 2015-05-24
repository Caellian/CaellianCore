package com.feronzed.core.util;

import java.util.Stack;

/**
 * Created on 19.5.2015. at 0:06.
 */
public class CommandTimeline
{
	public Stack<Command> undoStack;
	public Stack<Command> redoStack;

	public abstract class Command
	{
		public void firstExecute()
		{
			undoStack.add(this);
			redoStack.clear();
		}

		public void execute()
		{
			undoStack.add(this);
		}

		public void undo()
		{
			redoStack.push(this);
		}
	}
}