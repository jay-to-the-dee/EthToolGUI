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
 */
public abstract class AbstractLinkModes extends MultiEnumProperty
{
    public enum LinkModesEnum
    {
        ADVERTISED_10baseT_Half, ADVERTISED_10baseT_Full, ADVERTISED_100baseT_Half, ADVERTISED_100baseT_Full, ADVERTISED_1000baseT_Half, ADVERTISED_1000baseT_Full, ADVERTISED_1000baseKX_Full, ADVERTISED_2500baseX_Full, ADVERTISED_10000baseT_Full, ADVERTISED_10000baseKX4_Full, ADVERTISED_10000baseKR_Full, ADVERTISED_20000baseMLD2_Full, ADVERTISED_20000baseKR2_Full, ADVERTISED_40000baseKR4_Full, ADVERTISED_40000baseCR4_Full, ADVERTISED_40000baseSR4_Full, ADVERTISED_40000baseLR4_Full
    }

    public AbstractLinkModes()
    {
        super("Port", true);
        enumToStrings = new EnumMap(LinkModesEnum.class);
        //Taken from line 521 onwards in ethtool.c
        enumToStrings.put(LinkModesEnum.ADVERTISED_10baseT_Half, "10baseT/Half");
        enumToStrings.put(LinkModesEnum.ADVERTISED_10baseT_Full, "10baseT/Full");
        enumToStrings.put(LinkModesEnum.ADVERTISED_100baseT_Half, "100baseT/Half");
        enumToStrings.put(LinkModesEnum.ADVERTISED_100baseT_Full, "100baseT/Full");
        enumToStrings.put(LinkModesEnum.ADVERTISED_1000baseT_Half, "1000baseT/Half");
        enumToStrings.put(LinkModesEnum.ADVERTISED_1000baseT_Full, "1000baseT/Full");
        enumToStrings.put(LinkModesEnum.ADVERTISED_1000baseKX_Full, "1000baseKX/Full");
        enumToStrings.put(LinkModesEnum.ADVERTISED_2500baseX_Full, "2500baseX/Full");
        enumToStrings.put(LinkModesEnum.ADVERTISED_10000baseT_Full, "10000baseT/Full");
        enumToStrings.put(LinkModesEnum.ADVERTISED_10000baseKX4_Full, "10000baseKX4/Full");
        enumToStrings.put(LinkModesEnum.ADVERTISED_10000baseKR_Full, "10000baseKR/Full");
        enumToStrings.put(LinkModesEnum.ADVERTISED_20000baseMLD2_Full, "20000baseMLD2/Full");
        enumToStrings.put(LinkModesEnum.ADVERTISED_20000baseKR2_Full, "20000baseKR2/Full");
        enumToStrings.put(LinkModesEnum.ADVERTISED_40000baseKR4_Full, "40000baseKR4/Full");
        enumToStrings.put(LinkModesEnum.ADVERTISED_40000baseCR4_Full, "40000baseCR4/Full");
        enumToStrings.put(LinkModesEnum.ADVERTISED_40000baseSR4_Full, "40000baseSR4/Full");
        enumToStrings.put(LinkModesEnum.ADVERTISED_40000baseLR4_Full, "40000baseLR4/Full");
    }
}
