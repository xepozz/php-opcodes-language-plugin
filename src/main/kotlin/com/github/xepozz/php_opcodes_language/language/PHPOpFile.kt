package com.github.xepozz.php_opcodes_language.language

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider

class PHPOpFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, PHPOpLanguage.INSTANCE) {
    override fun getFileType() = PHPOpFileType.INSTANCE

    override fun toString() = "PHP Opcodes File"
}