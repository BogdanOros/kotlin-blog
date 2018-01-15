import com.boros.BlogApplication
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(BlogApplication::class), webEnvironment = WebEnvironment.RANDOM_PORT)
class BasicAuthenticationTest {

    @LocalServerPort
    lateinit var port: String

    private val template = TestRestTemplate()

    @Test
    fun shouldFailWithNoHeaderProvider() {
        val response = template.getForEntity("http://localhost:$port/api/ping", Any::class.java)
        assertThat(response.statusCode).isEqualTo(HttpStatus.UNAUTHORIZED)
    }

    @Test
    fun shouldReturnSuccessWithHeaderProvided() {
        val authHeader = "Basic " + String(Base64.getEncoder().encode("admin:admin".toByteArray()))
        val headers = HttpHeaders().apply { this.put(HttpHeaders.AUTHORIZATION, listOf(authHeader)) }

        val response = template.exchange("http://localhost:$port/api/ping", HttpMethod.GET, HttpEntity<Any>(headers), Any::class.java)
        assertThat(response.statusCode).isEqualTo(HttpStatus.NO_CONTENT)
    }

    @Test
    fun shouldFailWithInvalidClientProvided() {
        val authHeader = "Basic " + String(Base64.getEncoder().encode("bad:bad".toByteArray()))
        val headers = HttpHeaders().apply { this.put(HttpHeaders.AUTHORIZATION, listOf(authHeader)) }

        val response = template.exchange("http://localhost:$port/api/ping", HttpMethod.GET, HttpEntity<Any>(headers), Any::class.java)
        assertThat(response.statusCode).isEqualTo(HttpStatus.UNAUTHORIZED)
    }
}