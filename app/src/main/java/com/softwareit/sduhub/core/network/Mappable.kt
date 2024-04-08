package com.softwareit.sduhub.core.network

interface Mappable<TO> {
    suspend fun map(): TO
}