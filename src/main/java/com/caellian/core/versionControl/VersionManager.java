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

package com.caellian.core.versionControl;

import com.caellian.core.util.IterableNodeList;
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
 * is suggested that a {@code com.caellian.core.SplashScreen} is displayed during initialization period of this
 * object.
 * <p>
 *
 * @author Caellian
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
					Version version = null;
					String name = null;
					String changelog = null;
					URL download = null;

					for (Node dataNode : new IterableNodeList(source.getChildNodes()))
					{
						switch (dataNode.getNodeName())
						{
							case "version":
								String[] versionStrings = dataNode.getTextContent().split("\\.");
								version = new Version(Short.parseShort(versionStrings[0]), Short.parseShort(versionStrings[1]), Short.parseShort(versionStrings[2]));
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
					versions.add(new VersionData(version, name != null ? Optional.of(name) : Optional.<String>empty(), changelog != null ? Optional.of(changelog) : Optional.<String>empty(), download != null ? Optional.of(download) : Optional.<URL>empty()));
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
	 * @param programVersionData
	 * 		current version of program.
	 *
	 * @return true if there is newer version available.
	 */
	public boolean checkVersion(VersionData programVersionData)
	{
		return this.getLatestVersion().compareTo(programVersionData) < 0;
	}

	public VersionData getLatestVersion()
	{
		return versions.first();
	}
}
