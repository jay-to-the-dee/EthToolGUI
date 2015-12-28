/*
 * Copyright (C) 2015 jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
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
package model.property;

import java.util.*;
import java.util.Map.Entry;

/**
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public abstract class SingleEnumProperty extends Property<Enum>
{
    EnumMap enumToStrings;

    public SingleEnumProperty(String propertyName, boolean readOnly)
    {
        super(propertyName, readOnly);
    }

    @Override
    public String toString()
    {
        return (String) enumToStrings.get(value);
    }

    public Collection getAllPossibleValues()
    {
        return enumToStrings.values();
    }

    public Enum convertStringToEnumValue(String value)
    {
        Iterator iterator = enumToStrings.entrySet().iterator();
        while (iterator.hasNext())
        {
            Entry property = (Entry) iterator.next();
            String entryValue = (String) property.getValue();
            if (value.equals(entryValue))
            {
                return (Enum) property.getKey();
            }
        }
        return null;
    }
}
