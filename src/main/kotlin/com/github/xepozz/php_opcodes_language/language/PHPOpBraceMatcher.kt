package com.github.xepozz.php_opcodes_language.language

import com.github.xepozz.php_opcodes_language.language.psi.PHPOpTypes
import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType

class PHPOpBraceMatcher : PairedBraceMatcher {
    private val bracePairs = arrayOf(
        BracePair(PHPOpTypes.LPAREN, PHPOpTypes.RPAREN, true)
    )

    override fun getPairs() = bracePairs

    override fun isPairedBracesAllowedBeforeType(
        p0: IElementType,
        p1: IElementType?
    ) = true

    override fun getCodeConstructStart(
        file: PsiFile,
        openingBraceOffset: Int
    ): Int = openingBraceOffset

}