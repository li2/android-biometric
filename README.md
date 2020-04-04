[![](https://jitpack.io/v/li2/android-biometric.svg)](https://jitpack.io/#li2/android-biometric)


##  Biometric Library

This library is a wapper of AndroidX biometric.


## Usage

```kotlin
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
```

<img width="300" alt="Biometric Prompt" src="screenshots/biometric-prompt.png">


## Download

```gradle
implementation 'com.github.li2:android-biometric:latest_version'
```


## License

```
    Copyright (C) 2020 Weiyi Li

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
```