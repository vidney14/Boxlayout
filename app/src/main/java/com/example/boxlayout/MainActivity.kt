package com.example.boxlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.boxlayout.ui.theme.BoxLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BoxLayoutTheme {
                ProfileScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    Scaffold(
        topBar = {
            // Material 3: TopAppBar
            CenterAlignedTopAppBar(title = { Text("Profile") })
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        // Main container using Box for layering and contentAlignment
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            // 1. Header Background (Box + Gradient-like background)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp) // Requirement: Fixed size
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.primaryContainer
                            )
                        )
                    )
            )

            // Material 3: Card (ElevatedCard)
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .offset(y = 120.dp), // Requirement: offset
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp), // Requirement: elevation
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 50.dp, bottom = 24.dp, start = 16.dp, end = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Vidney Jadhav",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "AI Engineer",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Material 3: AssistChip
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        AssistChip(onClick = {}, label = { Text("ML") })
                        AssistChip(onClick = {}, label = { Text("Deep learning") })
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Material 3: Button
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(onClick = {}, modifier = Modifier.weight(1f)) {
                            Text("Follow")
                        }
                        FilledTonalButton(onClick = {}, modifier = Modifier.weight(1f)) {
                            Icon(Icons.Default.Email, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(Modifier.width(8.dp))
                            Text("Message")
                        }
                    }
                }
            }

            // 3. Foreground Avatar (Circle shape)
            // Uses align, offset, zIndex, clip, and aspectRatio
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter) // Requirement: align
                    .offset(y = 70.dp) // Requirement: offset
                    .zIndex(1f) // Requirement: zIndex
                    .size(100.dp) // Requirement: Fixed size
                    .aspectRatio(1f) // Requirement: aspectRatio
                    .clip(CircleShape) // Requirement: clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(4.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                contentAlignment = Alignment.Center
            ) {
                // Material 3: BadgedBox, Badge, Icon
                BadgedBox(
                    badge = {
                        Badge { Text("3") }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier.size(60.dp),
                        tint = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    BoxLayoutTheme {
        ProfileScreen()
    }
}
