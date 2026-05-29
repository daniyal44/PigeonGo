package com.example.ui

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.ui.theme.*

@Composable
fun TunnelScreen(
    url: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.testTag("tunnel_screen"),
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
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(ElectricGreen)
                    )
                    Text(
                        text = "TUNNEL",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = Slate400,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = url.take(18) + if (url.length > 18) "..." else "",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                }
                TextButton(
                    onClick = onBack,
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color(0xFFEF4444).copy(alpha = 0.15f)
                    ),
                    modifier = Modifier.testTag("end_session_button")
                ) {
                    Text(
                        "END SESSION",
                        color = Color(0xFFEF4444),
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
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
        ) {
            // Live web view iframe preview container
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(DeepBlack)
            ) {
                AndroidView(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .testTag("webview_preview"),
                    factory = { context ->
                        WebView(context).apply {
                            webViewClient = WebViewClient()
                            settings.javaScriptEnabled = true
                            settings.domStorageEnabled = true
                            settings.useWideViewPort = true
                            settings.loadWithOverviewMode = true
                            loadUrl(url)
                        }
                    },
                    update = { webView ->
                        // Optional update logic
                    }
                )
            }

            // Console Mirror Overlay
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .background(DeepBlack)
                    .border(
                        androidx.compose.foundation.BorderStroke(1.dp, Slate800),
                        RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                    )
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                    .testTag("console_mirror_overlay")
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(CardBackground)
                            .padding(horizontal = 24.dp, vertical = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "CONSOLE MIRROR",
                            color = Slate400,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.labelMedium,
                            letterSpacing = 1.sp
                        )
                        Text(
                            "STREAM ACTIVE",
                            color = ElectricGreen,
                            fontSize = 11.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Divider(color = Slate800)
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp)
                    ) {
                        Text(
                            text = "[14:02:11] [HMR] connected\n" +
                                   "[14:02:15] [pigeon] Syncing 12 changed files...\n" +
                                   "[14:02:18] [pigeon] Webpack recompilation done\n" +
                                   "[14:02:22] [HMR] app updated",
                            color = Slate400,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 13.sp,
                            lineHeight = 22.sp
                        )
                        Box(
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .size(8.dp, 16.dp)
                                .background(Color(0x33FFFFFF))
                        )
                    }
                }
            }
        }
    }
}
