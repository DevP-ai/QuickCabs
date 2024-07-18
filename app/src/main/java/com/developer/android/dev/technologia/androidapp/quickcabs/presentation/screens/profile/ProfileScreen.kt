package com.developer.android.dev.technologia.androidapp.quickcabs.presentation.screens.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Shop
import androidx.compose.material.icons.outlined.Web
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.developer.android.dev.technologia.androidapp.quickcabs.R
import com.developer.android.dev.technologia.androidapp.quickcabs.presentation.common.UberDivider
import com.developer.android.dev.technologia.androidapp.quickcabs.ui.theme.Typography
import com.developer.android.dev.technologia.androidapp.quickcabs.ui.theme.spacing
import com.developer.android.dev.technologia.androidapp.quickcabs.utils.clickableWithRipple
import org.intellij.lang.annotations.Language

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    navHostController: NavHostController
) {
    Scaffold(
        topBar = {
            Surface(
                modifier = Modifier.shadow(elevation = MaterialTheme.spacing.small)
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .statusBarsPadding()
                            .fillMaxWidth(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            IconButton(onClick = { navHostController.navigateUp() })
                            {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Back to previous screen"
                                )
                            }

                            androidx.compose.material3.Text(
                                text = "Profile",
                                style = Typography.bodyMedium,
                                modifier = Modifier.padding(horizontal = 12.dp)
                            )

                            TextButton(onClick = { }) {
                                Text(
                                    text = "Edit Profile",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Blue,
                                    modifier = Modifier.padding(start = 160.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item { Spacer(modifier = Modifier.height(20.dp)) }
            item { ProfileHeader() }
            item { Spacer(modifier = Modifier.height(20.dp)) }
            item { SavedPlaces() }
            item { Spacer(modifier = Modifier.height(20.dp)) }
            item { Language() }
            item { Spacer(modifier = Modifier.height(20.dp)) }
            item { Settings() }
        }
    }
}

@Composable
fun ProfileHeader() {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .size(200.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.person),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Raj Patel",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "+910123456789",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                Image(imageVector = Icons.Outlined.Email, contentDescription = "")

                Text(
                    text = "raj.patel@gmail.com",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                TextButton(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Blue,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .padding(start = 70.dp)
                        .background(Color.Blue, shape = CircleShape),
                    shape = CircleShape
                ) {
                    Text(text = "VERIFY", fontSize = 8.sp)
                }
            }
        }
    }
}

@Composable
fun SavedPlaces() {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .size(180.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ){
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(text = "Saved places", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            PlaceItem(text = "Enter home location","", Icons.Outlined.Home,)
            ProfileDivider()
            PlaceItem(text = "Enter work location","", Icons.Outlined.Shop)
            ProfileDivider()
            PlaceItem(text = "Add a place","", Icons.Outlined.Add)
        }
    }

}

@Composable
fun PlaceItem(text: String,subtitle:String,imageVector: ImageVector?) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { }
    ) {
        if (imageVector != null) {
            Image(imageVector = imageVector, contentDescription = text)
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = text, fontSize = 16.sp)
            Text(text = subtitle, fontSize = 10.sp, color = Color.Gray)
        }
    }
}

@Composable
fun Language() {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .size(120.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ){
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            PlaceItem(text = "Language", subtitle = "English - US", imageVector =Icons.Outlined.Web)
            ProfileDivider()
            PlaceItem(text = "Communication preferences", subtitle = "", imageVector = null)
        }
    }

}

@Composable
fun Settings() {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .size(110.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ){ 
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            PlaceItem(text = "Log out", subtitle = "", imageVector = Icons.Outlined.Logout)
            ProfileDivider()
            PlaceItem(text = "Delete Account", subtitle = "", imageVector = Icons.Outlined.Delete)

        }

    }

}


@Composable
fun LanguageItem(text: String, isAction: Boolean = false) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { /*TODO: Setting item action*/ }
    ) {
        Text(text = text, fontSize = 16.sp, color = if (isAction) Color.Red else Color.Black)
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navHostController = rememberNavController())
}
