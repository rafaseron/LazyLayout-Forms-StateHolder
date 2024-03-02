package br.com.alura.aluvery.ui.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import br.com.alura.aluvery.dao.ProductDao
import br.com.alura.aluvery.model.Product

class HomeScreenUiState() {

    private val dao = ProductDao()

    var texto by mutableStateOf("")

    fun getSearchedProducts(): List<Product>{
        return dao.listProducts().filter { p ->
            p.name.contains(texto, ignoreCase = true) ||
                    p.description?.contains(texto, ignoreCase = true) ?: false
        }
    }


}