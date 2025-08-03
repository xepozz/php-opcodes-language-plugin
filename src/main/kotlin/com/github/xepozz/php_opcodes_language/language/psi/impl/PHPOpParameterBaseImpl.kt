package com.github.xepozz.php_opcodes_language.language.psi.impl

import com.github.xepozz.php_opcodes_language.Opcodes
import com.github.xepozz.php_opcodes_language.Primitives
import com.github.xepozz.php_opcodes_language.language.psi.PHPOpBlock
import com.github.xepozz.php_opcodes_language.language.psi.PHPOpParameter
import com.intellij.icons.AllIcons
import com.intellij.ide.projectView.PresentationData
import com.intellij.lang.ASTNode
import com.intellij.openapi.util.NlsSafe
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementResolveResult
import com.intellij.psi.search.LocalSearchScope
import com.intellij.psi.search.SearchScope
import com.jetbrains.php.lang.psi.PhpPsiUtil

abstract class PHPOpParameterBaseImpl : PHPOpParameter, PHPOpElementImpl {
    constructor(node: ASTNode) : super(node)

    override fun getName(): String? = text.let {
        if (isPrimitive(this)) null else text
    }

    override fun setName(name: @NlsSafe String): PsiElement? {
        TODO("Not yet implemented")
    }

    override fun getPresentation() = PresentationData(text, null, getIcon(0), null)

    override fun getIcon(flags: Int) = AllIcons.Nodes.Property

    override fun getElement() = this

    override fun getRangeInElement() = TextRange(0, textLength)

    override fun resolve() = multiResolve(false).firstOrNull()?.element

    override fun multiResolve(incompleteCode: Boolean) = PsiElementResolveResult.createResults(this)

    override fun getCanonicalText(): String = text

    override fun handleElementRename(newName: String) = this.setName(newName)

    override fun bindToElement(element: PsiElement): PsiElement? {
        throw UnsupportedOperationException("Method bindToElement is not yet implemented in " + this.javaClass.getName())
    }

    override fun isReferenceTo(psiElement: PsiElement) = when (psiElement) {
        !is PHPOpParameter -> false
        else -> when {
            isSelfReferencable(this) && isSelfReferencable(psiElement) -> true
            isPrimitive(this) || isPrimitive(psiElement) -> false
            !this.isVariable || !psiElement.isVariable -> false
            else -> this.text == psiElement.text
        }
    }

    private fun isSelfReferencable(psiElement: PHPOpParameter): Boolean = Opcodes.selfReference
        .map { it.name }
        .contains(psiElement.text)

    private fun isPrimitive(element: PHPOpParameter): Boolean = Primitives.entries
        .map { it.name }
        .contains(element.text)

    override fun isSoft() = false

    override fun getUseScope(): SearchScope {
        val block = PhpPsiUtil.getParentOfClass(this, PHPOpBlock::class.java) ?: return super.getUseScope()
        return LocalSearchScope(block)
    }
}