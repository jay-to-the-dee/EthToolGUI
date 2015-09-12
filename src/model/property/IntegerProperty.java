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
public abstract class IntegerProperty extends Property
{
    public int maxIntValue;
    public int minIntValue;

    public IntegerProperty(String propertyName, boolean readOnly)
    {
        super(propertyName, readOnly);
    }

    @Override
    public Integer getValue()
    {
        return (Integer) value;
    }

    protected void setIntValue(Integer value) throws ValueOutOfBoundsException
    {
        if (value < minIntValue || value > maxIntValue)
        {
            throw new ValueOutOfBoundsException(value);
        }
        else
        {
            this.value = value;
        }
    }

    protected class ValueOutOfBoundsException extends Exception
    {
        public ValueOutOfBoundsException(Integer value)
        {
            System.err.println(getPropertyName() + " was set to out of bounds value: " + value);
        }
    }
}
