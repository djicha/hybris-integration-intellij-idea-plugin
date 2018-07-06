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

package com.intellij.idea.plugin.hybris.impex.inspection

import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.idea.plugin.hybris.impex.psi.ImpexMacroDeclaration
import com.intellij.idea.plugin.hybris.impex.psi.ImpexMacroUsageDec
import com.intellij.idea.plugin.hybris.impex.psi.ImpexVisitor
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.containers.ContainerUtilRt

/**
 * @author Nosov Aleksandr <nosovae.dev@gmail.com>
 */
class UnknownMacrosInspection : LocalInspectionTool() {
    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor = UnknownMacrosVisitor(holder)
}

private class UnknownMacrosVisitor(private val problemsHolder: ProblemsHolder) : ImpexVisitor() {
    private val cachedMacros = ContainerUtilRt.newHashMap<String, Boolean>()

    override fun visitMacroUsageDec(usage: ImpexMacroUsageDec) {
        if (usage.text == "\$config-") return
        val macroName = usage.text

        if (macroName.isNotEmpty()) {
            val isDeclarationExists = cachedMacros[macroName]
            if (isDeclarationExists == true) return
            if (isDeclarationExists != null && isDeclarationExists == false) {
                problemsHolder.registerProblem(usage, "Unknown macros", ProblemHighlightType.GENERIC_ERROR)
            } else {
                val declaration = findMacrosDeclaration(usage.containingFile, macroName)
                if (declaration == null) {
                    cachedMacros[macroName] = false
                    problemsHolder.registerProblem(usage, "Unknown macros", ProblemHighlightType.GENERIC_ERROR)
                } else {
                    cachedMacros[macroName] = true
                }
            }
        }

    }

    private fun findMacrosDeclaration(file: PsiFile, macroName: String): ImpexMacroDeclaration? {
        val declarations = PsiTreeUtil.findChildrenOfAnyType(file, ImpexMacroDeclaration::class.java)
        return declarations.find { it.firstChild.text == macroName }
    }
}