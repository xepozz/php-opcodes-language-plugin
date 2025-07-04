package com.github.xepozz.php_opcodes_language.language.psi

import com.github.xepozz.php_opcodes_language.language.PHPOpFileType
import com.github.xepozz.php_opcodes_language.language.PHPOpLanguage
import com.github.xepozz.php_opcodes_language.language.psi.impl.PHPOpElementImpl
import com.intellij.openapi.util.TextRange
import com.intellij.psi.AbstractElementManipulator
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.IncorrectOperationException

class PHPOpElementManipulator : AbstractElementManipulator<PHPOpElementImpl>() {

    @Throws(IncorrectOperationException::class)
    override fun handleContentChange(entry: PHPOpElementImpl, range: TextRange, newContent: String): PHPOpElementImpl {
        val language = entry.language as? PHPOpLanguage ?: return entry

        val fileType = language.associatedFileType as PHPOpFileType
        val file = PHPOpElementFactory
            .createFile(entry.project, entry.text)

        val newEntry = PsiTreeUtil.findChildOfType(file, PHPOpElementImpl::class.java)

        return when (newEntry) {
            null -> entry
            else -> entry.replace(newEntry) as PHPOpElementImpl
        }
    }
}