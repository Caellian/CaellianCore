/*******************************************************************************
 * Copyright (c) 2015 Contributors.
 * All rights reserved. This program and the accompanying materials are made available under
 * the terms of the GNU Lesser General Public
 * License v3.0 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 ******************************************************************************/

package hr.caellian.core.collections;

import java.io.Serializable;

/**
 * Created on 10.5.2015. at 1:27.
 * <p>
 * Convenience class firstly created for game Petulant archer as a key substitute for maps.
 * This is supposed to allow maps to use 3D (3 variable keys) keys.
 *
 * @author Feronzed
 */
public class Coordinates<X, Y, Z> implements Comparable, Serializable
{
	/**
	 * X coordinate
	 */
	private X x;

	/**
	 * Y coordinate
	 */
	private Y y;

	/**
	 * Z coordinate
	 */
	private Z z;

	public Coordinates(X x, Y y, Z z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * @param o
	 * 		Object to compare with this object
	 *
	 * @return Positive if 2 or more values of this object are higher than object compared to this.<br/>
	 * Negative if 2 or more values of this object are lower than object compared to this.
	 */
	@SuppressWarnings("NullableProblems")
	@Override
	public int compareTo(Object o)
	{
		if (this == o)
		{
			return 0;
		}
		if (!(o instanceof Coordinates))
		{
			return -1;
		}

		int result = 0;

		Coordinates<?, ?, ?> that = (Coordinates<?, ?, ?>) o;
		if (this.getX().hashCode() > that.getX().hashCode())
		{
			result++;
		}
		else if (this.getX().hashCode() < that.getX().hashCode())
		{
			result--;
		}
		if (this.getY().hashCode() > that.getY().hashCode())
		{
			result++;
		}
		else if (this.getY().hashCode() < that.getY().hashCode())
		{
			result--;
		}
		if (this.getZ().hashCode() > that.getZ().hashCode())
		{
			result++;
		}
		else if (this.getZ().hashCode() < that.getZ().hashCode())
		{
			result--;
		}
		return result;
	}

	/**
	 * @return X coordinate
	 */
	public X getX()
	{
		return x;
	}

	/**
	 * Sets the X coordinate to given value
	 *
	 * @param x
	 * 		X coordinate
	 */
	public void setX(X x)
	{
		this.x = x;
	}

	/**
	 * @return Y coordinate
	 */
	public Y getY()
	{
		return y;
	}

	/**
	 * Sets the Y coordinate to given value
	 *
	 * @param y
	 * 		Y coordinate
	 */
	public void setY(Y y)
	{
		this.y = y;
	}

	/**
	 * @return Z coordinate
	 */
	public Z getZ() {
		return z;
	}

	/**
	 * Sets the Z coordinate to given value
	 *
	 * @param z
	 * 		Z coordinate
	 */
	public void setZ(Z z)
	{
		this.z = z;
	}

	/**
	 * @return Hashcode of all coordinates
	 */
	@Override
	public int hashCode()
	{
		int result = getX() != null ? getX().hashCode() : 0;
		result = 31 * result + (getY() != null ? getY().hashCode() : 0);
		result = 31 * result + (getZ() != null ? getZ().hashCode() : 0);
		return result;
	}

	/**
	 * @param o
	 * 		Object to compare with this object
	 *
	 * @return True if all of the coordinates match
	 */
	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof Coordinates))
		{
			return false;
		}

		Coordinates<?, ?, ?> that = (Coordinates<?, ?, ?>) o;

		return !(getX() != null ? !getX().equals(that.getX()) : that.getX() != null) && !(getY() != null ? !getY().equals(that.getY()) : that.getY() != null) && !(getZ() != null ? !getZ().equals(that.getZ()) : that.getZ() != null);
	}
}
