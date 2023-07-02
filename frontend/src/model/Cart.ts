
import { CartItem } from "./CartItem"
import { Order } from "./Order"
export class Cart {
    idCart!: number

    promotionAdded!: Boolean
    empty!: Boolean
    total!: number
    cartItems!: CartItem[]

    order!: Order

}

