package org.example.presentation.uiController

class UiController {
     fun printMessage(message:String, isInline: Boolean = false) {
         if (isInline) {
             print(message)
         }else{
             println(message)
         }

     }
    fun readInput():String = readln()
}

