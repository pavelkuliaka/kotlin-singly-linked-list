package org.example

import org.example.list.CustomList

object ListPrinter {
    fun printList(list: CustomList) {
        println(list.joinToString(", ", "[", "]"))
    }
}