package com.soheibbettahar.yobi_test.di

import com.soheibbettahar.yobi_test.data.repository.UserRepository
import com.soheibbettahar.yobi_test.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository


}