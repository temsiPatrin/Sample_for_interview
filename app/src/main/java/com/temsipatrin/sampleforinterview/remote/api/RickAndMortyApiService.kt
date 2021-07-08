package com.temsipatrin.sampleforinterview.remote.api

import com.temsipatrin.sampleforinterview.remote.models.CharacterResponse
import com.temsipatrin.sampleforinterview.remote.models.CharacterListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApiService {
    @GET("character/")
   suspend fun getListCharacters(@Query("page") pageNumber: Int): CharacterListResponse

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): CharacterResponse

    @GET("character/")
    suspend fun getCharactersByName(
        @Query("page") page: Int,
        @Query("name") name: String,
    ): CharacterListResponse
}