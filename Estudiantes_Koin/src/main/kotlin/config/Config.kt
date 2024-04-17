package config

import org.lighthousegames.logging.logging
import java.nio.file.Files
import java.util.*
import kotlin.io.path.Path

private val logger = logging()

object Config {
    var databaseUrl: String = "jdbc:sqlite:productos.db"
        private set
    var databaseInitTables: Boolean = false
        private set
    var databaseInitData: Boolean = false
        private set
    var databaseInMemory: Boolean = false
        private set
    var storageData: String = "data"
        private set
    var cacheSize: Int = 5
        private set

    init {
        try {
            println("Cargando configuración........................")
            val properties = Properties()
            properties.load(ClassLoader.getSystemResourceAsStream("config.properties"))
            databaseUrl = properties.getProperty("database.url", this.databaseUrl)
            databaseInitTables =
                properties.getProperty("database.init.tables", this.databaseInitTables.toString()).toBoolean()
            databaseInitData =
                properties.getProperty("database.init.data", this.databaseInitData.toString()).toBoolean()
            databaseInMemory =
                properties.getProperty("database.inmemory", this.databaseInMemory.toString()).toBoolean()
            storageData = properties.getProperty("storage.data", this.storageData)
            cacheSize = properties.getProperty("cache.size", this.cacheSize.toString()).toInt()
            println("Configuración cargada correctamente........................")

            Files.createDirectories(Path(storageData))

        } catch (e: Exception) {
            println("Error cargando configuración: ${e.message}........................")
            println("Usando valores por defecto........................")
        }

    }
}