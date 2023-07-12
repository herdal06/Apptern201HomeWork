package com.ataerdal.apptern201homework.di

import com.ataerdal.apptern201homework.data.repository.product.ProductRepository
import com.ataerdal.apptern201homework.data.repository.product.ProductRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
abstract class RepositoryModule {

    @Binds
    abstract fun bindProductRepository(productRepositoryImpl: ProductRepositoryImpl): ProductRepository
}