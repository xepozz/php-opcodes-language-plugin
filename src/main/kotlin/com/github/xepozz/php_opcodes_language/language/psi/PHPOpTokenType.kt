package com.github.xepozz.php_opcodes_language.language.psi

import com.github.xepozz.php_opcodes_language.language.PHPOpLanguage
import com.intellij.psi.tree.IElementType

class PHPOpTokenType(debugName: String) : IElementType(debugName, PHPOpLanguage.INSTANCE) {
    override fun toString() = "PHPOpTokenType." + super.toString()
}