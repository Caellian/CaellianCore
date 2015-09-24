/*******************************************************************************
 * Copyright (c) 2015 Contributors.
 * All rights reserved. This program and the accompanying materials are made available under
 * the terms of the GNU Lesser General Public
 * License v3.0 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 ******************************************************************************/

package hr.caellian.core.versionControl;

import java.net.URL;
import java.util.Optional;

/**
 * Class used to store and handle version data.
 *
 * @author Caellian
 */

public class VersionData implements Comparable<VersionData>
{
	public final Version          version;
	public final Optional<String> name;
	public final Optional<String> changelog;
	public final Optional<URL>    downloadLink;

	public VersionData(Version version)
	{
		this(version, Optional.<String>empty(), Optional.<String>empty(), Optional.<URL>empty());
	}

	public VersionData(Version version, Optional<String> name, Optional<String> changelog, Optional<URL> downloadLink)
	{
		this.version = version;
		this.name = name;
		this.changelog = changelog;
		this.downloadLink = downloadLink;
	}

	@SuppressWarnings("NullableProblems")
	@Override
	public int compareTo(VersionData other)
	{
		return this.version.major - other.version.major == 0 ? (this.version.minor - other.version.minor == 0 ? (this.version.patch - other.version.patch == 0 ? 0 : (this.version.patch - other.version.patch)) : (this.version.minor - other.version.minor)) : (this.version.major - other.version.major);
	}

}
