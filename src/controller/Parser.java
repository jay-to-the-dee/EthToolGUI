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
package controller;

import model.property.*;
import java.io.*;
import model.InterfaceHandle;

/**
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public class Parser
{
    private final InterfaceHandle interfaceHandle;

    public Parser(InterfaceHandle interfaceHandle)
    {
        this.interfaceHandle = interfaceHandle;
    }

    public void executeEthtoolAndParse()
    {
        Process p;
        try
        {
            p = Runtime.getRuntime().exec("ethtool " + interfaceHandle.getDevice());

            p.waitFor();
            BufferedReader buf = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            String line;

            while ((line = buf.readLine()) != null)
            {
                if (line.startsWith("\tSupported pause frame use:"))
                {
                    try
                    {
                        interfaceHandle.addNewProperty(new SupportedPauseFrameUse(YesNoToBoolean(line)));
                    }
                    catch (ParseFailureException ex)
                    {
                        //Do nothing and passively accept
                    }
                }

                else if (line.startsWith("\tSupports auto-negotiation:"))
                {
                    try
                    {
                        interfaceHandle.addNewProperty(new SupportsAutoNegotiation(YesNoToBoolean(line)));
                    }
                    catch (ParseFailureException ex)
                    {
                        //Do nothing and passively accept
                    }
                }

                if (line.startsWith("\tAdvertised auto-negotiation:"))
                {
                    try
                    {
                        interfaceHandle.addNewProperty(new AdvertisedAutoNegotiation(YesNoToBoolean(line)));
                    }
                    catch (ParseFailureException ex)
                    {
                        //Do nothing and passively accept
                    }
                }

                else if (line.startsWith("\tSpeed:"))
                {
                    try
                    {
                        interfaceHandle.addNewProperty(new Speed(ExtractEndOfStringAfterColons(line)));
                    }
                    catch (ParseFailureException ex)
                    {
                        //Do nothing and passively accept
                    }
                }

                else if (line.startsWith("\tDuplex:"))
                {
                    try
                    {
                        interfaceHandle.addNewProperty(new Duplex(ExtractEndOfStringAfterColons(line)));
                    }
                    catch (ParseFailureException ex)
                    {
                        //Do nothing and passively accept
                    }
                }

                else if (line.startsWith("\tPort:"))
                {
                    try
                    {
                        interfaceHandle.addNewProperty(new Port(ExtractEndOfStringAfterColons(line)));
                    }
                    catch (ParseFailureException ex)
                    {
                        //Do nothing and passively accept
                    }
                }

                else if (line.startsWith("\tPHYAD:"))
                {
                    try
                    {
                        interfaceHandle.addNewProperty(new PHYAD(ExtractUnsignedIntegerValue(line)));
                    }
                    catch (Exception ex)
                    {
                        //Do nothing and passively accept
                    }
                }

                else if (line.startsWith("\tLink detected:"))
                {
                    try
                    {
                        interfaceHandle.addNewProperty(new LinkDetected(YesNoToBoolean(line)));
                    }
                    catch (ParseFailureException ex)
                    {
                        //Do nothing and passively accept
                    }
                }
            }

        }
        catch (IOException | InterruptedException ex)
        {
            System.err.println("Could not execute ethtool!");
        }
    }

    private boolean YesNoToBoolean(String value) throws ParseFailureException
    {
        String valueToLower = value.toLowerCase();
        if (valueToLower.endsWith("yes"))
        {
            return true;
        }
        else if (valueToLower.endsWith("no"))
        {
            return false;
        }
        else
        {
            throw new ParseFailureException(value);
        }
    }

    private String ExtractEndOfStringAfterColons(String value) throws ParseFailureException
    {
        try
        {
            int valueStartsAt = value.lastIndexOf(": ") + 2;
            int valueEndsAt = value.length();
            return value.substring(valueStartsAt, valueEndsAt);
        }
        catch (Exception ex) //Just in case we get any NullPointerExceptions or anything
        {
            throw new ParseFailureException(value);
        }
    }

    private Integer ExtractUnsignedIntegerValue(String value) throws ParseFailureException
    {
        value = ExtractEndOfStringAfterColons(value);
        return Integer.valueOf(value);
    }
}
