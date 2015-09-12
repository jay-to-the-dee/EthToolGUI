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
package view;

import controller.Parser;
import java.awt.*;
import javax.swing.*;
import model.InterfaceHandle;

/**
 * Main GUI screen that controls all the programs panels
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public class MainGUI extends JFrame
{
    public static final String defaultDevice = "eth0"; //TODO: Remove after GUI implentation

    private final Container panel;
    private DeviceInformationTable deviceInformation;

    private InterfaceHandle currentlyShownInterfaceDevice;

    public MainGUI()
    {
        this.setTitle("EthTool GUI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(450, 200));

        panel = this.getContentPane();
        panel.setLayout(new BorderLayout());

        currentlyShownInterfaceDevice = new InterfaceHandle(defaultDevice);
        Parser parser = new Parser(currentlyShownInterfaceDevice);
        parser.executeEthtoolAndParse();
        
        deviceInformation = new DeviceInformationTable(currentlyShownInterfaceDevice);
        panel.add(deviceInformation, BorderLayout.CENTER);

        this.setContentPane(panel);
        this.pack();
        this.setVisible(true);
    }
}
