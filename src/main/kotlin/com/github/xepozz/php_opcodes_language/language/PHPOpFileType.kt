package com.github.xepozz.php_opcodes_language.language

import com.github.xepozz.php_opcodes_language.PHPOpIcons
import com.intellij.openapi.fileTypes.LanguageFileType
import java.io.Serializable

class PHPOpFileType private constructor() : LanguageFileType(PHPOpLanguage.INSTANCE), Serializable {
    override fun getName() = "PHP Opcodes"

    override fun getDescription() = "PHP Opcodes language"

    override fun getDefaultExtension() = ""

    override fun getIcon() = PHPOpIcons.FILE

    companion object {
        @JvmStatic
        val INSTANCE = PHPOpFileType()
    }
}