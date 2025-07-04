package com.github.xepozz.php_opcodes_language.language

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.psi.PsiElement

class PHPOpAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
    }

    companion object {
        val PATTERN_HIGHLIGHT = TextAttributesKey.createTextAttributesKey(
            "PHP_OPCODES_LANGUAGE_PATTERN",
            DefaultLanguageHighlighterColors.STRING,
        )
        val REFERENCE_DECLARATION_HIGHLIGHT = TextAttributesKey.createTextAttributesKey(
            "PHP_OPCODES_LANGUAGE_DIRECTIVE",
            DefaultLanguageHighlighterColors.FUNCTION_DECLARATION,
        )
        val REFERENCE_USAGE_HIGHLIGHT = TextAttributesKey.createTextAttributesKey(
            "PHP_OPCODES_LANGUAGE_ARGUMENT",
            DefaultLanguageHighlighterColors.FUNCTION_CALL,
        )
        val BLOCK_NAME_HIGHLIGHT = TextAttributesKey.createTextAttributesKey(
            "PHP_OPCODES_LANGUAGE_BLOCK_NAME",
            DefaultLanguageHighlighterColors.CLASS_NAME,
        )
        private val IDENTIFIER_HIGHLIGHT = TextAttributesKey.createTextAttributesKey(
            "PHP_OPCODES_LANGUAGE_IDENTIFIER",
            DefaultLanguageHighlighterColors.KEYWORD,
        )
    }
}