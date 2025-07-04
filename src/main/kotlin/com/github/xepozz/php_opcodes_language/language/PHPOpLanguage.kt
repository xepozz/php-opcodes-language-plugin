package com.github.xepozz.php_opcodes_language.language

import com.intellij.lang.Language

class PHPOpLanguage : Language("PHP Opcodes") {
    companion object {
        @JvmStatic
        val INSTANCE = PHPOpLanguage();
    }
}