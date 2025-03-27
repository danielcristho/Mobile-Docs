    package com.codelab.basics.ui.theme

    import androidx.compose.foundation.isSystemInDarkTheme
    import androidx.compose.material3.*
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.graphics.Color

    private val DarkColorScheme = darkColorScheme(
        primary = Color(0xFF008080),
        secondary = Color(0xFF80CBC4),
        tertiary = Color(0xFFFFF9C4),
        background = Color(0xFF2E2E2E),
        surface = Color(0xFF424242),
        onPrimary = Color.White,
        onSecondary = Color.Black,
        onTertiary = Color.Black,
        onBackground = Color.White,
        onSurface = Color.White
    )

    private val LightColorScheme = lightColorScheme(
        primary = Color(0xFF008080),
        secondary = Color(0xFFB2DFDB),
        tertiary = Color(0xFFFFF9C4),
        background = Color(0xFFFFFBFA),
        surface = Color(0xFFF8F5F2),
        onPrimary = Color.White,
        onSecondary = Color.Black,
        onTertiary = Color.Black,
        onBackground = Color.Black,
        onSurface = Color.Black
    )

    @Composable
    fun BasicsCodelabTheme(
        darkTheme: Boolean = isSystemInDarkTheme(),
        content: @Composable () -> Unit
    ) {
        val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }