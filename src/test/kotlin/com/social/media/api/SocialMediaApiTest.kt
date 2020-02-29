package kotlin.com.social.media.api

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest
@AutoConfigureWebTestClient(timeout = "100000")
 internal class SocialMediaApiTest(@Autowired val client: WebTestClient) {

    @Test
    fun givenMultipleCallsToSocialAccounts_UrlsCalled_AlwaysReturnHttpIsSuccessful() {
        client.get().uri("/")
                .exchange().expectStatus().isOk
                .expectBody()
                .jsonPath("result").isNotEmpty
    }

}