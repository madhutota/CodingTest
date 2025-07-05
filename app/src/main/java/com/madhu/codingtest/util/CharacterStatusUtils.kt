package com.madhu.codingtest.util

import androidx.compose.ui.graphics.Color
import com.madhu.codingtest.domain.model.CharacterStatus

fun CharacterStatus.asColor(): Color {
    return when (this) {
        CharacterStatus.Alive -> Color.Green
        CharacterStatus.Dead -> Color.Red
        CharacterStatus.Unknown -> Color.Yellow
    }
}