package com.github.xepozz.php_opcodes_language.language

import com.github.xepozz.php_opcodes_language.language.parser.PHPOpLexerAdapter
import com.github.xepozz.php_opcodes_language.language.psi.PHPOpTypes
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType

internal class PHPOpSyntaxHighlighterFactory : SyntaxHighlighterFactory() {
    override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?) = object : SyntaxHighlighterBase() {
        override fun getHighlightingLexer() = PHPOpLexerAdapter()

        override fun getTokenHighlights(tokenType: IElementType) = when (tokenType) {
            PHPOpTypes.NUMBER -> NUMBER_KEYS
            PHPOpTypes.TEXT -> TEXT_KEYS
            PHPOpTypes.COMMENT -> COMMENT_KEYS
            TokenType.BAD_CHARACTER -> BAD_CHAR_KEYS
            else -> EMPTY_KEYS
        }
    }

    companion object {
        private val BAD_CHAR_KEYS = arrayOf(
            HighlighterColors.BAD_CHARACTER,
        )

        private val COMMENT_KEYS = arrayOf(
            DefaultLanguageHighlighterColors.DOC_COMMENT
        )
        private val TEXT_KEYS = arrayOf(
            DefaultLanguageHighlighterColors.STRING
        )
        private val NUMBER_KEYS = arrayOf(
            DefaultLanguageHighlighterColors.NUMBER
        )
        private val HEREDOC_MARKER_KEYS = arrayOf(
            DefaultLanguageHighlighterColors.KEYWORD
        )
        private val HEREDOC_CONTENT_KEYS = arrayOf(
            DefaultLanguageHighlighterColors.STRING
        )
        private val EMPTY_KEYS = emptyArray<TextAttributesKey>()
    }

}