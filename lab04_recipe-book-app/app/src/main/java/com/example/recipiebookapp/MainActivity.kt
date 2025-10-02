package com.example.recipiebookapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipiebookapp.ui.theme.RecipieBookAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val viewModel = RecipeViewModel() // Quick setup for ViewModel

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipieBookAppTheme {
                val sheetState = rememberModalBottomSheetState()
                val scope = rememberCoroutineScope()
                var showBottomSheet by remember { mutableStateOf(false) }

                if (showBottomSheet) {
                    ModalBottomSheet(
                        onDismissRequest = {
                            showBottomSheet = false
                        },
                        sheetState = sheetState
                    ) {
                        // Sheet content
                        Button(onClick = {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    showBottomSheet = false
                                }
                            }
                        }) {
                            Text("Hide bottom sheet")
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 80.dp)
                ) {
                    Column()
                    {
                        Box {
                            Text(
                                text = "Recipe Book",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                fontSize = 40.sp,
                                fontWeight = FontWeight.Black,
                            )
                        }
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                            .padding(vertical = 10.dp, horizontal = 20.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(
                                onClick = { viewModel.sortByName() },
                            ) {
                                Text(text = "Filter by name")
                            }
                                Button(
                                    onClick = { viewModel.sortByRating() },
                                ) {
                                    Text(text = "Filter by rating")
                                }
                        }
                        // Loop over all elements in list.
                        MockRecipes.forEach {
                            Log.v("PRINT", "for $it") // Debug print for Logcat TODO remove
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
                    FloatingActionButton(
                        onClick = {
                            showBottomSheet = true
                        },
                        shape = CircleShape,
                        modifier = Modifier.align(Alignment.BottomEnd)
                            .padding(20.dp)
                            .padding(bottom = 20.dp)
                            .size(70.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Floating Action Button"
                        )
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
    recipeTags: List<Tags>,
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
                                        maxLines = 3,
                                        overflow = TextOverflow.Ellipsis,
                                        lineHeight = 15.sp,
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