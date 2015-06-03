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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.caellyan.core.util;

import java.io.Serializable;

/**
 * Created on 10.5.2015. at 1:27.
 *
 * Convenience class firstly created for game Petulant archer as a key substitute for maps.
 * This is supposed to allow maps to use 3D (3 variable keys) keys.
 *
 * @author Feronzed
 */
public class Coordinates<X, Y, Z> implements Comparable, Serializable
{
	private X x;
	private Y y;
	private Z z;

	public Coordinates(X x, Y y, Z z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

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

	public X getX()
	{
		return x;
	}

	public Y getY()
	{
		return y;
	}

	public Z getZ()
	{
		return z;
	}

	public void setZ(Z z)
	{
		this.z = z;
	}

	public void setY(Y y)
	{
		this.y = y;
	}

	public void setX(X x)
	{
		this.x = x;
	}

	@Override
	public int hashCode()
	{
		int result = getX() != null ? getX().hashCode() : 0;
		result = 31 * result + (getY() != null ? getY().hashCode() : 0);
		result = 31 * result + (getZ() != null ? getZ().hashCode() : 0);
		return result;
	}

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
