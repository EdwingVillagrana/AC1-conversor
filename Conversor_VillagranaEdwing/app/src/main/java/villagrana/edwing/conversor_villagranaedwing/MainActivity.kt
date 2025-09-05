package villagrana.edwing.conversor_villagranaedwing

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    var conversionSeleccionada = 0
    lateinit var tvResultado: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnLongitud: Button = findViewById(R.id.btnLongitud)
        val btnMasa: Button = findViewById(R.id.btnMasa)
        val btnTemperatura: Button = findViewById(R.id.btnTemperatura)
        val btnVolumen: Button = findViewById(R.id.btnVolumen)
        val btnConvertir: Button = findViewById(R.id.btnConvertir)

        val tvConversionSeleccionada: TextView = findViewById(R.id.tvConversionSeleccionada)
        tvResultado = findViewById(R.id.tvResultado)

        val etCifra: EditText = findViewById(R.id.etCifra)

        btnLongitud.setOnClickListener {
            conversionSeleccionada = 1
            tvConversionSeleccionada.setText("Convertir Km a Millas")
        }

        btnMasa.setOnClickListener {
            conversionSeleccionada = 2
            tvConversionSeleccionada.setText("Convertir Kg a Libras")
        }

        btnTemperatura.setOnClickListener {
            conversionSeleccionada = 3
            tvConversionSeleccionada.setText("Convertir °C a °F")
        }

        btnVolumen.setOnClickListener {
            conversionSeleccionada = 4
            tvConversionSeleccionada.setText("Convertir Lts a Onzas")
        }

        btnConvertir.setOnClickListener {
            val valor: Double? = etCifra.text.toString().toDoubleOrNull()

            if (valor != null){
                when(conversionSeleccionada){
                    1 -> convertirLongitud(valor)
                    2 -> convertirMasa(valor)
                    3 -> convertirTemperatura(valor)
                    4 -> convertirVolumen(valor)
                    else -> {
                        AlertDialog.Builder(this)
                            .setTitle("Atención")
                            .setMessage("Por favor selecciona una opción de conversión.")
                            .setPositiveButton("OK", null)
                            .show()
                    }
                }
            } else{
                AlertDialog.Builder(this)
                    .setTitle("Atención")
                    .setMessage("Por favor ingresa un valor a convertir.")
                    .setPositiveButton("OK", null)
                    .show()
            }

        }
    }

    fun convertirLongitud(kilometros: Double){
        var millas = kilometros * 0.621371
        tvResultado.text = "%.2f".format(millas)
    }

    fun convertirMasa(kilogramos: Double){
        var libras = kilogramos * 2.20462
        tvResultado.text = "%.2f".format(libras)
    }

    fun convertirTemperatura(centigrados: Double){
        var fahrenheit = (centigrados * 9/5) + 32
        tvResultado.text = "%.2f".format(fahrenheit)
    }

    fun convertirVolumen(litros: Double){
        var onzas = litros * 33.814
        tvResultado.text = "%.2f".format(onzas)
    }

}