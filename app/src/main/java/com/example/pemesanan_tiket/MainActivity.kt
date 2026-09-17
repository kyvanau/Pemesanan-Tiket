package com.example.pemesanan_tiket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    TicketScreen()
                }
            }
        }
    }
}

@Composable
fun TicketScreen() {
    // Harga tiket tetap
    val hargaTiket = 25000
    var jumlahTiket by remember { mutableStateOf(1) }
    // Total pembayaran
    val total = hargaTiket * jumlahTiket
    // Format Rupiah
    fun formatRupiah(angka: Int): String {
        val format = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
        return format.format(angka)
            .replace("Rp", "Rp")
            .replace(",00", "")
            .replace(".", ".")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FA))
    ) {

        // =========================
        // HEADER
        // =========================
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color(0xFF2196F3),
                    RoundedCornerShape(
                        bottomStart = 28.dp,
                        bottomEnd = 28.dp
                    )
                )
                .padding(
                    top = 45.dp,
                    bottom = 35.dp,
                    start = 24.dp,
                    end = 24.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                imageVector = Icons.Default.ConfirmationNumber,
                contentDescription = "Tiket",
                tint = Color.White,
                modifier = Modifier.size(50.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Pemesanan Tiket",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Pesan tiket dengan mudah!",
                color = Color.White,
                fontSize = 15.sp
            )
        }

        // =========================
        // ISI
        // =========================
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // =========================
            // HARGA TIKET
            // =========================
            TicketCard {

                Text(
                    text = "Harga Tiket",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF263238)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = formatRupiah(hargaTiket),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2196F3)
                )

                Text(
                    text = "per tiket",
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }

            // =========================
            // JUMLAH TIKET
            // =========================
            TicketCard {

                Text(
                    text = "Jumlah Tiket",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF263238)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    // Tombol MINUS
                    IconButton(
                        onClick = {
                            if (jumlahTiket > 1) {
                                jumlahTiket--
                            }
                        },
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                Color(0xFF2196F3),
                                RoundedCornerShape(50.dp)
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Remove,
                            contentDescription = "Kurangi tiket",
                            tint = Color.White
                        )
                    }

                    // JUMLAH
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 12.dp)
                            .height(48.dp)
                            .background(
                                Color(0xFFF0F3F7),
                                RoundedCornerShape(12.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = jumlahTiket.toString(),
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF263238)
                        )
                    }

                    // Tombol PLUS
                    IconButton(
                        onClick = {
                            jumlahTiket++
                        },
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                Color(0xFF2196F3),
                                RoundedCornerShape(50.dp)
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Tambah tiket",
                            tint = Color.White
                        )
                    }
                }
            }

            // =========================
            // TOTAL
            // =========================
            TicketCard {

                Text(
                    text = "Total",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF263238)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = formatRupiah(total),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF008744)
                )
            }

            // =========================
            // RESET
            // =========================
            Button(
                onClick = {
                    jumlahTiket = 1
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFEF4444)
                )
            ) {

                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Reset"
                )

                Spacer(modifier = Modifier.size(8.dp))

                Text(
                    text = "RESET",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun TicketCard(
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color.White,
                RoundedCornerShape(14.dp)
            )
            .padding(16.dp)
    ) {
        content()
    }
}