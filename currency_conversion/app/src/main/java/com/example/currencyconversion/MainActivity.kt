package com.example.currencyconversion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.currencyconversion.ui.theme.CurrencyConversionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyConversionTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    CurrencyConverter()
                }
            }
        }
    }
}

@Composable
fun CurrencyConverter() {
    val currencies = listOf("IDR", "USD", "EUR")
    var amountInput by remember { mutableStateOf("") }
    var fromCurrency by remember { mutableStateOf("IDR") }
    var toCurrency by remember { mutableStateOf("USD") }

    val convertedAmount = convertCurrency(amountInput.toDoubleOrNull() ?: 0.0, fromCurrency, toCurrency)

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Currency Converter", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = amountInput,
            onValueChange = { amountInput = it },
            label = { Text("Amount") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        CurrencyDropdown(label = "From", selectedCurrency = fromCurrency, onCurrencySelected = {
            fromCurrency = it
        }, currencies = currencies)

        CurrencyDropdown(label = "To", selectedCurrency = toCurrency, onCurrencySelected = {
            toCurrency = it
        }, currencies = currencies)

        Text("Result: $convertedAmount $toCurrency", style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun CurrencyDropdown(
    label: String,
    selectedCurrency: String,
    onCurrencySelected: (String) -> Unit,
    currencies: List<String>
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(text = label)
        Box {
            OutlinedButton(onClick = { expanded = true }) {
                Text(selectedCurrency)
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                currencies.forEach { currency ->
                    DropdownMenuItem(
                        text = { Text(currency) },
                        onClick = {
                            onCurrencySelected(currency)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

fun convertCurrency(amount: Double, from: String, to: String): Double {
    val ratesToIDR = mapOf(
        "IDR" to 1.0,
        "USD" to 16823.0,
        "EUR" to 19150.0
    )

    val amountInIDR = amount * (ratesToIDR[from] ?: 1.0)
    return (amountInIDR / (ratesToIDR[to] ?: 1.0)).let { String.format("%.2f", it).toDouble() }
}