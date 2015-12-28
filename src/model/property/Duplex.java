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

import java.util.EnumMap;
import model.property.Duplex.DuplexEnum;

/**
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public class Duplex extends SingleEnumProperty<DuplexEnum>
{
    public enum DuplexEnum
    {
        //Half, Full, Unknown! (%i)
        HALF, FULL, UNKNOWN
    }

    public Duplex(String value)
    {
        super("Duplex", true);
        enumToStrings = new EnumMap(DuplexEnum.class);
        enumToStrings.put(DuplexEnum.HALF, "Half");
        enumToStrings.put(DuplexEnum.FULL, "Full");
        enumToStrings.put(DuplexEnum.UNKNOWN, "Unknown!");
        this.value = convertStringToEnumValue(value);
    }
}
