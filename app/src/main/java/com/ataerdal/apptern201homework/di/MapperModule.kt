package com.ataerdal.apptern201homework.di

import com.ataerdal.apptern201homework.domain.mapper.ProductDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object MapperModule {

    @Provides
    @Singleton
    fun provideProductDtoMapper(): ProductDtoMapper = ProductDtoMapper()
}