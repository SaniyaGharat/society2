package com.society.app.di

import com.society.app.data.repository.AuthRepository
import com.society.app.data.repository.ComplaintRepository
import com.society.app.data.repository.AnnouncementRepository
import com.society.app.data.repository.ResidentRepository
import com.society.app.data.repository.VisitorRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): retrofit2.Retrofit {
        return retrofit2.Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideSocietyApi(retrofit: retrofit2.Retrofit): com.society.app.data.api.SocietyApi {
        return retrofit.create(com.society.app.data.api.SocietyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(): AuthRepository {
        return AuthRepository()
    }

    @Provides
    @Singleton
    fun provideComplaintRepository(api: com.society.app.data.api.SocietyApi): ComplaintRepository {
        return ComplaintRepository(api)
    }

    @Provides
    @Singleton
    fun provideAnnouncementRepository(): AnnouncementRepository {
        return AnnouncementRepository()
    }

    @Provides
    @Singleton
    fun provideResidentRepository(): ResidentRepository {
        return ResidentRepository()
    }

    @Provides
    @Singleton
    fun provideVisitorRepository(): VisitorRepository {
        return VisitorRepository()
    }
}
