package com.github.xepozz.php_opcodes_language.language.parser

import com.intellij.lexer.FlexAdapter

class PHPOpLexerAdapter : FlexAdapter(PHPOpLexer(null))