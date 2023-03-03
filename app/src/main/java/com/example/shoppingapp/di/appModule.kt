package com.example.shoppingapp.di

import com.example.shoppingapp.data.db.provideDB
import com.example.shoppingapp.data.db.provideToDoDao
import com.example.shoppingapp.data.nework.buildOkHttpClient
import com.example.shoppingapp.data.nework.provideGsonConverterFactory
import com.example.shoppingapp.data.nework.provideProductApiService
import com.example.shoppingapp.data.nework.provideProductRetrofit
import com.example.shoppingapp.data.preference.PreferenceManager
import com.example.shoppingapp.data.repository.DefaultProductRepository
import com.example.shoppingapp.data.repository.ProductRepository
import com.example.shoppingapp.domain.product.*
import com.example.shoppingapp.domain.product.GetProductItemUseCase
import com.example.shoppingapp.domain.product.GetProductListUseCase
import com.example.shoppingapp.domain.product.OrderProductItemUseCase
import com.example.shoppingapp.presentation.detail.ProductDetailViewModel
import com.example.shoppingapp.presentation.list.ProductListViewModel
import com.example.shoppingapp.presentation.main.MainViewModel
import com.example.shoppingapp.presentation.profile.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    //viewmdels
    viewModel { MainViewModel() }
    viewModel { ProductListViewModel(get()) }
    viewModel { ProfileViewModel(get() ,get() ,get()) }
    viewModel { (productId: Long) -> ProductDetailViewModel(productId, get() , get()) }

    //coroutine Dispathcer
    single { Dispatchers.Main }
    single { Dispatchers.IO }

    //UseCases
    factory { GetProductItemUseCase(get()) }
    factory { GetProductListUseCase(get()) }
    factory { OrderProductItemUseCase(get()) }
    factory { DeleteOrderedProductListUseCase(get()) }
    factory { GetOrderedProductListUseCase(get()) }

    //repositories
    single<ProductRepository> { DefaultProductRepository(get(), get() , get()) }



    single { provideGsonConverterFactory() }

    single { buildOkHttpClient() }

    single { provideProductRetrofit(get(), get()) }

    single { provideProductApiService(get()) }

    single { PreferenceManager(androidContext()) }

    // Database
    single { provideDB(androidApplication()) }
    single { provideToDoDao(get()) }


}