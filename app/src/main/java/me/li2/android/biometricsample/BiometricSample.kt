package me.li2.android.biometricsample

import androidx.fragment.app.FragmentActivity
import me.li2.android.biometric.*
import me.li2.android.view.snackbar.snackbar

fun FragmentActivity.bioAuth() {
    BiometricAuth.authenticate(
        this,
        title = "title",
        subtitle = "subtitle",
        description = "description",
        negativeButtonText = "Cancel"
    ) { result ->
        when (result) {
            is BiometricAuthSucceeded -> snackbar("Biometric auth succeed with cryptoObject ${result.data.cryptoObject}")
            is BiometricAuthError -> snackbar("Biometric auth error: ${result.errorCode} - ${result.errString}")
            is BiometricAuthFailed -> snackbar("Biometric auth failed")
            is BiometricNotSupported -> snackbar("Biometric not supported")
        }
    }
}