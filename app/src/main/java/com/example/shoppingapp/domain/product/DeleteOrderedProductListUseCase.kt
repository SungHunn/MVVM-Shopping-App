package com.example.shoppingapp.domain.product

import com.example.shoppingapp.data.repository.ProductRepository
import com.example.shoppingapp.domain.UseCase

internal class DeleteOrderedProductListUseCase(
    private val productRepository: ProductRepository
): UseCase {

    suspend operator fun invoke() {
        return productRepository.deleteAll()
    }

}