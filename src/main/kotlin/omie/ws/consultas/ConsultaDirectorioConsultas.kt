package com.bassolsenergia.task.Source.Bassols.COM.OMIE.ws.Consultes

import org.w3c.dom.Node

class ConsultaDirectorioConsultas:AbstractConsulta<List<ConsultaDirectorioConsultasSeccionData>>(null) {
    override fun getResult(n: Node): List<ConsultaDirectorioConsultasSeccionData> {
        val result = mutableListOf<ConsultaDirectorioConsultasSeccionData>()
        for (i in 0 until n.childNodes.length) {
            val nSeccio = n.childNodes.item(i)
            if ("Seccion" == nSeccio.localName) {
                val consultas = mutableListOf<ConsultaDirectorioConsultasConsultaData>()
                for (j in 0 until nSeccio.childNodes.length) {
                    val nConsultas = nSeccio.childNodes.item(j)
                    consultas.add(ConsultaDirectorioConsultasConsultaData(
                        getVofLocalName(nConsultas.childNodes, "CodConsulta").toString(),
                        getVofLocalName(nConsultas.childNodes, "Titulo").toString(),
                        getVofLocalName(nConsultas.childNodes, "TipoConsulta").toString(),
                    ))
                }
                result.add(ConsultaDirectorioConsultasSeccionData(getV(nSeccio)!!, consultas))
            }
        }
        return result
    }

    override fun getService(): String {
        return "ServicioConsultaDirectorioConsultas"
    }
}

data class ConsultaDirectorioConsultasSeccionData(
    val titulo:String, val consultas:List<ConsultaDirectorioConsultasConsultaData>
)

data class ConsultaDirectorioConsultasConsultaData(
    val codConsulta:String, val titulo:String, val tipoConsulta:String
)
