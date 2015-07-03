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

import java.net.URL;
import java.util.Optional;

/**
 * Created by Caellian on 3.7.2015., at 15:24.
 */
public class Version implements Comparable<Version>
{
	public final String           version;
	public final Optional<String> name;
	public final Optional<String> changelog;
	public final Optional<URL>    downloadLink;

	public Version(String version)
	{
		this(version, Optional.<String>empty(), Optional.<String>empty(), Optional.<URL>empty());
	}

	public Version(String version, Optional<String> name, Optional<String> changelog, Optional<URL> downloadLink)
	{
		this.version = version;
		this.name = name;
		this.changelog = changelog;
		this.downloadLink = downloadLink;
	}

	public String getName()
	{
		return name.get();
	}

	public String getChangelog()
	{
		return changelog.get();
	}

	public URL getDownloadLink()
	{
		return downloadLink.get();
	}

	@Override
	public int compareTo(Version other)
	{
		return this.getVersionValue() - other.getVersionValue();
	}

	public int getVersionValue()
	{
		String[] versionNumbers = this.getVersion().split("\\.");
		int result = 0;

		result += Integer.parseInt(versionNumbers[2]);
		result += Integer.parseInt(versionNumbers[1]) * 1000;
		result += Integer.parseInt(versionNumbers[0]) * 1000000;

		return result;
	}

	public String getVersion()
	{
		return version;
	}
}
