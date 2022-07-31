package com.bdscampos.repositoriogithub.domain

import com.bdscampos.repositoriogithub.core.UseCase
import com.bdscampos.repositoriogithub.data.model.Repo
import com.bdscampos.repositoriogithub.data.repositories.RepoRepository
import kotlinx.coroutines.flow.Flow

class ListUserRepositoriesUseCase(private val repository: RepoRepository) : UseCase<String, List<Repo>>() {
    override suspend fun execute(param: String): Flow<List<Repo>> {
        return repository.listRepositories(param)
    }
}