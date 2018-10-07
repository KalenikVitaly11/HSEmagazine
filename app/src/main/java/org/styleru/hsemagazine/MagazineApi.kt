package org.styleru.hsemagazine

import com.google.gson.GsonBuilder
import retrofit2.Response
import io.reactivex.Observable
import org.styleru.hsemagazine.dataClasses.Edition
import org.styleru.hsemagazine.dataClasses.LastRelease
import org.styleru.hsemagazine.dataClasses.NewEdition
import org.styleru.hsemagazine.dataClasses.Release
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MagazineApi {

    @GET("/getAllReleases")
    fun getAllReleases():Observable<Response<NewEdition>>

    @GET("/getLastRelease")
    fun getLastRelease():Observable<Response<LastRelease>>

    @GET("/getRelease")
    fun getReleaseById(@Query("id") id:Int):Observable<Response<Release>>

    @GET("/en/getAllReleases")
    fun getAllReleasesEn():Observable<Response<NewEdition>>

    @GET("/en/getLastRelease")
    fun getLastReleaseEn():Observable<Response<LastRelease>>

    @GET("/en/getRelease")
    fun getReleaseByIdEn(@Query("id") id:Int):Observable<Response<Release>>


    companion object {
        fun create(): MagazineApi {
            val gson = GsonBuilder()
                    .setLenient()
                    .create()

            val retrofit = Retrofit.Builder()
                    .baseUrl("http://bijournal.styleru.org/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            return  retrofit.create(MagazineApi::class.java)
        }
    }
}