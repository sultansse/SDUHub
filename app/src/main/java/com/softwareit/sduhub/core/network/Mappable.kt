package com.softwareit.sduhub.core.network

interface Mappable<T> {
    fun map(): T
}

fun interface BaseMapper<FROM, TO> {
    fun map(source: FROM): TO
}