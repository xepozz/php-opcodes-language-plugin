package com.github.xepozz.php_opcodes_language.language.psi.impl

import com.github.xepozz.php_opcodes_language.language.psi.PHPOpBlock
import com.github.xepozz.php_opcodes_language.language.psi.PHPOpLineNumber
import com.github.xepozz.php_opcodes_language.language.psi.PHPOpTypes
import com.intellij.icons.AllIcons
import com.intellij.ide.projectView.PresentationData
import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementResolveResult
import com.intellij.psi.search.LocalSearchScope
import com.intellij.psi.search.SearchScope
import com.intellij.util.IncorrectOperationException
import com.jetbrains.php.lang.psi.PhpPsiUtil

abstract class PHPOpLineNumberBaseImpl : PHPOpLineNumber, PHPOpElementImpl {
    constructor(node: ASTNode) : super(node)

    override fun getName() = this.node.findChildByType(PHPOpTypes.NUMBER)?.text?.let {
        if (it.length == 4) {
            it
        } else null
    }

    override fun setName(name: String): PsiElement? {
        TODO("Not yet implemented")
    }

    override fun getPresentation() = PresentationData(text, null, getIcon(0), null)

    override fun getIcon(flags: Int) = AllIcons.Nodes.Property

    override fun getElement() = this

    override fun getRangeInElement() = TextRange(0, textLength)

    override fun resolve() = multiResolve(false).firstOrNull()?.element

    override fun multiResolve(incompleteCode: Boolean) = PsiElementResolveResult.createResults(this)

    override fun getCanonicalText(): String = text

    override fun handleElementRename(newName: String): PsiElement? = throw IncorrectOperationException()

    override fun bindToElement(element: PsiElement): PsiElement? {
        throw UnsupportedOperationException("Method bindToElement is not yet implemented in " + this.javaClass.getName())
    }

    override fun isReferenceTo(psiElement: PsiElement): Boolean {
        return when (psiElement) {
            is PHPOpLineNumber -> this.text == psiElement.text
            else -> false
        }
    }

    override fun isSoft() = false

    override fun getUseScope(): SearchScope {
        val block = PhpPsiUtil.getParentOfClass(this, PHPOpBlock::class.java) ?: return super.getUseScope()
        return LocalSearchScope(block)
    }
}