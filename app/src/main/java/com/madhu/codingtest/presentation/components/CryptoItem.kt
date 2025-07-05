package com.madhu.codingtest.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.madhu.codingtest.domain.model.Character
import com.madhu.codingtest.domain.model.Root

@Composable
fun CryptoItem(crypto: Character) {
    val str = crypto.imageUrl
    val result = str.replace("-", "")
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            AsyncImage(
                model = crypto.imageUrl,
                contentDescription = "",
                modifier = Modifier.size(64.dp)
            )
            Column {
                Box(modifier = Modifier.width(140.dp)) {
                    Text(
                        text = crypto.name.toString().uppercase(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
                Text(
                    text = crypto.name.toString(),
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
            }
        }
    }

}