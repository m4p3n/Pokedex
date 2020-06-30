package it.mmilani.pokedex.arch

import android.app.Application
import com.google.gson.Gson
import it.mmilani.pokedex.BuildConfig
import it.mmilani.pokedex.api.PokemonApi
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

val networkModule = module {
//        factory { provideCache(get()) }
        factory { provideOkHttpClient() }
        single { provideRetrofit(get()) }

        //apis
        factory { providePokemonApi(get()) }

     }

fun provideRetrofit(okHttpClient : OkHttpClient) : Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}

//fun provideCache(application: Application) : Cache {
//    val cacheSize = (5 * 1024 * 1024).toLong()
//    val httpCacheDirectory = File(application.cacheDir, "http-cache")
//    return Cache(httpCacheDirectory, cacheSize)
//}


fun provideOkHttpClient() : OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.level = if(BuildConfig.FLAVOR == "pokeTesting")
                        HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.BASIC

    val httpClient = OkHttpClient.Builder()
    httpClient.apply {
//        this.cache(cache)
        this.addInterceptor(logging)
        this.connectTimeout(30, TimeUnit.SECONDS)
        this.readTimeout(30, TimeUnit.SECONDS)
    }
    return httpClient.build()
}

fun providePokemonApi(retrofit: Retrofit) : PokemonApi = retrofit.create(PokemonApi::class.java)


