package com.diegolima.marvelheroes.core

import com.diegolima.marvelheroes.core.network.AuthInterceptor
import com.diegolima.marvelheroes.core.network.providerHttpClient
import com.diegolima.marvelheroes.core.network.providerMarvelApi
import com.diegolima.marvelheroes.core.network.providerRetrofit
import com.diegolima.marvelheroes.domain.network.MarvelCharacterRepository
import com.diegolima.marvelheroes.ui.characterdetail.CharacterDetailViewModel
import com.diegolima.marvelheroes.ui.characterlist.CharacterListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkApiModule = module {
    factory { AuthInterceptor() }
    factory { providerHttpClient(get()) }
    single { providerRetrofit(get()) }
}

val networkServiceModule = module {
    factory { providerMarvelApi(get()) }
}

val repositoryModule = module {
    factory { MarvelCharacterRepository(get()) }
}

val viewModelModule = module {
    viewModel { CharacterListViewModel(get()) }
    viewModel { CharacterDetailViewModel(get()) }
}