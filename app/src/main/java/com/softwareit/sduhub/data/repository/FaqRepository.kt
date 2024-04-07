package com.softwareit.sduhub.data.repository

import com.softwareit.sduhub.data.network.backend.BackendDataSource
import com.softwareit.sduhub.domain.model.FaqDTO


interface FaqRepository {

    suspend fun getFaqItems(): Result<List<FaqDTO>>
}


class FaqRepositoryImpl(
    val backendDataSource: BackendDataSource
) : FaqRepository {

    override suspend fun getFaqItems(): Result<List<FaqDTO>> {
        return backendDataSource.getFaqItems()
    }
}