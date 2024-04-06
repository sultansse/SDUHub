package com.softwareit.sduhub.utils.common.data.network

import android.content.Context
import androidx.annotation.StringRes
import com.softwareit.sduhub.R
import com.softwareit.sduhub.core.network.model.ConnectionException
import com.softwareit.sduhub.core.network.model.JSONException
import com.softwareit.sduhub.core.network.model.ServerException

fun Throwable.getLocalMessage(context: Context, @StringRes fallback: Int? = null): String = when (this) {
    is JSONException -> context.getString(R.string.failed_to_parse_json)
    is ConnectionException -> context.getString(R.string.failed_to_connect_to_server)
    is ServerException -> context.getString(R.string.server_error)
    else -> fallback?.let { context.getString(fallback) } ?: localizedMessage.orEmpty()
}