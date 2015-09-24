/*******************************************************************************
 * Copyright (c) 2015 Contributors.
 * All rights reserved. This program and the accompanying materials are made available under
 * the terms of the GNU Lesser General Public
 * License v3.0 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 ******************************************************************************/

package hr.caellian.core.configuration;

/**
 * This annotation allows more precise configuration options.
 *
 * @author Caellian
 */
public @interface ConfigData {
    /**
     * @return True if this option should be forcefully generated in configuration if it's value is invalid or the
     * option doesn't exits.
     */
    boolean regenerateInvalid() default true;
}
