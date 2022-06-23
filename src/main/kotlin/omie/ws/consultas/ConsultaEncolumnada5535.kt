package com.bassolsenergia.task.Source.Bassols.COM.OMIE.ws.Consultes

import org.w3c.dom.Node
import java.util.*

// version Última: 99
// version 1: 1
// version 2: 2
// version 3: 3
class ConsultaEncolumnada5535(
    val cal: Calendar, val version: Int = 99
) : AbstractConsultaEncolumnada<List<ConsultaEncolumnada5535Data>>("""
    <MensajeEjecucionConsulta>  
        <CodConsulta v="5535"/>  
        <Parametros>
            <Txt n="Version" v="$version"/> 
            <Fec n="Fecha" v="${getFormatParam(cal)}"/>  
        </Parametros>
    </MensajeEjecucionConsulta>
""".trimIndent()) {

    override fun getResult(n: Node): List<ConsultaEncolumnada5535Data> {
        val result = mutableListOf<ConsultaEncolumnada5535Data>()
        for (i in 0 until n.childNodes.length) {
            val nodeFila = n.childNodes.item(i)
            if ("Fila" == nodeFila.localName) {
                result.add(ConsultaEncolumnada5535Data(
                    cal,
                    getVofN(nodeFila.childNodes, "perido")!!.toInt(),
                    getVofN(nodeFila.childNodes, "version")!!.toInt(),
                    getVofN(nodeFila.childNodes, "impAjuPro")!!.toDouble(),
                    getVofN(nodeFila.childNodes, "impRenCon")!!.toDouble(),
                    getVofN(nodeFila.childNodes, "impcosDerLP")!!.toDouble(),
                    getVofN(nodeFila.childNodes, "impRenDia")!!.toDouble(),
                    getVofN(nodeFila.childNodes, "impRenAdi")!!.toDouble(),
                    getVofN(nodeFila.childNodes, "impAjuUad")!!.toDouble(),
                    getVofN(nodeFila.childNodes, "eneUad")!!.toDouble(),
                ))
            }
        }
        return result
    }
}

data class ConsultaEncolumnada5535Data(
    val cal:Calendar, val perido:Int, val version:Int, val impAjuPro:Double, val impRenCon:Double, val impcosDerLP:Double,
    val impRenDia:Double, val impRenAdi:Double, val impAjuUad:Double, val eneUad:Double
)
