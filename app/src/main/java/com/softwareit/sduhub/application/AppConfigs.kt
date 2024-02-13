package com.softwareit.sduhub.application

object AppConfigs {

    val myAppFlavor = AppFlavorConfig(
        name = "sduhub",
        label = "SDUHub",
        id = "com.softwareit.sduhub",
        versionName = "1.0.0",
        versionCode = 1,
        dimension = "app"
    )

    val myAppSigning = SigningConfigs(
        name = "sduhub",
        storeFilePath = "Keys/SDUHubKey",
        storePassword = "sduHub",
        keyAlias = "sduhub",
        keyPassword = "sduHub"
    )

    val stagingFlavor = EnvFlavorConfig(
        name = "staging",
        dimension = "env"
    )

    val productionFlavor = EnvFlavorConfig(
        name = "prod",
        dimension = "env"
    )

}

data class SigningConfigs(
    val name: String,
    val storeFilePath: String,
    val storePassword: String,
    val keyAlias: String,
    val keyPassword: String
)

data class AppFlavorConfig(
    val name: String,
    val label: String,
    val id: String,
    val versionName: String,
    val versionCode: Int,
    val dimension: String
)

data class EnvFlavorConfig(
    val name: String,
    val dimension: String
)