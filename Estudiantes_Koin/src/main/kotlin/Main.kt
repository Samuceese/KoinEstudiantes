
import di.estudiantesModule
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.context.GlobalContext.startKoin
import org.koin.fileProperties
import org.koin.test.verify.verify

@OptIn(KoinExperimentalAPI::class)
fun main() {
    println("-----------Koin-----------")
    startKoin {
        fileProperties("/config.properties")
        EstudiantesModule.verify(extraTypes = listOf(kotlin.Boolean::class))
        modules(estudiantesModule)
    }

    val app = EstudiantesApp()
    app.run()



}