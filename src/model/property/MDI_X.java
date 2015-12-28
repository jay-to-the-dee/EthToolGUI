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
import model.property.MDI_X.MDI_XEnum;

/**
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public class MDI_X extends SingleEnumProperty<MDI_XEnum>
{
    public enum MDI_XEnum
    {
        //off, on, Unknown ? (auto)
        OFF, ON, UNKNOWN, OFF_AUTO, ON_AUTO, UNKNOWN_AUTO
    }

    public MDI_X(String value)
    {
        super("MDI-X", true);
        enumToStrings = new EnumMap(MDI_XEnum.class);
        enumToStrings.put(MDI_XEnum.OFF, "off");
        enumToStrings.put(MDI_XEnum.ON, "on");
        enumToStrings.put(MDI_XEnum.UNKNOWN, "Unknown");
        enumToStrings.put(MDI_XEnum.OFF_AUTO, "off (auto)");
        enumToStrings.put(MDI_XEnum.ON_AUTO, "on (auto)");
        enumToStrings.put(MDI_XEnum.UNKNOWN_AUTO, "Unknown (auto)");
        this.value = convertStringToEnumValue(value);
    }
}
