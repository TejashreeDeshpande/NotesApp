package com.example.notesapp.di

import android.util.Log
import androidx.room.Room
import com.example.notesapp.data.local.NotesDatabase
import com.example.notesapp.data.repository.NotesRepositoryImpl
import com.example.notesapp.domain.repository.NotesRepository
import com.example.notesapp.ui.viewmodel.NotesViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import kotlin.jvm.java

private val json = Json { ignoreUnknownKeys = true }

val networkModule = module {

    single {
        HttpLoggingInterceptor { message ->
            Log.d("OkHttp", message)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single {
        val contentType = "application/json".toMediaType()

        Retrofit.Builder()
            .baseUrl("https://jsonmock.hackerrank.com/")
            .client(get())
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

}

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            NotesDatabase::class.java,
            "notes_db"
        ).build()
    }

    single {
        get<NotesDatabase>().noteDao()
    }
}

val repositoryModule = module {

    single<NotesRepository> {
        NotesRepositoryImpl(get())
    }
}

val viewModelModule = module {

    viewModel {
        NotesViewModel(get())
    }
}