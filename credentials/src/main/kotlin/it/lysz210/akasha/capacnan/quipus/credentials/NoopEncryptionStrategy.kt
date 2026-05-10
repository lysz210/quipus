package it.lysz210.akasha.capacnan.quipus.credentials

import com.google.protobuf.ByteString

class NoopEncryptionStrategy: EncryptionStrategy {

    override fun encrypt(payload: SecretData): ByteString =
        payload.toByteString()

    override fun decrypt(data: ByteString): SecretData =
        SecretData.parseFrom(data)

}