package com.sofia.observerpattern.data.remote

import com.sofia.observerpattern.app.Either
import com.sofia.observerpattern.app.ErrorApp
import com.sofia.observerpattern.app.left
import com.sofia.observerpattern.app.right
import com.sofia.observerpattern.domain.Hero
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

class HeroRemoteDataSource {
    private val baseUrl = "https://dam.sitehub.es/api-curso/superheroes/"

    private val interceptor = HttpLoggingInterceptor()
    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    private val service: HeroeApiService = retrofit.create(HeroeApiService::class.java)

    suspend fun findAllSuperHeroes(): Either<ErrorApp, List<Hero>> {
        try {
            val response: Response<List<HeroApiModel>> = service.finAllHeroes()

            return if (response.isSuccessful) {
                val heroes = response.body()!!.map {
                    it.toModel()
                }
                heroes.right()
            } else {
                ErrorApp.UnknownError.left()
            }

        } catch (ex: TimeoutException) {
            return ErrorApp.UnknownError.left()
        } catch (ex: UnknownHostException) {
            return ErrorApp.UnknownError.left()
        } catch (ex: Exception) {
            return ErrorApp.UnknownError.left()
        }
    }
}