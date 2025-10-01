package com.example.recipiebookapp

import android.graphics.drawable.shapes.Shape
import android.media.Rating
import android.os.Bundle
import android.util.Log
import android.widget.RatingBar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.pm.ShortcutInfoCompatSaver
import com.example.recipiebookapp.ui.theme.RecipieBookAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipieBookAppTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 80.dp)
                ) {
                    Column()
                    {
                        // loop over all elements in list.
                        MockRecipes.forEach {
                            Log.v("PRINT", "for $it") // Debug print for Logcat
                            Box(
                                modifier = Modifier
                                    .padding(bottom = 20.dp)
                                    .border(width = 2.dp, color = Color.Black)
                            ) {
                                RecipeCard(
                                    recipeName = it.name,
                                    recipeDescription = it.description,
                                    recipeRating = it.rating,
                                    recipeTags = it.tag
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}



@Composable
fun RecipeCard(
    recipeName: String,
    recipeDescription: String?,
    recipeRating: Int,
    recipeTags: List<Tags>
) {
    Box(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
    ){
        Row {
            Image(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(width = 100.dp)
                    .background(color = Color.Red),
                contentScale = ContentScale.Crop,
                painter = painterResource( id = R.drawable.food),
                contentDescription = ""
            )
                Row(
                    modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(
                                text = recipeName,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                            RatingBar(recipeRating)
                        }
                        TagRow(recipeTags)

                        Box (modifier = Modifier.padding(10.dp)){
                            Column {
                                Text(
                                    text = "Description: ",
                                    modifier = Modifier.padding(vertical = 2.dp),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp

                                )
                                if (recipeDescription != null) {
                                    Text(
                                        text = recipeDescription,
                                        fontSize = 12.sp
                                    )
                                } else{
                                    Text(
                                        text = "No description yet",
                                        fontSize = 12.sp
                                    )
                                }
                            }
                        }
                    }
                }


            }

        }
    }

@Composable
fun TagRow(recipeTags: List<Tags>) {
    Box(modifier = Modifier.width(400.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp)
            ) {
            Button(onClick = {/*TODO*/ }
            ) {
                Text(text ="Tags:")
            }
            recipeTags.forEach {
                Button(onClick = {/*TODO*/ }
                ) {
                    Text(text= it.toString())
                }
            }
            }
        }
    }


@Composable
fun RatingBar(
    recipeRating: Int
){
    Box(modifier = Modifier.width(200.dp),) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            for (i in 0..recipeRating-1) {
                Box (
                    modifier = Modifier.padding(bottom = 12.dp)
                ){
                    Icon( // Shadow behind Icons
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Star Icon shadow",
                        tint = Color(0, 0, 0, 40),
                        modifier = Modifier
                            .offset(1.dp, 1.dp)
                            .blur(
                                radiusX = 3.dp,
                                radiusY = 3.dp,
                                edgeTreatment = BlurredEdgeTreatment.Rectangle
                            )
                    )
                    Icon( // Actual Icon
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Star Icon shadow",
                        tint = Color.Yellow
                    )
                }
            }
        }
    }
}