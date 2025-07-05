package com.github.xepozz.php_opcodes_language.language

import com.github.xepozz.php_opcodes_language.language.psi.PHPOpInstructionName
import com.github.xepozz.php_opcodes_language.language.psi.PHPOpVarName
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import com.jetbrains.php.lang.highlighter.PhpHighlightingData

class PHPOpAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
//        println("Annotating $element: ${element.text}")
        when (element) {
            is PHPOpVarName -> {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(element)
                    .textAttributes(PhpHighlightingData.VAR)
                    .create()
            }

            is PHPOpInstructionName -> {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(element)
                    .textAttributes(PhpHighlightingData.FUNCTION_CALL)
                    .create()
            }
        }
    }
}