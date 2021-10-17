package com.example.topdownloader

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root
import java.io.Serializable

@Root(name = "entry", strict = false)
class Entry @JvmOverloads constructor(


    @field:Element(name = "name")
    @param:Element(name = "name")
    @Namespace(prefix="im")
    var name: String? = null,

    @field:Element(name = "releaseDate")
    @param:Element(name = "releaseDate")
    @Namespace(prefix="im")
    var updated: String? = null

) : Serializable {


}

