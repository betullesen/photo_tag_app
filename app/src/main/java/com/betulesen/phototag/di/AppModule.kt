package com.betulesen.phototag.di

import android.content.Context
import androidx.room.Dao
import androidx.room.Room
import com.betulesen.phototag.R
import com.betulesen.phototag.api.ApiServises
import com.betulesen.phototag.repository.PhotoTagRepository
import com.betulesen.phototag.roomDb.PhotoTagDao
import com.betulesen.phototag.roomDb.PhotoTagDatabase
import com.betulesen.phototag.util.constants.Constants.BASE_URL
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRoomDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,PhotoTagDatabase::class.java,"photoTagDB"
    ).build()

    @Singleton
    @Provides
    fun injectDao(database : PhotoTagDatabase) = database.photoTagDao()

    @Singleton
    @Provides
    fun injectRetrofitApi() : ApiServises{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiServises::class.java)
    }

    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context) = Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
        )

    @Singleton
    @Provides
    fun injectRepository(dao : PhotoTagDao , api : ApiServises) = PhotoTagRepository(dao,api)
}