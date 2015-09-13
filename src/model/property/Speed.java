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

/**
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public class Speed extends SingleEnumProperty
{
    public enum SpeedEnum
    {
        SPEED_10, SPEED_100, SPEED_1000, SPEED_2500, SPEED_10000, SPEED_UNKNOWN
    }

    public Speed(String value)
    {
        super("Speed", true);
        enumToStrings = new EnumMap(SpeedEnum.class);
        enumToStrings.put(SpeedEnum.SPEED_10, "10Mb/s");
        enumToStrings.put(SpeedEnum.SPEED_100, "100Mb/s");
        enumToStrings.put(SpeedEnum.SPEED_1000, "1000Mb/s");
        enumToStrings.put(SpeedEnum.SPEED_2500, "2500Mb/s");
        enumToStrings.put(SpeedEnum.SPEED_10000, "10000Mb/s");
        enumToStrings.put(SpeedEnum.SPEED_UNKNOWN, "Unknown!");
        this.value = convertStringToEnumValue(value);
    }
}
