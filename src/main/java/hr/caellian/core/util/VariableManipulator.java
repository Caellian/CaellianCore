/*******************************************************************************
 * Copyright (c) 2015 Contributors.
 * All rights reserved. This program and the accompanying materials are made available under
 * the terms of the GNU Lesser General Public
 * License v3.0 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 ******************************************************************************/

package hr.caellian.core.util;

import java.util.List;
import java.util.Random;

/**
 * @author Caellian
 */
public class VariableManipulator {
    public static Character[] from(char[] chars) {
        Character[] result = new Character[chars.length];
        for (int count = 0; count < chars.length; count++) {
            result[count] = chars[count];
        }
        return result;
    }

    public static Object randomOf(List list) {
        Object[] newList = list.toArray();
        return newList[new Random().nextInt(newList.length)];
    }
}
