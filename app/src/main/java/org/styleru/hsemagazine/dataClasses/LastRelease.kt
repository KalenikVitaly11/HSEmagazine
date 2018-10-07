package org.styleru.hsemagazine.dataClasses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LastRelease(@SerializedName("release") @Expose val release:Edition)