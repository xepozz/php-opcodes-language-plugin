package com.github.xepozz.php_opcodes_language.language.psi.impl

import com.github.xepozz.php_opcodes_language.language.psi.PHPOpParameter

class PHPOpPsiImplUtil {
    companion object {
        @JvmStatic
        fun isVariable(element: PHPOpParameter): Boolean = element.text.startsWith("$")
        fun isVariable(element: PHPOpParameter): Boolean = element.text.matches(Regex("[TV]\\d+"))
    }
}