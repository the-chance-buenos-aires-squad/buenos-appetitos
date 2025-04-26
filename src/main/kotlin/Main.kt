package org.example

import di.dataModule
import di.presentationModule
import di.useCaseModule
import org.example.presentation.HolderCLi
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.getKoin


fun main() {
    startKoin {
        modules(dataModule, useCaseModule, presentationModule)
    }

    val holderCli: HolderCLi = getKoin().get()

    holderCli.startCLI()
}