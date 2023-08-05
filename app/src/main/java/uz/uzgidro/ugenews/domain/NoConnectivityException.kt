package uz.uzgidro.ugenews.domain

import java.io.IOException

class NoConnectivityException: IOException() {

    override val message: String
        get() = "No internet connection"
}