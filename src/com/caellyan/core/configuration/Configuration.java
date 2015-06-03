/*
 * Software developed by Feronzed.
 * Copyright (C) 2015 Feronzed
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

package com.caellyan.core.configuration;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Created on 06.03.15.
 */
@SuppressWarnings("UnusedDeclaration")
public class Configuration
{
	private Properties properties = new Properties();
	private IConfiguration configurationClass;
	private String documentName = "settings.cfg";
	private State  currentState = State.NULL;

	public Configuration(IConfiguration parConfigurationClass)
	{
		configurationClass = parConfigurationClass;
		currentState = State.DORMANT;
	}

	public void changeConfigName(String name)
	{
		documentName = name + ".cfg";
	}

	public void loadConfig()
	{
		if (currentState != State.NULL && currentState != State.IN_PROGRESS)
		{
			new ConfigurationLoader().start();
		}
	}

	public State getCurrentState()
	{
		return currentState;
	}

	public void updateConfigFile()
	{
		if (currentState == State.FINISHED)
		{
			try
			{
				Class configClass = configurationClass.getClass();
				Field[] configurationOptions = configClass.getDeclaredFields();

				for (final Field field : configurationOptions)
				{
					try
					{
						field.setAccessible(true);
						properties.setProperty(field.getName().toLowerCase(), field.get(field).toString());
					} catch (IllegalAccessException ia)
					{
						System.err.println("Unable to store configuration for '" + field.getName() + "', skipping...");
						ia.printStackTrace();
					}
				}

				OutputStream out = new FileOutputStream(new File(documentName));
				properties.store(out, "Configuration file:");
				out.close();
			} catch (IOException e)
			{
				System.err.println("Couldn't update configuration file!");
				e.printStackTrace();
			}
		}
	}

	public enum State
	{
		NULL, DORMANT, IN_PROGRESS, FINISHED, FAILED
	}

	protected class ConfigurationManager extends Thread
	{

		public ConfigurationManager()
		{
			this.setDaemon(true);
		}

		@Override
		public void run()
		{
			super.run();
			updateConfigFile();
		}
	}

	@SuppressWarnings("ResultOfMethodCallIgnored")
	protected class ConfigurationLoader extends Thread
	{
		private boolean stop = false;

		protected ConfigurationLoader()
		{
			this.setDaemon(true);
		}

		@Override
		public void run()
		{
			if (stop)
			{
				try
				{
					Thread.sleep(1000);
					return;
				} catch (InterruptedException e)
				{
					return;
				}
			}
			currentState = Configuration.State.IN_PROGRESS;
			try
			{

				File testFile = new File(documentName);
				if (!testFile.exists())
				{
					testFile.createNewFile();
				}
				else
				{
					if (testFile.isDirectory())
					{
						testFile.delete();
						testFile.createNewFile();
					}
				}

				properties.load(new FileInputStream(documentName));

				Class configClass = configurationClass.getClass();
				Field[] configurationOptions = configClass.getDeclaredFields();

				for (final Field field : configurationOptions)
				{
					if (properties.getProperty(field.getName().toLowerCase()) != null)
					{
						try
						{
							field.setAccessible(true);
							if (field.getType().isAssignableFrom(Byte.class))
							{
								field.set(field, Byte.parseByte(properties.getProperty(field.getName().toLowerCase())));
							}
							else if (field.getType().isAssignableFrom(Short.class))
							{
								field.set(field, Short.parseShort(properties.getProperty(field.getName().toLowerCase())));
							}
							else if (field.getType().isAssignableFrom(Integer.class))
							{
								field.set(field, Integer.parseInt(properties.getProperty(field.getName().toLowerCase())));
							}
							else if (field.getType().isAssignableFrom(Long.class))
							{
								field.set(field, Long.parseLong(properties.getProperty(field.getName().toLowerCase())));
							}
							else if (field.getType().isAssignableFrom(Float.class))
							{
								field.set(field, Float.parseFloat(properties.getProperty(field.getName().toLowerCase())));
							}
							else if (field.getType().isAssignableFrom(Double.class))
							{
								field.set(field, Double.parseDouble(properties.getProperty(field.getName().toLowerCase())));
							}
							else if (field.getType().isAssignableFrom(Boolean.class))
							{
								field.set(field, Boolean.parseBoolean(properties.getProperty(field.getName().toLowerCase())));
							}
							else if (field.getType().isAssignableFrom(Character.class))
							{
								field.set(field, properties.getProperty(field.getName().toLowerCase()).toCharArray()[0]);
							}
						} catch (Exception e)
						{
							e.printStackTrace();
							System.err.println("Unable to load configuration for '" + field.getName() + "', skipping...");
						}
					}
					else
					{
						try
						{
							field.setAccessible(true);
							properties.setProperty(field.getName().toLowerCase(), field.get(field).toString());
						} catch (IllegalAccessException e)
						{
							System.err.println("Unable to store configuration for '" + field.getName() + "', skipping...");
							e.printStackTrace();
						}
					}
				}
				OutputStream out = new FileOutputStream(new File(documentName));
				properties.store(out, "Configuration file:");
				out.close();
			} catch (FileNotFoundException fnf)
			{
				System.err.println("Couldn't find '" + documentName + "' file.");
				currentState = Configuration.State.FAILED;
				fnf.printStackTrace();
			} catch (IOException ioe)
			{
				System.err.println("Unable to write '" + documentName + "' file.");
				currentState = Configuration.State.FAILED;
				ioe.printStackTrace();
			}
			currentState = Configuration.State.FINISHED;
			new ConfigurationManager().start();
			try
			{
				this.join();
			} catch (InterruptedException e)
			{
				System.err.println("Unable to stop configuration loading thread.");
				System.err.println("Using alternative method of preventing code execution.");
				stop = true;
				e.printStackTrace();
			}
		}
	}
}
