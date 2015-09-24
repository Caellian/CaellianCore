/*******************************************************************************
 * Copyright (c) 2015 Contributors.
 * All rights reserved. This program and the accompanying materials are made available under
 * the terms of the GNU Lesser General Public
 * License v3.0 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 ******************************************************************************/

package hr.caellian.core.lib;

import hr.caellian.core.versionControl.Version;
import hr.caellian.core.versionControl.VersionData;

/**
 * @author Caellian
 */
public class Reference {
    public static final String LIBRARY_NAME = "CaellianCore";
    public static final String LIBRARY_ID = "caellian_core";
    public static final String LIBRARY_VERSION = "1.0.4";
    public static final String LIBRARY_AUTHOR = "Caellian";

    private static final String[] versionNumbers = LIBRARY_VERSION.split(".");

    public static final VersionData VERSION = new VersionData(new Version(Short.parseShort(versionNumbers[0]), Short.parseShort(versionNumbers[1]), Short.parseShort(versionNumbers[2])));
}
