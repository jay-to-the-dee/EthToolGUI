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
public class SupportedPauseFrameUse extends SingleEnumProperty
{
    public enum SupportedPauseFrameUseEnum
    {
        //Can be Symmetric, Receive-only, Transmit-only or No
        SYMMETRIC, RECEIVE_ONLY, TRANSMIT_ONLY, NO
    }
    
    public SupportedPauseFrameUse(String value)
    {
        super("Supported pause frame use", true);
        enumToStrings = new EnumMap(SupportedPauseFrameUseEnum.class);
        enumToStrings.put(SupportedPauseFrameUseEnum.SYMMETRIC, "Symmetric");
        enumToStrings.put(SupportedPauseFrameUseEnum.RECEIVE_ONLY, "Receive-only");
        enumToStrings.put(SupportedPauseFrameUseEnum.TRANSMIT_ONLY, "Transmit-only");
        enumToStrings.put(SupportedPauseFrameUseEnum.NO, "No");
        this.value = convertStringToEnumValue(value);
    }
}
