package ru.d3rvich.datingapp.data.remote

import android.util.Log
import io.ktor.client.*
import io.ktor.client.request.*
import ru.d3rvich.datingapp.data.constants.ApiConstants
import ru.d3rvich.datingapp.data.services.DialogService
import javax.inject.Inject

class DialogServiceImpl @Inject constructor(private val httpClient: HttpClient) : DialogService {
    override suspend fun getMessageList() {
        val result = httpClient.get<Any>(ApiConstants.GET_DIALOGS)
        Log.d(DialogServiceImpl::class.java.simpleName, "Get dialog list result: $result")
    }
}