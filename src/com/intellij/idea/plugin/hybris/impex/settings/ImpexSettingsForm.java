/*
 * This file is part of "Hybris Integration" plugin for Intellij IDEA.
 * Copyright (C) 2014-2015 Alexander Bartash <AlexanderBartash@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.intellij.idea.plugin.hybris.impex.settings;

import javax.swing.*;

/**
 * Created 21:58 29 March 2015
 *
 * @author Alexander Bartash <AlexanderBartash@gmail.com>
 */
public class ImpexSettingsForm {

    private JCheckBox enableFoldingCheckBox;
    private JCheckBox useSmartFoldingCheckBox;
    private JPanel mainPanel;

    public void setData(final ImpexSettingsData data) {
        enableFoldingCheckBox.setSelected(data.isFoldingEnabled());
        useSmartFoldingCheckBox.setSelected(data.isUseSmartFolding());
    }

    public void getData(final ImpexSettingsData data) {
        data.setFoldingEnabled(enableFoldingCheckBox.isSelected());
        data.setUseSmartFolding(useSmartFoldingCheckBox.isSelected());
    }

    public boolean isModified(final ImpexSettingsData data) {
        if (enableFoldingCheckBox.isSelected() != data.isFoldingEnabled()) {
            return true;
        }
        if (useSmartFoldingCheckBox.isSelected() != data.isUseSmartFolding()) {
            return true;
        }
        return false;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
