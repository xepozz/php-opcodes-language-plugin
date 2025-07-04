package com.github.xepozz.php_opcodes_language.language.psi

import com.intellij.psi.tree.TokenSet

object PHPOpTokenSets {
    val EMPTY_SET = TokenSet.EMPTY

    val COMMENTS = TokenSet.create(PHPOpTypes.COMMENT)
    val WHITESPACES = TokenSet.WHITE_SPACE
}
