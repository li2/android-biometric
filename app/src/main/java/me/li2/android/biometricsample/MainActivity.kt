package me.li2.android.biometricsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import me.li2.android.biometric.*
import me.li2.android.view.snackbar.snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        biometric_auth_button.setOnClickListener {
            auth()
        }
    }

    private fun auth() {
        BiometricAuth.authenticate(this,
                title = "title",
                subtitle = "subtitle",
                description = "description",
                negativeButtonText = "Cancel") { result ->
            when (result) {
                is BiometricAuthSucceeded -> snackbar("Biometric auth succeed with cryptoObject ${result.data.cryptoObject}")
                is BiometricAuthError -> snackbar("Biometric auth error: ${result.errorCode} - ${result.errString}")
                is BiometricAuthFailed -> snackbar("Biometric auth failed")
                is BiometricNotSupported -> snackbar("Biometric not supported")
            }
        }
    }
}