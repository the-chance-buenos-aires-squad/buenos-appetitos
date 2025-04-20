package org.example.dependencyInjection

import org.example.presentation.HolderCLi
import org.koin.dsl.module

val presentationModule = module {
    single {
        HolderCLi(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
}