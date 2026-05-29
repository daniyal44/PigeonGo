package com.example.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.Project
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    projects: List<Project>,
    onConnect: (String) -> Unit,
    onDelete: (Project) -> Unit,
    modifier: Modifier = Modifier
) {
    var manualUrl by remember { mutableStateOf("") }
    
    Scaffold(
        modifier = modifier.testTag("dashboard_screen"),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DeepBlack.copy(alpha = 0.95f))
                    .statusBarsPadding()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Brush.linearGradient(listOf(NeonPurple, ElectricGreen))),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(12.dp, 3.dp)
                                .background(Color.White, RoundedCornerShape(1.dp))
                        )
                        Box(
                            modifier = Modifier
                                .size(3.dp, 12.dp)
                                .background(Color.White, RoundedCornerShape(1.dp))
                        )
                    }
                    Text(
                        text = "Pigeon GO",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        letterSpacing = (-0.5).sp
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Icon(
                        Icons.Filled.Share,
                        contentDescription = "Cast",
                        tint = Slate400,
                        modifier = Modifier
                            .size(22.dp)
                            .clickable { /* decorative action */ }
                    )
                    Icon(
                        Icons.Filled.Settings,
                        contentDescription = "Settings",
                        tint = Slate400,
                        modifier = Modifier
                            .size(22.dp)
                            .clickable { /* decorative action */ }
                    )
                }
            }
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Slate900.copy(alpha = 0.95f))
                    .padding(top = 16.dp, bottom = 24.dp)
                    .windowInsetsPadding(WindowInsets.navigationBars),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Live Bar Selection
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.padding(horizontal = 12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .background(NeonPurple.copy(alpha = 0.2f), RoundedCornerShape(20.dp))
                            .padding(horizontal = 20.dp, vertical = 6.dp)
                    ) {
                        Icon(
                            Icons.Filled.PlayArrow,
                            contentDescription = "Live",
                            tint = NeonPurple,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                    Text(
                        "Live",
                        color = NeonPurple,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                // Scanner
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .clickable { /* decorative bottom selection */ }
                ) {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = "Scanner",
                        tint = Slate400,
                        modifier = Modifier.size(26.dp)
                    )
                    Text(
                        "Scanner",
                        color = Slate400,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Medium
                    )
                }
                // Recent
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .clickable { /* decorative bottom selection */ }
                ) {
                    Icon(
                        Icons.Filled.List,
                        contentDescription = "Recent",
                        tint = Slate400,
                        modifier = Modifier.size(26.dp)
                    )
                    Text(
                        "Recent",
                        color = Slate400,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Medium
                    )
                }
                // Debug
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .clickable { /* decorative bottom selection */ }
                ) {
                    Icon(
                        Icons.Filled.Build,
                        contentDescription = "Debug",
                        tint = Slate400,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        "Debug",
                        color = Slate400,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(DeepBlack)
                .padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            // Connection setup
            Text(
                text = "CONNECTION SETUP",
                style = MaterialTheme.typography.labelSmall,
                color = Slate400,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = manualUrl,
                onValueChange = { manualUrl = it },
                label = { Text("Enter localhost or Ngrok URL", color = Slate400) },
                placeholder = { Text("e.g. 192.168.1.5:3000", color = Slate700) },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = NeonPurple,
                    unfocusedBorderColor = Slate800,
                    focusedContainerColor = Slate900.copy(alpha = 0.5f),
                    unfocusedContainerColor = Slate900.copy(alpha = 0.5f),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = NeonPurple
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("manual_url_input")
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (manualUrl.isNotBlank()) {
                        onConnect(manualUrl.trim())
                        manualUrl = ""
                    }
                },
                shape = RoundedCornerShape(16.dp),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .testTag("connect_button")
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Brush.horizontalGradient(listOf(NeonPurple, Color(0xFF6D28D9)))),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Connect via Tunnel",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            OutlinedButton(
                onClick = {
                    // Pre-fill a demo URL for easy testing
                    manualUrl = "https://example.com"
                },
                shape = RoundedCornerShape(16.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Slate800),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Slate400,
                    containerColor = Slate900.copy(alpha = 0.3f)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .testTag("scan_qr_button")
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Scan",
                    tint = Slate400,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "DEMO TUNNEL PRESET",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            Text(
                text = "RECENT SESSIONS",
                style = MaterialTheme.typography.labelSmall,
                color = Slate400,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(16.dp))

            if (projects.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No active tunnels detected.",
                        color = Slate700,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    items(projects, key = { it.id }) { project ->
                        ProjectItem(
                            project = project,
                            onClick = { onConnect(project.url) },
                            onDelete = { onDelete(project) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProjectItem(
    project: Project,
    onClick: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .testTag("project_item_${project.id}"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Slate900.copy(alpha = 0.5f)
        ),
        border = androidx.compose.foundation.BorderStroke(1.dp, Slate800)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(ElectricGreen)
                    )
                    Text(
                        text = "ACTIVE SESSION",
                        style = MaterialTheme.typography.labelSmall,
                        color = Slate400,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                }
                IconButton(
                    onClick = onDelete,
                    modifier = Modifier
                        .size(24.dp)
                        .testTag("delete_project_${project.id}")
                ) {
                    Icon(
                        Icons.Filled.Close,
                        contentDescription = "Delete",
                        tint = Slate400,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = project.url,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(DeepBlack, RoundedCornerShape(12.dp))
                        .padding(12.dp)
                ) {
                    Column {
                        Text(
                            text = "LATENCY",
                            style = MaterialTheme.typography.labelSmall,
                            color = NeonPurple,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = project.latency,
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(DeepBlack, RoundedCornerShape(12.dp))
                        .padding(12.dp)
                ) {
                    Column {
                        Text(
                            text = "FRAMERATE",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFF60A5FA),
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = project.fps,
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}
