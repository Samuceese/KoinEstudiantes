package di

import cache.Cache
import cache.CacheImpl
import database.service.SqlDeLightManager
import estudiantes.models.Estudiante
import org.koin.dsl.module
import estudiantes.repositories.EstudiantesRepository
import estudiantes.repositories.EstudiantesRepositoryImpl
import estudiantes.services.EstudiantesService


val estudiantesModule= module {
    factory<Cache<Long, Estudiante>> { CacheImpl(getProperty("cache.size")) }

    single<SqlDeLightManager >{
        SqlDeLightManager(
            getProperty("database.url"),
            getProperty("database.inmemory"),
            getProperty("database.init.data")
        )
    }

    single<EstudiantesRepository> { EstudiantesRepositoryImpl(get()) }



    factory<EstudiantesService> { EstudiantesServiceImpl(get(),get()) }


}
