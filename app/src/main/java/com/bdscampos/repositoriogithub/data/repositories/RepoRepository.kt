package com.bdscampos.repositoriogithub.data.repositories

import com.bdscampos.repositoriogithub.data.model.Repo
import kotlinx.coroutines.flow.Flow

interface RepoRepository {
    suspend fun listRepositories(user: String): Flow<List<Repo>>
}