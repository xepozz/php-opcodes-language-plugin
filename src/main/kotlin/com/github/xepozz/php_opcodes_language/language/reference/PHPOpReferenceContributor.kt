package com.github.xepozz.php_opcodes_language.language.reference

import com.github.xepozz.php_opcodes_language.language.psi.PHPOpLineNumber
import com.github.xepozz.php_opcodes_language.language.psi.PHPOpParameter
import com.github.xepozz.php_opcodes_language.language.psi.PHPOpParenParameter
import com.github.xepozz.php_opcodes_language.language.psi.PHPOpVarName
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceContributor
import com.intellij.psi.PsiReferenceProvider
import com.intellij.psi.PsiReferenceRegistrar
import com.intellij.util.ProcessingContext

class PHPOpReferenceContributor : PsiReferenceContributor() {
    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        registrar.registerReferenceProvider(
            PlatformPatterns.or(
                PlatformPatterns.psiElement(PHPOpVarName::class.java),
                PlatformPatterns.psiElement(PHPOpLineNumber::class.java),
                PlatformPatterns.psiElement(PHPOpParameter::class.java),
                PlatformPatterns.psiElement(PHPOpParenParameter::class.java),
            ),
            object : PsiReferenceProvider() {
                override fun getReferencesByElement(
                    element: PsiElement,
                    context: ProcessingContext
                ): Array<PsiReference> {
                    if (element !is PsiReference) return PsiReference.EMPTY_ARRAY

                    return arrayOf(element)
                }
            }
        )
    }
}