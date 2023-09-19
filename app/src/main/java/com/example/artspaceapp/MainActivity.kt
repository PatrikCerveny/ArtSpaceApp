package com.example.artspaceapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.accessibility.AccessibilityViewCommand.ScrollToPositionArguments
import com.example.compose.ArtSpaceAppTheme
import com.example.compose.md_theme_dark_onTertiary
import com.example.compose.md_theme_dark_onTertiaryContainer
import com.example.compose.md_theme_light_onTertiaryContainer
import com.example.compose.md_theme_light_tertiary
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = md_theme_dark_onTertiaryContainer,
                ) {
                    ArtPiece()
                }
            }
        }
    }
}

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ArtPiece(modifier: Modifier = Modifier) {
    val firstImage = R.drawable.painting_starry_night
    val secondImage = R.drawable.girl_with_a_pearl_earring
    val thirdImage = R.drawable.the_kiss
    val fourthImage = R.drawable.mona_lisa

    var visible by remember {
        mutableStateOf(false)
    }

    var title by remember {
        mutableStateOf(R.string.starry_night_title)
    }

    var author by remember {
        mutableStateOf(R.string.starry_night_author)
    }

    var description by remember {
        mutableStateOf(R.string.starry_night_desc)
    }

    var currentArtwork by remember {
        mutableStateOf(firstImage)
    }

    var imageResource by remember {
        mutableStateOf(currentArtwork)
    }

//    AnimatedVisibility(
//        visible = visible,
//        enter = fadeIn() + slideInHorizontally { fullHeight -> fullHeight },
//        ) {

    Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

        AnimatedContent(
            targetState = currentArtwork, label = "animated",
            transitionSpec = {
                fadeIn(
//                    animationSpec = tween (
//                        5500,
//                        delayMillis = 0,
//                    )
                )
                    .togetherWith(fadeOut())
            }
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Artwork(artwork = it)

                Spacer(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )

                TitleAndDesc(title = title, author = author, description = description,)

                Spacer(
                    modifier = Modifier
                        .padding(vertical = 2.dp)
                )
            }
        }
            Column (){

            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier
                        .padding(end = 6.dp,start = 24.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .height(55.dp)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(backgroundColor = md_theme_dark_onTertiary),
                    onClick = {

                        when (currentArtwork) {
                            firstImage -> {
                                title = R.string.mona_lisa_title
                                author = R.string.mona_lisa_author
                                description = R.string.mona_lisa_desc
                                currentArtwork = fourthImage

                            }

                            secondImage -> {
                                title = R.string.starry_night_title
                                author = R.string.starry_night_author
                                description = R.string.starry_night_desc
                                currentArtwork = firstImage
                            }

                            thirdImage -> {
                                title = R.string.girl_pearl_title
                                author = R.string.girl_pearl_author
                                description = R.string.girl_pearl_desc
                                currentArtwork = secondImage
                            }

                            fourthImage -> {
                                title = R.string.the_kiss_title
                                author = R.string.the_kiss_author
                                description = R.string.the_kiss_desc
                                currentArtwork = thirdImage
                            }
                        }
                    }) {
                    Text(text = "Back", color = Color.White)
                }

                Button(
                    modifier = Modifier
                        .padding(start = 6.dp, end = 24.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .height(55.dp)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(backgroundColor = md_theme_dark_onTertiary),
                    onClick = {
                        when (currentArtwork) {
                            firstImage -> {
                                title = R.string.girl_pearl_title
                                author = R.string.girl_pearl_author
                                description = R.string.girl_pearl_desc
                                currentArtwork = secondImage
                            }

                            secondImage -> {
                                title = R.string.the_kiss_title
                                author = R.string.the_kiss_author
                                description = R.string.the_kiss_desc
                                currentArtwork = thirdImage
                            }

                            thirdImage -> {
                                title = R.string.mona_lisa_title
                                author = R.string.mona_lisa_author
                                description = R.string.mona_lisa_desc
                                currentArtwork = fourthImage
                            }

                            fourthImage -> {
                                title = R.string.starry_night_title
                                author = R.string.starry_night_author
                                description = R.string.starry_night_desc
                                currentArtwork = firstImage
                            }
                        }
                    }
                ) {
                    Text(text = "Next", color = Color.White)
                }

            }
        }
    }
}

@Composable
fun Artwork(
    modifier: Modifier = Modifier,
    @DrawableRes artwork: Int
) {
        Image(
            painter = painterResource(artwork),
            contentDescription = "Displaying image",
            modifier = Modifier
                .requiredSizeIn(maxHeight = 290.dp)
                .clip(RoundedCornerShape(10.dp))
        )
}


@Composable
fun TitleAndDesc(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    @StringRes author: Int,
    @StringRes description: Int
) {
    Column(modifier = Modifier
        .clip(shape = RoundedCornerShape(10.dp))
        .background(color = md_theme_light_tertiary)
        .fillMaxWidth()
        .size(270.dp)
        .verticalScroll(ScrollState(0))
        .padding(15.dp)

    ) {
        Text(
            text = stringResource(author),
            textAlign = TextAlign.Start,
            fontSize = 20.sp,
            color = md_theme_light_onTertiaryContainer,
            fontWeight = FontWeight.Bold,

        )
        Text(
            text = stringResource(title),
            fontSize = 12.sp,
            color = md_theme_light_onTertiaryContainer,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier
            .padding(vertical = 5.dp))

        Text(
            text = stringResource(description),
            fontSize = 16.sp,
            color = md_theme_light_onTertiaryContainer,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Light
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceAppTheme {
        ArtPiece()
    }
}