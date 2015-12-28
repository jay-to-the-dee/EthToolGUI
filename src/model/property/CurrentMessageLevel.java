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

/**
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */

/*
 #ifndef HAVE_NETIF_MSG
 enum {
 NETIF_MSG_DRV		= 0x0001,
 NETIF_MSG_PROBE		= 0x0002,
 NETIF_MSG_LINK		= 0x0004,
 NETIF_MSG_TIMER		= 0x0008,
 NETIF_MSG_IFDOWN	= 0x0010,
 NETIF_MSG_IFUP		= 0x0020,
 NETIF_MSG_RX_ERR	= 0x0040,
 NETIF_MSG_TX_ERR	= 0x0080,
 NETIF_MSG_TX_QUEUED	= 0x0100,
 NETIF_MSG_INTR		= 0x0200,
 NETIF_MSG_TX_DONE	= 0x0400,
 NETIF_MSG_RX_STATUS	= 0x0800,
 NETIF_MSG_PKTDATA	= 0x1000,
 NETIF_MSG_HW		= 0x2000,
 NETIF_MSG_WOL		= 0x4000,
 };
 #endif

 //this.maxIntValue = (1 << 32) - 1; //Max value is 4,294,967,296 as this is a __u32 in C

 */
public class CurrentMessageLevel extends Property<String>
{
    public CurrentMessageLevel(String value)
    {
        super("Current message level", false);
        this.value = value;
    }

    @Override
    public String getValue()
    {
        return value;
    }
}
