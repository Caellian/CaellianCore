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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;

/**
 * Created by Caellian on 3.7.2015., at 16:55.
 */
public class VersionHistory extends ArrayList<Version> implements SortedSet<Version>, Comparator<Version>
{

	public VersionHistory()
	{
	}

	public VersionHistory(List<Version> versions)
	{
		this.addAll(versions);
		this.sort(this);
	}

	@Override
	public Comparator<? super Version> comparator()
	{
		return this;
	}

	@Override
	public SortedSet<Version> subSet(Version fromElement, Version toElement)
	{
		ArrayList<Version> versions = new ArrayList<>();
		for (int version = this.indexOf(fromElement); version < this.indexOf(toElement); version++)
		{
			versions.add(this.get(version));
		}
		return new VersionHistory(versions);
	}

	@Override
	public SortedSet<Version> headSet(Version toElement)
	{
		ArrayList<Version> versions = new ArrayList<>();
		for (int version = 0; version < this.indexOf(toElement); version++)
		{
			versions.add(this.get(version));
		}
		return new VersionHistory(versions);
	}

	@Override
	public SortedSet<Version> tailSet(Version fromElement)
	{
		ArrayList<Version> versions = new ArrayList<>();
		for (int version = this.indexOf(fromElement); version < this.size(); version++)
		{
			versions.add(this.get(version));
		}
		return new VersionHistory(versions);
	}

	/**
	 * @return latest version.
	 */
	@Override
	public Version first()
	{
		return this.get(0);
	}

	/**
	 * @return oldest version.
	 */
	@Override
	public Version last()
	{
		return this.get(this.size() - 1);
	}

	@Override
	public int compare(Version preVersion, Version postVersion)
	{
		return preVersion.getVersionValue() - postVersion.getVersionValue();
	}
}
