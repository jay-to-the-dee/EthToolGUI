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
 * @param <TypeOfEnum> The Enum for this Property e.g. SpeedEnum, MDI_XEnum etc.
 */
public abstract class SingleEnumProperty<TypeOfEnum extends Enum<TypeOfEnum>> extends Property<TypeOfEnum>
{
    EnumMap<TypeOfEnum, String> enumToStrings;

    public SingleEnumProperty(String propertyName, boolean readOnly)
    {
        super(propertyName, readOnly);
    }

    @Override
    public String toString()
    {
        return enumToStrings.get(value);
    }

    public Collection<String> getAllPossibleValues()
    {
        return enumToStrings.values();
    }

    public TypeOfEnum convertStringToEnumValue(String value)
    {
        for (Entry<TypeOfEnum, String> property : enumToStrings.entrySet())
        {
            String entryValue = property.getValue();
            if (value.equals(entryValue))
            {
                return property.getKey();
            }
        }
        return null;
    }
}
