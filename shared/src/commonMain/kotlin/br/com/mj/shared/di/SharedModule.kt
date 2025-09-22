package br.com.mj.shared.di

import br.com.mj.shared.data.mapper.EpisodeMapper
import br.com.mj.shared.data.mapper.ShowMapper
import br.com.mj.shared.data.remote.TvMazeApi
import br.com.mj.shared.data.remote.TvMazeApiImpl
import br.com.mj.shared.data.repository.EpisodeRepositoryImpl
import br.com.mj.shared.data.repository.PreferencesRepositoryImpl
import br.com.mj.shared.data.repository.ShowRepositoryImpl
import br.com.mj.shared.infra.domain.repository.EpisodeRepository
import br.com.mj.shared.infra.domain.repository.PreferencesRepository
import br.com.mj.shared.infra.domain.repository.ShowRepository
import br.com.mj.shared.infra.domain.usecase.GetSeasonsUseCase
import br.com.mj.shared.infra.domain.usecase.GetSeasonsUseCaseImpl
import br.com.mj.shared.infra.domain.usecase.GetShowsUseCase
import br.com.mj.shared.infra.domain.usecase.GetShowsUseCaseImpl
import br.com.mj.shared.infra.domain.usecase.MarkEpisodeWatchedUseCase
import br.com.mj.shared.infra.domain.usecase.MarkEpisodeWatchedUseCaseImpl
import br.com.mj.shared.infra.network.HttpClientFactory
import br.com.mj.shared.infra.settings.SettingsFactory
import org.koin.dsl.module

val sharedModule = module {

    // singleton instances
    single { HttpClientFactory.create() }
    single<TvMazeApi> { TvMazeApiImpl(get()) }
    single { ShowMapper() }
    single { EpisodeMapper() }
    single<ShowRepository> { ShowRepositoryImpl(get(), get()) }
    single<EpisodeRepository> { EpisodeRepositoryImpl(get(), get(), get()) }
    single<PreferencesRepository> { PreferencesRepositoryImpl(SettingsFactory.create()) }

    // factory instances
    factory<GetShowsUseCase> { GetShowsUseCaseImpl(get()) }
    factory<GetSeasonsUseCase> { GetSeasonsUseCaseImpl(get()) }
    factory<MarkEpisodeWatchedUseCase> { MarkEpisodeWatchedUseCaseImpl(get()) }
}