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
import model.property.Port.PortEnum;

/**
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public class Port extends SingleEnumProperty<PortEnum>
{
    public enum PortEnum
    {
        //Twisted Pair, AUI, BNC, MII, FIBRE, Direct Attach Copper, None, Other, Unknown! (%i)
        TWISTED_PAIR, AUI, BNC, MII, FIBRE, DIRECT_ATTACH_COPPER, NONE, OTHER, UNKNOWN
    }

    public Port(String value)
    {
        super("Port", true);
        enumToStrings = new EnumMap(PortEnum.class);
        enumToStrings.put(PortEnum.TWISTED_PAIR, "Twisted Pair");
        enumToStrings.put(PortEnum.AUI, "AUI");
        enumToStrings.put(PortEnum.BNC, "BNC");
        enumToStrings.put(PortEnum.MII, "MII");
        enumToStrings.put(PortEnum.FIBRE, "FIBRE");
        enumToStrings.put(PortEnum.DIRECT_ATTACH_COPPER, "Direct Attach Copper");
        enumToStrings.put(PortEnum.NONE, "None");
        enumToStrings.put(PortEnum.OTHER, "Other");
        enumToStrings.put(PortEnum.UNKNOWN, "Unknown!");
        this.value = convertStringToEnumValue(value);
    }
}
