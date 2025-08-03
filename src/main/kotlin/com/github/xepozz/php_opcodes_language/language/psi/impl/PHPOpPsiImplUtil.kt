package com.github.xepozz.php_opcodes_language.language.psi.impl

import com.github.xepozz.php_opcodes_language.language.psi.PHPOpBlockName
import com.github.xepozz.php_opcodes_language.language.psi.PHPOpParameter
import com.github.xepozz.php_opcodes_language.language.psi.PHPOpTypes

class PHPOpPsiImplUtil {
    companion object {
        @JvmStatic
        fun isVariable(element: PHPOpParameter): Boolean = element.text.matches(Regex("(?:[TV]|CV)\\d+"))

        @JvmStatic
        fun isFunction(element: PHPOpBlockName): Boolean = element.node.let {
            val children = it.getChildren(null)
            children.size == 1
                    && children[0].elementType == PHPOpTypes.IDENTIFIER
                    && !children[0].text.contains("\\")
        }

        // todo: check for class in a different way
        @JvmStatic
        fun isClass(element: PHPOpBlockName): Boolean = element.node.let {
            val children = it.getChildren(null)
            children.size == 1
                    && children[0].elementType == PHPOpTypes.IDENTIFIER
                    && children[0].text.contains("\\")
        }

        @JvmStatic
        fun isClassMethod(element: PHPOpBlockName): Boolean = element.node.let {
            val children = it.getChildren(null)
            children.size == 4
                    && children[0].elementType == PHPOpTypes.IDENTIFIER
                    && children[1].elementType == PHPOpTypes.COLON
                    && children[2].elementType == PHPOpTypes.COLON
                    && children[3].elementType == PHPOpTypes.IDENTIFIER
        }

        @JvmStatic
        fun isMain(element: PHPOpBlockName): Boolean = element.text == $$"$_main"
    }
}
