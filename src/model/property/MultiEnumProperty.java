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

/**
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 * @param <TypeOfEnum> The Enum for this Property e.g. SpeedEnum, MDI_XEnum etc.
 */
public abstract class MultiEnumProperty<TypeOfEnum extends Enum<TypeOfEnum>> extends Property<EnumSet<TypeOfEnum>>
{
    EnumMap<TypeOfEnum, String> enumToStrings;
    Class<TypeOfEnum> clazz;

    public MultiEnumProperty(String propertyName, boolean readOnly, Class<TypeOfEnum> clazz)
    {
        super(propertyName, readOnly);
        this.clazz = clazz;
    }

//Test code - currently unmaintained
//    @Override
//    public String toString()
//    {
//        String string = "";
//        for (Iterator it = value.iterator(); it.hasNext();)
//        {
//            Enum<TypeOfEnum> item = (Enum<TypeOfEnum>) it.next();
//            string += enumToStrings.get(item);
//            if (it.hasNext())
//            {
//                string += ", ";
//            }
//        }
//        return string;
//    }
    public TypeOfEnum convertStringToEnumValue(String value)
    {
        for (Map.Entry<TypeOfEnum, String> property : enumToStrings.entrySet())
        {
            String entryValue = property.getValue();
            if (value.equals(entryValue))
            {
                return property.getKey();
            }
        }
        return null;
    }

    public String convertEnumValueToString(TypeOfEnum localValue)
    {
        return enumToStrings.get(localValue);
    }

    public EnumSet<TypeOfEnum> convertStringSetToEnumValues(Set<String> values)
    {
        EnumSet<TypeOfEnum> enumSet = EnumSet.<TypeOfEnum>noneOf(clazz);
        for (String item : values)
        {
            try
            {
                enumSet.add(convertStringToEnumValue(item));
            }
            catch (Exception e)
            {
                System.err.println(e.getMessage());
                System.err.println("Could not lookup '" + item + "'!");
            }
        }
        return enumSet;
    }
}
