package com.bdscampos.repositoriogithub.data.services

import com.bdscampos.repositoriogithub.data.model.Repo
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubServices {
    @GET("users/{user}/repos")
    suspend fun listRepos(@Path("user") user: String) : List<Repo>
}