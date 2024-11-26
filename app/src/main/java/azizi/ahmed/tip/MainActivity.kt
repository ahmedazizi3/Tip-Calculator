package azizi.ahmed.tip

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import azizi.ahmed.tip.ui.theme.TipTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Tip { billAmount ->
                        Log.d("AMT", "MainContent: $billAmount")
                    }
                }
            }
        }
    }
}

@Composable
fun Tip(
    onValChange: (String) -> Unit = {}
) {

    val totalBill = remember {
        mutableStateOf("")
    }
    var valid = remember(totalBill.value) {
        totalBill.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    var split by remember {
        mutableStateOf(1)
    }

    var sliderPosition by remember {
        mutableStateOf(0f)
    }
    var tipPercentage = (sliderPosition * 100).toInt()
    
    var tipAmount by remember {
        mutableStateOf(0.0)
    }

    var totalPerPerson by remember {
        mutableStateOf(0.0)
    }






    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Tip Calculator",
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFFDE7CD1)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(8.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFDE7CD1),
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp,
                pressedElevation = 4.dp,
                focusedElevation = 4.dp,
                hoveredElevation = 4.dp,
                draggedElevation = 4.dp,
                disabledElevation = 4.dp
            )
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                Text(
                    text = "Total Per Person",
                    fontSize = 25.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))



                Text(
                    text = "$${String.format("%.2f", totalPerPerson)}",
                    fontSize = 30.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
//

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .background(Color.White),
            shape = RoundedCornerShape(corner = CornerSize(8.dp)),
            border = BorderStroke(
                width = 1.dp,
                color = Color(0xFFDE7CD1)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
//                OutlinedTextField(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(
//                            start = 8.dp,
//                            end = 8.dp,
//                            top = 8.dp
//                        )
//                        .background(Color.White),
//                    value = totalBill.value,
//                    onValueChange = {
//                        totalPerPerson = calculateTotalPerPerson(
//                            totalBill = totalBill.value.toDouble(),
//                            tipPercentage = tipPercentage,
//                            split = split
//                        )
//                        tipAmount = calculateTotalTip(
//                            bill = totalBill.value.toDouble(),
//                            tipPercentage = tipPercentage
//                        )
//                        totalBill.value = it
//                    },
//                    label = {
//                        Text(
//                            text = "Enter Bill",
//                            color = Color(0xFFDE7CD1)
//                        )
//                    },
//                    leadingIcon = {
//                        Icon(
//                            imageVector = Icons.Default.Star,
//                            contentDescription = "Dollar",
//                            tint = Color(0xFFDE7CD1)
//                        )
//                    },
//                    enabled = true,
//                    singleLine = true,
//                    keyboardActions = KeyboardActions {
//                        if(!valid) return@KeyboardActions
//                        onValChange(totalBill.toString().trim())
//                        keyboardController?.hide()
//                    },
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
//
//                )

                InputField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 8.dp,
                            end = 8.dp,
                            top = 8.dp
                        )
                        .background(Color.White),
                    valueState = totalBill,
                    calculateTip = {
                        totalPerPerson = calculateTotalPerPerson(
                            totalBill = totalBill.value.toDouble(),
                            tipPercentage = tipPercentage,
                            split = split
                        )
                        tipAmount = calculateTotalTip(
                            bill = totalBill.value.toDouble(),
                            tipPercentage = tipPercentage
                        )
                    },
                    labelId = "Enter Bill",
                    enabled = true,
                    isSingleLine = true,
                    keyboardActions = KeyboardActions {
                        if(!valid) return@KeyboardActions
                        onValChange(totalBill.toString().trim())
                        keyboardController?.hide()
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))



                if (valid) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Split to",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFDE7CD1)
                        )
                        Spacer(modifier = Modifier.width(60.dp))

                        Button(
                            onClick = {
                                if (split > 1) {
                                    split--
                                }
                                totalPerPerson = calculateTotalPerPerson(
                                    totalBill = totalBill.value.toDouble(),
                                    tipPercentage = tipPercentage,
                                    split = split
                                )
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor =  Color(0xFFDE7CD1)
                            )
                        ) {
                            Text(
                                text = "-",
                                fontSize = 45 .sp
                            )
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "$split",
                            color = Color(0xFFDE7CD1),
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Button(
                            onClick = {
                                split++
                                totalPerPerson = calculateTotalPerPerson(
                                    totalBill = totalBill.value.toDouble(),
                                    tipPercentage = tipPercentage,
                                    split = split
                                )
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor =  Color(0xFFDE7CD1)
                            ),
                            shape = CircleShape
                        ) {
                            Text(
                                text = "+",
                                fontSize = 45.sp
                            )
                        }

                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Tip:",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFDE7CD1)
                        )
                        Text(
                            text = "$$tipAmount",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFDE7CD1)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "$tipPercentage %",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFDE7CD1)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Slider(
                            value = sliderPosition,
                            onValueChange = { newVal ->
                                sliderPosition = newVal
                                tipPercentage = (sliderPosition * 100).toInt()
                                tipAmount = calculateTotalTip(
                                    bill = totalBill.value.toDouble(),
                                    tipPercentage = tipPercentage
                                )
                                totalPerPerson = calculateTotalPerPerson(
                                    totalBill = totalBill.value.toDouble(),
                                    tipPercentage = tipPercentage,
                                    split = split
                                )
                            },
                            colors = SliderDefaults.colors(
                                thumbColor = Color(0xFFDE7CD1)
                            ),
                            steps = 9

                        )
                    }
                }
            }
        }
    }
}

fun calculateTotalTip(
    bill: Double,
    tipPercentage: Int
): Double {
    return if (bill > 0.0 && tipPercentage > 0) (bill * tipPercentage) / 100.0
    else 0.0
}



fun calculateTotalPerPerson(
    totalBill: Double,
    tipPercentage: Int,
    split: Int
): Double {
    val bill = calculateTotalTip(
        bill = totalBill,
        tipPercentage = tipPercentage
    ) + totalBill
    return bill / split
}





@Preview
@Composable
private fun TipPreview() {
    TipTheme {
        Tip()
    }
}

