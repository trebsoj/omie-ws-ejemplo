import com.bassolsenergia.task.Source.Bassols.COM.OMIE.ws.Consultes.ConsultaDirectorioConsultas
import com.bassolsenergia.task.Source.Bassols.COM.OMIE.ws.Consultes.ConsultaEncolumnada5502
import com.bassolsenergia.task.Source.Bassols.COM.OMIE.ws.Consultes.ConsultaEncolumnada5535
import com.bassolsenergia.task.Source.Bassols.COM.OMIE.ws.Consultes.ConsultaEncolumnada5603
import es.omel.security.OmelSecuritySoftware
import es.omel.security.managers.ParametrizedSoftwareCertificateManager
import es.omel.webservices.OmelWebService
import java.util.*

fun main(args: Array<String>) {

    val certPath = "/path/to/file.p12"
    val certPassword = "****"
    val endpoint = "https://www.mercado.omie.es/jsiom/webServices/SIOMServiceRouter"

    // Inicializar conexión ws
    val ws = OmelWebService()
    OmelSecuritySoftware.setCertificateManager(
        ParametrizedSoftwareCertificateManager(certPath, certPassword)
    )
    ws.setEndPoint(endpoint)

    val cal = getCalendar(2022,6,16)

    // Ejemplo peticion listado consultas disponibles
    println("PETICION LISTADO CONSULTAS DISPONIBLES")
    ConsultaDirectorioConsultas().call(ws).forEach { println(it) }
    println();println();println()

    // Ejemplo precios mercada diario
    println("PETICION PRECIO MERCADO DIARIO")
    ConsultaEncolumnada5502(cal).call(ws).forEach { println(it) }
    println();println();println()

    // Ejemplo precios mercado intradiario (solo sesion 2)
    val sesion = 2
    println("PETICION PRECIO MERCADO INTRADIARIO (solo sesion 2)")
    ConsultaEncolumnada5603(cal, sesion).call(ws).forEach { println(it) }
    println();println();println()

    // Ejemplo datos
    println("PETICION DATOS LIQUIDACIÓN DEL AJUSTE DE LOS COSTES DE PRODUCCIÓN DE ENERGÍA ELÉCTRICA")
    ConsultaEncolumnada5535(cal).call(ws).forEach { println(it) }
}

fun getCalendar(year: Int, month: Int, day: Int): Calendar {
    val c = Calendar.getInstance()
    c[Calendar.YEAR] = year
    c[Calendar.MONTH] = month - 1
    c[Calendar.DAY_OF_MONTH] = day
    c[Calendar.HOUR_OF_DAY] = 0
    c[Calendar.MINUTE] = 0
    c[Calendar.SECOND] = 0
    return c
}
