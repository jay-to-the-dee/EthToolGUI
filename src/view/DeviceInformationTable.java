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

import javax.swing.*;
import javax.swing.table.*;
import model.InterfaceHandle;
import model.property.Property;

/**
 * Main table that provides the information from the InterfaceHandle
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public class DeviceInformationTable extends JTable
{
    public DeviceInformationTable(InterfaceHandle interfaceDevice)
    {
        DeviceInformationTableModel dataModel = new DeviceInformationTableModel();
        this.setModel(dataModel);

        for (Property property : interfaceDevice.getProperties())
        {

            dataModel.addRow(new Object[]
            {
                property.getPropertyName(), property.getValue()
            });

        }
    }

    private class DeviceInformationTableModel extends DefaultTableModel
    {

        public DeviceInformationTableModel()
        {
            super(new String[]
            {
                "Property", "Value"
            }, 0);
        }

        

    }
}
