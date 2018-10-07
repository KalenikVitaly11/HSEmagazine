package org.styleru.hsemagazine.dataClasses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Article (@SerializedName("name") @Expose val name:String,
                    @SerializedName("author") @Expose val author:String,
                    @SerializedName("link") @Expose val link:String)