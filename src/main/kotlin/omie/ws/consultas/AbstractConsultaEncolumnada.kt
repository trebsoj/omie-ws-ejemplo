package com.bassolsenergia.task.Source.Bassols.COM.OMIE.ws.Consultes

abstract class AbstractConsultaEncolumnada<T:Any>(params:String?): AbstractConsulta<T>(params) {

    override fun getService():String {
        return "ServicioEjecucionConsultaEncolumnada"
    }
}
