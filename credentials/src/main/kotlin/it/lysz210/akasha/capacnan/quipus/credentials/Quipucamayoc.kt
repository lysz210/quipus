package it.lysz210.akasha.capacnan.quipus.credentials

class Quipucamayoc (
    private val encryptionStrategy: EncryptionStrategy
) {
    protected fun validate(data: SecretData) {
        require(data.typeCase != SecretData.TypeCase.TYPE_NOT_SET) {
            "SecretData must have one credential type (oauth2, oauth1, basic, or api_key)"
        }
    }

    fun tie(user: String, data: SecretData): CredentialQuipu {
        this.validate(data)

        return credentialQuipu {
            provider = user
            security = encryptionStrategy.getMeta()
            encryptedPayload = encryptionStrategy.encrypt(data)
        }
    }

    fun untie(envelope: CredentialQuipu): SecretData {
        val data = encryptionStrategy.decrypt(envelope.encryptedPayload)
        this.validate(data)
        return data
    }
}