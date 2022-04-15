package com.example

import com.example.model.ApiResponse
import com.example.model.Ninja
import com.example.repository.NEXT_PAGE
import com.example.repository.NinjaRepository
import com.example.repository.PREV_PAGE
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.koin.java.KoinJavaComponent.inject
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {

    private val repository: NinjaRepository by inject(NinjaRepository::class.java)

    @Test
    fun `access root endpoint, return correct result`() {
        withTestApplication(moduleFunction = Application::module) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Welcome to Boruto API!", response.content)
            }
        }
    }

    @Test
    fun `access get ninjas endpoint, query all pages, return list of ninjas`() {
        withTestApplication(moduleFunction = Application::module) {
            val pages = 1..5
            val ninjas = listOf(
                repository.page1,
                repository.page2,
                repository.page3,
                repository.page4,
                repository.page5,
            )

            pages.forEach { page ->
                handleRequest(HttpMethod.Get, "/boruto/ninjas?page=$page").apply {
                    val expected = ApiResponse(
                        success = true,
                        message = "OK",
                        prevPage = calculatePage(page)[PREV_PAGE],
                        nextPage = calculatePage(page)[NEXT_PAGE],
                        ninjas = ninjas[page - 1]
                    )
                    val actual = Json.decodeFromString<ApiResponse>(response.content.toString())
                    assertEquals(HttpStatusCode.OK, response.status())
                    assertEquals(expected, actual)
                }
            }
        }
    }

    @Test
    fun `access get ninjas endpoint, query invalid pages, return empty list`() {
        withTestApplication(moduleFunction = Application::module) {
            handleRequest(HttpMethod.Get, "/boruto/ninjas?page=akwokaw").apply {
                val actual = Json.decodeFromString<ApiResponse>(response.content.toString())
                assertEquals(HttpStatusCode.BadRequest, response.status())
                assertEquals("Invalid page number", actual.message.toString())
                assertEquals(false, actual.success)
                assertEquals(emptyList(), actual.ninjas)
            }
        }
    }

    @Test
    fun `access get ninjas endpoint, query non existing pages, return empty list`() {
        withTestApplication(moduleFunction = Application::module) {
            handleRequest(HttpMethod.Get, "/boruto/ninjas?page=6").apply {
                val actual = Json.decodeFromString<ApiResponse>(response.content.toString())
                assertEquals(HttpStatusCode.NotFound, response.status())
                assertEquals(false, actual.success)
                assertEquals("Ninja not found", actual.message.toString())
                assertEquals(emptyList(), actual.ninjas)

            }
        }
    }

    @Test
    fun `access search ninjas endpoint, query existing ninjas, return single ninja`() {
        val query = "sas"
        withTestApplication(moduleFunction = Application::module) {
            handleRequest(HttpMethod.Get, "/search/ninjas?query=$query").apply {
                val expected = decideGivenNinjas(query = query)
                val actual = Json.decodeFromString<ApiResponse>(response.content.toString())
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("OK", actual.message.toString())
                assertEquals(expected, actual.ninjas)

            }
        }
    }

    @Test
    fun `access search ninjas endpoint, query existing ninjas, return multiple ninjas`() {
        val query = "sa"
        withTestApplication(moduleFunction = Application::module) {
            handleRequest(HttpMethod.Get, "/search/ninjas?query=$query").apply {
                val expected = decideGivenNinjas(query = query)
                val actual = Json.decodeFromString<ApiResponse>(response.content.toString())
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("OK", actual.message.toString())
                assertEquals(expected, actual.ninjas)

            }
        }
    }

    @Test
    fun `access search ninjas endpoint, query an empty text, return empty list`() {
        val query = ""
        withTestApplication(moduleFunction = Application::module) {
            handleRequest(HttpMethod.Get, "/search/ninjas?query=$query").apply {
                val actual = Json.decodeFromString<ApiResponse>(response.content.toString())
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("OK", actual.message.toString())
                assertEquals(emptyList(), actual.ninjas)

            }
        }
    }

    @Test
    fun `access non exist endpoint, return not found`() {
        withTestApplication(moduleFunction = Application::module) {
            handleRequest(HttpMethod.Get, "/search/bambang").apply {
                assertEquals(HttpStatusCode.NotFound, response.status())
                assertEquals("Page Not Found", response.content.toString())

            }
        }
    }

    private fun decideGivenNinjas(query: String?): List<Ninja> {
        if (query.isNullOrBlank()) return emptyList()
        val ninjaList = mutableListOf<Ninja>()

        repository.ninjas.forEach { (_, ninja) ->
            ninja.forEach {
                if (it.name.contains(query, true)) {
                    ninjaList.add(it)
                }
            }
        }

        return ninjaList
    }

    private fun calculatePage(page: Int): Map<String, Int?> {
        var prevPage: Int? = page
        var nextPage: Int? = page

        nextPage = if (page in 1..4) nextPage?.plus(1)
        else null

        prevPage = if (page in 2..5) prevPage?.minus(1)
        else null

        return mapOf(
            PREV_PAGE to prevPage,
            NEXT_PAGE to nextPage
        )
    }
}