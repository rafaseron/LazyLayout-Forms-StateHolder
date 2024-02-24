package br.com.alura.aluvery.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.alura.aluvery.R
import br.com.alura.aluvery.extensions.toBrazilianCurrency
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.sampledata.sampleProducts
import coil.compose.AsyncImage
import br.com.alura.aluvery.ui.theme.AluveryTheme
import java.math.BigDecimal

@Composable
fun CardProductItem(product: Product, elevation: Dp = 4.dp) {
    Card(Modifier.fillMaxWidth().heightIn(150.dp),
        elevation = elevation) {

        Column {
            AsyncImage(model = product.image, contentDescription = null,
                Modifier.fillMaxWidth().height(100.dp),
                placeholder = painterResource(id = R.drawable.placeholder),
                contentScale = ContentScale.Crop)

            Column(Modifier.fillMaxWidth()
                    .background(MaterialTheme.colors.primaryVariant)
                    .padding(16.dp)) {
                Text(text = product.name)
                Text(text = product.price.toBrazilianCurrency())
            }
            // TODO: adicionar descrição do produto
            product.description?.let { Text(text = it, Modifier.padding(16.dp)) }
        }
    }
}

@Preview
@Composable
private fun CardProductItemPreview() {
    AluveryTheme {
        Surface {
            CardProductItem(product = sampleProducts.random())
        }
    }
}

@Preview
@Composable
private fun CardProductItemPreviewWithDescription() {
    AluveryTheme {
        Surface {
            CardProductItem(product = Product(name = "Teste de Produto",
                price = BigDecimal(14.99),image = null,
                description = "Sou uma descricao"
            ))
        }
    }
}