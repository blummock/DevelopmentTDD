package com.github.johnnysc.practicetdd

import java.util.concurrent.CancellationException

interface ListRepository {

    suspend fun load(): LoadResult

    class CloudFirst(
        private val cacheDataSource: CacheDataSource,
        private val cloudDataSource: CloudDataSource,
        private val handleError: HandleError
    ) : ListRepository {
        override suspend fun load(): LoadResult {
            try {
                val data = cloudDataSource.load()
                cacheDataSource.save(data)
                return LoadResult.Success(data)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                val cache = cacheDataSource.load()
                return if (cache.isEmpty()) LoadResult.Error(handleError.handle(e)) else LoadResult.Success(cache)
            }
        }
    }

    class CacheFirst(
        private val cacheDataSource: CacheDataSource,
        private val cloudDataSource: CloudDataSource,
        private val handleError: HandleError
    ) : ListRepository {
        override suspend fun load(): LoadResult {
            val cache = cacheDataSource.load()
            if (cache.isEmpty()) {
                try {
                    val data = cloudDataSource.load()
                    cacheDataSource.save(data)
                    return LoadResult.Success(data)
                } catch (e: CancellationException) {
                    throw e
                } catch (e: Exception) {
                    val message = handleError.handle(e)
                    return LoadResult.Error(message)
                }
            } else {
                return LoadResult.Success(cache)
            }
        }
    }
}