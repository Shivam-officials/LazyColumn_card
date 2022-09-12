package com.example.basiccodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

/** composable for passing [Greeting] into a column with input of list<String> */
@Composable
fun MyApp(names: List<String> = listOf("World", "Compose")) {
    Surface(color = MaterialTheme.colors.background) {
        Column(modifier = Modifier.padding(vertical = 4.dp)) {
            for (name in names) {
                Greeting(name = name)
            }
        }
    }
}

/** Composable for Greeting UI and take String as input */
@Composable
fun Greeting(name: String) {
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {

            //column for greetings
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Hello")
                Text(text = name)
            }

            // Outlined-button for showing more text
            OutlinedButton(
                onClick = { /*TODO*/ }
            ) {
                Text("Show more")
            }
        }
    }
}

/** Default preview of the app */
@Preview(showBackground = true, name = "Text Preview", widthDp = 320)
@Composable
fun DefaultPreview() {
    BasicCodelabTheme {
        MyApp()
    }
}