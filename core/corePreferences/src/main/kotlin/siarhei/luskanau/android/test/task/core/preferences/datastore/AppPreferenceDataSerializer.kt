package siarhei.luskanau.android.test.task.core.preferences.datastore

import androidx.datastore.core.Serializer
import com.google.crypto.tink.Aead
import java.io.InputStream
import java.io.OutputStream
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Single

@Single
internal class AppPreferenceDataSerializer(private val aeadProvider: suspend () -> Aead) :
    Serializer<AppPreferenceData> {
    private val parser = Json { ignoreUnknownKeys = true }

    override val defaultValue: AppPreferenceData =
        AppPreferenceData()

    private var aead: Aead? = null

    private suspend fun getAead(): Aead {
        if (aead == null) {
            aead = aeadProvider.invoke()
        }
        return requireNotNull(aead)
    }

    override suspend fun readFrom(input: InputStream): AppPreferenceData = try {
        val encryptedInput = input.readBytes()
        val decryptedInput = if (encryptedInput.isNotEmpty()) {
            getAead().decrypt(encryptedInput, null)
        } else {
            encryptedInput
        }
        parser.decodeFromString(
            AppPreferenceData.serializer(),
            decryptedInput.decodeToString()
        )
    } catch (throwable: Throwable) {
        AppPreferenceData()
    }

    override suspend fun writeTo(t: AppPreferenceData, output: OutputStream) {
        val json = parser.encodeToString(AppPreferenceData.serializer(), t)
        val encryptedBytes = getAead().encrypt(json.encodeToByteArray(), null)
        withContext(Dispatchers.IO) {
            output.write(encryptedBytes)
        }
    }
}
