package org.styleru.hsemagazine.dataClasses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.styleru.hsemagazine.dataClasses.Edition

data class NewEdition (@SerializedName("releases") @Expose val releases:List<Edition>)