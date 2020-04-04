package me.li2.android.biometric

import androidx.biometric.BiometricPrompt

sealed class BiometricAuthResult
data class BiometricAuthSucceeded(val data: BiometricPrompt.AuthenticationResult) : BiometricAuthResult()
data class BiometricAuthError(val errorCode: Int, val errString: String) : BiometricAuthResult()
object BiometricAuthFailed : BiometricAuthResult()
object BiometricNotSupported : BiometricAuthResult()
