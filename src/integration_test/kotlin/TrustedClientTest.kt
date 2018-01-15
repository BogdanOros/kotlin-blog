import com.boros.BlogApplication
import com.boros.configuration.security.client.ClientDetail
import com.boros.configuration.security.client.ClientNotFoundException
import com.boros.configuration.security.client.ClientService
import com.boros.configuration.security.client.TrustedClientConfiguration
import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(BlogApplication::class), webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TrustedClientTest {

    @Autowired
    lateinit var clientService: ClientService

    @Autowired
    @Qualifier(TrustedClientConfiguration.MOBILE)
    lateinit var mobileDetail: ClientDetail

    @Autowired
    @Qualifier(TrustedClientConfiguration.WEB)
    lateinit var webDetail: ClientDetail

    @Autowired
    @Qualifier(TrustedClientConfiguration.ADMIN)
    lateinit var adminDetail: ClientDetail

    @Value("\${clients.mobile.id}")
    lateinit var mobileId: String

    @Value("\${clients.web.id}")
    lateinit var webId: String


    @Value("\${clients.admin.id}")
    lateinit var adminId: String

    @Test
    fun shouldInitializeClientService() {
        Assertions.assertThat(clientService).isNotNull()
    }

    @Test
    fun shouldFindClientById() {
        val client = clientService.loadClientById(adminId)

        Assertions.assertThat(client).isNotNull()
        Assertions.assertThat(client.id).isEqualTo(adminId)
    }

    @Test
    fun shouldThrowExceptionIfClientIdIsIncorrect() {
        val exception = Assertions.catchThrowable { clientService.loadClientById("bad") }

        Assertions.assertThat(exception).isInstanceOf(ClientNotFoundException::class.java)
        Assertions.assertThat(exception.message).contains("bad")
    }

    @Test
    fun shouldInitializeMobileClientDetail() {
        Assertions.assertThat(mobileDetail).isNotNull()
        Assertions.assertThat(mobileDetail.id).isEqualTo(mobileId)
        Assertions.assertThat(mobileDetail.secret).isNotNull()
    }

    @Test
    fun shouldInitializeWebClientDetail() {
        Assertions.assertThat(webDetail).isNotNull()
        Assertions.assertThat(webDetail.id).isEqualTo(webId)
        Assertions.assertThat(webDetail.secret).isNotNull()
    }

    @Test
    fun shouldInitializeAdminClientDetail() {
        Assertions.assertThat(adminDetail).isNotNull()
        Assertions.assertThat(adminDetail.id).isEqualTo(adminId)
        Assertions.assertThat(adminDetail.secret).isNotNull()
    }

}