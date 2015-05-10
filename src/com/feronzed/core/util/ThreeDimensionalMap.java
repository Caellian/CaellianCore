/*
 Feronzed Core, utility code
 Copyright (C) 2015 Skythees

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.feronzed.core.util;

import java.io.Serializable;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created on 10.5.2015. at 0:56.
 * <p>
 * <p>
 * This map is a modified version of {@link Map} created to optimise
 * code for game PetulantArcher and other games later designed by
 * Skythees.
 *
 * It allows 3 keys per value thus making this map more effective than
 * map of a map of a map containing the {@code <V>}.
 *
 * All further documentation can be found on Oracle's website
 * or contained within {@link Map} class.
 *
 * @param <X>
 * 		  represents x coordinate in 3D plane
 * @param <Y>
 * 		  represents y coordinate in 3D plane
 * @param <Z>
 * 		  represents z coordinate in 3D plane
 * @param <V>
 * 		  value located on intersection of <X>, <Y> & <Z> coordinates
 *
 * @author Josh Bloch
 * @author Feronzed
 * @see Map
 */
public interface ThreeDimensionalMap<X, Y, Z, V> extends Serializable
{
	/**
	 * Returns the number of coordinate-value mappings in this map. If the
	 * map contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
	 * <tt>Integer.MAX_VALUE</tt>.
	 *
	 * @return the number of coordinate-value mappings in this map
	 */
	int size();

	/**
	 * Returns <tt>true</tt> if this map contains no coordinate-value mappings.
	 *
	 * @return <tt>true</tt> if this map contains no coordinate-value mappings
	 */
	boolean isEmpty();

	/**
	 * Returns <tt>true</tt> if this map maps one or more coordinates to the
	 * specified value.  More formally, returns <tt>true</tt> if and only if
	 * this map contains at least one mapping to a value <tt>v</tt> such that
	 * <tt>(value==null ? v==null : value.equals(v))</tt>.  This operation
	 * will probably require time linear in the map size for most
	 * implementations of the <tt>Map</tt> interface.
	 *
	 * @param value
	 * 		  value whose presence in this map is to be tested
	 *
	 * @return <tt>true</tt> if this map maps one or more coordinates to the
	 * specified value
	 *
	 * @throws ClassCastException
	 * 		  if the value is of an inappropriate type for
	 * 		  this map
	 * 		  (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException
	 * 		  if the specified value is null and this
	 * 		  map does not permit null values
	 * 		  (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 */
	boolean containsValue(V value);

	/**
	 * Copies all of the mappings from the specified map to this map
	 * (optional operation).  The effect of this call is equivalent to that
	 * of calling {@link #put(X, Y, Z, Object) put(k, v)} on this map once
	 * for each mapping from coordinates <tt>x</tt>, <tt>y</tt> and <tt>z</tt>
	 * to value <tt>v</tt> in the specified map.
	 * The behavior of this operation is undefined if the
	 * specified map is modified while the operation is in progress.
	 *
	 * @param m
	 * 		  mappings to be stored in this map
	 *
	 * @throws UnsupportedOperationException
	 * 		  if the <tt>putAll</tt> operation
	 * 		  is not supported by this map
	 * @throws ClassCastException
	 * 		  if the class of coordinates or value in the
	 * 		  specified map prevents it from being stored in this map
	 * @throws NullPointerException
	 * 		  if the specified map is null, or if
	 * 		  this map does not permit null coordinates or values, and the
	 * 		  specified map contains null coordinates or values
	 * @throws IllegalArgumentException
	 * 		  if some property of a coordinate or value in
	 * 		  the specified map prevents it from being stored in this map
	 */
	void putAll(ThreeDimensionalMap<? extends X, ? extends Y, ? extends Z, ? extends V> m);

	/**
	 * Removes all of the mappings from this map (optional operation).
	 * The map will be empty after this call returns.
	 *
	 * @throws UnsupportedOperationException
	 * 		  if the <tt>clear</tt> operation
	 * 		  is not supported by this map
	 */
	void clear();

	/**
	 * Returns a {@link Set} view of the coordinates contained in this map.
	 * The set is backed by the map, so changes to the map are
	 * reflected in the set, and vice-versa.  If the map is modified
	 * while an iteration over the set is in progress (except through
	 * the iterator's own <tt>remove</tt> operation), the results of
	 * the iteration are undefined.  The set supports element removal,
	 * which removes the corresponding mapping from the map, via the
	 * <tt>Iterator.remove</tt>, <tt>Set.remove</tt>,
	 * <tt>removeAll</tt>, <tt>retainAll</tt>, and <tt>clear</tt>
	 * operations.  It does not support the <tt>add</tt> or <tt>addAll</tt>
	 * operations.
	 *
	 * @return a set view of the coordinates contained in this map
	 */
	Set<Coordinates> coordinateSet();

	/**
	 * Returns a {@link Collection} view of the values contained in this map.
	 * The collection is backed by the map, so changes to the map are
	 * reflected in the collection, and vice-versa.  If the map is
	 * modified while an iteration over the collection is in progress
	 * (except through the iterator's own <tt>remove</tt> operation),
	 * the results of the iteration are undefined.  The collection
	 * supports element removal, which removes the corresponding
	 * mapping from the map, via the <tt>Iterator.remove</tt>,
	 * <tt>Collection.remove</tt>, <tt>removeAll</tt>,
	 * <tt>retainAll</tt> and <tt>clear</tt> operations.  It does not
	 * support the <tt>add</tt> or <tt>addAll</tt> operations.
	 *
	 * @return a collection view of the values contained in this map
	 */
	Collection<V> values();

	/**
	 * Returns the value to which the specified coordinates are mapped, or
	 * {@code defaultValue} if this map contains no mapping for the coordinates.
	 *
	 * @param x
	 * 		  the x coordinate whose associated value is to be returned
	 * @param y
	 * 		  the y coordinate whose associated value is to be returned
	 * @param z
	 * 		  the z coordinate whose associated value is to be returned
	 * @param defaultValue
	 * 		  the default mapping of the coordinates
	 *
	 * @return the value to which the specified coordinates are mapped, or
	 * {@code defaultValue} if this map contains no mapping for the coordinates
	 *
	 * @throws ClassCastException
	 * 		  if the coordinates are of an inappropriate type for
	 * 		  this map
	 * 		  (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException
	 * 		  if the specified coordinates are null and this map
	 * 		  does not permit null coordinates
	 * 		  (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 */
	default V getOrDefault(X x, Y y, Z z, V defaultValue)
	{
		V v;
		return (((v = get(x, y, z)) != null) || containsCoordinates(x, y, z)) ? v : defaultValue;
	}

	/**
	 * Returns the value to which the specified coordinates are mapped,
	 * or {@code null} if this map contains no mapping for the coordinates.
	 * <p>
	 * <p>More formally, if this map contains a mapping from a coordinates
	 * {@code k} to a value {@code v} such that {@code (coordinates==null ? k==null :
	 * coordinates.equals(k))}, then this method returns {@code v}; otherwise
	 * it returns {@code null}.  (There can be at most one such mapping.)
	 * <p>
	 * <p>If this map permits null values, then a return value of
	 * {@code null} does not <i>necessarily</i> indicate that the map
	 * contains no mapping for the coordinates; it's also possible that the map
	 * explicitly maps the coordinates to {@code null}.  The {@link #containsCoordinates
	 * containsCoordinates} operation may be used to distinguish these two cases.
	 *
	 * @param x
	 * 		  the x coordinate whose associated value is to be returned
	 * @param y
	 * 		  the y coordinate whose associated value is to be returned
	 * @param z
	 * 		  the z coordinate whose associated value is to be returned
	 *
	 * @return the value to which the specified coordinates are mapped, or
	 * {@code null} if this map contains no mapping for the coordinates
	 *
	 * @throws ClassCastException
	 * 		  if the coordinates are of an inappropriate type for
	 * 		  this map
	 * 		  (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException
	 * 		  if the specified coordinates are null and this map
	 * 		  does not permit null coordinates
	 * 		  (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 */
	V get(X x, Y y, Z z);

	/**
	 * Returns <tt>true</tt> if this map contains a coordinate for the specified
	 * coordinates.  More formally, returns <tt>true</tt> if and only if
	 * this map contains a mapping for a coordinates <tt>k</tt> such that
	 * <tt>(coordinates==null ? k==null : coordinates.equals(k))</tt>.  (There can be
	 * at most one such mapping.)
	 *
	 * @param x
	 * 		  coordinate x whose presence in this map is to be tested
	 * @param y
	 * 		  coordinate y whose presence in this map is to be tested
	 * @param z
	 * 		  coordinate z whose presence in this map is to be tested
	 *
	 * @return <tt>true</tt> if this map contains a mapping for the specified
	 * coordinates
	 *
	 * @throws ClassCastException
	 * 		  if the coordinates are of an inappropriate type for
	 * 		  this map
	 * 		  (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException
	 * 		  if the specified coordinates are null and this map
	 * 		  does not permit null coordinates
	 * 		  (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 */
	boolean containsCoordinates(X x, Y y, Z z);

	/**
	 * Performs the given action for each entry in this map until all entries
	 * have been processed or the action throws an exception.   Unless
	 * otherwise specified by the implementing class, actions are performed in
	 * the order of entry set iteration (if an iteration order is specified.)
	 * Exceptions thrown by the action are relayed to the caller.
	 *
	 * @param action
	 * 		  The action to be performed for each entry
	 *
	 * @throws NullPointerException
	 * 		  if the specified action is null
	 * @throws ConcurrentModificationException
	 * 		  if an entry is found to be
	 * 		  removed during iteration
	 */
	default void forEach(BiConsumer<? super Coordinates, ? super V> action)
	{
		Objects.requireNonNull(action);
		for (ThreeDimensionalMap.Entry<X, Y, Z, V> entry : entrySet())
		{
			Coordinates coordinates;
			V v;
			try
			{
				coordinates = new Coordinates<>(entry.getX(), entry.getY(), entry.getZ());
				v = entry.getValue();
			} catch (IllegalStateException ise)
			{
				// this usually means the entry is no longer in the map.
				throw new ConcurrentModificationException(ise);
			}
			action.accept(coordinates, v);
		}
	}

	/**
	 * Returns a {@link Set} view of the mappings contained in this map.
	 * The set is backed by the map, so changes to the map are
	 * reflected in the set, and vice-versa.  If the map is modified
	 * while an iteration over the set is in progress (except through
	 * the iterator's own <tt>remove</tt> operation, or through the
	 * <tt>setValue</tt> operation on a map entry returned by the
	 * iterator) the results of the iteration are undefined.  The set
	 * supports element removal, which removes the corresponding
	 * mapping from the map, via the <tt>Iterator.remove</tt>,
	 * <tt>Set.remove</tt>, <tt>removeAll</tt>, <tt>retainAll</tt> and
	 * <tt>clear</tt> operations.  It does not support the
	 * <tt>add</tt> or <tt>addAll</tt> operations.
	 *
	 * @return a set view of the mappings contained in this map
	 */
	Set<ThreeDimensionalMap.Entry<X, Y, Z, V>> entrySet();

	/**
	 * Replaces each entry's value with the result of invoking the given
	 * function on that entry until all entries have been processed or the
	 * function throws an exception.  Exceptions thrown by the function are
	 * relayed to the caller.
	 *
	 * @param function
	 * 		  the function to apply to each entry
	 *
	 * @throws UnsupportedOperationException
	 * 		  if the {@code set} operation
	 * 		  is not supported by this map's entry set iterator.
	 * @throws ClassCastException
	 * 		  if the class of a replacement value
	 * 		  prevents it from being stored in this map
	 * @throws NullPointerException
	 * 		  if the specified function is null, or the
	 * 		  specified replacement value is null, and this map does not permit null
	 * 		  values
	 * @throws ClassCastException
	 * 		  if a replacement value is of an inappropriate
	 * 		  type for this map
	 * 		  (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException
	 * 		  if function or a replacement value is null,
	 * 		  and this map does not permit null coordinates or values
	 * 		  (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws IllegalArgumentException
	 * 		  if some property of a replacement value
	 * 		  prevents it from being stored in this map
	 * 		  (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws ConcurrentModificationException
	 * 		  if an entry is found to be
	 * 		  removed during iteration
	 */
	default void replaceAll(BiFunction<? super Coordinates, ? super V, ? extends V> function)
	{
		Objects.requireNonNull(function);
		for (ThreeDimensionalMap.Entry<X, Y, Z, V> entry : entrySet())
		{
			Coordinates coordinates;
			V v;
			try
			{
				coordinates = new Coordinates<X, Y, Z>(entry.getX(), entry.getY(), entry.getZ());
				v = entry.getValue();
			} catch (IllegalStateException ise)
			{
				// this usually means the entry is no longer in the map.
				throw new ConcurrentModificationException(ise);
			}

			// ise thrown from function is not a cme.
			v = function.apply(coordinates, v);

			try
			{
				entry.setValue(v);
			} catch (IllegalStateException ise)
			{
				// this usually means the entry is no longer in the map.
				throw new ConcurrentModificationException(ise);
			}
		}
	}

	/**
	 * If the specified coordinates are not already associated with a value (or is mapped
	 * to {@code null}) associates it with the given value and returns
	 * {@code null}, else returns the current value.
	 *
	 * @param x
	 * 		  x coordinate with which the specified value is to be associated
	 * @param y
	 * 		  y coordinate with which the specified value is to be associated
	 * @param z
	 * 		  z coordinate with which the specified value is to be associated
	 * @param value
	 * 		  value to be associated with the specified coordinates
	 *
	 * @return the previous value associated with the specified coordinates, or
	 * {@code null} if there was no mapping for the coordinates.
	 * (A {@code null} return can also indicate that the map
	 * previously associated {@code null} with the coordinates,
	 * if the implementation supports null values.)
	 *
	 * @throws UnsupportedOperationException
	 * 		  if the {@code put} operation
	 * 		  is not supported by this map
	 * 		  (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws ClassCastException
	 * 		  if the coordinates or value is of an inappropriate
	 * 		  type for this map
	 * 		  (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException
	 * 		  if the specified coordinates or value is null,
	 * 		  and this map does not permit null coordinates or values
	 * 		  (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws IllegalArgumentException
	 * 		  if some property of the specified coordinates
	 * 		  or value prevents it from being stored in this map
	 * 		  (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @since 1.8
	 */
	default V putIfAbsent(X x, Y y, Z z, V value)
	{
		V v = get(x, y, z);
		if (v == null)
		{
			v = put(x, y, z, value);
		}

		return v;
	}

	/**
	 * Associates the specified value with the specified coordinates in this map
	 * (optional operation). If the map previously contained a mapping for
	 * the coordinates, the old value is replaced by the specified value. (A map
	 * <tt>m</tt> is said to contain a mapping for coordinates <tt>k</tt>, <tt>k</tt>
	 * and <tt>k</tt> if and only if {@link #containsCoordinates(X, Y, Z) m.containsCoordinates(k)}
	 * would return <tt>true</tt>.)
	 *
	 * @param x
	 * 		  x coordinates with which the specified value is to be associated
	 * @param y
	 * 		  y coordinates with which the specified value is to be associated
	 * @param z
	 * 		  z coordinates with which the specified value is to be associated
	 * @param value
	 * 		  value to be associated with the specified coordinates
	 *
	 * @return the previous value associated with <tt>coordinates</tt>, or
	 * <tt>null</tt> if there was no mapping for <tt>coordinates</tt>.
	 * (A <tt>null</tt> return can also indicate that the map
	 * previously associated <tt>null</tt> with <tt>coordinates</tt>,
	 * if the implementation supports <tt>null</tt> values.)
	 *
	 * @throws UnsupportedOperationException
	 * 		  if the <tt>put</tt> operation
	 * 		  is not supported by this map
	 * @throws ClassCastException
	 * 		  if the class of the specified coordinates or value
	 * 		  prevents it from being stored in this map
	 * @throws NullPointerException
	 * 		  if the specified coordinates or value is null
	 * 		  and this map does not permit null coordinates or values
	 * @throws IllegalArgumentException
	 * 		  if some property of the specified coordinates
	 * 		  or value prevents it from being stored in this map
	 */
	V put(X x, Y y, Z z, V value);

	/**
	 * Returns the hash code value for this map. The hash code of a map is
	 * defined to be the sum of the hash codes of each entry in the map's
	 * <tt>entrySet()</tt> view.  This ensures that <tt>m1.equals(m2)</tt>
	 * implies that <tt>m1.hashCode()==m2.hashCode()</tt> for any two maps
	 * <tt>m1</tt> and <tt>m2</tt>, as required by the general contract of
	 * {@link Object#hashCode}.
	 *
	 * @return the hash code value for this map
	 *
	 * @see ThreeDimensionalMap.Entry#hashCode()
	 * @see Object#equals(Object)
	 * @see #equals(Object)
	 */
	int hashCode();

	/**
	 * Removes the entry for the specified coordinates only if it is currently
	 * mapped to the specified value.
	 *
	 * @param x
	 * 		  x coordinate with which the specified value is associated
	 * @param y
	 * 		  y coordinate with which the specified value is associated
	 * @param z
	 * 		  z coordinate with which the specified value is associated
	 * @param value
	 * 		  value expected to be associated with the specified coordinates
	 *
	 * @return {@code true} if the value was removed
	 *
	 * @throws UnsupportedOperationException
	 * 		  if the {@code remove} operation
	 * 		  is not supported by this map
	 * 		  (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws ClassCastException
	 * 		  if the coordinates or value is of an inappropriate
	 * 		  type for this map
	 * 		  (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException
	 * 		  if the specified coordinates or value is null,
	 * 		  and this map does not permit null coordinates or values
	 * 		  (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 */
	default boolean remove(X x, Y y, Z z, Object value)
	{
		V curValue = get(x, y, z);
		if (!Objects.equals(curValue, value) || (curValue == null && !containsCoordinates(x, y, z)))
		{
			return false;
		}
		remove(x, y, z);
		return true;
	}

	/**
	 * Removes the mapping for a coordinates from this map if it is present
	 * (optional operation). More formally, if this map contains a mapping
	 * from coordinates <tt>x</tt>, <tt>y</tt> and <tt>z</tt> to value <tt>v</tt> such that
	 * <code>(coordinates==null ?  k==null : coordinates.equals(k))</code>, that mapping
	 * is removed. (The map can contain at most one such mapping.)
	 * <p>
	 * <p>Returns the value to which this map previously associated the coordinates,
	 * or <tt>null</tt> if the map contained no mapping for the coordinates.
	 * <p>
	 * <p>If this map permits null values, then a return value of
	 * <tt>null</tt> does not <i>necessarily</i> indicate that the map
	 * contained no mapping for the coordinates; it's also possible that the map
	 * explicitly mapped the coordinates to <tt>null</tt>.
	 * <p>
	 * <p>The map will not contain a mapping for the specified coordinates once the
	 * call returns.
	 *
	 * @param x
	 * 		  x coordinate whose mapping is to be removed from the map
	 * @param y
	 * 		  y coordinate whose mapping is to be removed from the map
	 * @param z
	 * 		  z coordinate whose mapping is to be removed from the map
	 *
	 * @return the previous value associated with <tt>x</tt>, <tt>y</tt> and <tt>z</tt> or
	 * <tt>null</tt> if there was no mapping for <tt>x</tt>, <tt>y</tt> and <tt>z</tt>.
	 *
	 * @throws UnsupportedOperationException
	 * 		  if the <tt>remove</tt> operation
	 * 		  is not supported by this map
	 * @throws ClassCastException
	 * 		  if the coordinates are of an inappropriate type for
	 * 		  this map
	 * 		  (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException
	 * 		  if the specified coordinates are null and this
	 * 		  map does not permit null coordinates
	 * 		  (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 */
	V remove(X x, Y y, Z z);

	/**
	 * Replaces the entry for the specified coordinates only if currently
	 * mapped to the specified value.
	 *
	 * @param x
	 * 		  x coordinate with which the specified value is associated
	 * @param y
	 * 		  y coordinate with which the specified value is associated
	 * @param z
	 * 		  z coordinate with which the specified value is associated
	 * @param oldValue
	 * 		  value expected to be associated with the specified coordinates
	 * @param newValue
	 * 		  value to be associated with the specified coordinates
	 *
	 * @return {@code true} if the value was replaced
	 *
	 * @throws UnsupportedOperationException
	 * 		  if the {@code put} operation
	 * 		  is not supported by this map
	 * 		  (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws ClassCastException
	 * 		  if the class of a specified coordinates or value
	 * 		  prevents it from being stored in this map
	 * @throws NullPointerException
	 * 		  if specified coordinates or newValue is null,
	 * 		  and this map does not permit null coordinates or values
	 * @throws NullPointerException
	 * 		  if oldValue is null and this map does not
	 * 		  permit null values
	 * 		  (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws IllegalArgumentException
	 * 		  if some property of specified coordinates
	 * 		  or value prevents it from being stored in this map
	 */
	default boolean replace(X x, Y y, Z z, V oldValue, V newValue)
	{
		V curValue = get(x, y, z);
		if (!Objects.equals(curValue, oldValue) || (curValue == null && !containsCoordinates(x, y, z)))
		{
			return false;
		}
		put(x, y, z, newValue);
		return true;
	}

	/**
	 * Replaces the entry for the specified coordinates only if it is
	 * currently mapped to some value.
	 *
	 * @param x
	 * 		  x coordinate with which the specified value is associated
	 * @param y
	 * 		  y coordinate with which the specified value is associated
	 * @param z
	 * 		  z coordinate with which the specified value is associated
	 * @param value
	 * 		  value to be associated with the specified coordinates
	 *
	 * @return the previous value associated with the specified coordinates, or
	 * {@code null} if there was no mapping for the coordinates.
	 * (A {@code null} return can also indicate that the map
	 * previously associated {@code null} with the coordinates,
	 * if the implementation supports null values.)
	 *
	 * @throws UnsupportedOperationException
	 * 		  if the {@code put} operation
	 * 		  is not supported by this map
	 * 		  (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws ClassCastException
	 * 		  if the class of the specified coordinates or value
	 * 		  prevents it from being stored in this map
	 * 		  (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException
	 * 		  if the specified coordinates or value is null,
	 * 		  and this map does not permit null coordinates or values
	 * @throws IllegalArgumentException
	 * 		  if some property of the specified coordinates
	 * 		  or value prevents it from being stored in this map
	 */
	default V replace(X x, Y y, Z z, V value)
	{
		V curValue;
		if (((curValue = get(x, y, z)) != null) || containsCoordinates(x, y, z))
		{
			curValue = put(x, y, z, value);
		}
		return curValue;
	}

	/**
	 * If the specified coordinates is not already associated with a value (or is mapped
	 * to {@code null}), attempts to compute its value using the given mapping
	 * function and enters it into this map unless {@code null}.
	 * <p>
	 * <p>If the function returns {@code null} no mapping is recorded. If
	 * the function itself throws an (unchecked) exception, the
	 * exception is rethrown, and no mapping is recorded.  The most
	 * common usage is to construct a new object serving as an initial
	 * mapped value or memoized result, as in:
	 * <p>
	 * <pre> {@code
	 * map.computeIfAbsent(coordinates, x, y, z -> new Value(f(x,y,z)));
	 * }</pre>
	 * <p>
	 * <p>Or to implement a multi-value map, {@code Map<K,Collection<V>>},
	 * supporting multiple values per coordinates:
	 * <p>
	 * <pre> {@code
	 * map.computeIfAbsent(coordinates, k -> new HashSet<V>()).add(v);
	 * }</pre>
	 *
	 * @param x
	 * 		  x coordinate with which the specified value is to be associated
	 * @param y
	 * 		  y coordinate with which the specified value is to be associated
	 * @param z
	 * 		  z coordinate with which the specified value is to be associated
	 * @param mappingFunction
	 * 		  the function to compute a value
	 *
	 * @return the current (existing or computed) value associated with
	 * the specified coordinates, or null if the computed value is null
	 *
	 * @throws NullPointerException
	 * 		  if the specified coordinates are null and
	 * 		  this map does not support null coordinates, or the mappingFunction
	 * 		  is null
	 * @throws UnsupportedOperationException
	 * 		  if the {@code put} operation
	 * 		  is not supported by this map
	 * 		  (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws ClassCastException
	 * 		  if the class of the specified coordinates or value
	 * 		  prevents it from being stored in this map
	 * 		  (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 */
	default V computeIfAbsent(X x, Y y, Z z, Function<? super Coordinates, ? extends V> mappingFunction)
	{
		Objects.requireNonNull(mappingFunction);
		V v;
		if ((v = get(x, y, z)) == null)
		{
			V newValue;
			if ((newValue = mappingFunction.apply(new Coordinates(x, y, z))) != null)
			{
				put(x, y, z, newValue);
				return newValue;
			}
		}

		return v;
	}

	/**
	 * If the value for the specified coordinates are present and non-null, attempts to
	 * compute a new mapping given the coordinates and its current mapped value.
	 * <p>
	 * <p>If the function returns {@code null}, the mapping is removed.  If the
	 * function itself throws an (unchecked) exception, the exception is
	 * rethrown, and the current mapping is left unchanged.
	 *
	 * @param x
	 * 		  x coordinate with which the specified value is to be associated
	 * @param y
	 * 		  y coordinate with which the specified value is to be associated
	 * @param z
	 * 		  z coordinate with which the specified value is to be associated
	 * @param remappingFunction
	 * 		  the function to compute a value
	 *
	 * @return the new value associated with the specified coordinates, or null if none
	 *
	 * @throws NullPointerException
	 * 		  if the specified coordinates are null and
	 * 		  this map does not support null coordinates, or the
	 * 		  remappingFunction is null
	 * @throws UnsupportedOperationException
	 * 		  if the {@code put} operation
	 * 		  is not supported by this map
	 * 		  (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws ClassCastException
	 * 		  if the class of the specified coordinates or value
	 * 		  prevents it from being stored in this map
	 * 		  (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 */
	default V computeIfPresent(X x, Y y, Z z, BiFunction<? super Coordinates, ? super V, ? extends V> remappingFunction)
	{
		Objects.requireNonNull(remappingFunction);
		V oldValue;
		if ((oldValue = get(x, y, z)) != null)
		{
			V newValue = remappingFunction.apply(new Coordinates(x, y, z), oldValue);
			if (newValue != null)
			{
				put(x, y, z, newValue);
				return newValue;
			}
			else
			{
				remove(x, y, z);
				return null;
			}
		}
		else
		{
			return null;
		}
	}

	/**
	 * Attempts to compute a mapping for the specified coordinates and its current
	 * mapped value (or {@code null} if there is no current mapping). For
	 * example, to either create or append a {@code String} msg to a value
	 * mapping:
	 * <p>
	 * <pre> {@code
	 * map.compute(coordinates, (coordinates, v) -> (v == null) ? msg : v.concat(msg))}</pre>
	 * (Method {@link #merge merge()} is often simpler to use for such purposes.)
	 * <p>
	 * <p>If the function returns {@code null}, the mapping is removed (or
	 * remains absent if initially absent).  If the function itself throws an
	 * (unchecked) exception, the exception is rethrown, and the current mapping
	 * is left unchanged.
	 *
	 * @param x
	 * 		  x coordinates with which the specified value is to be associated
	 * @param y
	 * 		  y coordinates with which the specified value is to be associated
	 * @param z
	 * 		  z coordinates with which the specified value is to be associated
	 * @param remappingFunction
	 * 		  the function to compute a value
	 *
	 * @return the new value associated with the specified coordinates, or null if none
	 *
	 * @throws NullPointerException
	 * 		  if the specified coordinates are null and
	 * 		  this map does not support null coordinates, or the
	 * 		  remappingFunction is null
	 * @throws UnsupportedOperationException
	 * 		  if the {@code put} operation
	 * 		  is not supported by this map
	 * 		  (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws ClassCastException
	 * 		  if the class of the specified coordinates or value
	 * 		  prevents it from being stored in this map
	 * 		  (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 */
	default V compute(X x, Y y, Z z, BiFunction<? super Coordinates, ? super V, ? extends V> remappingFunction)
	{
		Objects.requireNonNull(remappingFunction);
		V oldValue = get(x, y, z);

		V newValue = remappingFunction.apply(new Coordinates(x, y, z), oldValue);
		if (newValue == null)
		{
			// delete mapping
			if (oldValue != null || containsCoordinates(x, y, z))
			{
				// something to remove
				remove(x, y, z);
				return null;
			}
			else
			{
				// nothing to do. Leave things as they were.
				return null;
			}
		}
		else
		{
			// add or replace old mapping
			put(x, y, z, newValue);
			return newValue;
		}
	}

	/**
	 * If the specified coordinates are not already associated with a value or are
	 * associated with null, associates them with the given non-null value.
	 * Otherwise, replaces the associated value with the results of the given
	 * remapping function, or removes if the result is {@code null}. This
	 * method may be of use when combining multiple mapped values for coordinates.
	 * For example, to either create or append a {@code String msg} to a
	 * value mapping:
	 * <p>
	 * <pre> {@code
	 * map.merge(coordinates, msg, String::concat)
	 * }</pre>
	 * <p>
	 * <p>If the function returns {@code null} the mapping is removed.  If the
	 * function itself throws an (unchecked) exception, the exception is
	 * rethrown, and the current mapping is left unchanged.
	 *
	 * @param x
	 * 		  x coordinate with which the resulting value is to be associated
	 * @param y
	 * 		  y coordinate with which the resulting value is to be associated
	 * @param z
	 * 		  z coordinate with which the resulting value is to be associated
	 * @param value
	 * 		  the non-null value to be merged with the existing value
	 * 		  associated with the coordinates or, if no existing value or a null value
	 * 		  is associated with the coordinates, to be associated with the coordinates
	 * @param remappingFunction
	 * 		  the function to recompute a value if present
	 *
	 * @return the new value associated with the specified coordinates, or null if no
	 * value is associated with the coordinates
	 *
	 * @throws UnsupportedOperationException
	 * 		  if the {@code put} operation
	 * 		  is not supported by this map
	 * 		  (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws ClassCastException
	 * 		  if the class of the specified coordinates or value
	 * 		  prevents it from being stored in this map
	 * 		  (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException
	 * 		  if the specified coordinates are null and this map
	 * 		  does not support null coordinates or the value or remappingFunction is
	 * 		  null
	 */
	default V merge(X x, Y y, Z z, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction)
	{
		Objects.requireNonNull(remappingFunction);
		Objects.requireNonNull(value);
		V oldValue = get(x, y, z);
		V newValue = (oldValue == null) ? value : remappingFunction.apply(oldValue, value);
		if (newValue == null)
		{
			remove(x, y, z);
		}
		else
		{
			put(x, y, z, newValue);
		}
		return newValue;
	}

	/**
	 * A map entry (coordinate-value pair).  The <tt>Map.entrySet</tt> method returns
	 * a collection-view of the map, whose elements are of this class.  The
	 * <i>only</i> way to obtain a reference to a map entry is from the
	 * iterator of this collection-view.  These <tt>WorldMap.Entry</tt> objects are
	 * valid <i>only</i> for the duration of the iteration; more formally,
	 * the behavior of a map entry is undefined if the backing map has been
	 * modified after the entry was returned by the iterator, except through
	 * the <tt>setValue</tt> operation on the map entry.
	 *
	 * @see Map#entrySet()
	 */
	interface Entry<X, Y, Z, V>
	{
		/**
		 * Returns a comparator that compares {@link ThreeDimensionalMap.Entry} in natural order on coordinates.
		 * <p>
		 * <p>The returned comparator is serializable and throws {@link
		 * NullPointerException} when comparing an entry with null coordinates.
		 *
		 * @param <X>
		 * 		  the {@link Comparable} type of the map x coordinate
		 * @param <Y>
		 * 		  the {@link Comparable} type of the map y coordinate
		 * @param <Z>
		 * 		  the {@link Comparable} type of the map z coordinate
		 * @param <V>
		 * 		  the type of the map values
		 *
		 * @return a comparator that compares {@link ThreeDimensionalMap.Entry} in natural order on coordinates.
		 *
		 * @see Comparable
		 */
		static <X extends Comparable<? super X>, Y extends Comparable<? super Y>, Z extends Comparable<? super Z>, V> Comparator<ThreeDimensionalMap.Entry<X, Y, Z, V>> comparingByKey()
		{
			return (Comparator<ThreeDimensionalMap.Entry<X, Y, Z, V>> & Serializable) (c1, c2)->new Coordinates<>(c1.getX(),c1.getY(),c1.getZ()).compareTo(new Coordinates<>(c2.getX(),c2.getY(),c2.getZ()));
		}

		/**
		 * Returns the x coordinate corresponding to this entry.
		 *
		 * @return the x coordinate corresponding to this entry
		 *
		 * @throws IllegalStateException
		 * 		  implementations may, but are not
		 * 		  required to, throw this exception if the entry has been
		 * 		  removed from the backing map.
		 */
		X getX();

		/**
		 * Returns the y coordinate corresponding to this entry.
		 *
		 * @return the y coordinate corresponding to this entry
		 *
		 * @throws IllegalStateException
		 * 		  implementations may, but are not
		 * 		  required to, throw this exception if the entry has been
		 * 		  removed from the backing map.
		 */
		Y getY();

		/**
		 * Returns the z coordinate corresponding to this entry.
		 *
		 * @return the z coordinate corresponding to this entry
		 *
		 * @throws IllegalStateException
		 * 		  implementations may, but are not
		 * 		  required to, throw this exception if the entry has been
		 * 		  removed from the backing map.
		 */
		Z getZ();

		/**
		 * Returns a comparator that compares {@link ThreeDimensionalMap.Entry} in natural order on value.
		 * <p>
		 * <p>The returned comparator is serializable and throws {@link
		 * NullPointerException} when comparing an entry with null values.
		 *
		 * @param <X>
		 * 		  the type of the map x coordinates
		 * @param <Y>
		 * 		  the type of the map y coordinates
		 * @param <Z>
		 * 		  the type of the map z coordinates
		 * @param <V>
		 * 		  the {@link Comparable} type of the map values
		 *
		 * @return a comparator that compares {@link ThreeDimensionalMap.Entry} in natural order on value.
		 *
		 * @see Comparable
		 */
		static <X, Y, Z, V extends Comparable<? super V>> Comparator<ThreeDimensionalMap.Entry<X, Y, Z, V>> comparingByValue()
		{
			return (Comparator<ThreeDimensionalMap.Entry<X, Y, Z, V>> & Serializable) (c1, c2)->c1.getValue().compareTo(c2.getValue());
		}

		/**
		 * Returns the value corresponding to this entry.  If the mapping
		 * has been removed from the backing map (by the iterator's
		 * <tt>remove</tt> operation), the results of this call are undefined.
		 *
		 * @return the value corresponding to this entry
		 *
		 * @throws IllegalStateException
		 * 		  implementations may, but are not
		 * 		  required to, throw this exception if the entry has been
		 * 		  removed from the backing map.
		 */
		V getValue();

		/**
		 * Returns a comparator that compares {@link ThreeDimensionalMap.Entry} by coordinates using the given
		 * {@link Comparator}.
		 * <p>
		 * <p>The returned comparator is serializable if the specified comparator
		 * is also serializable.
		 *
		 * @param <X>
		 * 		  the type of the map x coordinates
		 * @param <Y>
		 * 		  the type of the map y coordinates
		 * @param <Z>
		 * 		  the type of the map z coordinates
		 * @param <V>
		 * 		  the type of the map values
		 * @param cmp
		 * 		  the coordinate {@link Comparator}
		 *
		 * @return a comparator that compares {@link ThreeDimensionalMap.Entry} by the coordinates.
		 */
		static <X, Y, Z, V> Comparator<ThreeDimensionalMap.Entry<X, Y, Z, V>> comparingByKey(Comparator<? super Coordinates> cmp)
		{
			Objects.requireNonNull(cmp);
			return (Comparator<ThreeDimensionalMap.Entry<X,Y,Z, V>> & Serializable) (c1, c2)->cmp.compare(new Coordinates(c1.getX(),c1.getY(),c1.getZ()), new Coordinates(c2.getX(),c2.getY(),c2.getZ()));
		}

		/**
		 * Returns a comparator that compares {@link ThreeDimensionalMap.Entry} by value using the given
		 * {@link Comparator}.
		 * <p>
		 * <p>The returned comparator is serializable if the specified comparator
		 * is also serializable.
		 *
		 * @param <X> the type of the map x coordinates
		 * @param <Y> the type of the map y coordinates
		 * @param <Z> the type of the map z coordinates
		 * @param <V>
		 * 		  the type of the map values
		 * @param cmp
		 * 		  the value {@link Comparator}
		 *
		 * @return a comparator that compares {@link ThreeDimensionalMap.Entry} by the value.
		 */
		static <X,Y,Z, V> Comparator<ThreeDimensionalMap.Entry<X,Y,Z, V>> comparingByValue(Comparator<? super V> cmp)
		{
			Objects.requireNonNull(cmp);
			return (Comparator<ThreeDimensionalMap.Entry<X,Y,Z, V>> & Serializable) (c1, c2)->cmp.compare(c1.getValue(), c2.getValue());
		}

		/**
		 * Replaces the value corresponding to this entry with the specified
		 * value (optional operation).  (Writes through to the map.)  The
		 * behavior of this call is undefined if the mapping has already been
		 * removed from the map (by the iterator's <tt>remove</tt> operation).
		 *
		 * @param value
		 * 		  new value to be stored in this entry
		 *
		 * @return old value corresponding to the entry
		 *
		 * @throws UnsupportedOperationException
		 * 		  if the <tt>put</tt> operation
		 * 		  is not supported by the backing map
		 * @throws ClassCastException
		 * 		  if the class of the specified value
		 * 		  prevents it from being stored in the backing map
		 * @throws NullPointerException
		 * 		  if the backing map does not permit
		 * 		  null values, and the specified value is null
		 * @throws IllegalArgumentException
		 * 		  if some property of this value
		 * 		  prevents it from being stored in the backing map
		 * @throws IllegalStateException
		 * 		  implementations may, but are not
		 * 		  required to, throw this exception if the entry has been
		 * 		  removed from the backing map.
		 */
		V setValue(V value);

		/**
		 * Compares the specified object with this entry for equality.
		 * Returns <tt>true</tt> if the given object is also a map entry and
		 * the two entries represent the same mapping.  More formally, two
		 * entries <tt>e1</tt> and <tt>e2</tt> represent the same mapping
		 * if<pre>
		 *     (e1.getKey()==null ?
		 *      e2.getKey()==null : e1.getKey().equals(e2.getKey()))  &amp;&amp;
		 *     (e1.getValue()==null ?
		 *      e2.getValue()==null : e1.getValue().equals(e2.getValue()))
		 * </pre>
		 * This ensures that the <tt>equals</tt> method works properly across
		 * different implementations of the <tt>WorldMap.Entry</tt> interface.
		 *
		 * @param o
		 * 		  object to be compared for equality with this map entry
		 *
		 * @return <tt>true</tt> if the specified object is equal to this map
		 * entry
		 */
		boolean equals(Object o);

		/**
		 * Returns the hash code value for this map entry.  The hash code
		 * of a map entry <tt>e</tt> is defined to be: <pre>
		 *     (e.getKey()==null   ? 0 : e.getKey().hashCode()) ^
		 *     (e.getValue()==null ? 0 : e.getValue().hashCode())
		 * </pre>
		 * This ensures that <tt>e1.equals(e2)</tt> implies that
		 * <tt>e1.hashCode()==e2.hashCode()</tt> for any two Entries
		 * <tt>e1</tt> and <tt>e2</tt>, as required by the general
		 * contract of <tt>Object.hashCode</tt>.
		 *
		 * @return the hash code value for this map entry
		 *
		 * @see Object#hashCode()
		 * @see Object#equals(Object)
		 * @see #equals(Object)
		 */
		int hashCode();
	}

	/**
	 * Compares the specified object with this map for equality.  Returns
	 * <tt>true</tt> if the given object is also a map and the two maps
	 * represent the same mappings.  More formally, two maps <tt>m1</tt> and
	 * <tt>m2</tt> represent the same mappings if
	 * <tt>m1.entrySet().equals(m2.entrySet())</tt>.  This ensures that the
	 * <tt>equals</tt> method works properly across different implementations
	 * of the <tt>Map</tt> interface.
	 *
	 * @param o
	 * 		  object to be compared for equality with this map
	 *
	 * @return <tt>true</tt> if the specified object is equal to this map
	 */
	boolean equals(Object o);
}