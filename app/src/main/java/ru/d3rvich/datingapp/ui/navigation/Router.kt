package ru.d3rvich.datingapp.ui.navigation

import android.os.Bundle
import androidx.core.os.bundleOf

fun createExternalRouter(block: (String, Bundle) -> Unit): Router = object : Router {
    override fun routeTo(route: String, params: Bundle) {
        block.invoke(route, params)
    }
}

interface Router {
    fun routeTo(route: String, params: Bundle = bundleOf()) {
        throw NotImplementedError(message = "You used router, but don't implemented it for screen $route with params $params")
    }
}