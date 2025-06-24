# Build a simple app with text composables

First result:

![First result](./assets/first-result.png)

Modifications:

- Added "From" Input Field. Users can now enter their name as the sender of the greeting.
- Button changes the message to "Wishing You a Wonderful Year!" when clicked.

```kt
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyBirthdayTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BirthdayGreetingScreen()
                }
            }
        }
    }
}

@Composable
fun BirthdayGreetingScreen() {
    var name by remember { mutableStateOf("") }
    var from by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("Happy Birthday!") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        TextInputField(
            label = "Enter recipient's name",
            value = name,
            onValueChange = { name = it }
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextInputField(
            label = "Enter your name",
            value = from,
            onValueChange = { from = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = if (name.isNotEmpty()) "$message $name!" else message,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        if (from.isNotEmpty()) {
            Text(
                text = "- From $from",
                fontSize = 24.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // change message button
        Button(onClick = { message = "Wishing You a Wonderful Year!" }) {
            Text(text = "Change Message")
        }
    }
}

// Reusable Composable untuk Input Text
@Composable
fun TextInputField(label: String, value: String, onValueChange: (String) -> Unit) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .padding(12.dp)
            ) {
                if (value.isEmpty()) {
                    Text(label, color = Color.Gray)
                }
                innerTextField()
            }
        }
    )
}
```

Final result:

![Final result](./assets/final-result.gif)