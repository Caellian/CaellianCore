/*******************************************************************************
 * Copyright (c) 2015 Contributors.
 * All rights reserved. This program and the accompanying materials are made available under
 * the terms of the GNU Lesser General Public
 * License v3.0 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 ******************************************************************************/

package hr.caellian.core.configuration;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Properties;

/**
 * Created on 06.03.15.
 * <p>
 * This is universal class for easy configuration loading.
 */
public class Configuration
{
	private final IConfiguration configurationClass;
	private Properties properties   = new Properties();
	private String     documentName = "settings.cfg";
	private State      currentState = State.NULL;

	public Configuration(IConfiguration parConfigurationClass)
	{
		configurationClass = parConfigurationClass;
		currentState = State.DORMANT;
	}

	/**
	 * Changes configuration file name.
	 * Can be used before either saving or loading the file.
	 *
	 * @param name
	 * 		new file name.
	 */
	public void changeConfigName(String name)
	{
		documentName = name + ".cfg";
	}

	/**
	 * Loads configuration for {@code configurationClass}.
	 */
	public void loadConfig()
	{
		if (currentState != State.NULL && currentState != State.IN_PROGRESS)
		{
			new ConfigurationLoader().start();
		}
	}

	/**
	 * Returns current loading state.
	 *
	 * @return current loading state
	 */
	public State getCurrentState()
	{
		return currentState;
	}

	/**
	 * Updates configuration file.
	 * This method should be used after initialisation to store configuration if it has been changed.
	 */
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
		else
		{
			System.err.println("Configuration not yet loaded!");
		}
	}

	/**
	 * Enumeration of different states configuration could be in during initialisation phase.
	 */
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

	protected class ConfigurationLoader extends Thread
	{
		private boolean stop = false;

		protected ConfigurationLoader()
		{
			this.setDaemon(true);
		}

		@SuppressWarnings("ResultOfMethodCallIgnored")
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
							if (field.getType().isAssignableFrom(Byte.class) || Objects.equals(field.getType().toString(), "byte"))
							{
								field.set(field, Byte.parseByte(properties.getProperty(field.getName().toLowerCase())));
							}
							else if (field.getType().isAssignableFrom(Short.class) || Objects.equals(field.getType().toString(), "short"))
							{
								field.set(field, Short.parseShort(properties.getProperty(field.getName().toLowerCase())));
							}
							else if (field.getType().isAssignableFrom(Integer.class) || Objects.equals(field.getType().toString(), "int"))
							{
								field.set(field, Integer.parseInt(properties.getProperty(field.getName().toLowerCase())));
							}
							else if (field.getType().isAssignableFrom(Long.class) || Objects.equals(field.getType().toString(), "long"))
							{
								field.set(field, Long.parseLong(properties.getProperty(field.getName().toLowerCase())));
							}
							else if (field.getType().isAssignableFrom(Float.class) || Objects.equals(field.getType().toString(), "float"))
							{
								field.set(field, Float.parseFloat(properties.getProperty(field.getName().toLowerCase())));
							}
							else if (field.getType().isAssignableFrom(Double.class) || Objects.equals(field.getType().toString(), "double"))
							{
								field.set(field, Double.parseDouble(properties.getProperty(field.getName().toLowerCase())));
							}
							else if (field.getType().isAssignableFrom(Boolean.class) || Objects.equals(field.getType().toString(), "boolean"))
							{
								field.set(field, Boolean.parseBoolean(properties.getProperty(field.getName().toLowerCase())));
							}
							else if (field.getType().isAssignableFrom(Character.class) || Objects.equals(field.getType().toString(), "character"))
							{
								field.set(field, properties.getProperty(field.getName().toLowerCase()).toCharArray()[0]);
							}
							else if (field.getType().isAssignableFrom(String.class))
							{
								field.set(field, properties.getProperty(field.getName().toLowerCase()));
							}
						} catch (Exception e)
						{
							e.printStackTrace();
							System.err.println("Unable to load configuration for '" + field.getName() + "', skipping...");
						}
					}
					else
					{
						ConfigData configData = field.getDeclaredAnnotation(ConfigData.class);
						if ((configData != null && configData.regenerateInvalid()) || configData == null)
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
