package org.example.stack

import org.example.list.SingleLinkedList

class SingleLinkedStack : SingleLinkedList(), Stack {

    override fun push(value: Int) {
        addFirst(value)
    }

    override fun pop(): Int {
        if (size == 0) throw NoSuchElementException()
        val value = get(0)
        remove(value)
        return value
    }

    override fun peek(): Int {
        if (size == 0) throw NoSuchElementException()
        return get(0)
    }

    override val isEmpty: Boolean
        get() = size == 0
}
