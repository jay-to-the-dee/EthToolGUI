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
import model.property.AbstractLinkModes.LinkModesEnum;

/**
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public abstract class MultiEnumProperty extends Property
{
    EnumMap enumToStrings;

    public MultiEnumProperty(String propertyName, boolean readOnly)
    {
        super(propertyName, readOnly);
    }

    @Override
    public EnumSet getValue()
    {
        return (EnumSet) value;
    }

    @Override
    public String toString()
    {
        String string = "";
        for (Iterator it = ((EnumSet) value).iterator(); it.hasNext();)
        {
            Object item = it.next();
            string += enumToStrings.get(item);
            if (it.hasNext())
            {
                string += ", ";
            }
        }
        return string;
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
            Map.Entry property = (Map.Entry) iterator.next();
            String entryValue = (String) property.getValue();
            if (value.equals(entryValue))
            {
                return (Enum) property.getKey();
            }
        }
        return null;
    }

    public EnumSet convertStringSetToEnumValues(Set<String> values)
    {
        EnumSet enumSet = EnumSet.noneOf(LinkModesEnum.class); //TODO: Make generic

        for (String item : values)
        {
            enumSet.add(convertStringToEnumValue(item));
        }
        return enumSet;
    }
}
