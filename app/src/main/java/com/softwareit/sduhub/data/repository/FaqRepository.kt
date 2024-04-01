package com.softwareit.sduhub.data.repository

import com.softwareit.sduhub.data.local.faq.FaqDBO
import com.softwareit.sduhub.data.local.faq.FaqDao
import kotlinx.coroutines.flow.Flow


interface FaqRepository {

    fun getFaqItems(): Flow<List<FaqDBO>>
}

class FaqRepositoryImpl(
    private val faqDao: FaqDao,
) : FaqRepository {

    override fun getFaqItems(): Flow<List<FaqDBO>> {
        return faqDao.getFaqItems()
    }
}