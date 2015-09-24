/*******************************************************************************
 * Copyright (c) 2015 Contributors.
 * All rights reserved. This program and the accompanying materials are made available under
 * the terms of the GNU Lesser General Public
 * License v3.0 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 ******************************************************************************/

package hr.caellian.core.versionControl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;

/**
 * @author Caellian
 */
public class VersionHistory extends ArrayList<VersionData> implements SortedSet<VersionData>, Comparator<VersionData>
{
	public VersionHistory()
	{
	}

	public VersionHistory(List<VersionData> versionDatas)
	{
		this.addAll(versionDatas);
		this.sort(this);
	}

	@Override
	public Comparator<? super VersionData> comparator()
	{
		return this;
	}

	@SuppressWarnings("NullableProblems")
	@Override
	public SortedSet<VersionData> subSet(VersionData fromElement, VersionData toElement)
	{
		ArrayList<VersionData> versionDatas = new ArrayList<>();
		for (int version = this.indexOf(fromElement); version < this.indexOf(toElement); version++)
		{
			versionDatas.add(this.get(version));
		}
		return new VersionHistory(versionDatas);
	}

	@SuppressWarnings("NullableProblems")
	@Override
	public SortedSet<VersionData> headSet(VersionData toElement)
	{
		ArrayList<VersionData> versionDatas = new ArrayList<>();
		for (int version = 0; version < this.indexOf(toElement); version++)
		{
			versionDatas.add(this.get(version));
		}
		return new VersionHistory(versionDatas);
	}

	@SuppressWarnings("NullableProblems")
	@Override
	public SortedSet<VersionData> tailSet(VersionData fromElement)
	{
		ArrayList<VersionData> versionData = new ArrayList<>();
		for (int version = this.indexOf(fromElement); version < this.size(); version++)
		{
			versionData.add(this.get(version));
		}
		return new VersionHistory(versionData);
	}

	/**
	 * @return latest version.
	 */
	@Override
	public VersionData first()
	{
		return this.get(0);
	}

	/**
	 * @return oldest version.
	 */
	@Override
	public VersionData last()
	{
		return this.get(this.size() - 1);
	}

	@Override
	public int compare(VersionData preVersionData, VersionData postVersionData)
	{
		return preVersionData.compareTo(postVersionData);
	}
}
