package com.bassolsenergia.task.Source.Bassols.COM.OMIE.ws.Consultes

import es.omel.webservices.OmelWebService
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.text.SimpleDateFormat
import java.util.*

abstract class AbstractConsulta<T:Any>(val params:String?) {

    companion object{
        fun getFormatParam(cal:Calendar):String {
            return return SimpleDateFormat("yyyy-MM-dd").format(cal.time)
        }
    }

    fun call(ws:OmelWebService):T {
        ws.setService(getService())
        if(params != null) ws.setParam(params)
        val result = ws.invoke()
        // enviar a getResult elemento EjecucionConsultaEncolumnada.RespuestaEjecucionConsultaEncolumnada
        return getResult(result.returnElement.childNodes.item(0).childNodes.item(0))
    }

    abstract fun getResult(n: Node):T
    abstract fun getService():String

    fun getVofLocalName(l:NodeList, localName:String):String?{
        for (j in 0 until l.length) {
            if(getVofLocalName(l.item(j), localName) != null)
                return getVofLocalName(l.item(j), localName)
        }
        return null
    }
    fun getVofLocalName(item:Node, localName:String):String?{
        if(item.localName.uppercase()==localName.uppercase())
            return item.attributes.getNamedItem("v").nodeValue
        return null
    }

    fun getVofN(l:NodeList, n:String):String?{
        for (j in 0 until l.length) {
            if(getVofN(l.item(j), n) != null)
                return getVofN(l.item(j), n)
        }
        return null
    }
    fun getVofN(item:Node, n:String):String?{
        if(item.attributes.getNamedItem("n").nodeValue.uppercase()==n.uppercase())
            return item.attributes.getNamedItem("v").nodeValue
        return null
    }

    fun getV(item:Node):String?{
        return item.attributes.getNamedItem("v").nodeValue
    }
}
