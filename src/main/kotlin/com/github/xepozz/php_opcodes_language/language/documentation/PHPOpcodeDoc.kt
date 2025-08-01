package com.github.xepozz.php_opcodes_language.language.documentation

data class ParameterDoc(
    val name: String,
    val number: Int,
    val description: String,
    val example_php: String? = null,
    val example_opcode: String? = null
)