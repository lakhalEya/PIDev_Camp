import { Cart } from "./Cart"
import { Product } from "./Product"


export class CartItem {
    id!: number
    price!: number
    quantity!: number

    cart!: Cart
    product!: Product
}

