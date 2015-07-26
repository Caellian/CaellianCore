package com.caellian.core.versionControl;

/**
 * @author Caellian
 */
public class Version
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
}
