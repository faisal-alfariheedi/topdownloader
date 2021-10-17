package com.example.topdownloader

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.io.Serializable

@Root(name = "entry", strict = false)
class Entry @JvmOverloads constructor(


    @field:Element(name = "im:name")
    @param:Element(name = "im:name")
    var title: String? = null,

    @field:Element(name = "updated")
    @param:Element(name = "updated")
    var updated: String? = null

) : Serializable {


}

