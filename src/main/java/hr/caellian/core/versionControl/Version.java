/*******************************************************************************
 * Copyright (c) 2015 Contributors.
 * All rights reserved. This program and the accompanying materials are made available under
 * the terms of the GNU Lesser General Public
 * License v3.0 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 ******************************************************************************/

package hr.caellian.core.versionControl;

/**
 * @author Caellian
 */
public class Version implements Comparable<Version>, Cloneable
{
	public final short major;
	public final short minor;
	public final short patch;

	public Version(short major, short minor, short patch)
	{
		this.major = major;
		this.minor = minor;
		this.patch = patch;
	}

	@Override
	public int hashCode()
	{
		int result = (int) major;
		result = 31 * result + (int) minor;
		result = 31 * result + (int) patch;
		return result;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof Version))
		{
			return false;
		}

		Version version = (Version) o;

		return major == version.major && minor == version.minor && patch == version.patch;
	}

	@Override
	public String toString()
	{
		return major + "." + minor + "." + patch;
	}

	@SuppressWarnings("NullableProblems")
	@Override
	public int compareTo(Version o) {
		if (this.major == o.major) {
			if (this.minor == o.minor) {
				return this.patch - o.patch;
			} else {
				return this.minor - o.minor;
			}
		} else {
			return this.major - o.major;
		}
	}
}
