package com.developer.android.dev.technologia.androidapp.quickcabs.presentation


import android.content.Intent
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developer.android.dev.technologia.androidapp.quickcabs.MainActivity
import com.developer.android.dev.technologia.androidapp.quickcabs.R
import kotlinx.coroutines.delay

class SplashScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashScreen {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    var startAnimation by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (startAnimation) 1.0f else 0.5f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = { OvershootInterpolator(2f).getInterpolation(it) }
        )
    )

    LaunchedEffect(true) {
        startAnimation = true
        delay(3000)
        onTimeout()
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
        color = Color.Blue
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp, bottom = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.white_bar),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(200.dp)
            )

            Spacer(modifier = Modifier.height(120.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(15) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .background(Color.Blue)
                            .padding(end = 2.dp)
                    )
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .background(Color(0xFFFFCC00))
                            .padding(end = 2.dp)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(15) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .background(Color(0xFFFFCC00))
                            .padding(end = 2.dp)
                    )
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .background(Color.Blue)
                            .padding(end = 2.dp)
                    )
                }
            }
        }

//        Spacer(modifier = Modifier.height(80.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Quik",
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Box(
                    modifier = Modifier
                        .padding(start = 5.dp, top = 5.dp)
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                        }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.car),
                        contentDescription = "App Logo",
                        modifier = Modifier
                            .size(40.dp)
                            .scale(scale)
                    )
                }
            }
            Text(
                text = "Cabs",
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(start = 20.dp)
            )
        }
    }

}

