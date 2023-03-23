package com.reddot.composepractice.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.reddot.composepractice.R
import com.reddot.composepractice.common.AppIconButton
import com.reddot.composepractice.common.SpacerHeight
import com.reddot.composepractice.common.SpacerWidth
import com.reddot.composepractice.data.Pizza
import com.reddot.composepractice.data.pizzaList
import com.reddot.composepractice.ui.theme.*


@Composable
fun HomeScreen(){

    val menuList =
        listOf("Starter", "Asian", "Bangladeshi", "Classic")
    var currentMenuState by remember{ mutableStateOf("Starter") }

    var scrollState = rememberScrollState()


    Box(modifier = Modifier
        .fillMaxSize()
        .background(BackgroundColor)){
        Column {
            PizzaHeader()
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row ( 
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(scrollState)
                )
                {
                    menuList.forEach{
                        CustomCHip(title = it, selected = it == currentMenuState, onValueChange ={
                            data-> currentMenuState = data
                        } )
                    }
                }
            }
            LazyVerticalGrid(columns = GridCells.Fixed(2)){
                items(pizzaList){
                    ShowPizza(pizza = it)
                }
            }
        }
        ExtendedActionButton(modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 30.dp)
            .align(Alignment.BottomEnd)
        )
    }
}

@Composable
fun ExtendedActionButton(
    modifier: Modifier
){
    Box(modifier = modifier
        .height(49.dp)
        .clip(RoundedCornerShape(27.dp))
        .background(DarkBlackColor)){
        Row {
            SpacerWidth(20.dp)
            Text(text = "$60.40", style = TextStyle(
                fontSize = 17.sp,
                fontWeight = FontWeight.W600,
                color = Color.White
            ),
                modifier = Modifier.align(CenterVertically)
            )
            Icon(painter = painterResource(id = R.drawable.pizza), contentDescription = "",
            modifier = Modifier
                .size(46.dp)
                .padding(2.dp),
            tint = Color.Unspecified)
        }
    }
}

@Composable
fun ShowPizza(
    pizza: Pizza
){
    Card(modifier = Modifier
        .width(175.dp)
        .padding(5.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter = painterResource(id = R.drawable.pizza), contentDescription ="",
                modifier = Modifier.size(109.dp)
                )
                SpacerHeight()
                Text(text = pizza.price, style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400,
                    color = RedColor
                ),
                    textAlign = TextAlign.Center
                )
                SpacerHeight()
                Text(text = pizza.name, style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W600,
                    color = DarkBlackColor
                ),
                    textAlign = TextAlign.Center
                )
                SpacerHeight()
                Text(text = pizza.description, style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.W300,
                    color = LightGrayColor
                ),
                    textAlign = TextAlign.Center
                )
                SpacerHeight()
                Button(onClick = { },
                    modifier = Modifier.width(91.dp),
                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = YellowColor
                    )
                ) {
                    Text(text = "Add", style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W600,
                        color = Color.White
                    ))
                }
            }
        }
    }
}

@Composable
fun PizzaHeader(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(RedColor)){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
           Row {
               AppIconButton(icon = com.reddot.composepractice.R.drawable.menu)
               SpacerWidth(10.dp)
               Text(text = "Ridoy Pizza", style = TextStyle(
                   fontSize = 19.sp,
                   fontWeight = FontWeight.W600,
                   color = Color.White
               ))
           }
            AppIconButton(icon = com.reddot.composepractice.R.drawable.search)
        }
    }
}

@Composable
fun CustomCHip(
    title: String,
    selected: Boolean,
    onValueChange:(String)-> Unit
){
    TextButton(onClick = {
        onValueChange(title)
    },
        shape = RoundedCornerShape(200.dp),
        elevation = ButtonDefaults.elevation(
            0.dp
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (selected) YellowColor else Color.Transparent
        ),
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        Text(text = title, style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.W600,
            color = if (selected) Color.White else DarkBlackColor
        ))

    }
}