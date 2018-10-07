package org.styleru.hsemagazine.dataClasses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.styleru.hsemagazine.dataClasses.Article
import org.styleru.hsemagazine.dataClasses.Topic

data class Type (@SerializedName("type") @Expose val type: Topic,
                 @SerializedName("articles") val articles:List<Article>)