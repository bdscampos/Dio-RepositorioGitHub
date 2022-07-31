package com.bdscampos.repositoriogithub.data.repositories

import com.bdscampos.repositoriogithub.core.RemoteException
import com.bdscampos.repositoriogithub.data.services.GitHubServices
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class RepoRepositoryImpl(private val service: GitHubServices) : RepoRepository {

    override suspend fun listRepositories(user: String) = flow {
        try {
            val listRepo = service.listRepos(user)
            emit(listRepo)
        } catch (ex : HttpException ) {
            throw RemoteException(ex.message ?: "Não foi possível buscar no momento")
        }
    }
}