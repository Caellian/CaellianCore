package com.feronzed.core.util;

/**
 * Created by caellian on 24.05.15..
 */
public abstract class Command
{
	private CommandTimeline commandTimeline;

	public Command(CommandTimeline commandTimeline)
	{
		this.commandTimeline = commandTimeline;
	}

	public void firstExecute()
	{
		commandTimeline.undoStack.add(this);
		commandTimeline.redoStack.clear();
	}

	public void execute()
	{
		commandTimeline.undoStack.add(this);
	}

	public void undo()
	{
		commandTimeline.redoStack.push(this);
	}
}
