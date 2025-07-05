package com.madhu.codingtest.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.madhu.codingtest.presentation.ui.theme.Pink40

private val defaultModifier = Modifier
    .fillMaxSize()
    .padding(all = 128.dp)

@Composable
fun LoadingState(modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = modifier,
            color = Pink40
        )
    }



}