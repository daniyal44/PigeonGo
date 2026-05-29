package com.example.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.DeepBlack
import com.example.ui.theme.ElectricGreen
import com.example.ui.theme.NeonPurple
import com.example.ui.theme.Slate400
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onSplashComplete: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Elegant fade and scale entering animation
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
        label = "alpha"
    )
    val scaleAnim by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0.8f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "scale"
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        // Short premium transition delay
        delay(2200)
        onSplashComplete()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(DeepBlack)
            .testTag("splash_screen"),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .alpha(alphaAnim)
                .scale(scaleAnim)
        ) {
            // Cybernetic Bridge Icon
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(
                        Brush.linearGradient(listOf(NeonPurple, ElectricGreen)),
                        shape = RoundedCornerShape(28.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                // Sleek white cross representation inside icon
                Box(
                    modifier = Modifier
                        .size(32.dp, 8.dp)
                        .background(Color.White, RoundedCornerShape(4.dp))
                )
                Box(
                    modifier = Modifier
                        .size(8.dp, 32.dp)
                        .background(Color.White, RoundedCornerShape(4.dp))
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = "Pigeon GO",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 32.sp,
                letterSpacing = (-1).sp
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "Real-time Mobile Development Bridge",
                style = MaterialTheme.typography.bodyMedium,
                color = Slate400,
                fontSize = 14.sp
            )
        }
    }
}
