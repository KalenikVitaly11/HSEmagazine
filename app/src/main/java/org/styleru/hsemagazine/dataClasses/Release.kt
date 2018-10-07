package org.styleru.hsemagazine.dataClasses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Release(@SerializedName("edition") @Expose val edition: Edition,
                   @SerializedName("types") @Expose val types: List<Type>)
