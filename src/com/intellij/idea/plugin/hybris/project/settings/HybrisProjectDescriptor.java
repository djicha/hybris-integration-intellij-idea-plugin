/*
 * This file is part of "hybris integration" plugin for Intellij IDEA.
 * Copyright (C) 2014-2016 Alexander Bartash <AlexanderBartash@gmail.com>
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

package com.intellij.idea.plugin.hybris.project.settings;

import com.intellij.idea.plugin.hybris.project.tasks.TaskProgressProcessor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * Created 1:48 PM 14 June 2015.
 *
 * @author Alexander Bartash <AlexanderBartash@gmail.com>
 */
public interface HybrisProjectDescriptor {

    void setProject(@Nullable Project project);

    void setHybrisProject(@Nullable Project project);

    @Nullable
    Project getProject();

    @NotNull
    List<HybrisModuleDescriptor> getFoundModules();

    @NotNull
    List<HybrisModuleDescriptor> getModulesChosenForImport();

    void setModulesChosenForImport(@NotNull List<HybrisModuleDescriptor> moduleDescriptors);

    @NotNull
    Set<HybrisModuleDescriptor> getAlreadyOpenedModules();

    @Nullable
    File getRootDirectory();

    void clear();

    @Nullable
    File getModulesFilesDirectory();

    void setModulesFilesDirectory(@Nullable File modulesFilesDirectory);

    @Nullable
    File getSourceCodeZip();

    void setSourceCodeZip(@Nullable File sourceCodeZip);

    void setRootDirectoryAndScanForModules(@NotNull File rootDirectory,
                                           @Nullable TaskProgressProcessor<File> progressListenerProcessor,
                                           @Nullable TaskProgressProcessor<List<File>> errorsProcessor);

    boolean isOpenProjectSettingsAfterImport();

    void setOpenProjectSettingsAfterImport(boolean openProjectSettingsAfterImport);

    Boolean isImportOotbModulesInReadOnlyMode();

    void setImportOotbModulesInReadOnlyMode(boolean importOotbModulesInReadOnlyMode);

    @Nullable
    File getHybrisDistributionDirectory();

    void setHybrisDistributionDirectory(@Nullable File hybrisDistributionDirectory);

    @Nullable
    File getCustomExtensionsDirectory();

    void setCustomExtensionsDirectory(@Nullable File customExtensionsDirectory);

    boolean isCustomExtensionsPresent();

    void setCustomExtensionsPresent(boolean present);

    @Nullable
    String getJavadocUrl();

    void setJavadocUrl(@Nullable String javadocUrl);
}
