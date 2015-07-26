package com.caellian.core.configuration;

/**
 * This annotation allows more precise configuration options.
 *
 * @author Caellian
 */
public @interface ConfigData
{
	/**
	 * @return True if this option should be forcefully generated in configuration if it's value is invalid or the
	 * option doesn't exits.
	 */
	boolean regenerateInvalid() default true;
}
