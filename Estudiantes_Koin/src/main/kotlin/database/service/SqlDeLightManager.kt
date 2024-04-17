package database.service

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import database.DatabaseQueries
import database.initDemoEstudiantes
import org.example.database.AppDatabase

class SqlDeLightManager(
    private val databaseUrl: String,
    private val databaseInMemory: String,
    private val databaseInitData: String,
) {
    val databaseQueries: DatabaseQueries by lazy { initQueries() }

    init {
        println("Inicializando el gestor de Bases de Datos con SQLDelight")
        initialize()
    }

    private fun initQueries(): DatabaseQueries {

        return if (databaseInMemory.toBoolean()) {
            println("SqlDeLightClient - InMemory")
            JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        } else {
            println("SqlDeLightClient - File: ${databaseUrl}")
            JdbcSqliteDriver(databaseUrl)
        }.let { driver ->
            println("Creando Tablas (si es necesario)")
            AppDatabase.Schema.create(driver)
            AppDatabase(driver)
        }.databaseQueries

    }

    fun initialize() {
        if (databaseInitData.toBoolean()) {
            removeAllData()
            initDataExamples()
        }
    }

    private fun initDataExamples() {
        println("Iniciando datos de ejemplo")
        databaseQueries.transaction {
            demoClientes()
        }
    }



    private fun demoClientes() {
        println("Datos de ejemplo de Estudiantes")
        initDemoEstudiantes().forEach {
            databaseQueries.insertEstudiante(
                nombre = it.nombre,
                created_at = it.createAt.toString(),
            )
        }
    }


    private fun removeAllData() {
        println("SqlDeLightClient.removeAllData()")
        databaseQueries.transaction {
            databaseQueries.removeAllEstudiante()
        }
    }
}