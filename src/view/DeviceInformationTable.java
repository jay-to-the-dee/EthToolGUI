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

import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import model.InterfaceHandle;
import model.property.*;

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

            if (property instanceof MultiEnumProperty)
            {
                MultiEnumProperty multiEnumProperty = (MultiEnumProperty) property;
                EnumSet value = (EnumSet) multiEnumProperty.getValue();

                for (Iterator it = value.iterator(); it.hasNext();)
                {
                    Object item = it.next();
                    Enum enumItem = (Enum) item;

                    if (multiEnumProperty.isReadOnly())
                    {
                        JLabel itemLabel = new JLabel(multiEnumProperty.convertEnumValueToString(enumItem));
                        this.add(itemLabel);
                    }
                    else
                    {
                        //TODO: Advertised link modes needs its options deriving from supported
                        JCheckBox itemCheckBox = new JCheckBox(multiEnumProperty.convertEnumValueToString(enumItem), true);
                        this.add(itemCheckBox);
                    }

                    if (it.hasNext())
                    {
                        this.add(new JPanel()); //Filler panel for alignment
                    }
                }

            }

            else if (property instanceof SingleEnumProperty)
            {
                final JComboBox newPropertyValueComboBox;
                final SingleEnumProperty singleEnumProperty = (SingleEnumProperty) property;

                newPropertyValueComboBox = new JComboBox(singleEnumProperty.getAllPossibleValues().toArray());
                newPropertyValueComboBox.setSelectedItem(singleEnumProperty.toString());
                if (property.isReadOnly())
                {
                    newPropertyValueComboBox.addActionListener(new ActionListener()
                    { //This ActionListener stops other items being selected when value read-only
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            newPropertyValueComboBox.setSelectedItem(singleEnumProperty.toString());
                        }
                    });
                }

                this.add(newPropertyValueComboBox);
            }
            else if (property.getValue() instanceof Boolean)
            {
                JCheckBox newPropertyValueCheckBox;
                newPropertyValueCheckBox = new JCheckBox();
                newPropertyValueCheckBox.setSelected((Boolean) property.getValue());
                newPropertyValueCheckBox.setEnabled(!property.isReadOnly());

                this.add(newPropertyValueCheckBox);
            }
            else if (property.getValue() instanceof Integer)
            {
                JSpinner newPropertyValueSpinner;
                IntegerProperty integerProperty = (IntegerProperty) property;

                SpinnerModel sm = new SpinnerNumberModel(
                        (int) integerProperty.getValue(),
                        integerProperty.getMinIntValue(),
                        integerProperty.getMaxIntValue(),
                        1);
                newPropertyValueSpinner = new JSpinner(sm);

                newPropertyValueSpinner.setEnabled(!property.isReadOnly());
                this.add(newPropertyValueSpinner);
            }
            else
            {
                JTextField newPropertyValueTextField;
                newPropertyValueTextField = new JTextField((String) property.getValue());
                newPropertyValueTextField.setEditable(!property.isReadOnly());

                this.add(newPropertyValueTextField);
            }
        }
    }
}
