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

import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

/**
 * Defines the Toolbar that goes at the top of the GUI
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public class TopToolbar extends JToolBar
{
    private JComboBox deviceSelectionComboBox;
    private JButton refreshButton;
    private final MainGUI parent;

    public TopToolbar(MainGUI parent)
    {
        super("Top Toolbar", JToolBar.HORIZONTAL);
        this.parent = parent;

        deviceSelectionComboBox = new JComboBox(new String[]
        {
            "eth0", "lo"
        }); //TODO: Change this to actually get information about present ports

        deviceSelectionComboBox.addActionListener(new RefreshTableActionListener(parent));
        
        refreshButton = makeNavigationButton("refresh24",
                "Refresh",
                "Get the current device state information from the Ethernet adapter",
                new RefreshTableActionListener(parent));

        this.add(deviceSelectionComboBox);
        this.add(refreshButton);
    }

    private JButton makeNavigationButton(String imageName, String actionCommand, String toolTipText, ActionListener actionListener)
    {
        //Look for the image.
        String imgLocation = "images/"
                + imageName
                + ".png";
        URL imageURL = MainGUI.class.getResource(imgLocation);

        //Create and initialize the button.
        JButton button = new JButton();
        button.setActionCommand(actionCommand);
        button.setToolTipText(toolTipText);
        button.addActionListener(actionListener);

        if (imageURL != null)
        {
            button.setIcon(new ImageIcon(imageURL, actionCommand));
        }
        else
        {
            button.setText(actionCommand);
            System.err.println("Resource not found: " + imgLocation);
        }

        return button;
    }

    public JComboBox getDeviceSelectionComboBox()
    {
        return deviceSelectionComboBox;
    }

    private static class RefreshTableActionListener implements ActionListener
    {
        private final MainGUI parent;

        public RefreshTableActionListener(MainGUI parent)
        {
            this.parent = parent;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            parent.refreshTable();
        }
    }
}
