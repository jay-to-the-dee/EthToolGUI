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
public abstract class Property
{
    private final String propertyName;
    private final boolean readOnly;
    protected Object value;

    public Property(String propertyName, boolean readOnly)
    {
        this.propertyName = propertyName;
        this.readOnly = readOnly;
    }

    public String getPropertyName()
    {
        return propertyName;
    }

    public boolean isReadOnly()
    {
        return readOnly;
    }

    public abstract Object getValue();
}
