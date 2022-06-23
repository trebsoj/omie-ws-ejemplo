package com.bassolsenergia.task.Source.Bassols.COM.OMIE.ws.Consultes

import org.w3c.dom.Node
import java.util.*

class ConsultaEncolumnada5603(
    val cal: Calendar, val sesion: Int
) : AbstractConsultaEncolumnada<List<ConsultaEncolumnada5603Data>>("""
    <MensajeEjecucionConsulta>  
        <CodConsulta v="5603"/>  
        <Parametros>
            <Fec n="Fecha" v="${getFormatParam(cal)}"/>  
            <Ses n="Sesion" v="$sesion"/> 
        </Parametros>
    </MensajeEjecucionConsulta>
""".trimIndent()) {

    override fun getResult(n: Node): List<ConsultaEncolumnada5603Data> {
        val result = mutableListOf<ConsultaEncolumnada5603Data>()
        for (i in 0 until n.childNodes.length) {
            val nodeFila = n.childNodes.item(i)
            if ("Fila" == nodeFila.localName) {
                /* Descartem si la data entrada com a parametre no es la mateixa que la data del fitxer */
                if(getVofN(nodeFila.childNodes, "Fecha")!!.toString() != getFormatParam(cal)) continue

                result.add(ConsultaEncolumnada5603Data(
                    cal,
                    sesion,
                    getVofN(nodeFila.childNodes, "Hora")!!.toInt(),
                    getVofN(nodeFila.childNodes, "PrecioES")!!.toDouble(),
                    getVofN(nodeFila.childNodes, "PrecioPT")!!.toDouble(),
                    getVofN(nodeFila.childNodes, "Energia")!!.toDouble(),
                ))
            }
        }
        return result
    }
}

data class ConsultaEncolumnada5603Data(
    val cal:Calendar, val sesion:Int, val hora:Int, val precioES:Double, val precioPT:Double, val energia:Double,
)
