package com.example.shoppingapp.domain.product

import com.example.shoppingapp.data.entity.product.ProductEntity
import com.example.shoppingapp.data.repository.ProductRepository
import com.example.shoppingapp.domain.UseCase

internal class OrderProductItemUseCase(
    private val productRepository: ProductRepository
): UseCase {

    suspend operator fun invoke(productEntity: ProductEntity): Long {
        return productRepository.insertProductItem(productEntity)
    }

}