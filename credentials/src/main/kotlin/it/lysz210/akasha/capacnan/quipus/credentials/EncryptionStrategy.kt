package it.lysz210.akasha.capacnan.quipus.credentials

import com.google.protobuf.ByteString

interface EncryptionStrategy {
    fun getMeta(): EncryptionMeta

    fun encrypt(payload: SecretData): ByteString

    fun decrypt(data: ByteString): SecretData
}