package com.example.tracker_data.repository

import com.example.tracker_data.remote.OpenFoodApi
import com.example.tracker_data.remote.malformedFoodResponse
import com.example.tracker_data.remote.validFoodResponse
import com.example.tracker_domain.repository.TrackerRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class TrackerRepositoryImplTest {

    private lateinit var repository: TrackerRepository

    // necessary to all our test in order to avoid real api calls
    private lateinit var mockWebServer: MockWebServer
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var api: OpenFoodApi

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        // for fake api requests we should not need more than 1 sec
        okHttpClient = OkHttpClient.Builder()
            .writeTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .connectTimeout(1, TimeUnit.SECONDS)
            .build()

        // our fake api that make use of local mock web server
        api = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(mockWebServer.url("/")) // not very important in this case
            .build()
            .create(OpenFoodApi::class.java)

        repository = TrackerRepositoryImpl(
            dao = mockk(relaxed = true),
            api = api
        )
    }

    @After
    fun tearDown() {
        // have a new one every time
        mockWebServer.shutdown()
    }

    // we use runBlocking because of it is the recommended way in test cases
    // because the tests should not run in async way
    @Test
    fun `Search food, valid response, returns results`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(validFoodResponse) // our success response
        )
        val result = repository.searchFood("banana", 1, 40)

        // the isSuccess from our wrapper class
        assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun `Search food, invalid response, returns failure`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(403)
                .setBody(validFoodResponse)
        )
        val result = repository.searchFood("banana", 1, 40)
        // comes from our wrapper
        assertThat(result.isFailure).isTrue()
    }

    @Test
    fun `Search food, malformed response, returns failure`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(malformedFoodResponse)
        )
        val result = repository.searchFood("banana", 1, 40)

        assertThat(result.isFailure).isTrue()
    }
}