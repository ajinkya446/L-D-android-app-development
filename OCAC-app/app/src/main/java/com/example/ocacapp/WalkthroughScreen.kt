@file:OptIn(ExperimentalFoundationApi::class)

package com.example.ocacapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ocacapp.ui.theme.OCACAppTheme

class WalkthroughScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OCACAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    OnBoardingScreen()
                }
            }
        }
    }
}

@Composable
fun OnBoardingScreen() {
    val pagerState = rememberPagerState(0, 0F) {
        3
    }
    var resource = remember {
        0
    }
    var text = remember {
        ""
    }
    var description = remember {
        ""
    }
    Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
        HorizontalPager(state = pagerState) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (pagerState.currentPage) {
                    0 -> {
                        text = "Ask Questions, Get AI Chatbot Response"
                        description =
                            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typeset."
                        resource = R.drawable.apps
                    }

                    1 -> {
                        text = "Quickly Search Available Services"
                        description =
                            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typeset."
                        resource = R.drawable.services
                    }

                    2 -> {
                        text = "Get Responses from an AI Chatbot"
                        description =
                            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typeset."
                        resource = R.drawable.chatbot
                    }
                }
                Icon(
                    painterResource(id = resource), contentDescription = "Onboarding Screens",
                    modifier = Modifier
                        .width(500.dp)
                        .height(300.dp), tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = text,
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontFamily = FontFamily(Font(R.font.lato_regular)),
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = description,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color(0xff3F3F3F),
                        fontFamily = FontFamily(Font(R.font.lato_regular)),
                        fontWeight = FontWeight.Normal
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(3) {
                CustomIndicator(isSelected = pagerState.currentPage == it)
            }
            if (pagerState.currentPage == 2) {
                Spacer(modifier = Modifier.weight(1f))
            }
            if (pagerState.currentPage == 2) {
                val context = LocalContext.current
                Box(
                    modifier = Modifier
                        .clickable {
                            context.startActivity(
                                Intent(
                                    context,
                                    LoginScreenActivity::class.java
                                )
                            )
                        }
                        .width(120.dp)
                        .height(40.dp)
                        .padding(2.dp)
                        .background(
                            color = Color(0xff11842E),
                            shape = RoundedCornerShape(60)
                        )
                        .size(15.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Continue",
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.White,
                            fontFamily = FontFamily(Font(R.font.lato_regular)),
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomIndicator(isSelected: Boolean) {
    Box(
        modifier = Modifier
            .width(if (isSelected) 60.dp else 20.dp)
//            .border(width = 1.dp, color = Color(0xff11842E), shape = RoundedCornerShape(12))
            .padding(2.dp)
            .background(
                color = if (isSelected) Color(0xff11842E) else Color(0xffD3D5D7),
                shape = if (isSelected) RoundedCornerShape(60) else CircleShape
            )
            .size(15.dp)
    )
}
