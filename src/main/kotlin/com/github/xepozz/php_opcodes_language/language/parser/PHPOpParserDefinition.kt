package com.github.xepozz.php_opcodes_language.language.parser

import com.github.xepozz.php_opcodes_language.language.PHPOpFile
import com.github.xepozz.php_opcodes_language.language.PHPOpLanguage
import com.github.xepozz.php_opcodes_language.language.psi.PHPOpTokenSets
import com.github.xepozz.php_opcodes_language.language.psi.PHPOpTypes
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet

internal class PHPOpParserDefinition : ParserDefinition {
    override fun createLexer(project: Project) = PHPOpLexerAdapter()

    override fun getCommentTokens() = PHPOpTokenSets.COMMENTS

    override fun getWhitespaceTokens(): TokenSet = PHPOpTokenSets.WHITESPACES

    override fun getStringLiteralElements(): TokenSet = TokenSet.EMPTY

    override fun createParser(project: Project?) = PHPOpParser()

    override fun getFileNodeType() = FILE

    override fun createFile(viewProvider: FileViewProvider) = PHPOpFile(viewProvider)

    override fun createElement(node: ASTNode): PsiElement = PHPOpTypes.Factory.createElement(node)

    companion object {
        val FILE = IFileElementType(PHPOpLanguage.INSTANCE)
    }
}
