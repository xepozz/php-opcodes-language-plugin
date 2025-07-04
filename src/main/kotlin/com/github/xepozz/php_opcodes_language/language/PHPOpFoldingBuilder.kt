package com.github.xepozz.php_opcodes_language.language

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.DumbAware
import com.intellij.psi.PsiElement

class PHPOpFoldingBuilder : FoldingBuilderEx(), DumbAware {
    override fun buildFoldRegions(root: PsiElement, document: Document, quick: Boolean) = emptyArray<FoldingDescriptor>()

    override fun getPlaceholderText(node: ASTNode) = "{ ... }"

    override fun isCollapsedByDefault(node: ASTNode) = false
}
