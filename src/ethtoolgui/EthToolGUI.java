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
package ethtoolgui;

import javax.swing.*;

/**
 * A GUI interface to the Linux `ethtool` command
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public class EthToolGUI
{
    /**
     * @param args the command line arguments - perhaps device name should be
     * accepted in future?
     */
    public static void main(String[] args)
    {
        setGUITheme();
        view.MainGUI mainGUI = new view.MainGUI();
    }

    private static void setGUITheme()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
        {
            /*try
             {
             for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
             {
             if ("Nimbus".equals(info.getName()))
             {
             UIManager.setLookAndFeel(info.getClassName());
             break;
             }
             }
             }
             catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e2)
             {
             }
             /**/
        }
    }
}
