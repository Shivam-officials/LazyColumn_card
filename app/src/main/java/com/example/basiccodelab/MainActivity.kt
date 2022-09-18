package com.example.basiccodelab

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basiccodelab.ui.theme.BasicCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicCodelabTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp(){
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    if(shouldShowOnboarding) {
        OnboardingScreen( onContinueClicked = { shouldShowOnboarding = false } )
    }else{
        Greetings()
    }
}

/** MyApp MainActivity preview */
@Preview(
    showBackground = true,
    widthDp = 320,
    heightDp = 480,
    uiMode = UI_MODE_NIGHT_YES,
    name = "DarkMyAppPreview"
    )
@Preview(showBackground = true, widthDp = 320, heightDp = 480)
@Composable
fun MyAppPreview(){
    BasicCodelabTheme() {
        MyApp()
    }
}

/** composable for passing [Greeting] into a column with input of list<String> */
@Composable
fun Greetings(names: List<String> = List(1000){ "$it"}) {
    Surface(color = MaterialTheme.colors.background) {

      LazyColumn(modifier = Modifier.padding(vertical = 4.dp)){
          items(items = names) { name -> Greeting(name = name) }
    }

    }
}

/** Composable for Greeting UI and take String as input */
@Composable
fun Greeting(name: String) {

    //state for representing the expanded item
    var expanded by rememberSaveable{ mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        if(expanded) 48.dp else 0.dp ,
    animationSpec = spring(
        dampingRatio = Spring.DampingRatioMediumBouncy,
         stiffness = Spring.StiffnessLow)
    )


    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {

            //column for greetings
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))
            ) {
                Text(text = "Hello")
                Text(text = name , style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                ))
            }

            // Outlined-button for showing more text
            OutlinedButton(
                onClick = { expanded = !expanded }

            ) {
                Text(if(expanded) "Show less" else "Show more" )
            }
        }
    }
}

/** Default preview of the app */
@Preview(showBackground = true, name = "Text Preview", widthDp = 320,heightDp = 480)
@Composable
fun DefaultPreview() {
    BasicCodelabTheme {
        Greetings()
    }
}



@Composable
fun OnboardingScreen( onContinueClicked:() -> Unit ) {


    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basics Code-lab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("Continue")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    BasicCodelabTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}
