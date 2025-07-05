package com.madhu.codingtest.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.madhu.codingtest.domain.model.Character
import com.madhu.codingtest.domain.model.CharacterStatus
import com.madhu.codingtest.presentation.ui.theme.Pink40
import com.madhu.codingtest.presentation.ui.theme.Purple40

@Composable
fun CharacterGridItem(
    modifier: Modifier,
    character: Character
) {
    Column(
        modifier = modifier
            .border(
                width = 1.dp,
                brush = Brush.verticalGradient(listOf(Color.Transparent, Pink40)),
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
    ) {
        Box {
            CharacterImage(imageUrl = character.imageUrl)
            CharacterStatusCircle(
                status = character.status,
                modifier = Modifier.padding(start = 6.dp, top = 6.dp)
            )
        }
        Text(
            text = character.name,
            color = Purple40,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            lineHeight = 26.sp,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
        )
    }
}

@Preview
@Composable
private fun CharacterGridItemPreview() {
    CharacterGridItem(
        modifier = Modifier.fillMaxWidth(),
        character = Character(
            id = 1,
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
            name = "Morty Smith",
            status = CharacterStatus.Alive,
        )
    )
}