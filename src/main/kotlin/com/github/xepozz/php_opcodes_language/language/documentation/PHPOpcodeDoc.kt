package com.github.xepozz.php_opcodes_language.language.documentation

data class PHPOpcodeDoc(
    val name: String,
    val description: String,
    val syntax: String? = null,
    val examples: String? = null
)