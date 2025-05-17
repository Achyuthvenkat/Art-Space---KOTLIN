package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    val artList = listOf(
        ArtPiece(R.drawable.art1, "The Starry Night", "Vincent van Gogh", "1889"),
        ArtPiece(R.drawable.art2, "The Scream", "Edvard Munch", "1893"),
        ArtPiece(R.drawable.art3, "Girl with a Pearl Earring", "Johannes Vermeer", "1665")
    )

    var index by remember { mutableStateOf(0) }
    val art = artList[index]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(8.dp),
            shadowElevation = 8.dp,
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 2.dp
        ) {
            Image(
                painter = painterResource(id = art.imageRes),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }

        Surface(
            modifier = Modifier.padding(8.dp),
            color = Color.LightGray,
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(art.title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text("${art.artist} (${art.year})", fontSize = 16.sp, textAlign = TextAlign.Center)
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                index = if (index > 0) index - 1 else artList.lastIndex
            }) {
                Text("Previous")
            }

            Button(onClick = {
                index = (index + 1) % artList.size
            }) {
                Text("Next")
            }
        }
    }
}


data class ArtPiece(
    val imageRes: Int,
    val title: String,
    val artist: String,
    val year: String
)

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}