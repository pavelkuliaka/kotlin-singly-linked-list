package org.example.stack

import org.example.list.CustomArrayList
import org.example.list.CustomList

class ArrayListStack : CustomList by CustomArrayList(10), Stack {

    override fun push(value: Int) {
        addFirst(value)
    }

    override fun pop(): Int {
        if (isEmpty) throw NoSuchElementException()
        val value = get(0)
        remove(value)
        return value
    }

    override fun peek(): Int {
        if (isEmpty) throw NoSuchElementException()
        return get(0)
    }

    override val isEmpty: Boolean
        get() = size == 0
}