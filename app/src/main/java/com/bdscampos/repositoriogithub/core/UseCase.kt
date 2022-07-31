package com.bdscampos.repositoriogithub.core

import kotlinx.coroutines.flow.Flow

abstract class UseCase<Param, Source> {
    abstract suspend fun execute(param: Param): Flow<Source>

    open suspend operator fun invoke(param: Param) = execute(param)

    abstract class NoParam<Source> : UseCase<None, Flow<Source>>() {
        abstract suspend fun execute() : Flow<Source>

        final override suspend fun execute(param: None) = throw UnsupportedOperationException()
    }

    abstract class NoSource<Params> : UseCase<Params, Unit>(){
        override suspend fun invoke(param: Params): Flow<Unit> = execute(param)
    }

    object None
}