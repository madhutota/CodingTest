package com.madhu.codingtest.data.remote.model

import kotlinx.serialization.SerialName

data class RemoteCharacterPage(
    @SerialName("info")
    val info: Info,
    @SerialName("results")
    val results: List<RemoteCharacter>
) {
    data class Info(
        @SerialName("count")
        val count: Int,
        @SerialName("pages")
        val pages: Int,
        @SerialName("next")
        val next: String?,
        @SerialName("prev")
        val prev: String?
    )
}