package it.lysz210.akasha.capacnan.quipus.credentials

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatCode
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class QuipucamayocTest {

    private lateinit var strategy: EncryptionStrategy
    private lateinit var quipucamayoc: Quipucamayoc

    @BeforeEach
    fun setUp() {
        strategy = NoopEncryptionStrategy()
        quipucamayoc = Quipucamayoc(strategy)
    }

    private fun createSecredData(): SecretData {
        return secretData {
            serviceId = "service"
            basic = basic {
                username = "me"
                password = "secret"
            }
        }
    }

    @Test
    fun `should fail tying without no credentials`() {
        val invalidData = secretData {
            serviceId = "service"
        }

        assertThatCode {
            quipucamayoc.tie("me", invalidData)
        }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageStartingWith("SecretData must have one credential type")
    }

    @Test
    fun `should create the CredentialQuipu`() {
        val quipu = quipucamayoc.tie("me", createSecredData())
        assertThat(quipu)
            .hasFieldOrPropertyWithValue("security.algo", EncryptionAlgo.NONE)
        assertThat(quipu.encryptedPayload)
            .isNotEmpty
    }

    @Test
    @Order(2)
    fun `should return the SecretData`() {
        val quipu = credentialQuipu {
            userId = "user"
            encryptedPayload = strategy.encrypt(createSecredData())
        }
        val secret = this.quipucamayoc.untie(quipu)
        val basic = secret.basic
        assertThat(basic)
            .isNotNull
            .hasFieldOrPropertyWithValue("username", "me")
            .hasFieldOrPropertyWithValue("password", "secret")
    }

}