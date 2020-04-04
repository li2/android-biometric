package me.li2.android.biometric

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity
import java.util.concurrent.Executors

object BiometricAuth {
    private fun canAuthenticate(context: Context): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && context.packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)
    }

    /**
     * Shows the biometric prompt.
     */
    fun authenticate(
            fragmentActivity: FragmentActivity,
            title: String,
            subtitle: String? = null,
            description: String? = null,
            negativeButtonText: String,
            callback: (BiometricAuthResult) -> Unit) {

        if (!canAuthenticate(fragmentActivity)) {
            callback(BiometricNotSupported)
            return
        }

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle(title)
                .setSubtitle(subtitle)
                .setDescription(description)
                .setNegativeButtonText(negativeButtonText)
                .build()

        BiometricPrompt(fragmentActivity, Executors.newSingleThreadExecutor(),
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                        super.onAuthenticationError(errorCode, errString)
                        callback(BiometricAuthError(errorCode, errString.toString()))
                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        callback(BiometricAuthFailed)
                    }

                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        callback(BiometricAuthSucceeded(result))
                    }
                })
                .authenticate(promptInfo)
    }
}
