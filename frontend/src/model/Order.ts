import { Cart } from "./Cart"


export class Order {
    id!: number
    shipped!:Date
    ordered!:String
    total! :string
    shippingAdresse!:string
    status!:string
    cart!: Cart
     
}

