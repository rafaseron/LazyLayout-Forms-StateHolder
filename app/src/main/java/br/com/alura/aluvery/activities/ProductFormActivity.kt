package br.com.alura.aluvery.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.sampledata.addedProducts
import br.com.alura.aluvery.ui.theme.AluveryTheme
import coil.compose.AsyncImage
import java.lang.NumberFormatException
import java.math.BigDecimal

class ProductFormActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent(){
            AluveryTheme {
                Surface {
                    ProductFormScreen()
                }
            }
        }
    }
}

@Composable
fun ProductFormScreen() {
    var urlImagem by remember { mutableStateOf("") }
    var nome by remember { mutableStateOf("") }
    var preco by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }

    //TESTES DE DADOS
    /*
    urlImagem = "https://img.freepik.com/fotos-gratis/hamburguer-delicioso-isolado-no-fundo-branco_125540-3368.jpg"
    nome = "João"
    preco = "22,99"
    descricao = LoremIpsum(300).values.first()*/

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)) {

        Text(text = "Criando o Produto", fontWeight = FontWeight(400),
            fontSize = 28.sp,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp))

        if (urlImagem.isBlank()){} else{
            AsyncImage(model = urlImagem, contentDescription = null, modifier = Modifier
                .fillMaxWidth()
                .height(200.dp))
        }

        OutlinedTextField(value = urlImagem, onValueChange = {newValue ->
            urlImagem = newValue },
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(1f)
                .height(54.dp),
            placeholder = { Text(text = "Url da imagem")},
            label = { Text(text = "Url da imagem") })

        OutlinedTextField(value = nome, onValueChange = {newValue ->
            nome = newValue },
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(1f)
                .height(54.dp),
            placeholder = { Text(text = "Nome")},
            label = { Text(text = "Nome") })

        OutlinedTextField(value = preco, onValueChange = {newValue ->
            preco = newValue },
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(1f)
                .height(54.dp),
            placeholder = { Text(text = "Preço")},
            label = { Text(text = "Preço") })

        OutlinedTextField(value = descricao, onValueChange = {newValue ->
            descricao = newValue },
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(1f)
                .heightIn(min = 100.dp),
            placeholder = { Text(text = "Descrição")},
            maxLines = Int.MAX_VALUE,
            label = { Text(text = "Descrição") })

        Button(onClick = {
            //garantir que o price fique em BigDecimal
            val convertedPrice = try {
                BigDecimal(preco)
            }catch (e: NumberFormatException){
                BigDecimal.ZERO
            }

            if (nome.isBlank() || preco.isBlank()){}
            else{
                         val addProduct = Product(name = nome,
                             price = convertedPrice,
                             image = urlImagem,
                             description = descricao)
                         addedProducts.add(addProduct)
                Log.e("ProductFormActivity", "Adicionado agora -> $addProduct")
                Log.e("ProductFormActivity", "Todos -> $addedProducts") }
                         },
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp)
            .height(36.dp)
            .fillMaxWidth(1f)) {
            Text(text = "Salvar")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ProductFormScreenPreview() {
    AluveryTheme {
        Surface {
            ProductFormScreen()
        }
    }
}