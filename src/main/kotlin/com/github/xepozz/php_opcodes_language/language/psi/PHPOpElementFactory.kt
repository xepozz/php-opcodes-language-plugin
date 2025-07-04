package com.github.xepozz.php_opcodes_language.language.psi

import com.github.xepozz.php_opcodes_language.language.PHPOpFile
import com.github.xepozz.php_opcodes_language.language.PHPOpFileType
import com.intellij.openapi.project.Project

object PHPOpElementFactory {
    fun createFile(project: Project, text: String): PHPOpFile {
        val name = "PHPOp"
        return com.intellij.psi.PsiFileFactory.getInstance(project)
            .createFileFromText(name, PHPOpFileType.INSTANCE, text) as PHPOpFile
    }
}