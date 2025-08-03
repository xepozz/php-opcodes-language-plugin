package com.github.xepozz.php_opcodes_language.language.documentation

import com.github.xepozz.php_opcodes_language.language.psi.PHPOpParameter
import com.github.xepozz.php_opcodes_language.language.psi.PHPOpParenParameter
import com.intellij.lang.documentation.AbstractDocumentationProvider
import com.intellij.markdown.utils.lang.CodeBlockHtmlSyntaxHighlighter
import com.intellij.psi.PsiElement

class PHPOpDocumentationProvider : AbstractDocumentationProvider() {
    override fun getQuickNavigateInfo(element: PsiElement?, originalElement: PsiElement?): String? = when (element) {
        is PHPOpParenParameter -> getQuickNavigateInfo(element.parameter, originalElement)
        is PHPOpParameter -> {
            val doc = OpcodesDictionary.getDocumentation(element.text!!)

            doc?.description
        }

        else -> null
    }

    override fun generateDoc(element: PsiElement?, originalElement: PsiElement?): String? = when (element) {
        is PHPOpParenParameter -> generateDoc(element.parameter, originalElement)
        is PHPOpParameter -> {
            val doc = OpcodesDictionary.getDocumentation(element.text!!) ?: return null
            val highlighter = CodeBlockHtmlSyntaxHighlighter(element.project)

            buildString {
                append("<div class='definition'><pre>")
                append(doc.name)
                append("</pre></div>")

                append("<div class='content'>")
                append("<p>${doc.description}}</p>")

                if (doc.example_php != null) {
                    append("<h3>Example PHP</h3>")
                    append("<pre>${highlighter.color("InjectablePHP", doc.example_php)}</pre>")
                }

                if (doc.example_opcode != null) {
                    append("<h3>Example Opcodes</h3>")
                    append("<pre>")
                    append("<pre>${highlighter.color("PHP Opcodes", doc.example_opcode)}</pre>")
                    append("</pre>")
                }

                append("</div>")
            }
        }

        else -> null
    }
}