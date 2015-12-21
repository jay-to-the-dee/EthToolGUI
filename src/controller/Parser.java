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

import java.io.*;
import java.util.*;
import model.InterfaceHandle;
import model.property.IntegerProperty.ValueOutOfBoundsException;
import model.property.*;

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
                try
                {
                    if (line.startsWith("\tAdvertised link modes:"))
                    {
                        interfaceHandle.addNewProperty(new AdvertisedLinkModes(ExtractMultiLineRead(line, buf)));
                    }
                    else if (line.startsWith("\tSupported link modes:"))
                    {
                        interfaceHandle.addNewProperty(new SupportedLinkModes(ExtractMultiLineRead(line, buf)));
                    }
                    else if (line.startsWith("\tSupported pause frame use:"))
                    {
                        interfaceHandle.addNewProperty(new SupportedPauseFrameUse(ExtractEndOfStringAfterColons(line)));
                    }

                    else if (line.startsWith("\tSupports auto-negotiation:"))
                    {
                        interfaceHandle.addNewProperty(new SupportsAutoNegotiation(StringToBoolean(line)));
                    }

                    if (line.startsWith("\tAdvertised auto-negotiation:"))
                    {
                        interfaceHandle.addNewProperty(new AdvertisedAutoNegotiation(StringToBoolean(line)));
                    }

                    else if (line.startsWith("\tSpeed:"))
                    {
                        interfaceHandle.addNewProperty(new Speed(ExtractEndOfStringAfterColons(line)));
                    }

                    else if (line.startsWith("\tDuplex:"))
                    {
                        interfaceHandle.addNewProperty(new Duplex(ExtractEndOfStringAfterColons(line)));
                    }

                    else if (line.startsWith("\tPort:"))
                    {
                        interfaceHandle.addNewProperty(new Port(ExtractEndOfStringAfterColons(line)));
                    }

                    else if (line.startsWith("\tPHYAD:"))
                    {
                        interfaceHandle.addNewProperty(new PHYAD(ExtractUnsignedIntegerValue(line)));
                    }

                    else if (line.startsWith("\tAuto-negotiation:"))
                    {
                        interfaceHandle.addNewProperty(new AutoNegotiation(StringToBoolean(line)));
                    }

                    else if (line.startsWith("\tMDI-X:"))
                    {
                        interfaceHandle.addNewProperty(new MDI_X(ExtractEndOfStringAfterColons(line)));
                    }

                    else if (line.startsWith("\tCurrent message level:"))
                    {
                        interfaceHandle.addNewProperty(new CurrentMessageLevel(ExtractEndOfStringAfterColons(line)));
                    }

                    else if (line.startsWith("\tLink detected:"))
                    {
                        interfaceHandle.addNewProperty(new LinkDetected(StringToBoolean(line)));
                    }
                }
                catch (ParseFailureException | ValueOutOfBoundsException ex)
                {
                    //Do nothing and passively accept
                }
            }

        }
        catch (IOException | InterruptedException ex)
        {
            System.err.println("Could not execute ethtool!");
        }
    }

    private boolean StringToBoolean(String line) throws ParseFailureException
    {
        String valueToLower = line.toLowerCase();
        if (valueToLower.endsWith("yes") || valueToLower.endsWith("on"))
        {
            return true;
        }
        else if (valueToLower.endsWith("no") || valueToLower.endsWith("off"))
        {
            return false;
        }
        else
        {
            throw new ParseFailureException(line);
        }
    }

    private String ExtractEndOfStringAfterColons(String line) throws ParseFailureException
    {
        try
        {
            int valueStartsAt = line.lastIndexOf(": ") + 2;
            int valueEndsAt = line.length();
            return line.substring(valueStartsAt, valueEndsAt);
        }
        catch (Exception ex) //Just in case we get any NullPointerExceptions or anything
        {
            throw new ParseFailureException(line);
        }
    }

    private Integer ExtractUnsignedIntegerValue(String line) throws ParseFailureException
    {
        try
        {
            String extractedString = ExtractEndOfStringAfterColons(line);
            Integer integer = Integer.valueOf(extractedString);
            return integer;
        }
        catch (NumberFormatException ex)
        {
            throw new ParseFailureException(line);
        }
    }

    private Set<String> SplitStringBySpaces(String line) throws ParseFailureException
    {
        try
        {
            String[] splited = line.split("\\s+");
            return new HashSet<>(Arrays.asList(splited));
        }
        catch (Exception ex) //Just in case we get any NullPointerExceptions or anything
        {
            throw new ParseFailureException(line);
        }
    }

    private Set<String> ExtractMultiLineRead(String line, BufferedReader buf) throws ParseFailureException, IOException
    {
        Set<String> extractedStrings = new HashSet();

        boolean firstLoop = false; // We already have our first line for sure, so we need to keep this state
        do
        {
            if (firstLoop && line.contains(":"))
            {
                break;
            }
            firstLoop = true;
            buf.mark(0);        //Set our reset point in stream
            String colonExtract = ExtractEndOfStringAfterColons(line).replaceAll("^\\s+", "");
            extractedStrings.addAll(SplitStringBySpaces(colonExtract));
        }
        while ((line = buf.readLine()) != null);

        buf.reset();
        return extractedStrings;
    }
}
