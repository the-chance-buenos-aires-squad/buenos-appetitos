package org.example

import org.example.dependencyInjection.dataModule
import org.example.dependencyInjection.presentationModule
import org.example.dependencyInjection.useCaseModule
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