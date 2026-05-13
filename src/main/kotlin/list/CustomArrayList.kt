package org.example.list

class CustomArrayList(startSize: Int) : CustomList {
    private var inner = IntArray(startSize)
    private var _size = 0

    override val size: Int
        get() = _size

    override fun get(index: Int): Int {
        if (index !in 0..<_size) throw IndexOutOfBoundsException()
        return inner[index]
    }

    override fun set(index: Int, value: Int) {
        if (index !in 0..<_size) throw IndexOutOfBoundsException()
        inner[index] = value
    }

    override fun add(element: Int) {
        if (_size == inner.size) resize(inner.size * 2)
        inner[_size++] = element
    }

    override fun addFirst(element: Int) {
        if (_size == inner.size) resize(inner.size * 2)
        for (i in _size downTo 1) {
            inner[i] = inner[i - 1]
        }
        inner[0] = element
        _size++
    }

    override fun indexOf(element: Int): Int {
        for (i in 0..<_size) {
            if (inner[i] == element) return i
        }
        return -1
    }

    override fun remove(element: Int): Boolean {
        for (i in 0..<_size) {
            if (inner[i] == element) {
                for (j in i..<_size - 1) {
                    inner[j] = inner[j + 1]
                }
                _size--
                return true
            }
        }
        return false
    }

    private fun resize(newSize: Int) {
        val newInner = IntArray(newSize)
        for (i in 0..<_size) {
            newInner[i] = inner[i]
        }
        inner = newInner
    }

    override fun iterator(): Iterator<Int> {
        return object : Iterator<Int> {
            private var index = 0

            override fun hasNext(): Boolean = index < _size

            override fun next(): Int {
                if (!hasNext()) throw NoSuchElementException()
                return inner[index++]
            }
        }
    }

    companion object {
        fun customArrayListOf(vararg items: Int) =
            items.fold(CustomArrayList(items.size)) { list, item ->
                list.also { it.add(item) }
            }
    }
}
