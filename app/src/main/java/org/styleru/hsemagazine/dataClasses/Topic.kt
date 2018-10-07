package org.styleru.hsemagazine.dataClasses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Topic (@SerializedName("id") @Expose val id:String,
                  @SerializedName("name") @Expose val name:String)