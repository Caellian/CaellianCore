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

package com.caellyan.core.versionControl;

import com.caellyan.core.util.IterableNodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;

/**
 * This is implementation of version handler. It takes a long time to read version files and it
 * is suggested that a {@code com.caellyan.core.SplashScreen} is displayed during initialization period of this
 * object.
 * <p>
 * Created by Caellian on 3.7.2015., at 3:05.
 */
public class VersionManager
{
	public VersionHistory versions = new VersionHistory();

	public VersionManager(URL versionFile)
	{
		this.initVersionData(versionFile);
	}

	private void initVersionData(URL versionFile)
	{
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		InputStream inputStream = null;

		try
		{
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			inputStream = versionFile.openStream();
			Document document = documentBuilder.parse(inputStream);

			document.normalize();

			Node root = document.getElementsByTagName("versions").item(0);

			for (Node source : new IterableNodeList(root.getChildNodes()))
			{
				if (Objects.equals(source.getNodeName(), "version"))
				{
					String version = null;
					String name = null;
					String changelog = null;
					URL download = null;

					for (Node dataNode : new IterableNodeList(source.getChildNodes()))
					{
						switch (dataNode.getNodeName())
						{
							case "version":
								version = dataNode.getTextContent();
								break;
							case "name":
								String possibleName = dataNode.getTextContent();
								name = !Objects.equals(possibleName, "") ? possibleName : null;
								break;
							case "changelog":
								String possibleChangelog = dataNode.getTextContent();
								changelog = !Objects.equals(possibleChangelog, "") ? possibleChangelog : null;
								break;
							case "download":
								String possibleDownload = dataNode.getTextContent();
								if (!Objects.equals(possibleDownload, ""))
								{
									download = new URL(dataNode.getTextContent());
								}
								break;
							default:
								break;
						}
					}
					versions.add(new Version(version, name != null ? Optional.of(name) : Optional.<String>empty(), changelog != null ? Optional.of(changelog) : Optional.<String>empty(), download != null ? Optional.of(download) : Optional.<URL>empty()));
				}
			}
		} catch (Exception e)
		{
			System.err.println("Unable to import sources from internet.");
			e.printStackTrace();
		} finally
		{
			try
			{
				assert inputStream != null : "Input Stream mustn't be null!";
				inputStream.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param programVersion
	 * 		  current version of program.
	 *
	 * @return true if there is newer version available.
	 */
	public boolean checkVersion(Version programVersion)
	{
		return this.getLatestVersion().compareTo(programVersion) < 0;
	}

	public Version getLatestVersion()
	{
		return versions.first();
	}
}
