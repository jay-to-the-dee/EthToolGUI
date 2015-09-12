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

import java.awt.*;
import javax.swing.*;
import model.InterfaceHandle;
import model.property.Property;

/**
 * Main table that provides the information from the InterfaceHandle
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public class DeviceInformationTable extends JPanel
{
    public DeviceInformationTable(InterfaceHandle interfaceDevice)
    {

        this.setLayout(new GridLayout(0, 2));

        for (Property property : interfaceDevice.getProperties())
        {
            this.add(new JLabel(property.getPropertyName()));

            if (property.getValue() instanceof Boolean)
            {
                JCheckBox newPropertyValueCheckBox;
                newPropertyValueCheckBox = new JCheckBox();
                newPropertyValueCheckBox.setSelected((boolean)property.getValue());
                newPropertyValueCheckBox.setEnabled(!property.isReadOnly());
                this.add(newPropertyValueCheckBox);
            }
            else
            {
                JTextField newPropertyValueTextField;
                newPropertyValueTextField = new JTextField((String)property.getValue());
                newPropertyValueTextField.setEditable(!property.isReadOnly());
                this.add(newPropertyValueTextField);
            }

        }
    }

}
