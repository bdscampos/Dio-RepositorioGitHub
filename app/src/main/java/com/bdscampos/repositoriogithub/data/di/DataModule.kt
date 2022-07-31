package com.bdscampos.repositoriogithub.data.di

import android.util.Log
import com.bdscampos.repositoriogithub.data.repositories.RepoRepository
import com.bdscampos.repositoriogithub.data.repositories.RepoRepositoryImpl
import com.bdscampos.repositoriogithub.data.services.GitHubServices
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object DataModule {
    private const val OK_HTTP = "OkHttp"

    fun load(){
        loadKoinModules(networkModules() + repositoriesModule())
    }

    private fun networkModules(): Module {
        return module {
            single {
                val interceptor = HttpLoggingInterceptor {
                    Log.e(OK_HTTP, it )
                }
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }

            single {
                GsonConverterFactory.create(GsonBuilder().create())
            }

            single {
                createService<GitHubServices>(get(), get())
            }
        }
    }

    private fun repositoriesModule() : Module {
        return module {
            single<RepoRepository> { RepoRepositoryImpl(get())}
        }
    }

    private inline fun <reified T> createService(client: OkHttpClient, factory: GsonConverterFactory): T{
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(client)
            .addConverterFactory(factory)
            .build()
            .create(T::class.java)
    }


}