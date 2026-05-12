package org.example

class SingleLinkedList : CustomList {

    private var list: Node? = null
    private var listSize: Int = 0

    override val size: Int
        get() = listSize

    override fun add(element: Int) {
        if (list == null) {
            list = Node(element, null)
        } else {
            var current = list
            while (current?.next != null) {
                current = current.next
            }
            current?.next = Node(element, null)
        }
        listSize++
    }

    override operator fun get(index: Int): Int {
        if (index !in 0..<listSize) throw IndexOutOfBoundsException()
        var current = list
        repeat(index) { current = current?.next }
        return current?.key ?: error("List is corrupted")
    }

    override operator fun set(index: Int, value: Int) {
        if (index !in 0..<listSize) throw IndexOutOfBoundsException()
        var current = list
        repeat(index) { current = current?.next }
        (current ?: error("List is corrupted")).key = value
    }

    override fun addFirst(element: Int) {
        list = Node(element, list)
        listSize++
    }

    override fun indexOf(element: Int): Int {
        var current = list
        var index = 0
        while (current != null) {
            if (current.key == element) return index
            current = current.next
            index++
        }
        return -1
    }

    override fun remove(element: Int): Boolean {
        if (list?.key == element) {
            list = list?.next
            listSize--
            return true
        }
        var current = list
        while (current?.next != null) {
            if (current.next?.key == element) {
                current.next = current.next?.next
                listSize--
                return true
            }
            current = current.next
        }
        return false
    }

    override fun iterator(): Iterator<Int> {
        return object : Iterator<Int> {
            private var current = list

            override fun hasNext(): Boolean = current != null

            override fun next(): Int {
                if (!hasNext()) throw NoSuchElementException()
                val value = current?.key ?: error("Iterator is corrupted")
                current = current?.next
                return value
            }
        }
    }

    companion object {
        fun singleLinkedListOf(vararg items: Int) =
            items.fold(SingleLinkedList()) { list, item ->
                list.also{ it.add(item) }
            }
    }
}