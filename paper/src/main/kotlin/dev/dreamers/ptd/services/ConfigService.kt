package dev.dreamers.ptd.services

import dev.dreamers.ptd.helpers.StorageHelper

class ConfigService private constructor() : StorageHelper("config.yml") {

    companion object {
        private val instance = ConfigService()

        fun init() = instance.init()
        fun reload(): Boolean = instance.reload()

        var PRINT_SPLASHSCREEN: Boolean = true
        var UPDATE_CHECKER_ENABLED: Boolean = true
        var BLACKLISTED_ITEMS: List<String> = listOf("WOODEN_SWORD", "IRON_SWORD")
        var BLACKLISTED_CONTAINERS: List<String> = listOf("HOPPER")

        var CONFIG_VERSION: Int = 1
    }

    override fun loadValues() {
        val config = getConfig()

        PRINT_SPLASHSCREEN = config.getBoolean("Print-SplashScreen")
        UPDATE_CHECKER_ENABLED = config.getBoolean("Update-Checker.enabled")
        BLACKLISTED_ITEMS = config.getStringList("Settins.BlackListed-Items").ifEmpty { BLACKLISTED_ITEMS }
        BLACKLISTED_CONTAINERS = config.getStringList("Settins.BlackListed-Containers").ifEmpty { BLACKLISTED_CONTAINERS }

        CONFIG_VERSION = config.getInt("config-version")
    }
}
