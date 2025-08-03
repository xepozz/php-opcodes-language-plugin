package com.github.xepozz.php_opcodes_language

enum class Opcodes() {
    RETURN,
    THIS,
    EXT_STMT;

    companion object {
        val selfReference = listOf(
            RETURN,
            THIS,
            EXT_STMT,
        )
    }
}
