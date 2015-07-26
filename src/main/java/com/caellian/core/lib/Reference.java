package com.caellian.core.lib;

import com.caellian.core.versionControl.Version;
import com.caellian.core.versionControl.VersionData;

/**
 * @author Caellian
 */
public class Reference
{
	public static final String LIBRARY_NAME    = "CaellianCore";
	public static final String LIBRARY_ID      = "caellian_core";
	public static final String LIBRARY_VERSION = "#{version}";
	public static final String LIBRARY_AUTHOR  = "Caellian";

	private static final String[] versionNumbers = LIBRARY_VERSION.split(".");

	public static final VersionData VERSION = new VersionData(new Version(Short.parseShort(versionNumbers[0]), Short.parseShort(versionNumbers[1]), Short.parseShort(versionNumbers[2])));
}
