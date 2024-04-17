package estudiantes.errors

sealed class EstudianteError(val messaage:String) {
    class EstudianteNoEncontrado(message: String) : EstudianteError(message)
    class EstudianteNoValido(message: String) : EstudianteError(message)
    class EstudianteNoActualizado(message: String) : EstudianteError(message)
    class EstudianteNoEliminado(message: String) : EstudianteError(message)
}