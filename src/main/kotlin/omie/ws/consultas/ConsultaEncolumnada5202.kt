package com.bassolsenergia.task.Source.Bassols.COM.OMIE.ws.Consultes

import org.w3c.dom.Node
import java.util.*

class ConsultaEncolumnada5502(
    val cal: Calendar
) : AbstractConsultaEncolumnada<List<ConsultaEncolumnada5502Data>>("""
    <MensajeEjecucionConsulta>  
        <CodConsulta v="5202"/>  
        <Parametros>
            <Fec n="FechaCasacion" v="${getFormatParam(cal)}"/>  
        </Parametros>
    </MensajeEjecucionConsulta>
""".trimIndent()) {

    override fun getResult(n: Node): List<ConsultaEncolumnada5502Data> {
        val result = mutableListOf<ConsultaEncolumnada5502Data>()
        for (i in 0 until n.childNodes.length) {
            val nodeFila = n.childNodes.item(i)
            if ("Fila" == nodeFila.localName) {
                result.add(ConsultaEncolumnada5502Data(
                    cal,
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

data class ConsultaEncolumnada5502Data(
    val cal:Calendar, val hora:Int, val precioES:Double, val precioPT:Double, val energia:Double,
)
