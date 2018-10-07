package org.styleru.hsemagazine.dataClasses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Edition(@SerializedName("id") @Expose val id: String,
                   @SerializedName("name") @Expose val name: String,
                   @SerializedName("year") @Expose val year: String,
                   @SerializedName("link") @Expose val link: String,
                   @SerializedName("pict") @Expose val image: String)